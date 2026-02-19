package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjw extends zzflm<zzjw> {
    private static volatile zzjw[] zzbfy;
    private zzka zzbfz = null;
    private zzkc zzbga = null;
    private zzkd zzbgb = null;
    private zzke zzbgc = null;
    private zzjx zzbgd = null;
    private zzkb zzbge = null;
    private zzjz zzbgf = null;
    private Integer zzbgg = null;
    private Integer zzbgh = null;
    private zzju zzbgi = null;
    private Integer zzbgj = null;
    private Integer zzbgk = null;
    private Integer zzbgl = null;
    private Integer zzbgm = null;
    private Integer zzbgn = null;

    public zzjw() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzjw[] zzhy() {
        if (zzbfy == null) {
            synchronized (zzflq.zzpvt) {
                if (zzbfy == null) {
                    zzbfy = new zzjw[0];
                }
            }
        }
        return zzbfy;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            switch (zzcxx) {
                case 0:
                    return this;
                case 42:
                    if (this.zzbfz == null) {
                        this.zzbfz = new zzka();
                    }
                    zzfls = this.zzbfz;
                    break;
                case 50:
                    if (this.zzbga == null) {
                        this.zzbga = new zzkc();
                    }
                    zzfls = this.zzbga;
                    break;
                case 58:
                    if (this.zzbgb == null) {
                        this.zzbgb = new zzkd();
                    }
                    zzfls = this.zzbgb;
                    break;
                case 66:
                    if (this.zzbgc == null) {
                        this.zzbgc = new zzke();
                    }
                    zzfls = this.zzbgc;
                    break;
                case 74:
                    if (this.zzbgd == null) {
                        this.zzbgd = new zzjx();
                    }
                    zzfls = this.zzbgd;
                    break;
                case 82:
                    if (this.zzbge == null) {
                        this.zzbge = new zzkb();
                    }
                    zzfls = this.zzbge;
                    break;
                case 90:
                    if (this.zzbgf == null) {
                        this.zzbgf = new zzjz();
                    }
                    zzfls = this.zzbgf;
                    break;
                case 96:
                    this.zzbgg = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 104:
                    this.zzbgh = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 114:
                    if (this.zzbgi == null) {
                        this.zzbgi = new zzju();
                    }
                    zzfls = this.zzbgi;
                    break;
                case 120:
                    this.zzbgj = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 128:
                    this.zzbgk = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 136:
                    this.zzbgl = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 144:
                    this.zzbgm = Integer.valueOf(zzflj.zzcym());
                    continue;
                case 152:
                    this.zzbgn = Integer.valueOf(zzflj.zzcym());
                    continue;
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
        zzka zzka = this.zzbfz;
        if (zzka != null) {
            zzflk.zza(5, (zzfls) zzka);
        }
        zzkc zzkc = this.zzbga;
        if (zzkc != null) {
            zzflk.zza(6, (zzfls) zzkc);
        }
        zzkd zzkd = this.zzbgb;
        if (zzkd != null) {
            zzflk.zza(7, (zzfls) zzkd);
        }
        zzke zzke = this.zzbgc;
        if (zzke != null) {
            zzflk.zza(8, (zzfls) zzke);
        }
        zzjx zzjx = this.zzbgd;
        if (zzjx != null) {
            zzflk.zza(9, (zzfls) zzjx);
        }
        zzkb zzkb = this.zzbge;
        if (zzkb != null) {
            zzflk.zza(10, (zzfls) zzkb);
        }
        zzjz zzjz = this.zzbgf;
        if (zzjz != null) {
            zzflk.zza(11, (zzfls) zzjz);
        }
        Integer num = this.zzbgg;
        if (num != null) {
            zzflk.zzad(12, num.intValue());
        }
        Integer num2 = this.zzbgh;
        if (num2 != null) {
            zzflk.zzad(13, num2.intValue());
        }
        zzju zzju = this.zzbgi;
        if (zzju != null) {
            zzflk.zza(14, (zzfls) zzju);
        }
        Integer num3 = this.zzbgj;
        if (num3 != null) {
            zzflk.zzad(15, num3.intValue());
        }
        Integer num4 = this.zzbgk;
        if (num4 != null) {
            zzflk.zzad(16, num4.intValue());
        }
        Integer num5 = this.zzbgl;
        if (num5 != null) {
            zzflk.zzad(17, num5.intValue());
        }
        Integer num6 = this.zzbgm;
        if (num6 != null) {
            zzflk.zzad(18, num6.intValue());
        }
        Integer num7 = this.zzbgn;
        if (num7 != null) {
            zzflk.zzad(19, num7.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzka zzka = this.zzbfz;
        if (zzka != null) {
            zzq += zzflk.zzb(5, (zzfls) zzka);
        }
        zzkc zzkc = this.zzbga;
        if (zzkc != null) {
            zzq += zzflk.zzb(6, (zzfls) zzkc);
        }
        zzkd zzkd = this.zzbgb;
        if (zzkd != null) {
            zzq += zzflk.zzb(7, (zzfls) zzkd);
        }
        zzke zzke = this.zzbgc;
        if (zzke != null) {
            zzq += zzflk.zzb(8, (zzfls) zzke);
        }
        zzjx zzjx = this.zzbgd;
        if (zzjx != null) {
            zzq += zzflk.zzb(9, (zzfls) zzjx);
        }
        zzkb zzkb = this.zzbge;
        if (zzkb != null) {
            zzq += zzflk.zzb(10, (zzfls) zzkb);
        }
        zzjz zzjz = this.zzbgf;
        if (zzjz != null) {
            zzq += zzflk.zzb(11, (zzfls) zzjz);
        }
        Integer num = this.zzbgg;
        if (num != null) {
            zzq += zzflk.zzag(12, num.intValue());
        }
        Integer num2 = this.zzbgh;
        if (num2 != null) {
            zzq += zzflk.zzag(13, num2.intValue());
        }
        zzju zzju = this.zzbgi;
        if (zzju != null) {
            zzq += zzflk.zzb(14, (zzfls) zzju);
        }
        Integer num3 = this.zzbgj;
        if (num3 != null) {
            zzq += zzflk.zzag(15, num3.intValue());
        }
        Integer num4 = this.zzbgk;
        if (num4 != null) {
            zzq += zzflk.zzag(16, num4.intValue());
        }
        Integer num5 = this.zzbgl;
        if (num5 != null) {
            zzq += zzflk.zzag(17, num5.intValue());
        }
        Integer num6 = this.zzbgm;
        if (num6 != null) {
            zzq += zzflk.zzag(18, num6.intValue());
        }
        Integer num7 = this.zzbgn;
        return num7 != null ? zzq + zzflk.zzag(19, num7.intValue()) : zzq;
    }
}
