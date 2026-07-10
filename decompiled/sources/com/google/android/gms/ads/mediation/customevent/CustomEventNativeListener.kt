package com.google.android.gms.ads.mediation.customevent

import com.google.android.gms.ads.mediation.NativeAdMapper

interface CustomEventNativeListener : CustomEventListener {
    fun onAdImpression()

    fun onAdLoaded(mapper: NativeAdMapper)
}
