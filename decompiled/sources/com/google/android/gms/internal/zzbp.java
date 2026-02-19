package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbp extends zzflm<zzbp> {
    public String version = "";
    private String[] zzwu = zzflv.EMPTY_STRING_ARRAY;
    public String[] zzwv = zzflv.EMPTY_STRING_ARRAY;
    public zzbt[] zzww = zzbt.zzx();
    public zzbo[] zzwx = zzbo.zzu();
    public zzbl[] zzwy = zzbl.zzs();
    public zzbl[] zzwz = zzbl.zzs();
    public zzbl[] zzxa = zzbl.zzs();
    public zzbq[] zzxb = zzbq.zzv();
    private String zzxc = "";
    private String zzxd = "";
    private String zzxe = "0";
    private zzbk zzxf = null;
    private float zzxg = 0.0f;
    private boolean zzxh = false;
    private String[] zzxi = zzflv.EMPTY_STRING_ARRAY;
    public int zzxj = 0;

    public zzbp() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbp)) {
            return false;
        }
        zzbp zzbp = (zzbp) obj;
        if (!zzflq.equals((Object[]) this.zzwu, (Object[]) zzbp.zzwu) || !zzflq.equals((Object[]) this.zzwv, (Object[]) zzbp.zzwv) || !zzflq.equals((Object[]) this.zzww, (Object[]) zzbp.zzww) || !zzflq.equals((Object[]) this.zzwx, (Object[]) zzbp.zzwx) || !zzflq.equals((Object[]) this.zzwy, (Object[]) zzbp.zzwy) || !zzflq.equals((Object[]) this.zzwz, (Object[]) zzbp.zzwz) || !zzflq.equals((Object[]) this.zzxa, (Object[]) zzbp.zzxa) || !zzflq.equals((Object[]) this.zzxb, (Object[]) zzbp.zzxb)) {
            return false;
        }
        String str = this.zzxc;
        if (str == null) {
            if (zzbp.zzxc != null) {
                return false;
            }
        } else if (!str.equals(zzbp.zzxc)) {
            return false;
        }
        String str2 = this.zzxd;
        if (str2 == null) {
            if (zzbp.zzxd != null) {
                return false;
            }
        } else if (!str2.equals(zzbp.zzxd)) {
            return false;
        }
        String str3 = this.zzxe;
        if (str3 == null) {
            if (zzbp.zzxe != null) {
                return false;
            }
        } else if (!str3.equals(zzbp.zzxe)) {
            return false;
        }
        String str4 = this.version;
        if (str4 == null) {
            if (zzbp.version != null) {
                return false;
            }
        } else if (!str4.equals(zzbp.version)) {
            return false;
        }
        zzbk zzbk = this.zzxf;
        if (zzbk == null) {
            if (zzbp.zzxf != null) {
                return false;
            }
        } else if (!zzbk.equals(zzbp.zzxf)) {
            return false;
        }
        if (Float.floatToIntBits(this.zzxg) == Float.floatToIntBits(zzbp.zzxg) && this.zzxh == zzbp.zzxh && zzflq.equals((Object[]) this.zzxi, (Object[]) zzbp.zzxi) && this.zzxj == zzbp.zzxj) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbp.zzpvl == null || zzbp.zzpvl.isEmpty() : this.zzpvl.equals(zzbp.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzwu)) * 31) + zzflq.hashCode((Object[]) this.zzwv)) * 31) + zzflq.hashCode((Object[]) this.zzww)) * 31) + zzflq.hashCode((Object[]) this.zzwx)) * 31) + zzflq.hashCode((Object[]) this.zzwy)) * 31) + zzflq.hashCode((Object[]) this.zzwz)) * 31) + zzflq.hashCode((Object[]) this.zzxa)) * 31) + zzflq.hashCode((Object[]) this.zzxb)) * 31;
        String str = this.zzxc;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzxd;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.zzxe;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.version;
        int hashCode5 = hashCode4 + (str4 == null ? 0 : str4.hashCode());
        zzbk zzbk = this.zzxf;
        int hashCode6 = ((((((((((hashCode5 * 31) + (zzbk == null ? 0 : zzbk.hashCode())) * 31) + Float.floatToIntBits(this.zzxg)) * 31) + (this.zzxh ? 1231 : 1237)) * 31) + zzflq.hashCode((Object[]) this.zzxi)) * 31) + this.zzxj) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode6 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 10:
                    int zzb = zzflv.zzb(zzflj, 10);
                    String[] strArr = this.zzwv;
                    int length = strArr == null ? 0 : strArr.length;
                    int i = zzb + length;
                    String[] strArr2 = new String[i];
                    if (length != 0) {
                        System.arraycopy(strArr, 0, strArr2, 0, length);
                    }
                    while (length < i - 1) {
                        strArr2[length] = zzflj.readString();
                        zzflj.zzcxx();
                        length++;
                    }
                    strArr2[length] = zzflj.readString();
                    this.zzwv = strArr2;
                    break;
                case 18:
                    int zzb2 = zzflv.zzb(zzflj, 18);
                    zzbt[] zzbtArr = this.zzww;
                    int length2 = zzbtArr == null ? 0 : zzbtArr.length;
                    int i2 = zzb2 + length2;
                    zzbt[] zzbtArr2 = new zzbt[i2];
                    if (length2 != 0) {
                        System.arraycopy(zzbtArr, 0, zzbtArr2, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        zzbtArr2[length2] = new zzbt();
                        zzflj.zza(zzbtArr2[length2]);
                        zzflj.zzcxx();
                        length2++;
                    }
                    zzbtArr2[length2] = new zzbt();
                    zzflj.zza(zzbtArr2[length2]);
                    this.zzww = zzbtArr2;
                    break;
                case 26:
                    int zzb3 = zzflv.zzb(zzflj, 26);
                    zzbo[] zzboArr = this.zzwx;
                    int length3 = zzboArr == null ? 0 : zzboArr.length;
                    int i3 = zzb3 + length3;
                    zzbo[] zzboArr2 = new zzbo[i3];
                    if (length3 != 0) {
                        System.arraycopy(zzboArr, 0, zzboArr2, 0, length3);
                    }
                    while (length3 < i3 - 1) {
                        zzboArr2[length3] = new zzbo();
                        zzflj.zza(zzboArr2[length3]);
                        zzflj.zzcxx();
                        length3++;
                    }
                    zzboArr2[length3] = new zzbo();
                    zzflj.zza(zzboArr2[length3]);
                    this.zzwx = zzboArr2;
                    break;
                case 34:
                    int zzb4 = zzflv.zzb(zzflj, 34);
                    zzbl[] zzblArr = this.zzwy;
                    int length4 = zzblArr == null ? 0 : zzblArr.length;
                    int i4 = zzb4 + length4;
                    zzbl[] zzblArr2 = new zzbl[i4];
                    if (length4 != 0) {
                        System.arraycopy(zzblArr, 0, zzblArr2, 0, length4);
                    }
                    while (length4 < i4 - 1) {
                        zzblArr2[length4] = new zzbl();
                        zzflj.zza(zzblArr2[length4]);
                        zzflj.zzcxx();
                        length4++;
                    }
                    zzblArr2[length4] = new zzbl();
                    zzflj.zza(zzblArr2[length4]);
                    this.zzwy = zzblArr2;
                    break;
                case 42:
                    int zzb5 = zzflv.zzb(zzflj, 42);
                    zzbl[] zzblArr3 = this.zzwz;
                    int length5 = zzblArr3 == null ? 0 : zzblArr3.length;
                    int i5 = zzb5 + length5;
                    zzbl[] zzblArr4 = new zzbl[i5];
                    if (length5 != 0) {
                        System.arraycopy(zzblArr3, 0, zzblArr4, 0, length5);
                    }
                    while (length5 < i5 - 1) {
                        zzblArr4[length5] = new zzbl();
                        zzflj.zza(zzblArr4[length5]);
                        zzflj.zzcxx();
                        length5++;
                    }
                    zzblArr4[length5] = new zzbl();
                    zzflj.zza(zzblArr4[length5]);
                    this.zzwz = zzblArr4;
                    break;
                case 50:
                    int zzb6 = zzflv.zzb(zzflj, 50);
                    zzbl[] zzblArr5 = this.zzxa;
                    int length6 = zzblArr5 == null ? 0 : zzblArr5.length;
                    int i6 = zzb6 + length6;
                    zzbl[] zzblArr6 = new zzbl[i6];
                    if (length6 != 0) {
                        System.arraycopy(zzblArr5, 0, zzblArr6, 0, length6);
                    }
                    while (length6 < i6 - 1) {
                        zzblArr6[length6] = new zzbl();
                        zzflj.zza(zzblArr6[length6]);
                        zzflj.zzcxx();
                        length6++;
                    }
                    zzblArr6[length6] = new zzbl();
                    zzflj.zza(zzblArr6[length6]);
                    this.zzxa = zzblArr6;
                    break;
                case 58:
                    int zzb7 = zzflv.zzb(zzflj, 58);
                    zzbq[] zzbqArr = this.zzxb;
                    int length7 = zzbqArr == null ? 0 : zzbqArr.length;
                    int i7 = zzb7 + length7;
                    zzbq[] zzbqArr2 = new zzbq[i7];
                    if (length7 != 0) {
                        System.arraycopy(zzbqArr, 0, zzbqArr2, 0, length7);
                    }
                    while (length7 < i7 - 1) {
                        zzbqArr2[length7] = new zzbq();
                        zzflj.zza(zzbqArr2[length7]);
                        zzflj.zzcxx();
                        length7++;
                    }
                    zzbqArr2[length7] = new zzbq();
                    zzflj.zza(zzbqArr2[length7]);
                    this.zzxb = zzbqArr2;
                    break;
                case 74:
                    this.zzxc = zzflj.readString();
                    break;
                case 82:
                    this.zzxd = zzflj.readString();
                    break;
                case 98:
                    this.zzxe = zzflj.readString();
                    break;
                case 106:
                    this.version = zzflj.readString();
                    break;
                case 114:
                    if (this.zzxf == null) {
                        this.zzxf = new zzbk();
                    }
                    zzflj.zza(this.zzxf);
                    break;
                case 125:
                    this.zzxg = Float.intBitsToFloat(zzflj.zzcys());
                    break;
                case 130:
                    int zzb8 = zzflv.zzb(zzflj, 130);
                    String[] strArr3 = this.zzxi;
                    int length8 = strArr3 == null ? 0 : strArr3.length;
                    int i8 = zzb8 + length8;
                    String[] strArr4 = new String[i8];
                    if (length8 != 0) {
                        System.arraycopy(strArr3, 0, strArr4, 0, length8);
                    }
                    while (length8 < i8 - 1) {
                        strArr4[length8] = zzflj.readString();
                        zzflj.zzcxx();
                        length8++;
                    }
                    strArr4[length8] = zzflj.readString();
                    this.zzxi = strArr4;
                    break;
                case 136:
                    this.zzxj = zzflj.zzcym();
                    break;
                case 144:
                    this.zzxh = zzflj.zzcyd();
                    break;
                case 154:
                    int zzb9 = zzflv.zzb(zzflj, 154);
                    String[] strArr5 = this.zzwu;
                    int length9 = strArr5 == null ? 0 : strArr5.length;
                    int i9 = zzb9 + length9;
                    String[] strArr6 = new String[i9];
                    if (length9 != 0) {
                        System.arraycopy(strArr5, 0, strArr6, 0, length9);
                    }
                    while (length9 < i9 - 1) {
                        strArr6[length9] = zzflj.readString();
                        zzflj.zzcxx();
                        length9++;
                    }
                    strArr6[length9] = zzflj.readString();
                    this.zzwu = strArr6;
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

    public final void zza(zzflk zzflk) throws IOException {
        String[] strArr = this.zzwv;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.zzwv;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    zzflk.zzp(1, str);
                }
                i2++;
            }
        }
        zzbt[] zzbtArr = this.zzww;
        if (zzbtArr != null && zzbtArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzbt[] zzbtArr2 = this.zzww;
                if (i3 >= zzbtArr2.length) {
                    break;
                }
                zzbt zzbt = zzbtArr2[i3];
                if (zzbt != null) {
                    zzflk.zza(2, (zzfls) zzbt);
                }
                i3++;
            }
        }
        zzbo[] zzboArr = this.zzwx;
        if (zzboArr != null && zzboArr.length > 0) {
            int i4 = 0;
            while (true) {
                zzbo[] zzboArr2 = this.zzwx;
                if (i4 >= zzboArr2.length) {
                    break;
                }
                zzbo zzbo = zzboArr2[i4];
                if (zzbo != null) {
                    zzflk.zza(3, (zzfls) zzbo);
                }
                i4++;
            }
        }
        zzbl[] zzblArr = this.zzwy;
        if (zzblArr != null && zzblArr.length > 0) {
            int i5 = 0;
            while (true) {
                zzbl[] zzblArr2 = this.zzwy;
                if (i5 >= zzblArr2.length) {
                    break;
                }
                zzbl zzbl = zzblArr2[i5];
                if (zzbl != null) {
                    zzflk.zza(4, (zzfls) zzbl);
                }
                i5++;
            }
        }
        zzbl[] zzblArr3 = this.zzwz;
        if (zzblArr3 != null && zzblArr3.length > 0) {
            int i6 = 0;
            while (true) {
                zzbl[] zzblArr4 = this.zzwz;
                if (i6 >= zzblArr4.length) {
                    break;
                }
                zzbl zzbl2 = zzblArr4[i6];
                if (zzbl2 != null) {
                    zzflk.zza(5, (zzfls) zzbl2);
                }
                i6++;
            }
        }
        zzbl[] zzblArr5 = this.zzxa;
        if (zzblArr5 != null && zzblArr5.length > 0) {
            int i7 = 0;
            while (true) {
                zzbl[] zzblArr6 = this.zzxa;
                if (i7 >= zzblArr6.length) {
                    break;
                }
                zzbl zzbl3 = zzblArr6[i7];
                if (zzbl3 != null) {
                    zzflk.zza(6, (zzfls) zzbl3);
                }
                i7++;
            }
        }
        zzbq[] zzbqArr = this.zzxb;
        if (zzbqArr != null && zzbqArr.length > 0) {
            int i8 = 0;
            while (true) {
                zzbq[] zzbqArr2 = this.zzxb;
                if (i8 >= zzbqArr2.length) {
                    break;
                }
                zzbq zzbq = zzbqArr2[i8];
                if (zzbq != null) {
                    zzflk.zza(7, (zzfls) zzbq);
                }
                i8++;
            }
        }
        String str2 = this.zzxc;
        if (str2 != null && !str2.equals("")) {
            zzflk.zzp(9, this.zzxc);
        }
        String str3 = this.zzxd;
        if (str3 != null && !str3.equals("")) {
            zzflk.zzp(10, this.zzxd);
        }
        String str4 = this.zzxe;
        if (str4 != null && !str4.equals("0")) {
            zzflk.zzp(12, this.zzxe);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            zzflk.zzp(13, this.version);
        }
        zzbk zzbk = this.zzxf;
        if (zzbk != null) {
            zzflk.zza(14, (zzfls) zzbk);
        }
        if (Float.floatToIntBits(this.zzxg) != Float.floatToIntBits(0.0f)) {
            zzflk.zzd(15, this.zzxg);
        }
        String[] strArr3 = this.zzxi;
        if (strArr3 != null && strArr3.length > 0) {
            int i9 = 0;
            while (true) {
                String[] strArr4 = this.zzxi;
                if (i9 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i9];
                if (str6 != null) {
                    zzflk.zzp(16, str6);
                }
                i9++;
            }
        }
        int i10 = this.zzxj;
        if (i10 != 0) {
            zzflk.zzad(17, i10);
        }
        boolean z = this.zzxh;
        if (z) {
            zzflk.zzl(18, z);
        }
        String[] strArr5 = this.zzwu;
        if (strArr5 != null && strArr5.length > 0) {
            while (true) {
                String[] strArr6 = this.zzwu;
                if (i >= strArr6.length) {
                    break;
                }
                String str7 = strArr6[i];
                if (str7 != null) {
                    zzflk.zzp(19, str7);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String[] strArr = this.zzwv;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzwv;
                if (i2 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i2];
                if (str != null) {
                    i4++;
                    i3 += zzflk.zztx(str);
                }
                i2++;
            }
            zzq = zzq + i3 + (i4 * 1);
        }
        zzbt[] zzbtArr = this.zzww;
        if (zzbtArr != null && zzbtArr.length > 0) {
            int i5 = 0;
            while (true) {
                zzbt[] zzbtArr2 = this.zzww;
                if (i5 >= zzbtArr2.length) {
                    break;
                }
                zzbt zzbt = zzbtArr2[i5];
                if (zzbt != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzbt);
                }
                i5++;
            }
        }
        zzbo[] zzboArr = this.zzwx;
        if (zzboArr != null && zzboArr.length > 0) {
            int i6 = 0;
            while (true) {
                zzbo[] zzboArr2 = this.zzwx;
                if (i6 >= zzboArr2.length) {
                    break;
                }
                zzbo zzbo = zzboArr2[i6];
                if (zzbo != null) {
                    zzq += zzflk.zzb(3, (zzfls) zzbo);
                }
                i6++;
            }
        }
        zzbl[] zzblArr = this.zzwy;
        if (zzblArr != null && zzblArr.length > 0) {
            int i7 = 0;
            while (true) {
                zzbl[] zzblArr2 = this.zzwy;
                if (i7 >= zzblArr2.length) {
                    break;
                }
                zzbl zzbl = zzblArr2[i7];
                if (zzbl != null) {
                    zzq += zzflk.zzb(4, (zzfls) zzbl);
                }
                i7++;
            }
        }
        zzbl[] zzblArr3 = this.zzwz;
        if (zzblArr3 != null && zzblArr3.length > 0) {
            int i8 = 0;
            while (true) {
                zzbl[] zzblArr4 = this.zzwz;
                if (i8 >= zzblArr4.length) {
                    break;
                }
                zzbl zzbl2 = zzblArr4[i8];
                if (zzbl2 != null) {
                    zzq += zzflk.zzb(5, (zzfls) zzbl2);
                }
                i8++;
            }
        }
        zzbl[] zzblArr5 = this.zzxa;
        if (zzblArr5 != null && zzblArr5.length > 0) {
            int i9 = 0;
            while (true) {
                zzbl[] zzblArr6 = this.zzxa;
                if (i9 >= zzblArr6.length) {
                    break;
                }
                zzbl zzbl3 = zzblArr6[i9];
                if (zzbl3 != null) {
                    zzq += zzflk.zzb(6, (zzfls) zzbl3);
                }
                i9++;
            }
        }
        zzbq[] zzbqArr = this.zzxb;
        if (zzbqArr != null && zzbqArr.length > 0) {
            int i10 = 0;
            while (true) {
                zzbq[] zzbqArr2 = this.zzxb;
                if (i10 >= zzbqArr2.length) {
                    break;
                }
                zzbq zzbq = zzbqArr2[i10];
                if (zzbq != null) {
                    zzq += zzflk.zzb(7, (zzfls) zzbq);
                }
                i10++;
            }
        }
        String str2 = this.zzxc;
        if (str2 != null && !str2.equals("")) {
            zzq += zzflk.zzq(9, this.zzxc);
        }
        String str3 = this.zzxd;
        if (str3 != null && !str3.equals("")) {
            zzq += zzflk.zzq(10, this.zzxd);
        }
        String str4 = this.zzxe;
        if (str4 != null && !str4.equals("0")) {
            zzq += zzflk.zzq(12, this.zzxe);
        }
        String str5 = this.version;
        if (str5 != null && !str5.equals("")) {
            zzq += zzflk.zzq(13, this.version);
        }
        zzbk zzbk = this.zzxf;
        if (zzbk != null) {
            zzq += zzflk.zzb(14, (zzfls) zzbk);
        }
        if (Float.floatToIntBits(this.zzxg) != Float.floatToIntBits(0.0f)) {
            zzq += zzflk.zzlw(15) + 4;
        }
        String[] strArr3 = this.zzxi;
        if (strArr3 != null && strArr3.length > 0) {
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            while (true) {
                String[] strArr4 = this.zzxi;
                if (i11 >= strArr4.length) {
                    break;
                }
                String str6 = strArr4[i11];
                if (str6 != null) {
                    i13++;
                    i12 += zzflk.zztx(str6);
                }
                i11++;
            }
            zzq = zzq + i12 + (i13 * 2);
        }
        int i14 = this.zzxj;
        if (i14 != 0) {
            zzq += zzflk.zzag(17, i14);
        }
        if (this.zzxh) {
            zzq += zzflk.zzlw(18) + 1;
        }
        String[] strArr5 = this.zzwu;
        if (strArr5 == null || strArr5.length <= 0) {
            return zzq;
        }
        int i15 = 0;
        int i16 = 0;
        while (true) {
            String[] strArr6 = this.zzwu;
            if (i >= strArr6.length) {
                return zzq + i15 + (i16 * 2);
            }
            String str7 = strArr6[i];
            if (str7 != null) {
                i16++;
                i15 += zzflk.zztx(str7);
            }
            i++;
        }
    }
}
