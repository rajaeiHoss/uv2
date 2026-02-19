package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Iterator;
import java.util.List;

public final class zzdem extends zzdcr {
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length == 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        zzbq.checkArgument(zzdbb.has(str));
        zzdjq<?> zzdjq = zzdjqArr[1];
        zzbq.checkNotNull(zzdjq);
        zzdjx zzdjx = (zzdjx) zzdjqArr[2];
        zzbq.checkArgument(zzdjx instanceof zzdjx);
        List list = (List) zzdjx.value();
        Iterator<zzdjq<?>> zzbko = zzdjq.zzbko();
        while (zzbko.hasNext()) {
            zzdbb.zzb(str, zzbko.next());
            zzdjw zza = zzdke.zza(zzdbb, (List<zzdjq<?>>) list);
            if (zza == zzdjw.zzlcw) {
                return zzdjw.zzlcz;
            }
            if (zza.zzbkq()) {
                return zza;
            }
        }
        return zzdjw.zzlcz;
    }
}
