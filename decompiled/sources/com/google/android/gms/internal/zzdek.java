package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.HashMap;

public final class zzdek extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        HashMap hashMap = new HashMap();
        int i = 0;
        while (i < zzdjqArr.length - 1) {
            String zzd = zzdcq.zzd(zzdjqArr[i]);
            zzdjq<?> value = zzdjqArr[i + 1];
            if (!(value instanceof zzdjw) || value == zzdjw.zzlcy || value == zzdjw.zzlcz) {
                hashMap.put(zzd, value);
                i += 2;
            } else {
                throw new IllegalStateException("Illegal InternalType found in CreateObject.");
            }
        }
        return new zzdka(hashMap);
    }
}
