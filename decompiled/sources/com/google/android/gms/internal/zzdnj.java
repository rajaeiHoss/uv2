package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdnj extends zzflm<zzdnj> {
    private static volatile zzdnj[] zzlwn;
    public String name = "";
    public zzdnk zzlwo = null;

    public zzdnj() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzdnj[] zzbmb() {
        if (zzlwn == null) {
            synchronized (zzflq.zzpvt) {
                if (zzlwn == null) {
                    zzlwn = new zzdnj[0];
                }
            }
        }
        return zzlwn;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdnj)) {
            return false;
        }
        zzdnj zzdnj = (zzdnj) obj;
        String str = this.name;
        if (str == null) {
            if (zzdnj.name != null) {
                return false;
            }
        } else if (!str.equals(zzdnj.name)) {
            return false;
        }
        zzdnk zzdnk = this.zzlwo;
        if (zzdnk == null) {
            if (zzdnj.zzlwo != null) {
                return false;
            }
        } else if (!zzdnk.equals(zzdnj.zzlwo)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzdnj.zzpvl == null || zzdnj.zzpvl.isEmpty() : this.zzpvl.equals(zzdnj.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = hashCode + (str == null ? 0 : str.hashCode());
        zzdnk zzdnk = this.zzlwo;
        int hashCode3 = ((hashCode2 * 31) + (zzdnk == null ? 0 : zzdnk.hashCode())) * 31;
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
                if (this.zzlwo == null) {
                    this.zzlwo = new zzdnk();
                }
                zzflj.zza(this.zzlwo);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzflk.zzp(1, this.name);
        zzdnk zzdnk = this.zzlwo;
        if (zzdnk != null) {
            zzflk.zza(2, (zzfls) zzdnk);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq() + zzflk.zzq(1, this.name);
        zzdnk zzdnk = this.zzlwo;
        return zzdnk != null ? zzq + zzflk.zzb(2, (zzfls) zzdnk) : zzq;
    }
}
