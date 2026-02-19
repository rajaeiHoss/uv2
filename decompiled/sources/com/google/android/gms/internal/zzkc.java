package com.google.android.gms.internal;

import java.io.IOException;

public final class zzkc extends zzflm<zzkc> {
    private Integer zzbew = null;
    private Integer zzbgg = null;
    private Integer zzbgh = null;
    private zzjy zzbgo = null;
    private Integer zzbgs = null;

    public zzkc() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzz */
    public final zzkc zza(zzflj zzflj) throws IOException {
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
            } else if (zzcxx == 24) {
                this.zzbgg = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 32) {
                this.zzbgh = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 40) {
                this.zzbgs = Integer.valueOf(zzflj.zzcym());
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
        Integer num2 = this.zzbgg;
        if (num2 != null) {
            zzflk.zzad(3, num2.intValue());
        }
        Integer num3 = this.zzbgh;
        if (num3 != null) {
            zzflk.zzad(4, num3.intValue());
        }
        Integer num4 = this.zzbgs;
        if (num4 != null) {
            zzflk.zzad(5, num4.intValue());
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
        Integer num2 = this.zzbgg;
        if (num2 != null) {
            zzq += zzflk.zzag(3, num2.intValue());
        }
        Integer num3 = this.zzbgh;
        if (num3 != null) {
            zzq += zzflk.zzag(4, num3.intValue());
        }
        Integer num4 = this.zzbgs;
        return num4 != null ? zzq + zzflk.zzag(5, num4.intValue()) : zzq;
    }
}
