package com.hjq.http.listener

interface OnUpdateListener<T> : OnHttpListener<T> {
    fun onByte(j: Long, j2: Long) {
    }

    fun onProgress(i: Int)
}
