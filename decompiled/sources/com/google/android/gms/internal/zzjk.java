package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjk extends zzflm<zzjk> {
    private Integer zzbei = null;
    public String zzbej = null;
    private Integer zzbek = null;
    private Integer zzbel = null;
    private zzjv zzbem = null;
    public long[] zzben = zzflv.zzpvz;
    public zzji zzbeo = null;
    private zzjj zzbep = null;
    private zzjo zzbeq = null;
    public zzjd zzber = null;

    public zzjk() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzm */
    public final zzjk zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 72:
                    this.zzbei = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 82:
                    this.zzbej = zzflj.readString();
                    continue;
                case 88:
                    this.zzbek = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 96:
                    int position = zzflj.getPosition();
                    try {
                        this.zzbel = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                        continue;
                    } catch (IllegalArgumentException unused) {
                        zzflj.zzmw(position);
                        zza(zzflj, zzcxx);
                        continue;
                    }
                case 106:
                    if (this.zzbem == null) {
                        this.zzbem = new zzjv();
                    }
                    zzfls = this.zzbem;
                    break;
                case 112:
                    int zzb = zzflv.zzb(zzflj, 112);
                    long[] jArr = this.zzben;
                    int length = jArr == null ? 0 : jArr.length;
                    int i = zzb + length;
                    long[] jArr2 = new long[i];
                    if (length != 0) {
                        System.arraycopy(jArr, 0, jArr2, 0, length);
                    }
                    while (length < i - 1) {
                        jArr2[length] = zzflj.zzcyr();
                        zzflj.zzcxx();
                        length++;
                    }
                    jArr2[length] = zzflj.zzcyr();
                    this.zzben = jArr2;
                    continue;
                case 114:
                    int zzli = zzflj.zzli(zzflj.zzcym());
                    int position2 = zzflj.getPosition();
                    int i2 = 0;
                    while (zzflj.zzcyo() > 0) {
                        zzflj.zzcyr();
                        i2++;
                    }
                    zzflj.zzmw(position2);
                    long[] jArr3 = this.zzben;
                    int length2 = jArr3 == null ? 0 : jArr3.length;
                    int i3 = i2 + length2;
                    long[] jArr4 = new long[i3];
                    if (length2 != 0) {
                        System.arraycopy(jArr3, 0, jArr4, 0, length2);
                    }
                    while (length2 < i3) {
                        jArr4[length2] = zzflj.zzcyr();
                        length2++;
                    }
                    this.zzben = jArr4;
                    zzflj.zzlj(zzli);
                    continue;
                case 122:
                    if (this.zzbeo == null) {
                        this.zzbeo = new zzji();
                    }
                    zzfls = this.zzbeo;
                    break;
                case 130:
                    if (this.zzbep == null) {
                        this.zzbep = new zzjj();
                    }
                    zzfls = this.zzbep;
                    break;
                case 138:
                    if (this.zzbeq == null) {
                        this.zzbeq = new zzjo();
                    }
                    zzfls = this.zzbeq;
                    break;
                case 146:
                    if (this.zzber == null) {
                        this.zzber = new zzjd();
                    }
                    zzfls = this.zzber;
                    break;
                default:
                    if (!super.zza(zzflj, zzcxx)) {
                        return this;
                    }
                    continue;
            }
            zzflj.zza(zzfls);
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbei;
        if (num != null) {
            zzflk.zzad(9, num.intValue());
        }
        String str = this.zzbej;
        if (str != null) {
            zzflk.zzp(10, str);
        }
        Integer num2 = this.zzbek;
        int i = 0;
        if (num2 != null) {
            int intValue = num2.intValue();
            zzflk.zzac(11, 0);
            zzflk.zzmy(intValue);
        }
        Integer num3 = this.zzbel;
        if (num3 != null) {
            zzflk.zzad(12, num3.intValue());
        }
        zzjv zzjv = this.zzbem;
        if (zzjv != null) {
            zzflk.zza(13, (zzfls) zzjv);
        }
        long[] jArr = this.zzben;
        if (jArr != null && jArr.length > 0) {
            while (true) {
                long[] jArr2 = this.zzben;
                if (i >= jArr2.length) {
                    break;
                }
                zzflk.zza(14, jArr2[i]);
                i++;
            }
        }
        zzji zzji = this.zzbeo;
        if (zzji != null) {
            zzflk.zza(15, (zzfls) zzji);
        }
        zzjj zzjj = this.zzbep;
        if (zzjj != null) {
            zzflk.zza(16, (zzfls) zzjj);
        }
        zzjo zzjo = this.zzbeq;
        if (zzjo != null) {
            zzflk.zza(17, (zzfls) zzjo);
        }
        zzjd zzjd = this.zzber;
        if (zzjd != null) {
            zzflk.zza(18, (zzfls) zzjd);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        long[] jArr;
        int zzq = super.zzq();
        Integer num = this.zzbei;
        if (num != null) {
            zzq += zzflk.zzag(9, num.intValue());
        }
        String str = this.zzbej;
        if (str != null) {
            zzq += zzflk.zzq(10, str);
        }
        Integer num2 = this.zzbek;
        if (num2 != null) {
            zzq += zzflk.zzlw(11) + zzflk.zzmf(num2.intValue());
        }
        Integer num3 = this.zzbel;
        if (num3 != null) {
            zzq += zzflk.zzag(12, num3.intValue());
        }
        zzjv zzjv = this.zzbem;
        if (zzjv != null) {
            zzq += zzflk.zzb(13, (zzfls) zzjv);
        }
        long[] jArr2 = this.zzben;
        if (jArr2 != null && jArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                jArr = this.zzben;
                if (i >= jArr.length) {
                    break;
                }
                i2 += zzflk.zzdj(jArr[i]);
                i++;
            }
            zzq = zzq + i2 + (jArr.length * 1);
        }
        zzji zzji = this.zzbeo;
        if (zzji != null) {
            zzq += zzflk.zzb(15, (zzfls) zzji);
        }
        zzjj zzjj = this.zzbep;
        if (zzjj != null) {
            zzq += zzflk.zzb(16, (zzfls) zzjj);
        }
        zzjo zzjo = this.zzbeq;
        if (zzjo != null) {
            zzq += zzflk.zzb(17, (zzfls) zzjo);
        }
        zzjd zzjd = this.zzber;
        return zzjd != null ? zzq + zzflk.zzb(18, (zzfls) zzjd) : zzq;
    }
}
