package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfgb extends zzflm<zzfgb> {
    public long zzhhl = 0;
    public int zzpkl = 0;
    public String zzpmw = "";
    public long zzpmx = 0;
    private zzffn zzpmy = null;
    public boolean zzpmz = false;

    public zzfgb() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbb */
    public final zzfgb zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    switch (zzcym) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            this.zzpkl = zzcym;
                            break;
                        default:
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TriggerType");
                            throw new IllegalArgumentException(sb.toString());
                    }
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 18) {
                this.zzpmw = zzflj.readString();
            } else if (zzcxx == 24) {
                this.zzhhl = zzflj.zzcyr();
            } else if (zzcxx == 32) {
                this.zzpmx = zzflj.zzcyr();
            } else if (zzcxx == 42) {
                if (this.zzpmy == null) {
                    this.zzpmy = new zzffn();
                }
                zzflj.zza(this.zzpmy);
            } else if (zzcxx == 48) {
                this.zzpmz = zzflj.zzcyd();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfgb)) {
            return false;
        }
        zzfgb zzfgb = (zzfgb) obj;
        if (this.zzpkl != zzfgb.zzpkl) {
            return false;
        }
        String str = this.zzpmw;
        if (str == null) {
            if (zzfgb.zzpmw != null) {
                return false;
            }
        } else if (!str.equals(zzfgb.zzpmw)) {
            return false;
        }
        if (this.zzhhl != zzfgb.zzhhl || this.zzpmx != zzfgb.zzpmx) {
            return false;
        }
        zzffn zzffn = this.zzpmy;
        if (zzffn == null) {
            if (zzfgb.zzpmy != null) {
                return false;
            }
        } else if (!zzffn.equals(zzfgb.zzpmy)) {
            return false;
        }
        if (this.zzpmz != zzfgb.zzpmz) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfgb.zzpvl == null || zzfgb.zzpvl.isEmpty() : this.zzpvl.equals(zzfgb.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31;
        String str = this.zzpmw;
        int i = 0;
        int hashCode2 = str == null ? 0 : str.hashCode();
        long j = this.zzhhl;
        long j2 = this.zzpmx;
        int i2 = ((((hashCode + hashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)));
        zzffn zzffn = this.zzpmy;
        int hashCode3 = ((((i2 * 31) + (zzffn == null ? 0 : zzffn.hashCode())) * 31) + (this.zzpmz ? 1231 : 1237)) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        String str = this.zzpmw;
        if (str != null && !str.equals("")) {
            zzflk.zzp(2, this.zzpmw);
        }
        long j = this.zzhhl;
        if (j != 0) {
            zzflk.zzf(3, j);
        }
        long j2 = this.zzpmx;
        if (j2 != 0) {
            zzflk.zzf(4, j2);
        }
        zzffn zzffn = this.zzpmy;
        if (zzffn != null) {
            zzflk.zza(5, (zzfls) zzffn);
        }
        boolean z = this.zzpmz;
        if (z) {
            zzflk.zzl(6, z);
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
        String str = this.zzpmw;
        if (str != null && !str.equals("")) {
            zzq += zzflk.zzq(2, this.zzpmw);
        }
        long j = this.zzhhl;
        if (j != 0) {
            zzq += zzflk.zzc(3, j);
        }
        long j2 = this.zzpmx;
        if (j2 != 0) {
            zzq += zzflk.zzc(4, j2);
        }
        zzffn zzffn = this.zzpmy;
        if (zzffn != null) {
            zzq += zzflk.zzb(5, (zzfls) zzffn);
        }
        return this.zzpmz ? zzq + zzflk.zzlw(6) + 1 : zzq;
    }
}
