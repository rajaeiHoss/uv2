package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbt extends zzflm<zzbt> {
    private static volatile zzbt[] zzyk;
    public String string = "";
    public int type = 1;
    public zzbt[] zzyl = zzx();
    public zzbt[] zzym = zzx();
    public zzbt[] zzyn = zzx();
    public String zzyo = "";
    public String zzyp = "";
    public long zzyq = 0;
    public boolean zzyr = false;
    public zzbt[] zzys = zzx();
    public int[] zzyt = zzflv.zzpvy;
    public boolean zzyu = false;

    public zzbt() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    private static int zze(int i) {
        switch (i) {
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
                return i;
            default:
                StringBuilder sb = new StringBuilder(40);
                sb.append(i);
                sb.append(" is not a valid enum Escaping");
                throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final zzbt zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 8:
                    try {
                        int zzcym = zzflj.zzcym();
                        switch (zzcym) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                this.type = zzcym;
                                break;
                            default:
                                StringBuilder sb = new StringBuilder(36);
                                sb.append(zzcym);
                                sb.append(" is not a valid enum Type");
                                throw new IllegalArgumentException(sb.toString());
                        }
                    } catch (IllegalArgumentException unused) {
                        zzflj.zzmw(zzflj.getPosition());
                        zza(zzflj, zzcxx);
                        break;
                    }
                case 18:
                    this.string = zzflj.readString();
                    break;
                case 26:
                    int zzb = zzflv.zzb(zzflj, 26);
                    zzbt[] zzbtArr = this.zzyl;
                    int length = zzbtArr == null ? 0 : zzbtArr.length;
                    int i = zzb + length;
                    zzbt[] zzbtArr2 = new zzbt[i];
                    if (length != 0) {
                        System.arraycopy(zzbtArr, 0, zzbtArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzbtArr2[length] = new zzbt();
                        zzflj.zza(zzbtArr2[length]);
                        zzflj.zzcxx();
                        length++;
                    }
                    zzbtArr2[length] = new zzbt();
                    zzflj.zza(zzbtArr2[length]);
                    this.zzyl = zzbtArr2;
                    break;
                case 34:
                    int zzb2 = zzflv.zzb(zzflj, 34);
                    zzbt[] zzbtArr3 = this.zzym;
                    int length2 = zzbtArr3 == null ? 0 : zzbtArr3.length;
                    int i2 = zzb2 + length2;
                    zzbt[] zzbtArr4 = new zzbt[i2];
                    if (length2 != 0) {
                        System.arraycopy(zzbtArr3, 0, zzbtArr4, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        zzbtArr4[length2] = new zzbt();
                        zzflj.zza(zzbtArr4[length2]);
                        zzflj.zzcxx();
                        length2++;
                    }
                    zzbtArr4[length2] = new zzbt();
                    zzflj.zza(zzbtArr4[length2]);
                    this.zzym = zzbtArr4;
                    break;
                case 42:
                    int zzb3 = zzflv.zzb(zzflj, 42);
                    zzbt[] zzbtArr5 = this.zzyn;
                    int length3 = zzbtArr5 == null ? 0 : zzbtArr5.length;
                    int i3 = zzb3 + length3;
                    zzbt[] zzbtArr6 = new zzbt[i3];
                    if (length3 != 0) {
                        System.arraycopy(zzbtArr5, 0, zzbtArr6, 0, length3);
                    }
                    while (length3 < i3 - 1) {
                        zzbtArr6[length3] = new zzbt();
                        zzflj.zza(zzbtArr6[length3]);
                        zzflj.zzcxx();
                        length3++;
                    }
                    zzbtArr6[length3] = new zzbt();
                    zzflj.zza(zzbtArr6[length3]);
                    this.zzyn = zzbtArr6;
                    break;
                case 50:
                    this.zzyo = zzflj.readString();
                    break;
                case 58:
                    this.zzyp = zzflj.readString();
                    break;
                case 64:
                    this.zzyq = zzflj.zzcyr();
                    break;
                case 72:
                    this.zzyu = zzflj.zzcyd();
                    break;
                case 80:
                    int zzb4 = zzflv.zzb(zzflj, 80);
                    int[] iArr = new int[zzb4];
                    int i4 = 0;
                    for (int i5 = 0; i5 < zzb4; i5++) {
                        if (i5 != 0) {
                            zzflj.zzcxx();
                        }
                        int position = zzflj.getPosition();
                        try {
                            iArr[i4] = zze(zzflj.zzcym());
                            i4++;
                        } catch (IllegalArgumentException unused2) {
                            zzflj.zzmw(position);
                            zza(zzflj, zzcxx);
                        }
                    }
                    if (i4 != 0) {
                        int[] iArr2 = this.zzyt;
                        int length4 = iArr2 == null ? 0 : iArr2.length;
                        if (length4 != 0 || i4 != zzb4) {
                            int[] iArr3 = new int[(length4 + i4)];
                            if (length4 != 0) {
                                System.arraycopy(iArr2, 0, iArr3, 0, length4);
                            }
                            System.arraycopy(iArr, 0, iArr3, length4, i4);
                            this.zzyt = iArr3;
                            break;
                        } else {
                            this.zzyt = iArr;
                            break;
                        }
                    } else {
                        break;
                    }
                case 82:
                    int zzli = zzflj.zzli(zzflj.zzcym());
                    int position2 = zzflj.getPosition();
                    int i6 = 0;
                    while (zzflj.zzcyo() > 0) {
                        try {
                            zze(zzflj.zzcym());
                            i6++;
                        } catch (IllegalArgumentException unused3) {
                        }
                    }
                    if (i6 != 0) {
                        zzflj.zzmw(position2);
                        int[] iArr4 = this.zzyt;
                        int length5 = iArr4 == null ? 0 : iArr4.length;
                        int[] iArr5 = new int[(i6 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(iArr4, 0, iArr5, 0, length5);
                        }
                        while (zzflj.zzcyo() > 0) {
                            int position3 = zzflj.getPosition();
                            try {
                                iArr5[length5] = zze(zzflj.zzcym());
                                length5++;
                            } catch (IllegalArgumentException unused4) {
                                zzflj.zzmw(position3);
                                zza(zzflj, 80);
                            }
                        }
                        this.zzyt = iArr5;
                    }
                    zzflj.zzlj(zzli);
                    break;
                case 90:
                    int zzb5 = zzflv.zzb(zzflj, 90);
                    zzbt[] zzbtArr7 = this.zzys;
                    int length6 = zzbtArr7 == null ? 0 : zzbtArr7.length;
                    int i7 = zzb5 + length6;
                    zzbt[] zzbtArr8 = new zzbt[i7];
                    if (length6 != 0) {
                        System.arraycopy(zzbtArr7, 0, zzbtArr8, 0, length6);
                    }
                    while (length6 < i7 - 1) {
                        zzbtArr8[length6] = new zzbt();
                        zzflj.zza(zzbtArr8[length6]);
                        zzflj.zzcxx();
                        length6++;
                    }
                    zzbtArr8[length6] = new zzbt();
                    zzflj.zza(zzbtArr8[length6]);
                    this.zzys = zzbtArr8;
                    break;
                case 96:
                    this.zzyr = zzflj.zzcyd();
                    break;
                default:
                    if (super.zza(zzflj, zzcxx)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }

    public static zzbt[] zzx() {
        if (zzyk == null) {
            synchronized (zzflq.zzpvt) {
                if (zzyk == null) {
                    zzyk = new zzbt[0];
                }
            }
        }
        return zzyk;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbt)) {
            return false;
        }
        zzbt zzbt = (zzbt) obj;
        if (this.type != zzbt.type) {
            return false;
        }
        String str = this.string;
        if (str == null) {
            if (zzbt.string != null) {
                return false;
            }
        } else if (!str.equals(zzbt.string)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzyl, (Object[]) zzbt.zzyl) || !zzflq.equals((Object[]) this.zzym, (Object[]) zzbt.zzym) || !zzflq.equals((Object[]) this.zzyn, (Object[]) zzbt.zzyn)) {
            return false;
        }
        String str2 = this.zzyo;
        if (str2 == null) {
            if (zzbt.zzyo != null) {
                return false;
            }
        } else if (!str2.equals(zzbt.zzyo)) {
            return false;
        }
        String str3 = this.zzyp;
        if (str3 == null) {
            if (zzbt.zzyp != null) {
                return false;
            }
        } else if (!str3.equals(zzbt.zzyp)) {
            return false;
        }
        if (this.zzyq == zzbt.zzyq && this.zzyr == zzbt.zzyr && zzflq.equals((Object[]) this.zzys, (Object[]) zzbt.zzys) && zzflq.equals(this.zzyt, zzbt.zzyt) && this.zzyu == zzbt.zzyu) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbt.zzpvl == null || zzbt.zzpvl.isEmpty() : this.zzpvl.equals(zzbt.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.type) * 31;
        String str = this.string;
        int i = 0;
        int hashCode2 = (((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzyl)) * 31) + zzflq.hashCode((Object[]) this.zzym)) * 31) + zzflq.hashCode((Object[]) this.zzyn)) * 31;
        String str2 = this.zzyo;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzyp;
        int hashCode4 = str3 == null ? 0 : str3.hashCode();
        long j = this.zzyq;
        int i2 = (((hashCode3 + hashCode4) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        int i3 = 1231;
        int hashCode5 = (((((i2 + (this.zzyr ? 1231 : 1237)) * 31) + zzflq.hashCode((Object[]) this.zzys)) * 31) + zzflq.hashCode(this.zzyt)) * 31;
        if (!this.zzyu) {
            i3 = 1237;
        }
        int i4 = (hashCode5 + i3) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return i4 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzad(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            zzflk.zzp(2, this.string);
        }
        zzbt[] zzbtArr = this.zzyl;
        int i = 0;
        if (zzbtArr != null && zzbtArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbt[] zzbtArr2 = this.zzyl;
                if (i2 >= zzbtArr2.length) {
                    break;
                }
                zzbt zzbt = zzbtArr2[i2];
                if (zzbt != null) {
                    zzflk.zza(3, (zzfls) zzbt);
                }
                i2++;
            }
        }
        zzbt[] zzbtArr3 = this.zzym;
        if (zzbtArr3 != null && zzbtArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzbt[] zzbtArr4 = this.zzym;
                if (i3 >= zzbtArr4.length) {
                    break;
                }
                zzbt zzbt2 = zzbtArr4[i3];
                if (zzbt2 != null) {
                    zzflk.zza(4, (zzfls) zzbt2);
                }
                i3++;
            }
        }
        zzbt[] zzbtArr5 = this.zzyn;
        if (zzbtArr5 != null && zzbtArr5.length > 0) {
            int i4 = 0;
            while (true) {
                zzbt[] zzbtArr6 = this.zzyn;
                if (i4 >= zzbtArr6.length) {
                    break;
                }
                zzbt zzbt3 = zzbtArr6[i4];
                if (zzbt3 != null) {
                    zzflk.zza(5, (zzfls) zzbt3);
                }
                i4++;
            }
        }
        String str2 = this.zzyo;
        if (str2 != null && !str2.equals("")) {
            zzflk.zzp(6, this.zzyo);
        }
        String str3 = this.zzyp;
        if (str3 != null && !str3.equals("")) {
            zzflk.zzp(7, this.zzyp);
        }
        long j = this.zzyq;
        if (j != 0) {
            zzflk.zzf(8, j);
        }
        boolean z = this.zzyu;
        if (z) {
            zzflk.zzl(9, z);
        }
        int[] iArr = this.zzyt;
        if (iArr != null && iArr.length > 0) {
            int i5 = 0;
            while (true) {
                int[] iArr2 = this.zzyt;
                if (i5 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(10, iArr2[i5]);
                i5++;
            }
        }
        zzbt[] zzbtArr7 = this.zzys;
        if (zzbtArr7 != null && zzbtArr7.length > 0) {
            while (true) {
                zzbt[] zzbtArr8 = this.zzys;
                if (i >= zzbtArr8.length) {
                    break;
                }
                zzbt zzbt4 = zzbtArr8[i];
                if (zzbt4 != null) {
                    zzflk.zza(11, (zzfls) zzbt4);
                }
                i++;
            }
        }
        boolean z2 = this.zzyr;
        if (z2) {
            zzflk.zzl(12, z2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int zzq = super.zzq() + zzflk.zzag(1, this.type);
        String str = this.string;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(2, this.string);
        }
        zzbt[] zzbtArr = this.zzyl;
        int i = 0;
        if (zzbtArr != null && zzbtArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbt[] zzbtArr2 = this.zzyl;
                if (i2 >= zzbtArr2.length) {
                    break;
                }
                zzbt zzbt = zzbtArr2[i2];
                if (zzbt != null) {
                    zzq += zzflk.zzb(3, (zzfls) zzbt);
                }
                i2++;
            }
        }
        zzbt[] zzbtArr3 = this.zzym;
        if (zzbtArr3 != null && zzbtArr3.length > 0) {
            int i3 = 0;
            while (true) {
                zzbt[] zzbtArr4 = this.zzym;
                if (i3 >= zzbtArr4.length) {
                    break;
                }
                zzbt zzbt2 = zzbtArr4[i3];
                if (zzbt2 != null) {
                    zzq += zzflk.zzb(4, (zzfls) zzbt2);
                }
                i3++;
            }
        }
        zzbt[] zzbtArr5 = this.zzyn;
        if (zzbtArr5 != null && zzbtArr5.length > 0) {
            int i4 = 0;
            while (true) {
                zzbt[] zzbtArr6 = this.zzyn;
                if (i4 >= zzbtArr6.length) {
                    break;
                }
                zzbt zzbt3 = zzbtArr6[i4];
                if (zzbt3 != null) {
                    zzq += zzflk.zzb(5, (zzfls) zzbt3);
                }
                i4++;
            }
        }
        String str2 = this.zzyo;
        if (str2 != null && !str2.equals("")) {
            zzq += zzflk.zzq(6, this.zzyo);
        }
        String str3 = this.zzyp;
        if (str3 != null && !str3.equals("")) {
            zzq += zzflk.zzq(7, this.zzyp);
        }
        long j = this.zzyq;
        if (j != 0) {
            zzq += zzflk.zzc(8, j);
        }
        if (this.zzyu) {
            zzq += zzflk.zzlw(9) + 1;
        }
        int[] iArr2 = this.zzyt;
        if (iArr2 != null && iArr2.length > 0) {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                iArr = this.zzyt;
                if (i5 >= iArr.length) {
                    break;
                }
                i6 += zzflk.zzlx(iArr[i5]);
                i5++;
            }
            zzq = zzq + i6 + (iArr.length * 1);
        }
        zzbt[] zzbtArr7 = this.zzys;
        if (zzbtArr7 != null && zzbtArr7.length > 0) {
            while (true) {
                zzbt[] zzbtArr8 = this.zzys;
                if (i >= zzbtArr8.length) {
                    break;
                }
                zzbt zzbt4 = zzbtArr8[i];
                if (zzbt4 != null) {
                    zzq += zzflk.zzb(11, (zzfls) zzbt4);
                }
                i++;
            }
        }
        return this.zzyr ? zzq + zzflk.zzlw(12) + 1 : zzq;
    }
}
