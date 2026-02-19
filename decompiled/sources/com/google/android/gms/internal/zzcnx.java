package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcnx extends zzflm<zzcnx> {
    private static volatile zzcnx[] zzjtt;
    public String name = null;
    public Boolean zzjtu = null;
    public Boolean zzjtv = null;
    public Integer zzjtw = null;

    public zzcnx() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcnx[] zzbcw() {
        if (zzjtt == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjtt == null) {
                    zzjtt = new zzcnx[0];
                }
            }
        }
        return zzjtt;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcnx)) {
            return false;
        }
        zzcnx zzcnx = (zzcnx) obj;
        String str = this.name;
        if (str == null) {
            if (zzcnx.name != null) {
                return false;
            }
        } else if (!str.equals(zzcnx.name)) {
            return false;
        }
        Boolean bool = this.zzjtu;
        if (bool == null) {
            if (zzcnx.zzjtu != null) {
                return false;
            }
        } else if (!bool.equals(zzcnx.zzjtu)) {
            return false;
        }
        Boolean bool2 = this.zzjtv;
        if (bool2 == null) {
            if (zzcnx.zzjtv != null) {
                return false;
            }
        } else if (!bool2.equals(zzcnx.zzjtv)) {
            return false;
        }
        Integer num = this.zzjtw;
        if (num == null) {
            if (zzcnx.zzjtw != null) {
                return false;
            }
        } else if (!num.equals(zzcnx.zzjtw)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcnx.zzpvl == null || zzcnx.zzpvl.isEmpty() : this.zzpvl.equals(zzcnx.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.zzjtu;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.zzjtv;
        int hashCode4 = (hashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num = this.zzjtw;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode5 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.name = zzflj.readString();
            } else if (zzcxx == 16) {
                this.zzjtu = Boolean.valueOf(zzflj.zzcyd());
            } else if (zzcxx == 24) {
                this.zzjtv = Boolean.valueOf(zzflj.zzcyd());
            } else if (zzcxx == 32) {
                this.zzjtw = Integer.valueOf(zzflj.zzcym());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.name;
        if (str != null) {
            zzflk.zzp(1, str);
        }
        Boolean bool = this.zzjtu;
        if (bool != null) {
            zzflk.zzl(2, bool.booleanValue());
        }
        Boolean bool2 = this.zzjtv;
        if (bool2 != null) {
            zzflk.zzl(3, bool2.booleanValue());
        }
        Integer num = this.zzjtw;
        if (num != null) {
            zzflk.zzad(4, num.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.name;
        if (str != null) {
            zzq += zzflk.zzq(1, str);
        }
        Boolean bool = this.zzjtu;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(2) + 1;
        }
        Boolean bool2 = this.zzjtv;
        if (bool2 != null) {
            bool2.booleanValue();
            zzq += zzflk.zzlw(3) + 1;
        }
        Integer num = this.zzjtw;
        return num != null ? zzq + zzflk.zzag(4, num.intValue()) : zzq;
    }
}
