package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfat extends zzflm<zzfat> {
    public long timestamp = 0;
    public zzfaw[] zzosw = zzfaw.zzcnr();
    public byte[][] zzosx = zzflv.zzpwd;

    public zzfat() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfat)) {
            return false;
        }
        zzfat zzfat = (zzfat) obj;
        if (zzflq.equals((Object[]) this.zzosw, (Object[]) zzfat.zzosw) && this.timestamp == zzfat.timestamp && zzflq.zza(this.zzosx, zzfat.zzosx)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzfat.zzpvl == null || zzfat.zzpvl.isEmpty() : this.zzpvl.equals(zzfat.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.timestamp;
        return ((((((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzosw)) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + zzflq.zzd(this.zzosx)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzfaw[] zzfawArr = this.zzosw;
                int length = zzfawArr == null ? 0 : zzfawArr.length;
                int i = zzb + length;
                zzfaw[] zzfawArr2 = new zzfaw[i];
                if (length != 0) {
                    System.arraycopy(zzfawArr, 0, zzfawArr2, 0, length);
                }
                while (length < i - 1) {
                    zzfawArr2[length] = new zzfaw();
                    zzflj.zza(zzfawArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzfawArr2[length] = new zzfaw();
                zzflj.zza(zzfawArr2[length]);
                this.zzosw = zzfawArr2;
            } else if (zzcxx == 17) {
                this.timestamp = zzflj.zzcyt();
            } else if (zzcxx == 26) {
                int zzb2 = zzflv.zzb(zzflj, 26);
                byte[][] bArr = this.zzosx;
                int length2 = bArr == null ? 0 : bArr.length;
                int i2 = zzb2 + length2;
                byte[][] bArr2 = new byte[i2][];
                if (length2 != 0) {
                    System.arraycopy(bArr, 0, bArr2, 0, length2);
                }
                while (length2 < i2 - 1) {
                    bArr2[length2] = zzflj.readBytes();
                    zzflj.zzcxx();
                    length2++;
                }
                bArr2[length2] = zzflj.readBytes();
                this.zzosx = bArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzfaw[] zzfawArr = this.zzosw;
        int i = 0;
        if (zzfawArr != null && zzfawArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfaw[] zzfawArr2 = this.zzosw;
                if (i2 >= zzfawArr2.length) {
                    break;
                }
                zzfaw zzfaw = zzfawArr2[i2];
                if (zzfaw != null) {
                    zzflk.zza(1, (zzfls) zzfaw);
                }
                i2++;
            }
        }
        long j = this.timestamp;
        if (j != 0) {
            zzflk.zzb(2, j);
        }
        byte[][] bArr = this.zzosx;
        if (bArr != null && bArr.length > 0) {
            while (true) {
                byte[][] bArr2 = this.zzosx;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzflk.zzc(3, bArr3);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzfaw[] zzfawArr = this.zzosw;
        int i = 0;
        if (zzfawArr != null && zzfawArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzfaw[] zzfawArr2 = this.zzosw;
                if (i2 >= zzfawArr2.length) {
                    break;
                }
                zzfaw zzfaw = zzfawArr2[i2];
                if (zzfaw != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzfaw);
                }
                i2++;
            }
        }
        if (this.timestamp != 0) {
            zzq += zzflk.zzlw(2) + 8;
        }
        byte[][] bArr = this.zzosx;
        if (bArr == null || bArr.length <= 0) {
            return zzq;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            byte[][] bArr2 = this.zzosx;
            if (i >= bArr2.length) {
                return zzq + i3 + (i4 * 1);
            }
            byte[] bArr3 = bArr2[i];
            if (bArr3 != null) {
                i4++;
                i3 += zzflk.zzbg(bArr3);
            }
            i++;
        }
    }
}
