package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcnv extends zzflm<zzcnv> {
    private static volatile zzcnv[] zzjtm;
    public Integer zzjsx = null;
    public String zzjtn = null;
    public zzcnt zzjto = null;

    public zzcnv() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcnv[] zzbcv() {
        if (zzjtm == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjtm == null) {
                    zzjtm = new zzcnv[0];
                }
            }
        }
        return zzjtm;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcnv)) {
            return false;
        }
        zzcnv zzcnv = (zzcnv) obj;
        Integer num = this.zzjsx;
        if (num == null) {
            if (zzcnv.zzjsx != null) {
                return false;
            }
        } else if (!num.equals(zzcnv.zzjsx)) {
            return false;
        }
        String str = this.zzjtn;
        if (str == null) {
            if (zzcnv.zzjtn != null) {
                return false;
            }
        } else if (!str.equals(zzcnv.zzjtn)) {
            return false;
        }
        zzcnt zzcnt = this.zzjto;
        if (zzcnt == null) {
            if (zzcnv.zzjto != null) {
                return false;
            }
        } else if (!zzcnt.equals(zzcnv.zzjto)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcnv.zzpvl == null || zzcnv.zzpvl.isEmpty() : this.zzpvl.equals(zzcnv.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzjsx;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.zzjtn;
        int hashCode3 = hashCode2 + (str == null ? 0 : str.hashCode());
        zzcnt zzcnt = this.zzjto;
        int hashCode4 = ((hashCode3 * 31) + (zzcnt == null ? 0 : zzcnt.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode4 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzjsx = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 18) {
                this.zzjtn = zzflj.readString();
            } else if (zzcxx == 26) {
                if (this.zzjto == null) {
                    this.zzjto = new zzcnt();
                }
                zzflj.zza(this.zzjto);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzjsx;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        String str = this.zzjtn;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        zzcnt zzcnt = this.zzjto;
        if (zzcnt != null) {
            zzflk.zza(3, (zzfls) zzcnt);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzjsx;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        String str = this.zzjtn;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        zzcnt zzcnt = this.zzjto;
        return zzcnt != null ? zzq + zzflk.zzb(3, (zzfls) zzcnt) : zzq;
    }
}
