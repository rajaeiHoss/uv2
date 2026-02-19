package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdni extends zzflm<zzdni> {
    public zzdnj[] zzlwm = zzdnj.zzbmb();

    public zzdni() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public static zzdni zzad(byte[] bArr) throws zzflr {
        return (zzdni) zzfls.zza(new zzdni(), bArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdni)) {
            return false;
        }
        zzdni zzdni = (zzdni) obj;
        if (!zzflq.equals((Object[]) this.zzlwm, (Object[]) zzdni.zzlwm)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzdni.zzpvl == null || zzdni.zzpvl.isEmpty() : this.zzpvl.equals(zzdni.zzpvl);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzlwm)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzdnj[] zzdnjArr = this.zzlwm;
                int length = zzdnjArr == null ? 0 : zzdnjArr.length;
                int i = zzb + length;
                zzdnj[] zzdnjArr2 = new zzdnj[i];
                if (length != 0) {
                    System.arraycopy(zzdnjArr, 0, zzdnjArr2, 0, length);
                }
                while (length < i - 1) {
                    zzdnjArr2[length] = new zzdnj();
                    zzflj.zza(zzdnjArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzdnjArr2[length] = new zzdnj();
                zzflj.zza(zzdnjArr2[length]);
                this.zzlwm = zzdnjArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzdnj[] zzdnjArr = this.zzlwm;
        if (zzdnjArr != null && zzdnjArr.length > 0) {
            int i = 0;
            while (true) {
                zzdnj[] zzdnjArr2 = this.zzlwm;
                if (i >= zzdnjArr2.length) {
                    break;
                }
                zzdnj zzdnj = zzdnjArr2[i];
                if (zzdnj != null) {
                    zzflk.zza(1, (zzfls) zzdnj);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzdnj[] zzdnjArr = this.zzlwm;
        if (zzdnjArr != null && zzdnjArr.length > 0) {
            int i = 0;
            while (true) {
                zzdnj[] zzdnjArr2 = this.zzlwm;
                if (i >= zzdnjArr2.length) {
                    break;
                }
                zzdnj zzdnj = zzdnjArr2[i];
                if (zzdnj != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzdnj);
                }
                i++;
            }
        }
        return zzq;
    }
}
