package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcoa extends zzflm<zzcoa> {
    private static volatile zzcoa[] zzjud;
    public Integer zzjst = null;
    public zzcof zzjue = null;
    public zzcof zzjuf = null;
    public Boolean zzjug = null;

    public zzcoa() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcoa[] zzbcy() {
        if (zzjud == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjud == null) {
                    zzjud = new zzcoa[0];
                }
            }
        }
        return zzjud;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcoa)) {
            return false;
        }
        zzcoa zzcoa = (zzcoa) obj;
        Integer num = this.zzjst;
        if (num == null) {
            if (zzcoa.zzjst != null) {
                return false;
            }
        } else if (!num.equals(zzcoa.zzjst)) {
            return false;
        }
        zzcof zzcof = this.zzjue;
        if (zzcof == null) {
            if (zzcoa.zzjue != null) {
                return false;
            }
        } else if (!zzcof.equals(zzcoa.zzjue)) {
            return false;
        }
        zzcof zzcof2 = this.zzjuf;
        if (zzcof2 == null) {
            if (zzcoa.zzjuf != null) {
                return false;
            }
        } else if (!zzcof2.equals(zzcoa.zzjuf)) {
            return false;
        }
        Boolean bool = this.zzjug;
        if (bool == null) {
            if (zzcoa.zzjug != null) {
                return false;
            }
        } else if (!bool.equals(zzcoa.zzjug)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcoa.zzpvl == null || zzcoa.zzpvl.isEmpty() : this.zzpvl.equals(zzcoa.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzjst;
        int i = 0;
        int hashCode2 = hashCode + (num == null ? 0 : num.hashCode());
        zzcof zzcof = this.zzjue;
        int hashCode3 = (hashCode2 * 31) + (zzcof == null ? 0 : zzcof.hashCode());
        zzcof zzcof2 = this.zzjuf;
        int hashCode4 = ((hashCode3 * 31) + (zzcof2 == null ? 0 : zzcof2.hashCode())) * 31;
        Boolean bool = this.zzjug;
        int hashCode5 = (hashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
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
            if (zzcxx != 8) {
                if (zzcxx == 18) {
                    if (this.zzjue == null) {
                        this.zzjue = new zzcof();
                    }
                    zzflj.zza(this.zzjue);
                } else if (zzcxx == 26) {
                    if (this.zzjuf == null) {
                        this.zzjuf = new zzcof();
                    }
                    zzflj.zza(this.zzjuf);
                } else if (zzcxx == 32) {
                    this.zzjug = Boolean.valueOf(zzflj.zzcyd());
                } else if (!super.zza(zzflj, zzcxx)) {
                    return this;
                }
            } else {
                this.zzjst = Integer.valueOf(zzflj.zzcym());
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzjst;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        zzcof zzcof = this.zzjue;
        if (zzcof != null) {
            zzflk.zza(2, (zzfls) zzcof);
        }
        zzcof zzcof2 = this.zzjuf;
        if (zzcof2 != null) {
            zzflk.zza(3, (zzfls) zzcof2);
        }
        Boolean bool = this.zzjug;
        if (bool != null) {
            zzflk.zzl(4, bool.booleanValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzjst;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        zzcof zzcof = this.zzjue;
        if (zzcof != null) {
            zzq += zzflk.zzb(2, (zzfls) zzcof);
        }
        zzcof zzcof2 = this.zzjuf;
        if (zzcof2 != null) {
            zzq += zzflk.zzb(3, (zzfls) zzcof2);
        }
        Boolean bool = this.zzjug;
        if (bool == null) {
            return zzq;
        }
        bool.booleanValue();
        return zzq + zzflk.zzlw(4) + 1;
    }
}
