package com.google.android.gms.internal;

import java.io.IOException;

public final class zzke extends zzflm<zzke> {
    private Integer zzbew = null;
    private zzjy zzbgo = null;

    public zzke() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzab */
    public final zzke zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                int position = zzflj.getPosition();
                try {
                    this.zzbew = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 18) {
                if (this.zzbgo == null) {
                    this.zzbgo = new zzjy();
                }
                zzflj.zza(this.zzbgo);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbew;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        zzjy zzjy = this.zzbgo;
        if (zzjy != null) {
            zzflk.zza(2, (zzfls) zzjy);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbew;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        zzjy zzjy = this.zzbgo;
        return zzjy != null ? zzq + zzflk.zzb(2, (zzfls) zzjy) : zzq;
    }
}
