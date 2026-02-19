package com.hjq.http.ssl

import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class HttpSslConfig(
    private val mSSLSocketFactory: SSLSocketFactory,
    private val mTrustManager: X509TrustManager
) {
    fun getSslSocketFactory(): SSLSocketFactory {
        return mSSLSocketFactory
    }

    fun getTrustManager(): X509TrustManager {
        return mTrustManager
    }
}
