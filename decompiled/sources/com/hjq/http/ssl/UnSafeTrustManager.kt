package com.hjq.http.ssl

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

class UnSafeTrustManager : X509TrustManager {
    override fun checkClientTrusted(x509CertificateArr: Array<out X509Certificate>?, str: String?) {
    }

    override fun checkServerTrusted(x509CertificateArr: Array<out X509Certificate>?, str: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return emptyArray()
    }
}
