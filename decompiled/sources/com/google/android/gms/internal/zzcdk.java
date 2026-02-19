package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcdk extends zzflm<zzcdk> {
    private static volatile zzcdk[] zzilc;
    public int viewId = 0;
    public String zzild = "";
    public String zzile = "";

    public zzcdk() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcdk[] zzawl() {
        if (zzilc == null) {
            synchronized (zzflq.zzpvt) {
                if (zzilc == null) {
                    zzilc = new zzcdk[0];
                }
            }
        }
        return zzilc;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcdk)) {
            return false;
        }
        zzcdk zzcdk = (zzcdk) obj;
        String str = this.zzild;
        if (str == null) {
            if (zzcdk.zzild != null) {
                return false;
            }
        } else if (!str.equals(zzcdk.zzild)) {
            return false;
        }
        String str2 = this.zzile;
        if (str2 == null) {
            if (zzcdk.zzile != null) {
                return false;
            }
        } else if (!str2.equals(zzcdk.zzile)) {
            return false;
        }
        if (this.viewId != zzcdk.viewId) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcdk.zzpvl == null || zzcdk.zzpvl.isEmpty() : this.zzpvl.equals(zzcdk.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzild;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzile;
        int hashCode3 = (((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.viewId) * 31;
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
                this.zzild = zzflj.readString();
            } else if (zzcxx == 18) {
                this.zzile = zzflj.readString();
            } else if (zzcxx == 24) {
                this.viewId = zzflj.zzcym();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.zzild;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.zzild);
        }
        String str2 = this.zzile;
        if (str2 != null && !str2.equals("")) {
            zzflk.zzp(2, this.zzile);
        }
        int i = this.viewId;
        if (i != 0) {
            zzflk.zzad(3, i);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.zzild;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.zzild);
        }
        String str2 = this.zzile;
        if (str2 != null && !str2.equals("")) {
            zzq += zzflk.zzq(2, this.zzile);
        }
        int i = this.viewId;
        return i != 0 ? zzq + zzflk.zzag(3, i) : zzq;
    }
}
