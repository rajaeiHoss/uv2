package com.hjq.widget.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.hjq.widget.R

class PasswordEditText : RegexEditText, View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {
    private var mCurrentDrawable: Drawable
    private var mFocusChangeListener: View.OnFocusChangeListener? = null
    private val mInvisibleDrawable: Drawable
    private var mTouchListener: View.OnTouchListener? = null
    private val mVisibleDrawable: Drawable

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 16842862)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val visible = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.password_off_ic)!!)
        mVisibleDrawable = visible
        visible.setBounds(0, 0, visible.intrinsicWidth, visible.intrinsicHeight)

        val invisible = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.password_on_ic)!!)
        mInvisibleDrawable = invisible
        invisible.setBounds(0, 0, invisible.intrinsicWidth, invisible.intrinsicHeight)

        mCurrentDrawable = visible
        addInputType(128)
        if (getInputRegex() == null) {
            setInputRegex(REGEX_NONNULL)
        }
        setDrawableVisible(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        super.addTextChangedListener(this)
    }

    private fun setDrawableVisible(visible: Boolean) {
        if (mCurrentDrawable.isVisible != visible) {
            mCurrentDrawable.setVisible(visible, false)
            val drawables = compoundDrawablesRelative
            setCompoundDrawablesRelative(
                drawables[0],
                drawables[1],
                if (visible) mCurrentDrawable else null,
                drawables[3]
            )
        }
    }

    private fun refreshDrawableStatus() {
        val drawables = compoundDrawablesRelative
        setCompoundDrawablesRelative(drawables[0], drawables[1], mCurrentDrawable, drawables[3])
    }

    override fun setOnFocusChangeListener(listener: View.OnFocusChangeListener?) {
        mFocusChangeListener = listener
    }

    override fun setOnTouchListener(listener: View.OnTouchListener?) {
        mTouchListener = listener
    }

    override fun onFocusChange(view: View, hasFocus: Boolean) {
        if (!hasFocus || text == null) {
            setDrawableVisible(false)
        } else {
            setDrawableVisible(text!!.isNotEmpty())
        }
        mFocusChangeListener?.onFocusChange(view, hasFocus)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val layoutDirection = layoutDirection
        val touched =
            if (layoutDirection != 0) {
                layoutDirection == 1 &&
                    x > paddingStart &&
                    x < paddingStart + mCurrentDrawable.intrinsicWidth
            } else {
                x > (width - mCurrentDrawable.intrinsicWidth) - paddingEnd &&
                    x < width - paddingEnd
            }
        if (!mCurrentDrawable.isVisible || !touched) {
            val onTouchListener = mTouchListener
            if (onTouchListener == null || !onTouchListener.onTouch(view, event)) {
                return false
            }
            return true
        }
        if (event.action == MotionEvent.ACTION_UP) {
            val current = mCurrentDrawable
            if (current === mVisibleDrawable) {
                mCurrentDrawable = mInvisibleDrawable
                transformationMethod = HideReturnsTransformationMethod.getInstance()
                refreshDrawableStatus()
            } else if (current === mInvisibleDrawable) {
                mCurrentDrawable = mVisibleDrawable
                transformationMethod = PasswordTransformationMethod.getInstance()
                refreshDrawableStatus()
            }
            val editable = text
            if (editable != null) {
                setSelection(editable.toString().length)
            }
        }
        return true
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (isFocused) {
            setDrawableVisible(s.isNotEmpty())
        }
    }

    override fun afterTextChanged(s: Editable) {
    }
}
