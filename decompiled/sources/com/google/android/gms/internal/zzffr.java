package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffr extends zzflm<zzffr> {
    private int zzpkl = 0;
    private long zzpkm = 0;
    private int[] zzpmj = zzflv.zzpvy;
    private int[] zzpmk = zzflv.zzpvy;

    public zzffr() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzas */
    public final zzffr zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    switch (zzcym) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            this.zzpkl = zzcym;
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TriggerType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 16) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx != 24) {
                if (zzcxx == 26) {
                    i = zzflj.zzli(zzflj.zzcym());
                    int position = zzflj.getPosition();
                    int i2 = 0;
                    while (zzflj.zzcyo() > 0) {
                        try {
                            zzfgf.zzla(zzflj.zzcym());
                            i2++;
                        } catch (IllegalArgumentException unused2) {
                        }
                    }
                    if (i2 != 0) {
                        zzflj.zzmw(position);
                        int[] iArr = this.zzpmj;
                        int length = iArr == null ? 0 : iArr.length;
                        int[] iArr2 = new int[(i2 + length)];
                        if (length != 0) {
                            System.arraycopy(iArr, 0, iArr2, 0, length);
                        }
                        while (zzflj.zzcyo() > 0) {
                            int position2 = zzflj.getPosition();
                            try {
                                iArr2[length] = zzfgf.zzla(zzflj.zzcym());
                                length++;
                            } catch (IllegalArgumentException unused3) {
                                zzflj.zzmw(position2);
                                zza(zzflj, 24);
                            }
                        }
                        this.zzpmj = iArr2;
                    }
                    zzflj.zzlj(i);
                } else if (zzcxx == 32) {
                    int zzb = zzflv.zzb(zzflj, 32);
                    int[] iArr3 = new int[zzb];
                    int i3 = 0;
                    for (int i4 = 0; i4 < zzb; i4++) {
                        if (i4 != 0) {
                            zzflj.zzcxx();
                        }
                        int position3 = zzflj.getPosition();
                        try {
                            iArr3[i3] = zzfgf.zzlb(zzflj.zzcym());
                            i3++;
                        } catch (IllegalArgumentException unused4) {
                            zzflj.zzmw(position3);
                            zza(zzflj, zzcxx);
                        }
                    }
                    if (i3 != 0) {
                        int[] iArr4 = this.zzpmk;
                        int length2 = iArr4 == null ? 0 : iArr4.length;
                        if (length2 == 0 && i3 == zzb) {
                            this.zzpmk = iArr3;
                        } else {
                            int[] iArr5 = new int[(length2 + i3)];
                            if (length2 != 0) {
                                System.arraycopy(iArr4, 0, iArr5, 0, length2);
                            }
                            System.arraycopy(iArr3, 0, iArr5, length2, i3);
                            this.zzpmk = iArr5;
                        }
                    }
                } else if (zzcxx == 34) {
                    i = zzflj.zzli(zzflj.zzcym());
                    int position4 = zzflj.getPosition();
                    int i5 = 0;
                    while (zzflj.zzcyo() > 0) {
                        try {
                            zzfgf.zzlb(zzflj.zzcym());
                            i5++;
                        } catch (IllegalArgumentException unused5) {
                        }
                    }
                    if (i5 != 0) {
                        zzflj.zzmw(position4);
                        int[] iArr6 = this.zzpmk;
                        int length3 = iArr6 == null ? 0 : iArr6.length;
                        int[] iArr7 = new int[(i5 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(iArr6, 0, iArr7, 0, length3);
                        }
                        while (zzflj.zzcyo() > 0) {
                            int position5 = zzflj.getPosition();
                            try {
                                iArr7[length3] = zzfgf.zzlb(zzflj.zzcym());
                                length3++;
                            } catch (IllegalArgumentException unused6) {
                                zzflj.zzmw(position5);
                                zza(zzflj, 32);
                            }
                        }
                        this.zzpmk = iArr7;
                    }
                    zzflj.zzlj(i);
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                }
            } else {
                int zzb2 = zzflv.zzb(zzflj, 24);
                int[] iArr8 = new int[zzb2];
                int i6 = 0;
                for (int i7 = 0; i7 < zzb2; i7++) {
                    if (i7 != 0) {
                        zzflj.zzcxx();
                    }
                    int position6 = zzflj.getPosition();
                    try {
                        iArr8[i6] = zzfgf.zzla(zzflj.zzcym());
                        i6++;
                    } catch (IllegalArgumentException unused7) {
                        zzflj.zzmw(position6);
                        zza(zzflj, zzcxx);
                    }
                }
                if (i6 != 0) {
                    int[] iArr9 = this.zzpmj;
                    int length4 = iArr9 == null ? 0 : iArr9.length;
                    if (length4 == 0 && i6 == zzb2) {
                        this.zzpmj = iArr8;
                    } else {
                        int[] iArr10 = new int[(length4 + i6)];
                        if (length4 != 0) {
                            System.arraycopy(iArr9, 0, iArr10, 0, length4);
                        }
                        System.arraycopy(iArr8, 0, iArr10, length4, i6);
                        this.zzpmj = iArr10;
                    }
                }
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffr)) {
            return false;
        }
        zzffr zzffr = (zzffr) obj;
        if (this.zzpkl == zzffr.zzpkl && this.zzpkm == zzffr.zzpkm && zzflq.equals(this.zzpmj, zzffr.zzpmj) && zzflq.equals(this.zzpmk, zzffr.zzpmk)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffr.zzpvl == null || zzffr.zzpvl.isEmpty() : this.zzpvl.equals(zzffr.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpkm;
        return ((((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + zzflq.hashCode(this.zzpmj)) * 31) + zzflq.hashCode(this.zzpmk)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
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
        int[] iArr = this.zzpmj;
        int i2 = 0;
        if (iArr != null && iArr.length > 0) {
            int i3 = 0;
            while (true) {
                int[] iArr2 = this.zzpmj;
                if (i3 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(3, iArr2[i3]);
                i3++;
            }
        }
        int[] iArr3 = this.zzpmk;
        if (iArr3 != null && iArr3.length > 0) {
            while (true) {
                int[] iArr4 = this.zzpmk;
                if (i2 >= iArr4.length) {
                    break;
                }
                zzflk.zzad(4, iArr4[i2]);
                i2++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int zzq = super.zzq();
        int i = this.zzpkl;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzq += zzflk.zzc(2, j);
        }
        int[] iArr2 = this.zzpmj;
        int i2 = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                iArr = this.zzpmj;
                if (i3 >= iArr.length) {
                    break;
                }
                i4 += zzflk.zzlx(iArr[i3]);
                i3++;
            }
            zzq = zzq + i4 + (iArr.length * 1);
        }
        int[] iArr3 = this.zzpmk;
        if (iArr3 == null || iArr3.length <= 0) {
            return zzq;
        }
        int i5 = 0;
        while (true) {
            int[] iArr4 = this.zzpmk;
            if (i2 >= iArr4.length) {
                return zzq + i5 + (iArr4.length * 1);
            }
            i5 += zzflk.zzlx(iArr4[i2]);
            i2++;
        }
    }
}
