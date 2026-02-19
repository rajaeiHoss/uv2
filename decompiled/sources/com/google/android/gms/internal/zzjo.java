package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjo extends zzflm<zzjo> {
    private Integer zzbdh = null;
    private Integer zzbez = null;

    public zzjo() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzq */
    public final zzjo zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                i = zzflj.getPosition();
                int zzcym = zzflj.zzcym();
                if (!(zzcym == 0 || zzcym == 1)) {
                    if (zzcym != 2) {
                        StringBuilder sb = new StringBuilder(43);
                        sb.append(zzcym);
                        sb.append(" is not a valid enum NetworkType");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                this.zzbdh = Integer.valueOf(zzcym);
            } else if (zzcxx == 16) {
                i = zzflj.getPosition();
                try {
                    int zzcym2 = zzflj.zzcym();
                    if (!(zzcym2 == 0 || zzcym2 == 1 || zzcym2 == 2)) {
                        if (zzcym2 != 4) {
                            StringBuilder sb2 = new StringBuilder(51);
                            sb2.append(zzcym2);
                            sb2.append(" is not a valid enum CellularNetworkType");
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    }
                    this.zzbez = Integer.valueOf(zzcym2);
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
        Integer num = this.zzbdh;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        Integer num2 = this.zzbez;
        if (num2 != null) {
            zzflk.zzad(2, num2.intValue());
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
        Integer num2 = this.zzbez;
        return num2 != null ? zzq + zzflk.zzag(2, num2.intValue()) : zzq;
    }
}
