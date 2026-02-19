package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdcy extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length > 0 && zzdjqArr.length <= 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        zzdjq<?> zzdjq = zzdjqArr.length < 2 ? zzdjw.zzlcz : zzdjqArr[1];
        List list = (List) zzdjx.value();
        int size = list.size();
        int i = size - 1;
        if (zzdjqArr.length == 3) {
            int zzc = (int) zzdcq.zzc(zzdjqArr[2]);
            i = zzc < 0 ? size - Math.abs(zzc) : Math.min(zzc, i);
        }
        while (true) {
            if (i >= 0) {
                if (zzdjx.zzfi(i) && zzdcq.zzd(zzdjq, (zzdjq) list.get(i))) {
                    break;
                }
                i--;
            } else {
                i = -1;
                break;
            }
        }
        return new zzdju(Double.valueOf((double) i));
    }
}
