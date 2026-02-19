package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcnz extends zzflm<zzcnz> {
    private static volatile zzcnz[] zzjuc;
    public String key = null;
    public String value = null;

    public zzcnz() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcnz[] zzbcx() {
        if (zzjuc == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjuc == null) {
                    zzjuc = new zzcnz[0];
                }
            }
        }
        return zzjuc;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcnz)) {
            return false;
        }
        zzcnz zzcnz = (zzcnz) obj;
        String str = this.key;
        if (str == null) {
            if (zzcnz.key != null) {
                return false;
            }
        } else if (!str.equals(zzcnz.key)) {
            return false;
        }
        String str2 = this.value;
        if (str2 == null) {
            if (zzcnz.value != null) {
                return false;
            }
        } else if (!str2.equals(zzcnz.value)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcnz.zzpvl == null || zzcnz.zzpvl.isEmpty() : this.zzpvl.equals(zzcnz.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.key;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.value;
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
            if (zzcxx == 10) {
                this.key = zzflj.readString();
            } else if (zzcxx == 18) {
                this.value = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.key;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        String str2 = this.value;
        if (str2 != null) {
            zzflk.zzp(2, str2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.key;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        String str2 = this.value;
        return str2 != null ? zzq + zzflk.zzq(2, str2) : zzq;
    }
}
