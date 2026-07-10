package com.google.ads.mediation

import com.google.ads.AdRequest

@java.lang.Deprecated
interface MediationInterstitialListener {
    fun onDismissScreen(adapter: MediationInterstitialAdapter<*, *>)

    fun onFailedToReceiveAd(
        adapter: MediationInterstitialAdapter<*, *>,
        errorCode: AdRequest.ErrorCode,
    )

    fun onLeaveApplication(adapter: MediationInterstitialAdapter<*, *>)

    fun onPresentScreen(adapter: MediationInterstitialAdapter<*, *>)

    fun onReceivedAd(adapter: MediationInterstitialAdapter<*, *>)
}
