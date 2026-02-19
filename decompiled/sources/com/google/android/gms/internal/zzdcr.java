package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public abstract class zzdcr implements zzdcp {
    /* access modifiers changed from: protected */
    public abstract zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr);

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdbb != null);
        zzbq.checkArgument(zzdjqArr != null);
        zzdjq[] zzdjqArr2 = new zzdjq[zzdjqArr.length];
        for (int i = 0; i < zzdjqArr.length; i++) {
            zzbq.checkArgument(zzdjqArr[i] != null);
            zzbq.checkArgument(zzdjqArr[i] != zzdjw.zzlcw);
            zzbq.checkArgument(zzdjqArr[i] != zzdjw.zzlcx);
            zzdjqArr2[i] = zzdke.zza(zzdbb, (zzdjq) zzdjqArr[i]);
            zzbq.checkArgument(zzdjqArr2[i] != null);
            zzbq.checkArgument(zzdjqArr2[i] != zzdjw.zzlcw);
            zzbq.checkArgument(zzdjqArr2[i] != zzdjw.zzlcx);
        }
        zzdjq<?> zza = zza(zzdbb, zzdjqArr2);
        if (zza == null) {
            z = false;
        }
        zzbq.checkState(z);
        return zza;
    }
}
