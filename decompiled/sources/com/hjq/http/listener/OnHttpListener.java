package com.hjq.http.listener;

import okhttp3.Call;

public interface OnHttpListener<T> {
    void onEnd(Call call);

    void onFail(Exception exc);

    void onStart(Call call);

    void onSucceed(T t);

    void onSucceed(T t, boolean z);

    /* renamed from: com.hjq.http.listener.OnHttpListener$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onEnd(OnHttpListener onHttpListener, Call call) {
        }

        public static void $default$onStart(OnHttpListener onHttpListener, Call call) {
        }

        public static void $default$onSucceed(OnHttpListener _this, Object obj, boolean z) {
            _this.onSucceed(obj);
        }
    }
}
