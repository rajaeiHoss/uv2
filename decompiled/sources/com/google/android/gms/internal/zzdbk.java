package com.google.android.gms.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

final class zzdbk implements zzczt {
    private final Context mContext;
    private final String zzddt;
    private final zzdbn zzkyi;
    private final zzdbm zzkyj;

    zzdbk(Context context, zzdbm zzdbm) {
        this(new zzdbl(), context, zzdbm);
    }

    private zzdbk(zzdbn zzdbn, Context context, zzdbm zzdbm) {
        this.zzkyi = zzdbn;
        this.mContext = context.getApplicationContext();
        this.zzkyj = zzdbm;
        String str = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        String str2 = null;
        if (!(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
                sb.append("-");
                sb.append(locale.getCountry().toLowerCase());
            }
            str2 = sb.toString();
        }
        this.zzddt = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleTagManager", "5.06", str, str2, Build.MODEL, Build.ID});
    }

    private static URL zzd(zzczx zzczx) {
        try {
            return new URL(zzczx.zzbgt());
        } catch (MalformedURLException unused) {
            zzdal.e("Error trying to parse the GTM url.");
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x021d, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0224, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0228, code lost:
        r5 = java.lang.String.valueOf(r11);
        r6 = r0.getClass().getSimpleName();
        r12 = new java.lang.StringBuilder((java.lang.String.valueOf(r5).length() + 27) + java.lang.String.valueOf(r6).length());
        r12.append("Exception sending hit to ");
        r12.append(r5);
        r12.append(": ");
        r12.append(r6);
        com.google.android.gms.internal.zzdal.zzcz(r12.toString());
        com.google.android.gms.internal.zzdal.zzcz(r0.getMessage());
        r1.zzkyj.zzc(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00f5, code lost:
        r6 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00f6, code lost:
        if (r6 == 0) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f9, code lost:
        if (r6 == 1) goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00fb, code lost:
        if (r6 == 2) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00fe, code lost:
        if (r6 == 3) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0103, code lost:
        r14.setRequestMethod(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0106, code lost:
        if (r13 == null) goto L_0x0100;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0108, code lost:
        r14.setDoOutput(true);
        r5 = r13.getBytes(java.nio.charset.Charset.forName("UTF-8"));
        r14.setFixedLengthStreamingMode(r5.length);
        r6 = new java.io.BufferedOutputStream(r14.getOutputStream());
        r6.write(r5);
        r6.flush();
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x012c, code lost:
        if (r13 == null) goto L_0x0140;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x012e, code lost:
        r5 = new java.lang.Object[2];
        r5[0] = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r5[1] = r13;
        com.google.android.gms.internal.zzdal.zzcz(java.lang.String.format("Body of %s hit is ignored: %s.", r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0142, code lost:
        r14.setRequestMethod(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0145, code lost:
        r5 = r14.getResponseCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x014b, code lost:
        if (r5 == 200) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x014d, code lost:
        r0 = java.lang.String.valueOf(r11);
        r13 = new java.lang.StringBuilder(java.lang.String.valueOf(r0).length() + 39);
        r13.append("Bad response received for ");
        r13.append(r0);
        r13.append(": ");
        r13.append(r5);
        com.google.android.gms.internal.zzdal.zzcz(r13.toString());
        r0 = new java.lang.StringBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r5 = new java.io.BufferedReader(new java.io.InputStreamReader(r14.getErrorStream()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r6 = r5.readLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x018c, code lost:
        if (r6 == null) goto L_0x0192;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018e, code lost:
        r0.append(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0192, code lost:
        r0 = java.lang.String.valueOf(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01a0, code lost:
        if (r0.length() == 0) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01a2, code lost:
        r0 = "Error Message: ".concat(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a7, code lost:
        r0 = new java.lang.String("Error Message: ");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01ac, code lost:
        com.google.android.gms.internal.zzdal.zzcz(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        r5.close();
        r1.zzkyj.zzc(r10);
        r16 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01ba, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01bc, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01bd, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01be, code lost:
        if (r5 != null) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01c0, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01c3, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01c4, code lost:
        r5 = r14.getInputStream();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        r6 = java.lang.String.valueOf(r11);
        r12 = new java.lang.StringBuilder((java.lang.String.valueOf(r6).length() + 23) + java.lang.String.valueOf(r0).length());
        r12.append("Hit sent to ");
        r12.append(r6);
        r12.append("(method = ");
        r12.append(r0);
        r12.append(")");
        com.google.android.gms.internal.zzdal.v(r12.toString());
        r1.zzkyj.zza(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0206, code lost:
        r16 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0208, code lost:
        if (r16 == null) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        r16.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x020d, code lost:
        r14.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0211, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0212, code lost:
        r16 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0215, code lost:
        r0 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x021d A[Catch:{ IOException -> 0x0224 }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01c0 A[Catch:{ all -> 0x01ba, all -> 0x0215 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzam(java.util.List<com.google.android.gms.internal.zzczx> r19) {
        /*
            r18 = this;
            r1 = r18
            java.lang.String r2 = "GET"
            java.lang.String r3 = ": "
            int r0 = r19.size()
            r4 = 40
            int r4 = java.lang.Math.min(r0, r4)
            r6 = 1
            r7 = 0
            r8 = 1
        L_0x0013:
            if (r7 >= r4) goto L_0x0272
            r9 = r19
            java.lang.Object r0 = r9.get(r7)
            r10 = r0
            com.google.android.gms.internal.zzczx r10 = (com.google.android.gms.internal.zzczx) r10
            java.net.URL r11 = zzd(r10)
            java.lang.String r0 = r10.zzbis()
            java.util.Map r12 = r10.zzbit()
            java.lang.String r13 = r10.zzbiu()
            if (r11 != 0) goto L_0x003d
            java.lang.String r0 = "No destination: discarding hit."
            com.google.android.gms.internal.zzdal.zzcz(r0)
            com.google.android.gms.internal.zzdbm r0 = r1.zzkyj
            r0.zzb(r10)
        L_0x003a:
            r15 = 1
            goto L_0x026d
        L_0x003d:
            com.google.android.gms.internal.zzdbn r14 = r1.zzkyi     // Catch:{ IOException -> 0x0226 }
            java.net.HttpURLConnection r14 = r14.zzc(r11)     // Catch:{ IOException -> 0x0226 }
            if (r8 == 0) goto L_0x004b
            android.content.Context r15 = r1.mContext     // Catch:{ all -> 0x0217 }
            com.google.android.gms.internal.zzdan.zzeo(r15)     // Catch:{ all -> 0x0217 }
            r8 = 0
        L_0x004b:
            java.lang.String r15 = "User-Agent"
            java.lang.String r5 = r1.zzddt     // Catch:{ all -> 0x0217 }
            r14.setRequestProperty(r15, r5)     // Catch:{ all -> 0x0217 }
            if (r12 == 0) goto L_0x0078
            java.util.Set r5 = r12.entrySet()     // Catch:{ all -> 0x0217 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0217 }
        L_0x005c:
            boolean r12 = r5.hasNext()     // Catch:{ all -> 0x0217 }
            if (r12 == 0) goto L_0x0078
            java.lang.Object r12 = r5.next()     // Catch:{ all -> 0x0217 }
            java.util.Map$Entry r12 = (java.util.Map.Entry) r12     // Catch:{ all -> 0x0217 }
            java.lang.Object r15 = r12.getKey()     // Catch:{ all -> 0x0217 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x0217 }
            java.lang.Object r12 = r12.getValue()     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0217 }
            r14.setRequestProperty(r15, r12)     // Catch:{ all -> 0x0217 }
            goto L_0x005c
        L_0x0078:
            if (r0 != 0) goto L_0x0099
            java.lang.String r0 = "Hit %d retrieved from the store has null HTTP method."
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x0217 }
            long r12 = r10.zzbgr()     // Catch:{ all -> 0x0217 }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0217 }
            r13 = 0
            r5[r13] = r12     // Catch:{ all -> 0x0217 }
            java.lang.String r0 = java.lang.String.format(r0, r5)     // Catch:{ all -> 0x0217 }
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ all -> 0x0217 }
            com.google.android.gms.internal.zzdbm r0 = r1.zzkyj     // Catch:{ all -> 0x0217 }
            r0.zzb(r10)     // Catch:{ all -> 0x0217 }
        L_0x0095:
            r14.disconnect()     // Catch:{ IOException -> 0x0226 }
            goto L_0x003a
        L_0x0099:
            boolean r5 = r0.equals(r2)     // Catch:{ all -> 0x0217 }
            java.lang.String r12 = "POST"
            java.lang.String r15 = "PUT"
            java.lang.String r6 = "HEAD"
            if (r5 != 0) goto L_0x00cc
            boolean r5 = r0.equals(r6)     // Catch:{ all -> 0x0217 }
            if (r5 != 0) goto L_0x00cc
            boolean r5 = r0.equals(r12)     // Catch:{ all -> 0x0217 }
            if (r5 != 0) goto L_0x00cc
            boolean r5 = r0.equals(r15)     // Catch:{ all -> 0x0217 }
            if (r5 != 0) goto L_0x00cc
            java.lang.String r5 = "Unrecongnized HTTP method %s. Supported methods are GET, HEAD, PUT and/or POST"
            r6 = 1
            java.lang.Object[] r12 = new java.lang.Object[r6]     // Catch:{ all -> 0x0217 }
            r6 = 0
            r12[r6] = r0     // Catch:{ all -> 0x0217 }
            java.lang.String r0 = java.lang.String.format(r5, r12)     // Catch:{ all -> 0x0217 }
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ all -> 0x0217 }
            com.google.android.gms.internal.zzdbm r0 = r1.zzkyj     // Catch:{ all -> 0x0217 }
            r0.zzb(r10)     // Catch:{ all -> 0x0217 }
            goto L_0x0095
        L_0x00cc:
            int r17 = r0.hashCode()     // Catch:{ all -> 0x0217 }
            r5 = 2
            switch(r17) {
                case 70454: goto L_0x00ed;
                case 79599: goto L_0x00e5;
                case 2213344: goto L_0x00dd;
                case 2461856: goto L_0x00d5;
                default: goto L_0x00d4;
            }     // Catch:{ all -> 0x0217 }
        L_0x00d4:
            goto L_0x00f5
        L_0x00d5:
            boolean r6 = r0.equals(r12)     // Catch:{ all -> 0x0217 }
            if (r6 == 0) goto L_0x00f5
            r6 = 2
            goto L_0x00f6
        L_0x00dd:
            boolean r6 = r0.equals(r6)     // Catch:{ all -> 0x0217 }
            if (r6 == 0) goto L_0x00f5
            r6 = 1
            goto L_0x00f6
        L_0x00e5:
            boolean r6 = r0.equals(r15)     // Catch:{ all -> 0x0217 }
            if (r6 == 0) goto L_0x00f5
            r6 = 3
            goto L_0x00f6
        L_0x00ed:
            boolean r6 = r0.equals(r2)     // Catch:{ all -> 0x0217 }
            if (r6 == 0) goto L_0x00f5
            r6 = 0
            goto L_0x00f6
        L_0x00f5:
            r6 = -1
        L_0x00f6:
            if (r6 == 0) goto L_0x012c
            r12 = 1
            if (r6 == r12) goto L_0x012c
            if (r6 == r5) goto L_0x0103
            r5 = 3
            if (r6 == r5) goto L_0x0103
        L_0x0100:
            r12 = 0
            r15 = 1
            goto L_0x0145
        L_0x0103:
            r14.setRequestMethod(r0)     // Catch:{ all -> 0x0217 }
            if (r13 == 0) goto L_0x0100
            r14.setDoOutput(r12)     // Catch:{ all -> 0x0217 }
            java.lang.String r5 = "UTF-8"
            java.nio.charset.Charset r5 = java.nio.charset.Charset.forName(r5)     // Catch:{ all -> 0x0217 }
            byte[] r5 = r13.getBytes(r5)     // Catch:{ all -> 0x0217 }
            int r6 = r5.length     // Catch:{ all -> 0x0217 }
            r14.setFixedLengthStreamingMode(r6)     // Catch:{ all -> 0x0217 }
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0217 }
            java.io.OutputStream r12 = r14.getOutputStream()     // Catch:{ all -> 0x0217 }
            r6.<init>(r12)     // Catch:{ all -> 0x0217 }
            r6.write(r5)     // Catch:{ all -> 0x0217 }
            r6.flush()     // Catch:{ all -> 0x0217 }
            r6.close()     // Catch:{ all -> 0x0217 }
            goto L_0x0100
        L_0x012c:
            if (r13 == 0) goto L_0x0140
            java.lang.String r6 = "Body of %s hit is ignored: %s."
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0217 }
            r12 = 0
            r5[r12] = r0     // Catch:{ all -> 0x0217 }
            r15 = 1
            r5[r15] = r13     // Catch:{ all -> 0x0215 }
            java.lang.String r5 = java.lang.String.format(r6, r5)     // Catch:{ all -> 0x0215 }
            com.google.android.gms.internal.zzdal.zzcz(r5)     // Catch:{ all -> 0x0215 }
            goto L_0x0142
        L_0x0140:
            r12 = 0
            r15 = 1
        L_0x0142:
            r14.setRequestMethod(r0)     // Catch:{ all -> 0x0215 }
        L_0x0145:
            int r5 = r14.getResponseCode()     // Catch:{ all -> 0x0215 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 == r6) goto L_0x01c4
            java.lang.String r0 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0215 }
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0215 }
            int r6 = r6.length()     // Catch:{ all -> 0x0215 }
            int r6 = r6 + 39
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x0215 }
            r13.<init>(r6)     // Catch:{ all -> 0x0215 }
            java.lang.String r6 = "Bad response received for "
            r13.append(r6)     // Catch:{ all -> 0x0215 }
            r13.append(r0)     // Catch:{ all -> 0x0215 }
            r13.append(r3)     // Catch:{ all -> 0x0215 }
            r13.append(r5)     // Catch:{ all -> 0x0215 }
            java.lang.String r0 = r13.toString()     // Catch:{ all -> 0x0215 }
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ all -> 0x0215 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0215 }
            r0.<init>()     // Catch:{ all -> 0x0215 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ all -> 0x01bc }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ all -> 0x01bc }
            java.io.InputStream r13 = r14.getErrorStream()     // Catch:{ all -> 0x01bc }
            r6.<init>(r13)     // Catch:{ all -> 0x01bc }
            r5.<init>(r6)     // Catch:{ all -> 0x01bc }
        L_0x0188:
            java.lang.String r6 = r5.readLine()     // Catch:{ all -> 0x01ba }
            if (r6 == 0) goto L_0x0192
            r0.append(r6)     // Catch:{ all -> 0x01ba }
            goto L_0x0188
        L_0x0192:
            java.lang.String r6 = "Error Message: "
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01ba }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x01ba }
            int r13 = r0.length()     // Catch:{ all -> 0x01ba }
            if (r13 == 0) goto L_0x01a7
            java.lang.String r0 = r6.concat(r0)     // Catch:{ all -> 0x01ba }
            goto L_0x01ac
        L_0x01a7:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x01ba }
            r0.<init>(r6)     // Catch:{ all -> 0x01ba }
        L_0x01ac:
            com.google.android.gms.internal.zzdal.zzcz(r0)     // Catch:{ all -> 0x01ba }
            r5.close()     // Catch:{ all -> 0x0215 }
            com.google.android.gms.internal.zzdbm r0 = r1.zzkyj     // Catch:{ all -> 0x0215 }
            r0.zzc(r10)     // Catch:{ all -> 0x0215 }
            r16 = 0
            goto L_0x0208
        L_0x01ba:
            r0 = move-exception
            goto L_0x01be
        L_0x01bc:
            r0 = move-exception
            r5 = 0
        L_0x01be:
            if (r5 == 0) goto L_0x01c3
            r5.close()     // Catch:{ all -> 0x0215 }
        L_0x01c3:
            throw r0     // Catch:{ all -> 0x0215 }
        L_0x01c4:
            java.io.InputStream r5 = r14.getInputStream()     // Catch:{ all -> 0x0215 }
            java.lang.String r6 = java.lang.String.valueOf(r11)     // Catch:{ all -> 0x0211 }
            java.lang.String r13 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x0211 }
            int r13 = r13.length()     // Catch:{ all -> 0x0211 }
            int r13 = r13 + 23
            java.lang.String r16 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0211 }
            int r16 = r16.length()     // Catch:{ all -> 0x0211 }
            int r13 = r13 + r16
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ all -> 0x0211 }
            r12.<init>(r13)     // Catch:{ all -> 0x0211 }
            java.lang.String r13 = "Hit sent to "
            r12.append(r13)     // Catch:{ all -> 0x0211 }
            r12.append(r6)     // Catch:{ all -> 0x0211 }
            java.lang.String r6 = "(method = "
            r12.append(r6)     // Catch:{ all -> 0x0211 }
            r12.append(r0)     // Catch:{ all -> 0x0211 }
            java.lang.String r0 = ")"
            r12.append(r0)     // Catch:{ all -> 0x0211 }
            java.lang.String r0 = r12.toString()     // Catch:{ all -> 0x0211 }
            com.google.android.gms.internal.zzdal.v(r0)     // Catch:{ all -> 0x0211 }
            com.google.android.gms.internal.zzdbm r0 = r1.zzkyj     // Catch:{ all -> 0x0211 }
            r0.zza(r10)     // Catch:{ all -> 0x0211 }
            r16 = r5
        L_0x0208:
            if (r16 == 0) goto L_0x020d
            r16.close()     // Catch:{ IOException -> 0x0224 }
        L_0x020d:
            r14.disconnect()     // Catch:{ IOException -> 0x0224 }
            goto L_0x026d
        L_0x0211:
            r0 = move-exception
            r16 = r5
            goto L_0x021b
        L_0x0215:
            r0 = move-exception
            goto L_0x0219
        L_0x0217:
            r0 = move-exception
            r15 = 1
        L_0x0219:
            r16 = 0
        L_0x021b:
            if (r16 == 0) goto L_0x0220
            r16.close()     // Catch:{ IOException -> 0x0224 }
        L_0x0220:
            r14.disconnect()     // Catch:{ IOException -> 0x0224 }
            throw r0     // Catch:{ IOException -> 0x0224 }
        L_0x0224:
            r0 = move-exception
            goto L_0x0228
        L_0x0226:
            r0 = move-exception
            r15 = 1
        L_0x0228:
            java.lang.String r5 = java.lang.String.valueOf(r11)
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = r6.getSimpleName()
            java.lang.String r11 = java.lang.String.valueOf(r5)
            int r11 = r11.length()
            int r11 = r11 + 27
            java.lang.String r12 = java.lang.String.valueOf(r6)
            int r12 = r12.length()
            int r11 = r11 + r12
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r11)
            java.lang.String r11 = "Exception sending hit to "
            r12.append(r11)
            r12.append(r5)
            r12.append(r3)
            r12.append(r6)
            java.lang.String r5 = r12.toString()
            com.google.android.gms.internal.zzdal.zzcz(r5)
            java.lang.String r0 = r0.getMessage()
            com.google.android.gms.internal.zzdal.zzcz(r0)
            com.google.android.gms.internal.zzdbm r0 = r1.zzkyj
            r0.zzc(r10)
        L_0x026d:
            int r7 = r7 + 1
            r6 = 1
            goto L_0x0013
        L_0x0272:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdbk.zzam(java.util.List):void");
    }

    public final boolean zzbgj() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdal.v("...no network connectivity");
        return false;
    }
}
