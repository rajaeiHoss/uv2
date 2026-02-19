package com.hjq.widget.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class PressAlphaTextView : AppCompatTextView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun dispatchSetPressed(pressed: Boolean) {
        if (pressed) {
            alpha = 0.5f
        } else {
            alpha = 1f
        }
    }
}
