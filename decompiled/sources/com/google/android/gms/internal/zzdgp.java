package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public final class zzdgp implements zzdcp {
    private zze zzata = zzi.zzanq();

    public final void zza(zze zze) {
        this.zzata = (zze) zzbq.checkNotNull(zze);
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        return new zzdju(Double.valueOf((double) this.zzata.currentTimeMillis()));
    }
}
