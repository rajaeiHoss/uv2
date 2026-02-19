package com.google.android.gms.internal;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import java.io.File;
import java.io.IOException;
import java.lang.Thread;
import java.net.Socket;
import java.net.URI;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;

public final class zzeoe {
    private static final AtomicInteger zznpu = new AtomicInteger(0);
    private static final Charset zznpv = Charset.forName("UTF-8");
    private static ThreadFactory zznqd = Executors.defaultThreadFactory();
    private static zzeod zznqe = new zzeof();
    private volatile Socket socket = null;
    private final int zzfug;
    private final zzemm zzmxz;
    private final String zznaa;
    private volatile int zznpw = zzeoi.zznqh;
    private zzeoj zznpx = null;
    private final URI zznpy;
    private final zzeon zznpz;
    private final zzeoo zznqa;
    private final zzeol zznqb;
    private final Thread zznqc;

    public zzeoe(zzees zzees, URI uri, String str, Map<String, String> map) {
        int incrementAndGet = zznpu.incrementAndGet();
        this.zzfug = incrementAndGet;
        this.zznqc = zznqd.newThread(new zzeog(this));
        this.zznpy = uri;
        this.zznaa = zzees.zzbwp();
        zzemn zzbwk = zzees.zzbwk();
        StringBuilder sb = new StringBuilder(14);
        sb.append("sk_");
        sb.append(incrementAndGet);
        this.zzmxz = new zzemm(zzbwk, "WebSocket", sb.toString());
        this.zznqb = new zzeol(uri, (String) null, map);
        this.zznpz = new zzeon(this);
        this.zznqa = new zzeoo(this, "TubeSock", incrementAndGet);
    }

