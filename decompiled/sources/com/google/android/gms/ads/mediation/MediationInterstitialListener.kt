package com.google.android.gms.ads.mediation

interface MediationInterstitialListener {
    fun onAdClicked(adapter: MediationInterstitialAdapter)

    fun onAdClosed(adapter: MediationInterstitialAdapter)

    fun onAdFailedToLoad(adapter: MediationInterstitialAdapter, errorCode: Int)

    fun onAdLeftApplication(adapter: MediationInterstitialAdapter)

    fun onAdLoaded(adapter: MediationInterstitialAdapter)

    fun onAdOpened(adapter: MediationInterstitialAdapter)
}
