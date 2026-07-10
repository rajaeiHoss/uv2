package com.google.android.gms.cast.framework

interface AppVisibilityListener {
    fun onAppEnteredBackground()

    fun onAppEnteredForeground()
}
