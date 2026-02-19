package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdeh extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        if (zzdjqArr.length != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        for (Object value : (List) zzdjqArr[0].value()) {
            zzdjq zza = (zzdjq) value;
            zzdjq<?> zza2 = zzdke.zza(zzdbb, zza);
            if ((zza2 instanceof zzdjw) && (zza2 == zzdjw.zzlcw || zza2 == zzdjw.zzlcx || ((zzdjw) zza2).zzbkq())) {
                return zza2;
            }
        }
        return zzdjw.zzlcz;
    }
}
