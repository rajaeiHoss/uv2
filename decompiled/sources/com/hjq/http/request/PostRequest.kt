package com.hjq.http.request

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.model.HttpMethod

class PostRequest(
    lifecycleOwner: LifecycleOwner
) : BodyRequest<PostRequest>(lifecycleOwner) {
    override fun getRequestMethod(): String {
        return HttpMethod.POST.toString()
    }
}
