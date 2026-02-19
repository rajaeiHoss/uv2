package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzddr extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzdjq<?> zzdjq = zzdjqArr[0];
        zzdjq<?> zzdjq2 = zzdjqArr[1];
        if ((zzdjq instanceof zzdka) || (zzdjq instanceof zzdjx) || (zzdjq instanceof zzdjv)) {
            zzdjq = new zzdkc(zzdcq.zzd(zzdjq));
        }
        if ((zzdjq2 instanceof zzdka) || (zzdjq2 instanceof zzdjx) || (zzdjq2 instanceof zzdjv)) {
            zzdjq2 = new zzdkc(zzdcq.zzd(zzdjq2));
        }
        if (((!(zzdjq instanceof zzdkc) || !(zzdjq2 instanceof zzdkc)) && (Double.isNaN(zzdcq.zzb(zzdjq)) || Double.isNaN(zzdcq.zzb(zzdjq2)))) || zzdcq.zzb(zzdjq, zzdjq2)) {
            z = false;
        }
        return new zzdjt(Boolean.valueOf(z));
    }
}
