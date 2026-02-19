package com.hjq.bar

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView

object TitleBarSupport {
    const val NO_COLOR: Int = 0

    @JvmStatic
    fun getDrawable(context: Context, id: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= 21) {
            context.resources.getDrawable(id, context.theme)
        } else {
            context.resources.getDrawable(id)
        }
    }

    @JvmStatic
    fun setBackground(view: View, drawable: Drawable?) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.background = drawable
        } else {
            view.setBackgroundDrawable(drawable)
        }
    }

    @JvmStatic
    fun getAbsoluteGravity(view: View, gravity: Int): Int {
        if (Build.VERSION.SDK_INT < 17) {
            return gravity
        }
        return Gravity.getAbsoluteGravity(gravity, view.resources.configuration.layoutDirection)
    }

    @JvmStatic
    fun isLayoutRtl(context: Context): Boolean {
        return Build.VERSION.SDK_INT >= 17 && context.resources.configuration.layoutDirection == 1
    }

    @JvmStatic
    fun isContainContent(textView: TextView): Boolean {
        if (!TextUtils.isEmpty(textView.text)) {
            return true
        }
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                return true
            }
        }
        return false
    }

    @JvmStatic
    fun setDrawableTint(drawable: Drawable?, color: Int) {
        if (drawable != null && color != 0) {
            drawable.mutate()
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

    @JvmStatic
    fun clearDrawableTint(drawable: Drawable?) {
        if (drawable != null) {
            drawable.mutate()
            drawable.clearColorFilter()
        }
    }

    @JvmStatic
    fun setDrawableSize(drawable: Drawable?, width: Int, height: Int) {
        if (drawable == null) {
            return
        }
        if (width <= 0 && height <= 0) {
            drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            return
        }
        if (width <= 0 || height <= 0) {
            var intrinsicWidth = drawable.intrinsicWidth
            var intrinsicHeight = drawable.intrinsicHeight
            if (intrinsicWidth <= 0) {
                intrinsicWidth = width
            }
            if (intrinsicHeight <= 0) {
                intrinsicHeight = height
            }
            if (width > 0) {
                drawable.setBounds(0, 0, width, (intrinsicHeight * width) / intrinsicWidth)
            } else {
                drawable.setBounds(0, 0, (intrinsicWidth * height) / intrinsicHeight, height)
            }
            return
        }
        drawable.setBounds(0, 0, width, height)
    }

    @JvmStatic
    fun getTextCompoundDrawable(textView: TextView, gravity: Int): Drawable? {
        val compoundDrawables = textView.compoundDrawables
        return when (getAbsoluteGravity(textView, gravity)) {
            3 -> compoundDrawables[0]
            5 -> compoundDrawables[2]
            48 -> compoundDrawables[1]
            80 -> compoundDrawables[3]
            else -> null
        }
    }

    @JvmStatic
    fun setTextCompoundDrawable(textView: TextView, drawable: Drawable?, gravity: Int) {
        when (getAbsoluteGravity(textView, gravity)) {
            3 -> textView.setCompoundDrawables(drawable, null, null, null)
            5 -> textView.setCompoundDrawables(null, null, drawable, null)
            48 -> textView.setCompoundDrawables(null, drawable, null, null)
            80 -> textView.setCompoundDrawables(null, null, null, drawable)
            else -> textView.setCompoundDrawables(null, null, null, null)
        }
    }

    @JvmStatic
    fun setTextTypeface(textView: TextView, style: Int) {
        when (style) {
            0 -> textView.setTypeface(Typeface.DEFAULT, style)
            1 -> textView.setTypeface(Typeface.DEFAULT_BOLD, style)
            2, 3 -> textView.setTypeface(Typeface.MONOSPACE, style)
        }
    }
}
