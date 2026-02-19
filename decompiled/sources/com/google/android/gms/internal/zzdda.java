package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdda extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length == 1);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        List list = (List) zzdjqArr[0].value();
        return !list.isEmpty() ? (zzdjq) list.remove(list.size() - 1) : zzdjw.zzlcz;
    }
}
