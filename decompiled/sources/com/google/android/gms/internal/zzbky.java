package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzbky {
    private final zzfgc zzgnx;

    private zzbky(zzfgc zzfgc) {
        this.zzgnx = (zzfgc) zzbq.checkNotNull(zzfgc);
    }

    public static zzbky zzm(int i, int i2) {
        zzbq.checkArgument(true);
        zzfgc zzfgc = new zzfgc();
        zzfgc.zzpkl = 1;
        zzfgc.zzpna = i2;
        return new zzbky(zzfgc);
    }

    public final zzfgc zzapc() {
        return this.zzgnx;
    }
}
