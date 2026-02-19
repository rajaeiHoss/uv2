package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjf extends zzflm<zzjf> {
    private String zzatx = null;
    private zzje[] zzbdj = zzje.zzhv();
    private Integer zzbdk = null;

    public zzjf() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzi */
    public final zzjf zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzatx = zzflj.readString();
            } else if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zzje[] zzjeArr = this.zzbdj;
                int length = zzjeArr == null ? 0 : zzjeArr.length;
                int i = zzb + length;
                zzje[] zzjeArr2 = new zzje[i];
                if (length != 0) {
                    System.arraycopy(zzjeArr, 0, zzjeArr2, 0, length);
                }
                while (length < i - 1) {
                    zzjeArr2[length] = new zzje();
                    zzflj.zza(zzjeArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzjeArr2[length] = new zzje();
                zzflj.zza(zzjeArr2[length]);
                this.zzbdj = zzjeArr2;
            } else if (zzcxx == 24) {
                int position = zzflj.getPosition();
                try {
                    this.zzbdk = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzatx;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        zzje[] zzjeArr = this.zzbdj;
        if (zzjeArr != null && zzjeArr.length > 0) {
            int i = 0;
            while (true) {
                zzje[] zzjeArr2 = this.zzbdj;
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
        Integer num = this.zzbdk;
        if (num != null) {
            zzflk.zzad(3, num.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzatx;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        zzje[] zzjeArr = this.zzbdj;
        if (zzjeArr != null && zzjeArr.length > 0) {
            int i = 0;
            while (true) {
                zzje[] zzjeArr2 = this.zzbdj;
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
        Integer num = this.zzbdk;
        return num != null ? zzq + zzflk.zzag(3, num.intValue()) : zzq;
    }
}
