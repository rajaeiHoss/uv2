package org.apache.http.entity

import java.io.ByteArrayInputStream
import org.apache.http.message.BasicHeader

open class ByteArrayEntity(data: ByteArray?) : BasicHttpEntity() {
    private val data: ByteArray = data ?: ByteArray(0)

    init {
        setContent(ByteArrayInputStream(this.data))
        setContentLength(this.data.size.toLong())
    }

    open fun setContentType(contentType: String?) {
        // No-op stub.
    }

    open fun setContentEncoding(encoding: String?) {
        if (encoding != null) {
            setContentEncoding(BasicHeader("Content-Encoding", encoding))
        }
    }
}
