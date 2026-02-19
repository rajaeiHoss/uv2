package com.hjq.http.callback

import com.hjq.http.EasyConfig
import com.hjq.http.EasyLog
import com.hjq.http.EasyUtils
import com.hjq.http.lifecycle.HttpLifecycleManager
import com.hjq.http.model.CallProxy
import com.hjq.http.request.BaseRequest
import java.io.IOException
import java.net.SocketTimeoutException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response

abstract class BaseCallback(private val mBaseRequest: BaseRequest<*>) : Callback {
    private lateinit var mCall: CallProxy
    private var mRetryCount: Int = 0

    protected abstract fun onFailure(exc: Exception)

    @Throws(Exception::class)
    protected abstract fun onResponse(response: Response)

    protected abstract fun onStart(call: Call)

    init {
        HttpLifecycleManager.bind(mBaseRequest.lifecycleOwner)
    }

    fun setCall(callProxy: CallProxy): BaseCallback {
        mCall = callProxy
        return this
    }

    open fun start() {
        mCall.enqueue(this)
        onStart(mCall)
    }

    protected fun getCall(): CallProxy {
        return mCall
    }

    override fun onResponse(call: Call, response: Response) {
        try {
            onResponse(response)
        } catch (e: Exception) {
            onFailure(e)
        } finally {
            response.close()
        }
    }

    override fun onFailure(call: Call, iOException: IOException) {
        if (iOException !is SocketTimeoutException || mRetryCount >= EasyConfig.getInstance().retryCount) {
            onFailure(iOException)
        } else {
            val retryCall = call
            EasyUtils.postDelayed(
                {
                    retry(retryCall)
                },
                EasyConfig.getInstance().retryTime
            )
        }
    }

    private fun retry(call: Call) {
        if (!HttpLifecycleManager.isLifecycleActive(mBaseRequest.lifecycleOwner)) {
            EasyLog.print("宿主已被销毁，无法对请求进行重试")
            return
        }
        mRetryCount++
        val clone = call.clone()
        mCall.setCall(clone)
        clone.enqueue(this)
        EasyLog.print("请求超时，正在延迟重试，重试次数：$mRetryCount/${EasyConfig.getInstance().retryCount}")
    }
}
