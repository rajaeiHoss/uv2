package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdfz extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        if (zzdjqArr.length != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        return new zzdjt(Boolean.valueOf(zzdjqArr[0] instanceof zzdjx));
    }
}
