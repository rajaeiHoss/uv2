package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.EasyConfig
import com.hjq.http.EasyLog
import com.hjq.http.model.BodyType
import com.hjq.http.model.CacheMode
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import okhttp3.CacheControl
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody

abstract class UrlRequest<T : UrlRequest<T>>(
    lifecycleOwner: LifecycleOwner
) : BaseRequest<T>(lifecycleOwner) {
    override fun createRequest(
        str: String,
        str2: String?,
        httpParams: HttpParams,
        httpHeaders: HttpHeaders,
        bodyType: BodyType
    ): Request {
        val builder = Request.Builder()
        if (str2 != null) {
            builder.tag(str2)
        }
        if (getRequestCache().getMode() == CacheMode.NO_CACHE) {
            builder.cacheControl(CacheControl.Builder().noCache().build())
        }
        if (!httpHeaders.isEmpty()) {
            for (next in httpHeaders.getNames()) {
                builder.addHeader(next, httpHeaders.get(next))
            }
        }
        val newBuilder = HttpUrl.get(str).newBuilder()
        if (!httpParams.isEmpty()) {
            for (next2 in httpParams.getNames()) {
                newBuilder.addQueryParameter(next2, httpParams.get(next2).toString())
            }
        }
        val build = newBuilder.build()
        builder.url(build)
        builder.method(getRequestMethod(), null as RequestBody?)
        EasyLog.print("RequestUrl", build.toString())
        EasyLog.print("RequestMethod", getRequestMethod())
        if (EasyConfig.getInstance().isLogEnabled) {
            if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print()
            }
            for (next3 in httpHeaders.getNames()) {
                EasyLog.print(next3, httpHeaders.get(next3))
            }
            if (!httpHeaders.isEmpty() && !httpParams.isEmpty()) {
                EasyLog.print()
            }
            for (next4 in httpParams.getNames()) {
                EasyLog.print(next4, httpParams.get(next4).toString())
            }
            if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print()
            }
        }
        return getRequestHandler().requestStart(getLifecycleOwner(), getRequestApi(), builder)
    }
}
