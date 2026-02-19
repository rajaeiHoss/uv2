package com.hjq.widget.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.hjq.widget.R

class DrawableTextView : AppCompatTextView {
    private var mDrawableHeight = 0
    private var mDrawableWidth = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView)
        mDrawableWidth = typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_drawableWidth, 0)
        mDrawableHeight = typedArray.getDimensionPixelSize(R.styleable.DrawableTextView_drawableHeight, 0)
        typedArray.recycle()
        refreshDrawablesSize()
    }

    fun setDrawableSize(drawableWidth: Int, drawableHeight: Int) {
        mDrawableWidth = drawableWidth
        mDrawableHeight = drawableHeight
        if (isAttachedToWindow) {
            refreshDrawablesSize()
        }
    }

    fun setDrawableWidth(drawableWidth: Int) {
        mDrawableWidth = drawableWidth
        if (isAttachedToWindow) {
            refreshDrawablesSize()
        }
    }

    fun setDrawableHeight(drawableHeight: Int) {
        mDrawableHeight = drawableHeight
        if (isAttachedToWindow) {
            refreshDrawablesSize()
        }
    }

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawables(left, top, right, bottom)
        if (isAttachedToWindow) {
            refreshDrawablesSize()
        }
    }

    override fun setCompoundDrawablesRelative(
        start: Drawable?,
        top: Drawable?,
        end: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawablesRelative(start, top, end, bottom)
        if (isAttachedToWindow) {
            refreshDrawablesSize()
        }
    }

    private fun refreshDrawablesSize() {
        if (mDrawableWidth != 0 && mDrawableHeight != 0) {
            val drawables = compoundDrawables
            if (drawables[0] == null && drawables[1] == null) {
                val relativeDrawables = compoundDrawablesRelative
                super.setCompoundDrawablesRelative(
                    limitDrawableSize(relativeDrawables[0]),
                    limitDrawableSize(relativeDrawables[1]),
                    limitDrawableSize(relativeDrawables[2]),
                    limitDrawableSize(relativeDrawables[3])
                )
                return
            }
            super.setCompoundDrawables(
                limitDrawableSize(drawables[0]),
                limitDrawableSize(drawables[1]),
                limitDrawableSize(drawables[2]),
                limitDrawableSize(drawables[3])
            )
        }
    }

    private fun limitDrawableSize(drawable: Drawable?): Drawable? {
        if (drawable == null) {
            return null
        }
        if (mDrawableWidth != 0 && mDrawableHeight != 0) {
            drawable.setBounds(0, 0, mDrawableWidth, mDrawableHeight)
        }
        return drawable
    }
}
