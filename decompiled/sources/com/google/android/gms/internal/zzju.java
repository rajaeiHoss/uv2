package com.google.android.gms.internal;

import java.io.IOException;

public final class zzju extends zzflm<zzju> {
    private static volatile zzju[] zzbfs;
    private Integer zzbft = null;
    private Integer zzbfu = null;

    public zzju() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzju[] zzhx() {
        if (zzbfs == null) {
            synchronized (zzflq.zzpvt) {
                if (zzbfs == null) {
                    zzbfs = new zzju[0];
                }
            }
        }
        return zzbfs;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzbft = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 16) {
                this.zzbfu = Integer.valueOf(zzflj.zzcym());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbft;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        Integer num2 = this.zzbfu;
        if (num2 != null) {
            zzflk.zzad(2, num2.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbft;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        Integer num2 = this.zzbfu;
        return num2 != null ? zzq + zzflk.zzag(2, num2.intValue()) : zzq;
    }
}
