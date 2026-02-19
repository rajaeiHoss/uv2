package com.kenai.jbosh;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;

final class ApacheHTTPSender implements HTTPSender {
    private BOSHClientConfig cfg;
    private HttpClient httpClient;
    private final Lock lock = new ReentrantLock();

    ApacheHTTPSender() {
        Class<HttpClient> cls = HttpClient.class;
    }

    public void init(BOSHClientConfig bOSHClientConfig) {
        this.lock.lock();
        try {
            this.cfg = bOSHClientConfig;
            this.httpClient = initHttpClient(bOSHClientConfig);
        } finally {
            this.lock.unlock();
        }
    }

    public void destroy() {
        this.lock.lock();
        try {
            HttpClient httpClient2 = this.httpClient;
            if (httpClient2 != null) {
                httpClient2.getConnectionManager().shutdown();
            }
        } finally {
            this.cfg = null;
            this.httpClient = null;
            this.lock.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public HTTPResponse send(CMSessionParams cMSessionParams, AbstractBody abstractBody) {
        this.lock.lock();
        try {
            if (this.httpClient == null) {
                this.httpClient = initHttpClient(this.cfg);
            }
            HttpClient httpClient2 = this.httpClient;
            BOSHClientConfig bOSHClientConfig = this.cfg;
            this.lock.unlock();
            return new ApacheHTTPResponse(httpClient2, bOSHClientConfig, cMSessionParams, abstractBody);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    private synchronized HttpClient initHttpClient(BOSHClientConfig bOSHClientConfig) {
        BasicHttpParams basicHttpParams;
        SchemeRegistry schemeRegistry;
        basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        if (!(bOSHClientConfig == null || bOSHClientConfig.getProxyHost() == null || bOSHClientConfig.getProxyPort() == 0)) {
            basicHttpParams.setParameter("http.route.default-proxy", new HttpHost(bOSHClientConfig.getProxyHost(), bOSHClientConfig.getProxyPort()));
        }
        schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
        socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        schemeRegistry.register(new Scheme("https", socketFactory, 443));
        return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }
}
