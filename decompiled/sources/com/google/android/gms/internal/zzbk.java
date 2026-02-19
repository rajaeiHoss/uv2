package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbk extends zzflm<zzbk> {
    private int level = 1;
    private int zzwe = 0;
    private int zzwf = 0;

    public zzbk() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzbk zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 1 || zzcym == 2)) {
                        if (zzcym != 3) {
                            StringBuilder sb = new StringBuilder(42);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum CacheLevel");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.level = zzcym;
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 16) {
                this.zzwe = zzflj.zzcym();
            } else if (zzcxx == 24) {
                this.zzwf = zzflj.zzcym();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbk)) {
            return false;
        }
        zzbk zzbk = (zzbk) obj;
        if (this.level == zzbk.level && this.zzwe == zzbk.zzwe && this.zzwf == zzbk.zzwf) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbk.zzpvl == null || zzbk.zzpvl.isEmpty() : this.zzpvl.equals(zzbk.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((getClass().getName().hashCode() + 527) * 31) + this.level) * 31) + this.zzwe) * 31) + this.zzwf) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.level;
        if (i != 1) {
            zzflk.zzad(1, i);
        }
        int i2 = this.zzwe;
        if (i2 != 0) {
            zzflk.zzad(2, i2);
        }
        int i3 = this.zzwf;
        if (i3 != 0) {
            zzflk.zzad(3, i3);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.level;
        if (i != 1) {
            zzq += zzflk.zzag(1, i);
        }
        int i2 = this.zzwe;
        if (i2 != 0) {
            zzq += zzflk.zzag(2, i2);
        }
        int i3 = this.zzwf;
        return i3 != 0 ? zzq + zzflk.zzag(3, i3) : zzq;
    }
}
