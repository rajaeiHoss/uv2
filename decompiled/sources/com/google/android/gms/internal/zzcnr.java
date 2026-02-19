package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcnr extends zzflm<zzcnr> {
    private static volatile zzcnr[] zzjss;
    public Integer zzjst = null;
    public zzcnv[] zzjsu = zzcnv.zzbcv();
    public zzcns[] zzjsv = zzcns.zzbct();

    public zzcnr() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcnr[] zzbcs() {
        if (zzjss == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjss == null) {
                    zzjss = new zzcnr[0];
                }
            }
        }
        return zzjss;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcnr)) {
            return false;
        }
        zzcnr zzcnr = (zzcnr) obj;
        Integer num = this.zzjst;
        if (num == null) {
            if (zzcnr.zzjst != null) {
                return false;
            }
        } else if (!num.equals(zzcnr.zzjst)) {
            return false;
        }
        if (zzflq.equals((Object[]) this.zzjsu, (Object[]) zzcnr.zzjsu) && zzflq.equals((Object[]) this.zzjsv, (Object[]) zzcnr.zzjsv)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcnr.zzpvl == null || zzcnr.zzpvl.isEmpty() : this.zzpvl.equals(zzcnr.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzjst;
        int i = 0;
        int hashCode2 = (((((hashCode + (num == null ? 0 : num.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzjsu)) * 31) + zzflq.hashCode((Object[]) this.zzjsv)) * 31;
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
                this.zzjst = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 18) {
                int zzb = zzflv.zzb(zzflj, 18);
                zzcnv[] zzcnvArr = this.zzjsu;
                int length = zzcnvArr == null ? 0 : zzcnvArr.length;
                int i = zzb + length;
                zzcnv[] zzcnvArr2 = new zzcnv[i];
                if (length != 0) {
                    System.arraycopy(zzcnvArr, 0, zzcnvArr2, 0, length);
                }
                while (length < i - 1) {
                    zzcnvArr2[length] = new zzcnv();
                    zzflj.zza(zzcnvArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzcnvArr2[length] = new zzcnv();
                zzflj.zza(zzcnvArr2[length]);
                this.zzjsu = zzcnvArr2;
            } else if (zzcxx == 26) {
                int zzb2 = zzflv.zzb(zzflj, 26);
                zzcns[] zzcnsArr = this.zzjsv;
                int length2 = zzcnsArr == null ? 0 : zzcnsArr.length;
                int i2 = zzb2 + length2;
                zzcns[] zzcnsArr2 = new zzcns[i2];
                if (length2 != 0) {
                    System.arraycopy(zzcnsArr, 0, zzcnsArr2, 0, length2);
                }
                while (length2 < i2 - 1) {
                    zzcnsArr2[length2] = new zzcns();
                    zzflj.zza(zzcnsArr2[length2]);
                    zzflj.zzcxx();
                    length2++;
                }
                zzcnsArr2[length2] = new zzcns();
                zzflj.zza(zzcnsArr2[length2]);
                this.zzjsv = zzcnsArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzjst;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        zzcnv[] zzcnvArr = this.zzjsu;
        int i = 0;
        if (zzcnvArr != null && zzcnvArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzcnv[] zzcnvArr2 = this.zzjsu;
                if (i2 >= zzcnvArr2.length) {
                    break;
                }
                zzcnv zzcnv = zzcnvArr2[i2];
                if (zzcnv != null) {
                    zzflk.zza(2, (zzfls) zzcnv);
                }
                i2++;
            }
        }
        zzcns[] zzcnsArr = this.zzjsv;
        if (zzcnsArr != null && zzcnsArr.length > 0) {
            while (true) {
                zzcns[] zzcnsArr2 = this.zzjsv;
                if (i >= zzcnsArr2.length) {
                    break;
                }
                zzcns zzcns = zzcnsArr2[i];
                if (zzcns != null) {
                    zzflk.zza(3, (zzfls) zzcns);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzjst;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        zzcnv[] zzcnvArr = this.zzjsu;
        int i = 0;
        if (zzcnvArr != null && zzcnvArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzcnv[] zzcnvArr2 = this.zzjsu;
                if (i2 >= zzcnvArr2.length) {
                    break;
                }
                zzcnv zzcnv = zzcnvArr2[i2];
                if (zzcnv != null) {
                    zzq += zzflk.zzb(2, (zzfls) zzcnv);
                }
                i2++;
            }
        }
        zzcns[] zzcnsArr = this.zzjsv;
        if (zzcnsArr != null && zzcnsArr.length > 0) {
            while (true) {
                zzcns[] zzcnsArr2 = this.zzjsv;
                if (i >= zzcnsArr2.length) {
                    break;
                }
                zzcns zzcns = zzcnsArr2[i];
                if (zzcns != null) {
                    zzq += zzflk.zzb(3, (zzfls) zzcns);
                }
                i++;
            }
        }
        return zzq;
    }
}
