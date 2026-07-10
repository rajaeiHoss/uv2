package com.google.ads.mediation.customevent

import android.app.Activity
import com.google.ads.mediation.MediationAdRequest

@java.lang.Deprecated
interface CustomEventInterstitial : CustomEvent {
    fun requestInterstitialAd(
        listener: CustomEventInterstitialListener,
        activity: Activity,
        label: String,
        serverParameter: String,
        mediationAdRequest: MediationAdRequest,
        customEventExtras: Any?,
    )

    fun showInterstitial()
}
