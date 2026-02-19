package com.hjq.widget.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.hjq.widget.R

class ScaleImageView : AppCompatImageView {
    private var mScaleSize = 1.2f

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleImageView)
        setScaleSize(typedArray.getFloat(R.styleable.ScaleImageView_scaleRatio, mScaleSize))
        typedArray.recycle()
    }

    override fun dispatchSetPressed(pressed: Boolean) {
        if (pressed) {
            scaleX = mScaleSize
            scaleY = mScaleSize
            return
        }
        scaleX = 1f
        scaleY = 1f
    }

    fun setScaleSize(scaleSize: Float) {
        mScaleSize = scaleSize
    }
}
