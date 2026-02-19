package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcdl extends zzflm<zzcdl> {
    private static volatile zzcdl[] zzilf;
    public String name = "";
    public zzcdn zzilg = null;

    public zzcdl() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcdl[] zzawm() {
        if (zzilf == null) {
            synchronized (zzflq.zzpvt) {
                if (zzilf == null) {
                    zzilf = new zzcdl[0];
                }
            }
        }
        return zzilf;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcdl)) {
            return false;
        }
        zzcdl zzcdl = (zzcdl) obj;
        String str = this.name;
        if (str == null) {
            if (zzcdl.name != null) {
                return false;
            }
        } else if (!str.equals(zzcdl.name)) {
            return false;
        }
        zzcdn zzcdn = this.zzilg;
        if (zzcdn == null) {
            if (zzcdl.zzilg != null) {
                return false;
            }
        } else if (!zzcdn.equals(zzcdl.zzilg)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcdl.zzpvl == null || zzcdl.zzpvl.isEmpty() : this.zzpvl.equals(zzcdl.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = hashCode + (str == null ? 0 : str.hashCode());
        zzcdn zzcdn = this.zzilg;
        int hashCode3 = ((hashCode2 * 31) + (zzcdn == null ? 0 : zzcdn.hashCode())) * 31;
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
            if (zzcxx == 10) {
                this.name = zzflj.readString();
            } else if (zzcxx == 18) {
                if (this.zzilg == null) {
                    this.zzilg = new zzcdn();
                }
                zzflj.zza(this.zzilg);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.name);
        }
        zzcdn zzcdn = this.zzilg;
        if (zzcdn != null) {
            zzflk.zza(2, (zzfls) zzcdn);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.name);
        }
        zzcdn zzcdn = this.zzilg;
        return zzcdn != null ? zzq + zzflk.zzb(2, (zzfls) zzcdn) : zzq;
    }
}
