package com.hjq.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure
import com.hjq.widget.R

class SwitchButton : View {
    private var bRight = 0.0f
    protected var mAccentColor = -11806877
    private var mAnim1 = 0.0f
    private var mAnim2 = 0.0f
    protected val mAnimationSpeed: Float = 0.1f
    protected val mAspectRatio: Float = 0.68f
    private val mBackgroundPath = Path()
    private val mBarPath = Path()
    private val mBound = RectF()
    private var mCanVisibleDrawing = false
    private var mCenterX = 0.0f
    private var mCenterY = 0.0f
    protected var mChecked = false
    private var mCheckedState = STATE_SWITCH_OFF
    private val mInterpolator = AccelerateInterpolator(2.0f)
    private var mLastCheckedState = STATE_SWITCH_OFF
    private var mLeft = 0.0f
    private var mListener: OnCheckedChangeListener? = null
    private var mOff2LeftX = 0.0f
    protected var mOffColor = -1842205
    protected var mOffDarkColor = -4210753
    private var mOffLeftX = 0.0f
    private var mOffset = 0.0f
    private var mOn2LeftX = 0.0f
    private var mOnLeftX = 0.0f
    private val mPaint = Paint()
    protected var mPrimaryDarkColor = -12925358
    private var mRadius = 0.0f
    private var mRight = 0.0f
    private var mScale = 0.0f
    protected var mShadow = false
    protected var mShadowColor = -13421773
    private var mShadowGradient: RadialGradient? = null
    private var mShadowReservedHeight = 0.0f
    private var mStrokeWidth = 0.0f
    private var mWidth = 0.0f

