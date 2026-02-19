package com.google.android.gms.internal;

import android.content.Context;

@zzabh
public final class zzagt implements zzgv {
    private final Object mLock;
    private String zzapp;
    private final Context zzbky;
    private boolean zzdbu;

    public zzagt(Context context, String str) {
        this.zzbky = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzapp = str;
        this.zzdbu = false;
        this.mLock = new Object();
    }

    public final void setAdUnitId(String str) {
        this.zzapp = str;
    }

    public final void zza(zzgu zzgu) {
        zzw(zzgu.zzakc);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzw(boolean r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.zzagu r0 = com.google.android.gms.ads.internal.zzbt.zzfh()
            android.content.Context r1 = r3.zzbky
            boolean r0 = r0.zzq(r1)
            if (r0 != 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzdbu     // Catch:{ all -> 0x003f }
            if (r1 != r4) goto L_0x0016
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0016:
            r3.zzdbu = r4     // Catch:{ all -> 0x003f }
            java.lang.String r4 = r3.zzapp     // Catch:{ all -> 0x003f }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x0022:
            boolean r4 = r3.zzdbu     // Catch:{ all -> 0x003f }
            if (r4 == 0) goto L_0x0032
            com.google.android.gms.internal.zzagu r4 = com.google.android.gms.ads.internal.zzbt.zzfh()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.zzbky     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.zzapp     // Catch:{ all -> 0x003f }
            r4.zzb(r1, r2)     // Catch:{ all -> 0x003f }
            goto L_0x003d
        L_0x0032:
            com.google.android.gms.internal.zzagu r4 = com.google.android.gms.ads.internal.zzbt.zzfh()     // Catch:{ all -> 0x003f }
            android.content.Context r1 = r3.zzbky     // Catch:{ all -> 0x003f }
            java.lang.String r2 = r3.zzapp     // Catch:{ all -> 0x003f }
            r4.zzc(r1, r2)     // Catch:{ all -> 0x003f }
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return
        L_0x003f:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzagt.zzw(boolean):void");
    }
}
