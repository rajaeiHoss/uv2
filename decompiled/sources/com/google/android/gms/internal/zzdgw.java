package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdgw extends zzdcr {
    private final zzdaz zzlak;

    public zzdgw(zzdaz zzdaz) {
        this.zzlak = zzdaz;
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        return zzdke.zzau(this.zzlak.zzbjc().zzbip());
    }
}
