package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.List;

public final class zzdde extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        boolean z = true;
        if (zzdjqArr.length != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        Collections.reverse((List) zzdjqArr[0].value());
        return zzdjqArr[0];
    }
}
