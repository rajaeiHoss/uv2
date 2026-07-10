package com.google.android.material.shape

import android.graphics.RectF

interface CornerSize {
    fun getCornerSize(bounds: RectF): Float
}
