package org.apache.http.conn.scheme

open class PlainSocketFactory private constructor() : SocketFactory {
    companion object {
        private val INSTANCE: PlainSocketFactory = PlainSocketFactory()

        @JvmStatic
        fun getSocketFactory(): PlainSocketFactory = INSTANCE
    }
}
