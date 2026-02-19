package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffo extends zzflm<zzffo> {
    private String packageName = "";
    private int zzpkl = 0;

    public zzffo() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzaq */
    public final zzffo zza(zzflj zzflj) throws IOException {
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
            } else if (zzcxx == 18) {
                this.packageName = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffo)) {
            return false;
        }
        zzffo zzffo = (zzffo) obj;
        if (this.zzpkl != zzffo.zzpkl) {
            return false;
        }
        String str = this.packageName;
        if (str == null) {
            if (zzffo.packageName != null) {
                return false;
            }
        } else if (!str.equals(zzffo.packageName)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffo.zzpvl == null || zzffo.zzpvl.isEmpty() : this.zzpvl.equals(zzffo.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31;
        String str = this.packageName;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        String str = this.packageName;
        if (str != null && !str.equals("")) {
            zzflk.zzp(2, this.packageName);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.zzpkl;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        String str = this.packageName;
        return (str == null || str.equals("")) ? zzq : zzq + zzflk.zzq(2, this.packageName);
    }
}
