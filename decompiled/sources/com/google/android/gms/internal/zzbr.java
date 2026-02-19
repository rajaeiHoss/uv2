package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbr extends zzflm<zzbr> {
    private static volatile zzbr[] zzye;
    public String name = "";
    private zzbt zzyf = null;
    public zzbn zzyg = null;

    public zzbr() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzbr[] zzw() {
        if (zzye == null) {
            synchronized (zzflq.zzpvt) {
                if (zzye == null) {
                    zzye = new zzbr[0];
                }
            }
        }
        return zzye;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbr)) {
            return false;
        }
        zzbr zzbr = (zzbr) obj;
        String str = this.name;
        if (str == null) {
            if (zzbr.name != null) {
                return false;
            }
        } else if (!str.equals(zzbr.name)) {
            return false;
        }
        zzbt zzbt = this.zzyf;
        if (zzbt == null) {
            if (zzbr.zzyf != null) {
                return false;
            }
        } else if (!zzbt.equals(zzbr.zzyf)) {
            return false;
        }
        zzbn zzbn = this.zzyg;
        if (zzbn == null) {
            if (zzbr.zzyg != null) {
                return false;
            }
        } else if (!zzbn.equals(zzbr.zzyg)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbr.zzpvl == null || zzbr.zzpvl.isEmpty() : this.zzpvl.equals(zzbr.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = hashCode + (str == null ? 0 : str.hashCode());
        zzbt zzbt = this.zzyf;
        int hashCode3 = (hashCode2 * 31) + (zzbt == null ? 0 : zzbt.hashCode());
        zzbn zzbn = this.zzyg;
        int hashCode4 = ((hashCode3 * 31) + (zzbn == null ? 0 : zzbn.hashCode())) * 31;
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
            if (zzcxx != 10) {
                if (zzcxx == 18) {
                    if (this.zzyf == null) {
                        this.zzyf = new zzbt();
                    }
                    zzfls = this.zzyf;
                } else if (zzcxx == 26) {
                    if (this.zzyg == null) {
                        this.zzyg = new zzbn();
                    }
                    zzfls = this.zzyg;
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                } else {
                    continue;
                }
                zzflj.zza(zzfls);
            } else {
                this.name = zzflj.readString();
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.name);
        }
        zzbt zzbt = this.zzyf;
        if (zzbt != null) {
            zzflk.zza(2, (zzfls) zzbt);
        }
        zzbn zzbn = this.zzyg;
        if (zzbn != null) {
            zzflk.zza(3, (zzfls) zzbn);
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
        zzbt zzbt = this.zzyf;
        if (zzbt != null) {
            zzq += zzflk.zzb(2, (zzfls) zzbt);
        }
        zzbn zzbn = this.zzyg;
        return zzbn != null ? zzq + zzflk.zzb(3, (zzfls) zzbn) : zzq;
    }
}
