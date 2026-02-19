package com.google.android.gms.internal;

import java.io.IOException;
import org.xbill.DNS.TTL;

public final class zzbm extends zzflm<zzbm> {
    private static volatile zzbm[] zzwl;
    public String key = "";
    public long zzwm = 0;
    public long zzwn = TTL.MAX_VALUE;
    public boolean zzwo = false;
    public long zzwp = 0;

    public zzbm() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzbm[] zzt() {
        if (zzwl == null) {
            synchronized (zzflq.zzpvt) {
                if (zzwl == null) {
                    zzwl = new zzbm[0];
                }
            }
        }
        return zzwl;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbm)) {
            return false;
        }
        zzbm zzbm = (zzbm) obj;
        String str = this.key;
        if (str == null) {
            if (zzbm.key != null) {
                return false;
            }
        } else if (!str.equals(zzbm.key)) {
            return false;
        }
        if (this.zzwm == zzbm.zzwm && this.zzwn == zzbm.zzwn && this.zzwo == zzbm.zzwo && this.zzwp == zzbm.zzwp) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbm.zzpvl == null || zzbm.zzpvl.isEmpty() : this.zzpvl.equals(zzbm.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.key;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j = this.zzwm;
        long j2 = this.zzwn;
        int i2 = (((((hashCode + hashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        int i3 = this.zzwo ? 1231 : 1237;
        long j3 = this.zzwp;
        int i4 = (((i2 + i3) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return i4 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.key = zzflj.readString();
            } else if (zzcxx == 16) {
                this.zzwm = zzflj.zzcyr();
            } else if (zzcxx == 24) {
                this.zzwn = zzflj.zzcyr();
            } else if (zzcxx == 32) {
                this.zzwo = zzflj.zzcyd();
            } else if (zzcxx == 40) {
                this.zzwp = zzflj.zzcyr();
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
        long j = this.zzwm;
        if (j != 0) {
            zzflk.zzf(2, j);
        }
        long j2 = this.zzwn;
        if (j2 != TTL.MAX_VALUE) {
            zzflk.zzf(3, j2);
        }
        boolean z = this.zzwo;
        if (z) {
            zzflk.zzl(4, z);
        }
        long j3 = this.zzwp;
        if (j3 != 0) {
            zzflk.zzf(5, j3);
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
        long j = this.zzwm;
        if (j != 0) {
            zzq += zzflk.zzc(2, j);
        }
        long j2 = this.zzwn;
        if (j2 != TTL.MAX_VALUE) {
            zzq += zzflk.zzc(3, j2);
        }
        if (this.zzwo) {
            zzq += zzflk.zzlw(4) + 1;
        }
        long j3 = this.zzwp;
        return j3 != 0 ? zzq + zzflk.zzc(5, j3) : zzq;
    }
}
