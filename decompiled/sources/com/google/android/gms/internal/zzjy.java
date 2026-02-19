package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjy extends zzflm<zzjy> {
    private Integer zzbgq = null;

    public zzjy() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzv */
    public final zzjy zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1 || zzcym == 2)) {
                        if (zzcym != 3) {
                            StringBuilder sb = new StringBuilder(46);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum VideoErrorCode");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzbgq = Integer.valueOf(zzcym);
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbgq;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbgq;
        return num != null ? zzq + zzflk.zzag(1, num.intValue()) : zzq;
    }
}
