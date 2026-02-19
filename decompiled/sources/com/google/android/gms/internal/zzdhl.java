package com.google.android.gms.internal;

import android.os.Build;
import com.google.android.gms.common.internal.zzbq;

public final class zzdhl implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        return new zzdju(Double.valueOf((double) Build.VERSION.SDK_INT));
    }
}
