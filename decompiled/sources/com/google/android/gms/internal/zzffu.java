package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffu extends zzflm<zzffu> {
    private int zzpkl = 0;
    private long zzpkm = 0;
    private long zzpmi = 0;
    private int[] zzpmm = zzflv.zzpvy;
    private String[] zzpmn = zzflv.EMPTY_STRING_ARRAY;
    private String[] zzpmo = zzflv.EMPTY_STRING_ARRAY;
    private String zzpmp = "";

    public zzffu() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzav */
    public final zzffu zza(zzflj zzflj) throws IOException {
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
            } else if (zzcxx == 16) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 24) {
                int zzb = zzflv.zzb(zzflj, 24);
                int[] iArr = this.zzpmm;
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
                this.zzpmm = iArr2;
            } else if (zzcxx == 26) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position = zzflj.getPosition();
                int i2 = 0;
                while (zzflj.zzcyo() > 0) {
                    zzflj.zzcym();
                    i2++;
                }
                zzflj.zzmw(position);
                int[] iArr3 = this.zzpmm;
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
                this.zzpmm = iArr4;
                zzflj.zzlj(zzli);
            } else if (zzcxx == 34) {
                int zzb2 = zzflv.zzb(zzflj, 34);
                String[] strArr = this.zzpmn;
                int length3 = strArr == null ? 0 : strArr.length;
                int i4 = zzb2 + length3;
                String[] strArr2 = new String[i4];
                if (length3 != 0) {
                    System.arraycopy(strArr, 0, strArr2, 0, length3);
                }
                while (length3 < i4 - 1) {
                    strArr2[length3] = zzflj.readString();
                    zzflj.zzcxx();
                    length3++;
                }
                strArr2[length3] = zzflj.readString();
                this.zzpmn = strArr2;
            } else if (zzcxx == 40) {
                this.zzpmi = zzflj.zzcyr();
            } else if (zzcxx == 50) {
                int zzb3 = zzflv.zzb(zzflj, 50);
                String[] strArr3 = this.zzpmo;
                int length4 = strArr3 == null ? 0 : strArr3.length;
                int i5 = zzb3 + length4;
                String[] strArr4 = new String[i5];
                if (length4 != 0) {
                    System.arraycopy(strArr3, 0, strArr4, 0, length4);
                }
                while (length4 < i5 - 1) {
                    strArr4[length4] = zzflj.readString();
                    zzflj.zzcxx();
                    length4++;
                }
                strArr4[length4] = zzflj.readString();
                this.zzpmo = strArr4;
            } else if (zzcxx == 58) {
                this.zzpmp = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffu)) {
            return false;
        }
        zzffu zzffu = (zzffu) obj;
        if (this.zzpkl != zzffu.zzpkl || this.zzpkm != zzffu.zzpkm || !zzflq.equals(this.zzpmm, zzffu.zzpmm) || !zzflq.equals((Object[]) this.zzpmn, (Object[]) zzffu.zzpmn) || this.zzpmi != zzffu.zzpmi || !zzflq.equals((Object[]) this.zzpmo, (Object[]) zzffu.zzpmo)) {
            return false;
        }
        String str = this.zzpmp;
        if (str == null) {
            if (zzffu.zzpmp != null) {
                return false;
            }
        } else if (!str.equals(zzffu.zzpmp)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffu.zzpvl == null || zzffu.zzpvl.isEmpty() : this.zzpvl.equals(zzffu.zzpvl);
    }

    public final int hashCode() {
        long j = this.zzpkm;
        long j2 = this.zzpmi;
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + zzflq.hashCode(this.zzpmm)) * 31) + zzflq.hashCode((Object[]) this.zzpmn)) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + zzflq.hashCode((Object[]) this.zzpmo)) * 31;
        String str = this.zzpmp;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
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
        int[] iArr = this.zzpmm;
        int i2 = 0;
        if (iArr != null && iArr.length > 0) {
            int i3 = 0;
            while (true) {
                int[] iArr2 = this.zzpmm;
                if (i3 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(3, iArr2[i3]);
                i3++;
            }
        }
        String[] strArr = this.zzpmn;
        if (strArr != null && strArr.length > 0) {
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzpmn;
                if (i4 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i4];
                if (str != null) {
                    zzflk.zzp(4, str);
                }
                i4++;
            }
        }
        long j2 = this.zzpmi;
        if (j2 != 0) {
            zzflk.zzf(5, j2);
        }
        String[] strArr3 = this.zzpmo;
        if (strArr3 != null && strArr3.length > 0) {
            while (true) {
                String[] strArr4 = this.zzpmo;
                if (i2 >= strArr4.length) {
                    break;
                }
                String str2 = strArr4[i2];
                if (str2 != null) {
                    zzflk.zzp(6, str2);
                }
                i2++;
            }
        }
        String str3 = this.zzpmp;
        if (str3 != null && !str3.equals("")) {
            zzflk.zzp(7, this.zzpmp);
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
        int[] iArr2 = this.zzpmm;
        int i2 = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i3 = 0;
            int i4 = 0;
            while (true) {
                iArr = this.zzpmm;
                if (i3 >= iArr.length) {
                    break;
                }
                i4 += zzflk.zzlx(iArr[i3]);
                i3++;
            }
            zzq = zzq + i4 + (iArr.length * 1);
        }
        String[] strArr = this.zzpmn;
        if (strArr != null && strArr.length > 0) {
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            while (true) {
                String[] strArr2 = this.zzpmn;
                if (i5 >= strArr2.length) {
                    break;
                }
                String str = strArr2[i5];
                if (str != null) {
                    i7++;
                    i6 += zzflk.zztx(str);
                }
                i5++;
            }
            zzq = zzq + i6 + (i7 * 1);
        }
        long j2 = this.zzpmi;
        if (j2 != 0) {
            zzq += zzflk.zzc(5, j2);
        }
        String[] strArr3 = this.zzpmo;
        if (strArr3 != null && strArr3.length > 0) {
            int i8 = 0;
            int i9 = 0;
            while (true) {
                String[] strArr4 = this.zzpmo;
                if (i2 >= strArr4.length) {
                    break;
                }
                String str2 = strArr4[i2];
                if (str2 != null) {
                    i9++;
                    i8 += zzflk.zztx(str2);
                }
                i2++;
            }
            zzq = zzq + i8 + (i9 * 1);
        }
        String str3 = this.zzpmp;
        return (str3 == null || str3.equals("")) ? zzq : zzq + zzflk.zzq(7, this.zzpmp);
    }
}
