package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcoc extends zzflm<zzcoc> {
    private static volatile zzcoc[] zzjul;
    public String name = null;
    public String zzgim = null;
    private Float zzjsk = null;
    public Double zzjsl = null;
    public Long zzjum = null;

    public zzcoc() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcoc[] zzbda() {
        if (zzjul == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjul == null) {
                    zzjul = new zzcoc[0];
                }
            }
        }
        return zzjul;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcoc)) {
            return false;
        }
        zzcoc zzcoc = (zzcoc) obj;
        String str = this.name;
        if (str == null) {
            if (zzcoc.name != null) {
                return false;
            }
        } else if (!str.equals(zzcoc.name)) {
            return false;
        }
        String str2 = this.zzgim;
        if (str2 == null) {
            if (zzcoc.zzgim != null) {
                return false;
            }
        } else if (!str2.equals(zzcoc.zzgim)) {
            return false;
        }
        Long l = this.zzjum;
        if (l == null) {
            if (zzcoc.zzjum != null) {
                return false;
            }
        } else if (!l.equals(zzcoc.zzjum)) {
            return false;
        }
        Float f = this.zzjsk;
        if (f == null) {
            if (zzcoc.zzjsk != null) {
                return false;
            }
        } else if (!f.equals(zzcoc.zzjsk)) {
            return false;
        }
        Double d = this.zzjsl;
        if (d == null) {
            if (zzcoc.zzjsl != null) {
                return false;
            }
        } else if (!d.equals(zzcoc.zzjsl)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcoc.zzpvl == null || zzcoc.zzpvl.isEmpty() : this.zzpvl.equals(zzcoc.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzgim;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l = this.zzjum;
        int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        Float f = this.zzjsk;
        int hashCode5 = (hashCode4 + (f == null ? 0 : f.hashCode())) * 31;
        Double d = this.zzjsl;
        int hashCode6 = (hashCode5 + (d == null ? 0 : d.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode6 + i;
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
                this.zzgim = zzflj.readString();
            } else if (zzcxx == 24) {
                this.zzjum = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 37) {
                this.zzjsk = Float.valueOf(Float.intBitsToFloat(zzflj.zzcys()));
            } else if (zzcxx == 41) {
                this.zzjsl = Double.valueOf(Double.longBitsToDouble(zzflj.zzcyt()));
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
        String str2 = this.zzgim;
        if (str2 != null) {
            zzflk.zzp(2, str2);
        }
        Long l = this.zzjum;
        if (l != null) {
            zzflk.zzf(3, l.longValue());
        }
        Float f = this.zzjsk;
        if (f != null) {
            zzflk.zzd(4, f.floatValue());
        }
        Double d = this.zzjsl;
        if (d != null) {
            zzflk.zza(5, d.doubleValue());
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
        String str2 = this.zzgim;
        if (str2 != null) {
            zzq += zzflk.zzq(2, str2);
        }
        Long l = this.zzjum;
        if (l != null) {
            zzq += zzflk.zzc(3, l.longValue());
        }
        Float f = this.zzjsk;
        if (f != null) {
            f.floatValue();
            zzq += zzflk.zzlw(4) + 4;
        }
        Double d = this.zzjsl;
        if (d == null) {
            return zzq;
        }
        d.doubleValue();
        return zzq + zzflk.zzlw(5) + 8;
    }
}
