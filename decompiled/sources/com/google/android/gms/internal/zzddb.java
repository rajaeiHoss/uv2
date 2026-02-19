package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzddb extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length > 0);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        int size = ((List) zzdjx.value()).size();
        zzdjx.setSize((zzdjqArr.length + size) - 1);
        for (int i = 1; i < zzdjqArr.length; i++) {
            zzdjx.zza(size, zzdjqArr[i]);
            size++;
        }
        return new zzdju(Double.valueOf((double) size));
    }
}
