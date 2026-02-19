package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfml extends zzflm<zzfml> {
    public int score = 0;
    public boolean zzmnx = false;
    public String zzmny = "";
    private zzfmm[] zzpya = zzfmm.zzdcz();

    public zzfml() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfml)) {
            return false;
        }
        zzfml zzfml = (zzfml) obj;
        if (this.zzmnx != zzfml.zzmnx || this.score != zzfml.score) {
            return false;
        }
        String str = this.zzmny;
        if (str == null) {
            if (zzfml.zzmny != null) {
                return false;
            }
        } else if (!str.equals(zzfml.zzmny)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzpya, (Object[]) zzfml.zzpya)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfml.zzpvl == null || zzfml.zzpvl.isEmpty() : this.zzpvl.equals(zzfml.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzmnx ? 1231 : 1237)) * 31) + this.score) * 31;
        String str = this.zzmny;
        int i = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzpya)) * 31;
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
            if (zzcxx == 8) {
                this.zzmnx = zzflj.zzcyd();
            } else if (zzcxx == 16) {
                this.score = zzflj.zzcya();
            } else if (zzcxx == 26) {
                this.zzmny = zzflj.readString();
            } else if (zzcxx == 34) {
                int zzb = zzflv.zzb(zzflj, 34);
                zzfmm[] zzfmmArr = this.zzpya;
                int length = zzfmmArr == null ? 0 : zzfmmArr.length;
                int i = zzb + length;
                zzfmm[] zzfmmArr2 = new zzfmm[i];
                if (length != 0) {
                    System.arraycopy(zzfmmArr, 0, zzfmmArr2, 0, length);
                }
                while (length < i - 1) {
                    zzfmmArr2[length] = new zzfmm();
                    zzflj.zza(zzfmmArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzfmmArr2[length] = new zzfmm();
                zzflj.zza(zzfmmArr2[length]);
                this.zzpya = zzfmmArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        boolean z = this.zzmnx;
        if (z) {
            zzflk.zzl(1, z);
        }
        int i = this.score;
        if (i != 0) {
            zzflk.zzad(2, i);
        }
        String str = this.zzmny;
        if (str != null && !str.equals("")) {
            zzflk.zzp(3, this.zzmny);
        }
        zzfmm[] zzfmmArr = this.zzpya;
        if (zzfmmArr != null && zzfmmArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfmm[] zzfmmArr2 = this.zzpya;
                if (i2 >= zzfmmArr2.length) {
                    break;
                }
                zzfmm zzfmm = zzfmmArr2[i2];
                if (zzfmm != null) {
                    zzflk.zza(4, (zzfls) zzfmm);
                }
                i2++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        if (this.zzmnx) {
            zzq += zzflk.zzlw(1) + 1;
        }
        int i = this.score;
        if (i != 0) {
            zzq += zzflk.zzag(2, i);
        }
        String str = this.zzmny;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(3, this.zzmny);
        }
        zzfmm[] zzfmmArr = this.zzpya;
        if (zzfmmArr != null && zzfmmArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfmm[] zzfmmArr2 = this.zzpya;
                if (i2 >= zzfmmArr2.length) {
                    break;
                }
                zzfmm zzfmm = zzfmmArr2[i2];
                if (zzfmm != null) {
                    zzq += zzflk.zzb(4, (zzfls) zzfmm);
                }
                i2++;
            }
        }
        return zzq;
    }
}
