package com.google.android.gms.internal;

import java.io.IOException;

public final class zzflx extends zzflm<zzflx> {
    public String zzdbn = null;

    public zzflx() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzdbn = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzdbn;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzdbn;
        return str != null ? zzq + zzflk.zzq(1, str) : zzq;
    }
}
