package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdec implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(zzdjqArr != null);
        zzbq.checkArgument(zzdjqArr.length == 3);
        zzdjq<?> zza = zzdke.zza(zzdbb, (zzdjq) zzdjqArr[0]);
        zzdjq<?> zzdjq = zzdcq.zza(zza) ? zzdjqArr[1] : zzdjqArr[2];
        zzdjq<?> zza2 = zzdke.zza(zzdbb, (zzdjq) zzdjq);
        if (!(zza2 instanceof zzdjw) || zza2 == zzdjw.zzlcz || zza2 == zzdjw.zzlcy) {
            return zza2;
        }
        throw new IllegalArgumentException("Illegal InternalType passed to Ternary.");
    }
}
