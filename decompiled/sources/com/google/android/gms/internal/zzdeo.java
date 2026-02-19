package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdeo implements zzdcp {
    private static zzdav zzkvv;

    public zzdeo(zzdav zzdav) {
        zzkvv = zzdav;
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        return zzkvv.zzmr((String) zzdjqArr[0].value());
    }
}
