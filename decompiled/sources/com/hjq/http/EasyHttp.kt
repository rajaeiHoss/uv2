package com.hjq.http

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.request.DeleteRequest
import com.hjq.http.request.DownloadRequest
import com.hjq.http.request.GetRequest
import com.hjq.http.request.HeadRequest
import com.hjq.http.request.OptionsRequest
import com.hjq.http.request.PatchRequest
import com.hjq.http.request.PostRequest
import com.hjq.http.request.PutRequest
import com.hjq.http.request.TraceRequest
import okhttp3.Call
import okhttp3.OkHttpClient

class EasyHttp private constructor() {
    companion object {
        @JvmStatic
        fun get(lifecycleOwner: LifecycleOwner): GetRequest {
            return GetRequest(lifecycleOwner)
        }

        @JvmStatic
        fun post(lifecycleOwner: LifecycleOwner): PostRequest {
            return PostRequest(lifecycleOwner)
        }

        @JvmStatic
        fun head(lifecycleOwner: LifecycleOwner): HeadRequest {
            return HeadRequest(lifecycleOwner)
        }

        @JvmStatic
        fun delete(lifecycleOwner: LifecycleOwner): DeleteRequest {
            return DeleteRequest(lifecycleOwner)
        }

        @JvmStatic
        fun put(lifecycleOwner: LifecycleOwner): PutRequest {
            return PutRequest(lifecycleOwner)
        }

        @JvmStatic
        fun patch(lifecycleOwner: LifecycleOwner): PatchRequest {
            return PatchRequest(lifecycleOwner)
        }

        @JvmStatic
        fun options(lifecycleOwner: LifecycleOwner): OptionsRequest {
            return OptionsRequest(lifecycleOwner)
        }

        @JvmStatic
        fun trace(lifecycleOwner: LifecycleOwner): TraceRequest {
            return TraceRequest(lifecycleOwner)
        }

        @JvmStatic
        fun download(lifecycleOwner: LifecycleOwner): DownloadRequest {
            return DownloadRequest(lifecycleOwner)
        }

        @JvmStatic
        fun cancel(lifecycleOwner: LifecycleOwner) {
            cancel(lifecycleOwner.toString())
        }

        @JvmStatic
        fun cancel(obj: Any?) {
            if (obj != null) {
                val client: OkHttpClient = EasyConfig.getInstance().client
                for (next: Call in client.dispatcher().queuedCalls()) {
                    if (obj == next.request().tag()) {
                        next.cancel()
                    }
                }
                for (next2: Call in client.dispatcher().runningCalls()) {
                    if (obj == next2.request().tag()) {
                        next2.cancel()
                    }
                }
            }
        }

        @JvmStatic
        fun cancel() {
            val client: OkHttpClient = EasyConfig.getInstance().client
            for (cancel: Call in client.dispatcher().queuedCalls()) {
                cancel.cancel()
            }
            for (cancel2: Call in client.dispatcher().runningCalls()) {
                cancel2.cancel()
            }
        }
    }
}
