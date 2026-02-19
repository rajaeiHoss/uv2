package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzbkw {
    private final zzffz zzgnv;

    private zzbkw(zzffz zzffz) {
        this.zzgnv = (zzffz) zzbq.checkNotNull(zzffz);
    }

    private static zzffz zza(int i, long j, long j2) {
        boolean z = true;
        zzbq.checkArgument(j2 > j);
        zzbq.checkArgument(Math.abs(j) <= 86400000);
        if (Math.abs(j2) > 86400000) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzffz zzffz = new zzffz();
        zzffz.zzpkl = i;
        zzffz.zzpmu = j;
        zzffz.zzpmv = j2;
        return zzffz;
    }

    public static zzbkw zzc(long j, long j2) {
        return new zzbkw(zza(1, j, j2));
    }

    public static zzbkw zzd(long j, long j2) {
        return new zzbkw(zza(2, j, j2));
    }

    public final zzffz zzapa() {
        return this.zzgnv;
    }
}
