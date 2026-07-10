package okhttp3.internal.tls

import java.security.cert.X509Certificate

interface TrustRootIndex {
    fun findByIssuerAndSignature(cert: X509Certificate): X509Certificate?
}
