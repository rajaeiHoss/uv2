package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public final class zzdev extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 3);
        zzdjq<?> zzdjq = zzdjqArr[0];
        zzdjq<?> zzdjq2 = zzdjqArr[1];
        zzdjq<?> zzdjq3 = zzdjqArr[2];
        zzbq.checkArgument(zzdjq != zzdjw.zzlcy);
        zzbq.checkArgument(zzdjq != zzdjw.zzlcz);
        zzbq.checkArgument(!zzdke.zzm(zzdjq));
        zzbq.checkArgument(!zzdke.zzm(zzdjq2));
        zzbq.checkArgument(!zzdke.zzm(zzdjq3));
        if (zzdke.zzl(zzdjq)) {
            return zzdjq3;
        }
        String zzd = zzdcq.zzd(zzdjq2);
        if (zzdjq instanceof zzdka) {
            zzdka zzdka = (zzdka) zzdjq;
            if (!zzdka.isImmutable()) {
                zzdka.zzc(zzd, zzdjq3);
            }
            return zzdjq3;
        }
        if (zzdjq instanceof zzdjx) {
            zzdjx zzdjx = (zzdjx) zzdjq;
            if ("length".equals(zzd)) {
                double zzb = zzdcq.zzb(zzdjq3);
                if (Double.isInfinite(zzb) || zzb != Math.floor(zzb)) {
                    z = false;
                }
                zzbq.checkArgument(z);
                zzdjx.setSize((int) zzb);
                return zzdjq3;
            }
            double zzb2 = zzdcq.zzb(zzdjq2);
            if (!Double.isInfinite(zzb2) && zzb2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                int i = (int) zzb2;
                if (zzd.equals(Integer.toString(i))) {
                    zzdjx.zza(i, zzdjq3);
                    return zzdjq3;
                }
            }
        }
        zzdjq.zzc(zzd, zzdjq3);
        return zzdjq3;
    }
}
