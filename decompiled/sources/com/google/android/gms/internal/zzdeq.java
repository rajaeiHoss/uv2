package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdeq extends zzdcr {
    public static final zzdeq zzlab = new zzdeq();

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzdjq<?> zzdjq = zzdjqArr[0];
        zzdjq<?> zzdjq2 = zzdjqArr[1];
        zzbq.checkArgument(!zzdke.zzm(zzdjq));
        zzbq.checkArgument(true ^ zzdke.zzm(zzdjq2));
        String zzd = zzdcq.zzd(zzdjq2);
        if (zzdjq instanceof zzdjx) {
            zzdjx zzdjx = (zzdjx) zzdjq;
            if ("length".equals(zzd)) {
                return new zzdjt(true);
            }
            double zzb = zzdcq.zzb(zzdjq2);
            if (zzb == Math.floor(zzb)) {
                int i = (int) zzb;
                if (zzd.equals(Integer.toString(i)) && i >= 0 && i < ((List) zzdjx.value()).size()) {
                    return new zzdjt(true);
                }
            }
        } else if (zzdjq instanceof zzdkc) {
            zzdkc zzdkc = (zzdkc) zzdjq;
            if ("length".equals(zzd)) {
                return new zzdjt(true);
            }
            double zzb2 = zzdcq.zzb(zzdjq2);
            if (zzb2 == Math.floor(zzb2)) {
                int i2 = (int) zzb2;
                if (zzd.equals(Integer.toString(i2)) && i2 >= 0 && i2 < ((String) zzdkc.value()).length()) {
                    return new zzdjt(true);
                }
            }
            return new zzdjt(false);
        }
        return new zzdjt(Boolean.valueOf(zzdjq.zzni(zzd)));
    }
}
