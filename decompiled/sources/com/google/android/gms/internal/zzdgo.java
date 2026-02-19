package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdgo implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        return zzdbb.has("gtm.globals.eventName") ? zzdbb.zzmu("gtm.globals.eventName") : zzdjw.zzlcy;
    }
}
