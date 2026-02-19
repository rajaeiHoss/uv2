package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfge extends zzflm<zzfge> {
    private int zzpkl = 0;
    private float zzpnd = 0.0f;
    private float zzpne = 0.0f;
    private int zzpnf = 0;
    private int zzpng = 0;
    private int zzpnh = 0;

    public zzfge() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbe */
    public final zzfge zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1 || zzcym == 2 || zzcym == 3 || zzcym == 4)) {
                        if (zzcym != 5) {
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
            } else if (zzcxx == 21) {
                this.zzpnd = Float.intBitsToFloat(zzflj.zzcys());
            } else if (zzcxx == 29) {
                this.zzpne = Float.intBitsToFloat(zzflj.zzcys());
            } else if (zzcxx == 32) {
                this.zzpnf = zzflj.zzcym();
            } else if (zzcxx == 40) {
                this.zzpng = zzflj.zzcym();
            } else if (zzcxx == 48) {
                this.zzpnh = zzflj.zzcym();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfge)) {
            return false;
        }
        zzfge zzfge = (zzfge) obj;
        if (this.zzpkl == zzfge.zzpkl && Float.floatToIntBits(this.zzpnd) == Float.floatToIntBits(zzfge.zzpnd) && Float.floatToIntBits(this.zzpne) == Float.floatToIntBits(zzfge.zzpne) && this.zzpnf == zzfge.zzpnf && this.zzpng == zzfge.zzpng && this.zzpnh == zzfge.zzpnh) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfge.zzpvl == null || zzfge.zzpvl.isEmpty() : this.zzpvl.equals(zzfge.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + Float.floatToIntBits(this.zzpnd)) * 31) + Float.floatToIntBits(this.zzpne)) * 31) + this.zzpnf) * 31) + this.zzpng) * 31) + this.zzpnh) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        if (Float.floatToIntBits(this.zzpnd) != Float.floatToIntBits(0.0f)) {
            zzflk.zzd(2, this.zzpnd);
        }
        if (Float.floatToIntBits(this.zzpne) != Float.floatToIntBits(0.0f)) {
            zzflk.zzd(3, this.zzpne);
        }
        int i2 = this.zzpnf;
        if (i2 != 0) {
            zzflk.zzad(4, i2);
        }
        int i3 = this.zzpng;
        if (i3 != 0) {
            zzflk.zzad(5, i3);
        }
        int i4 = this.zzpnh;
        if (i4 != 0) {
            zzflk.zzad(6, i4);
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
        if (Float.floatToIntBits(this.zzpnd) != Float.floatToIntBits(0.0f)) {
            zzq += zzflk.zzlw(2) + 4;
        }
        if (Float.floatToIntBits(this.zzpne) != Float.floatToIntBits(0.0f)) {
            zzq += zzflk.zzlw(3) + 4;
        }
        int i2 = this.zzpnf;
        if (i2 != 0) {
            zzq += zzflk.zzag(4, i2);
        }
        int i3 = this.zzpng;
        if (i3 != 0) {
            zzq += zzflk.zzag(5, i3);
        }
        int i4 = this.zzpnh;
        return i4 != 0 ? zzq + zzflk.zzag(6, i4) : zzq;
    }
}
