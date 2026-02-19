package com.google.android.gms.internal;

import java.io.IOException;

public final class zzkd extends zzflm<zzkd> {
    private Integer zzbew = null;
    private zzjy zzbgo = null;
    private zzju zzbgp = null;

    public zzkd() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaa */
    public final zzkd zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx != 8) {
                if (zzcxx == 18) {
                    if (this.zzbgo == null) {
                        this.zzbgo = new zzjy();
                    }
                    zzfls = this.zzbgo;
                } else if (zzcxx == 26) {
                    if (this.zzbgp == null) {
                        this.zzbgp = new zzju();
                    }
                    zzfls = this.zzbgp;
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                } else {
                    continue;
                }
                zzflj.zza(zzfls);
            } else {
                int position = zzflj.getPosition();
                try {
                    this.zzbew = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
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
        zzju zzju = this.zzbgp;
        if (zzju != null) {
            zzflk.zza(3, (zzfls) zzju);
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
        if (zzjy != null) {
            zzq += zzflk.zzb(2, (zzfls) zzjy);
        }
        zzju zzju = this.zzbgp;
        return zzju != null ? zzq + zzflk.zzb(3, (zzfls) zzju) : zzq;
    }
}
