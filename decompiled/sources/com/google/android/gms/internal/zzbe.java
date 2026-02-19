package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbe extends zzflm<zzbe> {
    public byte[] data = null;
    public byte[] zzgk = null;
    public byte[] zzgl = null;
    public byte[] zzgm = null;

    public zzbe() {
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.data = zzflj.readBytes();
            } else if (zzcxx == 18) {
                this.zzgk = zzflj.readBytes();
            } else if (zzcxx == 26) {
                this.zzgl = zzflj.readBytes();
            } else if (zzcxx == 34) {
                this.zzgm = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        byte[] bArr = this.data;
        if (bArr != null) {
            zzflk.zzc(1, bArr);
        }
        byte[] bArr2 = this.zzgk;
        if (bArr2 != null) {
            zzflk.zzc(2, bArr2);
        }
        byte[] bArr3 = this.zzgl;
        if (bArr3 != null) {
            zzflk.zzc(3, bArr3);
        }
        byte[] bArr4 = this.zzgm;
        if (bArr4 != null) {
            zzflk.zzc(4, bArr4);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        byte[] bArr = this.data;
        if (bArr != null) {
            zzq += zzflk.zzd(1, bArr);
        }
        byte[] bArr2 = this.zzgk;
        if (bArr2 != null) {
            zzq += zzflk.zzd(2, bArr2);
        }
        byte[] bArr3 = this.zzgl;
        if (bArr3 != null) {
            zzq += zzflk.zzd(3, bArr3);
        }
        byte[] bArr4 = this.zzgm;
        return bArr4 != null ? zzq + zzflk.zzd(4, bArr4) : zzq;
    }
}
