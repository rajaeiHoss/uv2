package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgf implements zzdcp {
    private final zzczb zzlad;

    public zzdgf(Context context) {
        this(zzczb.zzep(context));
    }

    private zzdgf(zzczb zzczb) {
        this.zzlad = zzczb;
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        String zzbfb = this.zzlad.zzbfb();
        return zzbfb == null ? zzdjw.zzlcz : new zzdkc(zzbfb);
    }
}