    interface OnCheckedChangeListener {
        fun onCheckedChanged(switchButton: SwitchButton, checked: Boolean)
    }

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
        setLayerType(1, null)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton)
        mChecked = typedArray.getBoolean(R.styleable.SwitchButton_android_checked, mChecked)
        isEnabled = typedArray.getBoolean(R.styleable.SwitchButton_android_enabled, isEnabled)
        mCheckedState = if (mChecked) STATE_SWITCH_ON else STATE_SWITCH_OFF
        mLastCheckedState = mCheckedState
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var wSpec = widthMeasureSpec
        var hSpec = heightMeasureSpec

        val widthMode = MeasureSpec.getMode(wSpec)
        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            wSpec = MeasureSpec.makeMeasureSpec(
                (resources.getDimension(R.dimen.dp_56) + paddingLeft.toFloat() + paddingRight.toFloat()).toInt(),
                BasicMeasure.EXACTLY
            )
        }

        val heightMode = MeasureSpec.getMode(hSpec)
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            hSpec = MeasureSpec.makeMeasureSpec(
                (MeasureSpec.getSize(wSpec).toFloat() * mAspectRatio).toInt() + paddingTop + paddingBottom,
                BasicMeasure.EXACTLY
            )
        }

        setMeasuredDimension(wSpec, hSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val canDraw = w > paddingLeft + paddingRight && h > paddingTop + paddingBottom
        mCanVisibleDrawing = canDraw
        if (canDraw) {
            val availableWidth = ((w - paddingLeft) - paddingRight).toFloat()
            val expectedHeight = availableWidth * mAspectRatio
            val availableHeight = ((h - paddingTop) - paddingBottom).toFloat()

            val left: Int
            val right: Int
            val top: Int
            val bottom: Int
            if (expectedHeight < availableHeight) {
                left = paddingLeft
                right = w - paddingRight
                val verticalGap = ((availableHeight - expectedHeight).toInt()) / 2
                top = paddingTop + verticalGap
                bottom = (height - paddingBottom) - verticalGap
            } else {
                val horizontalGap = ((availableWidth - (availableHeight / mAspectRatio)).toInt()) / 2
                left = paddingLeft + horizontalGap
                right = (width - paddingRight) - horizontalGap
                top = paddingTop
                bottom = height - paddingBottom
            }

            val shadowReservedHeight = ((bottom - top).toFloat() * 0.07f).toInt().toFloat()
            mShadowReservedHeight = shadowReservedHeight

            val leftF = left.toFloat()
            val topF = top.toFloat() + shadowReservedHeight
            val rightF = right.toFloat()
            mRight = rightF
            val bottomF = bottom.toFloat() - shadowReservedHeight
            val radiusBase = bottomF - topF
            mCenterX = (rightF + leftF) / 2.0f
            val centerY = (bottomF + topF) / 2.0f
            mCenterY = centerY
            mLeft = leftF
            mWidth = radiusBase
            bRight = leftF + radiusBase

            val half = radiusBase / 2.0f
            val radius = 0.95f * half
            mRadius = radius
            val offset = 0.2f * radius
            mOffset = offset
            val strokeWidth = (half - radius) * 2.0f
            mStrokeWidth = strokeWidth

            val onLeft = rightF - radiusBase
            mOnLeftX = onLeft
            mOn2LeftX = onLeft - offset
            mOffLeftX = leftF
            mOff2LeftX = offset + leftF

            mScale = 1.0f - (strokeWidth / radiusBase)

            mBackgroundPath.reset()
            val rect = RectF()
            rect.top = topF
            rect.bottom = bottomF
            rect.left = leftF
            rect.right = leftF + radiusBase
            mBackgroundPath.arcTo(rect, 90.0f, 180.0f)
            rect.left = mRight - radiusBase
            rect.right = mRight
            mBackgroundPath.arcTo(rect, 270.0f, 180.0f)
            mBackgroundPath.close()

            mBound.left = mLeft
            mBound.right = bRight
            mBound.top = topF + (mStrokeWidth / 2.0f)
            mBound.bottom = bottomF - (mStrokeWidth / 2.0f)

            val shadowCenterX = (bRight + mLeft) / 2.0f
            val color = mShadowColor
            val red = (color shr 16) and 255
            val green = (color shr 8) and 255
            val blue = color and 255
            mShadowGradient = RadialGradient(
                shadowCenterX,
                centerY,
                mRadius,
                Color.argb(200, red, green, blue),
                Color.argb(25, red, green, blue),
                Shader.TileMode.CLAMP
            )
        }
    }

    private fun calcBPath(value: Float) {
        mBarPath.reset()
        mBound.left = mLeft + (mStrokeWidth / 2.0f)
        mBound.right = bRight - (mStrokeWidth / 2.0f)
        mBarPath.arcTo(mBound, 90.0f, 180.0f)

        mBound.left = mLeft + (mOffset * value) + (mStrokeWidth / 2.0f)
        mBound.right = (bRight + (value * mOffset)) - (mStrokeWidth / 2.0f)
        mBarPath.arcTo(mBound, 270.0f, 180.0f)
        mBarPath.close()
    }

    private fun calcBTranslate(value: Float): Float {
        var f2 = mOffLeftX
        var f3 = mOnLeftX
        var f4 = mOffLeftX
        var f5 = mOnLeftX
        var f6 = mOffLeftX
        val state = mCheckedState
        val diff = state - mLastCheckedState
        if (diff != -3) {
            if (diff != -2) {
                if (diff != -1) {
                    if (diff != 1) {
                        if (diff != 2) {
                            if (diff != 3) {
                                if (state == STATE_SWITCH_OFF) {
                                    f2 = mOffLeftX
                                } else if (state == STATE_SWITCH_ON) {
                                    f2 = mOnLeftX
                                }
                                return f2 - mOffLeftX
                            }
                            f5 = mOnLeftX
                            f6 = mOffLeftX
                        } else if (state == STATE_SWITCH_ON) {
                            f5 = mOnLeftX
                            f6 = mOffLeftX
                        } else if (state == STATE_SWITCH_ON2) {
                            f5 = mOn2LeftX
                            f6 = mOffLeftX
                        }
                    } else if (state == STATE_SWITCH_OFF2) {
                        f2 = mOffLeftX
                        return f2 - mOffLeftX
                    } else if (state == STATE_SWITCH_ON) {
                        f5 = mOnLeftX
                        f6 = mOn2LeftX
                    }
                    f2 = f5 - ((f5 - f6) * value)
                    return f2 - mOffLeftX
                } else if (state == STATE_SWITCH_ON2) {
                    f4 = mOn2LeftX
                    f3 = mOnLeftX
                } else if (state == STATE_SWITCH_OFF) {
                    f2 = mOffLeftX
                    return f2 - mOffLeftX
                }
            } else if (state == STATE_SWITCH_OFF) {
                f4 = mOffLeftX
                f3 = mOn2LeftX
            } else if (state == STATE_SWITCH_OFF2) {
                f4 = mOff2LeftX
                f3 = mOnLeftX
            }
            f2 = 0.0f
            return f2 - mOffLeftX
        }
        f4 = mOffLeftX
        f3 = mOnLeftX
        f2 = f4 + ((f3 - f4) * value)
        return f2 - mOffLeftX
    }

    override fun onDraw(canvas: Canvas) {
        if (mCanVisibleDrawing) {
            mPaint.isAntiAlias = true
            val state = mCheckedState
            val checked = state == STATE_SWITCH_ON || state == STATE_SWITCH_ON2

            mPaint.style = Paint.Style.FILL
            mPaint.color = if (checked) mAccentColor else mOffColor
            canvas.drawPath(mBackgroundPath, mPaint)

            val anim1 = mAnim1
            val nextAnim1 = if (anim1 - mAnimationSpeed > 0.0f) {
                anim1 - mAnimationSpeed
            } else {
                0.0f
            }
            mAnim1 = nextAnim1

            val anim2 = mAnim2
            mAnim2 = if (anim2 - mAnimationSpeed > 0.0f) {
                anim2 - mAnimationSpeed
            } else {
                0.0f
            }

            var interpolation1 = mInterpolator.getInterpolation(nextAnim1)
            var interpolation2 = mInterpolator.getInterpolation(mAnim2)

            val scale = mScale * if (checked) interpolation1 else 1.0f - interpolation1
            val knobOffset = (mRight - mCenterX) - mRadius
            if (checked) {
                interpolation1 = 1.0f - interpolation1
            }

            canvas.save()
            canvas.scale(scale, scale, mCenterX + (knobOffset * interpolation1), mCenterY)
            mPaint.color = if (isEnabled) -0x1 else -4473925
            canvas.drawPath(mBackgroundPath, mPaint)
            canvas.restore()

            canvas.save()
            canvas.translate(calcBTranslate(interpolation2), mShadowReservedHeight)
            val invert = mCheckedState == STATE_SWITCH_ON2 || mCheckedState == STATE_SWITCH_OFF2
            if (invert) {
                interpolation2 = 1.0f - interpolation2
            }
            calcBPath(interpolation2)

            if (mShadow) {
                mPaint.style = Paint.Style.FILL
                mPaint.shader = mShadowGradient
                canvas.drawPath(mBarPath, mPaint)
                mPaint.shader = null
            }

            canvas.translate(0.0f, -mShadowReservedHeight)
            val width = mWidth
            canvas.scale(0.98f, 0.98f, width / 2.0f, width / 2.0f)
            mPaint.style = Paint.Style.FILL
            mPaint.color = -0x1
            canvas.drawPath(mBarPath, mPaint)

            mPaint.style = Paint.Style.STROKE
            mPaint.strokeWidth = mStrokeWidth * 0.5f
            mPaint.color = if (checked) mPrimaryDarkColor else mOffDarkColor
            canvas.drawPath(mBarPath, mPaint)
            canvas.restore()

            mPaint.reset()
            if (mAnim1 > 0.0f || mAnim2 > 0.0f) {
                invalidate()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        val state = mCheckedState
        if (isEnabled &&
            (state == STATE_SWITCH_ON || state == STATE_SWITCH_OFF) &&
            mAnim1 * mAnim2 == 0.0f &&
            event.action == MotionEvent.ACTION_UP
        ) {
            mLastCheckedState = mCheckedState
            mAnim2 = 1.0f
            if (mCheckedState == STATE_SWITCH_OFF) {
                setChecked(true, false)
                mListener?.onCheckedChanged(this, true)
            } else if (mCheckedState == STATE_SWITCH_ON) {
                setChecked(false, false)
                mListener?.onCheckedChanged(this, false)
            }
        }
        return true
    }

    override fun onSaveInstanceState(): Parcelable {
        val savedState = SavedState(super.onSaveInstanceState())
        savedState.checked = mChecked
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        mChecked = savedState.checked
        mCheckedState = if (mChecked) STATE_SWITCH_ON else STATE_SWITCH_OFF
        invalidate()
    }

    fun setColor(accentColor: Int, primaryDarkColor: Int) {
        setColor(accentColor, primaryDarkColor, mOffColor, mOffDarkColor)
    }

    fun setColor(accentColor: Int, primaryDarkColor: Int, offColor: Int, offDarkColor: Int) {
        setColor(accentColor, primaryDarkColor, offColor, offDarkColor, mShadowColor)
    }

    fun setColor(
        accentColor: Int,
        primaryDarkColor: Int,
        offColor: Int,
        offDarkColor: Int,
        shadowColor: Int
    ) {
        mAccentColor = accentColor
        mPrimaryDarkColor = primaryDarkColor
        mOffColor = offColor
        mOffDarkColor = offDarkColor
        mShadowColor = shadowColor
        invalidate()
    }

    fun setShadow(shadow: Boolean) {
        mShadow = shadow
        invalidate()
    }

    fun isChecked(): Boolean {
        return mChecked
    }

    fun setChecked(checked: Boolean) {
        setChecked(checked, true)
    }

    fun setChecked(checked: Boolean, notifyListener: Boolean) {
        val targetState = if (checked) STATE_SWITCH_ON else STATE_SWITCH_OFF
        val currentState = mCheckedState
        if (targetState != currentState) {
            if ((targetState == STATE_SWITCH_ON &&
                    (currentState == STATE_SWITCH_OFF || currentState == STATE_SWITCH_OFF2)) ||
                (targetState == STATE_SWITCH_OFF &&
                    (currentState == STATE_SWITCH_ON || currentState == STATE_SWITCH_ON2))
            ) {
                mAnim1 = 1.0f
            }
            mAnim2 = 1.0f

            val oldChecked = mChecked
            if (!oldChecked && targetState == STATE_SWITCH_ON) {
                mChecked = true
            } else if (oldChecked && targetState == STATE_SWITCH_OFF) {
                mChecked = false
            }

            mLastCheckedState = currentState
            mCheckedState = targetState
            postInvalidate()

            if (notifyListener) {
                mListener?.onCheckedChanged(this, checked)
            }
        }
    }

    fun setOnCheckedChangeListener(listener: OnCheckedChangeListener?) {
        mListener = listener
    }

    private class SavedState : BaseSavedState {
        var checked: Boolean = false

        constructor(superState: Parcelable?) : super(superState)

        private constructor(parcel: Parcel) : super(parcel) {
            checked = 1 == parcel.readInt()
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            super.writeToParcel(parcel, flags)
            parcel.writeInt(if (checked) 1 else 0)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(parcel: Parcel): SavedState {
                    return SavedState(parcel)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    companion object {
        private const val STATE_SWITCH_OFF = 1
        private const val STATE_SWITCH_OFF2 = 2
        private const val STATE_SWITCH_ON = 3
        private const val STATE_SWITCH_ON2 = 4
    }
}
