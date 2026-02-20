package com.hjq.bar

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.hjq.bar.style.LightBarStyle
import com.hjq.bar.style.NightBarStyle
import com.hjq.bar.style.RippleBarStyle
import com.hjq.bar.style.TransparentBarStyle

open class TitleBar : FrameLayout, View.OnClickListener, View.OnLayoutChangeListener {
    private val mCurrentStyle: ITitleBarStyle
    private var mHorizontalPadding = 0
    private var mLeftIconGravity = 0
    private var mLeftIconHeight = 0
    private var mLeftIconTint = 0
    private var mLeftIconWidth = 0
    private val mLeftView: TextView
    private val mLineView: View
    private var mListener: OnTitleBarListener? = null
    private var mRightIconGravity = 0
    private var mRightIconHeight = 0
    private var mRightIconTint = 0
    private var mRightIconWidth = 0
    private val mRightView: TextView
    private var mTitleIconGravity = 0
    private var mTitleIconHeight = 0
    private var mTitleIconTint = 0
    private var mTitleIconWidth = 0
    private val mTitleView: TextView
    private var mVerticalPadding = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        if (sGlobalStyle == null) {
            sGlobalStyle = LightBarStyle()
        }

        val typedArray: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TitleBar,
            0,
            R.style.TitleBarStyle
        )

        val style = typedArray.getInt(R.styleable.TitleBar_barStyle, 0)
        mCurrentStyle = when (style) {
            16 -> LightBarStyle()
            32 -> NightBarStyle()
            48 -> TransparentBarStyle()
            64 -> RippleBarStyle()
            else -> sGlobalStyle!!
        }

        val titleView = mCurrentStyle.createTitleView(context)
        mTitleView = titleView
        val leftView = mCurrentStyle.createLeftView(context)
        mLeftView = leftView
        val rightView = mCurrentStyle.createRightView(context)
        mRightView = rightView
        val lineView = mCurrentStyle.createLineView(context)
        mLineView = lineView

        titleView.layoutParams = FrameLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT,
            1
        )
        leftView.layoutParams = FrameLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT,
            GravityCompat.START
        )
        rightView.layoutParams = FrameLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT,
            GravityCompat.END
        )
        lineView.layoutParams = FrameLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            mCurrentStyle.getLineSize(context),
            80
        )

        setTitleIconGravity(
            typedArray.getInt(
                R.styleable.TitleBar_titleIconGravity,
                mCurrentStyle.getTitleIconGravity(context)
            )
        )
        setLeftIconGravity(
            typedArray.getInt(
                R.styleable.TitleBar_leftIconGravity,
                mCurrentStyle.getLeftIconGravity(context)
            )
        )
        setRightIconGravity(
            typedArray.getInt(
                R.styleable.TitleBar_rightIconGravity,
                mCurrentStyle.getRightIconGravity(context)
            )
        )

        setTitleIconSize(
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_titleIconWidth,
                mCurrentStyle.getTitleIconWidth(context)
            ),
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_titleIconHeight,
                mCurrentStyle.getTitleIconHeight(context)
            )
        )

        setLeftIconSize(
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_leftIconWidth,
                mCurrentStyle.getLeftIconWidth(context)
            ),
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_leftIconHeight,
                mCurrentStyle.getLeftIconHeight(context)
            )
        )

        setRightIconSize(
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_rightIconWidth,
                mCurrentStyle.getRightIconWidth(context)
            ),
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_rightIconHeight,
                mCurrentStyle.getRightIconHeight(context)
            )
        )

        setTitleIconPadding(
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_titleIconPadding,
                mCurrentStyle.getTitleIconPadding(context)
            )
        )
        setLeftIconPadding(
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_leftIconPadding,
                mCurrentStyle.getLeftIconPadding(context)
            )
        )
        setRightIconPadding(
            typedArray.getDimensionPixelSize(
                R.styleable.TitleBar_rightIconPadding,
                mCurrentStyle.getRightIconPadding(context)
            )
        )

        if (typedArray.hasValue(R.styleable.TitleBar_title)) {
            setTitle(
                if (typedArray.getResourceId(R.styleable.TitleBar_title, 0) != R.string.bar_string_placeholder) {
                    typedArray.getString(R.styleable.TitleBar_title)
                } else {
                    mCurrentStyle.getTitle(context)
                }
            )
        }

        if (typedArray.hasValue(R.styleable.TitleBar_leftTitle)) {
            setLeftTitle(
                if (typedArray.getResourceId(R.styleable.TitleBar_leftTitle, 0) != R.string.bar_string_placeholder) {
                    typedArray.getString(R.styleable.TitleBar_leftTitle)
                } else {
                    mCurrentStyle.getLeftTitle(context)
                }
            )
        }

        if (typedArray.hasValue(R.styleable.TitleBar_rightTitle)) {
            setRightTitle(
                if (typedArray.getResourceId(R.styleable.TitleBar_rightTitle, 0) != R.string.bar_string_placeholder) {
                    typedArray.getString(R.styleable.TitleBar_rightTitle)
                } else {
                    mCurrentStyle.getRightTitle(context)
                }
            )
        }

        if (typedArray.hasValue(R.styleable.TitleBar_titleIconTint)) {
            setTitleIconTint(typedArray.getColor(R.styleable.TitleBar_titleIconTint, 0))
        }
        if (typedArray.hasValue(R.styleable.TitleBar_leftIconTint)) {
            setLeftIconTint(typedArray.getColor(R.styleable.TitleBar_leftIconTint, 0))
        }
        if (typedArray.hasValue(R.styleable.TitleBar_rightIconTint)) {
            setRightIconTint(typedArray.getColor(R.styleable.TitleBar_rightIconTint, 0))
        }

        if (typedArray.hasValue(R.styleable.TitleBar_titleIcon)) {
            setTitleIcon(
                TitleBarSupport.getDrawable(
                    context,
                    typedArray.getResourceId(R.styleable.TitleBar_titleIcon, 0)
                )
            )
        }

        if (typedArray.hasValue(R.styleable.TitleBar_leftIcon)) {
            val drawable = if (typedArray.getResourceId(
                    R.styleable.TitleBar_leftIcon,
                    0
                ) != R.drawable.bar_drawable_placeholder
            ) {
                TitleBarSupport.getDrawable(
                    context,
                    typedArray.getResourceId(R.styleable.TitleBar_leftIcon, 0)
                )
            } else {
                mCurrentStyle.getBackButtonDrawable(context)
            }
            setLeftIcon(drawable)
        }

        if (typedArray.hasValue(R.styleable.TitleBar_rightIcon)) {
            setRightIcon(
                TitleBarSupport.getDrawable(
                    context,
                    typedArray.getResourceId(R.styleable.TitleBar_rightIcon, 0)
                )
            )
        }

        val titleColor = if (typedArray.hasValue(R.styleable.TitleBar_titleColor)) {
            typedArray.getColorStateList(R.styleable.TitleBar_titleColor)
        } else {
            mCurrentStyle.getTitleColor(context)
        }
        setTitleColor(titleColor)

        val leftTitleColor = if (typedArray.hasValue(R.styleable.TitleBar_leftTitleColor)) {
            typedArray.getColorStateList(R.styleable.TitleBar_leftTitleColor)
        } else {
            mCurrentStyle.getLeftTitleColor(context)
        }
        setLeftTitleColor(leftTitleColor)

        val rightTitleColor = if (typedArray.hasValue(R.styleable.TitleBar_rightTitleColor)) {
            typedArray.getColorStateList(R.styleable.TitleBar_rightTitleColor)
        } else {
            mCurrentStyle.getRightTitleColor(context)
        }
        setRightTitleColor(rightTitleColor)

        val titleSize = if (typedArray.hasValue(R.styleable.TitleBar_titleSize)) {
            typedArray.getDimensionPixelSize(R.styleable.TitleBar_titleSize, 0).toFloat()
        } else {
            mCurrentStyle.getTitleSize(context)
        }
        setTitleSize(0, titleSize)

        val leftTitleSize = if (typedArray.hasValue(R.styleable.TitleBar_leftTitleSize)) {
            typedArray.getDimensionPixelSize(R.styleable.TitleBar_leftTitleSize, 0).toFloat()
        } else {
            mCurrentStyle.getLeftTitleSize(context)
        }
        setLeftTitleSize(0, leftTitleSize)

        val rightTitleSize = if (typedArray.hasValue(R.styleable.TitleBar_rightTitleSize)) {
            typedArray.getDimensionPixelSize(R.styleable.TitleBar_rightTitleSize, 0).toFloat()
        } else {
            mCurrentStyle.getRightTitleSize(context)
        }
        setRightTitleSize(0, rightTitleSize)

        val titleStyle = if (typedArray.hasValue(R.styleable.TitleBar_titleStyle)) {
            typedArray.getInt(R.styleable.TitleBar_titleStyle, 0)
        } else {
            mCurrentStyle.getTitleStyle(context)
        }
        setTitleStyle(titleStyle)

        val leftStyle = if (typedArray.hasValue(R.styleable.TitleBar_leftTitleStyle)) {
            typedArray.getInt(R.styleable.TitleBar_leftTitleStyle, 0)
        } else {
            mCurrentStyle.getLeftTitleStyle(context)
        }
        setLeftTitleStyle(leftStyle)

        val rightStyle = if (typedArray.hasValue(R.styleable.TitleBar_rightTitleStyle)) {
            typedArray.getInt(R.styleable.TitleBar_rightTitleStyle, 0)
        } else {
            mCurrentStyle.getRightTitleStyle(context)
        }
        setRightTitleStyle(rightStyle)

        if (typedArray.hasValue(R.styleable.TitleBar_titleGravity)) {
            setTitleGravity(typedArray.getInt(R.styleable.TitleBar_titleGravity, 0))
        }

        if (typedArray.hasValue(R.styleable.TitleBar_android_background) &&
            typedArray.getResourceId(
                R.styleable.TitleBar_android_background,
                0
            ) == R.drawable.bar_drawable_placeholder
        ) {
            TitleBarSupport.setBackground(this, mCurrentStyle.getTitleBarBackground(context))
        }

        if (typedArray.hasValue(R.styleable.TitleBar_leftBackground)) {
            setLeftBackground(
                if (typedArray.getResourceId(
                        R.styleable.TitleBar_leftBackground,
                        0
                    ) != R.drawable.bar_drawable_placeholder
                ) {
                    typedArray.getDrawable(R.styleable.TitleBar_leftBackground)
                } else {
                    mCurrentStyle.getLeftTitleBackground(context)
                }
            )
        }

        if (typedArray.hasValue(R.styleable.TitleBar_rightBackground)) {
            setRightBackground(
                if (typedArray.getResourceId(
                        R.styleable.TitleBar_rightBackground,
                        0
                    ) != R.drawable.bar_drawable_placeholder
                ) {
                    typedArray.getDrawable(R.styleable.TitleBar_rightBackground)
                } else {
                    mCurrentStyle.getRightTitleBackground(context)
                }
            )
        }

        setLineVisible(
            typedArray.getBoolean(
                R.styleable.TitleBar_lineVisible,
                mCurrentStyle.isLineVisible(context)
            )
        )

        if (typedArray.hasValue(R.styleable.TitleBar_lineDrawable)) {
            setLineDrawable(
                if (typedArray.getResourceId(
                        R.styleable.TitleBar_lineDrawable,
                        0
                    ) != R.drawable.bar_drawable_placeholder
                ) {
                    typedArray.getDrawable(R.styleable.TitleBar_lineDrawable)
                } else {
                    mCurrentStyle.getLineDrawable(context)
                }
            )
        }

        if (typedArray.hasValue(R.styleable.TitleBar_lineSize)) {
            setLineSize(typedArray.getDimensionPixelSize(R.styleable.TitleBar_lineSize, 0))
        }

        mHorizontalPadding = typedArray.getDimensionPixelSize(
            R.styleable.TitleBar_childPaddingHorizontal,
            mCurrentStyle.getChildHorizontalPadding(context)
        )
        val verticalPadding = typedArray.getDimensionPixelSize(
            R.styleable.TitleBar_childPaddingVertical,
            mCurrentStyle.getChildVerticalPadding(context)
        )
        mVerticalPadding = verticalPadding
        setChildPadding(mHorizontalPadding, verticalPadding)

        typedArray.recycle()

        addView(titleView, 0)
        addView(leftView, 1)
        addView(rightView, 2)
        addView(lineView, 3)

        addOnLayoutChangeListener(this)

        if (isInEditMode) {
            measure(0, 0)
            titleView.measure(0, 0)
            leftView.measure(0, 0)
            rightView.measure(0, 0)
            val max = maxOf(leftView.measuredWidth, rightView.measuredWidth) + mHorizontalPadding
            (titleView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(max, 0, max, 0)
        }
    }

    override fun onLayoutChange(
        v: View,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        oldLeft: Int,
        oldTop: Int,
        oldRight: Int,
        oldBottom: Int
    ) {
        removeOnLayoutChangeListener(this)

        if (!(mLeftView.maxWidth == Int.MAX_VALUE ||
                mTitleView.maxWidth == Int.MAX_VALUE ||
                mRightView.maxWidth == Int.MAX_VALUE)
        ) {
            mLeftView.maxWidth = Int.MAX_VALUE
            mTitleView.maxWidth = Int.MAX_VALUE
            mRightView.maxWidth = Int.MAX_VALUE
            mLeftView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0))
            mTitleView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0))
            mRightView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0))
        }

        val parentWidth = right - left
        val maxWidth = maxOf(mLeftView.measuredWidth, mRightView.measuredWidth)
        val twiceWidth = maxWidth * 2
        if (mTitleView.measuredWidth + twiceWidth >= parentWidth) {
            if (maxWidth > parentWidth / 3) {
                val quarter = parentWidth / 4
                mLeftView.maxWidth = quarter
                mTitleView.maxWidth = parentWidth / 2
                mRightView.maxWidth = quarter
            } else {
                mLeftView.maxWidth = maxWidth
                mTitleView.maxWidth = parentWidth - twiceWidth
                mRightView.maxWidth = maxWidth
            }
        } else if (!(mLeftView.maxWidth == Int.MAX_VALUE ||
                mTitleView.maxWidth == Int.MAX_VALUE ||
                mRightView.maxWidth == Int.MAX_VALUE)
        ) {
            mLeftView.maxWidth = Int.MAX_VALUE
            mTitleView.maxWidth = Int.MAX_VALUE
            mRightView.maxWidth = Int.MAX_VALUE
        }

        mLeftView.isEnabled = TitleBarSupport.isContainContent(mLeftView)
        mTitleView.isEnabled = TitleBarSupport.isContainContent(mTitleView)
        mRightView.isEnabled = TitleBarSupport.isContainContent(mRightView)

        post {
            addOnLayoutChangeListener(this@TitleBar)
        }
    }

    override fun onClick(view: View) {
        val listener = mListener
        if (listener != null) {
            if (view === mLeftView) {
                listener.onLeftClick(view)
            } else if (view === mRightView) {
                listener.onRightClick(view)
            } else if (view === mTitleView) {
                listener.onTitleClick(view)
            }
        }
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
        if (params.width == LayoutParams.WRAP_CONTENT) {
            params.width = LayoutParams.MATCH_PARENT
        }
        val horizontalPadding = mHorizontalPadding
        var verticalPadding = 0
        if (params.height == LayoutParams.WRAP_CONTENT) {
            verticalPadding = mVerticalPadding
        }
        setChildPadding(horizontalPadding, verticalPadding)
        super.setLayoutParams(params)
    }

    override fun generateDefaultLayoutParams(): FrameLayout.LayoutParams {
        return FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    fun setOnTitleBarListener(listener: OnTitleBarListener?): TitleBar {
        mListener = listener
        mTitleView.setOnClickListener(this)
        mLeftView.setOnClickListener(this)
        mRightView.setOnClickListener(this)
        return this
    }

    fun setTitle(resId: Int): TitleBar {
        return setTitle(resources.getString(resId))
    }

    fun setTitle(title: CharSequence?): TitleBar {
        mTitleView.text = title
        return this
    }

    fun getTitle(): CharSequence {
        return mTitleView.text
    }

    fun setLeftTitle(resId: Int): TitleBar {
        return setLeftTitle(resources.getString(resId))
    }

    fun setLeftTitle(leftTitle: CharSequence?): TitleBar {
        mLeftView.text = leftTitle
        return this
    }

    fun getLeftTitle(): CharSequence {
        return mLeftView.text
    }

    fun setRightTitle(resId: Int): TitleBar {
        return setRightTitle(resources.getString(resId))
    }

    fun setRightTitle(rightTitle: CharSequence?): TitleBar {
        mRightView.text = rightTitle
        return this
    }

    fun getRightTitle(): CharSequence {
        return mRightView.text
    }

    fun setTitleStyle(style: Int): TitleBar {
        TitleBarSupport.setTextTypeface(mTitleView, style)
        return this
    }

    fun setTitleStyle(typeface: Typeface?, style: Int): TitleBar {
        mTitleView.setTypeface(typeface, style)
        return this
    }

    fun setLeftTitleStyle(style: Int): TitleBar {
        TitleBarSupport.setTextTypeface(mLeftView, style)
        return this
    }

    fun setLeftTitleStyle(typeface: Typeface?, style: Int): TitleBar {
        mLeftView.typeface = typeface
        return this
    }

    fun setRightTitleStyle(style: Int): TitleBar {
        TitleBarSupport.setTextTypeface(mRightView, style)
        return this
    }

    fun setRightTitleStyle(typeface: Typeface?, style: Int): TitleBar {
        mRightView.typeface = typeface
        return this
    }

    fun setTitleColor(color: Int): TitleBar {
        return setTitleColor(ColorStateList.valueOf(color))
    }

    fun setTitleColor(color: ColorStateList?): TitleBar {
        if (color != null) {
            mTitleView.setTextColor(color)
        }
        return this
    }

    fun setLeftTitleColor(color: Int): TitleBar {
        return setLeftTitleColor(ColorStateList.valueOf(color))
    }

    fun setLeftTitleColor(color: ColorStateList?): TitleBar {
        if (color != null) {
            mLeftView.setTextColor(color)
        }
        return this
    }

    fun setRightTitleColor(color: Int): TitleBar {
        return setRightTitleColor(ColorStateList.valueOf(color))
    }

    fun setRightTitleColor(color: ColorStateList?): TitleBar {
        if (color != null) {
            mRightView.setTextColor(color)
        }
        return this
    }

    fun setTitleSize(size: Float): TitleBar {
        return setTitleSize(2, size)
    }

    fun setTitleSize(unit: Int, size: Float): TitleBar {
        mTitleView.setTextSize(unit, size)
        return this
    }

    fun setLeftTitleSize(size: Float): TitleBar {
        return setLeftTitleSize(2, size)
    }

    fun setLeftTitleSize(unit: Int, size: Float): TitleBar {
        mLeftView.setTextSize(unit, size)
        return this
    }

    fun setRightTitleSize(size: Float): TitleBar {
        return setRightTitleSize(2, size)
    }

    fun setRightTitleSize(unit: Int, size: Float): TitleBar {
        mRightView.setTextSize(unit, size)
        return this
    }

    fun setTitleIcon(resId: Int): TitleBar {
        return setTitleIcon(TitleBarSupport.getDrawable(context, resId))
    }

    fun setTitleIcon(drawable: Drawable?): TitleBar {
        TitleBarSupport.setDrawableTint(drawable, mTitleIconTint)
        TitleBarSupport.setDrawableSize(drawable, mTitleIconWidth, mTitleIconHeight)
        TitleBarSupport.setTextCompoundDrawable(mTitleView, drawable, mTitleIconGravity)
        return this
    }

    fun getTitleIcon(): Drawable? {
        return TitleBarSupport.getTextCompoundDrawable(mTitleView, mTitleIconGravity)
    }

    fun setLeftIcon(resId: Int): TitleBar {
        return setLeftIcon(TitleBarSupport.getDrawable(context, resId))
    }

    fun setLeftIcon(drawable: Drawable?): TitleBar {
        TitleBarSupport.setDrawableTint(drawable, mLeftIconTint)
        TitleBarSupport.setDrawableSize(drawable, mLeftIconWidth, mLeftIconHeight)
        TitleBarSupport.setTextCompoundDrawable(mLeftView, drawable, mLeftIconGravity)
        return this
    }

    fun getLeftIcon(): Drawable? {
        return TitleBarSupport.getTextCompoundDrawable(mLeftView, mLeftIconGravity)
    }

    fun setRightIcon(resId: Int): TitleBar {
        return setRightIcon(TitleBarSupport.getDrawable(context, resId))
    }

    fun setRightIcon(drawable: Drawable?): TitleBar {
        TitleBarSupport.setDrawableTint(drawable, mRightIconTint)
        TitleBarSupport.setDrawableSize(drawable, mRightIconWidth, mRightIconHeight)
        TitleBarSupport.setTextCompoundDrawable(mRightView, drawable, mRightIconGravity)
        return this
    }

    fun getRightIcon(): Drawable? {
        return TitleBarSupport.getTextCompoundDrawable(mRightView, mRightIconGravity)
    }

    fun setTitleIconSize(width: Int, height: Int): TitleBar {
        mTitleIconWidth = width
        mTitleIconHeight = height
        TitleBarSupport.setDrawableSize(getTitleIcon(), width, height)
        return this
    }

    fun setLeftIconSize(width: Int, height: Int): TitleBar {
        mLeftIconWidth = width
        mLeftIconHeight = height
        TitleBarSupport.setDrawableSize(getLeftIcon(), width, height)
        return this
    }

    fun setRightIconSize(width: Int, height: Int): TitleBar {
        mRightIconWidth = width
        mRightIconHeight = height
        TitleBarSupport.setDrawableSize(getRightIcon(), width, height)
        return this
    }

    fun setTitleIconPadding(padding: Int): TitleBar {
        mTitleView.compoundDrawablePadding = padding
        return this
    }

    fun setLeftIconPadding(padding: Int): TitleBar {
        mLeftView.compoundDrawablePadding = padding
        return this
    }

    fun setRightIconPadding(padding: Int): TitleBar {
        mRightView.compoundDrawablePadding = padding
        return this
    }

    fun setTitleIconTint(iconTint: Int): TitleBar {
        mTitleIconTint = iconTint
        TitleBarSupport.setDrawableTint(getTitleIcon(), iconTint)
        return this
    }

    fun setLeftIconTint(iconTint: Int): TitleBar {
        mLeftIconTint = iconTint
        TitleBarSupport.setDrawableTint(getLeftIcon(), iconTint)
        return this
    }

    fun setRightIconTint(iconTint: Int): TitleBar {
        mRightIconTint = iconTint
        TitleBarSupport.setDrawableTint(getRightIcon(), iconTint)
        return this
    }

    fun clearTitleIconTint(): TitleBar {
        mTitleIconTint = 0
        TitleBarSupport.clearDrawableTint(getTitleIcon())
        return this
    }

    fun clearLeftIconTint(): TitleBar {
        mLeftIconTint = 0
        TitleBarSupport.clearDrawableTint(getLeftIcon())
        return this
    }

    fun clearRightIconTint(): TitleBar {
        mRightIconTint = 0
        TitleBarSupport.clearDrawableTint(getRightIcon())
        return this
    }

    fun setTitleIconGravity(iconGravity: Int): TitleBar {
        val titleIcon = getTitleIcon()
        mTitleIconGravity = iconGravity
        if (titleIcon != null) {
            TitleBarSupport.setTextCompoundDrawable(mTitleView, titleIcon, iconGravity)
        }
        return this
    }

    fun setLeftIconGravity(iconGravity: Int): TitleBar {
        val leftIcon = getLeftIcon()
        mLeftIconGravity = iconGravity
        if (leftIcon != null) {
            TitleBarSupport.setTextCompoundDrawable(mLeftView, leftIcon, iconGravity)
        }
        return this
    }

    fun setRightIconGravity(iconGravity: Int): TitleBar {
        val rightIcon = getRightIcon()
        mRightIconGravity = iconGravity
        if (rightIcon != null) {
            TitleBarSupport.setTextCompoundDrawable(mRightView, rightIcon, iconGravity)
        }
        return this
    }

    fun setLeftBackground(resId: Int): TitleBar {
        return setLeftBackground(TitleBarSupport.getDrawable(context, resId))
    }

    fun setLeftBackground(background: Drawable?): TitleBar {
        TitleBarSupport.setBackground(mLeftView, background)
        return this
    }

    fun setRightBackground(resId: Int): TitleBar {
        return setRightBackground(TitleBarSupport.getDrawable(context, resId))
    }

    fun setRightBackground(background: Drawable?): TitleBar {
        TitleBarSupport.setBackground(mRightView, background)
        return this
    }

    fun setLineVisible(visible: Boolean): TitleBar {
        mLineView.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        return this
    }

    fun setLineColor(color: Int): TitleBar {
        return setLineDrawable(ColorDrawable(color))
    }

    fun setLineDrawable(drawable: Drawable?): TitleBar {
        TitleBarSupport.setBackground(mLineView, drawable)
        return this
    }

    fun setLineSize(size: Int): TitleBar {
        val params = mLineView.layoutParams
        params.height = size
        mLineView.layoutParams = params
        return this
    }

    fun setTitleGravity(gravity: Int): TitleBar {
        val absoluteGravity = TitleBarSupport.getAbsoluteGravity(this, gravity)
        if (absoluteGravity == 3) {
            if (TitleBarSupport.isContainContent(if (TitleBarSupport.isLayoutRtl(context)) mRightView else mLeftView)) {
                Log.e(LOG_TAG, "Title center of gravity for the left, the left title can not have content")
                return this
            }
        }
        if (absoluteGravity == 5) {
            if (TitleBarSupport.isContainContent(if (TitleBarSupport.isLayoutRtl(context)) mLeftView else mRightView)) {
                Log.e(LOG_TAG, "Title center of gravity for the right, the right title can not have content")
                return this
            }
        }
        val params = mTitleView.layoutParams as FrameLayout.LayoutParams
        params.gravity = absoluteGravity
        mTitleView.layoutParams = params
        return this
    }

    fun setChildPadding(horizontalPadding: Int, verticalPadding: Int): TitleBar {
        mHorizontalPadding = horizontalPadding
        mVerticalPadding = verticalPadding
        mLeftView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        mTitleView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        mRightView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding)
        return this
    }

    fun getLeftView(): TextView {
        return mLeftView
    }

    fun getTitleView(): TextView {
        return mTitleView
    }

    fun getRightView(): TextView {
        return mRightView
    }

    fun getLineView(): View {
        return mLineView
    }

    fun getCurrentStyle(): ITitleBarStyle {
        return mCurrentStyle
    }

    companion object {
        private const val LOG_TAG = "TitleBar"
        private var sGlobalStyle: ITitleBarStyle? = null

        @JvmStatic
        fun setDefaultStyle(titleBarStyle: ITitleBarStyle?) {
            sGlobalStyle = titleBarStyle
        }
    }
}
