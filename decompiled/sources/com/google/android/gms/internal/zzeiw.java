package com.google.android.gms.internal;

final class zzeiw implements zzekz<zzeiq, Void> {
    private /* synthetic */ zzeir zznie;

    zzeiw(zzeir zzeir) {
        this.zznie = zzeir;
    }

    public final Void zza(zzegu zzegu, zzeiq zzeiq, Void unused) {
        if (zzegu.isEmpty() || !zzeiq.zzbzc()) {
            for (zzelv zzcbi : zzeiq.zzbzb()) {
                zzelu zzcbi2 = zzcbi.zzcbi();
                this.zznie.zznhy.zza(zzeir.zzd(zzcbi2), this.zznie.zze(zzcbi2));
            }
            return null;
        }
        zzelu zzcbi3 = zzeiq.zzbzd().zzcbi();
        this.zznie.zznhy.zza(zzeir.zzd(zzcbi3), this.zznie.zze(zzcbi3));
        return null;
    }
}
