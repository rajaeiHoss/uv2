package com.google.android.gms.internal;

import java.io.IOException;

public final class zzdoi extends zzflm<zzdoi> {
    public zzdoj[] zzlyl = zzdoj.zzbmq();

    public zzdoi() {
        this.zzpnr = -1;
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzdoj[] zzdojArr = this.zzlyl;
                int length = zzdojArr == null ? 0 : zzdojArr.length;
                int i = zzb + length;
                zzdoj[] zzdojArr2 = new zzdoj[i];
                if (length != 0) {
                    System.arraycopy(zzdojArr, 0, zzdojArr2, 0, length);
                }
                while (length < i - 1) {
                    zzdojArr2[length] = new zzdoj();
                    zzflj.zza(zzdojArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzdojArr2[length] = new zzdoj();
                zzflj.zza(zzdojArr2[length]);
                this.zzlyl = zzdojArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzdoj[] zzdojArr = this.zzlyl;
        if (zzdojArr != null && zzdojArr.length > 0) {
            int i = 0;
            while (true) {
                zzdoj[] zzdojArr2 = this.zzlyl;
                if (i >= zzdojArr2.length) {
                    break;
                }
                zzdoj zzdoj = zzdojArr2[i];
                if (zzdoj != null) {
                    zzflk.zza(1, (zzfls) zzdoj);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzdoj[] zzdojArr = this.zzlyl;
        if (zzdojArr != null && zzdojArr.length > 0) {
            int i = 0;
            while (true) {
                zzdoj[] zzdojArr2 = this.zzlyl;
                if (i >= zzdojArr2.length) {
                    break;
                }
                zzdoj zzdoj = zzdojArr2[i];
                if (zzdoj != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzdoj);
                }
                i++;
            }
        }
        return zzq;
    }
}
