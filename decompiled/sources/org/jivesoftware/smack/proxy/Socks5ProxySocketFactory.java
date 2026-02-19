package org.jivesoftware.smack.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.SocketFactory;
import org.jivesoftware.smack.proxy.ProxyInfo;

public class Socks5ProxySocketFactory extends SocketFactory {
    private ProxyInfo proxy;

    public Socks5ProxySocketFactory(ProxyInfo proxyInfo) {
        this.proxy = proxyInfo;
    }

    public Socket createSocket(String str, int i) throws IOException, UnknownHostException {
        return socks5ProxifiedSocket(str, i);
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException, UnknownHostException {
        return socks5ProxifiedSocket(str, i);
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return socks5ProxifiedSocket(inetAddress.getHostAddress(), i);
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return socks5ProxifiedSocket(inetAddress.getHostAddress(), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x010e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010f, code lost:
        r6 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0139, code lost:
        throw new org.jivesoftware.smack.proxy.ProxyException(org.jivesoftware.smack.proxy.ProxyInfo.ProxyType.SOCKS5, r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x013f, code lost:
        throw new java.io.IOException(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0140, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0141, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x00e6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0104 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0095 A[Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0101 A[SYNTHETIC, Splitter:B:33:0x0101] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0114 A[SYNTHETIC, Splitter:B:42:0x0114] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0140 A[ExcHandler: RuntimeException (r0v1 'e' java.lang.RuntimeException A[CUSTOM_DECLARE]), Splitter:B:1:0x001d] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:35:0x0104=Splitter:B:35:0x0104, B:30:0x00e6=Splitter:B:30:0x00e6} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.Socket socks5ProxifiedSocket(java.lang.String r17, int r18) throws java.io.IOException {
        /*
            r16 = this;
            r1 = r16
            r0 = r18
            org.jivesoftware.smack.proxy.ProxyInfo r2 = r1.proxy
            java.lang.String r2 = r2.getProxyAddress()
            org.jivesoftware.smack.proxy.ProxyInfo r3 = r1.proxy
            int r3 = r3.getProxyPort()
            org.jivesoftware.smack.proxy.ProxyInfo r4 = r1.proxy
            java.lang.String r4 = r4.getProxyUsername()
            org.jivesoftware.smack.proxy.ProxyInfo r5 = r1.proxy
            java.lang.String r5 = r5.getProxyPassword()
            r6 = 0
            java.net.Socket r7 = new java.net.Socket     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x0111 }
            r7.<init>(r2, r3)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x0111 }
            java.io.InputStream r2 = r7.getInputStream()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.io.OutputStream r3 = r7.getOutputStream()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r6 = 1
            r7.setTcpNoDelay(r6)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8 = 1024(0x400, float:1.435E-42)
            byte[] r8 = new byte[r8]     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r9 = 5
            r10 = 0
            r8[r10] = r9     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r11 = 2
            r8[r6] = r11     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r11] = r10     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r12 = 3
            r8[r12] = r11     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r13 = 4
            r3.write(r8, r10, r13)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r1.fill(r2, r8, r11)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r14 = r8[r6]     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r14 = r14 & 255(0xff, float:3.57E-43)
            if (r14 == 0) goto L_0x0092
            if (r14 == r11) goto L_0x004e
            goto L_0x0090
        L_0x004e:
            if (r4 == 0) goto L_0x0090
            if (r5 != 0) goto L_0x0053
            goto L_0x0090
        L_0x0053:
            r8[r10] = r6     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r14 = r4.length()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r14 = (byte) r14     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r6] = r14     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte[] r14 = r4.getBytes()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r15 = r4.length()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.lang.System.arraycopy(r14, r10, r8, r11, r15)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r4 = r4.length()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r4 = r4 + r11
            int r14 = r4 + 1
            int r15 = r5.length()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r15 = (byte) r15     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r4] = r15     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte[] r4 = r5.getBytes()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r15 = r5.length()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.lang.System.arraycopy(r4, r10, r8, r14, r15)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r4 = r5.length()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r14 = r14 + r4
            r3.write(r8, r10, r14)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r1.fill(r2, r8, r11)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r4 = r8[r6]     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            if (r4 != 0) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            r4 = 0
            goto L_0x0093
        L_0x0092:
            r4 = 1
        L_0x0093:
            if (r4 == 0) goto L_0x0101
            r8[r10] = r9     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r6] = r6     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r11] = r10     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte[] r4 = r17.getBytes()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r5 = r4.length     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r12] = r12     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r14 = (byte) r5     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r13] = r14     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.lang.System.arraycopy(r4, r10, r8, r9, r5)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r9 = r9 + r5
            int r4 = r9 + 1
            int r5 = r0 >>> 8
            byte r5 = (byte) r5     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r9] = r5     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            int r5 = r4 + 1
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r8[r4] = r0     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r3.write(r8, r10, r5)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r1.fill(r2, r8, r13)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r0 = r8[r6]     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            if (r0 != 0) goto L_0x00e3
            byte r0 = r8[r12]     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 == r6) goto L_0x00de
            if (r0 == r12) goto L_0x00d2
            if (r0 == r13) goto L_0x00cc
            goto L_0x00e2
        L_0x00cc:
            r0 = 18
            r1.fill(r2, r8, r0)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            goto L_0x00e2
        L_0x00d2:
            r1.fill(r2, r8, r6)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r0 = r8[r10]     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 + r11
            r1.fill(r2, r8, r0)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            goto L_0x00e2
        L_0x00de:
            r0 = 6
            r1.fill(r2, r8, r0)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
        L_0x00e2:
            return r7
        L_0x00e3:
            r7.close()     // Catch:{ Exception -> 0x00e6, RuntimeException -> 0x0140 }
        L_0x00e6:
            org.jivesoftware.smack.proxy.ProxyException r0 = new org.jivesoftware.smack.proxy.ProxyException     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            org.jivesoftware.smack.proxy.ProxyInfo$ProxyType r2 = org.jivesoftware.smack.proxy.ProxyInfo.ProxyType.SOCKS5     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r3.<init>()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.lang.String r4 = "server returns "
            r3.append(r4)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            byte r4 = r8[r6]     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r3.append(r4)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.lang.String r3 = r3.toString()     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            r0.<init>(r2, r3)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            throw r0     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
        L_0x0101:
            r7.close()     // Catch:{ Exception -> 0x0104, RuntimeException -> 0x0140 }
        L_0x0104:
            org.jivesoftware.smack.proxy.ProxyException r0 = new org.jivesoftware.smack.proxy.ProxyException     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            org.jivesoftware.smack.proxy.ProxyInfo$ProxyType r2 = org.jivesoftware.smack.proxy.ProxyInfo.ProxyType.SOCKS5     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            java.lang.String r3 = "fail in SOCKS5 proxy"
            r0.<init>(r2, r3)     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
            throw r0     // Catch:{ RuntimeException -> 0x0140, Exception -> 0x010e }
        L_0x010e:
            r0 = move-exception
            r6 = r7
            goto L_0x0112
        L_0x0111:
            r0 = move-exception
        L_0x0112:
            if (r6 == 0) goto L_0x0119
            r6.close()     // Catch:{ Exception -> 0x0118 }
            goto L_0x0119
        L_0x0118:
        L_0x0119:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "ProxySOCKS5: "
            r2.append(r3)
            java.lang.String r3 = r0.toString()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            boolean r3 = r0 instanceof java.lang.Throwable
            if (r3 == 0) goto L_0x013a
            org.jivesoftware.smack.proxy.ProxyException r3 = new org.jivesoftware.smack.proxy.ProxyException
            org.jivesoftware.smack.proxy.ProxyInfo$ProxyType r4 = org.jivesoftware.smack.proxy.ProxyInfo.ProxyType.SOCKS5
            r3.<init>(r4, r2, r0)
            throw r3
        L_0x013a:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r2)
            throw r0
        L_0x0140:
            r0 = move-exception
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.proxy.Socks5ProxySocketFactory.socks5ProxifiedSocket(java.lang.String, int):java.net.Socket");
    }

    private void fill(InputStream inputStream, byte[] bArr, int i) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read > 0) {
                i2 += read;
            } else {
                throw new ProxyException(ProxyInfo.ProxyType.SOCKS5, "stream is closed");
            }
        }
    }
}
