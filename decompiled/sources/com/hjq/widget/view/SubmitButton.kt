package com.hjq.widget.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PorterDuff
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.animation.AccelerateInterpolator
import androidx.appcompat.widget.AppCompatButton
import com.hjq.widget.R

class SubmitButton : AppCompatButton {
    private lateinit var mBackgroundPaint: Paint
    private lateinit var mButtonPath: Path
    private var mButtonState = STATE_NONE
    private lateinit var mCircleLeft: RectF
    private lateinit var mCircleMid: RectF
    private lateinit var mCircleRight: RectF
    private var mCurrentProgress = 0.0f
    private var mDoResult = false
    private lateinit var mDstPath: Path
    private val mErrorColor: Int
    private lateinit var mLoadPath: Path
    private var mLoadValue = 0.0f
    private var mLoadingAnim: ValueAnimator? = null
    private lateinit var mLoadingPaint: Paint
    private var mMaxHeight = 0
    private var mMaxWidth = 0
    private lateinit var mPathMeasure: PathMeasure
    private val mProgressColor: Int
    private val mProgressStyle: Int
    private var mResultAnim: ValueAnimator? = null
    private lateinit var mResultPaint: Paint
    private lateinit var mResultPath: Path
    private var mSubmitAnim: ValueAnimator? = null
    private var mSucceed = false
    private val mSucceedColor: Int
    private var mViewHeight = 0
    private var mViewWidth = 0
    private var mX = 0
    private var mY = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setLayerType(1, null)
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.SubmitButton,
            defStyleAttr,
            0
        )
        mProgressColor = typedArray.getColor(
            R.styleable.SubmitButton_progressColor,
            getAccentColor()
        )
        mSucceedColor = typedArray.getColor(
            R.styleable.SubmitButton_succeedColor,
            Color.parseColor("#19CC95")
        )
        mErrorColor = typedArray.getColor(
            R.styleable.SubmitButton_errorColor,
            Color.parseColor("#FC8E34")
        )
        mProgressStyle = typedArray.getInt(R.styleable.SubmitButton_progressStyle, STYLE_LOADING)
        typedArray.recycle()

        initPaint()
        resetPaint()
    }

    private fun initPaint() {
        mBackgroundPaint = Paint()
        mLoadingPaint = Paint()
        mResultPaint = Paint()
        mButtonPath = Path()
        mLoadPath = Path()
        mResultPath = Path()
        mDstPath = Path()
        mCircleMid = RectF()
        mCircleLeft = RectF()
        mCircleRight = RectF()
        mPathMeasure = PathMeasure()
    }

    private fun resetPaint() {
        mBackgroundPaint.color = mProgressColor
        mBackgroundPaint.strokeWidth = 5.0f
        mBackgroundPaint.isAntiAlias = true

        mLoadingPaint.color = mProgressColor
        mLoadingPaint.style = Paint.Style.STROKE
        mLoadingPaint.strokeWidth = 9.0f
        mLoadingPaint.isAntiAlias = true

        mResultPaint.color = -0x1
        mResultPaint.style = Paint.Style.STROKE
        mResultPaint.strokeWidth = 9.0f
        mResultPaint.strokeCap = Paint.Cap.ROUND
        mResultPaint.isAntiAlias = true

        mButtonPath.reset()
        mLoadPath.reset()
        mResultPath.reset()
        mDstPath.reset()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mButtonState != STATE_LOADING) {
            val width = w - 10
            mViewWidth = width
            val height = h - 10
            mViewHeight = height
            mX = (w * 0.5).toInt()
            mY = (h * 0.5).toInt()
            mMaxWidth = width
            mMaxHeight = height
        }
    }

    override fun onDraw(canvas: Canvas) {
        val state = mButtonState
        if (state == STATE_NONE) {
            super.onDraw(canvas)
        } else if (state == STATE_SUBMIT || state == STATE_LOADING) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR)
            canvas.translate(mX.toFloat(), mY.toFloat())
            drawButton(canvas)
            drawLoading(canvas)
        } else if (state == STATE_RESULT) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR)
            canvas.translate(mX.toFloat(), mY.toFloat())
            drawButton(canvas)
            drawResult(canvas, mSucceed)
        }
    }

    private fun drawButton(canvas: Canvas) {
        mButtonPath.reset()
        val viewWidth = mViewWidth
        val viewHeight = mViewHeight

        mCircleLeft.set(
            (-viewWidth).toFloat() / 2.0f,
            (-viewHeight).toFloat() / 2.0f,
            (-viewWidth).toFloat() / 2.0f + viewHeight.toFloat(),
            viewHeight.toFloat() / 2.0f
        )
        mButtonPath.arcTo(mCircleLeft, 90.0f, 180.0f)

        mButtonPath.lineTo(
            (mViewWidth.toFloat() / 2.0f) - (viewHeight.toFloat() / 2.0f),
            (-viewHeight).toFloat() / 2.0f
        )

        val rightWidth = mViewWidth
        val rightHeight = mViewHeight
        mCircleRight.set(
            (rightWidth.toFloat() / 2.0f) - rightHeight.toFloat(),
            (-rightHeight).toFloat() / 2.0f,
            rightWidth.toFloat() / 2.0f,
            rightHeight.toFloat() / 2.0f
        )
        mButtonPath.arcTo(mCircleRight, 270.0f, 180.0f)

        mButtonPath.lineTo(
            (-mViewWidth).toFloat() / 2.0f + (mViewHeight.toFloat() / 2.0f),
            mViewHeight.toFloat() / 2.0f
        )

        canvas.drawPath(mButtonPath, mBackgroundPaint)
    }

    private fun drawLoading(canvas: Canvas) {
        mDstPath.reset()
        val maxHeight = mMaxHeight
        mCircleMid.set(
            (-maxHeight).toFloat() / 2.0f,
            (-maxHeight).toFloat() / 2.0f,
            maxHeight.toFloat() / 2.0f,
            maxHeight.toFloat() / 2.0f
        )
        mLoadPath.addArc(mCircleMid, 270.0f, 359.999f)
        mPathMeasure.setPath(mLoadPath, true)

        val start: Float
        val end: Float
        if (mProgressStyle == STYLE_LOADING) {
            start = mPathMeasure.length * mLoadValue
            end = (mPathMeasure.length / 2.0f) * mLoadValue + start
        } else {
            end = mCurrentProgress * mPathMeasure.length
            start = 0.0f
        }

        mPathMeasure.getSegment(start, end, mDstPath, true)
        canvas.drawPath(mDstPath, mLoadingPaint)
    }

    private fun drawResult(canvas: Canvas, succeed: Boolean) {
        if (succeed) {
            mResultPath.moveTo((-mViewHeight).toFloat() / 6.0f, 0.0f)
            mResultPath.lineTo(
                0.0f,
                (((-mViewHeight / 6).toDouble()) +
                    (((Math.sqrt(5.0) + 1.0) * mViewHeight.toDouble()) / 12.0)).toFloat()
            )
            val viewHeight = mViewHeight
            mResultPath.lineTo(viewHeight.toFloat() / 6.0f, (-viewHeight).toFloat() / 6.0f)
        } else {
            val viewHeight1 = mViewHeight
            mResultPath.moveTo((-viewHeight1).toFloat() / 6.0f, viewHeight1.toFloat() / 6.0f)
            val viewHeight2 = mViewHeight
            mResultPath.lineTo(viewHeight2.toFloat() / 6.0f, (-viewHeight2).toFloat() / 6.0f)
            val viewHeight3 = mViewHeight
            mResultPath.moveTo((-viewHeight3).toFloat() / 6.0f, (-viewHeight3).toFloat() / 6.0f)
            val viewHeight4 = mViewHeight
            mResultPath.lineTo(viewHeight4.toFloat() / 6.0f, viewHeight4.toFloat() / 6.0f)
        }
        canvas.drawPath(mResultPath, mResultPaint)
    }

    private fun startSubmitAnim() {
        mButtonState = STATE_SUBMIT
        val animator = ValueAnimator.ofInt(mMaxWidth, mMaxHeight)
        mSubmitAnim = animator
        animator.addUpdateListener {
            val value = (it.animatedValue as Int)
            mViewWidth = value
            if (value == mViewHeight) {
                mBackgroundPaint.color = Color.parseColor("#DDDDDD")
                mBackgroundPaint.style = Paint.Style.STROKE
            }
            invalidate()
        }
        animator.duration = 300
        animator.interpolator = AccelerateInterpolator()
        animator.start()
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                if (mDoResult) {
                    startResultAnim()
                } else {
                    startLoadingAnim()
                }
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
    }

    private fun startLoadingAnim() {
        mButtonState = STATE_LOADING
        if (mProgressStyle != STYLE_PROGRESS) {
            val animator = ValueAnimator.ofFloat(0.0f, 1.0f)
            mLoadingAnim = animator
            animator.addUpdateListener {
                mLoadValue = (it.animatedValue as Float)
                invalidate()
            }
            animator.duration = 2000
            animator.repeatCount = -1
            animator.start()
        }
    }

    private fun startResultAnim() {
        mButtonState = STATE_RESULT
        mLoadingAnim?.cancel()

        val animator = ValueAnimator.ofInt(mMaxHeight, mMaxWidth)
        mResultAnim = animator
        animator.addUpdateListener {
            val value = it.animatedValue as Int
            mViewWidth = value
            mResultPaint.alpha = ((value - mViewHeight) * 255) / (mMaxWidth - mMaxHeight)
            if (mViewWidth == mViewHeight) {
                mBackgroundPaint.color = if (mSucceed) mSucceedColor else mErrorColor
                mBackgroundPaint.style = Paint.Style.FILL_AND_STROKE
            }
            invalidate()
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                requestLayout()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
        animator.duration = 300
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }

    override fun performClick(): Boolean {
        if (mButtonState != STATE_NONE) {
            return true
        }
        startSubmitAnim()
        return super.performClick()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mSubmitAnim?.cancel()
        mLoadingAnim?.cancel()
        mResultAnim?.cancel()
    }

    fun showProgress() {
        if (mButtonState == STATE_NONE) {
            startSubmitAnim()
        }
    }

    fun showSucceed() {
        showResult(true)
    }

    fun showError() {
        showResult(false)
    }

    fun showError(delayMillis: Long) {
        showResult(false)
        postDelayed({ reset() }, delayMillis)
    }

    private fun showResult(succeed: Boolean) {
        val state = mButtonState
        if (state != STATE_NONE && state != STATE_RESULT && !mDoResult) {
            mDoResult = true
            mSucceed = succeed
            if (state == STATE_LOADING) {
                startResultAnim()
            }
        }
    }

    fun reset() {
        mSubmitAnim?.cancel()
        mLoadingAnim?.cancel()
        mResultAnim?.cancel()

        mButtonState = STATE_NONE
        mViewWidth = mMaxWidth
        mViewHeight = mMaxHeight
        mSucceed = false
        mDoResult = false
        mCurrentProgress = 0.0f
        resetPaint()
        invalidate()
    }

    fun setProgress(progress: Float) {
        mCurrentProgress = progress
        if (mProgressStyle == STYLE_PROGRESS && mButtonState == STATE_LOADING) {
            invalidate()
        }
    }

    private fun getAccentColor(): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
        return typedValue.data
    }

    companion object {
        private const val STATE_LOADING = 2
        private const val STATE_NONE = 0
        private const val STATE_RESULT = 3
        private const val STATE_SUBMIT = 1
        private const val STYLE_LOADING = 0
        private const val STYLE_PROGRESS = 1
    }
}
