package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;

public final class zzcdn extends zzflm<zzcdn> {
    public String zzgim = "";
    public boolean zzili = false;
    private long zzilj = 0;
    private double zzilk = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    public zzcdm zzill = null;

    public zzcdn() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcdn)) {
            return false;
        }
        zzcdn zzcdn = (zzcdn) obj;
        if (this.zzili != zzcdn.zzili) {
            return false;
        }
        String str = this.zzgim;
        if (str == null) {
            if (zzcdn.zzgim != null) {
                return false;
            }
        } else if (!str.equals(zzcdn.zzgim)) {
            return false;
        }
        if (this.zzilj != zzcdn.zzilj || Double.doubleToLongBits(this.zzilk) != Double.doubleToLongBits(zzcdn.zzilk)) {
            return false;
        }
        zzcdm zzcdm = this.zzill;
        if (zzcdm == null) {
            if (zzcdn.zzill != null) {
                return false;
            }
        } else if (!zzcdm.equals(zzcdn.zzill)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcdn.zzpvl == null || zzcdn.zzpvl.isEmpty() : this.zzpvl.equals(zzcdn.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + (this.zzili ? 1231 : 1237)) * 31;
        String str = this.zzgim;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j = this.zzilj;
        long doubleToLongBits = Double.doubleToLongBits(this.zzilk);
        int i2 = ((((hashCode + hashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        zzcdm zzcdm = this.zzill;
        int hashCode3 = ((i2 * 31) + (zzcdm == null ? 0 : zzcdm.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode3 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzili = zzflj.zzcyd();
            } else if (zzcxx == 18) {
                this.zzgim = zzflj.readString();
            } else if (zzcxx == 24) {
                this.zzilj = zzflj.zzcyr();
            } else if (zzcxx == 33) {
                this.zzilk = Double.longBitsToDouble(zzflj.zzcyt());
            } else if (zzcxx == 42) {
                if (this.zzill == null) {
                    this.zzill = new zzcdm();
                }
                zzflj.zza(this.zzill);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        boolean z = this.zzili;
        if (z) {
            zzflk.zzl(1, z);
        }
        String str = this.zzgim;
        if (str != null && !str.equals("")) {
            zzflk.zzp(2, this.zzgim);
        }
        long j = this.zzilj;
        if (j != 0) {
            zzflk.zzf(3, j);
        }
        if (Double.doubleToLongBits(this.zzilk) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzflk.zza(4, this.zzilk);
        }
        zzcdm zzcdm = this.zzill;
        if (zzcdm != null) {
            zzflk.zza(5, (zzfls) zzcdm);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        if (this.zzili) {
            zzq += zzflk.zzlw(1) + 1;
        }
        String str = this.zzgim;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(2, this.zzgim);
        }
        long j = this.zzilj;
        if (j != 0) {
            zzq += zzflk.zzc(3, j);
        }
        if (Double.doubleToLongBits(this.zzilk) != Double.doubleToLongBits(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            zzq += zzflk.zzlw(4) + 8;
        }
        zzcdm zzcdm = this.zzill;
        return zzcdm != null ? zzq + zzflk.zzb(5, (zzfls) zzcdm) : zzq;
    }
}
