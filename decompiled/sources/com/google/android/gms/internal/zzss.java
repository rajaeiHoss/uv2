package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzabh
public final class zzss extends zzrz {
    private final NativeCustomTemplateAd.OnCustomClickListener zzcav;

    public zzss(NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zzcav = onCustomClickListener;
    }

    public final void zzb(zzro zzro, String str) {
        this.zzcav.onCustomClick(zzrr.zza(zzro), str);
    }
}
