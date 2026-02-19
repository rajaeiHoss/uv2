package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzddv extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzdjq<?> zzdjq = zzdjqArr[0];
        zzdjq<?> zzdjq2 = zzdjqArr[1];
        zzdkc zzdkc;
        zzdkc zzdkc2;
        if ((zzdjq instanceof zzdka) || (zzdjq instanceof zzdjx) || (zzdjq instanceof zzdjv)) {
            zzdkc = new zzdkc(zzdcq.zzd(zzdjq));
        } else {
            zzdkc = (zzdkc) zzdjq;
        }
        if ((zzdjq2 instanceof zzdka) || (zzdjq2 instanceof zzdjx) || (zzdjq2 instanceof zzdjv)) {
            zzdkc2 = new zzdkc(zzdcq.zzd(zzdjq2));
        } else {
            zzdkc2 = (zzdkc) zzdjq2;
        }
        if (((!(zzdkc instanceof zzdkc) || !(zzdkc2 instanceof zzdkc)) && (Double.isNaN(zzdcq.zzb(zzdkc)) || Double.isNaN(zzdcq.zzb(zzdkc2)))) || zzdcq.zzb(zzdkc2, zzdkc)) {
            z = false;
        }
        return new zzdjt(Boolean.valueOf(z));
    }
}
