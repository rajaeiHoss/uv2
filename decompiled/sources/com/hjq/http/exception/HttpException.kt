package com.hjq.http.exception

open class HttpException(str: String, th: Throwable? = null) : Exception(str, th) {
    private val mMessage: String = str

    override val message: String
        get() = mMessage
}
