package com.google.ads.mediation.customevent

@java.lang.Deprecated
interface CustomEventListener {
    fun onDismissScreen()

    fun onFailedToReceiveAd()

    fun onLeaveApplication()

    fun onPresentScreen()
}
