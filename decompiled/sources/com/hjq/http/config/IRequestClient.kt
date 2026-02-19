package com.hjq.http.config

import com.hjq.http.EasyConfig
import okhttp3.OkHttpClient

interface IRequestClient {
    fun getClient(): OkHttpClient {
        return EasyConfig.getInstance().client
    }
}
