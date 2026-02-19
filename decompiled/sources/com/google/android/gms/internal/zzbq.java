package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbq extends zzflm<zzbq> {
    private static volatile zzbq[] zzxk;
    public int[] zzxl = zzflv.zzpvy;
    public int[] zzxm = zzflv.zzpvy;
    public int[] zzxn = zzflv.zzpvy;
    public int[] zzxo = zzflv.zzpvy;
    public int[] zzxp = zzflv.zzpvy;
    public int[] zzxq = zzflv.zzpvy;
    public int[] zzxr = zzflv.zzpvy;
    public int[] zzxs = zzflv.zzpvy;
    public int[] zzxt = zzflv.zzpvy;
    public int[] zzxu = zzflv.zzpvy;

    public zzbq() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzbq[] zzv() {
        if (zzxk == null) {
            synchronized (zzflq.zzpvt) {
                if (zzxk == null) {
                    zzxk = new zzbq[0];
                }
            }
        }
        return zzxk;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbq)) {
            return false;
        }
        zzbq zzbq = (zzbq) obj;
        if (zzflq.equals(this.zzxl, zzbq.zzxl) && zzflq.equals(this.zzxm, zzbq.zzxm) && zzflq.equals(this.zzxn, zzbq.zzxn) && zzflq.equals(this.zzxo, zzbq.zzxo) && zzflq.equals(this.zzxp, zzbq.zzxp) && zzflq.equals(this.zzxq, zzbq.zzxq) && zzflq.equals(this.zzxr, zzbq.zzxr) && zzflq.equals(this.zzxs, zzbq.zzxs) && zzflq.equals(this.zzxt, zzbq.zzxt) && zzflq.equals(this.zzxu, zzbq.zzxu)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbq.zzpvl == null || zzbq.zzpvl.isEmpty() : this.zzpvl.equals(zzbq.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode(this.zzxl)) * 31) + zzflq.hashCode(this.zzxm)) * 31) + zzflq.hashCode(this.zzxn)) * 31) + zzflq.hashCode(this.zzxo)) * 31) + zzflq.hashCode(this.zzxp)) * 31) + zzflq.hashCode(this.zzxq)) * 31) + zzflq.hashCode(this.zzxr)) * 31) + zzflq.hashCode(this.zzxs)) * 31) + zzflq.hashCode(this.zzxt)) * 31) + zzflq.hashCode(this.zzxu)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 8:
                    int zzb = zzflv.zzb(zzflj, 8);
                    int[] iArr = this.zzxl;
                    int length = iArr == null ? 0 : iArr.length;
                    int i2 = zzb + length;
                    int[] iArr2 = new int[i2];
                    if (length != 0) {
                        System.arraycopy(iArr, 0, iArr2, 0, length);
                    }
                    while (length < i2 - 1) {
                        iArr2[length] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length++;
                    }
                    iArr2[length] = zzflj.zzcym();
                    this.zzxl = iArr2;
                    continue;
                case 10:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position = zzflj.getPosition();
                    int i3 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i3++;
                    }
                    zzflj.zzmw(position);
                    int[] iArr3 = this.zzxl;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int i4 = i3 + length2;
                    int[] iArr4 = new int[i4];
                    if (length2 != 0) {
                        System.arraycopy(iArr3, 0, iArr4, 0, length2);
                    }
                    while (length2 < i4) {
                        iArr4[length2] = zzflj.zzcym();
                        length2++;
                    }
                    this.zzxl = iArr4;
                    break;
                case 16:
                    int zzb2 = zzflv.zzb(zzflj, 16);
                    int[] iArr5 = this.zzxm;
                    int length3 = iArr5 == null ? 0 : iArr5.length;
                    int i5 = zzb2 + length3;
                    int[] iArr6 = new int[i5];
                    if (length3 != 0) {
                        System.arraycopy(iArr5, 0, iArr6, 0, length3);
                    }
                    while (length3 < i5 - 1) {
                        iArr6[length3] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length3++;
                    }
                    iArr6[length3] = zzflj.zzcym();
                    this.zzxm = iArr6;
                    continue;
                case 18:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position2 = zzflj.getPosition();
                    int i6 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i6++;
                    }
                    zzflj.zzmw(position2);
                    int[] iArr7 = this.zzxm;
                    int length4 = iArr7 == null ? 0 : iArr7.length;
                    int i7 = i6 + length4;
                    int[] iArr8 = new int[i7];
                    if (length4 != 0) {
                        System.arraycopy(iArr7, 0, iArr8, 0, length4);
                    }
                    while (length4 < i7) {
                        iArr8[length4] = zzflj.zzcym();
                        length4++;
                    }
                    this.zzxm = iArr8;
                    break;
                case 24:
                    int zzb3 = zzflv.zzb(zzflj, 24);
                    int[] iArr9 = this.zzxn;
                    int length5 = iArr9 == null ? 0 : iArr9.length;
                    int i8 = zzb3 + length5;
                    int[] iArr10 = new int[i8];
                    if (length5 != 0) {
                        System.arraycopy(iArr9, 0, iArr10, 0, length5);
                    }
                    while (length5 < i8 - 1) {
                        iArr10[length5] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length5++;
                    }
                    iArr10[length5] = zzflj.zzcym();
                    this.zzxn = iArr10;
                    continue;
                case 26:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position3 = zzflj.getPosition();
                    int i9 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i9++;
                    }
                    zzflj.zzmw(position3);
                    int[] iArr11 = this.zzxn;
                    int length6 = iArr11 == null ? 0 : iArr11.length;
                    int i10 = i9 + length6;
                    int[] iArr12 = new int[i10];
                    if (length6 != 0) {
                        System.arraycopy(iArr11, 0, iArr12, 0, length6);
                    }
                    while (length6 < i10) {
                        iArr12[length6] = zzflj.zzcym();
                        length6++;
                    }
                    this.zzxn = iArr12;
                    break;
                case 32:
                    int zzb4 = zzflv.zzb(zzflj, 32);
                    int[] iArr13 = this.zzxo;
                    int length7 = iArr13 == null ? 0 : iArr13.length;
                    int i11 = zzb4 + length7;
                    int[] iArr14 = new int[i11];
                    if (length7 != 0) {
                        System.arraycopy(iArr13, 0, iArr14, 0, length7);
                    }
                    while (length7 < i11 - 1) {
                        iArr14[length7] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length7++;
                    }
                    iArr14[length7] = zzflj.zzcym();
                    this.zzxo = iArr14;
                    continue;
                case 34:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position4 = zzflj.getPosition();
                    int i12 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i12++;
                    }
                    zzflj.zzmw(position4);
                    int[] iArr15 = this.zzxo;
                    int length8 = iArr15 == null ? 0 : iArr15.length;
                    int i13 = i12 + length8;
                    int[] iArr16 = new int[i13];
                    if (length8 != 0) {
                        System.arraycopy(iArr15, 0, iArr16, 0, length8);
                    }
                    while (length8 < i13) {
                        iArr16[length8] = zzflj.zzcym();
                        length8++;
                    }
                    this.zzxo = iArr16;
                    break;
                case 40:
                    int zzb5 = zzflv.zzb(zzflj, 40);
                    int[] iArr17 = this.zzxp;
                    int length9 = iArr17 == null ? 0 : iArr17.length;
                    int i14 = zzb5 + length9;
                    int[] iArr18 = new int[i14];
                    if (length9 != 0) {
                        System.arraycopy(iArr17, 0, iArr18, 0, length9);
                    }
                    while (length9 < i14 - 1) {
                        iArr18[length9] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length9++;
                    }
                    iArr18[length9] = zzflj.zzcym();
                    this.zzxp = iArr18;
                    continue;
                case 42:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position5 = zzflj.getPosition();
                    int i15 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i15++;
                    }
                    zzflj.zzmw(position5);
                    int[] iArr19 = this.zzxp;
                    int length10 = iArr19 == null ? 0 : iArr19.length;
                    int i16 = i15 + length10;
                    int[] iArr20 = new int[i16];
                    if (length10 != 0) {
                        System.arraycopy(iArr19, 0, iArr20, 0, length10);
                    }
                    while (length10 < i16) {
                        iArr20[length10] = zzflj.zzcym();
                        length10++;
                    }
                    this.zzxp = iArr20;
                    break;
                case 48:
                    int zzb6 = zzflv.zzb(zzflj, 48);
                    int[] iArr21 = this.zzxq;
                    int length11 = iArr21 == null ? 0 : iArr21.length;
                    int i17 = zzb6 + length11;
                    int[] iArr22 = new int[i17];
                    if (length11 != 0) {
                        System.arraycopy(iArr21, 0, iArr22, 0, length11);
                    }
                    while (length11 < i17 - 1) {
                        iArr22[length11] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length11++;
                    }
                    iArr22[length11] = zzflj.zzcym();
                    this.zzxq = iArr22;
                    continue;
                case 50:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position6 = zzflj.getPosition();
                    int i18 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i18++;
                    }
                    zzflj.zzmw(position6);
                    int[] iArr23 = this.zzxq;
                    int length12 = iArr23 == null ? 0 : iArr23.length;
                    int i19 = i18 + length12;
                    int[] iArr24 = new int[i19];
                    if (length12 != 0) {
                        System.arraycopy(iArr23, 0, iArr24, 0, length12);
                    }
                    while (length12 < i19) {
                        iArr24[length12] = zzflj.zzcym();
                        length12++;
                    }
                    this.zzxq = iArr24;
                    break;
                case 56:
                    int zzb7 = zzflv.zzb(zzflj, 56);
                    int[] iArr25 = this.zzxr;
                    int length13 = iArr25 == null ? 0 : iArr25.length;
                    int i20 = zzb7 + length13;
                    int[] iArr26 = new int[i20];
                    if (length13 != 0) {
                        System.arraycopy(iArr25, 0, iArr26, 0, length13);
                    }
                    while (length13 < i20 - 1) {
                        iArr26[length13] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length13++;
                    }
                    iArr26[length13] = zzflj.zzcym();
                    this.zzxr = iArr26;
                    continue;
                case 58:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position7 = zzflj.getPosition();
                    int i21 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i21++;
                    }
                    zzflj.zzmw(position7);
                    int[] iArr27 = this.zzxr;
                    int length14 = iArr27 == null ? 0 : iArr27.length;
                    int i22 = i21 + length14;
                    int[] iArr28 = new int[i22];
                    if (length14 != 0) {
                        System.arraycopy(iArr27, 0, iArr28, 0, length14);
                    }
                    while (length14 < i22) {
                        iArr28[length14] = zzflj.zzcym();
                        length14++;
                    }
                    this.zzxr = iArr28;
                    break;
                case 64:
                    int zzb8 = zzflv.zzb(zzflj, 64);
                    int[] iArr29 = this.zzxs;
                    int length15 = iArr29 == null ? 0 : iArr29.length;
                    int i23 = zzb8 + length15;
                    int[] iArr30 = new int[i23];
                    if (length15 != 0) {
                        System.arraycopy(iArr29, 0, iArr30, 0, length15);
                    }
                    while (length15 < i23 - 1) {
                        iArr30[length15] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length15++;
                    }
                    iArr30[length15] = zzflj.zzcym();
                    this.zzxs = iArr30;
                    continue;
                case 66:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position8 = zzflj.getPosition();
                    int i24 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i24++;
                    }
                    zzflj.zzmw(position8);
                    int[] iArr31 = this.zzxs;
                    int length16 = iArr31 == null ? 0 : iArr31.length;
                    int i25 = i24 + length16;
                    int[] iArr32 = new int[i25];
                    if (length16 != 0) {
                        System.arraycopy(iArr31, 0, iArr32, 0, length16);
                    }
                    while (length16 < i25) {
                        iArr32[length16] = zzflj.zzcym();
                        length16++;
                    }
                    this.zzxs = iArr32;
                    break;
                case 72:
                    int zzb9 = zzflv.zzb(zzflj, 72);
                    int[] iArr33 = this.zzxt;
                    int length17 = iArr33 == null ? 0 : iArr33.length;
                    int i26 = zzb9 + length17;
                    int[] iArr34 = new int[i26];
                    if (length17 != 0) {
                        System.arraycopy(iArr33, 0, iArr34, 0, length17);
                    }
                    while (length17 < i26 - 1) {
                        iArr34[length17] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length17++;
                    }
                    iArr34[length17] = zzflj.zzcym();
                    this.zzxt = iArr34;
                    continue;
                case 74:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position9 = zzflj.getPosition();
                    int i27 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i27++;
                    }
                    zzflj.zzmw(position9);
                    int[] iArr35 = this.zzxt;
                    int length18 = iArr35 == null ? 0 : iArr35.length;
                    int i28 = i27 + length18;
                    int[] iArr36 = new int[i28];
                    if (length18 != 0) {
                        System.arraycopy(iArr35, 0, iArr36, 0, length18);
                    }
                    while (length18 < i28) {
                        iArr36[length18] = zzflj.zzcym();
                        length18++;
                    }
                    this.zzxt = iArr36;
                    break;
                case 80:
                    int zzb10 = zzflv.zzb(zzflj, 80);
                    int[] iArr37 = this.zzxu;
                    int length19 = iArr37 == null ? 0 : iArr37.length;
                    int i29 = zzb10 + length19;
                    int[] iArr38 = new int[i29];
                    if (length19 != 0) {
                        System.arraycopy(iArr37, 0, iArr38, 0, length19);
                    }
                    while (length19 < i29 - 1) {
                        iArr38[length19] = zzflj.zzcym();
                        zzflj.zzcxx();
                        length19++;
                    }
                    iArr38[length19] = zzflj.zzcym();
                    this.zzxu = iArr38;
                    continue;
                case 82:
                    i = zzflj.zzli(zzflj.zzcym());
                    int position10 = zzflj.getPosition();
                    int i30 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcym();
                        i30++;
                    }
                    zzflj.zzmw(position10);
                    int[] iArr39 = this.zzxu;
                    int length20 = iArr39 == null ? 0 : iArr39.length;
                    int i31 = i30 + length20;
                    int[] iArr40 = new int[i31];
                    if (length20 != 0) {
                        System.arraycopy(iArr39, 0, iArr40, 0, length20);
                    }
                    while (length20 < i31) {
                        iArr40[length20] = zzflj.zzcym();
                        length20++;
                    }
                    this.zzxu = iArr40;
                    break;
                default:
                    if (!super.zza(zzflj, zzcxx)) {
                        return this;
                    }
                    continue;
            }
            zzflj.zzlj(i);
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        int[] iArr = this.zzxl;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzxl;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(1, iArr2[i2]);
                i2++;
            }
        }
        int[] iArr3 = this.zzxm;
        if (iArr3 != null && iArr3.length > 0) {
            int i3 = 0;
            while (true) {
                int[] iArr4 = this.zzxm;
                if (i3 >= iArr4.length) {
                    break;
                }
                zzflk.zzad(2, iArr4[i3]);
                i3++;
            }
        }
        int[] iArr5 = this.zzxn;
        if (iArr5 != null && iArr5.length > 0) {
            int i4 = 0;
            while (true) {
                int[] iArr6 = this.zzxn;
                if (i4 >= iArr6.length) {
                    break;
                }
                zzflk.zzad(3, iArr6[i4]);
                i4++;
            }
        }
        int[] iArr7 = this.zzxo;
        if (iArr7 != null && iArr7.length > 0) {
            int i5 = 0;
            while (true) {
                int[] iArr8 = this.zzxo;
                if (i5 >= iArr8.length) {
                    break;
                }
                zzflk.zzad(4, iArr8[i5]);
                i5++;
            }
        }
        int[] iArr9 = this.zzxp;
        if (iArr9 != null && iArr9.length > 0) {
            int i6 = 0;
            while (true) {
                int[] iArr10 = this.zzxp;
                if (i6 >= iArr10.length) {
                    break;
                }
                zzflk.zzad(5, iArr10[i6]);
                i6++;
            }
        }
        int[] iArr11 = this.zzxq;
        if (iArr11 != null && iArr11.length > 0) {
            int i7 = 0;
            while (true) {
                int[] iArr12 = this.zzxq;
                if (i7 >= iArr12.length) {
                    break;
                }
                zzflk.zzad(6, iArr12[i7]);
                i7++;
            }
        }
        int[] iArr13 = this.zzxr;
        if (iArr13 != null && iArr13.length > 0) {
            int i8 = 0;
            while (true) {
                int[] iArr14 = this.zzxr;
                if (i8 >= iArr14.length) {
                    break;
                }
                zzflk.zzad(7, iArr14[i8]);
                i8++;
            }
        }
        int[] iArr15 = this.zzxs;
        if (iArr15 != null && iArr15.length > 0) {
            int i9 = 0;
            while (true) {
                int[] iArr16 = this.zzxs;
                if (i9 >= iArr16.length) {
                    break;
                }
                zzflk.zzad(8, iArr16[i9]);
                i9++;
            }
        }
        int[] iArr17 = this.zzxt;
        if (iArr17 != null && iArr17.length > 0) {
            int i10 = 0;
            while (true) {
                int[] iArr18 = this.zzxt;
                if (i10 >= iArr18.length) {
                    break;
                }
                zzflk.zzad(9, iArr18[i10]);
                i10++;
            }
        }
        int[] iArr19 = this.zzxu;
        if (iArr19 != null && iArr19.length > 0) {
            while (true) {
                int[] iArr20 = this.zzxu;
                if (i >= iArr20.length) {
                    break;
                }
                zzflk.zzad(10, iArr20[i]);
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        int[] iArr6;
        int[] iArr7;
        int[] iArr8;
        int[] iArr9;
        int zzq = super.zzq();
        int[] iArr10 = this.zzxl;
        int i = 0;
        if (iArr10 != null && iArr10.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                iArr9 = this.zzxl;
                if (i2 >= iArr9.length) {
                    break;
                }
                i3 += zzflk.zzlx(iArr9[i2]);
                i2++;
            }
            zzq = zzq + i3 + (iArr9.length * 1);
        }
        int[] iArr11 = this.zzxm;
        if (iArr11 != null && iArr11.length > 0) {
            int i4 = 0;
            int i5 = 0;
            while (true) {
                iArr8 = this.zzxm;
                if (i4 >= iArr8.length) {
                    break;
                }
                i5 += zzflk.zzlx(iArr8[i4]);
                i4++;
            }
            zzq = zzq + i5 + (iArr8.length * 1);
        }
        int[] iArr12 = this.zzxn;
        if (iArr12 != null && iArr12.length > 0) {
            int i6 = 0;
            int i7 = 0;
            while (true) {
                iArr7 = this.zzxn;
                if (i6 >= iArr7.length) {
                    break;
                }
                i7 += zzflk.zzlx(iArr7[i6]);
                i6++;
            }
            zzq = zzq + i7 + (iArr7.length * 1);
        }
        int[] iArr13 = this.zzxo;
        if (iArr13 != null && iArr13.length > 0) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                iArr6 = this.zzxo;
                if (i8 >= iArr6.length) {
                    break;
                }
                i9 += zzflk.zzlx(iArr6[i8]);
                i8++;
            }
            zzq = zzq + i9 + (iArr6.length * 1);
        }
        int[] iArr14 = this.zzxp;
        if (iArr14 != null && iArr14.length > 0) {
            int i10 = 0;
            int i11 = 0;
            while (true) {
                iArr5 = this.zzxp;
                if (i10 >= iArr5.length) {
                    break;
                }
                i11 += zzflk.zzlx(iArr5[i10]);
                i10++;
            }
            zzq = zzq + i11 + (iArr5.length * 1);
        }
        int[] iArr15 = this.zzxq;
        if (iArr15 != null && iArr15.length > 0) {
            int i12 = 0;
            int i13 = 0;
            while (true) {
                iArr4 = this.zzxq;
                if (i12 >= iArr4.length) {
                    break;
                }
                i13 += zzflk.zzlx(iArr4[i12]);
                i12++;
            }
            zzq = zzq + i13 + (iArr4.length * 1);
        }
        int[] iArr16 = this.zzxr;
        if (iArr16 != null && iArr16.length > 0) {
            int i14 = 0;
            int i15 = 0;
            while (true) {
                iArr3 = this.zzxr;
                if (i14 >= iArr3.length) {
                    break;
                }
                i15 += zzflk.zzlx(iArr3[i14]);
                i14++;
            }
            zzq = zzq + i15 + (iArr3.length * 1);
        }
        int[] iArr17 = this.zzxs;
        if (iArr17 != null && iArr17.length > 0) {
            int i16 = 0;
            int i17 = 0;
            while (true) {
                iArr2 = this.zzxs;
                if (i16 >= iArr2.length) {
                    break;
                }
                i17 += zzflk.zzlx(iArr2[i16]);
                i16++;
            }
            zzq = zzq + i17 + (iArr2.length * 1);
        }
        int[] iArr18 = this.zzxt;
        if (iArr18 != null && iArr18.length > 0) {
            int i18 = 0;
            int i19 = 0;
            while (true) {
                iArr = this.zzxt;
                if (i18 >= iArr.length) {
                    break;
                }
                i19 += zzflk.zzlx(iArr[i18]);
                i18++;
            }
            zzq = zzq + i19 + (iArr.length * 1);
        }
        int[] iArr19 = this.zzxu;
        if (iArr19 == null || iArr19.length <= 0) {
            return zzq;
        }
        int i20 = 0;
        while (true) {
            int[] iArr20 = this.zzxu;
            if (i >= iArr20.length) {
                return zzq + i20 + (iArr20.length * 1);
            }
            i20 += zzflk.zzlx(iArr20[i]);
            i++;
        }
    }
}
