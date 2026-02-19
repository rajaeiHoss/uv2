package com.google.android.gms.internal;

import android.os.Build;
import com.google.android.gms.common.internal.zzbq;

public final class zzdhj implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        int i = Build.VERSION.SDK_INT;
        StringBuilder sb = new StringBuilder(16);
        sb.append("5.06-");
        sb.append(i);
        return new zzdkc(sb.toString());
    }
}
