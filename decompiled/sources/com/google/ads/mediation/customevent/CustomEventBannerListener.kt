package com.google.ads.mediation.customevent

import android.view.View

@java.lang.Deprecated
interface CustomEventBannerListener : CustomEventListener {
    fun onClick()

    fun onReceivedAd(view: View)
}
