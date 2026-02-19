package com.hjq.http.listener

import okhttp3.Call

interface OnHttpListener<T> {
    fun onEnd(call: Call) {
    }

    fun onFail(exc: Exception)

    fun onStart(call: Call) {
    }

    fun onSucceed(t: T)

    fun onSucceed(t: T, z: Boolean) {
        onSucceed(t)
    }
}
