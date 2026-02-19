package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdfi extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2 || zzdjqArr.length == 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        String zzd = zzdcq.zzd(zzdjqArr[1]);
        double d = Double.POSITIVE_INFINITY;
        if (zzdjqArr.length == 3 && !Double.isNaN(zzdcq.zzb(zzdjqArr[2]))) {
            d = zzdcq.zzc(zzdjqArr[2]);
        }
        return new zzdju(Double.valueOf((double) str.lastIndexOf(zzd, (int) Math.min(Math.max(d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), (double) str.length()))));
    }
}
