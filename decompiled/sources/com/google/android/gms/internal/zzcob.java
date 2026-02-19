package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcob extends zzflm<zzcob> {
    private static volatile zzcob[] zzjuh;
    public Integer count = null;
    public String name = null;
    public zzcoc[] zzjui = zzcoc.zzbda();
    public Long zzjuj = null;
    public Long zzjuk = null;

    public zzcob() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzcob[] zzbcz() {
        if (zzjuh == null) {
            synchronized (zzflq.zzpvt) {
                if (zzjuh == null) {
                    zzjuh = new zzcob[0];
                }
            }
        }
        return zzjuh;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcob)) {
            return false;
        }
        zzcob zzcob = (zzcob) obj;
        if (!zzflq.equals((Object[]) this.zzjui, (Object[]) zzcob.zzjui)) {
            return false;
        }
        String str = this.name;
        if (str == null) {
            if (zzcob.name != null) {
                return false;
            }
        } else if (!str.equals(zzcob.name)) {
            return false;
        }
        Long l = this.zzjuj;
        if (l == null) {
            if (zzcob.zzjuj != null) {
                return false;
            }
        } else if (!l.equals(zzcob.zzjuj)) {
            return false;
        }
        Long l2 = this.zzjuk;
        if (l2 == null) {
            if (zzcob.zzjuk != null) {
                return false;
            }
        } else if (!l2.equals(zzcob.zzjuk)) {
            return false;
        }
        Integer num = this.count;
        if (num == null) {
            if (zzcob.count != null) {
                return false;
            }
        } else if (!num.equals(zzcob.count)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcob.zzpvl == null || zzcob.zzpvl.isEmpty() : this.zzpvl.equals(zzcob.zzpvl);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzjui)) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.zzjuj;
        int hashCode3 = (hashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        Long l2 = this.zzjuk;
        int hashCode4 = (hashCode3 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Integer num = this.count;
        int hashCode5 = (hashCode4 + (num == null ? 0 : num.hashCode())) * 31;
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
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzcoc[] zzcocArr = this.zzjui;
                int length = zzcocArr == null ? 0 : zzcocArr.length;
                int i = zzb + length;
                zzcoc[] zzcocArr2 = new zzcoc[i];
                if (length != 0) {
                    System.arraycopy(zzcocArr, 0, zzcocArr2, 0, length);
                }
                while (length < i - 1) {
                    zzcocArr2[length] = new zzcoc();
                    zzflj.zza(zzcocArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzcocArr2[length] = new zzcoc();
                zzflj.zza(zzcocArr2[length]);
                this.zzjui = zzcocArr2;
            } else if (zzcxx == 18) {
                this.name = zzflj.readString();
            } else if (zzcxx == 24) {
                this.zzjuj = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 32) {
                this.zzjuk = Long.valueOf(zzflj.zzcyr());
            } else if (zzcxx == 40) {
                this.count = Integer.valueOf(zzflj.zzcym());
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzcoc[] zzcocArr = this.zzjui;
        if (zzcocArr != null && zzcocArr.length > 0) {
            int i = 0;
            while (true) {
                zzcoc[] zzcocArr2 = this.zzjui;
                if (i >= zzcocArr2.length) {
                    break;
                }
                zzcoc zzcoc = zzcocArr2[i];
                if (zzcoc != null) {
                    zzflk.zza(1, (zzfls) zzcoc);
                }
                i++;
            }
        }
        String str = this.name;
        if (str != null) {
            zzflk.zzp(2, str);
        }
        Long l = this.zzjuj;
        if (l != null) {
            zzflk.zzf(3, l.longValue());
        }
        Long l2 = this.zzjuk;
        if (l2 != null) {
            zzflk.zzf(4, l2.longValue());
        }
        Integer num = this.count;
        if (num != null) {
            zzflk.zzad(5, num.intValue());
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzcoc[] zzcocArr = this.zzjui;
        if (zzcocArr != null && zzcocArr.length > 0) {
            int i = 0;
            while (true) {
                zzcoc[] zzcocArr2 = this.zzjui;
                if (i >= zzcocArr2.length) {
                    break;
                }
                zzcoc zzcoc = zzcocArr2[i];
                if (zzcoc != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzcoc);
                }
                i++;
            }
        }
        String str = this.name;
        if (str != null) {
            zzq += zzflk.zzq(2, str);
        }
        Long l = this.zzjuj;
        if (l != null) {
            zzq += zzflk.zzc(3, l.longValue());
        }
        Long l2 = this.zzjuk;
        if (l2 != null) {
            zzq += zzflk.zzc(4, l2.longValue());
        }
        Integer num = this.count;
        return num != null ? zzq + zzflk.zzag(5, num.intValue()) : zzq;
    }
}
