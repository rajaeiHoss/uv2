package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.List;

public final class zzddg extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length == 2 || zzdjqArr.length == 3);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        zzdjx zzdjx = (zzdjx) zzdjqArr[0];
        int zzc = (int) zzdcq.zzc(zzdjqArr[1]);
        int size = ((List) zzdjx.value()).size();
        int max = zzc < 0 ? Math.max(size + zzc, 0) : Math.min(zzc, size);
        int size2 = ((List) zzdjx.value()).size();
        if (zzdjqArr.length == 3) {
            int zzc2 = (int) zzdcq.zzc(zzdjqArr[2]);
            int size3 = ((List) zzdjx.value()).size();
            size2 = zzc2 < 0 ? Math.max(zzc2 + size3, 0) : Math.min(zzc2, size3);
        }
        return new zzdjx(new ArrayList(((List) zzdjx.value()).subList(max, Math.max(max, size2))));
    }
}
