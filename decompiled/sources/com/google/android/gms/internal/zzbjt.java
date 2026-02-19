package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzbjt {
    private final zzffe zzgmx;

    private zzbjt(zzffe zzffe) {
        this.zzgmx = (zzffe) zzbq.checkNotNull(zzffe);
    }

    public static zzbjt zza(int i, int[] iArr) {
        zzbq.checkArgument(iArr != null && iArr.length > 0);
        zzffe zzffe = new zzffe();
        zzffe.zzpkl = i;
        zzffe.zzpkm = 3000;
        zzffe.zzpkn = iArr;
        return new zzbjt(zzffe);
    }

    public final zzffe zzaou() {
        return this.zzgmx;
    }
}
