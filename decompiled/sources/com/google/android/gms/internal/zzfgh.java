package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;

public final class zzfgh extends zzflm<zzfgh> {
    private static zzfln<zzffk, zzfgh> zzpjy = zzfln.zza(11, zzfgh.class, 701234570);
    private static final zzfgh[] zzpnm = new zzfgh[0];
    private int state = 0;
    private double zzcyt = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

    public zzfgh() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbh */
    public final zzfgh zza(zzflj zzflj) throws IOException {
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
            } else if (zzcxx == 17) {
                this.zzcyt = Double.longBitsToDouble(zzflj.zzcyt());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public static int zzlc(int i) {
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {
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
        if (!(obj instanceof zzfgh)) {
            return false;
        }
        zzfgh zzfgh = (zzfgh) obj;
        if (this.state == zzfgh.state && Double.doubleToLongBits(this.zzcyt) == Double.doubleToLongBits(zzfgh.zzcyt)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfgh.zzpvl == null || zzfgh.zzpvl.isEmpty() : this.zzpvl.equals(zzfgh.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + this.state;
        long doubleToLongBits = Double.doubleToLongBits(this.zzcyt);
        return (((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.state;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        if (Double.doubleToLongBits(this.zzcyt) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzflk.zza(2, this.zzcyt);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.state;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        return Double.doubleToLongBits(this.zzcyt) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) ? zzq + zzflk.zzlw(2) + 8 : zzq;
    }
}
