package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffx extends zzflm<zzffx> {
    private int zzpkl = 0;
    private long zzpkm = 0;
    private int zzpmt = 0;

    public zzffx() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzay */
    public final zzffx zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                i = zzflj.getPosition();
                int zzcym = zzflj.zzcym();
                if (!(zzcym == 0 || zzcym == 1 || zzcym == 2)) {
                    if (zzcym != 3) {
                        StringBuilder sb = new StringBuilder(43);
                        sb.append(zzcym);
                        sb.append(" is not a valid enum TriggerType");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                this.zzpkl = zzcym;
            } else if (zzcxx == 16) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 24) {
                i = zzflj.getPosition();
                try {
                    this.zzpmt = zzfgi.zzlc(zzflj.zzcym());
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(i);
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffx)) {
            return false;
        }
        zzffx zzffx = (zzffx) obj;
        if (this.zzpkl == zzffx.zzpkl && this.zzpkm == zzffx.zzpkm && this.zzpmt == zzffx.zzpmt) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffx.zzpvl == null || zzffx.zzpvl.isEmpty() : this.zzpvl.equals(zzffx.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpkm;
        return ((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.zzpmt) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzflk.zzf(2, j);
        }
        int i2 = this.zzpmt;
        if (i2 != 0) {
            zzflk.zzad(3, i2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.zzpkl;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzq += zzflk.zzc(2, j);
        }
        int i2 = this.zzpmt;
        return i2 != 0 ? zzq + zzflk.zzag(3, i2) : zzq;
    }
}
