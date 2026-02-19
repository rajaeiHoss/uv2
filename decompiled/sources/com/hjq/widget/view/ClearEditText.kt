package com.hjq.widget.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.hjq.widget.R

class ClearEditText : RegexEditText, View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {
    private val mClearDrawable: Drawable
    private var mFocusChangeListener: View.OnFocusChangeListener? = null
    private var mTouchListener: View.OnTouchListener? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 16842862)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val wrap = DrawableCompat.wrap(ContextCompat.getDrawable(context, R.drawable.input_delete_ic)!!)
        mClearDrawable = wrap
        wrap.setBounds(0, 0, wrap.intrinsicWidth, wrap.intrinsicHeight)
        setDrawableVisible(false)
        super.setOnTouchListener(this)
        super.setOnFocusChangeListener(this)
        super.addTextChangedListener(this)
    }

    private fun setDrawableVisible(visible: Boolean) {
        if (mClearDrawable.isVisible != visible) {
            mClearDrawable.setVisible(visible, false)
            val drawables = compoundDrawablesRelative
            setCompoundDrawablesRelative(
                drawables[0],
                drawables[1],
                if (visible) mClearDrawable else null,
                drawables[3]
            )
        }
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
                    x < paddingStart + mClearDrawable.intrinsicWidth
            } else {
                x > (width - mClearDrawable.intrinsicWidth) - paddingEnd &&
                    x < width - paddingEnd
            }
        if (!mClearDrawable.isVisible || !touched) {
            val onTouchListener = mTouchListener
            if (onTouchListener == null || !onTouchListener.onTouch(view, event)) {
                return false
            }
            return true
        }
        if (event.action == MotionEvent.ACTION_UP) {
            setText("")
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
