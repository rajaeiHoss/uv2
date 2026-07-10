package com.google.android.gms.ads.mediation

import android.content.Context
import android.os.Bundle

interface MediationInterstitialAdapter : MediationAdapter {
    fun requestInterstitialAd(
        context: Context,
        listener: MediationInterstitialListener,
        serverParameters: Bundle,
        mediationAdRequest: MediationAdRequest,
        mediationExtras: Bundle,
    )

    fun showInterstitial()
}
