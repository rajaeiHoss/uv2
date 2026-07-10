package com.google.android.gms.ads.mediation.customevent

interface CustomEvent {
    fun onDestroy()

    fun onPause()

    fun onResume()
}
