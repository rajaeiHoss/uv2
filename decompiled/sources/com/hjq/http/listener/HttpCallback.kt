package com.hjq.http.listener

import okhttp3.Call

class HttpCallback<T>(
    onHttpListener: OnHttpListener<*>?
) : OnHttpListener<T> {
    private val mListener: OnHttpListener<Any?>? = onHttpListener as? OnHttpListener<Any?>

    override fun onStart(call: Call) {
        mListener?.onStart(call)
    }

    override fun onSucceed(t: T, z: Boolean) {
        onSucceed(t)
    }

    override fun onSucceed(t: T) {
        mListener?.onSucceed(t)
    }

    override fun onFail(exc: Exception) {
        mListener?.onFail(exc)
    }

    override fun onEnd(call: Call) {
        mListener?.onEnd(call)
    }
}
