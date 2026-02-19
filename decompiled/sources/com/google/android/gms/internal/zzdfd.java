package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdfd extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        double zzb = zzdcq.zzb(zzdjqArr[0]);
        double zzb2 = zzdcq.zzb(zzdjqArr[1]);
        if (Double.isNaN(zzb) || Double.isNaN(zzb2)) {
            return new zzdju(Double.valueOf(Double.NaN));
        }
        if ((Double.isInfinite(zzb) && zzb2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) || (zzb == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && Double.isInfinite(zzb2))) {
            return new zzdju(Double.valueOf(Double.NaN));
        }
        if (!Double.isInfinite(zzb) && !Double.isInfinite(zzb2)) {
            return new zzdju(Double.valueOf(zzb * zzb2));
        }
        boolean z2 = ((double) Double.compare(zzb, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (((double) Double.compare(zzb2, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            z = false;
        }
        return new zzdju(Double.valueOf(z ^ z2 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY));
    }
}
