package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbf extends zzflm<zzbf> {
    public Long zzgf = null;
    private String zzgn = null;
    private byte[] zzgo = null;

    public zzbf() {
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzgf = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 26) {
                this.zzgn = zzflj.readString();
            } else if (zzcxx == 34) {
                this.zzgo = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Long l = this.zzgf;
        if (l != null) {
            zzflk.zzf(1, l.longValue());
        }
        String str = this.zzgn;
        if (str != null) {
            zzflk.zzp(3, str);
        }
        byte[] bArr = this.zzgo;
        if (bArr != null) {
            zzflk.zzc(4, bArr);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Long l = this.zzgf;
        if (l != null) {
            zzq += zzflk.zzc(1, l.longValue());
        }
        String str = this.zzgn;
        if (str != null) {
            zzq += zzflk.zzq(3, str);
        }
        byte[] bArr = this.zzgo;
        return bArr != null ? zzq + zzflk.zzd(4, bArr) : zzq;
    }
}
