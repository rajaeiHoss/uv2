package com.hjq.widget.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.hjq.widget.R
import kotlin.math.max
import kotlin.math.min

class SimpleRatingBar : View {
    private var mCurrentGrade = 0f
    private var mFillDrawable: Drawable
    private val mGradeBounds: Rect = Rect()
    private var mGradeCount = 0
    private var mGradeHeight = 0
    private var mGradeSpace = 0
    private var mGradeStep: GradleStep
    private var mGradeWidth = 0
    private var mHalfDrawable: Drawable?
    private var mListener: OnRatingChangeListener? = null
    private var mNormalDrawable: Drawable

    enum class GradleStep {
        HALF,
        ONE
    }

    interface OnRatingChangeListener {
        fun onRatingChanged(simpleRatingBar: SimpleRatingBar, grade: Float, fromUser: Boolean)
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleRatingBar)
        mNormalDrawable = ContextCompat.getDrawable(
            context,
            typedArray.getResourceId(R.styleable.SimpleRatingBar_normalDrawable, R.drawable.rating_star_off_ic)
        )!!
        mHalfDrawable = ContextCompat.getDrawable(
            context,
            typedArray.getResourceId(R.styleable.SimpleRatingBar_halfDrawable, R.drawable.rating_star_half_ic)
        )
        mFillDrawable = ContextCompat.getDrawable(
            context,
            typedArray.getResourceId(R.styleable.SimpleRatingBar_fillDrawable, R.drawable.rating_star_fill_ic)
        )!!
        if (
            mNormalDrawable.intrinsicWidth == mFillDrawable.intrinsicWidth &&
            mNormalDrawable.intrinsicWidth == mHalfDrawable!!.intrinsicWidth &&
            mNormalDrawable.intrinsicHeight == mFillDrawable.intrinsicHeight &&
            mNormalDrawable.intrinsicHeight == mHalfDrawable!!.intrinsicHeight
        ) {
            mCurrentGrade = typedArray.getFloat(R.styleable.SimpleRatingBar_grade, 0f)
            mGradeCount = typedArray.getInt(R.styleable.SimpleRatingBar_gradeCount, 5)
            mGradeWidth = typedArray.getDimensionPixelSize(
                R.styleable.SimpleRatingBar_gradeWidth,
                mNormalDrawable.intrinsicWidth
            )
            mGradeHeight = typedArray.getDimensionPixelSize(
                R.styleable.SimpleRatingBar_gradeHeight,
                mFillDrawable.intrinsicHeight
            )
            mGradeSpace = typedArray.getDimension(
                R.styleable.SimpleRatingBar_gradeSpace,
                mGradeWidth.toFloat() / 4f
            ).toInt()
            mGradeStep =
                if (typedArray.getInt(R.styleable.SimpleRatingBar_gradeStep, 0) != 1) {
                    GradleStep.HALF
                } else {
                    GradleStep.ONE
                }
            typedArray.recycle()
            return
        }
        throw IllegalStateException("The width and height of the picture do not agree")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            (mGradeWidth * mGradeCount) + (mGradeSpace * (mGradeCount + 1)) + paddingLeft + paddingRight,
            mGradeHeight + paddingTop + paddingBottom
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isEnabled) {
            return false
        }
        val action = event.action
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            val x = event.x - paddingLeft.toFloat()
            val gradeOffset = x - mGradeSpace.toFloat()
            var grade = min(
                max(
                    if (gradeOffset > 0f) {
                        gradeOffset / (mGradeWidth + mGradeSpace).toFloat()
                    } else {
                        0f
                    },
                    0f
                ),
                mGradeCount.toFloat()
            )
            val integerPart = grade.toInt().toFloat()
            val decimalPart = grade - integerPart
            if (decimalPart > 0f) {
                grade =
                    if (decimalPart > 0.5f) {
                        (grade + 0.5f).toInt().toFloat()
                    } else {
                        integerPart + 0.5f
                    }
            }
            if (grade * 10f != mCurrentGrade * 10f) {
                mCurrentGrade = grade
                invalidate()
                mListener?.onRatingChanged(this, mCurrentGrade, true)
            }
        }
        return true
    }

    override fun onDraw(canvas: Canvas) {
        for (index in 0 until mGradeCount) {
            val leftSpace = mGradeSpace + ((mGradeWidth + mGradeSpace) * index)
            mGradeBounds.left = paddingLeft + leftSpace
            mGradeBounds.top = paddingTop
            mGradeBounds.right = mGradeBounds.left + mGradeWidth
            mGradeBounds.bottom = mGradeBounds.top + mGradeHeight
            if (mCurrentGrade > index.toFloat()) {
                if (mHalfDrawable != null && mGradeStep == GradleStep.HALF) {
                    val currentGrade = mCurrentGrade
                    if (currentGrade.toInt() == index && currentGrade - currentGrade.toInt().toFloat() == 0.5f) {
                        mHalfDrawable?.bounds = mGradeBounds
                        mHalfDrawable?.draw(canvas)
                    }
                }
                mFillDrawable.bounds = mGradeBounds
                mFillDrawable.draw(canvas)
            } else {
                mNormalDrawable.bounds = mGradeBounds
                mNormalDrawable.draw(canvas)
            }
        }
    }

    fun setRatingDrawable(normalDrawableId: Int, halfDrawableId: Int, fillDrawableId: Int) {
        setRatingDrawable(
            ContextCompat.getDrawable(context, normalDrawableId),
            ContextCompat.getDrawable(context, halfDrawableId),
            ContextCompat.getDrawable(context, fillDrawableId)
        )
    }

    fun setRatingDrawable(normalDrawable: Drawable?, halfDrawable: Drawable?, fillDrawable: Drawable?) {
        if (normalDrawable == null || fillDrawable == null) {
            throw IllegalStateException("Drawable cannot be empty")
        }
        if (
            normalDrawable.intrinsicWidth == fillDrawable.intrinsicWidth &&
            normalDrawable.intrinsicHeight == fillDrawable.intrinsicHeight
        ) {
            mNormalDrawable = normalDrawable
            mHalfDrawable = halfDrawable
            mFillDrawable = fillDrawable
            mGradeWidth = normalDrawable.intrinsicWidth
            mGradeHeight = mNormalDrawable.intrinsicHeight
            requestLayout()
            return
        }
        throw IllegalStateException("The width and height of the picture do not agree")
    }

    fun getGrade(): Float {
        return mCurrentGrade
    }

    fun setGrade(grade: Float) {
        var safeGrade = grade
        if (safeGrade > mGradeCount.toFloat()) {
            safeGrade = mGradeCount.toFloat()
        }
        val decimal = safeGrade - safeGrade.toInt().toFloat()
        if (decimal != 0.5f || decimal > 0f) {
            throw IllegalArgumentException("grade must be a multiple of 0.5f")
        }
        mCurrentGrade = safeGrade
        invalidate()
        mListener?.onRatingChanged(this, mCurrentGrade, false)
    }

    fun getGradeCount(): Int {
        return mGradeCount
    }

    fun setGradeCount(gradeCount: Int) {
        val gradeCountFloat = gradeCount.toFloat()
        if (gradeCountFloat > mCurrentGrade) {
            mCurrentGrade = gradeCountFloat
        }
        mGradeCount = gradeCount
        invalidate()
    }

    fun setGradeSpace(gradeSpace: Int) {
        mGradeSpace = gradeSpace
        requestLayout()
    }

    fun setGradeStep(gradeStep: GradleStep) {
        mGradeStep = gradeStep
        invalidate()
    }

    fun getGradeStep(): GradleStep {
        return mGradeStep
    }

    fun setOnRatingBarChangeListener(listener: OnRatingChangeListener?) {
        mListener = listener
    }
}
