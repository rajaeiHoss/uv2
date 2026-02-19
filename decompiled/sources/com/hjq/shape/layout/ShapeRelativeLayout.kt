package com.hjq.shape.layout

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.hjq.shape.R
import com.hjq.shape.builder.ShapeDrawableBuilder
import com.hjq.shape.styleable.ShapeRelativeLayoutStyleable

class ShapeRelativeLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    private val mShapeDrawableBuilder: ShapeDrawableBuilder

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeRelativeLayout)
        val shapeDrawableBuilder = ShapeDrawableBuilder(this, typedArray, STYLEABLE)
        mShapeDrawableBuilder = shapeDrawableBuilder
        typedArray.recycle()
        shapeDrawableBuilder.intoBackground()
    }

    fun getShapeDrawableBuilder(): ShapeDrawableBuilder {
        return mShapeDrawableBuilder
    }

    companion object {
        private val STYLEABLE: ShapeRelativeLayoutStyleable = ShapeRelativeLayoutStyleable()
    }
}
