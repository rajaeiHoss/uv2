package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbc extends zzflm<zzbc> {
    private Long zzeo = null;
    private Long zzep = null;
    public Long zzgc = null;
    public Long zzgd = null;
    public Long zzge = null;

    public zzbc() {
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzeo = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 16) {
                this.zzep = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 24) {
                this.zzgc = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 32) {
                this.zzgd = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 40) {
                this.zzge = Long.valueOf(zzflj.zzcyr());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Long l = this.zzeo;
        if (l != null) {
            zzflk.zzf(1, l.longValue());
        }
        Long l2 = this.zzep;
        if (l2 != null) {
            zzflk.zzf(2, l2.longValue());
        }
        Long l3 = this.zzgc;
        if (l3 != null) {
            zzflk.zzf(3, l3.longValue());
        }
        Long l4 = this.zzgd;
        if (l4 != null) {
            zzflk.zzf(4, l4.longValue());
        }
        Long l5 = this.zzge;
        if (l5 != null) {
            zzflk.zzf(5, l5.longValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Long l = this.zzeo;
        if (l != null) {
            zzq += zzflk.zzc(1, l.longValue());
        }
        Long l2 = this.zzep;
        if (l2 != null) {
            zzq += zzflk.zzc(2, l2.longValue());
        }
        Long l3 = this.zzgc;
        if (l3 != null) {
            zzq += zzflk.zzc(3, l3.longValue());
        }
        Long l4 = this.zzgd;
        if (l4 != null) {
            zzq += zzflk.zzc(4, l4.longValue());
        }
        Long l5 = this.zzge;
        return l5 != null ? zzq + zzflk.zzc(5, l5.longValue()) : zzq;
    }
}
