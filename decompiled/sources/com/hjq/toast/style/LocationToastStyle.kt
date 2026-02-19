package com.hjq.toast.style

import android.content.Context
import android.view.View
import com.hjq.toast.config.IToastStyle

class LocationToastStyle(
    private val mStyle: IToastStyle<*>?,
    private val mGravity: Int,
    private val mXOffset: Int,
    private val mYOffset: Int,
    private val mHorizontalMargin: Float,
    private val mVerticalMargin: Float
) : IToastStyle<View> {

    constructor(style: IToastStyle<*>?, gravity: Int) : this(style, gravity, 0, 0, 0.0f, 0.0f)

    override fun createView(context: Context): View = mStyle!!.createView(context)

    override fun getGravity(): Int = mGravity

    override fun getXOffset(): Int = mXOffset

    override fun getYOffset(): Int = mYOffset

    override fun getHorizontalMargin(): Float = mHorizontalMargin

    override fun getVerticalMargin(): Float = mVerticalMargin
}
