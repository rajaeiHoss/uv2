package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfgf extends zzflm<zzfgf> {
    private static zzfln<zzffk, zzfgf> zzpjy = zzfln.zza(11, zzfgf.class, 764442330);
    private static final zzfgf[] zzpni = new zzfgf[0];
    private int zzpnj = 0;
    private int zzpnk = 0;

    public zzfgf() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbf */
    public final zzfgf zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                i = zzflj.getPosition();
                this.zzpnj = zzla(zzflj.zzcym());
            } else if (zzcxx == 16) {
                i = zzflj.getPosition();
                try {
                    this.zzpnk = zzlb(zzflj.zzcym());
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(i);
                    zza(zzflj, zzcxx);
                }
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public static int zzla(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 3) {
            return i;
        }
        StringBuilder sb = new StringBuilder(47);
        sb.append(i);
        sb.append(" is not a valid enum ConnectionState");
        throw new IllegalArgumentException(sb.toString());
    }

    public static int zzlb(int i) {
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        StringBuilder sb = new StringBuilder(42);
        sb.append(i);
        sb.append(" is not a valid enum MeterState");
        throw new IllegalArgumentException(sb.toString());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpnj;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        int i2 = this.zzpnk;
        if (i2 != 0) {
            zzflk.zzad(2, i2);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.zzpnj;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        int i2 = this.zzpnk;
        return i2 != 0 ? zzq + zzflk.zzag(2, i2) : zzq;
    }
}
