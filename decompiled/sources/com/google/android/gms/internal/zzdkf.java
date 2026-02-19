package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdkf extends zzflm<zzdkf> {
    public long zzldl = 0;
    public zzbs zzldm = null;
    public zzbp zzyi = null;

    public zzdkf() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdkf)) {
            return false;
        }
        zzdkf zzdkf = (zzdkf) obj;
        if (this.zzldl != zzdkf.zzldl) {
            return false;
        }
        zzbp zzbp = this.zzyi;
        if (zzbp == null) {
            if (zzdkf.zzyi != null) {
                return false;
            }
        } else if (!zzbp.equals(zzdkf.zzyi)) {
            return false;
        }
        zzbs zzbs = this.zzldm;
        if (zzbs == null) {
            if (zzdkf.zzldm != null) {
                return false;
            }
        } else if (!zzbs.equals(zzdkf.zzldm)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzdkf.zzpvl == null || zzdkf.zzpvl.isEmpty() : this.zzpvl.equals(zzdkf.zzpvl);
    }

    public final int hashCode() {
        long j = this.zzldl;
        zzbp zzbp = this.zzyi;
        int i = 0;
        int hashCode = ((((getClass().getName().hashCode() + 527) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + (zzbp == null ? 0 : zzbp.hashCode());
        zzbs zzbs = this.zzldm;
        int hashCode2 = ((hashCode * 31) + (zzbs == null ? 0 : zzbs.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx != 8) {
                if (zzcxx == 18) {
                    if (this.zzyi == null) {
                        this.zzyi = new zzbp();
                    }
                    zzfls = this.zzyi;
                } else if (zzcxx == 26) {
                    if (this.zzldm == null) {
                        this.zzldm = new zzbs();
                    }
                    zzfls = this.zzldm;
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                } else {
                    continue;
                }
                zzflj.zza(zzfls);
            } else {
                this.zzldl = zzflj.zzcyr();
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzf(1, this.zzldl);
        zzbp zzbp = this.zzyi;
        if (zzbp != null) {
            zzflk.zza(2, (zzfls) zzbp);
        }
        zzbs zzbs = this.zzldm;
        if (zzbs != null) {
            zzflk.zza(3, (zzfls) zzbs);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq() + zzflk.zzc(1, this.zzldl);
        zzbp zzbp = this.zzyi;
        if (zzbp != null) {
            zzq += zzflk.zzb(2, (zzfls) zzbp);
        }
        zzbs zzbs = this.zzldm;
        return zzbs != null ? zzq + zzflk.zzb(3, (zzfls) zzbs) : zzq;
    }
}
