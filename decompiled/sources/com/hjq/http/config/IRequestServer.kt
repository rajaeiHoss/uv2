package com.hjq.http.config

import com.hjq.http.model.BodyType
import com.hjq.http.model.CacheMode

interface IRequestServer : IRequestHost, IRequestPath, IRequestClient, IRequestType, IRequestCache {
    override fun getPath(): String {
        return ""
    }

    override fun getType(): BodyType {
        return BodyType.FORM
    }

    override fun getMode(): CacheMode {
        return CacheMode.DEFAULT
    }
}
