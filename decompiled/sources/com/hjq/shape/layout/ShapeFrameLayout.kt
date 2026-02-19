package com.hjq.shape.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.hjq.shape.R
import com.hjq.shape.builder.ShapeDrawableBuilder
import com.hjq.shape.styleable.ShapeFrameLayoutStyleable

class ShapeFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val mShapeDrawableBuilder: ShapeDrawableBuilder

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeFrameLayout)
        val shapeDrawableBuilder = ShapeDrawableBuilder(this, typedArray, STYLEABLE)
        mShapeDrawableBuilder = shapeDrawableBuilder
        typedArray.recycle()
        shapeDrawableBuilder.intoBackground()
    }

    fun getShapeDrawableBuilder(): ShapeDrawableBuilder {
        return mShapeDrawableBuilder
    }

    companion object {
        private val STYLEABLE: ShapeFrameLayoutStyleable = ShapeFrameLayoutStyleable()
    }
}
