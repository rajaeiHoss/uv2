package com.hjq.widget.layout

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.hjq.widget.R

class SettingBar : FrameLayout {
    private var mLeftDrawableSize = 0
    private var mLeftDrawableTint = 0
    private val mMainLayout: LinearLayout
    private val mLeftView: TextView
    private val mRightView: TextView
    private val mLineView: View
    private var mRightDrawableSize = 0
    private var mRightDrawableTint = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        0
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        val mainLayout = LinearLayout(context)
        mMainLayout = mainLayout
        val leftView = TextView(context)
        mLeftView = leftView
        val rightView = TextView(context)
        mRightView = rightView
        val lineView = View(context)
        mLineView = lineView

        mainLayout.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL)

        val leftParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        leftParams.gravity = Gravity.CENTER_VERTICAL
        leftParams.weight = 1.0f
        leftView.layoutParams = leftParams

        val rightParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        rightParams.gravity = Gravity.CENTER_VERTICAL
        rightView.layoutParams = rightParams

        lineView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 1, Gravity.BOTTOM)

        leftView.gravity = Gravity.START or Gravity.CENTER_VERTICAL
        rightView.gravity = Gravity.END or Gravity.CENTER_VERTICAL

        leftView.isSingleLine = true
        rightView.isSingleLine = true
        leftView.ellipsize = TextUtils.TruncateAt.END
        rightView.ellipsize = TextUtils.TruncateAt.END

        leftView.setLineSpacing(resources.getDimension(R.dimen.dp_5), leftView.lineSpacingMultiplier)
        rightView.setLineSpacing(resources.getDimension(R.dimen.dp_5), rightView.lineSpacingMultiplier)

        val horizontalPadding = resources.getDimension(R.dimen.dp_15).toInt()
        val verticalPadding = resources.getDimension(R.dimen.dp_12).toInt()
        leftView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        rightView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)

        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingBar)
        if (typedArray.hasValue(R.styleable.SettingBar_bar_leftText)) {
            setLeftText(typedArray.getString(R.styleable.SettingBar_bar_leftText))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_rightText)) {
            setRightText(typedArray.getString(R.styleable.SettingBar_bar_rightText))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_leftTextHint)) {
            setLeftTextHint(typedArray.getString(R.styleable.SettingBar_bar_leftTextHint))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_rightTextHint)) {
            setRightTextHint(typedArray.getString(R.styleable.SettingBar_bar_rightTextHint))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_leftDrawableSize)) {
            setLeftDrawableSize(typedArray.getDimensionPixelSize(R.styleable.SettingBar_bar_leftDrawableSize, 0))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_rightDrawableSize)) {
            setRightDrawableSize(typedArray.getDimensionPixelSize(R.styleable.SettingBar_bar_rightDrawableSize, 0))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_leftDrawableTint)) {
            setLeftDraawbleTint(typedArray.getColor(R.styleable.SettingBar_bar_leftDrawableTint, 0))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_rightDrawableTint)) {
            setRightDrawableTint(typedArray.getColor(R.styleable.SettingBar_bar_rightDrawableTint, 0))
        }

        val leftDrawablePadding = if (typedArray.hasValue(R.styleable.SettingBar_bar_leftDrawablePadding)) {
            typedArray.getDimensionPixelSize(R.styleable.SettingBar_bar_leftDrawablePadding, 0)
        } else {
            resources.getDimension(R.dimen.dp_10).toInt()
        }
        setLeftDrawablePadding(leftDrawablePadding)

        val rightDrawablePadding = if (typedArray.hasValue(R.styleable.SettingBar_bar_rightDrawablePadding)) {
            typedArray.getDimensionPixelSize(R.styleable.SettingBar_bar_rightDrawablePadding, 0)
        } else {
            resources.getDimension(R.dimen.dp_10).toInt()
        }
        setRightDrawablePadding(rightDrawablePadding)

        if (typedArray.hasValue(R.styleable.SettingBar_bar_leftDrawable)) {
            setLeftDrawable(typedArray.getDrawable(R.styleable.SettingBar_bar_leftDrawable))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_rightDrawable)) {
            setRightDrawable(typedArray.getDrawable(R.styleable.SettingBar_bar_rightDrawable))
        }

        setLeftTextColor(
            typedArray.getColor(
                R.styleable.SettingBar_bar_leftTextColor,
                ContextCompat.getColor(context, R.color.black80)
            )
        )
        setRightTextColor(
            typedArray.getColor(
                R.styleable.SettingBar_bar_rightTextColor,
                ContextCompat.getColor(context, R.color.black60)
            )
        )
        setLeftTextSize(
            0,
            typedArray.getDimensionPixelSize(
                R.styleable.SettingBar_bar_leftTextSize,
                resources.getDimension(R.dimen.sp_15).toInt()
            ).toFloat()
        )
        setRightTextSize(
            0,
            typedArray.getDimensionPixelSize(
                R.styleable.SettingBar_bar_rightTextSize,
                resources.getDimension(R.dimen.sp_14).toInt()
            ).toFloat()
        )

        if (typedArray.hasValue(R.styleable.SettingBar_bar_lineDrawable)) {
            setLineDrawable(typedArray.getDrawable(R.styleable.SettingBar_bar_lineDrawable))
        } else {
            setLineDrawable(ColorDrawable(-1250068))
        }

        if (typedArray.hasValue(R.styleable.SettingBar_bar_lineVisible)) {
            setLineVisible(typedArray.getBoolean(R.styleable.SettingBar_bar_lineVisible, true))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_lineSize)) {
            setLineSize(typedArray.getDimensionPixelSize(R.styleable.SettingBar_bar_lineSize, 0))
        }
        if (typedArray.hasValue(R.styleable.SettingBar_bar_lineMargin)) {
            setLineMargin(typedArray.getDimensionPixelSize(R.styleable.SettingBar_bar_lineMargin, 0))
        }

        if (background == null) {
            val stateListDrawable = StateListDrawable()
            stateListDrawable.addState(
                intArrayOf(android.R.attr.state_pressed),
                ColorDrawable(ContextCompat.getColor(context, R.color.black5))
            )
            stateListDrawable.addState(
                intArrayOf(android.R.attr.state_selected),
                ColorDrawable(ContextCompat.getColor(context, R.color.black5))
            )
            stateListDrawable.addState(
                intArrayOf(android.R.attr.state_focused),
                ColorDrawable(ContextCompat.getColor(context, R.color.black5))
            )
            stateListDrawable.addState(
                intArrayOf(),
                ColorDrawable(ContextCompat.getColor(context, R.color.white))
            )
            background = stateListDrawable
            isFocusable = true
            isClickable = true
        }

        typedArray.recycle()

        mainLayout.addView(leftView)
        mainLayout.addView(rightView)
        addView(mainLayout, 0)
        addView(lineView, 1)
    }

    fun setLeftText(resId: Int): SettingBar {
        return setLeftText(resources.getString(resId))
    }

    fun setLeftText(text: CharSequence?): SettingBar {
        mLeftView.text = text
        return this
    }

    fun getLeftText(): CharSequence {
        return mLeftView.text
    }

    fun setLeftTextHint(resId: Int): SettingBar {
        return setLeftTextHint(resources.getString(resId))
    }

    fun setLeftTextHint(text: CharSequence?): SettingBar {
        mLeftView.hint = text
        return this
    }

    fun setRightText(resId: Int): SettingBar {
        setRightText(resources.getString(resId))
        return this
    }

    fun setRightText(text: CharSequence?): SettingBar {
        mRightView.text = text
        return this
    }

    fun getRightText(): CharSequence {
        return mRightView.text
    }

    fun setRightTextHint(resId: Int): SettingBar {
        return setRightTextHint(resources.getString(resId))
    }

    fun setRightTextHint(text: CharSequence?): SettingBar {
        mRightView.hint = text
        return this
    }

    fun setLeftDrawable(resId: Int): SettingBar {
        setLeftDrawable(ContextCompat.getDrawable(context, resId))
        return this
    }

    fun setLeftDrawable(drawable: Drawable?): SettingBar {
        mLeftView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        setLeftDrawableSize(mLeftDrawableSize)
        setLeftDraawbleTint(mLeftDrawableTint)
        return this
    }

    fun getLeftDrawable(): Drawable? {
        return mLeftView.compoundDrawables[0]
    }

    fun setRightDrawable(resId: Int): SettingBar {
        setRightDrawable(ContextCompat.getDrawable(context, resId))
        return this
    }

    fun setRightDrawable(drawable: Drawable?): SettingBar {
        mRightView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
        setRightDrawableSize(mRightDrawableSize)
        setRightDrawableTint(mRightDrawableTint)
        return this
    }

    fun getRightDrawable(): Drawable? {
        return mRightView.compoundDrawables[2]
    }

    fun setLeftDrawablePadding(padding: Int): SettingBar {
        mLeftView.compoundDrawablePadding = padding
        return this
    }

    fun setRightDrawablePadding(padding: Int): SettingBar {
        mRightView.compoundDrawablePadding = padding
        return this
    }

    fun setLeftDrawableSize(size: Int): SettingBar {
        mLeftDrawableSize = size
        val leftDrawable = getLeftDrawable()
        if (leftDrawable != null) {
            if (size > 0) {
                leftDrawable.setBounds(0, 0, size, size)
            } else {
                leftDrawable.setBounds(0, 0, leftDrawable.intrinsicWidth, leftDrawable.intrinsicHeight)
            }
            mLeftView.setCompoundDrawables(leftDrawable, null, null, null)
        }
        return this
    }

    fun setRightDrawableSize(size: Int): SettingBar {
        mRightDrawableSize = size
        val rightDrawable = getRightDrawable()
        if (rightDrawable != null) {
            if (size > 0) {
                rightDrawable.setBounds(0, 0, size, size)
            } else {
                rightDrawable.setBounds(0, 0, rightDrawable.intrinsicWidth, rightDrawable.intrinsicHeight)
            }
            mRightView.setCompoundDrawables(null, null, rightDrawable, null)
        }
        return this
    }

    fun setLeftDraawbleTint(color: Int): SettingBar {
        mLeftDrawableTint = color
        val leftDrawable = getLeftDrawable()
        if (leftDrawable != null && color != NO_COLOR) {
            leftDrawable.mutate()
            leftDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
        return this
    }

    fun setRightDrawableTint(color: Int): SettingBar {
        mRightDrawableTint = color
        val rightDrawable = getRightDrawable()
        if (rightDrawable != null && color != NO_COLOR) {
            rightDrawable.mutate()
            rightDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
        return this
    }

    fun setLeftTextColor(color: Int): SettingBar {
        mLeftView.setTextColor(color)
        return this
    }

    fun setRightTextColor(color: Int): SettingBar {
        mRightView.setTextColor(color)
        return this
    }

    fun setLeftTextSize(unit: Int, size: Float): SettingBar {
        mLeftView.setTextSize(unit, size)
        return this
    }

    fun setRightTextSize(unit: Int, size: Float): SettingBar {
        mRightView.setTextSize(unit, size)
        return this
    }

    fun setLineVisible(visible: Boolean): SettingBar {
        mLineView.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun setLineColor(color: Int): SettingBar {
        return setLineDrawable(ColorDrawable(color))
    }

    fun setLineDrawable(drawable: Drawable?): SettingBar {
        mLineView.background = drawable
        return this
    }

    fun setLineSize(size: Int): SettingBar {
        val params = (mLineView.layoutParams as? LayoutParams)
            ?: (generateDefaultLayoutParams() ?: LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            ))
        params.height = size
        mLineView.layoutParams = params
        return this
    }

    fun setLineMargin(margin: Int): SettingBar {
        val params = (mLineView.layoutParams as? LayoutParams)
            ?: (generateDefaultLayoutParams() ?: LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            ))
        params.leftMargin = margin
        params.rightMargin = margin
        mLineView.layoutParams = params
        return this
    }

    fun getMainLayout(): LinearLayout {
        return mMainLayout
    }

    fun getLeftView(): TextView {
        return mLeftView
    }

    fun getRightView(): TextView {
        return mRightView
    }

    fun getLineView(): View {
        return mLineView
    }

    companion object {
        const val NO_COLOR: Int = 0
    }
}
