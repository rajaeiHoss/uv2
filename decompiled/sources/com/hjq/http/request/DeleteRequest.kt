package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class DeleteRequest(
    lifecycleOwner: LifecycleOwner
) : UrlRequest<DeleteRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.DELETE.toString()
    }
}
