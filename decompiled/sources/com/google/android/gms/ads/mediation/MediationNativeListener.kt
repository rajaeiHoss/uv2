package com.google.android.gms.ads.mediation

import com.google.android.gms.ads.formats.NativeCustomTemplateAd

interface MediationNativeListener {
    fun onAdClicked(adapter: MediationNativeAdapter)

    fun onAdClosed(adapter: MediationNativeAdapter)

    fun onAdFailedToLoad(adapter: MediationNativeAdapter, errorCode: Int)

    fun onAdImpression(adapter: MediationNativeAdapter)

    fun onAdLeftApplication(adapter: MediationNativeAdapter)

    fun onAdLoaded(adapter: MediationNativeAdapter, nativeAdMapper: NativeAdMapper)

    fun onAdOpened(adapter: MediationNativeAdapter)

    fun onVideoEnd(adapter: MediationNativeAdapter)

    fun zza(adapter: MediationNativeAdapter, customTemplateAd: NativeCustomTemplateAd)

    fun zza(
        adapter: MediationNativeAdapter,
        customTemplateAd: NativeCustomTemplateAd,
        assetName: String,
    )

    fun zza(adapter: MediationNativeAdapter, mapper: zza)
}
