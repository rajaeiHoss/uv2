package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcod extends zzflm<zzcod> {
    public zzcoe[] zzjun = zzcoe.zzbdb();

    public zzcod() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcod)) {
            return false;
        }
        zzcod zzcod = (zzcod) obj;
        if (!zzflq.equals((Object[]) this.zzjun, (Object[]) zzcod.zzjun)) {
            return false;
        }
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzcod.zzpvl == null || zzcod.zzpvl.isEmpty() : this.zzpvl.equals(zzcod.zzpvl);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzflq.hashCode((Object[]) this.zzjun)) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 10) {
                int zzb = zzflv.zzb(zzflj, 10);
                zzcoe[] zzcoeArr = this.zzjun;
                int length = zzcoeArr == null ? 0 : zzcoeArr.length;
                int i = zzb + length;
                zzcoe[] zzcoeArr2 = new zzcoe[i];
                if (length != 0) {
                    System.arraycopy(zzcoeArr, 0, zzcoeArr2, 0, length);
                }
                while (length < i - 1) {
                    zzcoeArr2[length] = new zzcoe();
                    zzflj.zza(zzcoeArr2[length]);
                    zzflj.zzcxx();
                    length++;
                }
                zzcoeArr2[length] = new zzcoe();
                zzflj.zza(zzcoeArr2[length]);
                this.zzjun = zzcoeArr2;
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        zzcoe[] zzcoeArr = this.zzjun;
        if (zzcoeArr != null && zzcoeArr.length > 0) {
            int i = 0;
            while (true) {
                zzcoe[] zzcoeArr2 = this.zzjun;
                if (i >= zzcoeArr2.length) {
                    break;
                }
                zzcoe zzcoe = zzcoeArr2[i];
                if (zzcoe != null) {
                    zzflk.zza(1, (zzfls) zzcoe);
                }
                i++;
            }
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        zzcoe[] zzcoeArr = this.zzjun;
        if (zzcoeArr != null && zzcoeArr.length > 0) {
            int i = 0;
            while (true) {
                zzcoe[] zzcoeArr2 = this.zzjun;
                if (i >= zzcoeArr2.length) {
                    break;
                }
                zzcoe zzcoe = zzcoeArr2[i];
                if (zzcoe != null) {
                    zzq += zzflk.zzb(1, (zzfls) zzcoe);
                }
                i++;
            }
        }
        return zzq;
    }
}
