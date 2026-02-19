package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdfg extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        StringBuilder sb = new StringBuilder((String) zzdjqArr[0].value());
        for (int i = 1; i < zzdjqArr.length; i++) {
            sb.append(zzdcq.zzd(zzdjqArr[i]));
        }
        return new zzdkc(sb.toString());
    }
}
