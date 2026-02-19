package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdez implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(zzdjqArr != null);
        zzbq.checkArgument(zzdjqArr.length == 4);
        zzdjq zza = zzdke.zza(zzdbb, (zzdjq) zzdjqArr[3]);
        zzbq.checkArgument(zza instanceof zzdjx);
        List list = (List) ((zzdjx) zza).value();
        zzdjt zzdjt = (zzdjt) zzdjqArr[2];
        zzbq.checkArgument(zzdjt instanceof zzdjt);
        if (((Boolean) zzdjt.value()).booleanValue()) {
            zzdjw zza2 = zzdke.zza(zzdbb, (List<zzdjq<?>>) list);
            if (zza2 == zzdjw.zzlcw) {
                return zzdjw.zzlcz;
            }
            if (zza2.zzbkq()) {
                return zza2;
            }
        }
        while (zzdcq.zza(zzdke.zza(zzdbb, (zzdjq) zzdjqArr[0]))) {
            zzdjw zza3 = zzdke.zza(zzdbb, (List<zzdjq<?>>) list);
            if (zza3 == zzdjw.zzlcw) {
                return zzdjw.zzlcz;
            }
            if (zza3.zzbkq()) {
                return zza3;
            }
            zzdke.zza(zzdbb, (zzdjq) zzdjqArr[1]);
        }
        return zzdjw.zzlcz;
    }
}
