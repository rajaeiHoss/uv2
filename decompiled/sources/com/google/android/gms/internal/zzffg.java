package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffg extends zzflm<zzffg> {
    public int zzpkl = 0;
    public long zzpkm = 0;
    public zzffh[] zzpko = zzffh.zzcxh();

    public zzffg() {
        this.zzpnr = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzan */
    public final zzffg zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                try {
                    int zzcym = zzflj.zzcym();
                    if (!(zzcym == 0 || zzcym == 1 || zzcym == 2)) {
                        if (zzcym != 3) {
                            StringBuilder sb = new StringBuilder(43);
                            sb.append(zzcym);
                            sb.append(" is not a valid enum TriggerType");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                    this.zzpkl = zzcym;
                } catch (IllegalArgumentException unused) {
                    zzflj.zzmw(zzflj.getPosition());
                    zza(zzflj, zzcxx);
                }
            } else if (zzcxx == 24) {
                this.zzpkm = zzflj.zzcyr();
            } else if (zzcxx == 34) {
                int zzb = zzflv.zzb(zzflj, 34);
                zzffh[] zzffhArr = this.zzpko;
                int length = zzffhArr == null ? 0 : zzffhArr.length;
                int i = zzb + length;
                zzffh[] zzffhArr2 = new zzffh[i];
                if (length != 0) {
                    System.arraycopy(zzffhArr, 0, zzffhArr2, 0, length);
                }
                while (length < i - 1) {
                    zzffhArr2[length] = new zzffh();
                    zzflj.zza(zzffhArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzffhArr2[length] = new zzffh();
                zzflj.zza(zzffhArr2[length]);
                this.zzpko = zzffhArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffg)) {
            return false;
        }
        zzffg zzffg = (zzffg) obj;
        if (this.zzpkl == zzffg.zzpkl && this.zzpkm == zzffg.zzpkm && zzflq.equals((Object[]) this.zzpko, (Object[]) zzffg.zzpko)) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffg.zzpvl == null || zzffg.zzpvl.isEmpty() : this.zzpvl.equals(zzffg.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzpkm;
        return ((((((((getClass().getName().hashCode() + 527) * 31) + this.zzpkl) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + zzflq.hashCode((Object[]) this.zzpko)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.zzpkl;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        long j = this.zzpkm;
        if (j != 0) {
            zzflk.zzf(3, j);
        }
        zzffh[] zzffhArr = this.zzpko;
        if (zzffhArr != null && zzffhArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzffh[] zzffhArr2 = this.zzpko;
                if (i2 >= zzffhArr2.length) {
                    break;
                }
                zzffh zzffh = zzffhArr2[i2];
                if (zzffh != null) {
                    zzflk.zza(4, (zzfls) zzffh);
                }
                i2++;
            }
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
        long j = this.zzpkm;
        if (j != 0) {
            zzq += zzflk.zzc(3, j);
        }
        zzffh[] zzffhArr = this.zzpko;
        if (zzffhArr != null && zzffhArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzffh[] zzffhArr2 = this.zzpko;
                if (i2 >= zzffhArr2.length) {
                    break;
                }
                zzffh zzffh = zzffhArr2[i2];
                if (zzffh != null) {
                    zzq += zzflk.zzb(4, (zzfls) zzffh);
                }
                i2++;
            }
        }
        return zzq;
    }
}
