package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzclv implements Callable<String> {
    private /* synthetic */ zzclk zzjpy;

    zzclv(zzclk zzclk) {
        this.zzjpy = zzclk;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final String call() throws java.lang.Exception {
        /*
            r8 = this;
            com.google.android.gms.internal.zzclk r0 = r8.zzjpy
            com.google.android.gms.internal.zzcju r0 = r0.zzayq()
            java.lang.String r0 = r0.zzbbf()
            if (r0 == 0) goto L_0x000d
            return r0
        L_0x000d:
            com.google.android.gms.internal.zzclk r0 = r8.zzjpy
            com.google.android.gms.internal.zzclk r0 = r0.zzayd()
            com.google.android.gms.internal.zzcke r1 = r0.zzayo()
            boolean r1 = r1.zzbbk()
            r2 = 0
            if (r1 == 0) goto L_0x002c
            com.google.android.gms.internal.zzcjj r0 = r0.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()
            java.lang.String r1 = "Cannot retrieve app instance id from analytics worker thread"
        L_0x0028:
            r0.log(r1)
            goto L_0x0066
        L_0x002c:
            r0.zzayo()
            boolean r1 = com.google.android.gms.internal.zzcke.zzas()
            if (r1 == 0) goto L_0x0040
            com.google.android.gms.internal.zzcjj r0 = r0.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()
            java.lang.String r1 = "Cannot retrieve app instance id from main thread"
            goto L_0x0028
        L_0x0040:
            com.google.android.gms.common.util.zze r1 = r0.zzxx()
            long r1 = r1.elapsedRealtime()
            r3 = 120000(0x1d4c0, double:5.9288E-319)
            java.lang.String r5 = r0.zzbd(r3)
            com.google.android.gms.common.util.zze r6 = r0.zzxx()
            long r6 = r6.elapsedRealtime()
            long r6 = r6 - r1
            if (r5 != 0) goto L_0x0065
            int r1 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0065
            long r3 = r3 - r6
            java.lang.String r0 = r0.zzbd(r3)
            r2 = r0
            goto L_0x0066
        L_0x0065:
            r2 = r5
        L_0x0066:
            if (r2 == 0) goto L_0x0072
            com.google.android.gms.internal.zzclk r0 = r8.zzjpy
            com.google.android.gms.internal.zzcju r0 = r0.zzayq()
            r0.zzjx(r2)
            return r2
        L_0x0072:
            java.util.concurrent.TimeoutException r0 = new java.util.concurrent.TimeoutException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzclv.call():java.lang.Object");
    }
}
