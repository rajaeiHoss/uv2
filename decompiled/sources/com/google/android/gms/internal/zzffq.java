package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;

public final class zzffq extends zzflm<zzffq> {
    public int zzpkl = 0;
    public long zzpkm = 0;
    public int zzpmd = 0;
    public int zzpme = 0;
    public int zzpmf = 0;
    public double zzpmg = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public double zzpmh = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public long zzpmi = 0;

    public zzffq() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzar */
    public final zzffq zza(zzflj zzflj) throws IOException {
        int i;
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                i = zzflj.getPosition();
                int zzcym = zzflj.zzcym();
                if (!(zzcym == 0 || zzcym == 1 || zzcym == 2)) {
                    if (zzcym != 3) {
                        StringBuilder sb = new StringBuilder(43);
                        sb.append(zzcym);
                        sb.append(" is not a valid enum TriggerType");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                this.zzpkl = zzcym;
            } else if (zzcxx == 16) {
                i = zzflj.getPosition();
                try {
                    int zzcym2 = zzflj.zzcym();
                    if (zzcym2 != 0) {
                        if (zzcym2 != 1) {
                            StringBuilder sb2 = new StringBuilder(44);
                            sb2.append(zzcym2);
                            sb2.append(" is not a valid enum GeometryType");
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    }
                    this.zzpmd = zzcym2;
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(i);
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 24) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 32) {
                this.zzpme = zzflj.zzcym();
            } else if (zzcxx == 40) {
                this.zzpmf = zzflj.zzcym();
            } else if (zzcxx == 49) {
                this.zzpmg = Double.longBitsToDouble(zzflj.zzcyt());
            } else if (zzcxx == 57) {
                this.zzpmh = Double.longBitsToDouble(zzflj.zzcyt());
            } else if (zzcxx == 64) {
                this.zzpmi = zzflj.zzcyr();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffq)) {
            return false;
        }
        zzffq zzffq = (zzffq) obj;
        if (this.zzpkl == zzffq.zzpkl && this.zzpmd == zzffq.zzpmd && this.zzpkm == zzffq.zzpkm && this.zzpme == zzffq.zzpme && this.zzpmf == zzffq.zzpmf && Double.doubleToLongBits(this.zzpmg) == Double.doubleToLongBits(zzffq.zzpmg) && Double.doubleToLongBits(this.zzpmh) == Double.doubleToLongBits(zzffq.zzpmh) && this.zzpmi == zzffq.zzpmi) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffq.zzpvl == null || zzffq.zzpvl.isEmpty() : this.zzpvl.equals(zzffq.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpkm;
        int hashCode = ((((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + this.zzpmd) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.zzpme) * 31) + this.zzpmf;
        long doubleToLongBits = Double.doubleToLongBits(this.zzpmg);
        long doubleToLongBits2 = Double.doubleToLongBits(this.zzpmh);
        long j2 = this.zzpmi;
        return (((((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        int i2 = this.zzpmd;
        if (i2 != 0) {
            zzflk.zzad(2, i2);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzflk.zzf(3, j);
        }
        int i3 = this.zzpme;
        if (i3 != 0) {
            zzflk.zzad(4, i3);
        }
        int i4 = this.zzpmf;
        if (i4 != 0) {
            zzflk.zzad(5, i4);
        }
        if (Double.doubleToLongBits(this.zzpmg) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzflk.zza(6, this.zzpmg);
        }
        if (Double.doubleToLongBits(this.zzpmh) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzflk.zza(7, this.zzpmh);
        }
        long j2 = this.zzpmi;
        if (j2 != 0) {
            zzflk.zzf(8, j2);
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
        int i2 = this.zzpmd;
        if (i2 != 0) {
            zzq += zzflk.zzag(2, i2);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzq += zzflk.zzc(3, j);
        }
        int i3 = this.zzpme;
        if (i3 != 0) {
            zzq += zzflk.zzag(4, i3);
        }
        int i4 = this.zzpmf;
        if (i4 != 0) {
            zzq += zzflk.zzag(5, i4);
        }
        if (Double.doubleToLongBits(this.zzpmg) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzq += zzflk.zzlw(6) + 8;
        }
        if (Double.doubleToLongBits(this.zzpmh) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzq += zzflk.zzlw(7) + 8;
        }
        long j2 = this.zzpmi;
        return j2 != 0 ? zzq + zzflk.zzc(8, j2) : zzq;
    }
}
