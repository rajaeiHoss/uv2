package com.google.android.gms.analytics;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import java.util.ArrayList;
import java.util.List;

public class zzj<T extends zzj> {
    private final zzk zzdvh;
    protected final zzg zzdvi;
    private final List<zzh> zzdvj = new ArrayList();

    protected zzj(zzk zzk, zze zze) {
        zzbq.checkNotNull(zzk);
        this.zzdvh = zzk;
        zzg zzg = new zzg(this, zze);
        zzg.zzwf();
        this.zzdvi = zzg;
    }

    /* access modifiers changed from: protected */
    public void zza(zzg zzg) {
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzg zzg) {
        for (zzh zza : this.zzdvj) {
            zza.zza(this, zzg);
        }
    }

    public zzg zzvs() {
        zzg zzvx = this.zzdvi.zzvx();
        zzd(zzvx);
        return zzvx;
    }

    /* access modifiers changed from: protected */
    public final zzk zzwg() {
        return this.zzdvh;
    }
}
