package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffz extends zzflm<zzffz> {
    public int zzpkl = 0;
    public long zzpmu = 0;
    public long zzpmv = 0;

    public zzffz() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaz */
    public final zzffz zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1)) {
                        if (zzcym != 2) {
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TriggerType");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzpkl = zzcym;
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 24) {
                this.zzpmu = zzflj.zzcyr();
            } else if (zzcxx == 32) {
                this.zzpmv = zzflj.zzcyr();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffz)) {
            return false;
        }
        zzffz zzffz = (zzffz) obj;
        if (this.zzpkl == zzffz.zzpkl && this.zzpmu == zzffz.zzpmu && this.zzpmv == zzffz.zzpmv) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffz.zzpvl == null || zzffz.zzpvl.isEmpty() : this.zzpvl.equals(zzffz.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpmu;
        long j2 = this.zzpmv;
        return ((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        long j = this.zzpmu;
        if (j != 0) {
            zzflk.zzf(3, j);
        }
        long j2 = this.zzpmv;
        if (j2 != 0) {
            zzflk.zzf(4, j2);
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
        long j = this.zzpmu;
        if (j != 0) {
            zzq += zzflk.zzc(3, j);
        }
        long j2 = this.zzpmv;
        return j2 != 0 ? zzq + zzflk.zzc(4, j2) : zzq;
    }
}
