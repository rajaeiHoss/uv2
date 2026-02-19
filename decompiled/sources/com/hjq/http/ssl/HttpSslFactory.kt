package com.hjq.http.ssl

import com.hjq.http.EasyLog
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.UnrecoverableKeyException
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.KeyManager
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class HttpSslFactory private constructor() {
    companion object {
        @JvmStatic
        fun generateSslConfig(): HttpSslConfig {
            return generateSslConfigBase(null, null, null)
        }

        @JvmStatic
        fun generateSslConfig(x509TrustManager: X509TrustManager?): HttpSslConfig {
            return generateSslConfigBase(x509TrustManager, null, null)
        }

        @JvmStatic
        fun generateSslConfig(vararg inputStreamArr: InputStream): HttpSslConfig {
            return generateSslConfigBase(null, null, null, *inputStreamArr)
        }

        @JvmStatic
        fun generateSslConfig(inputStream: InputStream?, str: String?, vararg inputStreamArr: InputStream): HttpSslConfig {
            return generateSslConfigBase(null, inputStream, str, *inputStreamArr)
        }

        @JvmStatic
        fun generateSslConfig(inputStream: InputStream?, str: String?, x509TrustManager: X509TrustManager?): HttpSslConfig {
            return generateSslConfigBase(x509TrustManager, inputStream, str)
        }

        private fun generateSslConfigBase(
            x509TrustManager: X509TrustManager?,
            inputStream: InputStream?,
            str: String?,
            vararg inputStreamArr: InputStream
        ): HttpSslConfig {
            try {
                val prepareKeyManager = prepareKeyManager(inputStream, str)
                val prepareTrustManager = prepareTrustManager(*inputStreamArr)
                var trustManager = x509TrustManager
                if (trustManager == null) {
                    trustManager = if (prepareTrustManager != null) {
                        chooseTrustManager(prepareTrustManager)
                    } else {
                        UnSafeTrustManager()
                    }
                }
                val instance = SSLContext.getInstance("TLS")
                instance.init(prepareKeyManager, arrayOf<TrustManager>(trustManager!!), null as SecureRandom?)
                return HttpSslConfig(instance.socketFactory, trustManager)
            } catch (e: KeyManagementException) {
                throw AssertionError(e)
            } catch (e: NoSuchAlgorithmException) {
                throw AssertionError(e)
            }
        }

        private fun prepareKeyManager(inputStream: InputStream?, str: String?): Array<KeyManager>? {
            if (inputStream == null || str == null) {
                return null
            }
            return try {
                val instance = KeyStore.getInstance("BKS")
                instance.load(inputStream, str.toCharArray())
                val instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
                instance2.init(instance, str.toCharArray())
                instance2.keyManagers
            } catch (e: IOException) {
                EasyLog.print(e)
                null
            } catch (e: KeyStoreException) {
                EasyLog.print(e)
                null
            } catch (e: NoSuchAlgorithmException) {
                EasyLog.print(e)
                null
            } catch (e: UnrecoverableKeyException) {
                EasyLog.print(e)
                null
            } catch (e: CertificateException) {
                EasyLog.print(e)
                null
            }
        }

        private fun prepareTrustManager(vararg inputStreamArr: InputStream): Array<TrustManager>? {
            if (inputStreamArr.isEmpty()) {
                return null
            }
            return try {
                val certificateFactory = CertificateFactory.getInstance("X.509")
                val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
                keyStore.load(null)
                var i = 0
                for (inputStream in inputStreamArr) {
                    try {
                        val certAlias = Integer.toString(i++)
                        val certificate = certificateFactory.generateCertificate(inputStream)
                        keyStore.setCertificateEntry(certAlias, certificate)
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close()
                            } catch (e: IOException) {
                                EasyLog.print(e)
                            }
                        }
                    }
                }
                val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)
                trustManagerFactory.trustManagers
            } catch (e: IOException) {
                EasyLog.print(e)
                null
            } catch (e: CertificateException) {
                EasyLog.print(e)
                null
            } catch (e: KeyStoreException) {
                EasyLog.print(e)
                null
            } catch (e: NoSuchAlgorithmException) {
                EasyLog.print(e)
                null
            }
        }

        private fun chooseTrustManager(trustManagerArr: Array<TrustManager>): X509TrustManager? {
            for (trustManager in trustManagerArr) {
                if (trustManager is X509TrustManager) {
                    return trustManager
                }
            }
            return null
        }

        @JvmStatic
        fun generateUnSafeHostnameVerifier(): HostnameVerifier {
            return UnSafeHostnameVerifier()
        }
    }
}
