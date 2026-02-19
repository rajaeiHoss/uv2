package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbug extends zzflm<zzbug> {
    public long sequenceNumber = -1;
    public int versionCode = 1;
    public long zzgyd = -1;
    public long zzgye = -1;

    public zzbug() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbug)) {
            return false;
        }
        zzbug zzbug = (zzbug) obj;
        if (this.versionCode == zzbug.versionCode && this.sequenceNumber == zzbug.sequenceNumber && this.zzgyd == zzbug.zzgyd && this.zzgye == zzbug.zzgye) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbug.zzpvl == null || zzbug.zzpvl.isEmpty() : this.zzpvl.equals(zzbug.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.sequenceNumber;
        long j2 = this.zzgyd;
        long j3 = this.zzgye;
        return ((((((((((getClass().getName().hashCode() + 527) * 31) + this.versionCode) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.versionCode = zzflj.zzcym();
            } else if (zzcxx == 16) {
                long zzcyr = zzflj.zzcyr();
                this.sequenceNumber = (-(zzcyr & 1)) ^ (zzcyr >>> 1);
            } else if (zzcxx == 24) {
                long zzcyr2 = zzflj.zzcyr();
                this.zzgyd = (-(zzcyr2 & 1)) ^ (zzcyr2 >>> 1);
            } else if (zzcxx == 32) {
                long zzcyr3 = zzflj.zzcyr();
                this.zzgye = (-(zzcyr3 & 1)) ^ (zzcyr3 >>> 1);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzad(1, this.versionCode);
        zzflk.zzg(2, this.sequenceNumber);
        zzflk.zzg(3, this.zzgyd);
        zzflk.zzg(4, this.zzgye);
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        return super.zzq() + zzflk.zzag(1, this.versionCode) + zzflk.zzh(2, this.sequenceNumber) + zzflk.zzh(3, this.zzgyd) + zzflk.zzh(4, this.zzgye);
    }
}
