package org.conscrypt

import java.security.Provider
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

class Conscrypt private constructor() {
    companion object {
        @JvmStatic
        fun isAvailable(): Boolean = false

        @JvmStatic
        fun isConscrypt(factory: SSLSocketFactory?): Boolean = false

        @JvmStatic
        fun isConscrypt(socket: SSLSocket?): Boolean = false

        @JvmStatic
        fun setUseSessionTickets(socket: SSLSocket?, enabled: Boolean) {
        }

        @JvmStatic
        fun setHostname(socket: SSLSocket?, hostname: String?) {
        }

        @JvmStatic
        fun setApplicationProtocols(socket: SSLSocket?, protocols: Array<String?>?) {
        }

        @JvmStatic
        fun getApplicationProtocol(socket: SSLSocket?): String? = null

        @JvmStatic
        fun setUseEngineSocket(factory: SSLSocketFactory?, enabled: Boolean) {
        }

        @JvmStatic
        fun newProviderBuilder(): ProviderBuilder = ProviderBuilder()
    }

    class ProviderBuilder {
        fun provideTrustManager(): ProviderBuilder = this

        fun build(): Provider = object : Provider("Conscrypt", 1.0, "Stub") {}
    }
}
