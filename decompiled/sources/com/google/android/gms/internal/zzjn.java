package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjn extends zzflm<zzjn> {
    private zzju zzbev = null;
    private Integer zzbew = null;
    private zzjl zzbex = null;
    private zzjt[] zzbey = zzjt.zzhw();

    public zzjn() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzp */
    public final zzjn zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                if (this.zzbex == null) {
                    this.zzbex = new zzjl();
                }
                zzfls = this.zzbex;
            } else if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zzjt[] zzjtArr = this.zzbey;
                int length = zzjtArr == null ? 0 : zzjtArr.length;
                int i = zzb + length;
                zzjt[] zzjtArr2 = new zzjt[i];
                if (length != 0) {
                    System.arraycopy(zzjtArr, 0, zzjtArr2, 0, length);
                }
                while (length < i - 1) {
                    zzjtArr2[length] = new zzjt();
                    zzflj.zza(zzjtArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzjtArr2[length] = new zzjt();
                zzflj.zza(zzjtArr2[length]);
                this.zzbey = zzjtArr2;
                continue;
            } else if (zzcxx == 24) {
                int position = zzflj.getPosition();
                try {
                    this.zzbew = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
                continue;
            } else if (zzcxx == 34) {
                if (this.zzbev == null) {
                    this.zzbev = new zzju();
                }
                zzfls = this.zzbev;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            } else {
                continue;
            }
            zzflj.zza(zzfls);
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzjl zzjl = this.zzbex;
        if (zzjl != null) {
            zzflk.zza(1, (zzfls) zzjl);
        }
        zzjt[] zzjtArr = this.zzbey;
        if (zzjtArr != null && zzjtArr.length > 0) {
            int i = 0;
            while (true) {
                zzjt[] zzjtArr2 = this.zzbey;
                if (i >= zzjtArr2.length) {
                    break;
                }
                zzjt zzjt = zzjtArr2[i];
                if (zzjt != null) {
                    zzflk.zza(2, (zzfls) zzjt);
                }
                i++;
            }
        }
        Integer num = this.zzbew;
        if (num != null) {
            zzflk.zzad(3, num.intValue());
        }
        zzju zzju = this.zzbev;
        if (zzju != null) {
            zzflk.zza(4, (zzfls) zzju);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzjl zzjl = this.zzbex;
        if (zzjl != null) {
            zzq += zzflk.zzb(1, (zzfls) zzjl);
        }
        zzjt[] zzjtArr = this.zzbey;
        if (zzjtArr != null && zzjtArr.length > 0) {
            int i = 0;
            while (true) {
                zzjt[] zzjtArr2 = this.zzbey;
                if (i >= zzjtArr2.length) {
                    break;
                }
                zzjt zzjt = zzjtArr2[i];
                if (zzjt != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzjt);
                }
                i++;
            }
        }
        Integer num = this.zzbew;
        if (num != null) {
            zzq += zzflk.zzag(3, num.intValue());
        }
        zzju zzju = this.zzbev;
        return zzju != null ? zzq + zzflk.zzb(4, (zzfls) zzju) : zzq;
    }
}
