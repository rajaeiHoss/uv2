package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjp extends zzflm<zzjp> {
    private Integer zzbfa = null;
    private zzju zzbfb = null;

    public zzjp() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzr */
    public final zzjp zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                int position = zzflj.getPosition();
                try {
                    this.zzbfa = Integer.valueOf(zzjc.zzd(zzflj.zzcym()));
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 18) {
                if (this.zzbfb == null) {
                    this.zzbfb = new zzju();
                }
                zzflj.zza(this.zzbfb);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbfa;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        zzju zzju = this.zzbfb;
        if (zzju != null) {
            zzflk.zza(2, (zzfls) zzju);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbfa;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        zzju zzju = this.zzbfb;
        return zzju != null ? zzq + zzflk.zzb(2, (zzfls) zzju) : zzq;
    }
}
