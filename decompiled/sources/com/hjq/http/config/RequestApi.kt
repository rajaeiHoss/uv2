package com.hjq.http.config

import com.hjq.http.annotation.HttpIgnore

class RequestApi(str: String) : IRequestApi {
    @field:HttpIgnore
    private val mApi: String = str

    override fun getApi(): String {
        return mApi
    }

    override fun toString(): String {
        return mApi
    }
}
