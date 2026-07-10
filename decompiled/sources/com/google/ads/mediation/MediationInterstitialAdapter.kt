package com.google.ads.mediation

import android.app.Activity

@java.lang.Deprecated
interface MediationInterstitialAdapter<
    ADDITIONAL_PARAMETERS : NetworkExtras,
    SERVER_PARAMETERS : MediationServerParameters,
> : MediationAdapter<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS> {
    fun requestInterstitialAd(
        listener: MediationInterstitialListener,
        activity: Activity,
        serverParameters: SERVER_PARAMETERS,
        mediationAdRequest: MediationAdRequest,
        additionalParameters: ADDITIONAL_PARAMETERS,
    )

    fun showInterstitial()
}
