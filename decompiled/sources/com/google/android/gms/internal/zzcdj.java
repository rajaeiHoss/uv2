package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcdj extends zzflm<zzcdj> {
    public zzcdk[] zzilb = zzcdk.zzawl();

    public zzcdj() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcdj)) {
            return false;
        }
        zzcdj zzcdj = (zzcdj) obj;
        if (!zzflq.equals((Object[]) this.zzilb, (Object[]) zzcdj.zzilb)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcdj.zzpvl == null || zzcdj.zzpvl.isEmpty() : this.zzpvl.equals(zzcdj.zzpvl);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzilb)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzcdk[] zzcdkArr = this.zzilb;
                int length = zzcdkArr == null ? 0 : zzcdkArr.length;
                int i = zzb + length;
                zzcdk[] zzcdkArr2 = new zzcdk[i];
                if (length != 0) {
                    System.arraycopy(zzcdkArr, 0, zzcdkArr2, 0, length);
                }
                while (length < i - 1) {
                    zzcdkArr2[length] = new zzcdk();
                    zzflj.zza(zzcdkArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzcdkArr2[length] = new zzcdk();
                zzflj.zza(zzcdkArr2[length]);
                this.zzilb = zzcdkArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzcdk[] zzcdkArr = this.zzilb;
        if (zzcdkArr != null && zzcdkArr.length > 0) {
            int i = 0;
            while (true) {
                zzcdk[] zzcdkArr2 = this.zzilb;
                if (i >= zzcdkArr2.length) {
                    break;
                }
                zzcdk zzcdk = zzcdkArr2[i];
                if (zzcdk != null) {
                    zzflk.zza(1, (zzfls) zzcdk);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzcdk[] zzcdkArr = this.zzilb;
        if (zzcdkArr != null && zzcdkArr.length > 0) {
            int i = 0;
            while (true) {
                zzcdk[] zzcdkArr2 = this.zzilb;
                if (i >= zzcdkArr2.length) {
                    break;
                }
                zzcdk zzcdk = zzcdkArr2[i];
                if (zzcdk != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzcdk);
                }
                i++;
            }
        }
        return zzq;
    }
}
