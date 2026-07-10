package com.google.android.gms.ads.mediation.customevent

import android.view.View

interface CustomEventBannerListener : CustomEventListener {
    fun onAdLoaded(view: View)
}
