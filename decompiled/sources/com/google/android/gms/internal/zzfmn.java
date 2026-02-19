package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmn extends zzflm<zzfmn> {
    private static volatile zzfmn[] zzpyi;
    private String type = "";
    private zzfmm[] zzpya = zzfmm.zzdcz();
    private zzfml zzpyj = null;
    private String zzwc = "";

    public zzfmn() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzfmn[] zzdda() {
        if (zzpyi == null) {
            synchronized (zzflq.zzpvt) {
                if (zzpyi == null) {
                    zzpyi = new zzfmn[0];
                }
            }
        }
        return zzpyi;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfmn)) {
            return false;
        }
        zzfmn zzfmn = (zzfmn) obj;
        String str = this.type;
        if (str == null) {
            if (zzfmn.type != null) {
                return false;
            }
        } else if (!str.equals(zzfmn.type)) {
            return false;
        }
        String str2 = this.zzwc;
        if (str2 == null) {
            if (zzfmn.zzwc != null) {
                return false;
            }
        } else if (!str2.equals(zzfmn.zzwc)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzpya, (Object[]) zzfmn.zzpya)) {
            return false;
        }
        zzfml zzfml = this.zzpyj;
        if (zzfml == null) {
            if (zzfmn.zzpyj != null) {
                return false;
            }
        } else if (!zzfml.equals(zzfmn.zzpyj)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfmn.zzpvl == null || zzfmn.zzpvl.isEmpty() : this.zzpvl.equals(zzfmn.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.type;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.zzwc;
        int hashCode3 = ((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzpya);
        zzfml zzfml = this.zzpyj;
        int hashCode4 = ((hashCode3 * 31) + (zzfml == null ? 0 : zzfml.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode4 + i;
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
                this.zzwc = zzflj.readString();
            } else if (zzcxx == 26) {
                int zzb = zzflv.zzb(zzflj, 26);
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
            } else if (zzcxx == 34) {
                if (this.zzpyj == null) {
                    this.zzpyj = new zzfml();
                }
                zzflj.zza(this.zzpyj);
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
        String str2 = this.zzwc;
        if (str2 != null && !str2.equals("")) {
            zzflk.zzp(2, this.zzwc);
        }
        zzfmm[] zzfmmArr = this.zzpya;
        if (zzfmmArr != null && zzfmmArr.length > 0) {
            int i = 0;
            while (true) {
                zzfmm[] zzfmmArr2 = this.zzpya;
                if (i >= zzfmmArr2.length) {
                    break;
                }
                zzfmm zzfmm = zzfmmArr2[i];
                if (zzfmm != null) {
                    zzflk.zza(3, (zzfls) zzfmm);
                }
                i++;
            }
        }
        zzfml zzfml = this.zzpyj;
        if (zzfml != null) {
            zzflk.zza(4, (zzfls) zzfml);
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
        String str2 = this.zzwc;
        if (str2 != null && !str2.equals("")) {
            zzq += zzflk.zzq(2, this.zzwc);
        }
        zzfmm[] zzfmmArr = this.zzpya;
        if (zzfmmArr != null && zzfmmArr.length > 0) {
            int i = 0;
            while (true) {
                zzfmm[] zzfmmArr2 = this.zzpya;
                if (i >= zzfmmArr2.length) {
                    break;
                }
                zzfmm zzfmm = zzfmmArr2[i];
                if (zzfmm != null) {
                    zzq += zzflk.zzb(3, (zzfls) zzfmm);
                }
                i++;
            }
        }
        zzfml zzfml = this.zzpyj;
        return zzfml != null ? zzq + zzflk.zzb(4, (zzfls) zzfml) : zzq;
    }
}
