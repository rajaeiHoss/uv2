package com.hjq.shape.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.hjq.shape.R
import com.hjq.shape.builder.ShapeDrawableBuilder
import com.hjq.shape.builder.TextColorBuilder
import com.hjq.shape.styleable.ShapeButtonStyleable

class ShapeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {
    private val mShapeDrawableBuilder: ShapeDrawableBuilder
    private val mTextColorBuilder: TextColorBuilder

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeButton)
        val styleable = STYLEABLE
        val shapeDrawableBuilder = ShapeDrawableBuilder(this, typedArray, styleable)
        mShapeDrawableBuilder = shapeDrawableBuilder
        val textColorBuilder = TextColorBuilder(this, typedArray, styleable)
        mTextColorBuilder = textColorBuilder
        typedArray.recycle()
        shapeDrawableBuilder.intoBackground()
        if (textColorBuilder.isTextGradientColor) {
            setText(text)
        } else {
            textColorBuilder.intoTextColor()
        }
    }

    override fun setTextColor(color: Int) {
        super.setTextColor(color)
        mTextColorBuilder.setTextColor(color)
        mTextColorBuilder.clearTextGradientColor()
    }

    override fun setText(text: CharSequence?, type: TextView.BufferType?) {
        if (!mTextColorBuilder.isTextGradientColor) {
            super.setText(text, type)
        } else {
            super.setText(mTextColorBuilder.buildLinearGradientSpannable(text), type)
        }
    }

    fun getShapeDrawableBuilder(): ShapeDrawableBuilder {
        return mShapeDrawableBuilder
    }

    fun getTextColorBuilder(): TextColorBuilder {
        return mTextColorBuilder
    }

    companion object {
        private val STYLEABLE: ShapeButtonStyleable = ShapeButtonStyleable()
    }
}
