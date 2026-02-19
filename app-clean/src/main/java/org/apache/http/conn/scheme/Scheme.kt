package org.apache.http.conn.scheme

open class Scheme(
    private val name: String?,
    private val socketFactory: SocketFactory?,
    private val port: Int
) {
    open fun getName(): String? = name

    open fun getPort(): Int = port

    open fun getSocketFactory(): SocketFactory? = socketFactory
}
