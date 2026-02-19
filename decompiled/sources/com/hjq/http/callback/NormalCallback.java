package com.hjq.http.callback;

import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import com.hjq.http.lifecycle.HttpLifecycleManager;
import com.hjq.http.listener.OnHttpListener;
import com.hjq.http.model.CacheMode;
import com.hjq.http.request.BaseRequest;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;

public final class NormalCallback extends BaseCallback {
    private final BaseRequest mBaseRequest;
    private OnHttpListener mListener;

    public NormalCallback(BaseRequest baseRequest) {
        super(baseRequest);
        this.mBaseRequest = baseRequest;
    }

    public NormalCallback setListener(OnHttpListener onHttpListener) {
        this.mListener = onHttpListener;
        return this;
    }

    public void start() {
        CacheMode mode = this.mBaseRequest.getRequestCache().getMode();
        if (mode == CacheMode.USE_CACHE_ONLY || mode == CacheMode.USE_CACHE_FIRST) {
            try {
                Object readCache = this.mBaseRequest.getRequestHandler().readCache(this.mBaseRequest.getLifecycleOwner(), this.mBaseRequest.getRequestApi(), EasyUtils.getReflectType(this.mListener));
                EasyLog.print("ReadCache result：" + readCache);
                if (readCache == null) {
                    super.start();
                    return;
                }
                final Object cached = readCache;
                EasyUtils.post(new Runnable() {
                    public void run() {
                        NormalCallback.this.lambda$start$0$NormalCallback(cached);
                    }
                });
                if (mode == CacheMode.USE_CACHE_FIRST) {
                    EasyUtils.postDelayed(new Runnable() {
                        public final void run() {
                            NormalCallback.this.lambda$start$1$NormalCallback();
                        }
                    }, 1);
                }
            } catch (Throwable th) {
                EasyLog.print("ReadCache error");
                EasyLog.print(th);
                super.start();
            }
        } else {
            super.start();
        }
    }

    public /* synthetic */ void lambda$start$0$NormalCallback(Object obj) {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onStart(getCall());
            this.mListener.onSucceed(obj, true);
            this.mListener.onEnd(getCall());
        }
    }

    public /* synthetic */ void lambda$start$1$NormalCallback() {
        if (HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener = null;
            super.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart(Call call) {
        final Call startCall = call;
        EasyUtils.post(new Runnable() {
            public void run() {
                NormalCallback.this.lambda$onStart$2$NormalCallback(startCall);
            }
        });
    }

    public /* synthetic */ void lambda$onStart$2$NormalCallback(Call call) {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onStart(call);
        }
    }

    /* access modifiers changed from: protected */
    public void onResponse(Response response) throws Exception {
        EasyLog.print("RequestConsuming：" + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + " ms");
        Object requestSucceed = this.mBaseRequest.getRequestHandler().requestSucceed(this.mBaseRequest.getLifecycleOwner(), this.mBaseRequest.getRequestApi(), response, EasyUtils.getReflectType(this.mListener));
        CacheMode mode = this.mBaseRequest.getRequestCache().getMode();
        if (mode == CacheMode.USE_CACHE_ONLY || mode == CacheMode.USE_CACHE_FIRST) {
            try {
                boolean writeCache = this.mBaseRequest.getRequestHandler().writeCache(this.mBaseRequest.getLifecycleOwner(), this.mBaseRequest.getRequestApi(), response, requestSucceed);
                EasyLog.print("WriteCache result：" + writeCache);
            } catch (Throwable th) {
                EasyLog.print("WriteCache error");
                EasyLog.print(th);
            }
        }
        final Object responseBody = requestSucceed;
        EasyUtils.post(new Runnable() {
            public void run() {
                NormalCallback.this.lambda$onResponse$3$NormalCallback(responseBody);
            }
        });
    }

    public /* synthetic */ void lambda$onResponse$3$NormalCallback(Object obj) {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onSucceed(obj, false);
            this.mListener.onEnd(getCall());
        }
    }

    /* access modifiers changed from: protected */
    public void onFailure(Exception exc) {
        if ((exc instanceof IOException) && this.mBaseRequest.getRequestCache().getMode() == CacheMode.USE_CACHE_AFTER_FAILURE) {
            try {
                Object readCache = this.mBaseRequest.getRequestHandler().readCache(this.mBaseRequest.getLifecycleOwner(), this.mBaseRequest.getRequestApi(), EasyUtils.getReflectType(this.mListener));
                EasyLog.print("ReadCache result：" + readCache);
                if (readCache != null) {
                    final Object cached = readCache;
                    EasyUtils.post(new Runnable() {
                        public void run() {
                            NormalCallback.this.lambda$onFailure$4$NormalCallback(cached);
                        }
                    });
                    return;
                }
            } catch (Throwable th) {
                EasyLog.print("ReadCache error");
                EasyLog.print(th);
            }
        }
        Exception requestFail = this.mBaseRequest.getRequestHandler().requestFail(this.mBaseRequest.getLifecycleOwner(), this.mBaseRequest.getRequestApi(), exc);
        EasyLog.print((Throwable) requestFail);
        final Exception error = requestFail;
        EasyUtils.post(new Runnable() {
            public void run() {
                NormalCallback.this.lambda$onFailure$5$NormalCallback(error);
            }
        });
    }

    public /* synthetic */ void lambda$onFailure$4$NormalCallback(Object obj) {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onSucceed(obj, true);
            this.mListener.onEnd(getCall());
        }
    }

    public /* synthetic */ void lambda$onFailure$5$NormalCallback(Exception exc) {
        if (this.mListener != null && HttpLifecycleManager.isLifecycleActive(this.mBaseRequest.getLifecycleOwner())) {
            this.mListener.onFail(exc);
            this.mListener.onEnd(getCall());
        }
    }
}
