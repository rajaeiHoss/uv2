package com.hjq.http.config

import com.hjq.http.model.CacheMode

interface IRequestCache {
    fun getMode(): CacheMode
}
