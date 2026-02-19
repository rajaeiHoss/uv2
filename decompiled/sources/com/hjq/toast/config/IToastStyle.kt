package com.hjq.toast.config

import android.content.Context
import android.view.View

interface IToastStyle<V : View> {
    fun createView(context: Context): V

    fun getGravity(): Int = 17

    fun getHorizontalMargin(): Float = 0.0f

    fun getVerticalMargin(): Float = 0.0f

    fun getXOffset(): Int = 0

    fun getYOffset(): Int = 0
}
