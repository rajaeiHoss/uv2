package com.hjq.http

import com.hjq.http.config.ILogStrategy
import com.hjq.http.config.IRequestHandler
import com.hjq.http.config.IRequestInterceptor
import com.hjq.http.config.IRequestServer
import com.hjq.http.config.LogStrategy
import com.hjq.http.config.RequestServer
import java.net.MalformedURLException
import java.net.URL
import java.util.HashMap
import okhttp3.OkHttpClient

class EasyConfig private constructor(okHttpClient: OkHttpClient?) {
    private var mClient: OkHttpClient? = okHttpClient
    private var mHandler: IRequestHandler? = null
    private var mHeaders: HashMap<String, String> = HashMap()
    private var mInterceptor: IRequestInterceptor? = null
    private var mLogEnabled: Boolean = true
    private var mLogStrategy: ILogStrategy? = null
    private var mLogTag: String = "EasyHttp"
    private var mParams: HashMap<String, Any> = HashMap()
    private var mRetryCount: Int = 0
    private var mRetryTime: Long = 2000
    private var mServer: IRequestServer? = null

    fun setServer(str: String): EasyConfig {
        return setServer(RequestServer(str))
    }

    fun setServer(iRequestServer: IRequestServer?): EasyConfig {
        mServer = iRequestServer
        return this
    }

    fun setHandler(iRequestHandler: IRequestHandler?): EasyConfig {
        mHandler = iRequestHandler
        return this
    }

    fun setInterceptor(iRequestInterceptor: IRequestInterceptor?): EasyConfig {
        mInterceptor = iRequestInterceptor
        return this
    }

    fun setClient(okHttpClient: OkHttpClient?): EasyConfig {
        mClient = okHttpClient
        if (okHttpClient != null) {
            return this
        }
        throw IllegalArgumentException("The OkHttp client object cannot be empty")
    }

    fun setParams(hashMap: HashMap<String, Any>?): EasyConfig {
        mParams = hashMap ?: HashMap()
        return this
    }

    fun setHeaders(hashMap: HashMap<String, String>?): EasyConfig {
        mHeaders = hashMap ?: HashMap()
        return this
    }

    fun addHeader(str: String?, str2: String?): EasyConfig {
        if (str != null && str2 != null) {
            mHeaders[str] = str2
        }
        return this
    }

    fun removeHeader(str: String?): EasyConfig {
        if (str != null) {
            mHeaders.remove(str)
        }
        return this
    }

    fun addParam(str: String?, str2: String?): EasyConfig {
        if (str != null && str2 != null) {
            mParams[str] = str2
        }
        return this
    }

    fun removeParam(str: String?): EasyConfig {
        if (str != null) {
            mParams.remove(str)
        }
        return this
    }

    fun setLogStrategy(iLogStrategy: ILogStrategy?): EasyConfig {
        mLogStrategy = iLogStrategy
        return this
    }

    fun setLogEnabled(z: Boolean): EasyConfig {
        mLogEnabled = z
        return this
    }

    fun setLogTag(str: String): EasyConfig {
        mLogTag = str
        return this
    }

    fun setRetryCount(i: Int): EasyConfig {
        if (i >= 0) {
            mRetryCount = i
            return this
        }
        throw IllegalArgumentException("The number of retries must be greater than 0")
    }

    fun setRetryTime(j: Long): EasyConfig {
        if (j >= 0) {
            mRetryTime = j
            return this
        }
        throw IllegalArgumentException("The retry time must be greater than 0")
    }

    val server: IRequestServer
        get() = mServer ?: throw IllegalStateException("The host configuration cannot be empty")

    val handler: IRequestHandler
        get() = mHandler ?: throw IllegalStateException("The object being processed by the request cannot be empty")

    val interceptor: IRequestInterceptor?
        get() = mInterceptor

    val client: OkHttpClient
        get() = mClient ?: throw IllegalStateException("The OkHttp client object cannot be empty")

    val params: HashMap<String, Any>
        get() = mParams

    val headers: HashMap<String, String>
        get() = mHeaders

    val logStrategy: ILogStrategy
        get() = mLogStrategy ?: throw IllegalStateException("The log strategy cannot be empty")

    val isLogEnabled: Boolean
        get() = mLogEnabled && mLogStrategy != null

    val logTag: String
        get() = mLogTag

    val retryCount: Int
        get() = mRetryCount

    val retryTime: Long
        get() = mRetryTime

    fun into() {
        if (mClient == null) {
            throw IllegalArgumentException("The OkHttp client object cannot be empty")
        } else if (mServer == null) {
            throw IllegalArgumentException("The host configuration cannot be empty")
        } else if (mHandler != null) {
            try {
                URL(mServer!!.getHost() + mServer!!.getPath())
                if (mLogStrategy == null) {
                    mLogStrategy = LogStrategy()
                }
                setInstance(this)
            } catch (_: MalformedURLException) {
                throw IllegalArgumentException("The configured host path url address is not correct")
            }
        } else {
            throw IllegalArgumentException("The object being processed by the request cannot be empty")
        }
    }

    companion object {
        @Volatile
        private var sConfig: EasyConfig? = null

        @JvmStatic
        fun getInstance(): EasyConfig {
            return sConfig ?: throw IllegalStateException("You haven't initialized the configuration yet")
        }

        private fun setInstance(easyConfig: EasyConfig) {
            sConfig = easyConfig
        }

        @JvmStatic
        fun with(okHttpClient: OkHttpClient?): EasyConfig {
            return EasyConfig(okHttpClient)
        }
    }
}
