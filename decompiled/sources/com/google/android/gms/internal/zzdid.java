package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public abstract class zzdid {
    private zze zzata;
    private int zzbkq;
    protected final zzczp zzkvu;
    protected final zzdim zzlay;
    private zzdii zzlaz;

    public zzdid(int i, zzdim zzdim, zzdii zzdii, zzczp zzczp) {
        this(i, zzdim, zzdii, zzczp, zzi.zzanq());
    }

    private zzdid(int i, zzdim zzdim, zzdii zzdii, zzczp zzczp, zze zze) {
        this.zzlay = (zzdim) zzbq.checkNotNull(zzdim);
        zzbq.checkNotNull(zzdim.zzbjt());
        this.zzbkq = i;
        this.zzlaz = (zzdii) zzbq.checkNotNull(zzdii);
        this.zzata = (zze) zzbq.checkNotNull(zze);
        this.zzkvu = zzczp;
    }

    private final zzdin zzab(byte[] bArr) {
        zzdin zzdin;
        try {
            zzdin = this.zzlaz.zzac(bArr);
            if (zzdin == null) {
                zzdal.zzcy("Parsed resource from is null");
            }
        } catch (zzdib unused2) {
            zzdin = null;
            zzdal.zzcy("Resource data is corrupted");
            return zzdin;
        }
        return zzdin;
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzdin zzdin);

    public final void zzaa(byte[] bArr) {
        zzdin zzdin;
        zzdin zzab = zzab(bArr);
        zzczp zzczp = this.zzkvu;
        if (zzczp != null && this.zzbkq == 0) {
            zzczp.zzbfz();
        }
        if (zzab == null || zzab.getStatus() != Status.zzftq) {
            zzdin = new zzdin(Status.zzfts, this.zzbkq);
        } else {
            byte[] bArr2 = bArr;
            zzdin = new zzdin(Status.zzftq, this.zzbkq, new zzdio(this.zzlay.zzbjt(), bArr2, zzab.zzbju().zzbjz(), this.zzata.currentTimeMillis()), zzab.zzbjv());
        }
        zza(zzdin);
    }

    public final void zzo(int i, int i2) {
        zzczp zzczp = this.zzkvu;
        if (zzczp != null && i2 == 0 && i == 3) {
            zzczp.zzbfy();
        }
        String containerId = this.zzlay.zzbjt().getContainerId();
        String str = i != 0 ? i != 1 ? i != 2 ? "Unknown reason" : "Server error" : "IOError" : "Resource not available";
        StringBuilder sb = new StringBuilder(String.valueOf(containerId).length() + 61 + str.length());
        sb.append("Failed to fetch the container resource for the container \"");
        sb.append(containerId);
        sb.append("\": ");
        sb.append(str);
        zzdal.v(sb.toString());
        zza(new zzdin(Status.zzfts, i2));
    }
}
