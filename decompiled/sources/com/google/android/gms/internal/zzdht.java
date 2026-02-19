package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public abstract class zzdht extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        try {
            double zzb = zzdcq.zzb(zzdjqArr[0]);
            double zzb2 = zzdcq.zzb(zzdjqArr[1]);
            return (Double.isNaN(zzb) || Double.isNaN(zzb2)) ? new zzdjt(false) : new zzdjt(Boolean.valueOf(zze(zzb, zzb2)));
        } catch (IllegalArgumentException unused) {
            return new zzdjt(false);
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean zze(double d, double d2);
}
