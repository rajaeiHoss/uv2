package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdfc extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        double zzb = zzdcq.zzb(zzdjqArr[0]);
        double zzb2 = zzdcq.zzb(zzdjqArr[1]);
        if (Double.isNaN(zzb) || Double.isNaN(zzb2) || Double.isInfinite(zzb) || zzb2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return new zzdju(Double.valueOf(Double.NaN));
        }
        return new zzdju(Double.valueOf(zzb % zzb2));
    }
}
