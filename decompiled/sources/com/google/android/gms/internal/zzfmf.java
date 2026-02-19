package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmf extends zzflm<zzfmf> {
    public String zzpxp = null;
    public Long zzpxq = null;
    public Boolean zzpxr = null;

    public zzfmf() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzpxp = zzflj.readString();
            } else if (zzcxx == 16) {
                this.zzpxq = Long.valueOf(zzflj.zzcxz());
            } else if (zzcxx == 24) {
                this.zzpxr = Boolean.valueOf(zzflj.zzcyd());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzpxp;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        Long l = this.zzpxq;
        if (l != null) {
            zzflk.zzf(2, l.longValue());
        }
        Boolean bool = this.zzpxr;
        if (bool != null) {
            zzflk.zzl(3, bool.booleanValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzpxp;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        Long l = this.zzpxq;
        if (l != null) {
            zzq += zzflk.zzc(2, l.longValue());
        }
        Boolean bool = this.zzpxr;
        if (bool == null) {
            return zzq;
        }
        bool.booleanValue();
        return zzq + zzflk.zzlw(3) + 1;
    }
}
