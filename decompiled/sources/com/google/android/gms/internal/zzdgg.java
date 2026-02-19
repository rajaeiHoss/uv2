package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgg implements zzdcp {
    private final zzczb zzlad;

    public zzdgg(Context context) {
        this(zzczb.zzep(context));
    }

    private zzdgg(zzczb zzczb) {
        this.zzlad = zzczb;
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = false;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length == 0) {
            z = true;
        }
        zzbq.checkArgument(z);
        return new zzdjt(Boolean.valueOf(!this.zzlad.isLimitAdTrackingEnabled()));
    }
}
