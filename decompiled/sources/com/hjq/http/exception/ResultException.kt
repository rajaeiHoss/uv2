package com.hjq.http.exception

class ResultException : HttpException {
    private val mData: Any

    constructor(str: String, obj: Any) : super(str) {
        mData = obj
    }

    constructor(str: String, th: Throwable, obj: Any) : super(str, th) {
        mData = obj
    }

    fun getData(): Any {
        return mData
    }
}
