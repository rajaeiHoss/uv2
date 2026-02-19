package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.List;

public final class zzdep extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzdjq<?> zzfh;
        zzbq.checkArgument(true);
        boolean z = false;
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzdjq<?> zzdjq0 = zzdjqArr[0];
        zzdjq<?> zzdjq = zzdjqArr[1];
        boolean z2 = zzdjq0 instanceof zzdkc;
        if (z2 || !zzdke.zzl(zzdjq0)) {
            z = true;
        }
        zzbq.checkArgument(z);
        zzbq.checkArgument(!zzdke.zzm(zzdjq0));
        zzbq.checkArgument(true ^ zzdke.zzm(zzdjq));
        String zzd = zzdcq.zzd(zzdjq);
        if (zzdjq0 instanceof zzdjx) {
            zzdjx zzdjx2 = (zzdjx) zzdjq0;
            if ("length".equals(zzd)) {
                return new zzdju(Double.valueOf((double) ((List) zzdjx2.value()).size()));
            }
            double zzb = zzdcq.zzb(zzdjq);
            if (zzb == Math.floor(zzb)) {
                int i = (int) zzb;
                if (zzd.equals(Integer.toString(i)) && (zzfh = zzdjx2.zzfh(i)) != zzdjw.zzlcz) {
                    return zzfh;
                }
            }
        } else if (z2) {
            zzdkc zzdkc = (zzdkc) zzdjq0;
            if ("length".equals(zzd)) {
                return new zzdju(Double.valueOf((double) ((String) zzdkc.value()).length()));
            }
            double zzb2 = zzdcq.zzb(zzdjq);
            if (zzb2 == Math.floor(zzb2)) {
                int i2 = (int) zzb2;
                if (zzd.equals(Integer.toString(i2))) {
                    return zzdkc.zzfj(i2);
                }
            }
            return zzdjw.zzlcz;
        }
        return zzdjq0.zznj(zzd);
    }
}
