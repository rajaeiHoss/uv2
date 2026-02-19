package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzddf extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        boolean z = true;
        if (zzdjqArr.length != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjw zzdjwVar = zzdjw.zzlcz;
        List list = (List) zzdjqArr[0].value();
        return !list.isEmpty() ? (zzdjq) list.remove(0) : zzdjwVar;
    }
}
