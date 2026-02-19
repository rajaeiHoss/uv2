package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcdm extends zzflm<zzcdm> {
    public String type = "";
    public zzcdl[] zzilh = zzcdl.zzawm();

    public zzcdm() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcdm)) {
            return false;
        }
        zzcdm zzcdm = (zzcdm) obj;
        String str = this.type;
        if (str == null) {
            if (zzcdm.type != null) {
                return false;
            }
        } else if (!str.equals(zzcdm.type)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzilh, (Object[]) zzcdm.zzilh)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcdm.zzpvl == null || zzcdm.zzpvl.isEmpty() : this.zzpvl.equals(zzcdm.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.type;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzilh)) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode2 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                this.type = zzflj.readString();
            } else if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zzcdl[] zzcdlArr = this.zzilh;
                int length = zzcdlArr == null ? 0 : zzcdlArr.length;
                int i = zzb + length;
                zzcdl[] zzcdlArr2 = new zzcdl[i];
                if (length != 0) {
                    System.arraycopy(zzcdlArr, 0, zzcdlArr2, 0, length);
                }
                while (length < i - 1) {
                    zzcdlArr2[length] = new zzcdl();
                    zzflj.zza(zzcdlArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzcdlArr2[length] = new zzcdl();
                zzflj.zza(zzcdlArr2[length]);
                this.zzilh = zzcdlArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        String str = this.type;
        if (str != null && !str.equals("")) {
            zzflk.zzp(1, this.type);
        }
        zzcdl[] zzcdlArr = this.zzilh;
        if (zzcdlArr != null && zzcdlArr.length > 0) {
            int i = 0;
            while (true) {
                zzcdl[] zzcdlArr2 = this.zzilh;
                if (i >= zzcdlArr2.length) {
                    break;
                }
                zzcdl zzcdl = zzcdlArr2[i];
                if (zzcdl != null) {
                    zzflk.zza(2, (zzfls) zzcdl);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        String str = this.type;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(1, this.type);
        }
        zzcdl[] zzcdlArr = this.zzilh;
        if (zzcdlArr != null && zzcdlArr.length > 0) {
            int i = 0;
            while (true) {
                zzcdl[] zzcdlArr2 = this.zzilh;
                if (i >= zzcdlArr2.length) {
                    break;
                }
                zzcdl zzcdl = zzcdlArr2[i];
                if (zzcdl != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzcdl);
                }
                i++;
            }
        }
        return zzq;
    }
}
