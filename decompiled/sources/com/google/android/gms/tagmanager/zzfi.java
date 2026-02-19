package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbt;

final class zzfi {
    private zzea<zzbt> zzkta;
    private zzbt zzktb;

    public zzfi(zzea<zzbt> zzea, zzbt zzbt) {
        this.zzkta = zzea;
        this.zzktb = zzbt;
    }

    public final int getSize() {
        int zzdcr = this.zzkta.getObject().zzdcr();
        zzbt zzbt = this.zzktb;
        return zzdcr + (zzbt == null ? 0 : zzbt.zzdcr());
    }

    public final zzea<zzbt> zzbhq() {
        return this.zzkta;
    }

    public final zzbt zzbhr() {
        return this.zzktb;
    }
}
