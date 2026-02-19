package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdhh extends zzdcr {
    private static final zzdju zzlal = new zzdju(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
    private static final zzdju zzlam = new zzdju(Double.valueOf(2.147483647E9d));

    private static boolean zzg(zzdjq<?> zzdjq) {
        return (zzdjq instanceof zzdju) && !Double.isNaN(((Double) ((zzdju) zzdjq).value()).doubleValue());
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        double d;
        double d2;
        zzbq.checkArgument(true);
        zzdju zzdju = zzdjqArr.length > 0 ? (zzdju) zzdjqArr[0] : zzlal;
        zzdju zzdju2 = zzdjqArr.length > 1 ? (zzdju) zzdjqArr[1] : zzlam;
        if (!zzg(zzdju) || !zzg(zzdju2) || !zzdcq.zzb(zzdju, zzdju2)) {
            d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            d = 2.147483647E9d;
        } else {
            d2 = ((Double) zzdju.value()).doubleValue();
            d = ((Double) zzdju2.value()).doubleValue();
        }
        return new zzdju(Double.valueOf((double) Math.round((Math.random() * (d - d2)) + d2)));
    }
}
