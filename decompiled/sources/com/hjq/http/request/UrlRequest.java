package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.EasyConfig;
import com.hjq.http.EasyLog;
import com.hjq.http.model.BodyType;
import com.hjq.http.model.CacheMode;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.hjq.http.request.UrlRequest;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

public abstract class UrlRequest<T extends UrlRequest<?>> extends BaseRequest<T> {
    public UrlRequest(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public Request createRequest(String str, String str2, HttpParams httpParams, HttpHeaders httpHeaders, BodyType bodyType) {
        Request.Builder builder = new Request.Builder();
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
        HttpUrl.Builder newBuilder = HttpUrl.get(str).newBuilder();
        if (!httpParams.isEmpty()) {
            for (String next2 : httpParams.getNames()) {
                newBuilder.addQueryParameter(next2, String.valueOf(httpParams.get(next2)));
            }
        }
        HttpUrl build = newBuilder.build();
        builder.url(build);
        builder.method(getRequestMethod(), (RequestBody) null);
        EasyLog.print("RequestUrl", String.valueOf(build));
        EasyLog.print("RequestMethod", getRequestMethod());
        if (EasyConfig.getInstance().isLogEnabled()) {
            if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print();
            }
            for (String next3 : httpHeaders.getNames()) {
                EasyLog.print(next3, httpHeaders.get(next3));
            }
            if (!httpHeaders.isEmpty() && !httpParams.isEmpty()) {
                EasyLog.print();
            }
            for (String next4 : httpParams.getNames()) {
                EasyLog.print(next4, String.valueOf(httpParams.get(next4)));
            }
            if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print();
            }
        }
        return getRequestHandler().requestStart(getLifecycleOwner(), getRequestApi(), builder);
    }
}
