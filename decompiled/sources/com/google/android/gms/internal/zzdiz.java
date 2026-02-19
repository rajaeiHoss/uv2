package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzdiz implements Runnable {
    private final Context mContext;
    private final zzdim zzlay;
    private final zzdid zzlby;
    private final zzdiy zzlbz;
    private final zzdiv zzlca;

    public zzdiz(Context context, zzdim zzdim, zzdid zzdid) {
        this(context, zzdim, zzdid, new zzdiy(), new zzdiv());
    }

    private zzdiz(Context context, zzdim zzdim, zzdid zzdid, zzdiy zzdiy, zzdiv zzdiv) {
        this.mContext = (Context) zzbq.checkNotNull(context);
        this.zzlby = (zzdid) zzbq.checkNotNull(zzdid);
        this.zzlay = zzdim;
        this.zzlbz = zzdiy;
        this.zzlca = zzdiv;
    }

    private final boolean zzeh(String str) {
        return this.mContext.getPackageManager().checkPermission(str, this.mContext.getPackageName()) == 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r9 = this;
            java.lang.String r0 = " "
            java.lang.String r1 = "android.permission.INTERNET"
            boolean r1 = r9.zzeh(r1)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0013
            java.lang.String r1 = "Missing android.permission.INTERNET. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.INTERNET\" />"
        L_0x000e:
            com.google.android.gms.internal.zzdal.e(r1)
        L_0x0011:
            r1 = 0
            goto L_0x003d
        L_0x0013:
            java.lang.String r1 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r1 = r9.zzeh(r1)
            if (r1 != 0) goto L_0x001e
            java.lang.String r1 = "Missing android.permission.ACCESS_NETWORK_STATE. Please add the following declaration to your AndroidManifest.xml: <uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />"
            goto L_0x000e
        L_0x001e:
            android.content.Context r1 = r9.mContext
            java.lang.String r4 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r4)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x0037
            boolean r1 = r1.isConnected()
            if (r1 != 0) goto L_0x0035
            goto L_0x0037
        L_0x0035:
            r1 = 1
            goto L_0x003d
        L_0x0037:
            java.lang.String r1 = "No network connectivity - Offline"
            com.google.android.gms.internal.zzdal.zzcz(r1)
            goto L_0x0011
        L_0x003d:
            if (r1 != 0) goto L_0x0045
            com.google.android.gms.internal.zzdid r0 = r9.zzlby
            r0.zzo(r3, r3)
            return
        L_0x0045:
            java.lang.String r1 = "Starting to load resource from Network."
            com.google.android.gms.internal.zzdal.v(r1)
            com.google.android.gms.internal.zzdiw r1 = new com.google.android.gms.internal.zzdiw
            r1.<init>()
            r4 = 0
            com.google.android.gms.internal.zzdiv r5 = r9.zzlca     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdim r6 = r9.zzlay     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdia r6 = r6.zzbjt()     // Catch:{ all -> 0x014a }
            java.lang.String r5 = r5.zzb(r6)     // Catch:{ all -> 0x014a }
            java.lang.String r6 = "Loading resource from "
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x014a }
            int r8 = r7.length()     // Catch:{ all -> 0x014a }
            if (r8 == 0) goto L_0x006d
            java.lang.String r6 = r6.concat(r7)     // Catch:{ all -> 0x014a }
            goto L_0x0073
        L_0x006d:
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x014a }
            r7.<init>(r6)     // Catch:{ all -> 0x014a }
            r6 = r7
        L_0x0073:
            com.google.android.gms.internal.zzdal.v(r6)     // Catch:{ all -> 0x014a }
            r6 = 2
            java.io.InputStream r4 = r1.zzne(r5)     // Catch:{ FileNotFoundException -> 0x0127, zzdjb -> 0x00b7, IOException -> 0x007c }
            goto L_0x00d7
        L_0x007c:
            r4 = move-exception
            java.lang.String r6 = r4.getMessage()     // Catch:{ all -> 0x014a }
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x014a }
            int r7 = r7.length()     // Catch:{ all -> 0x014a }
            int r7 = r7 + 54
            java.lang.String r8 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x014a }
            int r8 = r8.length()     // Catch:{ all -> 0x014a }
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014a }
            r8.<init>(r7)     // Catch:{ all -> 0x014a }
            java.lang.String r7 = "NetworkLoader: Error when loading resource from url: "
            r8.append(r7)     // Catch:{ all -> 0x014a }
            r8.append(r5)     // Catch:{ all -> 0x014a }
            r8.append(r0)     // Catch:{ all -> 0x014a }
            r8.append(r6)     // Catch:{ all -> 0x014a }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdal.zzb(r0, r4)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdid r0 = r9.zzlby     // Catch:{ all -> 0x014a }
            r0.zzo(r2, r3)     // Catch:{ all -> 0x014a }
            r1.close()
            return
        L_0x00b7:
            java.lang.String r2 = "NetworkLoader: Error when loading resource for url: "
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x014a }
            int r8 = r7.length()     // Catch:{ all -> 0x014a }
            if (r8 == 0) goto L_0x00c8
            java.lang.String r2 = r2.concat(r7)     // Catch:{ all -> 0x014a }
            goto L_0x00ce
        L_0x00c8:
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x014a }
            r7.<init>(r2)     // Catch:{ all -> 0x014a }
            r2 = r7
        L_0x00ce:
            com.google.android.gms.internal.zzdal.e(r2)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdid r2 = r9.zzlby     // Catch:{ all -> 0x014a }
            r7 = 3
            r2.zzo(r7, r3)     // Catch:{ all -> 0x014a }
        L_0x00d7:
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x00ec }
            r2.<init>()     // Catch:{ IOException -> 0x00ec }
            com.google.android.gms.common.util.zzp.zza(r4, r2, r3)     // Catch:{ IOException -> 0x00ec }
            com.google.android.gms.internal.zzdid r4 = r9.zzlby     // Catch:{ IOException -> 0x00ec }
            byte[] r2 = r2.toByteArray()     // Catch:{ IOException -> 0x00ec }
            r4.zzaa(r2)     // Catch:{ IOException -> 0x00ec }
            r1.close()
            return
        L_0x00ec:
            r2 = move-exception
            java.lang.String r4 = r2.getMessage()     // Catch:{ all -> 0x014a }
            java.lang.String r7 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x014a }
            int r7 = r7.length()     // Catch:{ all -> 0x014a }
            int r7 = r7 + 66
            java.lang.String r8 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x014a }
            int r8 = r8.length()     // Catch:{ all -> 0x014a }
            int r7 = r7 + r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x014a }
            r8.<init>(r7)     // Catch:{ all -> 0x014a }
            java.lang.String r7 = "NetworkLoader: Error when parsing downloaded resources from url: "
            r8.append(r7)     // Catch:{ all -> 0x014a }
            r8.append(r5)     // Catch:{ all -> 0x014a }
            r8.append(r0)     // Catch:{ all -> 0x014a }
            r8.append(r4)     // Catch:{ all -> 0x014a }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdal.zzb(r0, r2)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdid r0 = r9.zzlby     // Catch:{ all -> 0x014a }
            r0.zzo(r6, r3)     // Catch:{ all -> 0x014a }
            r1.close()
            return
        L_0x0127:
            java.lang.String r0 = "NetworkLoader: No data was retrieved from the given url: "
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x014a }
            int r4 = r2.length()     // Catch:{ all -> 0x014a }
            if (r4 == 0) goto L_0x0138
            java.lang.String r0 = r0.concat(r2)     // Catch:{ all -> 0x014a }
            goto L_0x013e
        L_0x0138:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x014a }
            r2.<init>(r0)     // Catch:{ all -> 0x014a }
            r0 = r2
        L_0x013e:
            com.google.android.gms.internal.zzdal.e(r0)     // Catch:{ all -> 0x014a }
            com.google.android.gms.internal.zzdid r0 = r9.zzlby     // Catch:{ all -> 0x014a }
            r0.zzo(r6, r3)     // Catch:{ all -> 0x014a }
            r1.close()
            return
        L_0x014a:
            r0 = move-exception
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdiz.run():void");
    }
}
