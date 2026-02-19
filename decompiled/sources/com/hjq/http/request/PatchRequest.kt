package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class PatchRequest(
    lifecycleOwner: LifecycleOwner
) : BodyRequest<PatchRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.PATCH.toString()
    }
}
