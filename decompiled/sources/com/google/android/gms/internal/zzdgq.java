package com.google.android.gms.internal;

import android.os.Build;
import com.google.android.gms.common.internal.zzbq;

public final class zzdgq implements zzdcp {
    private final String zzlag = Build.BRAND;

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        return new zzdkc(this.zzlag);
    }
}
