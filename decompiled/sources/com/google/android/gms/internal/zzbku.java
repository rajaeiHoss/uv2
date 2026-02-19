package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzbku {
    private final zzffq zzgnu;

    private zzbku(zzffq zzffq) {
        this.zzgnu = (zzffq) zzbq.checkNotNull(zzffq);
    }

    public static zzbku zza(int i, int i2, double d) {
        zzbq.checkArgument(d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        return new zzbku(zza(2, 1, i, i2, d, d, 3000, 0));
    }

    public static zzbku zza(int i, int i2, double d, long j) {
        zzbq.checkArgument(d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        return new zzbku(zza(1, 1, i, i2, d, d, 0, j));
    }

    private static zzffq zza(int i, int i2, int i3, int i4, double d, double d2, long j, long j2) {
        zzffq zzffq = new zzffq();
        zzffq.zzpkl = i;
        zzffq.zzpmd = 1;
        zzffq.zzpkm = j;
        zzffq.zzpmi = j2;
        zzffq.zzpme = i3;
        zzffq.zzpmf = i4;
        zzffq.zzpmg = d;
        zzffq.zzpmh = d2;
        return zzffq;
    }

    public static zzbku zzb(int i, int i2, double d) {
        zzbq.checkArgument(d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        return new zzbku(zza(3, 1, i, i2, d, d, 3000, 0));
    }

    public final zzffq zzaoz() {
        return this.zzgnu;
    }
}
