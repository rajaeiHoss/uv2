package com.hjq.widget.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class SmartTextView : AppCompatTextView {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 16842884)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        refreshVisibilityStatus()
    }

    override fun setText(text: CharSequence?, type: TextView.BufferType?) {
        super.setText(text, type)
        refreshVisibilityStatus()
    }

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawables(left, top, right, bottom)
        refreshVisibilityStatus()
    }

    override fun setCompoundDrawablesRelative(
        start: Drawable?,
        top: Drawable?,
        end: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawablesRelative(start, top, end, bottom)
        refreshVisibilityStatus()
    }

    private fun refreshVisibilityStatus() {
        if (isEmptyContent() && visibility != View.GONE) {
            visibility = View.GONE
        } else if (visibility != View.VISIBLE) {
            visibility = View.VISIBLE
        }
    }

    private fun isEmptyContent(): Boolean {
        if (!TextUtils.isEmpty(text)) {
            return false
        }
        for (drawable in compoundDrawables) {
            if (drawable != null) {
                return false
            }
        }
        for (drawable in compoundDrawablesRelative) {
            if (drawable != null) {
                return false
            }
        }
        return true
    }
}
