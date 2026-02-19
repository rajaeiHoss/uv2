package com.hjq.http.ssl;

import com.hjq.http.EasyLog;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public final class HttpSslFactory {
    public static HttpSslConfig generateSslConfig() {
        return generateSslConfigBase((X509TrustManager) null, (InputStream) null, (String) null, new InputStream[0]);
    }

    public static HttpSslConfig generateSslConfig(X509TrustManager x509TrustManager) {
        return generateSslConfigBase(x509TrustManager, (InputStream) null, (String) null, new InputStream[0]);
    }

    public static HttpSslConfig generateSslConfig(InputStream... inputStreamArr) {
        return generateSslConfigBase((X509TrustManager) null, (InputStream) null, (String) null, inputStreamArr);
    }

    public static HttpSslConfig generateSslConfig(InputStream inputStream, String str, InputStream... inputStreamArr) {
        return generateSslConfigBase((X509TrustManager) null, inputStream, str, inputStreamArr);
    }

    public static HttpSslConfig generateSslConfig(InputStream inputStream, String str, X509TrustManager x509TrustManager) {
        return generateSslConfigBase(x509TrustManager, inputStream, str, new InputStream[0]);
    }

    private static HttpSslConfig generateSslConfigBase(X509TrustManager x509TrustManager, InputStream inputStream, String str, InputStream... inputStreamArr) {
        try {
            KeyManager[] prepareKeyManager = prepareKeyManager(inputStream, str);
            TrustManager[] prepareTrustManager = prepareTrustManager(inputStreamArr);
            if (x509TrustManager == null) {
                if (prepareTrustManager != null) {
                    x509TrustManager = chooseTrustManager(prepareTrustManager);
                } else {
                    x509TrustManager = new UnSafeTrustManager();
                }
            }
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(prepareKeyManager, new TrustManager[]{x509TrustManager}, (SecureRandom) null);
            return new HttpSslConfig(instance.getSocketFactory(), x509TrustManager);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    private static KeyManager[] prepareKeyManager(InputStream inputStream, String str) {
        if (!(inputStream == null || str == null)) {
            try {
                KeyStore instance = KeyStore.getInstance("BKS");
                instance.load(inputStream, str.toCharArray());
                KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                instance2.init(instance, str.toCharArray());
                return instance2.getKeyManagers();
            } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e) {
                EasyLog.print(e);
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        com.hjq.http.EasyLog.print((java.lang.Throwable) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        r9 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004a A[ExcHandler: KeyStoreException | NoSuchAlgorithmException | CertificateException (e java.lang.Throwable), Splitter:B:5:0x0009] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static javax.net.ssl.TrustManager[] prepareTrustManager(java.io.InputStream... r9) {
        /*
            r0 = 0
            if (r9 == 0) goto L_0x0054
            int r1 = r9.length
            if (r1 > 0) goto L_0x0007
            goto L_0x0054
        L_0x0007:
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            java.lang.String r2 = java.security.KeyStore.getDefaultType()     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            java.security.KeyStore r2 = java.security.KeyStore.getInstance(r2)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            r2.load(r0)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            int r3 = r9.length     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            r4 = 0
            r5 = 0
        L_0x001b:
            if (r4 >= r3) goto L_0x003a
            r6 = r9[r4]     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            int r7 = r5 + 1
            java.lang.String r5 = java.lang.Integer.toString(r5)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            java.security.cert.Certificate r8 = r1.generateCertificate(r6)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            r2.setCertificateEntry(r5, r8)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            if (r6 == 0) goto L_0x0036
            r6.close()     // Catch:{ IOException -> 0x0032, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            goto L_0x0036
        L_0x0032:
            r5 = move-exception
            com.hjq.http.EasyLog.print((java.lang.Throwable) r5)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
        L_0x0036:
            int r4 = r4 + 1
            r5 = r7
            goto L_0x001b
        L_0x003a:
            java.lang.String r9 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            javax.net.ssl.TrustManagerFactory r9 = javax.net.ssl.TrustManagerFactory.getInstance(r9)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            r9.init(r2)     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            javax.net.ssl.TrustManager[] r9 = r9.getTrustManagers()     // Catch:{ IOException -> 0x0050, CertificateException -> 0x004e, KeyStoreException -> 0x004c, NoSuchAlgorithmException -> 0x004a }
            return r9
        L_0x004a:
            r9 = move-exception
            goto L_0x0051
        L_0x004c:
            r9 = move-exception
            goto L_0x0051
        L_0x004e:
            r9 = move-exception
            goto L_0x0051
        L_0x0050:
            r9 = move-exception
        L_0x0051:
            com.hjq.http.EasyLog.print((java.lang.Throwable) r9)
        L_0x0054:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hjq.http.ssl.HttpSslFactory.prepareTrustManager(java.io.InputStream[]):javax.net.ssl.TrustManager[]");
    }

    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }

    public static HostnameVerifier generateUnSafeHostnameVerifier() {
        return new UnSafeHostnameVerifier();
    }
}
