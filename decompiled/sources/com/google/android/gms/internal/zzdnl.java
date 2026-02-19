package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.util.Arrays;

public final class zzdnl extends zzflm<zzdnl> {
    public byte[] zzlwr = zzflv.zzpwe;
    public String zzlws = "";
    public double zzlwt = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public float zzlwu = 0.0f;
    public long zzlwv = 0;
    public int zzlww = 0;
    public int zzlwx = 0;
    public boolean zzlwy = false;
    public zzdnj[] zzlwz = zzdnj.zzbmb();
    public zzdnk[] zzlxa = zzdnk.zzbmc();
    public String[] zzlxb = zzflv.EMPTY_STRING_ARRAY;
    public long[] zzlxc = zzflv.zzpvz;
    public float[] zzlxd = zzflv.zzpwa;
    public long zzlxe = 0;

    public zzdnl() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdnl)) {
            return false;
        }
        zzdnl zzdnl = (zzdnl) obj;
        if (!Arrays.equals(this.zzlwr, zzdnl.zzlwr)) {
            return false;
        }
        String str = this.zzlws;
        if (str == null) {
            if (zzdnl.zzlws != null) {
                return false;
            }
        } else if (!str.equals(zzdnl.zzlws)) {
            return false;
        }
        if (Double.doubleToLongBits(this.zzlwt) == Double.doubleToLongBits(zzdnl.zzlwt) && Float.floatToIntBits(this.zzlwu) == Float.floatToIntBits(zzdnl.zzlwu) && this.zzlwv == zzdnl.zzlwv && this.zzlww == zzdnl.zzlww && this.zzlwx == zzdnl.zzlwx && this.zzlwy == zzdnl.zzlwy && zzflq.equals((Object[]) this.zzlwz, (Object[]) zzdnl.zzlwz) && zzflq.equals((Object[]) this.zzlxa, (Object[]) zzdnl.zzlxa) && zzflq.equals((Object[]) this.zzlxb, (Object[]) zzdnl.zzlxb) && zzflq.equals(this.zzlxc, zzdnl.zzlxc) && zzflq.equals(this.zzlxd, zzdnl.zzlxd) && this.zzlxe == zzdnl.zzlxe) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzdnl.zzpvl == null || zzdnl.zzpvl.isEmpty() : this.zzpvl.equals(zzdnl.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzlwr)) * 31;
        String str = this.zzlws;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long doubleToLongBits = Double.doubleToLongBits(this.zzlwt);
        long j = this.zzlwv;
        int floatToIntBits = (((((((((((hashCode + hashCode2) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + Float.floatToIntBits(this.zzlwu)) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.zzlww) * 31) + this.zzlwx) * 31;
        int i2 = this.zzlwy ? 1231 : 1237;
        long j2 = this.zzlxe;
        int hashCode3 = (((((((((((((floatToIntBits + i2) * 31) + zzflq.hashCode((Object[]) this.zzlwz)) * 31) + zzflq.hashCode((Object[]) this.zzlxa)) * 31) + zzflq.hashCode((Object[]) this.zzlxb)) * 31) + zzflq.hashCode(this.zzlxc)) * 31) + zzflq.hashCode(this.zzlxd)) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode3 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 10:
                    this.zzlwr = zzflj.readBytes();
                    break;
                case 18:
                    this.zzlws = zzflj.readString();
                    break;
                case 25:
                    this.zzlwt = Double.longBitsToDouble(zzflj.zzcyt());
                    break;
                case 37:
                    this.zzlwu = Float.intBitsToFloat(zzflj.zzcys());
                    break;
                case 40:
                    this.zzlwv = zzflj.zzcyr();
                    break;
                case 48:
                    this.zzlww = zzflj.zzcym();
                    break;
                case 56:
                    int zzcym = zzflj.zzcym();
                    this.zzlwx = (-(zzcym & 1)) ^ (zzcym >>> 1);
                    break;
                case 64:
                    this.zzlwy = zzflj.zzcyd();
                    break;
                case 74:
                    int zzb = zzflv.zzb(zzflj, 74);
                    zzdnj[] zzdnjArr = this.zzlwz;
                    int length = zzdnjArr == null ? 0 : zzdnjArr.length;
                    int i = zzb + length;
                    zzdnj[] zzdnjArr2 = new zzdnj[i];
                    if (length != 0) {
                        System.arraycopy(zzdnjArr, 0, zzdnjArr2, 0, length);
                    }
                    while (length < i - 1) {
                        zzdnjArr2[length] = new zzdnj();
                        zzflj.zza(zzdnjArr2[length]);
                        zzflj.zzcxx();
                        length++;
                    }
                    zzdnjArr2[length] = new zzdnj();
                    zzflj.zza(zzdnjArr2[length]);
                    this.zzlwz = zzdnjArr2;
                    break;
                case 82:
                    int zzb2 = zzflv.zzb(zzflj, 82);
                    zzdnk[] zzdnkArr = this.zzlxa;
                    int length2 = zzdnkArr == null ? 0 : zzdnkArr.length;
                    int i2 = zzb2 + length2;
                    zzdnk[] zzdnkArr2 = new zzdnk[i2];
                    if (length2 != 0) {
                        System.arraycopy(zzdnkArr, 0, zzdnkArr2, 0, length2);
                    }
                    while (length2 < i2 - 1) {
                        zzdnkArr2[length2] = new zzdnk();
                        zzflj.zza(zzdnkArr2[length2]);
                        zzflj.zzcxx();
                        length2++;
                    }
                    zzdnkArr2[length2] = new zzdnk();
                    zzflj.zza(zzdnkArr2[length2]);
                    this.zzlxa = zzdnkArr2;
                    break;
                case 90:
                    int zzb3 = zzflv.zzb(zzflj, 90);
                    String[] strArr = this.zzlxb;
                    int length3 = strArr == null ? 0 : strArr.length;
                    int i3 = zzb3 + length3;
                    String[] strArr2 = new String[i3];
                    if (length3 != 0) {
                        System.arraycopy(strArr, 0, strArr2, 0, length3);
                    }
                    while (length3 < i3 - 1) {
                        strArr2[length3] = zzflj.readString();
                        zzflj.zzcxx();
                        length3++;
                    }
                    strArr2[length3] = zzflj.readString();
                    this.zzlxb = strArr2;
                    break;
                case 96:
                    int zzb4 = zzflv.zzb(zzflj, 96);
                    long[] jArr = this.zzlxc;
                    int length4 = jArr == null ? 0 : jArr.length;
                    int i4 = zzb4 + length4;
                    long[] jArr2 = new long[i4];
                    if (length4 != 0) {
                        System.arraycopy(jArr, 0, jArr2, 0, length4);
                    }
                    while (length4 < i4 - 1) {
                        jArr2[length4] = zzflj.zzcyr();
                        zzflj.zzcxx();
                        length4++;
                    }
                    jArr2[length4] = zzflj.zzcyr();
                    this.zzlxc = jArr2;
                    break;
                case 98:
                    int zzli = zzflj.zzli(zzflj.zzcym());
                    int position = zzflj.getPosition();
                    int i5 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcyr();
                        i5++;
                    }
                    zzflj.zzmw(position);
                    long[] jArr3 = this.zzlxc;
                    int length5 = jArr3 == null ? 0 : jArr3.length;
                    int i6 = i5 + length5;
                    long[] jArr4 = new long[i6];
                    if (length5 != 0) {
                        System.arraycopy(jArr3, 0, jArr4, 0, length5);
                    }
                    while (length5 < i6) {
                        jArr4[length5] = zzflj.zzcyr();
                        length5++;
                    }
                    this.zzlxc = jArr4;
                    zzflj.zzlj(zzli);
                    break;
                case 104:
                    this.zzlxe = zzflj.zzcyr();
                    break;
                case 114:
                    int zzcym2 = zzflj.zzcym();
                    int zzli2 = zzflj.zzli(zzcym2);
                    int i7 = zzcym2 / 4;
                    float[] fArr = this.zzlxd;
                    int length6 = fArr == null ? 0 : fArr.length;
                    int i8 = i7 + length6;
                    float[] fArr2 = new float[i8];
                    if (length6 != 0) {
                        System.arraycopy(fArr, 0, fArr2, 0, length6);
                    }
                    while (length6 < i8) {
                        fArr2[length6] = Float.intBitsToFloat(zzflj.zzcys());
                        length6++;
                    }
                    this.zzlxd = fArr2;
                    zzflj.zzlj(zzli2);
                    break;
                case 117:
                    int zzb5 = zzflv.zzb(zzflj, 117);
                    float[] fArr3 = this.zzlxd;
                    int length7 = fArr3 == null ? 0 : fArr3.length;
                    int i9 = zzb5 + length7;
                    float[] fArr4 = new float[i9];
                    if (length7 != 0) {
                        System.arraycopy(fArr3, 0, fArr4, 0, length7);
                    }
                    while (length7 < i9 - 1) {
                        fArr4[length7] = Float.intBitsToFloat(zzflj.zzcys());
                        zzflj.zzcxx();
                        length7++;
                    }
                    fArr4[length7] = Float.intBitsToFloat(zzflj.zzcys());
                    this.zzlxd = fArr4;
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
        if (!Arrays.equals(this.zzlwr, zzflv.zzpwe)) {
            zzflk.zzc(1, this.zzlwr);
        }
        String str = this.zzlws;
        if (str != null && !str.equals("")) {
            zzflk.zzp(2, this.zzlws);
        }
        if (Double.doubleToLongBits(this.zzlwt) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzflk.zza(3, this.zzlwt);
        }
        if (Float.floatToIntBits(this.zzlwu) != Float.floatToIntBits(0.0f)) {
            zzflk.zzd(4, this.zzlwu);
        }
        long j = this.zzlwv;
        if (j != 0) {
            zzflk.zzf(5, j);
        }
        int i = this.zzlww;
        if (i != 0) {
            zzflk.zzad(6, i);
        }
        int i2 = this.zzlwx;
        int i3 = 0;
        if (i2 != 0) {
            zzflk.zzac(7, 0);
            zzflk.zzmy(zzflk.zzme(i2));
        }
        boolean z = this.zzlwy;
        if (z) {
            zzflk.zzl(8, z);
        }
        zzdnj[] zzdnjArr = this.zzlwz;
        if (zzdnjArr != null && zzdnjArr.length > 0) {
            int i4 = 0;
            while (true) {
                zzdnj[] zzdnjArr2 = this.zzlwz;
                if (i4 >= zzdnjArr2.length) {
                    break;
                }
                zzdnj zzdnj = zzdnjArr2[i4];
                if (zzdnj != null) {
                    zzflk.zza(9, (zzfls) zzdnj);
                }
                i4++;
            }
        }
        zzdnk[] zzdnkArr = this.zzlxa;
        if (zzdnkArr != null && zzdnkArr.length > 0) {
            int i5 = 0;
            while (true) {
                zzdnk[] zzdnkArr2 = this.zzlxa;
                if (i5 >= zzdnkArr2.length) {
                    break;
                }
                zzdnk zzdnk = zzdnkArr2[i5];
                if (zzdnk != null) {
                    zzflk.zza(10, (zzfls) zzdnk);
                }
                i5++;
            }
        }
        String[] strArr = this.zzlxb;
        if (strArr != null && strArr.length > 0) {
            int i6 = 0;
            while (true) {
                String[] strArr2 = this.zzlxb;
                if (i6 >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i6];
                if (str2 != null) {
                    zzflk.zzp(11, str2);
                }
                i6++;
            }
        }
        long[] jArr = this.zzlxc;
        if (jArr != null && jArr.length > 0) {
            int i7 = 0;
            while (true) {
                long[] jArr2 = this.zzlxc;
                if (i7 >= jArr2.length) {
                    break;
                }
                zzflk.zzf(12, jArr2[i7]);
                i7++;
            }
        }
        long j2 = this.zzlxe;
        if (j2 != 0) {
            zzflk.zzf(13, j2);
        }
        float[] fArr = this.zzlxd;
        if (fArr != null && fArr.length > 0) {
            while (true) {
                float[] fArr2 = this.zzlxd;
                if (i3 >= fArr2.length) {
                    break;
                }
                zzflk.zzd(14, fArr2[i3]);
                i3++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        long[] jArr;
        int zzq = super.zzq();
        if (!Arrays.equals(this.zzlwr, zzflv.zzpwe)) {
            zzq += zzflk.zzd(1, this.zzlwr);
        }
        String str = this.zzlws;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(2, this.zzlws);
        }
        if (Double.doubleToLongBits(this.zzlwt) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzq += zzflk.zzlw(3) + 8;
        }
        if (Float.floatToIntBits(this.zzlwu) != Float.floatToIntBits(0.0f)) {
            zzq += zzflk.zzlw(4) + 4;
        }
        long j = this.zzlwv;
        if (j != 0) {
            zzq += zzflk.zzc(5, j);
        }
        int i = this.zzlww;
        if (i != 0) {
            zzq += zzflk.zzag(6, i);
        }
        int i2 = this.zzlwx;
        if (i2 != 0) {
            zzq += zzflk.zzlw(7) + zzflk.zzmf(zzflk.zzme(i2));
        }
        if (this.zzlwy) {
            zzq += zzflk.zzlw(8) + 1;
        }
        zzdnj[] zzdnjArr = this.zzlwz;
        int i3 = 0;
        if (zzdnjArr != null && zzdnjArr.length > 0) {
            int i4 = 0;
            while (true) {
                zzdnj[] zzdnjArr2 = this.zzlwz;
                if (i4 >= zzdnjArr2.length) {
                    break;
                }
                zzdnj zzdnj = zzdnjArr2[i4];
                if (zzdnj != null) {
                    zzq += zzflk.zzb(9, (zzfls) zzdnj);
                }
                i4++;
            }
        }
        zzdnk[] zzdnkArr = this.zzlxa;
        if (zzdnkArr != null && zzdnkArr.length > 0) {
            int i5 = 0;
            while (true) {
                zzdnk[] zzdnkArr2 = this.zzlxa;
                if (i5 >= zzdnkArr2.length) {
                    break;
                }
                zzdnk zzdnk = zzdnkArr2[i5];
                if (zzdnk != null) {
                    zzq += zzflk.zzb(10, (zzfls) zzdnk);
                }
                i5++;
            }
        }
        String[] strArr = this.zzlxb;
        if (strArr != null && strArr.length > 0) {
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (true) {
                String[] strArr2 = this.zzlxb;
                if (i6 >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i6];
                if (str2 != null) {
                    i8++;
                    i7 += zzflk.zztx(str2);
                }
                i6++;
            }
            zzq = zzq + i7 + (i8 * 1);
        }
        long[] jArr2 = this.zzlxc;
        if (jArr2 != null && jArr2.length > 0) {
            int i9 = 0;
            while (true) {
                jArr = this.zzlxc;
                if (i3 >= jArr.length) {
                    break;
                }
                i9 += zzflk.zzdj(jArr[i3]);
                i3++;
            }
            zzq = zzq + i9 + (jArr.length * 1);
        }
        long j2 = this.zzlxe;
        if (j2 != 0) {
            zzq += zzflk.zzc(13, j2);
        }
        float[] fArr = this.zzlxd;
        return (fArr == null || fArr.length <= 0) ? zzq : zzq + (fArr.length * 4) + (fArr.length * 1);
    }
}
