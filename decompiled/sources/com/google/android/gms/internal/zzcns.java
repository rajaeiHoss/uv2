package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcns extends zzflm<zzcns> {
    private static volatile zzcns[] zzjsw;
    public Integer zzjsx = null;
    public String zzjsy = null;
    public zzcnt[] zzjsz = zzcnt.zzbcu();
    private Boolean zzjta = null;
    public zzcnu zzjtb = null;

    public zzcns() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcns[] zzbct() {
        if (zzjsw == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjsw == null) {
                    zzjsw = new zzcns[0];
                }
            }
        }
        return zzjsw;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcns)) {
            return false;
        }
        zzcns zzcns = (zzcns) obj;
        Integer num = this.zzjsx;
        if (num == null) {
            if (zzcns.zzjsx != null) {
                return false;
            }
        } else if (!num.equals(zzcns.zzjsx)) {
            return false;
        }
        String str = this.zzjsy;
        if (str == null) {
            if (zzcns.zzjsy != null) {
                return false;
            }
        } else if (!str.equals(zzcns.zzjsy)) {
            return false;
        }
        if (!zzflq.equals((Object[]) this.zzjsz, (Object[]) zzcns.zzjsz)) {
            return false;
        }
        Boolean bool = this.zzjta;
        if (bool == null) {
            if (zzcns.zzjta != null) {
                return false;
            }
        } else if (!bool.equals(zzcns.zzjta)) {
            return false;
        }
        zzcnu zzcnu = this.zzjtb;
        if (zzcnu == null) {
            if (zzcns.zzjtb != null) {
                return false;
            }
        } else if (!zzcnu.equals(zzcns.zzjtb)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcns.zzpvl == null || zzcns.zzpvl.isEmpty() : this.zzpvl.equals(zzcns.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzjsx;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.zzjsy;
        int hashCode3 = (((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + zzflq.hashCode((Object[]) this.zzjsz)) * 31;
        Boolean bool = this.zzjta;
        int hashCode4 = hashCode3 + (bool == null ? 0 : bool.hashCode());
        zzcnu zzcnu = this.zzjtb;
        int hashCode5 = ((hashCode4 * 31) + (zzcnu == null ? 0 : zzcnu.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode5 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.zzjsx = Integer.valueOf(zzflj.zzcym());
            } else if (zzcxx == 18) {
                this.zzjsy = zzflj.readString();
            } else if (zzcxx == 26) {
                int zzb = zzflv.zzb(zzflj, 26);
                zzcnt[] zzcntArr = this.zzjsz;
                int length = zzcntArr == null ? 0 : zzcntArr.length;
                int i = zzb + length;
                zzcnt[] zzcntArr2 = new zzcnt[i];
                if (length != 0) {
                    System.arraycopy(zzcntArr, 0, zzcntArr2, 0, length);
                }
                while (length < i - 1) {
                    zzcntArr2[length] = new zzcnt();
                    zzflj.zza(zzcntArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzcntArr2[length] = new zzcnt();
                zzflj.zza(zzcntArr2[length]);
                this.zzjsz = zzcntArr2;
            } else if (zzcxx == 32) {
                this.zzjta = Boolean.valueOf(zzflj.zzcyd());
            } else if (zzcxx == 42) {
                if (this.zzjtb == null) {
                    this.zzjtb = new zzcnu();
                }
                zzflj.zza(this.zzjtb);
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        Integer num = this.zzjsx;
        if (num != null) {
            zzflk.zzad(1, num.intValue());
        }
        String str = this.zzjsy;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        zzcnt[] zzcntArr = this.zzjsz;
        if (zzcntArr != null && zzcntArr.length > 0) {
            int i = 0;
            while (true) {
                zzcnt[] zzcntArr2 = this.zzjsz;
                if (i >= zzcntArr2.length) {
                    break;
                }
                zzcnt zzcnt = zzcntArr2[i];
                if (zzcnt != null) {
                    zzflk.zza(3, (zzfls) zzcnt);
                }
                i++;
            }
        }
        Boolean bool = this.zzjta;
        if (bool != null) {
            zzflk.zzl(4, bool.booleanValue());
        }
        zzcnu zzcnu = this.zzjtb;
        if (zzcnu != null) {
            zzflk.zza(5, (zzfls) zzcnu);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        Integer num = this.zzjsx;
        if (num != null) {
            zzq += zzflk.zzag(1, num.intValue());
        }
        String str = this.zzjsy;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        zzcnt[] zzcntArr = this.zzjsz;
        if (zzcntArr != null && zzcntArr.length > 0) {
            int i = 0;
            while (true) {
                zzcnt[] zzcntArr2 = this.zzjsz;
                if (i >= zzcntArr2.length) {
                    break;
                }
                zzcnt zzcnt = zzcntArr2[i];
                if (zzcnt != null) {
                    zzq += zzflk.zzb(3, (zzfls) zzcnt);
                }
                i++;
            }
        }
        Boolean bool = this.zzjta;
        if (bool != null) {
            bool.booleanValue();
            zzq += zzflk.zzlw(4) + 1;
        }
        zzcnu zzcnu = this.zzjtb;
        return zzcnu != null ? zzq + zzflk.zzb(5, (zzfls) zzcnu) : zzq;
    }
}
