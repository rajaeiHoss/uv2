package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjg extends zzflm<zzjg> {
    private String zzbdl = null;
    private zzje[] zzbdm = zzje.zzhv();
    private Integer zzbdn = null;
    private Integer zzbdo = null;
    private Integer zzbdp = null;

    public zzjg() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzj */
    public final zzjg zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzbdl = zzflj.readString();
            } else if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zzje[] zzjeArr = this.zzbdm;
                int length = zzjeArr == null ? 0 : zzjeArr.length;
                int i2 = zzb + length;
                zzje[] zzjeArr2 = new zzje[i2];
                if (length != 0) {
                    System.arraycopy(zzjeArr, 0, zzjeArr2, 0, length);
                }
                while (length < i2 - 1) {
                    zzjeArr2[length] = new zzje();
                    zzflj.zza(zzjeArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzjeArr2[length] = new zzje();
                zzflj.zza(zzjeArr2[length]);
                this.zzbdm = zzjeArr2;
            } else if (zzcxx == 24) {
                i = zzflj.getPosition();
                this.zzbdn = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
            } else if (zzcxx == 32) {
                i = zzflj.getPosition();
                this.zzbdo = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
            } else if (zzcxx == 40) {
                i = zzflj.getPosition();
                try {
                    this.zzbdp = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(i);
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzbdl;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        zzje[] zzjeArr = this.zzbdm;
        if (zzjeArr != null && zzjeArr.length > 0) {
            int i = 0;
            while (true) {
                zzje[] zzjeArr2 = this.zzbdm;
                if (i >= zzjeArr2.length) {
                    break;
                }
                zzje zzje = zzjeArr2[i];
                if (zzje != null) {
                    zzflk.zza(2, (zzfls) zzje);
                }
                i++;
            }
        }
        Integer num = this.zzbdn;
        if (num != null) {
            zzflk.zzad(3, num.intValue());
        }
        Integer num2 = this.zzbdo;
        if (num2 != null) {
            zzflk.zzad(4, num2.intValue());
        }
        Integer num3 = this.zzbdp;
        if (num3 != null) {
            zzflk.zzad(5, num3.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzbdl;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        zzje[] zzjeArr = this.zzbdm;
        if (zzjeArr != null && zzjeArr.length > 0) {
            int i = 0;
            while (true) {
                zzje[] zzjeArr2 = this.zzbdm;
                if (i >= zzjeArr2.length) {
                    break;
                }
                zzje zzje = zzjeArr2[i];
                if (zzje != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzje);
                }
                i++;
            }
        }
        Integer num = this.zzbdn;
        if (num != null) {
            zzq += zzflk.zzag(3, num.intValue());
        }
        Integer num2 = this.zzbdo;
        if (num2 != null) {
            zzq += zzflk.zzag(4, num2.intValue());
        }
        Integer num3 = this.zzbdp;
        return num3 != null ? zzq + zzflk.zzag(5, num3.intValue()) : zzq;
    }
}
