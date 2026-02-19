package com.hjq.http.ssl;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

public final class HttpSslConfig {
    private final SSLSocketFactory mSSLSocketFactory;
    private final X509TrustManager mTrustManager;

    HttpSslConfig(SSLSocketFactory sSLSocketFactory, X509TrustManager x509TrustManager) {
        this.mSSLSocketFactory = sSLSocketFactory;
        this.mTrustManager = x509TrustManager;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return this.mSSLSocketFactory;
    }

    public X509TrustManager getTrustManager() {
        return this.mTrustManager;
    }
}
