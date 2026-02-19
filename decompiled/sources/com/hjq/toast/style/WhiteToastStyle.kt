package com.hjq.toast.style

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue

class WhiteToastStyle : BlackToastStyle() {
    override fun getTextColor(context: Context): Int = -1157627904

    override fun getBackgroundDrawable(context: Context): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(-1381654)
        gradientDrawable.cornerRadius = TypedValue.applyDimension(1, 8.0f, context.resources.displayMetrics)
        return gradientDrawable
    }
}
