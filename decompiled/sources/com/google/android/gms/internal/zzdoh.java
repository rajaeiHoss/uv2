package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdoh extends zzflm<zzdoh> {
    public String[] zzlyi = zzflv.EMPTY_STRING_ARRAY;
    public int[] zzlyj = zzflv.zzpvy;
    public byte[][] zzlyk = zzflv.zzpwd;

    public zzdoh() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzdoh zzae(byte[] bArr) throws zzflr {
        return (zzdoh) zzfls.zza(new zzdoh(), bArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdoh)) {
            return false;
        }
        zzdoh zzdoh = (zzdoh) obj;
        if (zzflq.equals((Object[]) this.zzlyi, (Object[]) zzdoh.zzlyi) && zzflq.equals(this.zzlyj, zzdoh.zzlyj) && zzflq.zza(this.zzlyk, zzdoh.zzlyk)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzdoh.zzpvl == null || zzdoh.zzpvl.isEmpty() : this.zzpvl.equals(zzdoh.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzlyi)) * 31) + zzflq.hashCode(this.zzlyj)) * 31) + zzflq.zzd(this.zzlyk)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                String[] strArr = this.zzlyi;
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
                this.zzlyi = strArr2;
            } else if (zzcxx == 16) {
                int zzb2 = zzflv.zzb(zzflj, 16);
                int[] iArr = this.zzlyj;
                int length2 = iArr == null ? 0 : iArr.length;
                int i2 = zzb2 + length2;
                int[] iArr2 = new int[i2];
                if (length2 != 0) {
                    System.arraycopy(iArr, 0, iArr2, 0, length2);
                }
                while (length2 < i2 - 1) {
                    iArr2[length2] = zzflj.zzcym();
                    zzflj.zzcxx();
                    length2++;
                }
                iArr2[length2] = zzflj.zzcym();
                this.zzlyj = iArr2;
            } else if (zzcxx == 18) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position = zzflj.getPosition();
                int i3 = 0;
                while (zzflj.zzcyo() > 0) {
                    zzflj.zzcym();
                    i3++;
                }
                zzflj.zzmw(position);
                int[] iArr3 = this.zzlyj;
                int length3 = iArr3 == null ? 0 : iArr3.length;
                int i4 = i3 + length3;
                int[] iArr4 = new int[i4];
                if (length3 != 0) {
                    System.arraycopy(iArr3, 0, iArr4, 0, length3);
                }
                while (length3 < i4) {
                    iArr4[length3] = zzflj.zzcym();
                    length3++;
                }
                this.zzlyj = iArr4;
                zzflj.zzlj(zzli);
            } else if (zzcxx == 26) {
                int zzb3 = zzflv.zzb(zzflj, 26);
                byte[][] bArr = this.zzlyk;
                int length4 = bArr == null ? 0 : bArr.length;
                int i5 = zzb3 + length4;
                byte[][] bArr2 = new byte[i5][];
                if (length4 != 0) {
                    System.arraycopy(bArr, 0, bArr2, 0, length4);
                }
                while (length4 < i5 - 1) {
                    bArr2[length4] = zzflj.readBytes();
                    zzflj.zzcxx();
                    length4++;
                }
                bArr2[length4] = zzflj.readBytes();
                this.zzlyk = bArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String[] strArr = this.zzlyi;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.zzlyi;
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
        int[] iArr = this.zzlyj;
        if (iArr != null && iArr.length > 0) {
            int i3 = 0;
            while (true) {
                int[] iArr2 = this.zzlyj;
                if (i3 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(2, iArr2[i3]);
                i3++;
            }
        }
        byte[][] bArr = this.zzlyk;
        if (bArr != null && bArr.length > 0) {
            while (true) {
                byte[][] bArr2 = this.zzlyk;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzflk.zzc(3, bArr3);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int zzq = super.zzq();
        String[] strArr = this.zzlyi;
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzlyi;
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
        int[] iArr2 = this.zzlyj;
        if (iArr2 != null && iArr2.length > 0) {
            int i5 = 0;
            int i6 = 0;
            while (true) {
                iArr = this.zzlyj;
                if (i5 >= iArr.length) {
                    break;
                }
                i6 += zzflk.zzlx(iArr[i5]);
                i5++;
            }
            zzq = zzq + i6 + (iArr.length * 1);
        }
        byte[][] bArr = this.zzlyk;
        if (bArr == null || bArr.length <= 0) {
            return zzq;
        }
        int i7 = 0;
        int i8 = 0;
        while (true) {
            byte[][] bArr2 = this.zzlyk;
            if (i >= bArr2.length) {
                return zzq + i7 + (i8 * 1);
            }
            byte[] bArr3 = bArr2[i];
            if (bArr3 != null) {
                i8++;
                i7 += zzflk.zzbg(bArr3);
            }
            i++;
        }
    }
}
