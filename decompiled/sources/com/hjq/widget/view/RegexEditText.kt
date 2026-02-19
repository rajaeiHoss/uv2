package com.hjq.widget.view

import android.content.Context
import android.content.res.TypedArray
import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.hjq.widget.R
import java.util.regex.Pattern

open class RegexEditText : AppCompatEditText, InputFilter {
    private var mPattern: Pattern? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 16842862)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RegexEditText)
        if (!typedArray.hasValue(R.styleable.RegexEditText_inputRegex)) {
            if (typedArray.hasValue(R.styleable.RegexEditText_regexType)) {
                when (typedArray.getInt(R.styleable.RegexEditText_regexType, 0)) {
                    1 -> setInputRegex(REGEX_MOBILE)
                    2 -> setInputRegex(REGEX_CHINESE)
                    3 -> setInputRegex(REGEX_ENGLISH)
                    4 -> setInputRegex(REGEX_NUMBER)
                    5 -> setInputRegex(REGEX_COUNT)
                    6 -> setInputRegex(REGEX_NAME)
                    7 -> setInputRegex(REGEX_NONNULL)
                }
            }
        } else {
            setInputRegex(typedArray.getString(R.styleable.RegexEditText_inputRegex))
        }
        typedArray.recycle()
    }

    fun hasInputType(inputType: Int): Boolean {
        return inputType and this.inputType != 0
    }

    fun addInputType(inputType: Int) {
        this.inputType = inputType or this.inputType
    }

    fun removeInputType(inputType: Int) {
        this.inputType = inputType.inv() and this.inputType
    }

    fun setInputRegex(inputRegex: String?) {
        if (!TextUtils.isEmpty(inputRegex)) {
            mPattern = Pattern.compile(inputRegex)
            addFilters(this)
        }
    }

    fun getInputRegex(): String? {
        return mPattern?.pattern()
    }

    fun addFilters(inputFilter: InputFilter?) {
        if (inputFilter != null) {
            val oldFilters = filters
            val newFilters: Array<InputFilter> =
                if (oldFilters == null || oldFilters.isEmpty()) {
                    arrayOf(inputFilter)
                } else {
                    Array(oldFilters.size + 1) { index ->
                        if (index < oldFilters.size) {
                            oldFilters[index]
                        } else {
                            inputFilter
                        }
                    }
                }
            super.setFilters(newFilters)
        }
    }

    fun clearFilters() {
        super.setFilters(emptyArray())
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence {
        val pattern = mPattern ?: return source
        val left = dest.toString().substring(0, dstart)
        val merged = left + source + dest.toString()
            .substring((dend - dstart) + dstart, (dest.toString().length - left.length) + dstart)
        if (dstart <= dend - 1) {
            return if (pattern.matcher(merged).matches() || "" == merged) {
                source
            } else {
                dest.toString().substring(dstart, dend)
            }
        }
        if (!pattern.matcher(merged).matches()) {
            return ""
        }
        return source
    }

    companion object {
        const val REGEX_CHINESE = "[\\u4e00-\\u9fa5]*"
        const val REGEX_COUNT = "[1-9]\\d*"
        const val REGEX_ENGLISH = "[a-zA-Z]*"
        const val REGEX_MOBILE = "[1]\\d{0,10}"
        const val REGEX_NAME = "[[\\u4e00-\\u9fa5]|[a-zA-Z]|\\d]*"
        const val REGEX_NONNULL = "\\S+"
        const val REGEX_NUMBER = "\\d*"
    }
}
