package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdff extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        int i = 0;
        zzbq.checkArgument(zzdjqArr.length == 1 || zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        if (zzdjqArr.length == 2) {
            i = (int) zzdcq.zzc(zzdjqArr[1]);
        }
        return (i < 0 || i >= str.length()) ? new zzdkc("") : new zzdkc(String.valueOf(str.charAt(i)));
    }
}
