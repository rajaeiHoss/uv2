package com.google.android.gms.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.zzbq;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.text.Typography;

final class zzatf extends zzari {
    /* access modifiers changed from: private */
    public static final byte[] zzedy = "\n".getBytes();
    private final String zzddt = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleAnalytics", zzarj.VERSION, Build.VERSION.RELEASE, zzatt.zza(Locale.getDefault()), Build.MODEL, Build.ID});
    private final zzatp zzedx;

    zzatf(zzark zzark) {
        super(zzark);
        this.zzedx = new zzatp(zzark.zzxx());
    }

    private final int zza(URL url) {
        zzbq.checkNotNull(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection zzb = zzb(url);
            zzb.connect();
            zzb(zzb);
            int responseCode = zzb.getResponseCode();
            if (responseCode == 200) {
                zzyc().zzxv();
            }
            zzb("GET status", Integer.valueOf(responseCode));
            if (zzb != null) {
                zzb.disconnect();
            }
            return responseCode;
        } catch (IOException e) {
            zzd("Network GET connection error", e);
            if (httpURLConnection == null) {
                return 0;
            }
            httpURLConnection.disconnect();
            return 0;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.net.HttpURLConnection} */
    /* JADX WARNING: type inference failed for: r5v1, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r5v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r5v5 */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: type inference failed for: r5v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0079 A[SYNTHETIC, Splitter:B:29:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008b A[SYNTHETIC, Splitter:B:37:0x008b] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.net.URL r5, byte[] r6) {
        /*
            r4 = this;
            java.lang.String r0 = "Error closing http post connection output stream"
            com.google.android.gms.common.internal.zzbq.checkNotNull(r5)
            com.google.android.gms.common.internal.zzbq.checkNotNull(r6)
            int r1 = r6.length
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "POST bytes, url"
            r4.zzb(r2, r1, r5)
            boolean r1 = zzqk()
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = new java.lang.String
            r1.<init>(r6)
            java.lang.String r2 = "Post payload\n"
            r4.zza(r2, r1)
        L_0x0022:
            r1 = 0
            android.content.Context r2 = r4.getContext()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r2.getPackageName()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.net.HttpURLConnection r5 = r4.zzb((java.net.URL) r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r2 = 1
            r5.setDoOutput(r2)     // Catch:{ IOException -> 0x006b }
            int r2 = r6.length     // Catch:{ IOException -> 0x006b }
            r5.setFixedLengthStreamingMode(r2)     // Catch:{ IOException -> 0x006b }
            r5.connect()     // Catch:{ IOException -> 0x006b }
            java.io.OutputStream r1 = r5.getOutputStream()     // Catch:{ IOException -> 0x006b }
            r1.write(r6)     // Catch:{ IOException -> 0x006b }
            r4.zzb((java.net.HttpURLConnection) r5)     // Catch:{ IOException -> 0x006b }
            int r6 = r5.getResponseCode()     // Catch:{ IOException -> 0x006b }
            r2 = 200(0xc8, float:2.8E-43)
            if (r6 != r2) goto L_0x0052
            com.google.android.gms.internal.zzaqz r2 = r4.zzyc()     // Catch:{ IOException -> 0x006b }
            r2.zzxv()     // Catch:{ IOException -> 0x006b }
        L_0x0052:
            java.lang.String r2 = "POST status"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x006b }
            r4.zzb(r2, r3)     // Catch:{ IOException -> 0x006b }
            if (r1 == 0) goto L_0x0065
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x0065
        L_0x0061:
            r1 = move-exception
            r4.zze(r0, r1)
        L_0x0065:
            if (r5 == 0) goto L_0x006a
            r5.disconnect()
        L_0x006a:
            return r6
        L_0x006b:
            r6 = move-exception
            goto L_0x0072
        L_0x006d:
            r6 = move-exception
            r5 = r1
            goto L_0x0089
        L_0x0070:
            r6 = move-exception
            r5 = r1
        L_0x0072:
            java.lang.String r2 = "Network POST connection error"
            r4.zzd(r2, r6)     // Catch:{ all -> 0x0088 }
            if (r1 == 0) goto L_0x0081
            r1.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r6 = move-exception
            r4.zze(r0, r6)
        L_0x0081:
            if (r5 == 0) goto L_0x0086
            r5.disconnect()
        L_0x0086:
            r5 = 0
            return r5
        L_0x0088:
            r6 = move-exception
        L_0x0089:
            if (r1 == 0) goto L_0x0093
            r1.close()     // Catch:{ IOException -> 0x008f }
            goto L_0x0093
        L_0x008f:
            r1 = move-exception
            r4.zze(r0, r1)
        L_0x0093:
            if (r5 == 0) goto L_0x0098
            r5.disconnect()
        L_0x0098:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatf.zza(java.net.URL, byte[]):int");
    }

    private static void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append(Typography.amp);
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }

    private final URL zzaay() {
        String valueOf = String.valueOf(zzasl.zzaab());
        String valueOf2 = String.valueOf(zzast.zzece.get());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00d6 A[SYNTHETIC, Splitter:B:42:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00e8 A[SYNTHETIC, Splitter:B:50:0x00e8] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzb(java.net.URL r11, byte[] r12) {
        /*
            r10 = this;
            java.lang.String r0 = "Error closing http compressed post connection output stream"
            com.google.android.gms.common.internal.zzbq.checkNotNull(r11)
            com.google.android.gms.common.internal.zzbq.checkNotNull(r12)
            r1 = 0
            android.content.Context r2 = r10.getContext()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r2.getPackageName()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r2.<init>()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            java.util.zip.GZIPOutputStream r3 = new java.util.zip.GZIPOutputStream     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r3.<init>(r2)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r3.write(r12)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r3.close()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r2.close()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            byte[] r2 = r2.toByteArray()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            java.lang.String r3 = "POST compressed size, ratio %, url"
            int r4 = r2.length     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r5 = 100
            int r7 = r2.length     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            long r7 = (long) r7     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            long r7 = r7 * r5
            int r5 = r12.length     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            long r5 = (long) r5     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            long r7 = r7 / r5
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r10.zza(r3, r4, r5, r11)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            int r3 = r2.length     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            int r4 = r12.length     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            if (r3 <= r4) goto L_0x0051
            java.lang.String r3 = "Compressed payload is larger then uncompressed. compressed, uncompressed"
            int r4 = r2.length     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            int r5 = r12.length     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r10.zzc(r3, r4, r5)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
        L_0x0051:
            boolean r3 = zzqk()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            if (r3 == 0) goto L_0x0073
            java.lang.String r3 = "Post payload"
            java.lang.String r4 = "\n"
            java.lang.String r5 = new java.lang.String     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r5.<init>(r12)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            int r12 = r5.length()     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            if (r12 == 0) goto L_0x006b
            java.lang.String r12 = r4.concat(r5)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            goto L_0x0070
        L_0x006b:
            java.lang.String r12 = new java.lang.String     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r12.<init>(r4)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
        L_0x0070:
            r10.zza(r3, r12)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
        L_0x0073:
            java.net.HttpURLConnection r11 = r10.zzb((java.net.URL) r11)     // Catch:{ IOException -> 0x00cd, all -> 0x00ca }
            r12 = 1
            r11.setDoOutput(r12)     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            java.lang.String r12 = "Content-Encoding"
            java.lang.String r3 = "gzip"
            r11.addRequestProperty(r12, r3)     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            int r12 = r2.length     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            r11.setFixedLengthStreamingMode(r12)     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            r11.connect()     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            java.io.OutputStream r12 = r11.getOutputStream()     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            r12.write(r2)     // Catch:{ IOException -> 0x00ba, all -> 0x00b4 }
            r12.close()     // Catch:{ IOException -> 0x00ba, all -> 0x00b4 }
            r10.zzb((java.net.HttpURLConnection) r11)     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            int r12 = r11.getResponseCode()     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            r2 = 200(0xc8, float:2.8E-43)
            if (r12 != r2) goto L_0x00a5
            com.google.android.gms.internal.zzaqz r2 = r10.zzyc()     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            r2.zzxv()     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
        L_0x00a5:
            java.lang.String r2 = "POST status"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r12)     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            r10.zzb(r2, r3)     // Catch:{ IOException -> 0x00c5, all -> 0x00c0 }
            if (r11 == 0) goto L_0x00b3
            r11.disconnect()
        L_0x00b3:
            return r12
        L_0x00b4:
            r1 = move-exception
            r9 = r12
            r12 = r11
            r11 = r1
            r1 = r9
            goto L_0x00e6
        L_0x00ba:
            r1 = move-exception
            r9 = r12
            r12 = r11
            r11 = r1
            r1 = r9
            goto L_0x00cf
        L_0x00c0:
            r12 = move-exception
            r9 = r12
            r12 = r11
            r11 = r9
            goto L_0x00e6
        L_0x00c5:
            r12 = move-exception
            r9 = r12
            r12 = r11
            r11 = r9
            goto L_0x00cf
        L_0x00ca:
            r11 = move-exception
            r12 = r1
            goto L_0x00e6
        L_0x00cd:
            r11 = move-exception
            r12 = r1
        L_0x00cf:
            java.lang.String r2 = "Network compressed POST connection error"
            r10.zzd(r2, r11)     // Catch:{ all -> 0x00e5 }
            if (r1 == 0) goto L_0x00de
            r1.close()     // Catch:{ IOException -> 0x00da }
            goto L_0x00de
        L_0x00da:
            r11 = move-exception
            r10.zze(r0, r11)
        L_0x00de:
            if (r12 == 0) goto L_0x00e3
            r12.disconnect()
        L_0x00e3:
            r11 = 0
            return r11
        L_0x00e5:
            r11 = move-exception
        L_0x00e6:
            if (r1 == 0) goto L_0x00f0
            r1.close()     // Catch:{ IOException -> 0x00ec }
            goto L_0x00f0
        L_0x00ec:
            r1 = move-exception
            r10.zze(r0, r1)
        L_0x00f0:
            if (r12 == 0) goto L_0x00f5
            r12.disconnect()
        L_0x00f5:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatf.zzb(java.net.URL, byte[]):int");
    }

    private final HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(zzast.zzecp.get().intValue());
            httpURLConnection.setReadTimeout(zzast.zzecq.get().intValue());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("User-Agent", this.zzddt);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    private final URL zzb(zzasy zzasy, String str) {
        String str2;
        StringBuilder sb;
        String str3;
        if (zzasy.zzaap()) {
            str2 = zzasl.zzaab();
            str3 = zzasl.zzaad();
            sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str3).length() + String.valueOf(str).length());
        } else {
            str2 = zzasl.zzaac();
            str3 = zzasl.zzaad();
            sb = new StringBuilder(String.valueOf(str2).length() + 1 + String.valueOf(str3).length() + String.valueOf(str).length());
        }
        sb.append(str2);
        sb.append(str3);
        sb.append("?");
        sb.append(str);
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0021 A[SYNTHETIC, Splitter:B:18:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(java.net.HttpURLConnection r4) throws java.io.IOException {
        /*
            r3 = this;
            java.lang.String r0 = "Error closing http connection input stream"
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ all -> 0x001d }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x001b }
        L_0x000a:
            int r2 = r4.read(r1)     // Catch:{ all -> 0x001b }
            if (r2 > 0) goto L_0x000a
            if (r4 == 0) goto L_0x001a
            r4.close()     // Catch:{ IOException -> 0x0016 }
            return
        L_0x0016:
            r4 = move-exception
            r3.zze(r0, r4)
        L_0x001a:
            return
        L_0x001b:
            r1 = move-exception
            goto L_0x001f
        L_0x001d:
            r1 = move-exception
            r4 = 0
        L_0x001f:
            if (r4 == 0) goto L_0x0029
            r4.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r4 = move-exception
            r3.zze(r0, r4)
        L_0x0029:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatf.zzb(java.net.HttpURLConnection):void");
    }

    private final URL zzd(zzasy zzasy) {
        String valueOf = String.valueOf(zzasy.zzaap() ? zzasl.zzaab() : zzasl.zzaac());
        String valueOf2 = String.valueOf(zzasl.zzaad());
        String str = valueOf2.length() == 0 ? new String(valueOf) : valueOf.concat(valueOf2);
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0057, code lost:
        if (zza(r2) == 200) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0090, code lost:
        if (zza(r5, r2) == 200) goto L_0x0092;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9 A[EDGE_INSN: B:32:0x00a9->B:30:0x00a9 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.Long> zzv(java.util.List<com.google.android.gms.internal.zzasy> r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r9.size()
            r0.<init>(r1)
            java.util.Iterator r9 = r9.iterator()
        L_0x000d:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L_0x00a9
            java.lang.Object r1 = r9.next()
            com.google.android.gms.internal.zzasy r1 = (com.google.android.gms.internal.zzasy) r1
            com.google.android.gms.common.internal.zzbq.checkNotNull(r1)
            boolean r2 = r1.zzaap()
            r3 = 1
            r2 = r2 ^ r3
            java.lang.String r2 = r8.zza((com.google.android.gms.internal.zzasy) r1, (boolean) r2)
            r4 = 0
            if (r2 != 0) goto L_0x0033
            com.google.android.gms.internal.zzatd r2 = r8.zzxy()
            java.lang.String r4 = "Error formatting hit for upload"
        L_0x002f:
            r2.zza(r1, r4)
            goto L_0x0092
        L_0x0033:
            int r5 = r2.length()
            com.google.android.gms.internal.zzasu<java.lang.Integer> r6 = com.google.android.gms.internal.zzast.zzecf
            java.lang.Object r6 = r6.get()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r7 = 200(0xc8, float:2.8E-43)
            if (r5 > r6) goto L_0x005c
            java.net.URL r2 = r8.zzb((com.google.android.gms.internal.zzasy) r1, (java.lang.String) r2)
            if (r2 != 0) goto L_0x0053
            java.lang.String r2 = "Failed to build collect GET endpoint url"
        L_0x004f:
            r8.zzee(r2)
            goto L_0x005a
        L_0x0053:
            int r2 = r8.zza(r2)
            if (r2 != r7) goto L_0x005a
            goto L_0x0092
        L_0x005a:
            r3 = 0
            goto L_0x0092
        L_0x005c:
            java.lang.String r2 = r8.zza((com.google.android.gms.internal.zzasy) r1, (boolean) r4)
            if (r2 != 0) goto L_0x0069
            com.google.android.gms.internal.zzatd r2 = r8.zzxy()
            java.lang.String r4 = "Error formatting hit for POST upload"
            goto L_0x002f
        L_0x0069:
            byte[] r2 = r2.getBytes()
            int r5 = r2.length
            com.google.android.gms.internal.zzasu<java.lang.Integer> r6 = com.google.android.gms.internal.zzast.zzeck
            java.lang.Object r6 = r6.get()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            if (r5 <= r6) goto L_0x0083
            com.google.android.gms.internal.zzatd r2 = r8.zzxy()
            java.lang.String r4 = "Hit payload exceeds size limit"
            goto L_0x002f
        L_0x0083:
            java.net.URL r5 = r8.zzd(r1)
            if (r5 != 0) goto L_0x008c
            java.lang.String r2 = "Failed to build collect POST endpoint url"
            goto L_0x004f
        L_0x008c:
            int r2 = r8.zza((java.net.URL) r5, (byte[]) r2)
            if (r2 != r7) goto L_0x005a
        L_0x0092:
            if (r3 == 0) goto L_0x00a9
            long r1 = r1.zzaam()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r0.add(r1)
            int r1 = r0.size()
            int r2 = com.google.android.gms.internal.zzasl.zzzz()
            if (r1 < r2) goto L_0x000d
        L_0x00a9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatf.zzv(java.util.List):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public final String zza(zzasy zzasy, boolean z) {
        zzbq.checkNotNull(zzasy);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : zzasy.zzjq().entrySet()) {
                String str = (String) next.getKey();
                if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str) && !"z".equals(str) && !"_gmsv".equals(str)) {
                    zza(sb, str, (String) next.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzasy.zzaan()));
            zza(sb, "qt", String.valueOf(zzxx().currentTimeMillis() - zzasy.zzaan()));
            if (z) {
                long zzaaq = zzasy.zzaaq();
                zza(sb, "z", zzaaq != 0 ? String.valueOf(zzaaq) : String.valueOf(zzasy.zzaam()));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    public final boolean zzaax() {
        NetworkInfo networkInfo;
        zzk.zzwj();
        zzyk();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException unused) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzea("No network connectivity");
        return false;
    }

    public final List<Long> zzu(List<zzasy> list) {
        boolean z;
        zzk.zzwj();
        zzyk();
        zzbq.checkNotNull(list);
        boolean z2 = false;
        if (zzxz().zzaae().isEmpty() || !this.zzedx.zzu(((long) zzast.zzecn.get().intValue()) * 1000)) {
            z = false;
        } else {
            z = zzasb.zzej(zzast.zzecg.get()) != zzasb.NONE;
            if (zzash.zzek(zzast.zzech.get()) == zzash.GZIP) {
                z2 = true;
            }
        }
        if (!z) {
            return zzv(list);
        }
        zzbq.checkArgument(!list.isEmpty());
        zza("Uploading batched hits. compression, count", Boolean.valueOf(z2), Integer.valueOf(list.size()));
        zzatg zzatg = new zzatg(this);
        ArrayList arrayList = new ArrayList();
        for (zzasy next : list) {
            if (!zzatg.zze(next)) {
                break;
            }
            arrayList.add(Long.valueOf(next.zzaam()));
        }
        if (zzatg.zzaaz() == 0) {
            return arrayList;
        }
        URL zzaay = zzaay();
        if (zzaay == null) {
            zzee("Failed to build batching endpoint url");
        } else {
            int zzb = z2 ? zzb(zzaay, zzatg.getPayload()) : zza(zzaay, zzatg.getPayload());
            if (200 == zzb) {
                zza("Batched upload completed. Hits batched", Integer.valueOf(zzatg.zzaaz()));
                return arrayList;
            }
            zza("Network error uploading hits. status code", Integer.valueOf(zzb));
            if (zzxz().zzaae().contains(Integer.valueOf(zzb))) {
                zzed("Server instructed the client to stop batching");
                this.zzedx.start();
            }
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
        zza("Network initialized. User agent", this.zzddt);
    }
}
