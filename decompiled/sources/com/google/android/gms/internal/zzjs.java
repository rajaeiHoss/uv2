package com.google.android.gms.internal;

import java.io.IOException;

public final class zzjs extends zzflm<zzjs> {
    private Integer zzbfp = null;
    private Integer zzbfq = null;

    public zzjs() {
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
                this.zzbfp = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 16) {
                this.zzbfq = Integer.valueOf(zzflj.zzcym());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzbfp;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        Integer num2 = this.zzbfq;
        if (num2 != null) {
            zzflk.zzad(2, num2.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzbfp;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        Integer num2 = this.zzbfq;
        return num2 != null ? zzq + zzflk.zzag(2, num2.intValue()) : zzq;
    }
}
