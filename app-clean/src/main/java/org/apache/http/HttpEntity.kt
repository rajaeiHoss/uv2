package org.apache.http

import java.io.InputStream

interface HttpEntity {
    fun getContent(): InputStream?

    fun getContentLength(): Long

    fun getContentEncoding(): Header?
}
