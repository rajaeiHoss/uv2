package com.hjq.shape.builder

import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.text.SpannableStringBuilder
import android.widget.TextView
import com.hjq.shape.other.LinearGradientFontSpan
import com.hjq.shape.styleable.ITextColorStyleable

class TextColorBuilder(
    private val mTextView: TextView,
    typedArray: TypedArray,
    iTextColorStyleable: ITextColorStyleable
) {
    private var mTextCheckedColor: Int? = null
    private var mTextColor: Int
    private var mTextDisabledColor: Int? = null
    private var mTextFocusedColor: Int? = null
    private var mTextGradientColor: IntArray? = null
    private var mTextGradientOrientation: Int
    private var mTextPressedColor: Int? = null
    private var mTextSelectedColor: Int? = null

    init {
        mTextColor = typedArray.getColor(
            iTextColorStyleable.getTextColorStyleable(),
            mTextView.textColors.defaultColor
        )
        if (typedArray.hasValue(iTextColorStyleable.getTextPressedColorStyleable())) {
            mTextPressedColor =
                typedArray.getColor(iTextColorStyleable.getTextPressedColorStyleable(), mTextColor)
        }
        if (
            iTextColorStyleable.getTextCheckedColorStyleable() > 0 &&
            typedArray.hasValue(iTextColorStyleable.getTextCheckedColorStyleable())
        ) {
            mTextCheckedColor =
                typedArray.getColor(iTextColorStyleable.getTextCheckedColorStyleable(), mTextColor)
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextDisabledColorStyleable())) {
            mTextDisabledColor =
                typedArray.getColor(iTextColorStyleable.getTextDisabledColorStyleable(), mTextColor)
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextFocusedColorStyleable())) {
            mTextFocusedColor =
                typedArray.getColor(iTextColorStyleable.getTextFocusedColorStyleable(), mTextColor)
        }
        if (typedArray.hasValue(iTextColorStyleable.getTextSelectedColorStyleable())) {
            mTextSelectedColor =
                typedArray.getColor(iTextColorStyleable.getTextSelectedColorStyleable(), mTextColor)
        }
        if (
            typedArray.hasValue(iTextColorStyleable.getTextStartColorStyleable()) &&
            typedArray.hasValue(iTextColorStyleable.getTextEndColorStyleable())
        ) {
            mTextGradientColor =
                if (typedArray.hasValue(iTextColorStyleable.getTextCenterColorStyleable())) {
                    intArrayOf(
                        typedArray.getColor(iTextColorStyleable.getTextStartColorStyleable(), mTextColor),
                        typedArray.getColor(iTextColorStyleable.getTextCenterColorStyleable(), mTextColor),
                        typedArray.getColor(iTextColorStyleable.getTextEndColorStyleable(), mTextColor)
                    )
                } else {
                    intArrayOf(
                        typedArray.getColor(iTextColorStyleable.getTextStartColorStyleable(), mTextColor),
                        typedArray.getColor(iTextColorStyleable.getTextEndColorStyleable(), mTextColor)
                    )
                }
        }
        mTextGradientOrientation =
            typedArray.getColor(iTextColorStyleable.getTextGradientOrientationStyleable(), 0)
    }

    fun setTextColor(color: Int?): TextColorBuilder {
        mTextColor = color!!
        clearTextGradientColor()
        return this
    }

    fun getTextColor(): Int? {
        return mTextColor
    }

    fun setTextPressedColor(color: Int?): TextColorBuilder {
        mTextPressedColor = color
        return this
    }

    fun getTextPressedColor(): Int? {
        return mTextPressedColor
    }

    fun setTextCheckedColor(color: Int?): TextColorBuilder {
        mTextCheckedColor = color
        return this
    }

    fun getTextCheckedColor(): Int? {
        return mTextCheckedColor
    }

    fun setTextDisabledColor(color: Int?): TextColorBuilder {
        mTextDisabledColor = color
        return this
    }

    fun getTextDisabledColor(): Int? {
        return mTextDisabledColor
    }

    fun setTextFocusedColor(color: Int?): TextColorBuilder {
        mTextFocusedColor = color
        return this
    }

    fun getTextFocusedColor(): Int? {
        return mTextFocusedColor
    }

    fun setTextSelectedColor(color: Int?): TextColorBuilder {
        mTextSelectedColor = color
        return this
    }

    fun getTextSelectedColor(): Int? {
        return mTextSelectedColor
    }

    fun setTextGradientColor(color: IntArray?): TextColorBuilder {
        mTextGradientColor = color
        return this
    }

    fun getTextGradientColor(): IntArray? {
        return mTextGradientColor
    }

    fun setTextGradientOrientation(orientation: Int): TextColorBuilder {
        mTextGradientOrientation = orientation
        return this
    }

    fun getTextGradientOrientation(): Int? {
        return mTextGradientOrientation
    }

    val isTextGradientColor: Boolean
        get() {
            val gradientColor = mTextGradientColor
            return gradientColor != null && gradientColor.isNotEmpty()
        }

    fun clearTextGradientColor() {
        mTextGradientColor = null
    }

    fun buildLinearGradientSpannable(charSequence: CharSequence?): SpannableStringBuilder {
        return LinearGradientFontSpan.buildLinearGradientSpannable(
            charSequence!!,
            mTextGradientColor,
            null,
            mTextGradientOrientation
        )
    }

    fun buildColorState(): ColorStateList {
        if (
            mTextPressedColor == null &&
            mTextCheckedColor == null &&
            mTextDisabledColor == null &&
            mTextFocusedColor == null &&
            mTextSelectedColor == null
        ) {
            return ColorStateList.valueOf(mTextColor)
        }
        val states = ArrayList<IntArray>(6)
        val colors = ArrayList<Int>(6)
        mTextPressedColor?.let {
            states.add(intArrayOf(16842919))
            colors.add(it)
        }
        mTextCheckedColor?.let {
            states.add(intArrayOf(16842912))
            colors.add(it)
        }
        mTextDisabledColor?.let {
            states.add(intArrayOf(-16842910))
            colors.add(it)
        }
        mTextFocusedColor?.let {
            states.add(intArrayOf(16842908))
            colors.add(it)
        }
        mTextSelectedColor?.let {
            states.add(intArrayOf(16842913))
            colors.add(it)
        }
        states.add(intArrayOf())
        colors.add(mTextColor)
        return ColorStateList(states.toTypedArray(), colors.toIntArray())
    }

    fun intoTextColor() {
        if (isTextGradientColor) {
            mTextView.text = buildLinearGradientSpannable(mTextView.text)
            return
        }
        mTextView.setTextColor(buildColorState())
    }
}
