package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffm extends zzflm<zzffm> {
    private String moduleId = "";
    public int version = 0;
    private long zzplx = 0;
    private long zzply = 0;
    private String zzplz = "";

    public zzffm() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffm)) {
            return false;
        }
        zzffm zzffm = (zzffm) obj;
        if (this.zzplx != zzffm.zzplx || this.zzply != zzffm.zzply || this.version != zzffm.version) {
            return false;
        }
        String str = this.zzplz;
        if (str == null) {
            if (zzffm.zzplz != null) {
                return false;
            }
        } else if (!str.equals(zzffm.zzplz)) {
            return false;
        }
        String str2 = this.moduleId;
        if (str2 == null) {
            if (zzffm.moduleId != null) {
                return false;
            }
        } else if (!str2.equals(zzffm.moduleId)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffm.zzpvl == null || zzffm.zzpvl.isEmpty() : this.zzpvl.equals(zzffm.zzpvl);
    }

    public final int hashCode() {
        long j = this.zzplx;
        long j2 = this.zzply;
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.version) * 31;
        String str = this.zzplz;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.moduleId;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode3 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzplx = zzflj.zzcyr();
            } else if (zzcxx == 16) {
                this.zzply = zzflj.zzcyr();
            } else if (zzcxx == 24) {
                this.version = zzflj.zzcym();
            } else if (zzcxx == 34) {
                this.zzplz = zzflj.readString();
            } else if (zzcxx == 42) {
                this.moduleId = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        long j = this.zzplx;
        if (j != 0) {
            zzflk.zzf(1, j);
        }
        long j2 = this.zzply;
        if (j2 != 0) {
            zzflk.zzf(2, j2);
        }
        int i = this.version;
        if (i != 0) {
            zzflk.zzad(3, i);
        }
        String str = this.zzplz;
        if (str != null && !str.equals("")) {
            zzflk.zzp(4, this.zzplz);
        }
        String str2 = this.moduleId;
        if (str2 != null && !str2.equals("")) {
            zzflk.zzp(5, this.moduleId);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        long j = this.zzplx;
        if (j != 0) {
            zzq += zzflk.zzc(1, j);
        }
        long j2 = this.zzply;
        if (j2 != 0) {
            zzq += zzflk.zzc(2, j2);
        }
        int i = this.version;
        if (i != 0) {
            zzq += zzflk.zzag(3, i);
        }
        String str = this.zzplz;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(4, this.zzplz);
        }
        String str2 = this.moduleId;
        return (str2 == null || str2.equals("")) ? zzq : zzq + zzflk.zzq(5, this.moduleId);
    }
}
