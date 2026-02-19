package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdfm extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0 && zzdjqArr.length <= 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        double zzc = zzdjqArr.length < 2 ? 0.0d : zzdcq.zzc(zzdjqArr[1]);
        double length = (double) str.length();
        if (zzdjqArr.length == 3 && zzdjqArr[2] != zzdjw.zzlcz) {
            length = zzdcq.zzc(zzdjqArr[2]);
        }
        int max = (int) (zzc < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.max(((double) str.length()) + zzc, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : Math.min(zzc, (double) str.length()));
        return new zzdkc(str.substring(max, Math.max(0, ((int) (length < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.max(((double) str.length()) + length, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) : Math.min(length, (double) str.length()))) - max) + max));
    }
}
