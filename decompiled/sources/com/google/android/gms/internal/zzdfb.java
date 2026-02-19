package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdfb extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzdjq<?>[] zzdjqArr2 = zzdjqArr;
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr2.length == 2);
        double zzb = zzdcq.zzb(zzdjqArr2[0]);
        double zzb2 = zzdcq.zzb(zzdjqArr2[1]);
        if (Double.isNaN(zzb) || Double.isNaN(zzb2)) {
            return new zzdju(Double.valueOf(Double.NaN));
        }
        if (Double.isInfinite(zzb) && Double.isInfinite(zzb2)) {
            return new zzdju(Double.valueOf(Double.NaN));
        }
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        boolean z2 = ((double) Double.compare(zzb, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (((double) Double.compare(zzb2, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            z = false;
        }
        boolean z3 = z2 ^ z;
        double d2 = Double.NEGATIVE_INFINITY;
        if (Double.isInfinite(zzb) && !Double.isInfinite(zzb2)) {
            if (!z3) {
                d2 = Double.POSITIVE_INFINITY;
            }
            return new zzdju(Double.valueOf(d2));
        } else if (Double.isInfinite(zzb) || !Double.isInfinite(zzb2)) {
            int i = (zzb > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (zzb == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
            if (i == 0) {
                if (zzb2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return new zzdju(Double.valueOf(Double.NaN));
                }
                if (z3) {
                    d = -0.0d;
                }
                return new zzdju(Double.valueOf(d));
            } else if (Double.isInfinite(zzb) || i == 0 || zzb2 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return new zzdju(Double.valueOf(zzb / zzb2));
            } else {
                if (!z3) {
                    d2 = Double.POSITIVE_INFINITY;
                }
                return new zzdju(Double.valueOf(d2));
            }
        } else {
            if (z3) {
                d = -0.0d;
            }
            return new zzdju(Double.valueOf(d));
        }
    }
}
