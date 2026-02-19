package com.hjq.http.config

import com.hjq.http.annotation.HttpIgnore

class RequestServer : IRequestServer {
    @field:HttpIgnore
    private val mHost: String

    @field:HttpIgnore
    private val mPath: String

    constructor(str: String) : this(str, "")

    constructor(str: String, str2: String) {
        mHost = str
        mPath = str2
    }

    override fun getHost(): String {
        return mHost
    }

    override fun getPath(): String {
        return mPath
    }

    override fun toString(): String {
        return mHost + mPath
    }
}
