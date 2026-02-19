package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmc extends zzflm<zzfmc> {
    private byte[] zzpxb = null;
    private Integer zzpxe = null;
    private byte[] zzpxf = null;

    public zzfmc() {
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
                this.zzpxe = Integer.valueOf(zzflj.zzcya());
            } else if (zzcxx == 18) {
                this.zzpxf = zzflj.readBytes();
            } else if (zzcxx == 26) {
                this.zzpxb = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzpxe;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        byte[] bArr = this.zzpxf;
        if (bArr != null) {
            zzflk.zzc(2, bArr);
        }
        byte[] bArr2 = this.zzpxb;
        if (bArr2 != null) {
            zzflk.zzc(3, bArr2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzpxe;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        byte[] bArr = this.zzpxf;
        if (bArr != null) {
            zzq += zzflk.zzd(2, bArr);
        }
        byte[] bArr2 = this.zzpxb;
        return bArr2 != null ? zzq + zzflk.zzd(3, bArr2) : zzq;
    }
}
