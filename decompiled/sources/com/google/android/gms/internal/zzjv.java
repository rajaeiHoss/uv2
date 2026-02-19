package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjv extends zzflm<zzjv> {
    public Integer zzbfv = null;
    public Integer zzbfw = null;
    public Integer zzbfx = null;

    public zzjv() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzbfv = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 16) {
                this.zzbfw = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 24) {
                this.zzbfx = Integer.valueOf(zzflj.zzcym());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbfv;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        Integer num2 = this.zzbfw;
        if (num2 != null) {
            zzflk.zzad(2, num2.intValue());
        }
        Integer num3 = this.zzbfx;
        if (num3 != null) {
            zzflk.zzad(3, num3.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbfv;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        Integer num2 = this.zzbfw;
        if (num2 != null) {
            zzq += zzflk.zzag(2, num2.intValue());
        }
        Integer num3 = this.zzbfx;
        return num3 != null ? zzq + zzflk.zzag(3, num3.intValue()) : zzq;
    }
}
