package org.apache.http

open class HttpHost(private val hostName: String?, private val port: Int) {
    open fun getHostName(): String? = hostName

    open fun getPort(): Int = port
}
