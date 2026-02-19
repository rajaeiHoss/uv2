package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfff extends zzflm<zzfff> {
    public int zzpka = 0;
    public int zzpkb = 0;
    public int zzpkc = 0;
    public int zzpkd = 0;
    public int zzpke = 0;
    public int zzpkf = 0;
    public int zzpkl = 0;
    public long zzpkm = 0;

    public zzfff() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzam */
    public final zzfff zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                i = zzflj.getPosition();
                int zzcym = zzflj.zzcym();
                switch (zzcym) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        this.zzpkl = zzcym;
                        break;
                    default:
                        StringBuilder sb = new StringBuilder(43);
                        sb.append(zzcym);
                        sb.append(" is not a valid enum TriggerType");
                        throw new IllegalArgumentException(sb.toString());
                }
            } else if (zzcxx == 16) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 24) {
                i = zzflj.getPosition();
                this.zzpka = zzffb.zzkr(zzflj.zzcym());
            } else if (zzcxx == 32) {
                i = zzflj.getPosition();
                this.zzpkb = zzffb.zzks(zzflj.zzcym());
            } else if (zzcxx == 40) {
                i = zzflj.getPosition();
                this.zzpkc = zzffb.zzkt(zzflj.zzcym());
            } else if (zzcxx == 48) {
                i = zzflj.getPosition();
                this.zzpkd = zzffb.zzku(zzflj.zzcym());
            } else if (zzcxx == 56) {
                i = zzflj.getPosition();
                this.zzpke = zzffb.zzkv(zzflj.zzcym());
            } else if (zzcxx == 64) {
                i = zzflj.getPosition();
                try {
                    this.zzpkf = zzffb.zzkw(zzflj.zzcym());
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
        if (!(obj instanceof zzfff)) {
            return false;
        }
        zzfff zzfff = (zzfff) obj;
        if (this.zzpkl == zzfff.zzpkl && this.zzpkm == zzfff.zzpkm && this.zzpka == zzfff.zzpka && this.zzpkb == zzfff.zzpkb && this.zzpkc == zzfff.zzpkc && this.zzpkd == zzfff.zzpkd && this.zzpke == zzfff.zzpke && this.zzpkf == zzfff.zzpkf) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfff.zzpvl == null || zzfff.zzpvl.isEmpty() : this.zzpvl.equals(zzfff.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpkm;
        return ((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.zzpka) * 31) + this.zzpkb) * 31) + this.zzpkc) * 31) + this.zzpkd) * 31) + this.zzpke) * 31) + this.zzpkf) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
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
        int i2 = this.zzpka;
        if (i2 != 0) {
            zzflk.zzad(3, i2);
        }
        int i3 = this.zzpkb;
        if (i3 != 0) {
            zzflk.zzad(4, i3);
        }
        int i4 = this.zzpkc;
        if (i4 != 0) {
            zzflk.zzad(5, i4);
        }
        int i5 = this.zzpkd;
        if (i5 != 0) {
            zzflk.zzad(6, i5);
        }
        int i6 = this.zzpke;
        if (i6 != 0) {
            zzflk.zzad(7, i6);
        }
        int i7 = this.zzpkf;
        if (i7 != 0) {
            zzflk.zzad(8, i7);
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
        int i2 = this.zzpka;
        if (i2 != 0) {
            zzq += zzflk.zzag(3, i2);
        }
        int i3 = this.zzpkb;
        if (i3 != 0) {
            zzq += zzflk.zzag(4, i3);
        }
        int i4 = this.zzpkc;
        if (i4 != 0) {
            zzq += zzflk.zzag(5, i4);
        }
        int i5 = this.zzpkd;
        if (i5 != 0) {
            zzq += zzflk.zzag(6, i5);
        }
        int i6 = this.zzpke;
        if (i6 != 0) {
            zzq += zzflk.zzag(7, i6);
        }
        int i7 = this.zzpkf;
        return i7 != 0 ? zzq + zzflk.zzag(8, i7) : zzq;
    }
}
