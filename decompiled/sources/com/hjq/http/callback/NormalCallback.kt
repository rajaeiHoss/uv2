package com.hjq.http.callback

import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import com.hjq.http.lifecycle.HttpLifecycleManager
import com.hjq.http.listener.OnHttpListener
import com.hjq.http.model.CacheMode
import com.hjq.http.request.BaseRequest
import java.io.IOException
import okhttp3.Call
import okhttp3.Response

class NormalCallback(baseRequest: BaseRequest<*>) : BaseCallback(baseRequest) {
    private val mBaseRequest: BaseRequest<*> = baseRequest
    private var mListener: OnHttpListener<Any?>? = null

    fun setListener(onHttpListener: OnHttpListener<*>?): NormalCallback {
        mListener = onHttpListener as? OnHttpListener<Any?>
        return this
    }

    override fun start() {
        val mode = mBaseRequest.requestCache.getMode()
        if (mode == CacheMode.USE_CACHE_ONLY || mode == CacheMode.USE_CACHE_FIRST) {
            try {
                val readCache = mBaseRequest.requestHandler.readCache(
                    mBaseRequest.lifecycleOwner,
                    mBaseRequest.requestApi,
                    EasyUtils.getReflectType(mListener)
                )
                EasyLog.print("ReadCache result：$readCache")
                if (readCache == null) {
                    startRequest()
                    return
                }
                val cached = readCache
                EasyUtils.post {
                    if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                        mListener?.onStart(getCall())
                        mListener?.onSucceed(cached, true)
                        mListener?.onEnd(getCall())
                    }
                }
                if (mode == CacheMode.USE_CACHE_FIRST) {
                    EasyUtils.postDelayed(
                        {
                            if (HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                                mListener = null
                                startRequest()
                            }
                        },
                        1
                    )
                }
            } catch (th: Throwable) {
                EasyLog.print("ReadCache error")
                EasyLog.print(th)
                startRequest()
            }
        } else {
            startRequest()
        }
    }

    private fun startRequest() {
        super.start()
    }

    override fun onStart(call: Call) {
        val startCall = call
        EasyUtils.post {
            if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                mListener?.onStart(startCall)
            }
        }
    }

    @Throws(Exception::class)
    override fun onResponse(response: Response) {
        EasyLog.print("RequestConsuming：" + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + " ms")
        val requestSucceed = mBaseRequest.requestHandler.requestSucceed(
            mBaseRequest.lifecycleOwner,
            mBaseRequest.requestApi,
            response,
            EasyUtils.getReflectType(mListener)
        )
        val mode = mBaseRequest.requestCache.getMode()
        if (mode == CacheMode.USE_CACHE_ONLY || mode == CacheMode.USE_CACHE_FIRST) {
            try {
                val writeCache = mBaseRequest.requestHandler.writeCache(
                    mBaseRequest.lifecycleOwner,
                    mBaseRequest.requestApi,
                    response,
                    requestSucceed
                )
                EasyLog.print("WriteCache result：$writeCache")
            } catch (th: Throwable) {
                EasyLog.print("WriteCache error")
                EasyLog.print(th)
            }
        }
        val responseBody = requestSucceed
        EasyUtils.post {
            if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                mListener?.onSucceed(responseBody, false)
                mListener?.onEnd(getCall())
            }
        }
    }

    override fun onFailure(exc: Exception) {
        if (exc is IOException && mBaseRequest.requestCache.getMode() == CacheMode.USE_CACHE_AFTER_FAILURE) {
            try {
                val readCache = mBaseRequest.requestHandler.readCache(
                    mBaseRequest.lifecycleOwner,
                    mBaseRequest.requestApi,
                    EasyUtils.getReflectType(mListener)
                )
                EasyLog.print("ReadCache result：$readCache")
                if (readCache != null) {
                    val cached = readCache
                    EasyUtils.post {
                        if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                            mListener?.onSucceed(cached, true)
                            mListener?.onEnd(getCall())
                        }
                    }
                    return
                }
            } catch (th: Throwable) {
                EasyLog.print("ReadCache error")
                EasyLog.print(th)
            }
        }
        val requestFail = mBaseRequest.requestHandler.requestFail(
            mBaseRequest.lifecycleOwner,
            mBaseRequest.requestApi,
            exc
        )
        EasyLog.print(requestFail)
        val error = requestFail
        EasyUtils.post {
            if (mListener != null && HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
                mListener?.onFail(error)
                mListener?.onEnd(getCall())
            }
        }
    }
}
