package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

public final class zzdet implements zzdcp {
    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(zzdjqArr != null);
        ArrayList arrayList = new ArrayList(zzdjqArr.length);
        for (zzdjq<?> add : zzdjqArr) {
            arrayList.add(add);
        }
        return new zzdjx(arrayList);
    }
}
