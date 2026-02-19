package com.hjq.http.body

import com.hjq.http.model.ContentType
import java.io.IOException
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink

open class StringBody(
    private val mText: String = ""
) : RequestBody() {
    private val mBytes: ByteArray = mText.toByteArray()

    override fun contentType(): MediaType {
        return ContentType.TEXT
    }

    override fun contentLength(): Long {
        return mBytes.size.toLong()
    }

    @Throws(IOException::class)
    override fun writeTo(bufferedSink: BufferedSink) {
        bufferedSink.write(mBytes, 0, mBytes.size)
    }

    override fun toString(): String {
        return mText
    }
}
