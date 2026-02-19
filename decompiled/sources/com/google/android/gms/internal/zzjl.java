package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjl extends zzflm<zzjl> {
    private String zzbes = null;
    private Integer zzbet = null;
    private int[] zzbeu = zzflv.zzpvy;
    private zzju zzbev = null;

    public zzjl() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzn */
    public final zzjl zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzbes = zzflj.readString();
            } else if (zzcxx == 16) {
                int position = zzflj.getPosition();
                try {
                    this.zzbet = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 24) {
                int zzb = zzflv.zzb(zzflj, 24);
                int[] iArr = this.zzbeu;
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
                this.zzbeu = iArr2;
            } else if (zzcxx == 26) {
                int zzli = zzflj.zzli(zzflj.zzcym());
                int position2 = zzflj.getPosition();
                int i2 = 0;
                while (zzflj.zzcyo() > 0) {
                    zzflj.zzcym();
                    i2++;
                }
                zzflj.zzmw(position2);
                int[] iArr3 = this.zzbeu;
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
                this.zzbeu = iArr4;
                zzflj.zzlj(zzli);
            } else if (zzcxx == 34) {
                if (this.zzbev == null) {
                    this.zzbev = new zzju();
                }
                zzflj.zza(this.zzbev);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzbes;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        Integer num = this.zzbet;
        if (num != null) {
            zzflk.zzad(2, num.intValue());
        }
        int[] iArr = this.zzbeu;
        if (iArr != null && iArr.length > 0) {
            int i = 0;
            while (true) {
                int[] iArr2 = this.zzbeu;
                if (i >= iArr2.length) {
                    break;
                }
                zzflk.zzad(3, iArr2[i]);
                i++;
            }
        }
        zzju zzju = this.zzbev;
        if (zzju != null) {
            zzflk.zza(4, (zzfls) zzju);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int[] iArr;
        int zzq = super.zzq();
        String str = this.zzbes;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        Integer num = this.zzbet;
        if (num != null) {
            zzq += zzflk.zzag(2, num.intValue());
        }
        int[] iArr2 = this.zzbeu;
        if (iArr2 != null && iArr2.length > 0) {
            int i = 0;
            int i2 = 0;
            while (true) {
                iArr = this.zzbeu;
                if (i >= iArr.length) {
                    break;
                }
                i2 += zzflk.zzlx(iArr[i]);
                i++;
            }
            zzq = zzq + i2 + (iArr.length * 1);
        }
        zzju zzju = this.zzbev;
        return zzju != null ? zzq + zzflk.zzb(4, (zzfls) zzju) : zzq;
    }
}
