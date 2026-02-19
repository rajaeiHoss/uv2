package com.hjq.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

open class SimpleLayout : ViewGroup {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
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
    )

    override fun shouldDelayChildPressedState(): Boolean {
        return false
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childCount = childCount
        var maxWidth = 0
        var maxHeight = 0
        var childState = 0
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            if (child.visibility != View.GONE) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0)
                val params = child.layoutParams as MarginLayoutParams
                maxWidth = max(
                    maxWidth,
                    child.measuredWidth + params.leftMargin + params.rightMargin
                )
                maxHeight = max(
                    maxHeight,
                    child.measuredHeight + params.topMargin + params.bottomMargin
                )
                childState = combineMeasuredStates(childState, child.measuredState)
            }
        }
        val measuredWidth = maxWidth + paddingLeft + paddingRight
        val measuredHeight = maxHeight + paddingTop + paddingBottom
        setMeasuredDimension(
            resolveSizeAndState(max(measuredWidth, suggestedMinimumWidth), widthMeasureSpec, childState),
            resolveSizeAndState(
                max(measuredHeight, suggestedMinimumHeight),
                heightMeasureSpec,
                childState shl 16
            )
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val childCount = childCount
        for (index in 0 until childCount) {
            val child = getChildAt(index)
            val params = child.layoutParams as MarginLayoutParams
            val childLeft = params.leftMargin + paddingLeft
            val childTop = params.topMargin + paddingTop
            child.layout(
                childLeft,
                childTop,
                child.measuredWidth + childLeft + paddingRight + params.rightMargin,
                child.measuredHeight + childTop + paddingBottom + params.bottomMargin
            )
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(-1, -1)
    }

    override fun generateLayoutParams(params: LayoutParams): LayoutParams {
        return MarginLayoutParams(params)
    }

    override fun checkLayoutParams(params: LayoutParams): Boolean {
        return params is MarginLayoutParams
    }
}
