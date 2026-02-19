package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdfa extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 2);
        zzdjq<?> zzdjq = zzdjqArr[0];
        zzdjq<?> zzdjq2 = zzdjqArr[1];
        if ((!(zzdjq instanceof zzdjw) || zzdjq == zzdjw.zzlcz || zzdjq == zzdjw.zzlcy) && (!(zzdjq2 instanceof zzdjw) || zzdjq2 == zzdjw.zzlcz || zzdjq2 == zzdjw.zzlcy)) {
            if ((zzdjq instanceof zzdka) || (zzdjq instanceof zzdjx) || (zzdjq instanceof zzdjv)) {
                zzdjq = new zzdkc(zzdcq.zzd(zzdjq));
            }
            if ((zzdjq2 instanceof zzdka) || (zzdjq2 instanceof zzdjx) || (zzdjq2 instanceof zzdjv)) {
                zzdjq2 = new zzdkc(zzdcq.zzd(zzdjq2));
            }
            if (!(zzdjq instanceof zzdkc) && !(zzdjq2 instanceof zzdkc)) {
                return new zzdju(Double.valueOf(zzdcq.zza(zzdjq, zzdjq2)));
            }
            String valueOf = String.valueOf(zzdcq.zzd(zzdjq));
            String valueOf2 = String.valueOf(zzdcq.zzd(zzdjq2));
            return new zzdkc(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        }
        throw new IllegalArgumentException("Illegal InternalType passed to Add.");
    }
}
