package com.hjq.shape.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatCheckBox
import com.hjq.shape.R
import com.hjq.shape.builder.ButtonDrawableBuilder
import com.hjq.shape.builder.ShapeDrawableBuilder
import com.hjq.shape.builder.TextColorBuilder
import com.hjq.shape.styleable.ShapeCheckBoxStyleable

class ShapeCheckBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.checkboxStyle
) : AppCompatCheckBox(context, attrs, defStyleAttr) {
    private val mShapeDrawableBuilder: ShapeDrawableBuilder
    private val mTextColorBuilder: TextColorBuilder
    private val mButtonDrawableBuilder: ButtonDrawableBuilder

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeCheckBox)
        val styleable = STYLEABLE
        val shapeDrawableBuilder = ShapeDrawableBuilder(this, typedArray, styleable)
        mShapeDrawableBuilder = shapeDrawableBuilder
        val textColorBuilder = TextColorBuilder(this, typedArray, styleable)
        mTextColorBuilder = textColorBuilder
        val buttonDrawableBuilder = ButtonDrawableBuilder(this, typedArray, styleable)
        mButtonDrawableBuilder = buttonDrawableBuilder
        typedArray.recycle()
        shapeDrawableBuilder.intoBackground()
        if (textColorBuilder.isTextGradientColor) {
            setText(text)
        } else {
            textColorBuilder.intoTextColor()
        }
        buttonDrawableBuilder.intoButtonDrawable()
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

    override fun setButtonDrawable(drawable: Drawable?) {
        super.setButtonDrawable(drawable)
        mButtonDrawableBuilder.setButtonDrawable(drawable)
    }

    fun getShapeDrawableBuilder(): ShapeDrawableBuilder {
        return mShapeDrawableBuilder
    }

    fun getTextColorBuilder(): TextColorBuilder {
        return mTextColorBuilder
    }

    fun getButtonDrawableBuilder(): ButtonDrawableBuilder {
        return mButtonDrawableBuilder
    }

    companion object {
        private val STYLEABLE: ShapeCheckBoxStyleable = ShapeCheckBoxStyleable()
    }
}
