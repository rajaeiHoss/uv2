package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbui extends zzflm<zzbui> {
    public long zzgyd = -1;
    public long zzgyg = -1;

    public zzbui() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbui)) {
            return false;
        }
        zzbui zzbui = (zzbui) obj;
        if (this.zzgyg == zzbui.zzgyg && this.zzgyd == zzbui.zzgyd) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbui.zzpvl == null || zzbui.zzpvl.isEmpty() : this.zzpvl.equals(zzbui.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzgyg;
        long j2 = this.zzgyd;
        return ((((((getClass().getName().hashCode() + 527) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                long zzcyr = zzflj.zzcyr();
                this.zzgyg = (-(zzcyr & 1)) ^ (zzcyr >>> 1);
            } else if (zzcxx == 16) {
                long zzcyr2 = zzflj.zzcyr();
                this.zzgyd = (-(zzcyr2 & 1)) ^ (zzcyr2 >>> 1);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzg(1, this.zzgyg);
        zzflk.zzg(2, this.zzgyd);
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        return super.zzq() + zzflk.zzh(1, this.zzgyg) + zzflk.zzh(2, this.zzgyd);
    }
}
