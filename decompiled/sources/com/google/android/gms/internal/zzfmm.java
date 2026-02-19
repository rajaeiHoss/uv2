package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfmm extends zzflm<zzfmm> {
    private static volatile zzfmm[] zzpyb;
    private String name = "";
    private boolean[] zzpyc = zzflv.zzpwc;
    private long[] zzpyd = zzflv.zzpvz;
    private String[] zzpye = zzflv.EMPTY_STRING_ARRAY;
    private zzfmn[] zzpyf = zzfmn.zzdda();
    private byte[] zzpyg = zzflv.zzpwe;
    private double[] zzpyh = zzflv.zzpwb;

    public zzfmm() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzfmm[] zzdcz() {
        if (zzpyb == null) {
            synchronized (zzflq.zzpvt) {
                if (zzpyb == null) {
                    zzpyb = new zzfmm[0];
                }
            }
        }
        return zzpyb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfmm)) {
            return false;
        }
        zzfmm zzfmm = (zzfmm) obj;
        String str = this.name;
        if (str == null) {
            if (zzfmm.name != null) {
                return false;
            }
        } else if (!str.equals(zzfmm.name)) {
            return false;
        }
        if (zzflq.equals(this.zzpyc, zzfmm.zzpyc) && zzflq.equals(this.zzpyd, zzfmm.zzpyd) && zzflq.equals((Object[]) this.zzpye, (Object[]) zzfmm.zzpye) && zzflq.equals((Object[]) this.zzpyf, (Object[]) zzfmm.zzpyf) && Arrays.equals(this.zzpyg, zzfmm.zzpyg) && zzflq.equals(this.zzpyh, zzfmm.zzpyh)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfmm.zzpvl == null || zzfmm.zzpvl.isEmpty() : this.zzpvl.equals(zzfmm.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (((((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzflq.hashCode(this.zzpyc)) * 31) + zzflq.hashCode(this.zzpyd)) * 31) + zzflq.hashCode((Object[]) this.zzpye)) * 31) + zzflq.hashCode((Object[]) this.zzpyf)) * 31) + Arrays.hashCode(this.zzpyg)) * 31) + zzflq.hashCode(this.zzpyh)) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.name = zzflj.readString();
            } else if (zzcxx != 16) {
                if (zzcxx == 18) {
                    i = zzflj.zzli(zzflj.zzcym());
                    int position = zzflj.getPosition();
                    int i2 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcyd();
                        i2++;
                    }
                    zzflj.zzmw(position);
                    boolean[] zArr = this.zzpyc;
                    int length = zArr == null ? 0 : zArr.length;
                    int i3 = i2 + length;
                    boolean[] zArr2 = new boolean[i3];
                    if (length != 0) {
                        System.arraycopy(zArr, 0, zArr2, 0, length);
                    }
                    while (length < i3) {
                        zArr2[length] = zzflj.zzcyd();
                        length++;
                    }
                    this.zzpyc = zArr2;
                    zzflj.zzlj(i);
                } else if (zzcxx == 24) {
                    int zzb = zzflv.zzb(zzflj, 24);
                    long[] jArr = this.zzpyd;
                    int length2 = jArr == null ? 0 : jArr.length;
                    int i4 = zzb + length2;
                    long[] jArr2 = new long[i4];
                    if (length2 != 0) {
                        System.arraycopy(jArr, 0, jArr2, 0, length2);
                    }
                    while (length2 < i4 - 1) {
                        jArr2[length2] = zzflj.zzcxz();
                        zzflj.zzcxx();
                        length2++;
                    }
                    jArr2[length2] = zzflj.zzcxz();
                    this.zzpyd = jArr2;
                } else if (zzcxx == 26) {
                    i = zzflj.zzli(zzflj.zzcym());
                    int position2 = zzflj.getPosition();
                    int i5 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcxz();
                        i5++;
                    }
                    zzflj.zzmw(position2);
                    long[] jArr3 = this.zzpyd;
                    int length3 = jArr3 == null ? 0 : jArr3.length;
                    int i6 = i5 + length3;
                    long[] jArr4 = new long[i6];
                    if (length3 != 0) {
                        System.arraycopy(jArr3, 0, jArr4, 0, length3);
                    }
                    while (length3 < i6) {
                        jArr4[length3] = zzflj.zzcxz();
                        length3++;
                    }
                    this.zzpyd = jArr4;
                    zzflj.zzlj(i);
                } else if (zzcxx == 34) {
                    int zzb2 = zzflv.zzb(zzflj, 34);
                    String[] strArr = this.zzpye;
                    int length4 = strArr == null ? 0 : strArr.length;
                    int i7 = zzb2 + length4;
                    String[] strArr2 = new String[i7];
                    if (length4 != 0) {
                        System.arraycopy(strArr, 0, strArr2, 0, length4);
                    }
                    while (length4 < i7 - 1) {
                        strArr2[length4] = zzflj.readString();
                        zzflj.zzcxx();
                        length4++;
                    }
                    strArr2[length4] = zzflj.readString();
                    this.zzpye = strArr2;
                } else if (zzcxx == 42) {
                    int zzb3 = zzflv.zzb(zzflj, 42);
                    zzfmn[] zzfmnArr = this.zzpyf;
                    int length5 = zzfmnArr == null ? 0 : zzfmnArr.length;
                    int i8 = zzb3 + length5;
                    zzfmn[] zzfmnArr2 = new zzfmn[i8];
                    if (length5 != 0) {
                        System.arraycopy(zzfmnArr, 0, zzfmnArr2, 0, length5);
                    }
                    while (length5 < i8 - 1) {
                        zzfmnArr2[length5] = new zzfmn();
                        zzflj.zza(zzfmnArr2[length5]);
                        zzflj.zzcxx();
                        length5++;
                    }
                    zzfmnArr2[length5] = new zzfmn();
                    zzflj.zza(zzfmnArr2[length5]);
                    this.zzpyf = zzfmnArr2;
                } else if (zzcxx == 50) {
                    this.zzpyg = zzflj.readBytes();
                } else if (zzcxx == 57) {
                    int zzb4 = zzflv.zzb(zzflj, 57);
                    double[] dArr = this.zzpyh;
                    int length6 = dArr == null ? 0 : dArr.length;
                    int i9 = zzb4 + length6;
                    double[] dArr2 = new double[i9];
                    if (length6 != 0) {
                        System.arraycopy(dArr, 0, dArr2, 0, length6);
                    }
                    while (length6 < i9 - 1) {
                        dArr2[length6] = Double.longBitsToDouble(zzflj.zzcyt());
                        zzflj.zzcxx();
                        length6++;
                    }
                    dArr2[length6] = Double.longBitsToDouble(zzflj.zzcyt());
                    this.zzpyh = dArr2;
                } else if (zzcxx == 58) {
                    int zzcym = zzflj.zzcym();
                    int zzli = zzflj.zzli(zzcym);
                    int i10 = zzcym / 8;
                    double[] dArr3 = this.zzpyh;
                    int length7 = dArr3 == null ? 0 : dArr3.length;
                    int i11 = i10 + length7;
                    double[] dArr4 = new double[i11];
                    if (length7 != 0) {
                        System.arraycopy(dArr3, 0, dArr4, 0, length7);
                    }
                    while (length7 < i11) {
                        dArr4[length7] = Double.longBitsToDouble(zzflj.zzcyt());
                        length7++;
                    }
                    this.zzpyh = dArr4;
                    zzflj.zzlj(zzli);
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                }
            } else {
                int zzb5 = zzflv.zzb(zzflj, 16);
                boolean[] zArr3 = this.zzpyc;
                int length8 = zArr3 == null ? 0 : zArr3.length;
                int i12 = zzb5 + length8;
                boolean[] zArr4 = new boolean[i12];
                if (length8 != 0) {
                    System.arraycopy(zArr3, 0, zArr4, 0, length8);
                }
                while (length8 < i12 - 1) {
                    zArr4[length8] = zzflj.zzcyd();
                    zzflj.zzcxx();
                    length8++;
                }
                zArr4[length8] = zzflj.zzcyd();
                this.zzpyc = zArr4;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.name);
        }
        boolean[] zArr = this.zzpyc;
        int i = 0;
        if (zArr != null && zArr.length > 0) {
            int i2 = 0;
            while (true) {
                boolean[] zArr2 = this.zzpyc;
                if (i2 >= zArr2.length) {
                    break;
                }
                zzflk.zzl(2, zArr2[i2]);
                i2++;
            }
        }
        long[] jArr = this.zzpyd;
        if (jArr != null && jArr.length > 0) {
            int i3 = 0;
            while (true) {
                long[] jArr2 = this.zzpyd;
                if (i3 >= jArr2.length) {
                    break;
                }
                zzflk.zzf(3, jArr2[i3]);
                i3++;
            }
        }
        String[] strArr = this.zzpye;
        if (strArr != null && strArr.length > 0) {
            int i4 = 0;
            while (true) {
                String[] strArr2 = this.zzpye;
                if (i4 >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i4];
                if (str2 != null) {
                    zzflk.zzp(4, str2);
                }
                i4++;
            }
        }
        zzfmn[] zzfmnArr = this.zzpyf;
        if (zzfmnArr != null && zzfmnArr.length > 0) {
            int i5 = 0;
            while (true) {
                zzfmn[] zzfmnArr2 = this.zzpyf;
                if (i5 >= zzfmnArr2.length) {
                    break;
                }
                zzfmn zzfmn = zzfmnArr2[i5];
                if (zzfmn != null) {
                    zzflk.zza(5, (zzfls) zzfmn);
                }
                i5++;
            }
        }
        if (!Arrays.equals(this.zzpyg, zzflv.zzpwe)) {
            zzflk.zzc(6, this.zzpyg);
        }
        double[] dArr = this.zzpyh;
        if (dArr != null && dArr.length > 0) {
            while (true) {
                double[] dArr2 = this.zzpyh;
                if (i >= dArr2.length) {
                    break;
                }
                zzflk.zza(7, dArr2[i]);
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        long[] jArr;
        int zzq = super.zzq();
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.name);
        }
        boolean[] zArr = this.zzpyc;
        if (zArr != null && zArr.length > 0) {
            zzq = zzq + (zArr.length * 1) + (zArr.length * 1);
        }
        long[] jArr2 = this.zzpyd;
        int i = 0;
        if (jArr2 != null && jArr2.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                jArr = this.zzpyd;
                if (i2 >= jArr.length) {
                    break;
                }
                i3 += zzflk.zzdj(jArr[i2]);
                i2++;
            }
            zzq = zzq + i3 + (jArr.length * 1);
        }
        String[] strArr = this.zzpye;
        if (strArr != null && strArr.length > 0) {
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (true) {
                String[] strArr2 = this.zzpye;
                if (i4 >= strArr2.length) {
                    break;
                }
                String str2 = strArr2[i4];
                if (str2 != null) {
                    i6++;
                    i5 += zzflk.zztx(str2);
                }
                i4++;
            }
            zzq = zzq + i5 + (i6 * 1);
        }
        zzfmn[] zzfmnArr = this.zzpyf;
        if (zzfmnArr != null && zzfmnArr.length > 0) {
            while (true) {
                zzfmn[] zzfmnArr2 = this.zzpyf;
                if (i >= zzfmnArr2.length) {
                    break;
                }
                zzfmn zzfmn = zzfmnArr2[i];
                if (zzfmn != null) {
                    zzq += zzflk.zzb(5, (zzfls) zzfmn);
                }
                i++;
            }
        }
        if (!Arrays.equals(this.zzpyg, zzflv.zzpwe)) {
            zzq += zzflk.zzd(6, this.zzpyg);
        }
        double[] dArr = this.zzpyh;
        return (dArr == null || dArr.length <= 0) ? zzq : zzq + (dArr.length * 8) + (dArr.length * 1);
    }
}
