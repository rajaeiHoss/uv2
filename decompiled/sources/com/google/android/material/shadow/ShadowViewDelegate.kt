package com.google.android.material.shadow

import android.graphics.drawable.Drawable

interface ShadowViewDelegate {
    fun getRadius(): Float

    fun isCompatPaddingEnabled(): Boolean

    fun setBackgroundDrawable(background: Drawable)

    fun setShadowPadding(left: Int, top: Int, right: Int, bottom: Int)
}
