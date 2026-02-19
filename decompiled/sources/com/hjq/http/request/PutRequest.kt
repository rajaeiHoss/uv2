package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class PutRequest(
    lifecycleOwner: LifecycleOwner
) : BodyRequest<PutRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.PUT.toString()
    }
}
