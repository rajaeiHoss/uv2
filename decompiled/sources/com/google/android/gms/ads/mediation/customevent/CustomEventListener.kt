package com.google.android.gms.ads.mediation.customevent

interface CustomEventListener {
    fun onAdClicked()

    fun onAdClosed()

    fun onAdFailedToLoad(errorCode: Int)

    fun onAdLeftApplication()

    fun onAdOpened()
}
