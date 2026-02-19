package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzddd extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzdjq<?> zzdjq;
        int i;
        int i2;
        zzdjq<?> zzdjq2;
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
            i = size - 1;
        } else {
            zzbq.checkState(size > 0);
            int i3 = size - 1;
            zzdjq<?> zzfh = zzdjx.zzfh(i3);
            int i4 = size - 2;
            while (true) {
                if (i3 < 0) {
                    zzdjq<?> zzdjq3 = zzfh;
                    i2 = i4;
                    zzdjq2 = zzdjq3;
                    break;
                } else if (zzdjx.zzfi(i3)) {
                    zzdjq2 = zzdjx.zzfh(i3);
                    i2 = i3 - 1;
                    break;
                } else {
                    i3--;
                }
            }
            zzbq.checkState(i3 >= 0);
            zzdjq = zzdjq2;
            i = i2;
        }
        while (i >= 0) {
            if (zzdjx.zzfi(i)) {
                zzdjq = ((zzdcp) zzdjv.value()).zzb(zzdbb, zzdjq, (zzdjq) list.get(i), new zzdju(Double.valueOf((double) i)), zzdjx);
            }
            i--;
        }
        return zzdjq;
    }
}
