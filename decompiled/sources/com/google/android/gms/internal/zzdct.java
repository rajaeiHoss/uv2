package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdct extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzbq.checkArgument(zzdjqArr[1] instanceof zzdjv);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        zzdjv zzdjv = (zzdjv) zzdjqArr[1];
        List list = (List) zzdjx.value();
        int size = list.size();
        boolean z = true;
        int i = 0;
        while (z && i < size && i < ((List) zzdjx.value()).size()) {
            if (zzdjx.zzfi(i)) {
                z &= zzdcq.zza(((zzdcp) zzdjv.value()).zzb(zzdbb, (zzdjq) list.get(i), new zzdju(Double.valueOf((double) i)), zzdjx));
            }
            i++;
        }
        return new zzdjt(Boolean.valueOf(z));
    }
}
