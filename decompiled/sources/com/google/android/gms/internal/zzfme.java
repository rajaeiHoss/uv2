package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfme extends zzflm<zzfme> {
    private static volatile zzfme[] zzpxh;
    public String url = null;
    public Integer zzjsx = null;
    public zzflz zzpxi = null;
    private zzfmb zzpxj = null;
    private Integer zzpxk = null;
    private int[] zzpxl = zzflv.zzpvy;
    private String zzpxm = null;
    public Integer zzpxn = null;
    public String[] zzpxo = zzflv.EMPTY_STRING_ARRAY;

    public zzfme() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbm */
    public final zzfme zza(zzflj zzflj) throws IOException {
        while (true) {
            zzfls zzfls = null;
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 8:
                    this.zzjsx = Integer.valueOf(zzflj.zzcya());
                    continue;
                case 18:
                    this.url = zzflj.readString();
                    continue;
                case 26:
                    if (this.zzpxi == null) {
                        this.zzpxi = new zzflz();
                    }
                    zzfls = this.zzpxi;
                    break;
                case 34:
                    if (this.zzpxj == null) {
                        this.zzpxj = new zzfmb();
                    }
                    zzfls = this.zzpxj;
                    break;
                case 40:
                    this.zzpxk = Integer.valueOf(zzflj.zzcya());
                    continue;
                case 48:
                    int zzb = zzflv.zzb(zzflj, 48);
                    int[] iArr = this.zzpxl;
                    int length = iArr == null ? 0 : iArr.length;
                    int i = zzb + length;
                    int[] iArr2 = new int[i];
                    if (length != 0) {
                        System.arraycopy(iArr, 0, iArr2, 0, length);
                    }
                    while (length < i - 1) {
                        iArr2[length] = zzflj.zzcya();
                        zzflj.zzcxx();
                        length++;
                    }
                    iArr2[length] = zzflj.zzcya();
                    this.zzpxl = iArr2;
                    continue;
                case 50:
                    int zzli = zzflj.zzli(zzflj.zzcym());
                    int position = zzflj.getPosition();
                    int i2 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcya();
                        i2++;
                    }
                    zzflj.zzmw(position);
                    int[] iArr3 = this.zzpxl;
                    int length2 = iArr3 == null ? 0 : iArr3.length;
                    int i3 = i2 + length2;
                    int[] iArr4 = new int[i3];
                    if (length2 != 0) {
                        System.arraycopy(iArr3, 0, iArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        iArr4[length2] = zzflj.zzcya();
                        length2++;
                    }
                    this.zzpxl = iArr4;
                    zzflj.zzlj(zzli);
                    continue;
                case 58:
                    this.zzpxm = zzflj.readString();
                    continue;
                case 64:
                    try {
                        int zzcya = zzflj.zzcya();
                        if (!(zzcya == 0 || zzcya == 1 || zzcya == 2)) {
                            if (zzcya != 3) {
                                StringBuilder sb = new StringBuilder(46);
                                sb.append(zzcya);
                                sb.append(" is not a valid enum AdResourceType");
                                throw new IllegalArgumentException(sb.toString());
                            }
                        }
                        this.zzpxn = Integer.valueOf(zzcya);
                        continue;
                    } catch (IllegalArgumentException unused) {
                        zzflj.zzmw(zzflj.getPosition());
                        zza(zzflj, zzcxx);
                        break;
                    }
                case 74:
                    int zzb2 = zzflv.zzb(zzflj, 74);
                    String[] strArr = this.zzpxo;
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
                    this.zzpxo = strArr2;
                    continue;
                default:
                    if (!super.zza(zzflj, zzcxx)) {
                        return this;
                    }
                    continue;
            }
            if (zzfls != null) {
                zzflj.zza(zzfls);
            }
        }
    }

    public static zzfme[] zzdct() {
        if (zzpxh == null) {
            synchronized (zzflq.zzpvt) {
                if (zzpxh == null) {
                    zzpxh = new zzfme[0];
                }
            }
        }
        return zzpxh;
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzad(1, this.zzjsx.intValue());
        String str = this.url;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        zzflz zzflz = this.zzpxi;
        if (zzflz != null) {
            zzflk.zza(3, (zzfls) zzflz);
        }
        zzfmb zzfmb = this.zzpxj;
        if (zzfmb != null) {
            zzflk.zza(4, (zzfls) zzfmb);
        }
        Integer num = this.zzpxk;
        if (num != null) {
            zzflk.zzad(5, num.intValue());
        }
        int[] iArr = this.zzpxl;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            while (true) {
                int[] iArr2 = this.zzpxl;
                if (i2 >= iArr2.length) {
                    break;
                }
                zzflk.zzad(6, iArr2[i2]);
                i2++;
            }
        }
        String str2 = this.zzpxm;
        if (str2 != null) {
            zzflk.zzp(7, str2);
        }
        Integer num2 = this.zzpxn;
        if (num2 != null) {
            zzflk.zzad(8, num2.intValue());
        }
        String[] strArr = this.zzpxo;
        if (strArr != null && strArr.length > 0) {
            while (true) {
                String[] strArr2 = this.zzpxo;
                if (i >= strArr2.length) {
                    break;
                }
                String str3 = strArr2[i];
                if (str3 != null) {
                    zzflk.zzp(9, str3);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int zzq = super.zzq() + zzflk.zzag(1, this.zzjsx.intValue());
        String str = this.url;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        zzflz zzflz = this.zzpxi;
        if (zzflz != null) {
            zzq += zzflk.zzb(3, (zzfls) zzflz);
        }
        zzfmb zzfmb = this.zzpxj;
        if (zzfmb != null) {
            zzq += zzflk.zzb(4, (zzfls) zzfmb);
        }
        Integer num = this.zzpxk;
        if (num != null) {
            zzq += zzflk.zzag(5, num.intValue());
        }
        int[] iArr2 = this.zzpxl;
        int i = 0;
        if (iArr2 != null && iArr2.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                iArr = this.zzpxl;
                if (i2 >= iArr.length) {
                    break;
                }
                i3 += zzflk.zzlx(iArr[i2]);
                i2++;
            }
            zzq = zzq + i3 + (iArr.length * 1);
        }
        String str2 = this.zzpxm;
        if (str2 != null) {
            zzq += zzflk.zzq(7, str2);
        }
        Integer num2 = this.zzpxn;
        if (num2 != null) {
            zzq += zzflk.zzag(8, num2.intValue());
        }
        String[] strArr = this.zzpxo;
        if (strArr == null || strArr.length <= 0) {
            return zzq;
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            String[] strArr2 = this.zzpxo;
            if (i >= strArr2.length) {
                return zzq + i4 + (i5 * 1);
            }
            String str3 = strArr2[i];
            if (str3 != null) {
                i5++;
                i4 += zzflk.zztx(str3);
            }
            i++;
        }
    }
}
