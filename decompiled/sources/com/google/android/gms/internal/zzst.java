package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

@zzabh
public final class zzst extends zzsc {
    private final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzcaw;

    public zzst(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener) {
        this.zzcaw = onCustomTemplateAdLoadedListener;
    }

    public final void zzb(zzro zzro) {
        this.zzcaw.onCustomTemplateAdLoaded(zzrr.zza(zzro));
    }
}
