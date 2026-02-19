package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfau extends zzflm<zzfau> {
    private static volatile zzfau[] zzosy;
    public String key = "";
    public byte[] zzosz = zzflv.zzpwe;

    public zzfau() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzfau[] zzcnq() {
        if (zzosy == null) {
            synchronized (zzflq.zzpvt) {
                if (zzosy == null) {
                    zzosy = new zzfau[0];
                }
            }
        }
        return zzosy;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfau)) {
            return false;
        }
        zzfau zzfau = (zzfau) obj;
        String str = this.key;
        if (str == null) {
            if (zzfau.key != null) {
                return false;
            }
        } else if (!str.equals(zzfau.key)) {
            return false;
        }
        if (!Arrays.equals(this.zzosz, zzfau.zzosz)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfau.zzpvl == null || zzfau.zzpvl.isEmpty() : this.zzpvl.equals(zzfau.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.key;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Arrays.hashCode(this.zzosz)) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
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
                this.zzosz = zzflj.readBytes();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.key;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.key);
        }
        if (!Arrays.equals(this.zzosz, zzflv.zzpwe)) {
            zzflk.zzc(2, this.zzosz);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.key;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.key);
        }
        return !Arrays.equals(this.zzosz, zzflv.zzpwe) ? zzq + zzflk.zzd(2, this.zzosz) : zzq;
    }
}
