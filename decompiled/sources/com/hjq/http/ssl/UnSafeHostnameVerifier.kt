package com.hjq.http.ssl

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

class UnSafeHostnameVerifier : HostnameVerifier {
    override fun verify(str: String?, sSLSession: SSLSession?): Boolean {
        return true
    }
}
