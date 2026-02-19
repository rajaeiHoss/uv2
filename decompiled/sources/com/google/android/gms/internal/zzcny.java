package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcny extends zzflm<zzcny> {
    public String zzjfl = null;
    public Long zzjtx = null;
    private Integer zzjty = null;
    public zzcnz[] zzjtz = zzcnz.zzbcx();
    public zzcnx[] zzjua = zzcnx.zzbcw();
    public zzcnr[] zzjub = zzcnr.zzbcs();

    public zzcny() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcny)) {
            return false;
        }
        zzcny zzcny = (zzcny) obj;
        Long l = this.zzjtx;
        if (l == null) {
            if (zzcny.zzjtx != null) {
                return false;
            }
        } else if (!l.equals(zzcny.zzjtx)) {
            return false;
        }
        String str = this.zzjfl;
        if (str == null) {
            if (zzcny.zzjfl != null) {
                return false;
            }
        } else if (!str.equals(zzcny.zzjfl)) {
            return false;
        }
        Integer num = this.zzjty;
        if (num == null) {
            if (zzcny.zzjty != null) {
                return false;
            }
        } else if (!num.equals(zzcny.zzjty)) {
            return false;
        }
        if (zzflq.equals((Object[]) this.zzjtz, (Object[]) zzcny.zzjtz) && zzflq.equals((Object[]) this.zzjua, (Object[]) zzcny.zzjua) && zzflq.equals((Object[]) this.zzjub, (Object[]) zzcny.zzjub)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcny.zzpvl == null || zzcny.zzpvl.isEmpty() : this.zzpvl.equals(zzcny.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Long l = this.zzjtx;
        int i = 0;
        int hashCode2 = (hashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.zzjfl;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.zzjty;
        int hashCode4 = (((((((hashCode3 + (num == null ? 0 : num.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzjtz)) * 31) + zzflq.hashCode((Object[]) this.zzjua)) * 31) + zzflq.hashCode((Object[]) this.zzjub)) * 31;
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
            if (zzcxx == 8) {
                this.zzjtx = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 18) {
                this.zzjfl = zzflj.readString();
            } else if (zzcxx == 24) {
                this.zzjty = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 34) {
                int zzb = zzflv.zzb(zzflj, 34);
                zzcnz[] zzcnzArr = this.zzjtz;
                int length = zzcnzArr == null ? 0 : zzcnzArr.length;
                int i = zzb + length;
                zzcnz[] zzcnzArr2 = new zzcnz[i];
                if (length != 0) {
                    System.arraycopy(zzcnzArr, 0, zzcnzArr2, 0, length);
                }
                while (length < i - 1) {
                    zzcnzArr2[length] = new zzcnz();
                    zzflj.zza(zzcnzArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzcnzArr2[length] = new zzcnz();
                zzflj.zza(zzcnzArr2[length]);
                this.zzjtz = zzcnzArr2;
            } else if (zzcxx == 42) {
                int zzb2 = zzflv.zzb(zzflj, 42);
                zzcnx[] zzcnxArr = this.zzjua;
                int length2 = zzcnxArr == null ? 0 : zzcnxArr.length;
                int i2 = zzb2 + length2;
                zzcnx[] zzcnxArr2 = new zzcnx[i2];
                if (length2 != 0) {
                    System.arraycopy(zzcnxArr, 0, zzcnxArr2, 0, length2);
                }
                while (length2 < i2 - 1) {
                    zzcnxArr2[length2] = new zzcnx();
                    zzflj.zza(zzcnxArr2[length2]);
                    zzflj.zzcxx();
                    length2++;
                }
                zzcnxArr2[length2] = new zzcnx();
                zzflj.zza(zzcnxArr2[length2]);
                this.zzjua = zzcnxArr2;
            } else if (zzcxx == 50) {
                int zzb3 = zzflv.zzb(zzflj, 50);
                zzcnr[] zzcnrArr = this.zzjub;
                int length3 = zzcnrArr == null ? 0 : zzcnrArr.length;
                int i3 = zzb3 + length3;
                zzcnr[] zzcnrArr2 = new zzcnr[i3];
                if (length3 != 0) {
                    System.arraycopy(zzcnrArr, 0, zzcnrArr2, 0, length3);
                }
                while (length3 < i3 - 1) {
                    zzcnrArr2[length3] = new zzcnr();
                    zzflj.zza(zzcnrArr2[length3]);
                    zzflj.zzcxx();
                    length3++;
                }
                zzcnrArr2[length3] = new zzcnr();
                zzflj.zza(zzcnrArr2[length3]);
                this.zzjub = zzcnrArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Long l = this.zzjtx;
        if (l != null) {
            zzflk.zzf(1, l.longValue());
        }
        String str = this.zzjfl;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        Integer num = this.zzjty;
        if (num != null) {
            zzflk.zzad(3, num.intValue());
        }
        zzcnz[] zzcnzArr = this.zzjtz;
        int i = 0;
        if (zzcnzArr != null && zzcnzArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzcnz[] zzcnzArr2 = this.zzjtz;
                if (i2 >= zzcnzArr2.length) {
                    break;
                }
                zzcnz zzcnz = zzcnzArr2[i2];
                if (zzcnz != null) {
                    zzflk.zza(4, (zzfls) zzcnz);
                }
                i2++;
            }
        }
        zzcnx[] zzcnxArr = this.zzjua;
        if (zzcnxArr != null && zzcnxArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzcnx[] zzcnxArr2 = this.zzjua;
                if (i3 >= zzcnxArr2.length) {
                    break;
                }
                zzcnx zzcnx = zzcnxArr2[i3];
                if (zzcnx != null) {
                    zzflk.zza(5, (zzfls) zzcnx);
                }
                i3++;
            }
        }
        zzcnr[] zzcnrArr = this.zzjub;
        if (zzcnrArr != null && zzcnrArr.length > 0) {
            while (true) {
                zzcnr[] zzcnrArr2 = this.zzjub;
                if (i >= zzcnrArr2.length) {
                    break;
                }
                zzcnr zzcnr = zzcnrArr2[i];
                if (zzcnr != null) {
                    zzflk.zza(6, (zzfls) zzcnr);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Long l = this.zzjtx;
        if (l != null) {
            zzq += zzflk.zzc(1, l.longValue());
        }
        String str = this.zzjfl;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        Integer num = this.zzjty;
        if (num != null) {
            zzq += zzflk.zzag(3, num.intValue());
        }
        zzcnz[] zzcnzArr = this.zzjtz;
        int i = 0;
        if (zzcnzArr != null && zzcnzArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzcnz[] zzcnzArr2 = this.zzjtz;
                if (i2 >= zzcnzArr2.length) {
                    break;
                }
                zzcnz zzcnz = zzcnzArr2[i2];
                if (zzcnz != null) {
                    zzq += zzflk.zzb(4, (zzfls) zzcnz);
                }
                i2++;
            }
        }
        zzcnx[] zzcnxArr = this.zzjua;
        if (zzcnxArr != null && zzcnxArr.length > 0) {
            int i3 = 0;
            while (true) {
                zzcnx[] zzcnxArr2 = this.zzjua;
                if (i3 >= zzcnxArr2.length) {
                    break;
                }
                zzcnx zzcnx = zzcnxArr2[i3];
                if (zzcnx != null) {
                    zzq += zzflk.zzb(5, (zzfls) zzcnx);
                }
                i3++;
            }
        }
        zzcnr[] zzcnrArr = this.zzjub;
        if (zzcnrArr != null && zzcnrArr.length > 0) {
            while (true) {
                zzcnr[] zzcnrArr2 = this.zzjub;
                if (i >= zzcnrArr2.length) {
                    break;
                }
                zzcnr zzcnr = zzcnrArr2[i];
                if (zzcnr != null) {
                    zzq += zzflk.zzb(6, (zzfls) zzcnr);
                }
                i++;
            }
        }
        return zzq;
    }
}
