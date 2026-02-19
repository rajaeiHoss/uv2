package com.hjq.http.exception

class DataException : HttpException {
    constructor(str: String) : super(str)

    constructor(str: String, th: Throwable) : super(str, th)
}
