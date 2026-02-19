package com.hjq.widget.layout

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure
import com.hjq.widget.R

class RatioFrameLayout : FrameLayout {
    private var mHeightRatio = 0f
    private var mWidthRatio = 0f

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioFrameLayout)
        val ratio = typedArray.getString(R.styleable.RatioFrameLayout_sizeRatio)
        if (!TextUtils.isEmpty(ratio)) {
            val split = ratio!!.split(":")
            when (split.size) {
                1 -> {
                    mWidthRatio = split[0].toFloat()
                    mHeightRatio = 1f
                }

                2 -> {
                    mWidthRatio = split[0].toFloat()
                    mHeightRatio = split[1].toFloat()
                }

                else -> {
                    throw IllegalArgumentException("are you ok?")
                }
            }
        }
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthSpec = widthMeasureSpec
        var heightSpec = heightMeasureSpec
        if (mWidthRatio != 0f && mHeightRatio != 0f) {
            val sizeRatio = getSizeRatio()
            val params = layoutParams
            val widthMode = View.MeasureSpec.getMode(widthSpec)
            val widthSize = View.MeasureSpec.getSize(widthSpec)
            val heightMode = View.MeasureSpec.getMode(heightSpec)
            val heightSize = View.MeasureSpec.getSize(heightSpec)
            if (
                params.width != ViewGroup.LayoutParams.WRAP_CONTENT &&
                params.height != ViewGroup.LayoutParams.WRAP_CONTENT &&
                widthMode == View.MeasureSpec.EXACTLY &&
                heightMode == View.MeasureSpec.EXACTLY
            ) {
                val width = widthSize.toFloat()
                val targetHeight = width / sizeRatio
                val height = heightSize.toFloat()
                if (targetHeight <= height) {
                    heightSpec = View.MeasureSpec.makeMeasureSpec(targetHeight.toInt(), BasicMeasure.EXACTLY)
                } else {
                    val targetWidth = height * sizeRatio
                    if (targetWidth <= width) {
                        widthSpec = View.MeasureSpec.makeMeasureSpec(targetWidth.toInt(), BasicMeasure.EXACTLY)
                    }
                }
            } else if (
                params.width != ViewGroup.LayoutParams.WRAP_CONTENT &&
                widthMode == View.MeasureSpec.EXACTLY &&
                heightMode != View.MeasureSpec.EXACTLY
            ) {
                heightSpec = View.MeasureSpec.makeMeasureSpec(
                    (widthSize.toFloat() / sizeRatio).toInt(),
                    BasicMeasure.EXACTLY
                )
            } else if (
                params.height != ViewGroup.LayoutParams.WRAP_CONTENT &&
                heightMode == View.MeasureSpec.EXACTLY &&
                widthMode != View.MeasureSpec.EXACTLY
            ) {
                widthSpec = View.MeasureSpec.makeMeasureSpec(
                    (heightSize.toFloat() * sizeRatio).toInt(),
                    BasicMeasure.EXACTLY
                )
            }
        }
        super.onMeasure(widthSpec, heightSpec)
    }

    fun getWidthRatio(): Float {
        return mWidthRatio
    }

    fun getHeightRatio(): Float {
        return mHeightRatio
    }

    fun getSizeRatio(): Float {
        return mWidthRatio / mHeightRatio
    }

    fun setSizeRatio(widthRatio: Float, heightRatio: Float) {
        mWidthRatio = widthRatio
        mHeightRatio = heightRatio
        requestLayout()
        invalidate()
    }
}