    private final Socket createSocket() {
        String scheme = this.zznpy.getScheme();
        String host = this.zznpy.getHost();
        int port = this.zznpy.getPort();
        if (scheme != null && scheme.equals("ws")) {
            if (port == -1) {
                port = 80;
            }
            try {
                return new Socket(host, port);
            } catch (UnknownHostException e) {
                String valueOf = String.valueOf(host);
                throw new zzeok(valueOf.length() != 0 ? "unknown host: ".concat(valueOf) : new String("unknown host: "), e);
            } catch (IOException e2) {
                String valueOf2 = String.valueOf(this.zznpy);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 31);
                sb.append("error while creating socket to ");
                sb.append(valueOf2);
                throw new zzeok(sb.toString(), e2);
            }
        } else if (scheme == null || !scheme.equals("wss")) {
            String valueOf3 = String.valueOf(scheme);
            throw new zzeok(valueOf3.length() != 0 ? "unsupported protocol: ".concat(valueOf3) : new String("unsupported protocol: "));
        } else {
            if (port == -1) {
                port = 443;
            }
            SSLSessionCache sSLSessionCache = null;
            try {
                if (this.zznaa != null) {
                    sSLSessionCache = new SSLSessionCache(new File(this.zznaa));
                }
            } catch (IOException e3) {
                this.zzmxz.zzb("Failed to initialize SSL session cache", e3, new Object[0]);
            }
            try {
                SSLSocket sSLSocket = (SSLSocket) SSLCertificateSocketFactory.getDefault(60000, sSLSessionCache).createSocket(host, port);
                if (HttpsURLConnection.getDefaultHostnameVerifier().verify(host, sSLSocket.getSession())) {
                    return sSLSocket;
                }
                String valueOf4 = String.valueOf(this.zznpy);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf4).length() + 39);
                sb2.append("Error while verifying secure socket to ");
                sb2.append(valueOf4);
                throw new zzeok(sb2.toString());
            } catch (UnknownHostException e4) {
                String valueOf5 = String.valueOf(host);
                throw new zzeok(valueOf5.length() != 0 ? "unknown host: ".concat(valueOf5) : new String("unknown host: "), e4);
            } catch (IOException e5) {
                String valueOf6 = String.valueOf(this.zznpy);
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf6).length() + 38);
                sb3.append("error while creating secure socket to ");
                sb3.append(valueOf6);
                throw new zzeok(sb3.toString(), e5);
            }
        }
    }

    static ThreadFactory getThreadFactory() {
        return zznqd;
    }

    private final synchronized void zza(byte b, byte[] bArr) {
        if (this.zznpw != zzeoi.zznqj) {
            this.zznpx.zza(new zzeok("error while sending data: not connected"));
            return;
        }
        try {
            this.zznqa.zza(b, true, bArr);
        } catch (IOException e) {
            this.zznpx.zza(new zzeok("Failed to send frame", e));
            close();
        }
    }

    static zzeod zzcdb() {
        return zznqe;
    }

    private final synchronized void zzcde() {
        if (this.zznpw != zzeoi.zznql) {
            this.zznpz.zzcdj();
            this.zznqa.zzcdl();
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            this.zznpw = zzeoi.zznql;
            this.zznpx.onClose();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r1 = new java.io.DataInputStream(r0.getInputStream());
        r0 = r0.getOutputStream();
        r0.write(r11.zznqb.zzcdh());
        r3 = new byte[1000];
        r4 = new java.util.ArrayList();
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        if (r6 != false) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0047, code lost:
        r9 = r1.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        if (r9 == -1) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        r3[r7] = (byte) r9;
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0059, code lost:
        if (r3[r7 - 1] != 10) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        if (r3[r7 - 2] != 13) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0063, code lost:
        r7 = new java.lang.String(r3, zznpv);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0074, code lost:
        if (r7.trim().equals("") == false) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0076, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0078, code lost:
        r4.add(r7.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007f, code lost:
        r3 = new byte[1000];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0082, code lost:
        if (r7 != 1000) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0084, code lost:
        r0 = new java.lang.String(r3, zznpv);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0093, code lost:
        if (r0.length() == 0) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0095, code lost:
        r0 = "Unexpected long line in handshake: ".concat(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009a, code lost:
        r0 = new java.lang.String("Unexpected long line in handshake: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a2, code lost:
        throw new com.google.android.gms.internal.zzeok(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00aa, code lost:
        throw new com.google.android.gms.internal.zzeok("Connection closed before handshake was complete");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ab, code lost:
        r2 = java.lang.Integer.valueOf(((java.lang.String) r4.get(0)).substring(9, 12)).intValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c3, code lost:
        if (r2 == 407) goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c7, code lost:
        if (r2 == 404) goto L_0x016f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00cb, code lost:
        if (r2 != 101) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00cd, code lost:
        r4.remove(0);
        r2 = new java.util.HashMap();
        r4 = r4;
        r3 = r4.size();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00dc, code lost:
        if (r6 >= r3) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00de, code lost:
        r7 = r4.get(r6);
        r6 = r6 + 1;
        r7 = ((java.lang.String) r7).split(": ", 2);
        r2.put(r7[0], r7[1]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0109, code lost:
        if (((java.lang.String) r2.get("Upgrade")).toLowerCase(java.util.Locale.US).equals("websocket") == false) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011f, code lost:
        if (((java.lang.String) r2.get("Connection")).toLowerCase(java.util.Locale.US).equals("upgrade") == false) goto L_0x0146;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0121, code lost:
        r11.zznqa.zzb(r0);
        r11.zznpz.zza(r1);
        r11.zznpw = com.google.android.gms.internal.zzeoi.zznqj;
        r11.zznqa.zzcdn().start();
        r11.zznpx.zzbxp();
        r11.zznpz.run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0142, code lost:
        close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0145, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x014d, code lost:
        throw new com.google.android.gms.internal.zzeok("connection failed: missing header field in server handshake: Connection");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0155, code lost:
        throw new com.google.android.gms.internal.zzeok("connection failed: missing header field in server handshake: Upgrade");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        r3 = new java.lang.StringBuilder(50);
        r3.append("connection failed: unknown status code ");
        r3.append(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016e, code lost:
        throw new com.google.android.gms.internal.zzeok(r3.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0176, code lost:
        throw new com.google.android.gms.internal.zzeok("connection failed: 404 not found");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x017e, code lost:
        throw new com.google.android.gms.internal.zzeok("connection failed: proxy authentication not supported");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzcdg() {
        /*
            r11 = this;
            java.net.Socket r0 = r11.createSocket()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            monitor-enter(r11)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r11.socket = r0     // Catch:{ all -> 0x017f }
            int r1 = r11.zznpw     // Catch:{ all -> 0x017f }
            int r2 = com.google.android.gms.internal.zzeoi.zznql     // Catch:{ all -> 0x017f }
            if (r1 != r2) goto L_0x0021
            java.net.Socket r0 = r11.socket     // Catch:{ IOException -> 0x001a }
            r0.close()     // Catch:{ IOException -> 0x001a }
            r0 = 0
            r11.socket = r0     // Catch:{ all -> 0x017f }
            monitor-exit(r11)     // Catch:{ all -> 0x017f }
            r11.close()
            return
        L_0x001a:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x017f }
            r1.<init>(r0)     // Catch:{ all -> 0x017f }
            throw r1     // Catch:{ all -> 0x017f }
        L_0x0021:
            monitor-exit(r11)     // Catch:{ all -> 0x017f }
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.io.InputStream r2 = r0.getInputStream()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r1.<init>(r2)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.io.OutputStream r0 = r0.getOutputStream()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            com.google.android.gms.internal.zzeol r2 = r11.zznqb     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            byte[] r2 = r2.zzcdh()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.write(r2)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r2 = 1000(0x3e8, float:1.401E-42)
            byte[] r3 = new byte[r2]     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r4.<init>()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r5 = 0
            r6 = 0
        L_0x0043:
            r7 = 0
        L_0x0044:
            r8 = 1
            if (r6 != 0) goto L_0x00ab
            int r9 = r1.read()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r10 = -1
            if (r9 == r10) goto L_0x00a3
            byte r9 = (byte) r9     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r3[r7] = r9     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            int r7 = r7 + 1
            int r9 = r7 + -1
            byte r9 = r3[r9]     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r10 = 10
            if (r9 != r10) goto L_0x0082
            int r9 = r7 + -2
            byte r9 = r3[r9]     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r10 = 13
            if (r9 != r10) goto L_0x0082
            java.lang.String r7 = new java.lang.String     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.nio.charset.Charset r9 = zznpv     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r7.<init>(r3, r9)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r3 = r7.trim()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r9 = ""
            boolean r3 = r3.equals(r9)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            if (r3 == 0) goto L_0x0078
            r6 = 1
            goto L_0x007f
        L_0x0078:
            java.lang.String r3 = r7.trim()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r4.add(r3)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x007f:
            byte[] r3 = new byte[r2]     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            goto L_0x0043
        L_0x0082:
            if (r7 != r2) goto L_0x0044
            java.lang.String r0 = new java.lang.String     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.nio.charset.Charset r1 = zznpv     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.<init>(r3, r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            com.google.android.gms.internal.zzeok r1 = new com.google.android.gms.internal.zzeok     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r2 = "Unexpected long line in handshake: "
            int r3 = r0.length()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            if (r3 == 0) goto L_0x009a
            java.lang.String r0 = r2.concat(r0)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            goto L_0x009f
        L_0x009a:
            java.lang.String r0 = new java.lang.String     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.<init>(r2)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x009f:
            r1.<init>(r0)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            throw r1     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x00a3:
            com.google.android.gms.internal.zzeok r0 = new com.google.android.gms.internal.zzeok     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r1 = "Connection closed before handshake was complete"
            r0.<init>(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            throw r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x00ab:
            java.lang.Object r2 = r4.get(r5)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r3 = 9
            r6 = 12
            java.lang.String r2 = r2.substring(r3, r6)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            int r2 = r2.intValue()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r3 = 407(0x197, float:5.7E-43)
            if (r2 == r3) goto L_0x0177
            r3 = 404(0x194, float:5.66E-43)
            if (r2 == r3) goto L_0x016f
            r3 = 101(0x65, float:1.42E-43)
            if (r2 != r3) goto L_0x0156
            r4.remove(r5)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r2.<init>()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            int r3 = r4.size()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r6 = 0
        L_0x00dc:
            if (r6 >= r3) goto L_0x00f5
            java.lang.Object r7 = r4.get(r6)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            int r6 = r6 + 1
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r9 = ": "
            r10 = 2
            java.lang.String[] r7 = r7.split(r9, r10)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r9 = r7[r5]     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r7 = r7[r8]     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r2.put(r9, r7)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            goto L_0x00dc
        L_0x00f5:
            java.lang.String r3 = "Upgrade"
            java.lang.Object r3 = r2.get(r3)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.util.Locale r4 = java.util.Locale.US     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r3 = r3.toLowerCase(r4)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r4 = "websocket"
            boolean r3 = r3.equals(r4)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            if (r3 == 0) goto L_0x014e
            java.lang.String r3 = "Connection"
            java.lang.Object r2 = r2.get(r3)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.util.Locale r3 = java.util.Locale.US     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r2 = r2.toLowerCase(r3)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r3 = "upgrade"
            boolean r2 = r2.equals(r3)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            if (r2 == 0) goto L_0x0146
            com.google.android.gms.internal.zzeoo r2 = r11.zznqa     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r2.zzb(r0)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            com.google.android.gms.internal.zzeon r0 = r11.zznpz     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.zza(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            int r0 = com.google.android.gms.internal.zzeoi.zznqj     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r11.zznpw = r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            com.google.android.gms.internal.zzeoo r0 = r11.zznqa     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.Thread r0 = r0.zzcdn()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.start()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            com.google.android.gms.internal.zzeoj r0 = r11.zznpx     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.zzbxp()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            com.google.android.gms.internal.zzeon r0 = r11.zznpz     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.run()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r11.close()
            return
        L_0x0146:
            com.google.android.gms.internal.zzeok r0 = new com.google.android.gms.internal.zzeok     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r1 = "connection failed: missing header field in server handshake: Connection"
            r0.<init>(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            throw r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x014e:
            com.google.android.gms.internal.zzeok r0 = new com.google.android.gms.internal.zzeok     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r1 = "connection failed: missing header field in server handshake: Upgrade"
            r0.<init>(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            throw r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x0156:
            com.google.android.gms.internal.zzeok r0 = new com.google.android.gms.internal.zzeok     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r1 = 50
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r3.<init>(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r1 = "connection failed: unknown status code "
            r3.append(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r3.append(r2)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r1 = r3.toString()     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            r0.<init>(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            throw r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x016f:
            com.google.android.gms.internal.zzeok r0 = new com.google.android.gms.internal.zzeok     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r1 = "connection failed: 404 not found"
            r0.<init>(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            throw r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x0177:
            com.google.android.gms.internal.zzeok r0 = new com.google.android.gms.internal.zzeok     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            java.lang.String r1 = "connection failed: proxy authentication not supported"
            r0.<init>(r1)     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
            throw r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x017f:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x017f }
            throw r0     // Catch:{ zzeok -> 0x01ae, IOException -> 0x0184 }
        L_0x0182:
            r0 = move-exception
            goto L_0x01b8
        L_0x0184:
            r0 = move-exception
            com.google.android.gms.internal.zzeoj r1 = r11.zznpx     // Catch:{ all -> 0x0182 }
            com.google.android.gms.internal.zzeok r2 = new com.google.android.gms.internal.zzeok     // Catch:{ all -> 0x0182 }
            java.lang.String r3 = "error while connecting: "
            java.lang.String r4 = r0.getMessage()     // Catch:{ all -> 0x0182 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0182 }
            int r5 = r4.length()     // Catch:{ all -> 0x0182 }
            if (r5 == 0) goto L_0x019e
            java.lang.String r3 = r3.concat(r4)     // Catch:{ all -> 0x0182 }
            goto L_0x01a4
        L_0x019e:
            java.lang.String r4 = new java.lang.String     // Catch:{ all -> 0x0182 }
            r4.<init>(r3)     // Catch:{ all -> 0x0182 }
            r3 = r4
        L_0x01a4:
            r2.<init>(r3, r0)     // Catch:{ all -> 0x0182 }
            r1.zza((com.google.android.gms.internal.zzeok) r2)     // Catch:{ all -> 0x0182 }
            r11.close()
            return
        L_0x01ae:
            r0 = move-exception
            com.google.android.gms.internal.zzeoj r1 = r11.zznpx     // Catch:{ all -> 0x0182 }
            r1.zza((com.google.android.gms.internal.zzeok) r0)     // Catch:{ all -> 0x0182 }
            r11.close()
            return
        L_0x01b8:
            r11.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzeoe.zzcdg():void");
    }

    public final synchronized void close() {
        int i = zzeoh.zznqg[this.zznpw - 1];
        if (i == 1) {
            this.zznpw = zzeoi.zznql;
        } else if (i == 2) {
            zzcde();
        } else if (i == 3) {
            try {
                this.zznpw = zzeoi.zznqk;
                this.zznqa.zzcdl();
                this.zznqa.zza((byte) 8, true, new byte[0]);
            } catch (IOException e) {
                this.zznpx.zza(new zzeok("Failed to send close frame", e));
            }
        } else if (i == 4) {
        }
    }

    public final synchronized void connect() {
        if (this.zznpw != zzeoi.zznqh) {
            this.zznpx.zza(new zzeok("connect() already called"));
            close();
            return;
        }
        zzeod zzeod = zznqe;
        Thread thread = this.zznqc;
        int i = this.zzfug;
        StringBuilder sb = new StringBuilder(26);
        sb.append("TubeSockReader-");
        sb.append(i);
        zzeod.zza(thread, sb.toString());
        this.zznpw = zzeoi.zznqi;
        this.zznqc.start();
    }

    public final void zza(zzeoj zzeoj) {
        this.zznpx = zzeoj;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzaq(byte[] bArr) {
        zza((byte) 10, bArr);
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzeok zzeok) {
        this.zznpx.zza(zzeok);
        if (this.zznpw == zzeoi.zznqj) {
            close();
        }
        zzcde();
    }

    /* access modifiers changed from: package-private */
    public final zzeoj zzcdc() {
        return this.zznpx;
    }

    /* access modifiers changed from: package-private */
    public final void zzcdd() {
        zzcde();
    }

    public final void zzcdf() throws InterruptedException {
        if (this.zznqa.zzcdn().getState() != Thread.State.NEW) {
            this.zznqa.zzcdn().join();
        }
        this.zznqc.join();
    }

    public final synchronized void zzpy(String str) {
        zza((byte) 1, str.getBytes(zznpv));
    }
}
