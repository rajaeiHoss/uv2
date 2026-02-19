package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdgn implements zzdcp {
    private final zzdjq<?> zzlaf;

    public zzdgn(zzdjq<?> zzdjq) {
        this.zzlaf = (zzdjq) zzbq.checkNotNull(zzdjq);
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        return this.zzlaf;
    }
}
