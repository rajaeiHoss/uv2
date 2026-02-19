package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcog extends zzflm<zzcog> {
    private static volatile zzcog[] zzjvq;
    public String name = null;
    public String zzgim = null;
    private Float zzjsk = null;
    public Double zzjsl = null;
    public Long zzjum = null;
    public Long zzjvr = null;

    public zzcog() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcog[] zzbdc() {
        if (zzjvq == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjvq == null) {
                    zzjvq = new zzcog[0];
                }
            }
        }
        return zzjvq;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcog)) {
            return false;
        }
        zzcog zzcog = (zzcog) obj;
        Long l = this.zzjvr;
        if (l == null) {
            if (zzcog.zzjvr != null) {
                return false;
            }
        } else if (!l.equals(zzcog.zzjvr)) {
            return false;
        }
        String str = this.name;
        if (str == null) {
            if (zzcog.name != null) {
                return false;
            }
        } else if (!str.equals(zzcog.name)) {
            return false;
        }
        String str2 = this.zzgim;
        if (str2 == null) {
            if (zzcog.zzgim != null) {
                return false;
            }
        } else if (!str2.equals(zzcog.zzgim)) {
            return false;
        }
        Long l2 = this.zzjum;
        if (l2 == null) {
            if (zzcog.zzjum != null) {
                return false;
            }
        } else if (!l2.equals(zzcog.zzjum)) {
            return false;
        }
        Float f = this.zzjsk;
        if (f == null) {
            if (zzcog.zzjsk != null) {
                return false;
            }
        } else if (!f.equals(zzcog.zzjsk)) {
            return false;
        }
        Double d = this.zzjsl;
        if (d == null) {
            if (zzcog.zzjsl != null) {
                return false;
            }
        } else if (!d.equals(zzcog.zzjsl)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcog.zzpvl == null || zzcog.zzpvl.isEmpty() : this.zzpvl.equals(zzcog.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Long l = this.zzjvr;
        int i = 0;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.name;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzgim;
        int hashCode4 = (hashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l2 = this.zzjum;
        int hashCode5 = (hashCode4 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Float f = this.zzjsk;
        int hashCode6 = (hashCode5 + (f == null ? 0 : f.hashCode())) * 31;
        Double d = this.zzjsl;
        int hashCode7 = (hashCode6 + (d == null ? 0 : d.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode7 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzjvr = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 18) {
                this.name = zzflj.readString();
            } else if (zzcxx == 26) {
                this.zzgim = zzflj.readString();
            } else if (zzcxx == 32) {
                this.zzjum = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 45) {
                this.zzjsk = Float.valueOf(Float.intBitsToFloat(zzflj.zzcys()));
            } else if (zzcxx == 49) {
                this.zzjsl = Double.valueOf(Double.longBitsToDouble(zzflj.zzcyt()));
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Long l = this.zzjvr;
        if (l != null) {
            zzflk.zzf(1, l.longValue());
        }
        String str = this.name;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        String str2 = this.zzgim;
        if (str2 != null) {
            zzflk.zzp(3, str2);
        }
        Long l2 = this.zzjum;
        if (l2 != null) {
            zzflk.zzf(4, l2.longValue());
        }
        Float f = this.zzjsk;
        if (f != null) {
            zzflk.zzd(5, f.floatValue());
        }
        Double d = this.zzjsl;
        if (d != null) {
            zzflk.zza(6, d.doubleValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Long l = this.zzjvr;
        if (l != null) {
            zzq += zzflk.zzc(1, l.longValue());
        }
        String str = this.name;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        String str2 = this.zzgim;
        if (str2 != null) {
            zzq += zzflk.zzq(3, str2);
        }
        Long l2 = this.zzjum;
        if (l2 != null) {
            zzq += zzflk.zzc(4, l2.longValue());
        }
        Float f = this.zzjsk;
        if (f != null) {
            f.floatValue();
            zzq += zzflk.zzlw(5) + 4;
        }
        Double d = this.zzjsl;
        if (d == null) {
            return zzq;
        }
        d.doubleValue();
        return zzq + zzflk.zzlw(6) + 8;
    }
}
