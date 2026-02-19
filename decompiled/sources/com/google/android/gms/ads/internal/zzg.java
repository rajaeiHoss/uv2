package com.google.android.gms.ads.internal;

import java.util.concurrent.Callable;

final class zzg implements Callable<String> {
    private /* synthetic */ zzd zzanx;

    zzg(zzd zzd) {
        this.zzanx = zzd;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r0 = com.google.android.gms.ads.internal.zzbt.zzen().zzau(r2.zzanx.zzanm.zzaiq);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final String call() throws Exception {
        /*
            r2 = this;
            com.google.android.gms.internal.zzny<java.lang.Boolean> r0 = com.google.android.gms.internal.zzoi.zzbtz
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0029
            com.google.android.gms.internal.zzaip r0 = com.google.android.gms.ads.internal.zzbt.zzen()
            com.google.android.gms.ads.internal.zzd r1 = r2.zzanx
            com.google.android.gms.ads.internal.zzbu r1 = r1.zzanm
            android.content.Context r1 = r1.zzaiq
            android.webkit.CookieManager r0 = r0.zzau(r1)
            if (r0 == 0) goto L_0x0029
            java.lang.String r1 = "googleads.g.doubleclick.net"
            java.lang.String r0 = r0.getCookie(r1)
            goto L_0x002b
        L_0x0029:
            java.lang.String r0 = ""
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzg.call():java.lang.Object");
    }
}
