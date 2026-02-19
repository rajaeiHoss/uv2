package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzddo implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(zzdjqArr != null);
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzdjq<?> zza = zzdke.zza(zzdbb, (zzdjq) zzdjqArr[0]);
        return !zzdcq.zza(zza) ? zza : zzdke.zza(zzdbb, (zzdjq) zzdjqArr[1]);
    }
}
