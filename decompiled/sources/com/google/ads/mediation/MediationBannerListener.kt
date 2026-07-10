package com.google.ads.mediation

import com.google.ads.AdRequest

@java.lang.Deprecated
interface MediationBannerListener {
    fun onClick(adapter: MediationBannerAdapter<*, *>)

    fun onDismissScreen(adapter: MediationBannerAdapter<*, *>)

    fun onFailedToReceiveAd(
        adapter: MediationBannerAdapter<*, *>,
        errorCode: AdRequest.ErrorCode,
    )

    fun onLeaveApplication(adapter: MediationBannerAdapter<*, *>)

    fun onPresentScreen(adapter: MediationBannerAdapter<*, *>)

    fun onReceivedAd(adapter: MediationBannerAdapter<*, *>)
}
