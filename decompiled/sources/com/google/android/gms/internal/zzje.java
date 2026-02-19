package com.google.android.gms.internal;

import java.io.IOException;

public final class zzje extends zzflm<zzje> {
    private static volatile zzje[] zzbdg;
    private Integer zzbdh = null;
    private zzjs zzbdi = null;

    public zzje() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzh */
    public final zzje zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    switch (zzcym) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                            this.zzbdh = Integer.valueOf(zzcym);
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(44);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum AdFormatType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 18) {
                if (this.zzbdi == null) {
                    this.zzbdi = new zzjs();
                }
                zzflj.zza(this.zzbdi);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public static zzje[] zzhv() {
        if (zzbdg == null) {
            synchronized (zzflq.zzpvt) {
                if (zzbdg == null) {
                    zzbdg = new zzje[0];
                }
            }
        }
        return zzbdg;
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbdh;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        zzjs zzjs = this.zzbdi;
        if (zzjs != null) {
            zzflk.zza(2, (zzfls) zzjs);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbdh;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        zzjs zzjs = this.zzbdi;
        return zzjs != null ? zzq + zzflk.zzb(2, (zzfls) zzjs) : zzq;
    }
}
