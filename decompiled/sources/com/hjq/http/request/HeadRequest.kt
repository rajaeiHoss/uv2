package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class HeadRequest(
    lifecycleOwner: LifecycleOwner
) : UrlRequest<HeadRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.HEAD.toString()
    }
}
