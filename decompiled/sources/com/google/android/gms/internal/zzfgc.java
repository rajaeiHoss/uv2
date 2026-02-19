package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfgc extends zzflm<zzfgc> {
    public int zzpkl = 0;
    public int zzpna = 0;

    public zzfgc() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbc */
    public final zzfgc zza(zzflj zzflj) throws IOException {
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
                i = zzflj.getPosition();
                try {
                    this.zzpna = zzffc.zzkx(zzflj.zzcym());
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
        if (!(obj instanceof zzfgc)) {
            return false;
        }
        zzfgc zzfgc = (zzfgc) obj;
        if (this.zzpkl == zzfgc.zzpkl && this.zzpna == zzfgc.zzpna) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfgc.zzpvl == null || zzfgc.zzpvl.isEmpty() : this.zzpvl.equals(zzfgc.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + this.zzpna) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        int i2 = this.zzpna;
        if (i2 != 0) {
            zzflk.zzad(2, i2);
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
        int i2 = this.zzpna;
        return i2 != 0 ? zzq + zzflk.zzag(2, i2) : zzq;
    }
}
