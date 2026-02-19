package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

public final class zzdej extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        ArrayList arrayList = new ArrayList();
        int length = zzdjqArr.length;
        for (int i = 0; i < length; i++) {
            zzdjq<?> zzdjq = zzdjqArr[i];
            zzbq.checkArgument(!(zzdjq instanceof zzdjw) || zzdjq == zzdjw.zzlcz || zzdjq == zzdjw.zzlcy);
            arrayList.add(zzdjq);
        }
        return new zzdjx(arrayList);
    }
}
