package com.hjq.http.request;

import androidx.lifecycle.LifecycleOwner;
import com.hjq.http.EasyConfig;
import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import com.hjq.http.annotation.HttpHeader;
import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.annotation.HttpRename;
import com.hjq.http.callback.NormalCallback;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestCache;
import com.hjq.http.config.IRequestClient;
import com.hjq.http.config.IRequestHandler;
import com.hjq.http.config.IRequestHost;
import com.hjq.http.config.IRequestInterceptor;
import com.hjq.http.config.IRequestPath;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.config.IRequestType;
import com.hjq.http.config.RequestApi;
import com.hjq.http.config.RequestServer;
import com.hjq.http.lifecycle.HttpLifecycleManager;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.http.model.BodyType;
import com.hjq.http.model.CacheMode;
import com.hjq.http.model.CallProxy;
import com.hjq.http.model.HttpHeaders;
import com.hjq.http.model.HttpParams;
import com.hjq.http.model.ResponseClass;
import com.hjq.http.request.BaseRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public abstract class BaseRequest<T extends BaseRequest<?>> {
    private CallProxy mCallProxy;
    private long mDelayMillis;
    private final LifecycleOwner mLifecycleOwner;
    private IRequestApi mRequestApi;
    private IRequestCache mRequestCache = EasyConfig.getInstance().getServer();
    private IRequestClient mRequestClient = EasyConfig.getInstance().getServer();
    private IRequestHandler mRequestHandler = EasyConfig.getInstance().getHandler();
    private IRequestHost mRequestHost = EasyConfig.getInstance().getServer();
    private IRequestPath mRequestPath = EasyConfig.getInstance().getServer();
    private IRequestType mRequestType = EasyConfig.getInstance().getServer();
    private String mTag;

    /* access modifiers changed from: protected */
    public abstract Request createRequest(String str, String str2, HttpParams httpParams, HttpHeaders httpHeaders, BodyType bodyType);

    /* access modifiers changed from: protected */
    public abstract String getRequestMethod();

    public BaseRequest(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
        tag((Object) lifecycleOwner);
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    public T api(Class<? extends IRequestApi> cls) {
        try {
            return api((IRequestApi) cls.newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public T api(String str) {
        return api((IRequestApi) new RequestApi(str));
    }

    public T api(IRequestApi iRequestApi) {
        this.mRequestApi = iRequestApi;
        if (iRequestApi instanceof IRequestHost) {
            this.mRequestHost = (IRequestHost) iRequestApi;
        }
        if (iRequestApi instanceof IRequestPath) {
            this.mRequestPath = (IRequestPath) iRequestApi;
        }
        if (iRequestApi instanceof IRequestClient) {
            this.mRequestClient = (IRequestClient) iRequestApi;
        }
        if (iRequestApi instanceof IRequestType) {
            this.mRequestType = (IRequestType) iRequestApi;
        }
        if (iRequestApi instanceof IRequestCache) {
            this.mRequestCache = (IRequestCache) iRequestApi;
        }
        if (iRequestApi instanceof IRequestHandler) {
            this.mRequestHandler = (IRequestHandler) iRequestApi;
        }
        return self();
    }

    public T server(Class<? extends IRequestServer> cls) {
        try {
            return server((IRequestServer) cls.newInstance());
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public T server(String str) {
        return server((IRequestServer) new RequestServer(str));
    }

    public T server(IRequestServer iRequestServer) {
        this.mRequestHost = iRequestServer;
        this.mRequestPath = iRequestServer;
        this.mRequestClient = iRequestServer;
        this.mRequestType = iRequestServer;
        this.mRequestCache = iRequestServer;
        return self();
    }

    public T delay(long j, TimeUnit timeUnit) {
        return delay(timeUnit.toMillis(j));
    }

    public T delay(long j) {
        this.mDelayMillis = j;
        return self();
    }

    public T handler(IRequestHandler iRequestHandler) {
        this.mRequestHandler = iRequestHandler;
        return self();
    }

    public T tag(Object obj) {
        return obj != null ? tag(String.valueOf(obj)) : self();
    }

    public T tag(String str) {
        this.mTag = str;
        return self();
    }

    /* access modifiers changed from: protected */
    public Call createCall() {
        String str;
        BodyType type = this.mRequestType.getType();
        HttpParams httpParams = new HttpParams();
        HttpHeaders httpHeaders = new HttpHeaders();
        ArrayList<Field> arrayList = new ArrayList<>();
        Class cls = this.mRequestApi.getClass();
        do {
            arrayList.addAll(0, Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
            if (cls == null || Object.class.equals(cls)) {
                httpParams.setMultipart(EasyUtils.isMultipart(arrayList));
            }
            arrayList.addAll(0, Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
            break;
        } while (Object.class.equals(cls));
        httpParams.setMultipart(EasyUtils.isMultipart(arrayList));
        if (httpParams.isMultipart() && type != BodyType.FORM) {
            type = BodyType.FORM;
        }
        BodyType bodyType = type;
        for (Field field : arrayList) {
            field.setAccessible(true);
            try {
                Object obj = field.get(this.mRequestApi);
                HttpRename httpRename = (HttpRename) field.getAnnotation(HttpRename.class);
                if (httpRename != null) {
                    str = httpRename.value();
                } else {
                    str = field.getName();
                    if (!str.matches("this\\$\\d+")) {
                        if ("Companion".equals(str)) {
                        }
                    }
                }
                if (field.isAnnotationPresent(HttpIgnore.class)) {
                    if (field.isAnnotationPresent(HttpHeader.class)) {
                        httpHeaders.remove(str);
                    } else {
                        httpParams.remove(str);
                    }
                } else if (!EasyUtils.isEmpty(obj)) {
                    if (!field.isAnnotationPresent(HttpHeader.class)) {
                        int i = AnonymousClass1.$SwitchMap$com$hjq$http$model$BodyType[bodyType.ordinal()];
                        if (i != 1) {
                            if (i == 2) {
                                if (obj instanceof List) {
                                    httpParams.put(str, EasyUtils.listToJsonArray((List) obj));
                                } else if (obj instanceof Map) {
                                    httpParams.put(str, EasyUtils.mapToJsonObject((Map) obj));
                                } else if (EasyUtils.isBeanType(obj)) {
                                    httpParams.put(str, EasyUtils.mapToJsonObject(EasyUtils.beanToHashMap(obj)));
                                } else {
                                    httpParams.put(str, obj);
                                }
                            }
                        } else if (obj instanceof Map) {
                            Map map = (Map) obj;
                            for (Object next : map.keySet()) {
                                if (!(next == null || map.get(next) == null)) {
                                    httpParams.put(String.valueOf(next), map.get(next));
                                }
                            }
                        } else {
                            httpParams.put(str, obj);
                        }
                    } else if (obj instanceof Map) {
                        Map map2 = (Map) obj;
                        for (Object next2 : map2.keySet()) {
                            if (!(next2 == null || map2.get(next2) == null)) {
                                httpHeaders.put(String.valueOf(next2), String.valueOf(map2.get(next2)));
                            }
                        }
                    } else {
                        httpHeaders.put(str, String.valueOf(obj));
                    }
                }
            } catch (IllegalAccessException e) {
                EasyLog.print((Throwable) e);
            }
        }
        String str2 = this.mRequestHost.getHost() + this.mRequestPath.getPath() + this.mRequestApi.getApi();
        IRequestInterceptor interceptor = EasyConfig.getInstance().getInterceptor();
        if (interceptor != null) {
            interceptor.interceptArguments(this.mRequestApi, httpParams, httpHeaders);
        }
        Request createRequest = createRequest(str2, this.mTag, httpParams, httpHeaders, bodyType);
        Objects.requireNonNull(createRequest, "The request object cannot be empty");
        return this.mRequestClient.getClient().newCall(createRequest);
    }

    /* renamed from: com.hjq.http.request.BaseRequest$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hjq$http$model$BodyType;

        static {
            int[] iArr = new int[com.hjq.http.model.BodyType.values().length];
            $SwitchMap$com$hjq$http$model$BodyType = iArr;
            try {
                iArr[com.hjq.http.model.BodyType.FORM.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.hjq.http.model.BodyType.JSON.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void request(OnHttpListener<?> onHttpListener) {
        long j = this.mDelayMillis;
        if (j > 0) {
            EasyLog.print("RequestDelay", String.valueOf(j));
        }
        final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        final OnHttpListener<?> listener = onHttpListener;
        EasyUtils.postDelayed(new Runnable() {
            public void run() {
                BaseRequest.this.lambda$request$0$BaseRequest(stackTrace, listener);
            }
        }, this.mDelayMillis);
    }

    public /* synthetic */ void lambda$request$0$BaseRequest(StackTraceElement[] stackTraceElementArr, OnHttpListener onHttpListener) {
        if (!HttpLifecycleManager.isLifecycleActive(this.mLifecycleOwner)) {
            EasyLog.print("宿主已被销毁，请求无法进行");
            return;
        }
        EasyLog.print(stackTraceElementArr);
        this.mCallProxy = new CallProxy(createCall());
        new NormalCallback(this).setListener(onHttpListener).setCall(this.mCallProxy).start();
    }

    public <Bean> Bean execute(ResponseClass<Bean> responseClass) throws Exception {
        long j = this.mDelayMillis;
        if (j > 0) {
            EasyLog.print("RequestDelay", String.valueOf(j));
            Thread.sleep(this.mDelayMillis);
        }
        Exception requestException = null;
        if (HttpLifecycleManager.isLifecycleActive(this.mLifecycleOwner)) {
            EasyLog.print(new Throwable().getStackTrace());
            Type reflectType = EasyUtils.getReflectType(responseClass);
            this.mCallProxy = new CallProxy(createCall());
            CacheMode mode = getRequestCache().getMode();
            if (mode == CacheMode.USE_CACHE_ONLY || mode == CacheMode.USE_CACHE_FIRST) {
                try {
                    Bean readCache = (Bean) this.mRequestHandler.readCache(this.mLifecycleOwner, this.mRequestApi, reflectType);
                    EasyLog.print("ReadCache result：" + readCache);
                    if (mode == CacheMode.USE_CACHE_FIRST) {
                        new NormalCallback(this).setCall(this.mCallProxy).start();
                    }
                    if (readCache != null) {
                        return readCache;
                    }
                } catch (Throwable th) {
                    EasyLog.print("ReadCache error");
                    EasyLog.print(th);
                }
            }
            try {
                Response execute = this.mCallProxy.execute();
                Bean requestSucceed = (Bean) this.mRequestHandler.requestSucceed(this.mLifecycleOwner, this.mRequestApi, execute, reflectType);
                if (mode == CacheMode.USE_CACHE_ONLY) {
                    try {
                        boolean writeCache = this.mRequestHandler.writeCache(this.mLifecycleOwner, this.mRequestApi, execute, requestSucceed);
                        EasyLog.print("WriteCache result：" + writeCache);
                    } catch (Throwable th2) {
                        EasyLog.print("WriteCache error");
                        EasyLog.print(th2);
                    }
                }
                return requestSucceed;
            } catch (Exception e) {
                requestException = e;
                if ((e instanceof IOException) && mode == CacheMode.USE_CACHE_AFTER_FAILURE) {
                    try {
                        Bean readCache2 = (Bean) this.mRequestHandler.readCache(this.mLifecycleOwner, this.mRequestApi, reflectType);
                        EasyLog.print("ReadCache result：" + readCache2);
                        if (readCache2 != null) {
                            return readCache2;
                        }
                    } catch (Throwable th4) {
                        EasyLog.print("ReadCache error");
                        EasyLog.print(th4);
                    }
                }
            } catch (Throwable th3) {
                requestException = new Exception(th3);
                EasyLog.print("ReadCache error");
                EasyLog.print(th3);
            }
        } else {
            EasyLog.print("宿主已被销毁，请求无法进行");
            throw new IllegalStateException("The host has been destroyed and the request cannot proceed");
        }
        throw this.mRequestHandler.requestFail(this.mLifecycleOwner, this.mRequestApi, requestException);
    }

    public T cancel() {
        CallProxy callProxy = this.mCallProxy;
        if (callProxy != null) {
            callProxy.cancel();
        }
        return self();
    }

    public LifecycleOwner getLifecycleOwner() {
        return this.mLifecycleOwner;
    }

    public IRequestApi getRequestApi() {
        return this.mRequestApi;
    }

    public IRequestHandler getRequestHandler() {
        return this.mRequestHandler;
    }

    public IRequestCache getRequestCache() {
        return this.mRequestCache;
    }

    /* access modifiers changed from: protected */
    public long getDelayMillis() {
        return this.mDelayMillis;
    }
}
