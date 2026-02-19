package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class GetRequest(
    lifecycleOwner: LifecycleOwner
) : UrlRequest<GetRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.GET.toString()
    }
}
