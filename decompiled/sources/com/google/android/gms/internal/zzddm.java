package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.List;

public final class zzddm extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length >= 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        int zzc = (int) zzdcq.zzc(zzdjqArr[1]);
        int size = ((List) zzdjx.value()).size();
        int max = zzc < 0 ? Math.max(size + zzc, 0) : Math.min(zzc, size);
        int min = Math.min(Math.max((int) zzdcq.zzc(zzdjqArr[2]), 0), ((List) zzdjx.value()).size() - max) + max;
        ArrayList arrayList = new ArrayList(((List) zzdjx.value()).subList(max, min));
        ((List) zzdjx.value()).subList(max, min).clear();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 3; i < zzdjqArr.length; i++) {
            arrayList2.add(zzdjqArr[i]);
        }
        ((List) zzdjx.value()).addAll(max, arrayList2);
        return new zzdjx(arrayList);
    }
}
