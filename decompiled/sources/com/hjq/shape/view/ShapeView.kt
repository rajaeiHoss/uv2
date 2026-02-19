package com.hjq.shape.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hjq.shape.R
import com.hjq.shape.builder.ShapeDrawableBuilder
import com.hjq.shape.styleable.ShapeViewStyleable

class ShapeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mShapeDrawableBuilder: ShapeDrawableBuilder

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeView)
        val shapeDrawableBuilder = ShapeDrawableBuilder(this, typedArray, STYLEABLE)
        mShapeDrawableBuilder = shapeDrawableBuilder
        typedArray.recycle()
        shapeDrawableBuilder.intoBackground()
    }

    fun getShapeDrawableBuilder(): ShapeDrawableBuilder {
        return mShapeDrawableBuilder
    }

    companion object {
        private val STYLEABLE: ShapeViewStyleable = ShapeViewStyleable()
    }
}
