package com.hjq.shape.layout

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.hjq.shape.R
import com.hjq.shape.builder.ShapeDrawableBuilder
import com.hjq.shape.styleable.ShapeConstraintLayoutStyleable

class ShapeConstraintLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val mShapeDrawableBuilder: ShapeDrawableBuilder

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeConstraintLayout)
        val shapeDrawableBuilder = ShapeDrawableBuilder(this, typedArray, STYLEABLE)
        mShapeDrawableBuilder = shapeDrawableBuilder
        typedArray.recycle()
        shapeDrawableBuilder.intoBackground()
    }

    fun getShapeDrawableBuilder(): ShapeDrawableBuilder {
        return mShapeDrawableBuilder
    }

    companion object {
        private val STYLEABLE: ShapeConstraintLayoutStyleable = ShapeConstraintLayoutStyleable()
    }
}
