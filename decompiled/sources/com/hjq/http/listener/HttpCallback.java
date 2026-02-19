package com.hjq.http.listener;

import okhttp3.Call;

public class HttpCallback<T> implements OnHttpListener<T> {
    private final OnHttpListener mListener;

    public HttpCallback(OnHttpListener onHttpListener) {
        this.mListener = onHttpListener;
    }

    public void onStart(Call call) {
        OnHttpListener onHttpListener = this.mListener;
        if (onHttpListener != null) {
            onHttpListener.onStart(call);
        }
    }

    public void onSucceed(T t, boolean z) {
        onSucceed(t);
    }

    public void onSucceed(T t) {
        OnHttpListener onHttpListener = this.mListener;
        if (onHttpListener != null) {
            onHttpListener.onSucceed(t);
        }
    }

    public void onFail(Exception exc) {
        OnHttpListener onHttpListener = this.mListener;
        if (onHttpListener != null) {
            onHttpListener.onFail(exc);
        }
    }

    public void onEnd(Call call) {
        OnHttpListener onHttpListener = this.mListener;
        if (onHttpListener != null) {
            onHttpListener.onEnd(call);
        }
    }
}
