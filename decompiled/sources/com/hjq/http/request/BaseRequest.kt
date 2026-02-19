package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.EasyConfig
import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import com.hjq.http.annotation.HttpHeader
import com.hjq.http.annotation.HttpIgnore
import com.hjq.http.annotation.HttpRename
import com.hjq.http.callback.NormalCallback
import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestCache
import com.hjq.http.config.IRequestClient
import com.hjq.http.config.IRequestHandler
import com.hjq.http.config.IRequestHost
import com.hjq.http.config.IRequestPath
import com.hjq.http.config.IRequestServer
import com.hjq.http.config.IRequestType
import com.hjq.http.config.RequestApi
import com.hjq.http.config.RequestServer
import com.hjq.http.lifecycle.HttpLifecycleManager
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.model.BodyType
import com.hjq.http.model.CacheMode
import com.hjq.http.model.CallProxy
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import com.hjq.http.model.ResponseClass
import java.io.IOException
import java.lang.reflect.Field
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response

abstract class BaseRequest<T : BaseRequest<T>>(
    private val mLifecycleOwner: LifecycleOwner
) {
    private var mCallProxy: CallProxy? = null
    private var mDelayMillis: Long = 0
    private var mRequestApi: IRequestApi? = null
    private var mRequestCache: IRequestCache = EasyConfig.getInstance().server
    private var mRequestClient: IRequestClient = EasyConfig.getInstance().server
    private var mRequestHandler: IRequestHandler = EasyConfig.getInstance().handler
    private var mRequestHost: IRequestHost = EasyConfig.getInstance().server
    private var mRequestPath: IRequestPath = EasyConfig.getInstance().server
    private var mRequestType: IRequestType = EasyConfig.getInstance().server
    private var mTag: String? = null

    init {
        tag(mLifecycleOwner)
    }

    abstract fun createRequest(
        str: String,
        str2: String?,
        httpParams: HttpParams,
        httpHeaders: HttpHeaders,
        bodyType: BodyType
    ): Request

    abstract fun getRequestMethod(): String

    private fun self(): T {
        return this as T
    }

    fun api(cls: Class<out IRequestApi>): T {
        return try {
            api(cls.newInstance())
        } catch (e: InstantiationException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }

    fun api(str: String): T {
        return api(RequestApi(str))
    }

    fun api(iRequestApi: IRequestApi): T {
        mRequestApi = iRequestApi
        if (iRequestApi is IRequestHost) {
            mRequestHost = iRequestApi
        }
        if (iRequestApi is IRequestPath) {
            mRequestPath = iRequestApi
        }
        if (iRequestApi is IRequestClient) {
            mRequestClient = iRequestApi
        }
        if (iRequestApi is IRequestType) {
            mRequestType = iRequestApi
        }
        if (iRequestApi is IRequestCache) {
            mRequestCache = iRequestApi
        }
        if (iRequestApi is IRequestHandler) {
            mRequestHandler = iRequestApi
        }
        return self()
    }

    fun server(cls: Class<out IRequestServer>): T {
        return try {
            server(cls.newInstance())
        } catch (e: InstantiationException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }

    fun server(str: String): T {
        return server(RequestServer(str))
    }

    fun server(iRequestServer: IRequestServer): T {
        mRequestHost = iRequestServer
        mRequestPath = iRequestServer
        mRequestClient = iRequestServer
        mRequestType = iRequestServer
        mRequestCache = iRequestServer
        return self()
    }

    fun delay(j: Long, timeUnit: TimeUnit): T {
        return delay(timeUnit.toMillis(j))
    }

    fun delay(j: Long): T {
        mDelayMillis = j
        return self()
    }

    fun handler(iRequestHandler: IRequestHandler): T {
        mRequestHandler = iRequestHandler
        return self()
    }

    fun tag(obj: Any?): T {
        return if (obj != null) tag(obj.toString()) else self()
    }

    fun tag(str: String?): T {
        mTag = str
        return self()
    }

    protected fun createCall(): Call {
        var type = mRequestType.getType()
        val httpParams = HttpParams()
        val httpHeaders = HttpHeaders()
        val fieldList = ArrayList<Field>()
        var cls: Class<*>? = requestApi.javaClass
        while (cls != null && cls != Any::class.java) {
            fieldList.addAll(0, cls.declaredFields.toList())
            cls = cls.superclass
        }
        httpParams.setMultipart(EasyUtils.isMultipart(fieldList))
        if (httpParams.isMultipart() && type != BodyType.FORM) {
            type = BodyType.FORM
        }
        val bodyType = type
        for (field in fieldList) {
            field.isAccessible = true
            try {
                val obj = field.get(requestApi)
                val httpRename = field.getAnnotation(HttpRename::class.java)
                val key = if (httpRename != null) {
                    httpRename.value
                } else {
                    field.name
                }
                if (key.matches("this\\$\\d+".toRegex()) || "Companion" == key) {
                    continue
                }
                if (field.isAnnotationPresent(HttpIgnore::class.java)) {
                    if (field.isAnnotationPresent(HttpHeader::class.java)) {
                        httpHeaders.remove(key)
                    } else {
                        httpParams.remove(key)
                    }
                } else if (!EasyUtils.isEmpty(obj)) {
                    if (!field.isAnnotationPresent(HttpHeader::class.java)) {
                        if (bodyType == BodyType.FORM) {
                            if (obj is Map<*, *>) {
                                for (next in obj.keys) {
                                    val value = obj[next]
                                    if (next != null && value != null) {
                                        httpParams.put(next.toString(), value)
                                    }
                                }
                            } else {
                                httpParams.put(key, obj)
                            }
                        } else if (bodyType == BodyType.JSON) {
                            when {
                                obj is List<*> -> httpParams.put(key, EasyUtils.listToJsonArray(obj))
                                obj is Map<*, *> -> httpParams.put(key, EasyUtils.mapToJsonObject(obj))
                                EasyUtils.isBeanType(obj) -> httpParams.put(
                                    key,
                                    EasyUtils.mapToJsonObject(EasyUtils.beanToHashMap(obj))
                                )

                                else -> httpParams.put(key, obj)
                            }
                        }
                    } else if (obj is Map<*, *>) {
                        for (next2 in obj.keys) {
                            val value2 = obj[next2]
                            if (next2 != null && value2 != null) {
                                httpHeaders.put(next2.toString(), value2.toString())
                            }
                        }
                    } else {
                        httpHeaders.put(key, obj.toString())
                    }
                }
            } catch (e: IllegalAccessException) {
                EasyLog.print(e)
            }
        }
        val requestUrl = mRequestHost.getHost() + mRequestPath.getPath() + requestApi.getApi()
        val interceptor = EasyConfig.getInstance().interceptor
        if (interceptor != null) {
            interceptor.interceptArguments(requestApi, httpParams, httpHeaders)
        }
        val request = createRequest(requestUrl, mTag, httpParams, httpHeaders, bodyType)
        requireNotNull(request) { "The request object cannot be empty" }
        return mRequestClient.getClient().newCall(request)
    }

    open fun request(onHttpListener: OnHttpListener<*>?) {
        val j = mDelayMillis
        if (j > 0) {
            EasyLog.print("RequestDelay", j.toString())
        }
        val stackTrace = Throwable().stackTrace
        val listener = onHttpListener
        EasyUtils.postDelayed(
            Runnable { requestInternal(stackTrace, listener) },
            mDelayMillis
        )
    }

    private fun requestInternal(stackTraceElementArr: Array<StackTraceElement>, onHttpListener: OnHttpListener<*>?) {
        if (!HttpLifecycleManager.isLifecycleActive(mLifecycleOwner)) {
            EasyLog.print("宿主已被销毁，请求无法进行")
            return
        }
        EasyLog.print(stackTraceElementArr)
        mCallProxy = CallProxy(createCall())
        NormalCallback(this).setListener(onHttpListener).setCall(mCallProxy!!).start()
    }

    @Throws(Exception::class)
    open fun <Bean> execute(responseClass: ResponseClass<Bean>): Bean {
        val j = mDelayMillis
        if (j > 0) {
            EasyLog.print("RequestDelay", j.toString())
            Thread.sleep(mDelayMillis)
        }
        var requestException: Exception? = null
        if (HttpLifecycleManager.isLifecycleActive(mLifecycleOwner)) {
            EasyLog.print(Throwable().stackTrace)
            val reflectType: Type = EasyUtils.getReflectType(responseClass)
            mCallProxy = CallProxy(createCall())
            val mode = requestCache.getMode()
            if (mode == CacheMode.USE_CACHE_ONLY || mode == CacheMode.USE_CACHE_FIRST) {
                try {
                    val readCache = mRequestHandler.readCache(mLifecycleOwner, requestApi, reflectType) as Bean?
                    EasyLog.print("ReadCache result：$readCache")
                    if (mode == CacheMode.USE_CACHE_FIRST) {
                        NormalCallback(this).setCall(mCallProxy!!).start()
                    }
                    if (readCache != null) {
                        return readCache
                    }
                } catch (th: Throwable) {
                    EasyLog.print("ReadCache error")
                    EasyLog.print(th)
                }
            }
            try {
                val execute: Response = mCallProxy!!.execute()
                val requestSucceed = mRequestHandler.requestSucceed(mLifecycleOwner, requestApi, execute, reflectType) as Bean
                if (mode == CacheMode.USE_CACHE_ONLY) {
                    try {
                        val writeCache = mRequestHandler.writeCache(mLifecycleOwner, requestApi, execute, requestSucceed)
                        EasyLog.print("WriteCache result：$writeCache")
                    } catch (th2: Throwable) {
                        EasyLog.print("WriteCache error")
                        EasyLog.print(th2)
                    }
                }
                return requestSucceed
            } catch (e: Exception) {
                requestException = e
                if (e is IOException && mode == CacheMode.USE_CACHE_AFTER_FAILURE) {
                    try {
                        val readCache2 = mRequestHandler.readCache(mLifecycleOwner, requestApi, reflectType) as Bean?
                        EasyLog.print("ReadCache result：$readCache2")
                        if (readCache2 != null) {
                            return readCache2
                        }
                    } catch (th4: Throwable) {
                        EasyLog.print("ReadCache error")
                        EasyLog.print(th4)
                    }
                }
            } catch (th3: Throwable) {
                requestException = Exception(th3)
                EasyLog.print("ReadCache error")
                EasyLog.print(th3)
            }
        } else {
            EasyLog.print("宿主已被销毁，请求无法进行")
            throw IllegalStateException("The host has been destroyed and the request cannot proceed")
        }
        throw mRequestHandler.requestFail(mLifecycleOwner, requestApi, requestException ?: Exception("Unknown request error"))
    }

    open fun cancel(): T {
        mCallProxy?.cancel()
        return self()
    }

    val lifecycleOwner: LifecycleOwner
        get() = mLifecycleOwner

    val requestApi: IRequestApi
        get() = mRequestApi ?: throw IllegalStateException("The request api object cannot be empty")

    val requestHandler: IRequestHandler
        get() = mRequestHandler

    val requestCache: IRequestCache
        get() = mRequestCache

    protected val delayMillis: Long
        get() = mDelayMillis
}
