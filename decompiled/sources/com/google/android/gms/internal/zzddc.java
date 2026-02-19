package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzddc extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzdjq<?> zzdjq;
        int i;
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length == 2 || zzdjqArr.length == 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzbq.checkArgument(zzdjqArr[1] instanceof zzdjv);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        zzdjv zzdjv = (zzdjv) zzdjqArr[1];
        List list = (List) zzdjx.value();
        int size = list.size();
        if (zzdjqArr.length == 3) {
            zzdjq = zzdjqArr[2];
            i = 0;
        } else {
            zzbq.checkState(size > 0);
            zzdjq = zzdjx.zzfh(0);
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i = 1;
                    break;
                } else if (zzdjx.zzfi(i2)) {
                    zzdjq = zzdjx.zzfh(i2);
                    i = i2 + 1;
                    break;
                } else {
                    i2++;
                }
            }
            zzbq.checkState(i2 < size);
        }
        while (i < size && i < ((List) zzdjx.value()).size()) {
            if (zzdjx.zzfi(i)) {
                zzdjq = ((zzdcp) zzdjv.value()).zzb(zzdbb, zzdjq, (zzdjq) list.get(i), new zzdju(Double.valueOf((double) i)), zzdjx);
            }
            i++;
        }
        return zzdjq;
    }
}
