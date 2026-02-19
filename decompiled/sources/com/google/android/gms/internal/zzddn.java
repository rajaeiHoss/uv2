package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.List;

public final class zzddn extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length > 0);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < zzdjqArr.length; i++) {
            arrayList.add(zzdjqArr[i]);
        }
        ((List) zzdjx.value()).addAll(0, arrayList);
        return new zzdju(Double.valueOf((double) ((List) zzdjx.value()).size()));
    }
}
