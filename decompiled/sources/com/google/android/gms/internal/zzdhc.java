package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Locale;

public final class zzdhc implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        return new zzdkc(Locale.getDefault().getCountry());
    }
}
