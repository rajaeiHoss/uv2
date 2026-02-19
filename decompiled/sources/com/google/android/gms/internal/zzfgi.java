package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfgi extends zzflm<zzfgi> {
    private static zzfln<zzffk, zzfgi> zzpjy = zzfln.zza(11, zzfgi.class, 634279410);
    private static final zzfgi[] zzpnn = new zzfgi[0];
    private int state = 0;

    public zzfgi() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbi */
    public final zzfgi zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                int position = zzflj.getPosition();
                try {
                    this.state = zzlc(zzflj.zzcym());
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(position);
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public static int zzlc(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append(i);
        sb.append(" is not a valid enum State");
        throw new IllegalArgumentException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfgi)) {
            return false;
        }
        zzfgi zzfgi = (zzfgi) obj;
        if (this.state != zzfgi.state) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfgi.zzpvl == null || zzfgi.zzpvl.isEmpty() : this.zzpvl.equals(zzfgi.zzpvl);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + this.state) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.state;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.state;
        return i != 0 ? zzq + zzflk.zzag(1, i) : zzq;
    }
}
