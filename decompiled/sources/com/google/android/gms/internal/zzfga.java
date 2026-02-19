package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfga extends zzflm<zzfga> {
    private int type = 3;
    private long zzhhl = 0;
    private long zzhhm = 0;

    public zzfga() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzba */
    public final zzfga zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 1 || zzcym == 2)) {
                        if (zzcym != 3) {
                            StringBuilder sb = new StringBuilder(40);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TimeType");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.type = zzcym;
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 16) {
                this.zzhhl = zzflj.zzcyr();
            } else if (zzcxx == 24) {
                this.zzhhm = zzflj.zzcyr();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfga)) {
            return false;
        }
        zzfga zzfga = (zzfga) obj;
        if (this.type == zzfga.type && this.zzhhl == zzfga.zzhhl && this.zzhhm == zzfga.zzhhm) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfga.zzpvl == null || zzfga.zzpvl.isEmpty() : this.zzpvl.equals(zzfga.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzhhl;
        long j2 = this.zzhhm;
        return ((((((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.type;
        if (i != 3) {
            zzflk.zzad(1, i);
        }
        long j = this.zzhhl;
        if (j != 0) {
            zzflk.zzf(2, j);
        }
        long j2 = this.zzhhm;
        if (j2 != 0) {
            zzflk.zzf(3, j2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.type;
        if (i != 3) {
            zzq += zzflk.zzag(1, i);
        }
        long j = this.zzhhl;
        if (j != 0) {
            zzq += zzflk.zzc(2, j);
        }
        long j2 = this.zzhhm;
        return j2 != 0 ? zzq + zzflk.zzc(3, j2) : zzq;
    }
}
