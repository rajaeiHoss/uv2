package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfma extends zzflm<zzfma> {
    private byte[] zzpwz = null;
    private byte[] zzpxa = null;
    private byte[] zzpxb = null;

    public zzfma() {
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
                this.zzpwz = zzflj.readBytes();
            } else if (zzcxx == 18) {
                this.zzpxa = zzflj.readBytes();
            } else if (zzcxx == 26) {
                this.zzpxb = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        byte[] bArr = this.zzpwz;
        if (bArr != null) {
            zzflk.zzc(1, bArr);
        }
        byte[] bArr2 = this.zzpxa;
        if (bArr2 != null) {
            zzflk.zzc(2, bArr2);
        }
        byte[] bArr3 = this.zzpxb;
        if (bArr3 != null) {
            zzflk.zzc(3, bArr3);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        byte[] bArr = this.zzpwz;
        if (bArr != null) {
            zzq += zzflk.zzd(1, bArr);
        }
        byte[] bArr2 = this.zzpxa;
        if (bArr2 != null) {
            zzq += zzflk.zzd(2, bArr2);
        }
        byte[] bArr3 = this.zzpxb;
        return bArr3 != null ? zzq + zzflk.zzd(3, bArr3) : zzq;
    }
}
