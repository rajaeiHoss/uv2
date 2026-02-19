package com.hjq.http.config

import androidx.lifecycle.LifecycleOwner
import java.lang.reflect.Type
import okhttp3.Request
import okhttp3.Response

interface IRequestHandler {
    @Throws(Throwable::class)
    fun readCache(lifecycleOwner: LifecycleOwner, iRequestApi: IRequestApi, type: Type): Any? {
        return null
    }

    fun requestFail(lifecycleOwner: LifecycleOwner, iRequestApi: IRequestApi, exc: Exception): Exception

    fun requestStart(lifecycleOwner: LifecycleOwner, iRequestApi: IRequestApi, builder: Request.Builder): Request {
        return builder.build()
    }

    @Throws(Exception::class)
    fun requestSucceed(lifecycleOwner: LifecycleOwner, iRequestApi: IRequestApi, response: Response, type: Type): Any?

    @Throws(Throwable::class)
    fun writeCache(lifecycleOwner: LifecycleOwner, iRequestApi: IRequestApi, response: Response, obj: Any?): Boolean {
        return false
    }
}
