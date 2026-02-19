package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdfh extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2 || zzdjqArr.length == 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        return new zzdju(Double.valueOf((double) str.indexOf(zzdcq.zzd(zzdjqArr[1]), (int) Math.min(Math.max(zzdjqArr.length < 3 ? 0.0d : zzdcq.zzc(zzdjqArr[2]), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE), (double) str.length()))));
    }
}
