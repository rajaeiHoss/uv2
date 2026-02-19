package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class OptionsRequest(
    lifecycleOwner: LifecycleOwner
) : UrlRequest<OptionsRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.OPTIONS.toString()
    }
}
