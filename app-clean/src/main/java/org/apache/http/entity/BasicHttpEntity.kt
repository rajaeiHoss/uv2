package org.apache.http.entity

import java.io.InputStream
import org.apache.http.Header
import org.apache.http.HttpEntity

open class BasicHttpEntity : HttpEntity {
    private var content: InputStream? = null
    private var contentLength: Long = -1
    private var contentEncoding: Header? = null

    open fun setContent(content: InputStream?) {
        this.content = content
    }

    open fun setContentLength(contentLength: Long) {
        this.contentLength = contentLength
    }

    open fun setContentEncoding(contentEncoding: Header?) {
        this.contentEncoding = contentEncoding
    }

    override fun getContent(): InputStream? = content

    override fun getContentLength(): Long = contentLength

    override fun getContentEncoding(): Header? = contentEncoding
}
