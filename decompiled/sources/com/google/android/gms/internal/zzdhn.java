package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdhn extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        return new zzdjt(Boolean.valueOf(zzdcq.zzd(zzdjqArr[0]).endsWith(zzdcq.zzd(zzdjqArr[1]))));
    }
}
