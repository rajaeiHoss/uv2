package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdim {
    private zzdia zzlbk;

    public final String getId() {
        zzdia zzdia = this.zzlbk;
        return zzdia == null ? "" : zzdia.getContainerId();
    }

    public final zzdim zza(zzdia zzdia) throws IllegalArgumentException {
        zzbq.checkNotNull(zzdia);
        this.zzlbk = zzdia;
        return this;
    }

    public final zzdia zzbjt() {
        return this.zzlbk;
    }
}
