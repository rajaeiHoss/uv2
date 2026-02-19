package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfav extends zzflm<zzfav> {
    public int zzota = 0;
    public boolean zzotb = false;
    public long zzotc = 0;

    public zzfav() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfav)) {
            return false;
        }
        zzfav zzfav = (zzfav) obj;
        if (this.zzota == zzfav.zzota && this.zzotb == zzfav.zzotb && this.zzotc == zzfav.zzotc) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfav.zzpvl == null || zzfav.zzpvl.isEmpty() : this.zzpvl.equals(zzfav.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.zzota) * 31;
        int i = this.zzotb ? 1231 : 1237;
        long j = this.zzotc;
        return ((((hashCode + i) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzota = zzflj.zzcym();
            } else if (zzcxx == 16) {
                this.zzotb = zzflj.zzcyd();
            } else if (zzcxx == 25) {
                this.zzotc = zzflj.zzcyt();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzota;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        boolean z = this.zzotb;
        if (z) {
            zzflk.zzl(2, z);
        }
        long j = this.zzotc;
        if (j != 0) {
            zzflk.zzb(3, j);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.zzota;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        if (this.zzotb) {
            zzq += zzflk.zzlw(2) + 1;
        }
        return this.zzotc != 0 ? zzq + zzflk.zzlw(3) + 8 : zzq;
    }
}
