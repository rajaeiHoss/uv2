package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdey implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length <= 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        for (zzdjq<?> zzdjq : zzdjqArr) {
            zzbq.checkNotNull(zzdjq);
            zzbq.checkArgument(zzdjq instanceof zzdkc);
            zzdkc zzdkc = (zzdkc) zzdjq;
            zzdbb.zza((String) zzdkc.value(), zzdjw.zzlcz);
        }
        return zzdjw.zzlcz;
    }
}
