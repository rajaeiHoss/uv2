package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.Comparator;

final class zzddk implements Comparator<zzdjq<?>> {
    private /* synthetic */ zzdjv zzkzz;
    private /* synthetic */ zzdbb zzlaa;

    zzddk(zzddi zzddi, zzdjv zzdjv, zzdbb zzdbb) {
        this.zzkzz = zzdjv;
        this.zzlaa = zzdbb;
    }

    public final int compare(zzdjq<?> zzdjq, zzdjq<?> zzdjq2) {
        if (zzdjq == null) {
            return zzdjq2 != null ? 1 : 0;
        }
        if (zzdjq2 == null) {
            return zzdjq != null ? -1 : 0;
        }
        zzdjq<?> zzb = ((zzdcp) this.zzkzz.value()).zzb(this.zzlaa, zzdjq, zzdjq2);
        zzbq.checkState(zzb instanceof zzdju);
        return (int) ((Double) ((zzdju) zzb).value()).doubleValue();
    }
}
