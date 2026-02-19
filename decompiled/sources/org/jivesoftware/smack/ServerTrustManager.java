package org.jivesoftware.smack;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.X509TrustManager;

class ServerTrustManager implements X509TrustManager {
    private static Pattern cnPattern = Pattern.compile("(?i)(cn=)([^,]*)");
    private ConnectionConfiguration configuration;
    private String server;
    private KeyStore trustStore;

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0044 A[SYNTHETIC, Splitter:B:18:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ServerTrustManager(java.lang.String r4, org.jivesoftware.smack.ConnectionConfiguration r5) {
        /*
            r3 = this;
            r3.<init>()
            r3.configuration = r5
            r3.server = r4
            r4 = 0
            java.lang.String r0 = r5.getTruststoreType()     // Catch:{ Exception -> 0x0032, all -> 0x002e }
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)     // Catch:{ Exception -> 0x0032, all -> 0x002e }
            r3.trustStore = r0     // Catch:{ Exception -> 0x0032, all -> 0x002e }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0032, all -> 0x002e }
            java.lang.String r1 = r5.getTruststorePath()     // Catch:{ Exception -> 0x0032, all -> 0x002e }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0032, all -> 0x002e }
            java.security.KeyStore r4 = r3.trustStore     // Catch:{ Exception -> 0x002c }
            java.lang.String r1 = r5.getTruststorePassword()     // Catch:{ Exception -> 0x002c }
            char[] r1 = r1.toCharArray()     // Catch:{ Exception -> 0x002c }
            r4.load(r0, r1)     // Catch:{ Exception -> 0x002c }
        L_0x0028:
            r0.close()     // Catch:{ IOException -> 0x0040 }
            goto L_0x0040
        L_0x002c:
            r4 = move-exception
            goto L_0x0036
        L_0x002e:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x0042
        L_0x0032:
            r0 = move-exception
            r2 = r0
            r0 = r4
            r4 = r2
        L_0x0036:
            r4.printStackTrace()     // Catch:{ all -> 0x0041 }
            r4 = 0
            r5.setVerifyRootCAEnabled(r4)     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x0040
            goto L_0x0028
        L_0x0040:
            return
        L_0x0041:
            r4 = move-exception
        L_0x0042:
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.ServerTrustManager.<init>(java.lang.String, org.jivesoftware.smack.ConnectionConfiguration):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkServerTrusted(java.security.cert.X509Certificate[] r8, java.lang.String r9) throws java.security.cert.CertificateException {
        /*
            r7 = this;
            int r9 = r8.length
            r0 = 0
            r1 = r8[r0]
            java.util.List r1 = getPeerIdentity(r1)
            org.jivesoftware.smack.ConnectionConfiguration r2 = r7.configuration
            boolean r2 = r2.isVerifyChainEnabled()
            if (r2 == 0) goto L_0x0067
            r2 = 0
            int r3 = r9 + -1
        L_0x0013:
            if (r3 < 0) goto L_0x0067
            r4 = r8[r3]
            java.security.Principal r5 = r4.getIssuerDN()
            java.security.Principal r4 = r4.getSubjectDN()
            if (r2 == 0) goto L_0x0063
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x004c
            int r2 = r3 + 1
            r2 = r8[r2]     // Catch:{ GeneralSecurityException -> 0x0035 }
            java.security.PublicKey r2 = r2.getPublicKey()     // Catch:{ GeneralSecurityException -> 0x0035 }
            r5 = r8[r3]     // Catch:{ GeneralSecurityException -> 0x0035 }
            r5.verify(r2)     // Catch:{ GeneralSecurityException -> 0x0035 }
            goto L_0x0063
        L_0x0035:
            java.security.cert.CertificateException r8 = new java.security.cert.CertificateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "signature verification failed of "
            r9.append(r0)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x004c:
            java.security.cert.CertificateException r8 = new java.security.cert.CertificateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "subject/issuer verification failed of "
            r9.append(r0)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0063:
            int r3 = r3 + -1
            r2 = r4
            goto L_0x0013
        L_0x0067:
            org.jivesoftware.smack.ConnectionConfiguration r2 = r7.configuration
            boolean r2 = r2.isVerifyRootCAEnabled()
            r3 = 1
            if (r2 == 0) goto L_0x00c4
            java.security.KeyStore r2 = r7.trustStore     // Catch:{ KeyStoreException -> 0x00a5 }
            int r4 = r9 + -1
            r4 = r8[r4]     // Catch:{ KeyStoreException -> 0x00a5 }
            java.lang.String r2 = r2.getCertificateAlias(r4)     // Catch:{ KeyStoreException -> 0x00a5 }
            if (r2 == 0) goto L_0x007e
            r2 = 1
            goto L_0x007f
        L_0x007e:
            r2 = 0
        L_0x007f:
            if (r2 != 0) goto L_0x00aa
            if (r9 != r3) goto L_0x00aa
            org.jivesoftware.smack.ConnectionConfiguration r4 = r7.configuration     // Catch:{ KeyStoreException -> 0x00a3 }
            boolean r4 = r4.isSelfSignedCertificateEnabled()     // Catch:{ KeyStoreException -> 0x00a3 }
            if (r4 == 0) goto L_0x00aa
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ KeyStoreException -> 0x00a3 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ KeyStoreException -> 0x00a3 }
            r5.<init>()     // Catch:{ KeyStoreException -> 0x00a3 }
            java.lang.String r6 = "Accepting self-signed certificate of remote server: "
            r5.append(r6)     // Catch:{ KeyStoreException -> 0x00a3 }
            r5.append(r1)     // Catch:{ KeyStoreException -> 0x00a3 }
            java.lang.String r5 = r5.toString()     // Catch:{ KeyStoreException -> 0x00a3 }
            r4.println(r5)     // Catch:{ KeyStoreException -> 0x00a3 }
            r2 = 1
            goto L_0x00aa
        L_0x00a3:
            r4 = move-exception
            goto L_0x00a7
        L_0x00a5:
            r4 = move-exception
            r2 = 0
        L_0x00a7:
            r4.printStackTrace()
        L_0x00aa:
            if (r2 == 0) goto L_0x00ad
            goto L_0x00c4
        L_0x00ad:
            java.security.cert.CertificateException r8 = new java.security.cert.CertificateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "root certificate not trusted of "
            r9.append(r0)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x00c4:
            org.jivesoftware.smack.ConnectionConfiguration r2 = r7.configuration
            boolean r2 = r2.isNotMatchingDomainCheckEnabled()
            if (r2 == 0) goto L_0x012a
            int r2 = r1.size()
            java.lang.String r4 = "target verification failed of "
            if (r2 != r3) goto L_0x010c
            java.lang.Object r2 = r1.get(r0)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r3 = "*."
            boolean r2 = r2.startsWith(r3)
            if (r2 == 0) goto L_0x010c
            java.lang.Object r2 = r1.get(r0)
            java.lang.String r2 = (java.lang.String) r2
            java.lang.String r5 = ""
            java.lang.String r2 = r2.replace(r3, r5)
            java.lang.String r3 = r7.server
            boolean r2 = r3.endsWith(r2)
            if (r2 == 0) goto L_0x00f7
            goto L_0x012a
        L_0x00f7:
            java.security.cert.CertificateException r8 = new java.security.cert.CertificateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x010c:
            java.lang.String r2 = r7.server
            boolean r2 = r1.contains(r2)
            if (r2 == 0) goto L_0x0115
            goto L_0x012a
        L_0x0115:
            java.security.cert.CertificateException r8 = new java.security.cert.CertificateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r4)
            r9.append(r1)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x012a:
            org.jivesoftware.smack.ConnectionConfiguration r1 = r7.configuration
            boolean r1 = r1.isExpiredCertificatesCheckEnabled()
            if (r1 == 0) goto L_0x015a
            java.util.Date r1 = new java.util.Date
            r1.<init>()
        L_0x0137:
            if (r0 >= r9) goto L_0x015a
            r2 = r8[r0]     // Catch:{ GeneralSecurityException -> 0x0141 }
            r2.checkValidity(r1)     // Catch:{ GeneralSecurityException -> 0x0141 }
            int r0 = r0 + 1
            goto L_0x0137
        L_0x0141:
            java.security.cert.CertificateException r8 = new java.security.cert.CertificateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "invalid date of "
            r9.append(r0)
            java.lang.String r0 = r7.server
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x015a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.ServerTrustManager.checkServerTrusted(java.security.cert.X509Certificate[], java.lang.String):void");
    }

    public static List<String> getPeerIdentity(X509Certificate x509Certificate) {
        List<String> subjectAlternativeNames = getSubjectAlternativeNames(x509Certificate);
        if (!subjectAlternativeNames.isEmpty()) {
            return subjectAlternativeNames;
        }
        String name = x509Certificate.getSubjectDN().getName();
        Matcher matcher = cnPattern.matcher(name);
        if (matcher.find()) {
            name = matcher.group(2);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(name);
        return arrayList;
    }

    private static List<String> getSubjectAlternativeNames(X509Certificate x509Certificate) {
        ArrayList arrayList = new ArrayList();
        try {
            if (x509Certificate.getSubjectAlternativeNames() == null) {
                return Collections.emptyList();
            }
        } catch (CertificateParsingException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
