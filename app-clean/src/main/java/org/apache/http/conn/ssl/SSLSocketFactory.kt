package org.apache.http.conn.ssl

import org.apache.http.conn.scheme.SocketFactory

open class SSLSocketFactory private constructor() : SocketFactory {
    open fun setHostnameVerifier(verifier: Any?) {
        // No-op stub.
    }

    companion object {
        @JvmField
        val ALLOW_ALL_HOSTNAME_VERIFIER: Any = Any()

        private val INSTANCE: SSLSocketFactory = SSLSocketFactory()

        @JvmStatic
        fun getSocketFactory(): SSLSocketFactory = INSTANCE
    }
}
