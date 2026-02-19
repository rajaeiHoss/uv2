package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.HashMap;
import java.util.Map;

public final class zzdga extends zzdcr {
    private final zzdgb zzlac;

    public zzdga(zzdgb zzdgb) {
        this.zzlac = zzdgb;
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkNotNull(zzdjqArr);
        zzbq.checkArgument(zzdjqArr.length > 0);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        String str = (String) zzdjqArr[0].value();
        HashMap hashMap = new HashMap();
        if (zzdjqArr.length >= 2 && zzdjqArr[1] != zzdjw.zzlcz) {
            zzbq.checkArgument(zzdjqArr[1] instanceof zzdka);
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) zzdjqArr[1].value()).entrySet()) {
                zzbq.checkState(!(entry.getValue() instanceof zzdkb));
                zzbq.checkState(!zzdke.zzm((zzdjq) entry.getValue()));
                hashMap.put((String) entry.getKey(), ((zzdjq) entry.getValue()).value());
            }
        }
        return zzdke.zzau(this.zzlac.zze(str, hashMap));
    }
}
