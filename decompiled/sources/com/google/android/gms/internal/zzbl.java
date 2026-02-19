package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbl extends zzflm<zzbl> {
    private static volatile zzbl[] zzwg;
    private int name = 0;
    public int[] zzwh = zzflv.zzpvy;
    private int zzwi = 0;
    private boolean zzwj = false;
    private boolean zzwk = false;

    public zzbl() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzbl[] zzs() {
        if (zzwg == null) {
            synchronized (zzflq.zzpvt) {
                if (zzwg == null) {
                    zzwg = new zzbl[0];
                }
            }
        }
        return zzwg;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbl)) {
            return false;
        }
        zzbl zzbl = (zzbl) obj;
        if (zzflq.equals(this.zzwh, zzbl.zzwh) && this.zzwi == zzbl.zzwi && this.name == zzbl.name && this.zzwj == zzbl.zzwj && this.zzwk == zzbl.zzwk) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbl.zzpvl == null || zzbl.zzpvl.isEmpty() : this.zzpvl.equals(zzbl.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode(this.zzwh)) * 31) + this.zzwi) * 31) + this.name) * 31) + (this.zzwj ? 1231 : 1237)) * 31;
        if (!this.zzwk) {
            i = 1237;
        }
        return ((hashCode + i) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzwk = zzflj.zzcyd();
            } else if (zzcxx == 16) {
                this.zzwi = zzflj.zzcym();
            } else if (zzcxx == 24) {
                int zzb = zzflv.zzb(zzflj, 24);
                int[] iArr = this.zzwh;
                int length = iArr == null ? 0 : iArr.length;
                int i = zzb + length;
                int[] iArr2 = new int[i];
                if (length != 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, length);
                }
                while (length < i - 1) {
                    iArr2[length] = zzflj.zzcym();
                    zzflj.zzcxx();
                    length++;
                }
                iArr2[length] = zzflj.zzcym();
                this.zzwh = iArr2;
            } else if (zzcxx == 26) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position = zzflj.getPosition();
                int i2 = 0;
                while (zzflj.zzcyo() > 0) {
                    zzflj.zzcym();
                    i2++;
                }
                zzflj.zzmw(position);
                int[] iArr3 = this.zzwh;
                int length2 = iArr3 == null ? 0 : iArr3.length;
                int i3 = i2 + length2;
                int[] iArr4 = new int[i3];
                if (length2 != 0) {
                    System.arraycopy(iArr3, 0, iArr4, 0, length2);
                }
                while (length2 < i3) {
                    iArr4[length2] = zzflj.zzcym();
                    length2++;
                }
                this.zzwh = iArr4;
                zzflj.zzlj(zzli);
            } else if (zzcxx == 32) {
                this.name = zzflj.zzcym();
            } else if (zzcxx == 48) {
                this.zzwj = zzflj.zzcyd();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        boolean z = this.zzwk;
        if (z) {
            zzflk.zzl(1, z);
        }
        zzflk.zzad(2, this.zzwi);
        int[] iArr = this.zzwh;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzwh;
                if (i >= iArr2.length) {
                    break;
                }
                zzflk.zzad(3, iArr2[i]);
                i++;
            }
        }
        int i2 = this.name;
        if (i2 != 0) {
            zzflk.zzad(4, i2);
        }
        boolean z2 = this.zzwj;
        if (z2) {
            zzflk.zzl(6, z2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int zzq = super.zzq();
        if (this.zzwk) {
            zzq += zzflk.zzlw(1) + 1;
        }
        int zzag = zzq + zzflk.zzag(2, this.zzwi);
        int[] iArr2 = this.zzwh;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                iArr = this.zzwh;
                if (i >= iArr.length) {
                    break;
                }
                i2 += zzflk.zzlx(iArr[i]);
                i++;
            }
            zzag = zzag + i2 + (iArr.length * 1);
        }
        int i3 = this.name;
        if (i3 != 0) {
            zzag += zzflk.zzag(4, i3);
        }
        return this.zzwj ? zzag + zzflk.zzlw(6) + 1 : zzag;
    }
}
