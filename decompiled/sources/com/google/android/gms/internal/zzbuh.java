package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbuh extends zzflm<zzbuh> {
    public int versionCode = 1;
    public long zzgyd = -1;
    public String zzgyf = "";
    public long zzgyg = -1;
    public int zzgyh = -1;

    public zzbuh() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbuh)) {
            return false;
        }
        zzbuh zzbuh = (zzbuh) obj;
        if (this.versionCode != zzbuh.versionCode) {
            return false;
        }
        String str = this.zzgyf;
        if (str == null) {
            if (zzbuh.zzgyf != null) {
                return false;
            }
        } else if (!str.equals(zzbuh.zzgyf)) {
            return false;
        }
        if (this.zzgyg == zzbuh.zzgyg && this.zzgyd == zzbuh.zzgyd && this.zzgyh == zzbuh.zzgyh) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbuh.zzpvl == null || zzbuh.zzpvl.isEmpty() : this.zzpvl.equals(zzbuh.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.versionCode) * 31;
        String str = this.zzgyf;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j = this.zzgyg;
        long j2 = this.zzgyd;
        int i2 = (((((((hashCode + hashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.zzgyh) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return i2 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.versionCode = zzflj.zzcym();
            } else if (zzcxx == 18) {
                this.zzgyf = zzflj.readString();
            } else if (zzcxx == 24) {
                long zzcyr = zzflj.zzcyr();
                this.zzgyg = (-(zzcyr & 1)) ^ (zzcyr >>> 1);
            } else if (zzcxx == 32) {
                long zzcyr2 = zzflj.zzcyr();
                this.zzgyd = (-(zzcyr2 & 1)) ^ (zzcyr2 >>> 1);
            } else if (zzcxx == 40) {
                this.zzgyh = zzflj.zzcym();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzad(1, this.versionCode);
        zzflk.zzp(2, this.zzgyf);
        zzflk.zzg(3, this.zzgyg);
        zzflk.zzg(4, this.zzgyd);
        int i = this.zzgyh;
        if (i != -1) {
            zzflk.zzad(5, i);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq() + zzflk.zzag(1, this.versionCode) + zzflk.zzq(2, this.zzgyf) + zzflk.zzh(3, this.zzgyg) + zzflk.zzh(4, this.zzgyd);
        int i = this.zzgyh;
        return i != -1 ? zzq + zzflk.zzag(5, i) : zzq;
    }
}
