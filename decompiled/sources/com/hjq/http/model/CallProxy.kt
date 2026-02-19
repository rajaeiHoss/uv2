package com.hjq.http.model

import java.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import okio.Timeout

class CallProxy(call: Call) : Call {
    private var mCall: Call = call

    fun setCall(call: Call) {
        mCall = call
    }

    override fun request(): Request {
        return mCall.request()
    }

    @Throws(IOException::class)
    override fun execute(): Response {
        return mCall.execute()
    }

    override fun enqueue(callback: Callback) {
        mCall.enqueue(callback)
    }

    override fun cancel() {
        mCall.cancel()
    }

    override fun isExecuted(): Boolean {
        return mCall.isExecuted
    }

    override fun isCanceled(): Boolean {
        return mCall.isCanceled
    }

    override fun timeout(): Timeout {
        return mCall.timeout()
    }

    public override fun clone(): Call {
        return mCall.clone()
    }
}
