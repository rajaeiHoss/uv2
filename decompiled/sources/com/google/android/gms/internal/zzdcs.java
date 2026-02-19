package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.List;

public final class zzdcs extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length > 0);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdjx);
        ArrayList arrayList = new ArrayList();
        for (Object item : (List) zzdjqArr[0].value()) {
            arrayList.add((zzdjq) item);
        }
        for (int i = 1; i < zzdjqArr.length; i++) {
            if (zzdjqArr[i] instanceof zzdjx) {
                for (Object item2 : (List) zzdjqArr[i].value()) {
                    arrayList.add((zzdjq) item2);
                }
            } else {
                arrayList.add(zzdjqArr[i]);
            }
        }
        return new zzdjx(arrayList);
    }
}
