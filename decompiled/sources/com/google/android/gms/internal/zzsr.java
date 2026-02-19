package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeContentAd;

@zzabh
public final class zzsr extends zzrw {
    private final NativeContentAd.OnContentAdLoadedListener zzcau;

    public zzsr(NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzcau = onContentAdLoadedListener;
    }

    public final void zza(zzrk zzrk) {
        this.zzcau.onContentAdLoaded(new zzrn(zzrk));
    }
}
