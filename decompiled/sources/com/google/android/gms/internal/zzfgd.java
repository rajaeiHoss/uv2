package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfgd extends zzflm<zzfgd> {
    private int zzpkl = 0;
    private long zzpkm = 0;
    private float zzpnb = 0.0f;
    private int zzpnc = 0;

    public zzfgd() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbd */
    public final zzfgd zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
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
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 16) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 29) {
                this.zzpnb = Float.intBitsToFloat(zzflj.zzcys());
            } else if (zzcxx == 32) {
                this.zzpnc = zzflj.zzcym();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfgd)) {
            return false;
        }
        zzfgd zzfgd = (zzfgd) obj;
        if (this.zzpkl == zzfgd.zzpkl && this.zzpkm == zzfgd.zzpkm && Float.floatToIntBits(this.zzpnb) == Float.floatToIntBits(zzfgd.zzpnb) && this.zzpnc == zzfgd.zzpnc) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfgd.zzpvl == null || zzfgd.zzpvl.isEmpty() : this.zzpvl.equals(zzfgd.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpkm;
        return ((((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Float.floatToIntBits(this.zzpnb)) * 31) + this.zzpnc) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
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
        if (Float.floatToIntBits(this.zzpnb) != Float.floatToIntBits(0.0f)) {
            zzflk.zzd(3, this.zzpnb);
        }
        int i2 = this.zzpnc;
        if (i2 != 0) {
            zzflk.zzad(4, i2);
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
        if (Float.floatToIntBits(this.zzpnb) != Float.floatToIntBits(0.0f)) {
            zzq += zzflk.zzlw(3) + 4;
        }
        int i2 = this.zzpnc;
        return i2 != 0 ? zzq + zzflk.zzag(4, i2) : zzq;
    }
}
