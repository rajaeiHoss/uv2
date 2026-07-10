package com.google.ads.mediation

import android.app.Activity
import android.view.View
import com.google.ads.AdSize

@java.lang.Deprecated
interface MediationBannerAdapter<
    ADDITIONAL_PARAMETERS : NetworkExtras,
    SERVER_PARAMETERS : MediationServerParameters,
> : MediationAdapter<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS> {
    val bannerView: View

    fun requestBannerAd(
        listener: MediationBannerListener,
        activity: Activity,
        serverParameters: SERVER_PARAMETERS,
        adSize: AdSize,
        mediationAdRequest: MediationAdRequest,
        additionalParameters: ADDITIONAL_PARAMETERS,
    )
}
