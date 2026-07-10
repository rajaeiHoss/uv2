package com.google.android.gms.ads.mediation

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.gms.ads.AdSize

interface MediationBannerAdapter : MediationAdapter {
    val bannerView: View

    fun requestBannerAd(
        context: Context,
        listener: MediationBannerListener,
        serverParameters: Bundle,
        adSize: AdSize,
        mediationAdRequest: MediationAdRequest,
        mediationExtras: Bundle,
    )
}
