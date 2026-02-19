package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzaqv extends zzi<zzaqv> {
    private String zzaqh;
    private int zzdxq;
    private int zzdxr;
    private String zzdxs;
    private String zzdxt;
    private boolean zzdxu;
    private boolean zzdxv;

    public zzaqv() {
        this(false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzaqv(boolean r5) {
        /*
            r4 = this;
            java.util.UUID r5 = java.util.UUID.randomUUID()
            long r0 = r5.getLeastSignificantBits()
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r0 = r0 & r2
            int r1 = (int) r0
            if (r1 == 0) goto L_0x0010
            goto L_0x0023
        L_0x0010:
            long r0 = r5.getMostSignificantBits()
            long r0 = r0 & r2
            int r1 = (int) r0
            if (r1 == 0) goto L_0x0019
            goto L_0x0023
        L_0x0019:
            java.lang.String r5 = "GAv4"
            java.lang.String r0 = "UUID.randomUUID() returned 0."
            android.util.Log.e(r5, r0)
            r1 = 2147483647(0x7fffffff, float:NaN)
        L_0x0023:
            r5 = 0
            r4.<init>(r5, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaqv.<init>(boolean):void");
    }

    private zzaqv(boolean z, int i) {
        if (i != 0) {
            this.zzdxq = i;
            this.zzdxv = false;
            return;
        }
        throw new IllegalArgumentException("Given Integer is zero");
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("screenName", this.zzaqh);
        hashMap.put("interstitial", Boolean.valueOf(this.zzdxu));
        hashMap.put("automatic", Boolean.valueOf(this.zzdxv));
        hashMap.put("screenId", Integer.valueOf(this.zzdxq));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzdxr));
        hashMap.put("referrerScreenName", this.zzdxs);
        hashMap.put("referrerUri", this.zzdxt);
        return zzl(hashMap);
    }

    public final void zzb(zzaqv zzaqv) {
        if (!TextUtils.isEmpty(this.zzaqh)) {
            zzaqv.zzaqh = this.zzaqh;
        }
        int i = this.zzdxq;
        if (i != 0) {
            zzaqv.zzdxq = i;
        }
        int i2 = this.zzdxr;
        if (i2 != 0) {
            zzaqv.zzdxr = i2;
        }
        if (!TextUtils.isEmpty(this.zzdxs)) {
            zzaqv.zzdxs = this.zzdxs;
        }
        if (!TextUtils.isEmpty(this.zzdxt)) {
            String str = this.zzdxt;
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            zzaqv.zzdxt = str;
        }
        boolean z = this.zzdxu;
        if (z) {
            zzaqv.zzdxu = z;
        }
        boolean z2 = this.zzdxv;
        if (z2) {
            zzaqv.zzdxv = z2;
        }
    }

    public final String zzxk() {
        return this.zzaqh;
    }

    public final int zzxl() {
        return this.zzdxq;
    }

    public final String zzxm() {
        return this.zzdxt;
    }
}
