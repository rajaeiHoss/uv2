package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfly extends zzflm<zzfly> {
    private static volatile zzfly[] zzpwt;
    public byte[] zzosz = null;
    public byte[] zzpwu = null;

    public zzfly() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzfly[] zzdcs() {
        if (zzpwt == null) {
            synchronized (zzflq.zzpvt) {
                if (zzpwt == null) {
                    zzpwt = new zzfly[0];
                }
            }
        }
        return zzpwt;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.zzpwu = zzflj.readBytes();
            } else if (zzcxx == 18) {
                this.zzosz = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzc(1, this.zzpwu);
        byte[] bArr = this.zzosz;
        if (bArr != null) {
            zzflk.zzc(2, bArr);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq() + zzflk.zzd(1, this.zzpwu);
        byte[] bArr = this.zzosz;
        return bArr != null ? zzq + zzflk.zzd(2, bArr) : zzq;
    }
}
