package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbs extends zzflm<zzbs> {
    public zzbr[] zzyh = zzbr.zzw();
    public zzbp zzyi = null;
    public String zzyj = "";

    public zzbs() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbs)) {
            return false;
        }
        zzbs zzbs = (zzbs) obj;
        if (!zzflq.equals((Object[]) this.zzyh, (Object[]) zzbs.zzyh)) {
            return false;
        }
        zzbp zzbp = this.zzyi;
        if (zzbp == null) {
            if (zzbs.zzyi != null) {
                return false;
            }
        } else if (!zzbp.equals(zzbs.zzyi)) {
            return false;
        }
        String str = this.zzyj;
        if (str == null) {
            if (zzbs.zzyj != null) {
                return false;
            }
        } else if (!str.equals(zzbs.zzyj)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzbs.zzpvl == null || zzbs.zzpvl.isEmpty() : this.zzpvl.equals(zzbs.zzpvl);
    }

    public final int hashCode() {
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzyh);
        zzbp zzbp = this.zzyi;
        int i = 0;
        int hashCode2 = ((hashCode * 31) + (zzbp == null ? 0 : zzbp.hashCode())) * 31;
        String str = this.zzyj;
        int hashCode3 = (hashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        if (this.zzpvl != null && !this.zzpvl.isEmpty()) {
            i = this.zzpvl.hashCode();
        }
        return hashCode3 + i;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzbr[] zzbrArr = this.zzyh;
                int length = zzbrArr == null ? 0 : zzbrArr.length;
                int i = zzb + length;
                zzbr[] zzbrArr2 = new zzbr[i];
                if (length != 0) {
                    System.arraycopy(zzbrArr, 0, zzbrArr2, 0, length);
                }
                while (length < i - 1) {
                    zzbrArr2[length] = new zzbr();
                    zzflj.zza(zzbrArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzbrArr2[length] = new zzbr();
                zzflj.zza(zzbrArr2[length]);
                this.zzyh = zzbrArr2;
            } else if (zzcxx == 18) {
                if (this.zzyi == null) {
                    this.zzyi = new zzbp();
                }
                zzflj.zza(this.zzyi);
            } else if (zzcxx == 26) {
                this.zzyj = zzflj.readString();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzbr[] zzbrArr = this.zzyh;
        if (zzbrArr != null && zzbrArr.length > 0) {
            int i = 0;
            while (true) {
                zzbr[] zzbrArr2 = this.zzyh;
                if (i >= zzbrArr2.length) {
                    break;
                }
                zzbr zzbr = zzbrArr2[i];
                if (zzbr != null) {
                    zzflk.zza(1, (zzfls) zzbr);
                }
                i++;
            }
        }
        zzbp zzbp = this.zzyi;
        if (zzbp != null) {
            zzflk.zza(2, (zzfls) zzbp);
        }
        String str = this.zzyj;
        if (str != null && !str.equals("")) {
            zzflk.zzp(3, this.zzyj);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzbr[] zzbrArr = this.zzyh;
        if (zzbrArr != null && zzbrArr.length > 0) {
            int i = 0;
            while (true) {
                zzbr[] zzbrArr2 = this.zzyh;
                if (i >= zzbrArr2.length) {
                    break;
                }
                zzbr zzbr = zzbrArr2[i];
                if (zzbr != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzbr);
                }
                i++;
            }
        }
        zzbp zzbp = this.zzyi;
        if (zzbp != null) {
            zzq += zzflk.zzb(2, (zzfls) zzbp);
        }
        String str = this.zzyj;
        return (str == null || str.equals("")) ? zzq : zzq + zzflk.zzq(3, this.zzyj);
    }
}
