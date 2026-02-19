package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdfo extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0 && zzdjqArr.length <= 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        int zzc = (int) zzdcq.zzc(zzdjqArr.length < 2 ? zzdjw.zzlcz : zzdjqArr[1]);
        int length = str.length();
        if (zzdjqArr.length == 3 && zzdjqArr[2] != zzdjw.zzlcz) {
            length = (int) zzdcq.zzc(zzdke.zza(zzdbb, (zzdjq) zzdjqArr[2]));
        }
        int min = Math.min(Math.max(zzc, 0), str.length());
        int min2 = Math.min(Math.max(length, 0), str.length());
        return new zzdkc(str.substring(Math.min(min, min2), Math.max(min, min2)));
    }
}
