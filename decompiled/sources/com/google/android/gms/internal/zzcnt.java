package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcnt extends zzflm<zzcnt> {
    private static volatile zzcnt[] zzjtc;
    public zzcnw zzjtd = null;
    public zzcnu zzjte = null;
    public Boolean zzjtf = null;
    public String zzjtg = null;

    public zzcnt() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcnt[] zzbcu() {
        if (zzjtc == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjtc == null) {
                    zzjtc = new zzcnt[0];
                }
            }
        }
        return zzjtc;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcnt)) {
            return false;
        }
        zzcnt zzcnt = (zzcnt) obj;
        zzcnw zzcnw = this.zzjtd;
        if (zzcnw == null) {
            if (zzcnt.zzjtd != null) {
                return false;
            }
        } else if (!zzcnw.equals(zzcnt.zzjtd)) {
            return false;
        }
        zzcnu zzcnu = this.zzjte;
        if (zzcnu == null) {
            if (zzcnt.zzjte != null) {
                return false;
            }
        } else if (!zzcnu.equals(zzcnt.zzjte)) {
            return false;
        }
        Boolean bool = this.zzjtf;
        if (bool == null) {
            if (zzcnt.zzjtf != null) {
                return false;
            }
        } else if (!bool.equals(zzcnt.zzjtf)) {
            return false;
        }
        String str = this.zzjtg;
        if (str == null) {
            if (zzcnt.zzjtg != null) {
                return false;
            }
        } else if (!str.equals(zzcnt.zzjtg)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcnt.zzpvl == null || zzcnt.zzpvl.isEmpty() : this.zzpvl.equals(zzcnt.zzpvl);
    }

    public final int hashCode() {
        zzcnw zzcnw = this.zzjtd;
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + (zzcnw == null ? 0 : zzcnw.hashCode());
        zzcnu zzcnu = this.zzjte;
        int hashCode2 = ((hashCode * 31) + (zzcnu == null ? 0 : zzcnu.hashCode())) * 31;
        Boolean bool = this.zzjtf;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.zzjtg;
        int hashCode4 = (hashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode4 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        zzfls zzfls;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                if (this.zzjtd == null) {
                    this.zzjtd = new zzcnw();
                }
                zzfls = this.zzjtd;
                zzflj.zza(zzfls);
            } else if (zzcxx == 18) {
                if (this.zzjte == null) {
                    this.zzjte = new zzcnu();
                }
                zzfls = this.zzjte;
                zzflj.zza(zzfls);
            } else if (zzcxx == 24) {
                this.zzjtf = Boolean.valueOf(zzflj.zzcyd());
            } else if (zzcxx == 34) {
                this.zzjtg = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzcnw zzcnw = this.zzjtd;
        if (zzcnw != null) {
            zzflk.zza(1, (zzfls) zzcnw);
        }
        zzcnu zzcnu = this.zzjte;
        if (zzcnu != null) {
            zzflk.zza(2, (zzfls) zzcnu);
        }
        Boolean bool = this.zzjtf;
        if (bool != null) {
            zzflk.zzl(3, bool.booleanValue());
        }
        String str = this.zzjtg;
        if (str != null) {
            zzflk.zzp(4, str);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzcnw zzcnw = this.zzjtd;
        if (zzcnw != null) {
            zzq += zzflk.zzb(1, (zzfls) zzcnw);
        }
        zzcnu zzcnu = this.zzjte;
        if (zzcnu != null) {
            zzq += zzflk.zzb(2, (zzfls) zzcnu);
        }
        Boolean bool = this.zzjtf;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(3) + 1;
        }
        String str = this.zzjtg;
        return str != null ? zzq + zzflk.zzq(4, str) : zzq;
    }
}
