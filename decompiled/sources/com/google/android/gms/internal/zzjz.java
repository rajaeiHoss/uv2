package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjz extends zzflm<zzjz> {
    private Integer zzbew = null;
    private zzjy zzbgo = null;
    private zzju zzbgp = null;
    private zzjv zzbgr = null;

    public zzjz() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzw */
    public final zzjz zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                if (this.zzbgr == null) {
                    this.zzbgr = new zzjv();
                }
                zzfls = this.zzbgr;
            } else if (zzcxx == 16) {
                int position = zzflj.getPosition();
                try {
                    this.zzbew = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
                continue;
            } else if (zzcxx == 26) {
                if (this.zzbgo == null) {
                    this.zzbgo = new zzjy();
                }
                zzfls = this.zzbgo;
            } else if (zzcxx == 34) {
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
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzjv zzjv = this.zzbgr;
        if (zzjv != null) {
            zzflk.zza(1, (zzfls) zzjv);
        }
        Integer num = this.zzbew;
        if (num != null) {
            zzflk.zzad(2, num.intValue());
        }
        zzjy zzjy = this.zzbgo;
        if (zzjy != null) {
            zzflk.zza(3, (zzfls) zzjy);
        }
        zzju zzju = this.zzbgp;
        if (zzju != null) {
            zzflk.zza(4, (zzfls) zzju);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzjv zzjv = this.zzbgr;
        if (zzjv != null) {
            zzq += zzflk.zzb(1, (zzfls) zzjv);
        }
        Integer num = this.zzbew;
        if (num != null) {
            zzq += zzflk.zzag(2, num.intValue());
        }
        zzjy zzjy = this.zzbgo;
        if (zzjy != null) {
            zzq += zzflk.zzb(3, (zzfls) zzjy);
        }
        zzju zzju = this.zzbgp;
        return zzju != null ? zzq + zzflk.zzb(4, (zzfls) zzju) : zzq;
    }
}
