package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.List;

public final class zzddi extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzdjv zzdjv;
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length == 1 || zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        if (zzdjqArr.length == 2) {
            zzbq.checkArgument(zzdjqArr[1] instanceof zzdjv);
            zzdjv = (zzdjv) zzdjqArr[1];
        } else {
            zzdjv = new zzdjv(new zzddl());
        }
        Collections.sort((List) zzdjx.value(), new zzddk(this, zzdjv, zzdbb));
        return zzdjqArr[0];
    }
}
