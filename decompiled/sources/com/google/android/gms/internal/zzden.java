package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzden implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        zzdjq<?> zzmu = zzdbb.zzmu((String) zzdjqArr[0].value());
        if (zzmu instanceof zzdkb) {
            throw new IllegalStateException("Illegal Statement type encountered in Get.");
        } else if (!(zzmu instanceof zzdjw) || zzmu == zzdjw.zzlcz || zzmu == zzdjw.zzlcy) {
            return zzmu;
        } else {
            throw new IllegalStateException("Illegal InternalType encountered in Get.");
        }
    }
}
