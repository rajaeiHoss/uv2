package com.google.android.gms.internal;

import com.google.android.gms.internal.zzdwp;
import com.google.android.gms.internal.zzdwr;
import java.security.GeneralSecurityException;

public final class zzdtg {
    private zzdwp zzmet;

    private zzdtg(zzdwp zzdwp) {
        this.zzmet = zzdwp;
    }

    static final zzdtg zza(zzdwp zzdwp) throws GeneralSecurityException {
        if (zzdwp != null && zzdwp.zzbrl() > 0) {
            return new zzdtg(zzdwp);
        }
        throw new GeneralSecurityException("empty keyset");
    }

    public final String toString() {
        zzdwp zzdwp = this.zzmet;
        zzdwr.zza zzgs = zzdwr.zzbru().zzgs(zzdwp.zzbrj());
        for (zzdwp.zzb next : zzdwp.zzbrk()) {
            zzgs.zzb((zzdwr.zzb) zzdwr.zzb.zzbrw().zzov(next.zzbro().zzbqu()).zzb(next.zzbrp()).zzb(next.zzbrr()).zzgu(next.zzbrq()).zzczx());
        }
        return ((zzdwr) zzgs.zzczx()).toString();
    }

    /* access modifiers changed from: package-private */
    public final zzdwp zzboe() {
        return this.zzmet;
    }
}
