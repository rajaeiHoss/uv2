package com.hjq.toast.style

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.hjq.toast.config.IToastStyle

class ViewToastStyle(
    private val mLayoutId: Int,
    private val mStyle: IToastStyle<*>?
) : IToastStyle<View> {
    override fun createView(context: Context): View =
        LayoutInflater.from(context).inflate(mLayoutId, null)

    override fun getGravity(): Int = mStyle?.getGravity() ?: 17

    override fun getXOffset(): Int = mStyle?.getXOffset() ?: 0

    override fun getYOffset(): Int = mStyle?.getYOffset() ?: 0

    override fun getHorizontalMargin(): Float = mStyle?.getHorizontalMargin() ?: 0.0f

    override fun getVerticalMargin(): Float = mStyle?.getVerticalMargin() ?: 0.0f
}
