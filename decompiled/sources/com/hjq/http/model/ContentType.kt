package com.hjq.http.model

import android.text.TextUtils
import java.net.URLConnection
import okhttp3.MediaType

class ContentType private constructor() {
    companion object {
        @JvmField
        val JSON: MediaType = MediaType.parse("application/json; charset=utf-8")!!

        @JvmField
        val STREAM: MediaType = MediaType.parse("application/octet-stream")!!

        @JvmField
        val TEXT: MediaType = MediaType.parse("text/plain; charset=utf-8")!!

        @JvmStatic
        fun guessMimeType(str: String?): MediaType {
            if (TextUtils.isEmpty(str)) {
                return STREAM
            }
            val contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str!!.replace("#", ""))
            if (contentTypeFor == null) {
                return STREAM
            }
            val parse = MediaType.parse(contentTypeFor)
            return parse ?: STREAM
        }
    }
}
