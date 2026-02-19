package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.zzbs;
import com.google.android.gms.internal.zzdkt;

final class zzes implements Runnable {
    private final Context mContext;
    private final String zzknc;
    private volatile String zzkoa;
    private final zzdkt zzkrz;
    private final String zzksa;
    private zzdi<zzbs> zzksb;
    private volatile zzal zzksc;
    private volatile String zzksd;

    private zzes(Context context, String str, zzdkt zzdkt, zzal zzal) {
        this.mContext = context;
        this.zzkrz = zzdkt;
        this.zzknc = str;
        this.zzksc = zzal;
        String valueOf = String.valueOf(str);
        String concat = valueOf.length() != 0 ? "/r?id=".concat(valueOf) : new String("/r?id=");
        this.zzksa = concat;
        this.zzkoa = concat;
        this.zzksd = null;
    }

    public zzes(Context context, String str, zzal zzal) {
        this(context, str, new zzdkt(), zzal);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:57:0x01e0 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            java.lang.String r0 = " "
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzbs> r1 = r7.zzksb
            if (r1 == 0) goto L_0x0225
            r1.zzbfu()
            android.content.Context r1 = r7.mContext
            java.lang.String r2 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x0022
            boolean r1 = r1.isConnected()
            if (r1 != 0) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r1 = 1
            goto L_0x0028
        L_0x0022:
            java.lang.String r1 = "...no network connectivity"
            com.google.android.gms.tagmanager.zzdj.v(r1)
            r1 = 0
        L_0x0028:
            if (r1 != 0) goto L_0x0032
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzbs> r0 = r7.zzksb
            int r1 = com.google.android.gms.tagmanager.zzda.zzkqn
            r0.zzex(r1)
            return
        L_0x0032:
            java.lang.String r1 = "Start loading resource from network ..."
            com.google.android.gms.tagmanager.zzdj.v(r1)
            com.google.android.gms.tagmanager.zzal r1 = r7.zzksc
            java.lang.String r1 = r1.zzbgb()
            java.lang.String r2 = r7.zzkoa
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r3 = r3 + 12
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = "&v=a65833898"
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            java.lang.String r2 = r7.zzksd
            if (r2 == 0) goto L_0x00a5
            java.lang.String r2 = r7.zzksd
            java.lang.String r2 = r2.trim()
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x00a5
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = r7.zzksd
            java.lang.String r3 = java.lang.String.valueOf(r1)
            int r3 = r3.length()
            int r3 = r3 + 4
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r4 = r4.length()
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            r4.append(r1)
            java.lang.String r1 = "&pv="
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
        L_0x00a5:
            com.google.android.gms.tagmanager.zzei r2 = com.google.android.gms.tagmanager.zzei.zzbhh()
            com.google.android.gms.tagmanager.zzei$zza r2 = r2.zzbhi()
            com.google.android.gms.tagmanager.zzei$zza r3 = com.google.android.gms.tagmanager.zzei.zza.CONTAINER_DEBUG
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00cc
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = "&gtm_debug=x"
            int r3 = r2.length()
            if (r3 == 0) goto L_0x00c6
            java.lang.String r1 = r1.concat(r2)
            goto L_0x00cc
        L_0x00c6:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r1 = r2
        L_0x00cc:
            com.google.android.gms.internal.zzdks r2 = com.google.android.gms.internal.zzdkt.zzblc()
            r3 = 0
            java.io.InputStream r3 = r2.zzne(r1)     // Catch:{ FileNotFoundException -> 0x01e0, zzdku -> 0x0116, IOException -> 0x00d9 }
            goto L_0x0137
        L_0x00d6:
            r0 = move-exception
            goto L_0x0221
        L_0x00d9:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x00d6 }
            java.lang.String r5 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d6 }
            int r5 = r5.length()     // Catch:{ all -> 0x00d6 }
            int r5 = r5 + 40
            java.lang.String r6 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00d6 }
            int r6 = r6.length()     // Catch:{ all -> 0x00d6 }
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d6 }
            r6.<init>(r5)     // Catch:{ all -> 0x00d6 }
            java.lang.String r5 = "Error when loading resources from url: "
            r6.append(r5)     // Catch:{ all -> 0x00d6 }
            r6.append(r1)     // Catch:{ all -> 0x00d6 }
            r6.append(r0)     // Catch:{ all -> 0x00d6 }
            r6.append(r4)     // Catch:{ all -> 0x00d6 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00d6 }
            com.google.android.gms.tagmanager.zzdj.zzc(r0, r3)     // Catch:{ all -> 0x00d6 }
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzbs> r0 = r7.zzksb     // Catch:{ all -> 0x00d6 }
            int r1 = com.google.android.gms.tagmanager.zzda.zzkqo     // Catch:{ all -> 0x00d6 }
            r0.zzex(r1)     // Catch:{ all -> 0x00d6 }
            r2.close()
            return
        L_0x0116:
            java.lang.String r4 = "Error when loading resource for url: "
            java.lang.String r5 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d6 }
            int r6 = r5.length()     // Catch:{ all -> 0x00d6 }
            if (r6 == 0) goto L_0x0127
            java.lang.String r4 = r4.concat(r5)     // Catch:{ all -> 0x00d6 }
            goto L_0x012d
        L_0x0127:
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x00d6 }
            r5.<init>(r4)     // Catch:{ all -> 0x00d6 }
            r4 = r5
        L_0x012d:
            com.google.android.gms.tagmanager.zzdj.zzcz(r4)     // Catch:{ all -> 0x00d6 }
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzbs> r4 = r7.zzksb     // Catch:{ all -> 0x00d6 }
            int r5 = com.google.android.gms.tagmanager.zzda.zzkqq     // Catch:{ all -> 0x00d6 }
            r4.zzex(r5)     // Catch:{ all -> 0x00d6 }
        L_0x0137:
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x01a3 }
            r4.<init>()     // Catch:{ IOException -> 0x01a3 }
            com.google.android.gms.internal.zzdkh.zzb(r3, r4)     // Catch:{ IOException -> 0x01a3 }
            byte[] r3 = r4.toByteArray()     // Catch:{ IOException -> 0x01a3 }
            com.google.android.gms.internal.zzbs r4 = new com.google.android.gms.internal.zzbs     // Catch:{ IOException -> 0x01a3 }
            r4.<init>()     // Catch:{ IOException -> 0x01a3 }
            com.google.android.gms.internal.zzfls r3 = com.google.android.gms.internal.zzfls.zza(r4, r3)     // Catch:{ IOException -> 0x01a3 }
            com.google.android.gms.internal.zzbs r3 = (com.google.android.gms.internal.zzbs) r3     // Catch:{ IOException -> 0x01a3 }
            java.lang.String r4 = java.lang.String.valueOf(r3)     // Catch:{ IOException -> 0x01a3 }
            java.lang.String r5 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x01a3 }
            int r5 = r5.length()     // Catch:{ IOException -> 0x01a3 }
            int r5 = r5 + 43
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01a3 }
            r6.<init>(r5)     // Catch:{ IOException -> 0x01a3 }
            java.lang.String r5 = "Successfully loaded supplemented resource: "
            r6.append(r5)     // Catch:{ IOException -> 0x01a3 }
            r6.append(r4)     // Catch:{ IOException -> 0x01a3 }
            java.lang.String r4 = r6.toString()     // Catch:{ IOException -> 0x01a3 }
            com.google.android.gms.tagmanager.zzdj.v(r4)     // Catch:{ IOException -> 0x01a3 }
            com.google.android.gms.internal.zzbp r4 = r3.zzyi     // Catch:{ IOException -> 0x01a3 }
            if (r4 != 0) goto L_0x0195
            com.google.android.gms.internal.zzbr[] r4 = r3.zzyh     // Catch:{ IOException -> 0x01a3 }
            int r4 = r4.length     // Catch:{ IOException -> 0x01a3 }
            if (r4 != 0) goto L_0x0195
            java.lang.String r4 = "No change for container: "
            java.lang.String r5 = r7.zzknc     // Catch:{ IOException -> 0x01a3 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ IOException -> 0x01a3 }
            int r6 = r5.length()     // Catch:{ IOException -> 0x01a3 }
            if (r6 == 0) goto L_0x018c
            java.lang.String r4 = r4.concat(r5)     // Catch:{ IOException -> 0x01a3 }
            goto L_0x0192
        L_0x018c:
            java.lang.String r5 = new java.lang.String     // Catch:{ IOException -> 0x01a3 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x01a3 }
            r4 = r5
        L_0x0192:
            com.google.android.gms.tagmanager.zzdj.v(r4)     // Catch:{ IOException -> 0x01a3 }
        L_0x0195:
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzbs> r4 = r7.zzksb     // Catch:{ IOException -> 0x01a3 }
            r4.onSuccess(r3)     // Catch:{ IOException -> 0x01a3 }
            r2.close()
            java.lang.String r0 = "Load resource from network finished."
            com.google.android.gms.tagmanager.zzdj.v(r0)
            return
        L_0x01a3:
            r3 = move-exception
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x00d6 }
            java.lang.String r5 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d6 }
            int r5 = r5.length()     // Catch:{ all -> 0x00d6 }
            int r5 = r5 + 51
            java.lang.String r6 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00d6 }
            int r6 = r6.length()     // Catch:{ all -> 0x00d6 }
            int r5 = r5 + r6
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d6 }
            r6.<init>(r5)     // Catch:{ all -> 0x00d6 }
            java.lang.String r5 = "Error when parsing downloaded resources from url: "
            r6.append(r5)     // Catch:{ all -> 0x00d6 }
            r6.append(r1)     // Catch:{ all -> 0x00d6 }
            r6.append(r0)     // Catch:{ all -> 0x00d6 }
            r6.append(r4)     // Catch:{ all -> 0x00d6 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00d6 }
            com.google.android.gms.tagmanager.zzdj.zzc(r0, r3)     // Catch:{ all -> 0x00d6 }
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzbs> r0 = r7.zzksb     // Catch:{ all -> 0x00d6 }
            int r1 = com.google.android.gms.tagmanager.zzda.zzkqp     // Catch:{ all -> 0x00d6 }
            r0.zzex(r1)     // Catch:{ all -> 0x00d6 }
            r2.close()
            return
        L_0x01e0:
            java.lang.String r0 = r7.zzknc     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00d6 }
            int r3 = r3.length()     // Catch:{ all -> 0x00d6 }
            int r3 = r3 + 79
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00d6 }
            int r4 = r4.length()     // Catch:{ all -> 0x00d6 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d6 }
            r4.<init>(r3)     // Catch:{ all -> 0x00d6 }
            java.lang.String r3 = "No data is retrieved from the given url: "
            r4.append(r3)     // Catch:{ all -> 0x00d6 }
            r4.append(r1)     // Catch:{ all -> 0x00d6 }
            java.lang.String r1 = ". Make sure container_id: "
            r4.append(r1)     // Catch:{ all -> 0x00d6 }
            r4.append(r0)     // Catch:{ all -> 0x00d6 }
            java.lang.String r0 = " is correct."
            r4.append(r0)     // Catch:{ all -> 0x00d6 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x00d6 }
            com.google.android.gms.tagmanager.zzdj.zzcz(r0)     // Catch:{ all -> 0x00d6 }
            com.google.android.gms.tagmanager.zzdi<com.google.android.gms.internal.zzbs> r0 = r7.zzksb     // Catch:{ all -> 0x00d6 }
            int r1 = com.google.android.gms.tagmanager.zzda.zzkqp     // Catch:{ all -> 0x00d6 }
            r0.zzex(r1)     // Catch:{ all -> 0x00d6 }
            r2.close()
            return
        L_0x0221:
            r2.close()
            throw r0
        L_0x0225:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "callback must be set before execute"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzes.run():void");
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzdi<zzbs> zzdi) {
        this.zzksb = zzdi;
    }

    /* access modifiers changed from: package-private */
    public final void zzll(String str) {
        if (str == null) {
            str = this.zzksa;
        } else {
            String valueOf = String.valueOf(str);
            zzdj.zzby(valueOf.length() != 0 ? "Setting CTFE URL path: ".concat(valueOf) : new String("Setting CTFE URL path: "));
        }
        this.zzkoa = str;
    }

    /* access modifiers changed from: package-private */
    public final void zzmb(String str) {
        String valueOf = String.valueOf(str);
        zzdj.zzby(valueOf.length() != 0 ? "Setting previous container version: ".concat(valueOf) : new String("Setting previous container version: "));
        this.zzksd = str;
    }
}
