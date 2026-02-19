package com.hjq.toast.style

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import com.hjq.toast.config.IToastStyle

open class BlackToastStyle : IToastStyle<TextView> {
    override fun getGravity(): Int = 17

    override fun getHorizontalMargin(): Float = 0.0f

    protected open fun getMaxLines(context: Context): Int = 5

    protected open fun getTextColor(context: Context): Int = -285212673

    protected open fun getTextGravity(context: Context): Int = 17

    override fun getVerticalMargin(): Float = 0.0f

    override fun getXOffset(): Int = 0

    override fun getYOffset(): Int = 0

    override fun createView(context: Context): TextView {
        val textView = TextView(context)
        textView.id = 16908299
        textView.gravity = getTextGravity(context)
        textView.setTextColor(getTextColor(context))
        textView.setTextSize(0, getTextSize(context))
        val horizontalPadding = getHorizontalPadding(context)
        val verticalPadding = getVerticalPadding(context)
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        } else {
            textView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        }
        textView.layoutParams = ViewGroup.LayoutParams(-2, -2)
        val backgroundDrawable = getBackgroundDrawable(context)
        if (Build.VERSION.SDK_INT >= 16) {
            textView.background = backgroundDrawable
        } else {
            textView.setBackgroundDrawable(backgroundDrawable)
        }
        if (Build.VERSION.SDK_INT >= 21) {
            textView.z = getTranslationZ(context)
        }
        textView.maxLines = getMaxLines(context)
        return textView
    }

    protected open fun getTextSize(context: Context): Float =
        TypedValue.applyDimension(2, 14.0f, context.resources.displayMetrics)

    protected open fun getHorizontalPadding(context: Context): Int =
        TypedValue.applyDimension(1, 24.0f, context.resources.displayMetrics).toInt()

    protected open fun getVerticalPadding(context: Context): Int =
        TypedValue.applyDimension(1, 16.0f, context.resources.displayMetrics).toInt()

    protected open fun getBackgroundDrawable(context: Context): Drawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(-2013265920)
        gradientDrawable.cornerRadius = TypedValue.applyDimension(1, 10.0f, context.resources.displayMetrics)
        return gradientDrawable
    }

    protected open fun getTranslationZ(context: Context): Float =
        TypedValue.applyDimension(1, 3.0f, context.resources.displayMetrics)
}
