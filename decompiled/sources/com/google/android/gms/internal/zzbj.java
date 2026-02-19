package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbj {

    public static final class zza extends zzflm<zza> {
        public static final zzfln<zzbt, zza> zzxv = zzfln.zza(11, zza.class, 810);
        private static final zza[] zzxw = new zza[0];
        public int[] zzxx = zzflv.zzpvy;
        public int[] zzxy = zzflv.zzpvy;
        public int[] zzxz = zzflv.zzpvy;
        private int zzya = 0;
        public int[] zzyb = zzflv.zzpvy;
        public int zzyc = 0;
        private int zzyd = 0;

        public zza() {
            this.zzpvl = null;
            this.zzpnr = -1;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (zzflq.equals(this.zzxx, zza.zzxx) && zzflq.equals(this.zzxy, zza.zzxy) && zzflq.equals(this.zzxz, zza.zzxz) && this.zzya == zza.zzya && zzflq.equals(this.zzyb, zza.zzyb) && this.zzyc == zza.zzyc && this.zzyd == zza.zzyd) {
                return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zza.zzpvl == null || zza.zzpvl.isEmpty() : this.zzpvl.equals(zza.zzpvl);
            }
            return false;
        }

        public final int hashCode() {
            return ((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode(this.zzxx)) * 31) + zzflq.hashCode(this.zzxy)) * 31) + zzflq.hashCode(this.zzxz)) * 31) + this.zzya) * 31) + zzflq.hashCode(this.zzyb)) * 31) + this.zzyc) * 31) + this.zzyd) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
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
                        int[] iArr = this.zzxx;
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
                        this.zzxx = iArr2;
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
                        int[] iArr3 = this.zzxx;
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
                        this.zzxx = iArr4;
                        break;
                    case 16:
                        int zzb2 = zzflv.zzb(zzflj, 16);
                        int[] iArr5 = this.zzxy;
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
                        this.zzxy = iArr6;
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
                        int[] iArr7 = this.zzxy;
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
                        this.zzxy = iArr8;
                        break;
                    case 24:
                        int zzb3 = zzflv.zzb(zzflj, 24);
                        int[] iArr9 = this.zzxz;
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
                        this.zzxz = iArr10;
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
                        int[] iArr11 = this.zzxz;
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
                        this.zzxz = iArr12;
                        break;
                    case 32:
                        this.zzya = zzflj.zzcym();
                        continue;
                    case 40:
                        int zzb4 = zzflv.zzb(zzflj, 40);
                        int[] iArr13 = this.zzyb;
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
                        this.zzyb = iArr14;
                        continue;
                    case 42:
                        i = zzflj.zzli(zzflj.zzcym());
                        int position4 = zzflj.getPosition();
                        int i12 = 0;
                        while (zzflj.zzcyo() > 0) {
                            zzflj.zzcym();
                            i12++;
                        }
                        zzflj.zzmw(position4);
                        int[] iArr15 = this.zzyb;
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
                        this.zzyb = iArr16;
                        break;
                    case 48:
                        this.zzyc = zzflj.zzcym();
                        continue;
                    case 56:
                        this.zzyd = zzflj.zzcym();
                        continue;
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
            int[] iArr = this.zzxx;
            int i = 0;
            if (iArr != null && iArr.length > 0) {
                int i2 = 0;
                while (true) {
                    int[] iArr2 = this.zzxx;
                    if (i2 >= iArr2.length) {
                        break;
                    }
                    zzflk.zzad(1, iArr2[i2]);
                    i2++;
                }
            }
            int[] iArr3 = this.zzxy;
            if (iArr3 != null && iArr3.length > 0) {
                int i3 = 0;
                while (true) {
                    int[] iArr4 = this.zzxy;
                    if (i3 >= iArr4.length) {
                        break;
                    }
                    zzflk.zzad(2, iArr4[i3]);
                    i3++;
                }
            }
            int[] iArr5 = this.zzxz;
            if (iArr5 != null && iArr5.length > 0) {
                int i4 = 0;
                while (true) {
                    int[] iArr6 = this.zzxz;
                    if (i4 >= iArr6.length) {
                        break;
                    }
                    zzflk.zzad(3, iArr6[i4]);
                    i4++;
                }
            }
            int i5 = this.zzya;
            if (i5 != 0) {
                zzflk.zzad(4, i5);
            }
            int[] iArr7 = this.zzyb;
            if (iArr7 != null && iArr7.length > 0) {
                while (true) {
                    int[] iArr8 = this.zzyb;
                    if (i >= iArr8.length) {
                        break;
                    }
                    zzflk.zzad(5, iArr8[i]);
                    i++;
                }
            }
            int i6 = this.zzyc;
            if (i6 != 0) {
                zzflk.zzad(6, i6);
            }
            int i7 = this.zzyd;
            if (i7 != 0) {
                zzflk.zzad(7, i7);
            }
            super.zza(zzflk);
        }

        /* access modifiers changed from: protected */
        public final int zzq() {
            int[] iArr;
            int[] iArr2;
            int[] iArr3;
            int[] iArr4;
            int zzq = super.zzq();
            int[] iArr5 = this.zzxx;
            int i = 0;
            if (iArr5 != null && iArr5.length > 0) {
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    iArr4 = this.zzxx;
                    if (i2 >= iArr4.length) {
                        break;
                    }
                    i3 += zzflk.zzlx(iArr4[i2]);
                    i2++;
                }
                zzq = zzq + i3 + (iArr4.length * 1);
            }
            int[] iArr6 = this.zzxy;
            if (iArr6 != null && iArr6.length > 0) {
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    iArr3 = this.zzxy;
                    if (i4 >= iArr3.length) {
                        break;
                    }
                    i5 += zzflk.zzlx(iArr3[i4]);
                    i4++;
                }
                zzq = zzq + i5 + (iArr3.length * 1);
            }
            int[] iArr7 = this.zzxz;
            if (iArr7 != null && iArr7.length > 0) {
                int i6 = 0;
                int i7 = 0;
                while (true) {
                    iArr2 = this.zzxz;
                    if (i6 >= iArr2.length) {
                        break;
                    }
                    i7 += zzflk.zzlx(iArr2[i6]);
                    i6++;
                }
                zzq = zzq + i7 + (iArr2.length * 1);
            }
            int i8 = this.zzya;
            if (i8 != 0) {
                zzq += zzflk.zzag(4, i8);
            }
            int[] iArr8 = this.zzyb;
            if (iArr8 != null && iArr8.length > 0) {
                int i9 = 0;
                while (true) {
                    iArr = this.zzyb;
                    if (i >= iArr.length) {
                        break;
                    }
                    i9 += zzflk.zzlx(iArr[i]);
                    i++;
                }
                zzq = zzq + i9 + (iArr.length * 1);
            }
            int i10 = this.zzyc;
            if (i10 != 0) {
                zzq += zzflk.zzag(6, i10);
            }
            int i11 = this.zzyd;
            return i11 != 0 ? zzq + zzflk.zzag(7, i11) : zzq;
        }
    }
}
