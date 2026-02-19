package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class TraceRequest(
    lifecycleOwner: LifecycleOwner
) : UrlRequest<TraceRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.TRACE.toString()
    }
}
