package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdew extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 3);
        zzbq.checkArgument(zzdjqArr[1] instanceof zzdjx);
        zzbq.checkArgument(zzdjqArr[2] instanceof zzdjx);
        zzdjq<?> zzdjq = zzdjqArr[0];
        List list = (List) zzdjqArr[1].value();
        List list2 = (List) zzdjqArr[2].value();
        zzbq.checkArgument(list2.size() <= list.size() + 1);
        boolean z = false;
        for (int i = 0; i < list.size(); i++) {
            if (z || zzdcq.zzd(zzdjq, zzdke.zza(zzdbb, (zzdjq) list.get(i)))) {
                zzdjq<?> zza = zzdke.zza(zzdbb, (zzdjq) list2.get(i));
                if (!(zza instanceof zzdjw)) {
                    z = true;
                } else if (zza == zzdjw.zzlcx || ((zzdjw) zza).zzbkq()) {
                    return zza;
                } else {
                    if (zza == zzdjw.zzlcw) {
                        return zzdjw.zzlcz;
                    }
                }
            }
        }
        if (list.size() < list2.size()) {
            zzdjq<?> zza2 = zzdke.zza(zzdbb, (zzdjq) list2.get(list2.size() - 1));
            if ((zza2 instanceof zzdjw) && (zza2 == zzdjw.zzlcx || ((zzdjw) zza2).zzbkq())) {
                return zza2;
            }
        }
        return zzdjw.zzlcz;
    }
}
