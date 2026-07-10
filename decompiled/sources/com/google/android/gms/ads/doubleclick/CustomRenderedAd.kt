package com.google.android.gms.ads.doubleclick

import android.view.View

interface CustomRenderedAd {
    val baseUrl: String

    val content: String

    fun onAdRendered(view: View?)

    fun recordClick()

    fun recordImpression()
}
