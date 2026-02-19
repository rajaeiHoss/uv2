package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.EasyConfig;
import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import com.hjq.http.body.JsonBody;
import com.hjq.http.body.ProgressBody;
import com.hjq.http.body.StringBody;
import com.hjq.http.body.UpdateBody;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.http.listener.OnUpdateListener;
import com.hjq.http.model.BodyType;
import com.hjq.http.model.CacheMode;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.hjq.http.request.BodyRequest;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class BodyRequest<T extends BodyRequest<?>> extends BaseRequest<T> {
    private RequestBody mRequestBody;
    private OnUpdateListener<?> mUpdateListener;

    public BodyRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    public T json(Map<?, ?> map) {
        return map == null ? self() : body(new JsonBody(map));
    }

    public T json(List<?> list) {
        return list == null ? self() : body(new JsonBody(list));
    }

    public T json(String str) {
        return str == null ? self() : body(new JsonBody(str));
    }

    public T text(String str) {
        return str == null ? self() : body(new StringBody(str));
    }

    public T body(RequestBody requestBody) {
        this.mRequestBody = requestBody;
        return self();
    }

    /* access modifiers changed from: protected */
    public Request createRequest(String str, String str2, HttpParams httpParams, HttpHeaders httpHeaders, BodyType bodyType) {
        Request.Builder builder = new Request.Builder();
        builder.url(str);
        EasyLog.print("RequestUrl", str);
        EasyLog.print("RequestMethod", getRequestMethod());
        if (str2 != null) {
            builder.tag(str2);
        }
        if (getRequestCache().getMode() == CacheMode.NO_CACHE) {
            builder.cacheControl(new CacheControl.Builder().noCache().build());
        }
        if (!httpHeaders.isEmpty()) {
            for (String next : httpHeaders.getNames()) {
                builder.addHeader(next, httpHeaders.get(next));
            }
        }
        RequestBody requestBody = this.mRequestBody;
        if (requestBody == null) {
            requestBody = createBody(httpParams, bodyType);
        }
        builder.method(getRequestMethod(), requestBody);
        if (EasyConfig.getInstance().isLogEnabled()) {
            if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print();
            }
            for (String next2 : httpHeaders.getNames()) {
                EasyLog.print(next2, httpHeaders.get(next2));
            }
            if (!httpHeaders.isEmpty() && !httpParams.isEmpty()) {
                EasyLog.print();
            }
            if ((requestBody instanceof FormBody) || (requestBody instanceof MultipartBody) || (requestBody instanceof ProgressBody)) {
                for (String next3 : httpParams.getNames()) {
                    Object obj = httpParams.get(next3);
                    if (obj instanceof String) {
                        EasyLog.print(next3, "\"" + obj + "\"");
                    } else {
                        EasyLog.print(next3, String.valueOf(obj));
                    }
                }
            } else if (requestBody instanceof JsonBody) {
                EasyLog.json(requestBody.toString());
            } else {
                EasyLog.print(requestBody.toString());
            }
            if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print();
            }
        }
        return getRequestHandler().requestStart(getLifecycleOwner(), getRequestApi(), builder);
    }

    public void request(OnHttpListener<?> onHttpListener) {
        if (onHttpListener instanceof OnUpdateListener) {
            this.mUpdateListener = (OnUpdateListener) onHttpListener;
        }
        super.request(onHttpListener);
    }

    private RequestBody createBody(HttpParams httpParams, BodyType bodyType) {
        RequestBody requestBody;
        if (httpParams.isMultipart() && !httpParams.isEmpty()) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);
            for (String next : httpParams.getNames()) {
                Object obj = httpParams.get(next);
                if (obj instanceof File) {
                    MultipartBody.Part createPart = EasyUtils.createPart(next, (File) obj);
                    if (createPart != null) {
                        builder.addPart(createPart);
                    }
                } else if (obj instanceof InputStream) {
                    MultipartBody.Part createPart2 = EasyUtils.createPart(next, (InputStream) obj);
                    if (createPart2 != null) {
                        builder.addPart(createPart2);
                    }
                } else if (obj instanceof MultipartBody.Part) {
                    builder.addPart((MultipartBody.Part) obj);
                } else if (!(obj instanceof RequestBody)) {
                    if (obj instanceof List) {
                        List<File> list = (List) obj;
                        if (EasyUtils.isFileList(list)) {
                            for (File createPart3 : list) {
                                MultipartBody.Part createPart4 = EasyUtils.createPart(next, createPart3);
                                if (createPart4 != null) {
                                    builder.addPart(createPart4);
                                }
                            }
                        }
                    }
                    builder.addFormDataPart(next, String.valueOf(obj));
                } else if (obj instanceof UpdateBody) {
                    builder.addFormDataPart(next, EasyUtils.encodeString(((UpdateBody) obj).getKeyName()), (RequestBody) obj);
                } else {
                    builder.addFormDataPart(next, (String) null, (RequestBody) obj);
                }
            }
            try {
                requestBody = builder.build();
            } catch (IllegalStateException unused) {
                requestBody = new FormBody.Builder().build();
            }
        } else if (bodyType == BodyType.JSON) {
            requestBody = new JsonBody((Map<?, ?>) httpParams.getParams());
        } else {
            FormBody.Builder builder2 = new FormBody.Builder();
            if (!httpParams.isEmpty()) {
                for (String next2 : httpParams.getNames()) {
                    builder2.add(next2, String.valueOf(httpParams.get(next2)));
                }
            }
            requestBody = builder2.build();
        }
        return this.mUpdateListener == null ? requestBody : new ProgressBody(requestBody, getLifecycleOwner(), this.mUpdateListener);
    }
}
