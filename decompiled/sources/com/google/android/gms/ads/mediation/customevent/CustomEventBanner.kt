package com.google.android.gms.ads.mediation.customevent

import android.content.Context
import android.os.Bundle
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.mediation.MediationAdRequest

interface CustomEventBanner : CustomEvent {
    fun requestBannerAd(
        context: Context,
        listener: CustomEventBannerListener,
        serverParameter: String,
        adSize: AdSize,
        mediationAdRequest: MediationAdRequest,
        customEventExtras: Bundle,
    )
}
