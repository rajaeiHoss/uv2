package com.google.android.gms.internal;

import com.google.android.gms.awareness.state.BeaconState;
import com.google.android.gms.common.internal.zzbq;

public final class zzbjy {
    private final zzffg zzgnb;

    private zzbjy(zzffg zzffg) {
        this.zzgnb = (zzffg) zzbq.checkNotNull(zzffg);
    }

    public static zzbjy zza(BeaconState.TypeFilter[] typeFilterArr) {
        return new zzbjy(zza(1, typeFilterArr, 3000));
    }

    private static zzffg zza(int i, BeaconState.TypeFilter[] typeFilterArr, long j) {
        zzffg zzffg = new zzffg();
        zzffg.zzpkl = i;
        zzffg.zzpko = new zzffh[typeFilterArr.length];
        zzffg.zzpkm = 3000;
        for (int i2 = 0; i2 < typeFilterArr.length; i2++) {
            zzffg.zzpko[i2] = ((zzayq) typeFilterArr[i2]).zzadf();
        }
        return zzffg;
    }

    public static zzbjy zzb(BeaconState.TypeFilter[] typeFilterArr) {
        return new zzbjy(zza(2, typeFilterArr, 3000));
    }

    public static zzbjy zzc(BeaconState.TypeFilter[] typeFilterArr) {
        return new zzbjy(zza(3, typeFilterArr, 3000));
    }

    public final zzffg zzaoy() {
        return this.zzgnb;
    }
}
