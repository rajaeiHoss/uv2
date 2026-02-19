package org.apache.http

class HttpVersion private constructor(private val text: String) {
    override fun toString(): String = text

    companion object {
        @JvmField
        val HTTP_1_1: HttpVersion = HttpVersion("HTTP/1.1")
    }
}
