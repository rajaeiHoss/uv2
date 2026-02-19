package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffw extends zzflm<zzffw> {
    private int zzpkl = 0;

    public zzffw() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzax */
    public final zzffw zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1)) {
                        if (zzcym != 2) {
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TriggerType");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzpkl = zzcym;
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffw)) {
            return false;
        }
        zzffw zzffw = (zzffw) obj;
        if (this.zzpkl != zzffw.zzpkl) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffw.zzpvl == null || zzffw.zzpvl.isEmpty() : this.zzpvl.equals(zzffw.zzpvl);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.zzpkl;
        return i != 0 ? zzq + zzflk.zzag(1, i) : zzq;
    }
}
