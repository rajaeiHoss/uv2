package com.hjq.http.listener

import java.io.File

interface OnDownloadListener {
    fun onByte(file: File, j: Long, j2: Long) {
    }

    fun onComplete(file: File)

    fun onEnd(file: File)

    fun onError(file: File, exc: Exception)

    fun onProgress(file: File, i: Int)

    fun onStart(file: File)
}
