package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdef extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        if (zzdbb.has(str)) {
            zzdbb.zzb(str, zzdjqArr[1]);
            return zzdjqArr[1];
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() != 0 ? "Attempting to assign to undefined variable ".concat(valueOf) : new String("Attempting to assign to undefined variable "));
    }
}
