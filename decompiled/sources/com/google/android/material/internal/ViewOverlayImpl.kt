package com.google.android.material.internal

import android.graphics.drawable.Drawable

interface ViewOverlayImpl {
    fun add(drawable: Drawable)

    fun remove(drawable: Drawable)
}
