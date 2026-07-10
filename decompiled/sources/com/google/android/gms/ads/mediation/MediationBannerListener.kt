package com.google.android.gms.ads.mediation

interface MediationBannerListener {
    fun onAdClicked(adapter: MediationBannerAdapter)

    fun onAdClosed(adapter: MediationBannerAdapter)

    fun onAdFailedToLoad(adapter: MediationBannerAdapter, errorCode: Int)

    fun onAdLeftApplication(adapter: MediationBannerAdapter)

    fun onAdLoaded(adapter: MediationBannerAdapter)

    fun onAdOpened(adapter: MediationBannerAdapter)

    fun zza(adapter: MediationBannerAdapter, name: String, value: String)
}
