package com.google.ads.mediation.customevent

import android.app.Activity
import com.google.ads.AdSize
import com.google.ads.mediation.MediationAdRequest

@java.lang.Deprecated
interface CustomEventBanner : CustomEvent {
    fun requestBannerAd(
        listener: CustomEventBannerListener,
        activity: Activity,
        label: String,
        serverParameter: String,
        adSize: AdSize,
        mediationAdRequest: MediationAdRequest,
        customEventExtras: Any?,
    )
}
