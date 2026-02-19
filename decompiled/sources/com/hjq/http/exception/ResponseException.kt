package com.hjq.http.exception

import okhttp3.Response

class ResponseException : HttpException {
    private val mResponse: Response

    constructor(str: String, response: Response) : super(str) {
        mResponse = response
    }

    constructor(str: String, th: Throwable, response: Response) : super(str, th) {
        mResponse = response
    }

    fun getResponse(): Response {
        return mResponse
    }
}
