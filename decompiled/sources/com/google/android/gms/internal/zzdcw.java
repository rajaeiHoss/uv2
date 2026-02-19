package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdcw extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        int i = 0;
        zzbq.checkArgument(zzdjqArr.length > 0 && zzdjqArr.length <= 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        zzdjw zzdjwVar = zzdjqArr.length < 2 ? zzdjw.zzlcz : (zzdjw) zzdjqArr[1];
        zzdjw zzdjwVar2 = zzdjqArr.length < 3 ? zzdjw.zzlcz : (zzdjw) zzdjqArr[2];
        List list = (List) zzdjx.value();
        int size = list.size();
        if (zzdjwVar2 != zzdjw.zzlcz) {
            int zzc = (int) zzdcq.zzc(zzdjwVar2);
            i = zzc < 0 ? Math.max(size - Math.abs(zzc), 0) : zzc;
        }
        int i2 = -1;
        while (true) {
            if (i < size) {
                if (zzdjx.zzfi(i) && zzdcq.zzd(zzdjwVar, (zzdjq) list.get(i))) {
                    i2 = i;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        return new zzdju(Double.valueOf((double) i2));
    }
}
