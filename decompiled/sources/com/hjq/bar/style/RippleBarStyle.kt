package com.hjq.bar.style

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.TypedValue
import com.hjq.bar.TitleBarSupport

class RippleBarStyle : TransparentBarStyle() {
    override fun getLeftTitleBackground(context: Context): Drawable =
        createRippleDrawable(context) ?: super.getLeftTitleBackground(context)

    override fun getRightTitleBackground(context: Context): Drawable =
        createRippleDrawable(context) ?: super.getRightTitleBackground(context)

    open fun createRippleDrawable(context: Context): Drawable? {
        val typedValue = TypedValue()
        return if (context.theme.resolveAttribute(16843868, typedValue, true)) {
            TitleBarSupport.getDrawable(context, typedValue.resourceId)
        } else {
            null
        }
    }
}
