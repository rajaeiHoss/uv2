package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.EasyConfig
import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import com.hjq.http.body.JsonBody
import com.hjq.http.body.ProgressBody
import com.hjq.http.body.StringBody
import com.hjq.http.body.UpdateBody
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.listener.OnUpdateListener
import com.hjq.http.model.BodyType
import com.hjq.http.model.CacheMode
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import java.io.File
import java.io.InputStream
import okhttp3.CacheControl
import okhttp3.FormBody
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody

abstract class BodyRequest<T : BodyRequest<T>>(
    lifecycleOwner: LifecycleOwner
) : BaseRequest<T>(lifecycleOwner) {
    private var mRequestBody: RequestBody? = null
    private var mUpdateListener: OnUpdateListener<*>? = null

    private fun self(): T {
        return this as T
    }

    fun json(map: Map<*, *>?): T {
        return if (map == null) self() else body(JsonBody(map))
    }

    fun json(list: List<*>?): T {
        return if (list == null) self() else body(JsonBody(list))
    }

    fun json(str: String?): T {
        return if (str == null) self() else body(JsonBody(str))
    }

    fun text(str: String?): T {
        return if (str == null) self() else body(StringBody(str))
    }

    fun body(requestBody: RequestBody): T {
        mRequestBody = requestBody
        return self()
    }

    override fun createRequest(
        str: String,
        str2: String?,
        httpParams: HttpParams,
        httpHeaders: HttpHeaders,
        bodyType: BodyType
    ): Request {
        val builder = Request.Builder()
        builder.url(str)
        EasyLog.print("RequestUrl", str)
        EasyLog.print("RequestMethod", getRequestMethod())
        if (str2 != null) {
            builder.tag(str2)
        }
        if (requestCache.getMode() == CacheMode.NO_CACHE) {
            builder.cacheControl(CacheControl.Builder().noCache().build())
        }
        if (!httpHeaders.isEmpty()) {
            for (next in httpHeaders.getNames()) {
                builder.addHeader(next, httpHeaders.get(next))
            }
        }
        var requestBody = mRequestBody
        if (requestBody == null) {
            requestBody = createBody(httpParams, bodyType)
        }
        builder.method(getRequestMethod(), requestBody)
        if (EasyConfig.getInstance().isLogEnabled) {
            if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print()
            }
            for (next2 in httpHeaders.getNames()) {
                EasyLog.print(next2, httpHeaders.get(next2))
            }
            if (!httpHeaders.isEmpty() && !httpParams.isEmpty()) {
                EasyLog.print()
            }
            if (requestBody is FormBody || requestBody is MultipartBody || requestBody is ProgressBody) {
                for (next3 in httpParams.getNames()) {
                    val obj = httpParams.get(next3)
                    if (obj is String) {
                        EasyLog.print(next3, "\"$obj\"")
                    } else {
                        EasyLog.print(next3, obj.toString())
                    }
                }
            } else if (requestBody is JsonBody) {
                EasyLog.json(requestBody.toString())
            } else {
                EasyLog.print(requestBody.toString())
            }
        if (!httpHeaders.isEmpty() || !httpParams.isEmpty()) {
                EasyLog.print()
            }
        }
        return requestHandler.requestStart(lifecycleOwner, requestApi, builder)
    }

    override fun request(onHttpListener: OnHttpListener<*>?) {
        if (onHttpListener is OnUpdateListener<*>) {
            mUpdateListener = onHttpListener
        }
        super.request(onHttpListener)
    }

    private fun createBody(httpParams: HttpParams, bodyType: BodyType): RequestBody {
        val requestBody: RequestBody = if (httpParams.isMultipart() && !httpParams.isEmpty()) {
            val builder = MultipartBody.Builder()
            builder.setType(MultipartBody.FORM)
            for (next in httpParams.getNames()) {
                val obj = httpParams.get(next)
                if (obj is File) {
                    val createPart = EasyUtils.createPart(next, obj)
                    if (createPart != null) {
                        builder.addPart(createPart)
                    }
                } else if (obj is InputStream) {
                    val createPart2 = EasyUtils.createPart(next, obj)
                    if (createPart2 != null) {
                        builder.addPart(createPart2)
                    }
                } else if (obj is MultipartBody.Part) {
                    builder.addPart(obj)
                } else if (obj is RequestBody) {
                    if (obj is UpdateBody) {
                        builder.addFormDataPart(next, EasyUtils.encodeString(obj.getKeyName()), obj)
                    } else {
                        builder.addFormDataPart(next, null, obj)
                    }
                } else {
                    if (obj is List<*>) {
                        if (EasyUtils.isFileList(obj)) {
                            for (file in obj) {
                                val createPart4 = EasyUtils.createPart(next, file as File)
                                if (createPart4 != null) {
                                    builder.addPart(createPart4)
                                }
                            }
                        }
                    }
                    builder.addFormDataPart(next, obj.toString())
                }
            }
            try {
                builder.build()
            } catch (_: IllegalStateException) {
                FormBody.Builder().build()
            }
        } else if (bodyType == BodyType.JSON) {
            JsonBody(httpParams.getParams() as Map<*, *>)
        } else {
            val builder2 = FormBody.Builder()
            if (!httpParams.isEmpty()) {
                for (next2 in httpParams.getNames()) {
                    builder2.add(next2, httpParams.get(next2).toString())
                }
            }
            builder2.build()
        }
        return if (mUpdateListener == null) {
            requestBody
        } else {
            ProgressBody(requestBody, lifecycleOwner, mUpdateListener)
        }
    }
}
