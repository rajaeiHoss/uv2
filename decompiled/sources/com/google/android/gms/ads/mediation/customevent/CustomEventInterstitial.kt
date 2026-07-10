package com.google.android.gms.ads.mediation.customevent

import android.content.Context
import android.os.Bundle
import com.google.android.gms.ads.mediation.MediationAdRequest

interface CustomEventInterstitial : CustomEvent {
    fun requestInterstitialAd(
        context: Context,
        listener: CustomEventInterstitialListener,
        serverParameter: String,
        mediationAdRequest: MediationAdRequest,
        customEventExtras: Bundle,
    )

    fun showInterstitial()
}
