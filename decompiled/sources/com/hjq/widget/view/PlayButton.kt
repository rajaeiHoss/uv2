package com.hjq.widget.view

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure
import com.hjq.widget.R

class PlayButton : View {
    private var mAnimDuration = 0
    private lateinit var mBgRectF: RectF
    private var mCenterX = 0
    private var mCenterY = 0
    private var mCircleRadius = 0
    private var mCurrentState = STATE_PAUSE
    private val mDstPath = Path()
    private var mFraction = 1f
    private var mHeight = 0
    private val mPaint: Paint
    private val mPath = Path()
    private var mPathLength = 0f
    private val mPathMeasure = PathMeasure()
    private lateinit var mRectF: RectF
    private var mWidth = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.PlayButton)
        val color = typedArray.getColor(R.styleable.PlayButton_pb_lineColor, -1)
        val lineSize = typedArray.getInteger(
            R.styleable.PlayButton_pb_lineSize,
            resources.getDimension(R.dimen.dp_4).toInt()
        )
        mAnimDuration = typedArray.getInteger(R.styleable.PlayButton_pb_animDuration, 200)
        typedArray.recycle()

        setLayerType(LAYER_TYPE_SOFTWARE, null)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint = paint
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.color = color
        paint.strokeWidth = lineSize.toFloat()
        paint.pathEffect = CornerPathEffect(1f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = (w * 9) / 10
        mHeight = (h * 9) / 10
        mCircleRadius = mWidth / resources.getDimension(R.dimen.dp_4).toInt()
        mCenterX = w / 2
        mCenterY = h / 2

        mRectF = RectF(
            (mCenterX - mCircleRadius).toFloat(),
            mCenterY.toFloat() + (mCircleRadius.toFloat() * 0.6f),
            (mCenterX + mCircleRadius).toFloat(),
            mCenterY.toFloat() + (mCircleRadius.toFloat() * 2.6f)
        )

        mBgRectF = RectF(
            mCenterX.toFloat() - (mWidth.toFloat() / 2f),
            mCenterY.toFloat() - (mHeight.toFloat() / 2f),
            mCenterX.toFloat() + (mWidth.toFloat() / 2f),
            mCenterY.toFloat() + (mHeight.toFloat() / 2f)
        )

        mPath.moveTo(
            (mCenterX - mCircleRadius).toFloat(),
            mCenterY.toFloat() + (mCircleRadius.toFloat() * 1.8f)
        )
        mPath.lineTo(
            (mCenterX - mCircleRadius).toFloat(),
            mCenterY.toFloat() - (mCircleRadius.toFloat() * 1.8f)
        )
        mPath.lineTo((mCenterX + mCircleRadius).toFloat(), mCenterY.toFloat())
        mPath.close()
        mPathMeasure.setPath(mPath, false)
        mPathLength = mPathMeasure.length
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthSpec = widthMeasureSpec
        var heightSpec = heightMeasureSpec
        val widthMode = MeasureSpec.getMode(widthSpec)
        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.UNSPECIFIED) {
            widthSpec = MeasureSpec.makeMeasureSpec(resources.getDimension(R.dimen.dp_60).toInt(), BasicMeasure.EXACTLY)
        }
        val heightMode = MeasureSpec.getMode(heightSpec)
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            heightSpec = MeasureSpec.makeMeasureSpec(resources.getDimension(R.dimen.dp_60).toInt(), BasicMeasure.EXACTLY)
        }
        setMeasuredDimension(widthSpec, heightSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(mCenterX.toFloat(), mCenterY.toFloat(), mWidth.toFloat() / 2f, mPaint)
        val fraction = mFraction
        if (fraction < 0f) {
            canvas.drawLine(
                (mCenterX + mCircleRadius).toFloat(),
                (mCenterY.toFloat() - (mCircleRadius.toFloat() * 1.6f)) + ((mCircleRadius * 10).toFloat() * fraction),
                (mCenterX + mCircleRadius).toFloat(),
                mCenterY.toFloat() + (mCircleRadius.toFloat() * 1.6f) + ((mCircleRadius * 10).toFloat() * fraction),
                mPaint
            )
            canvas.drawLine(
                (mCenterX - mCircleRadius).toFloat(),
                mCenterY.toFloat() - (mCircleRadius.toFloat() * 1.6f),
                (mCenterX - mCircleRadius).toFloat(),
                mCenterY.toFloat() + (mCircleRadius.toFloat() * 1.6f),
                mPaint
            )
            canvas.drawArc(mBgRectF, -105f, 360f, false, mPaint)
        } else if (fraction <= 0.3f) {
            canvas.drawLine(
                (mCenterX + mCircleRadius).toFloat(),
                (mCenterY.toFloat() - (mCircleRadius.toFloat() * 1.6f)) + ((((mCircleRadius.toFloat() * 3.2f) / 0.3f) * fraction)),
                (mCenterX + mCircleRadius).toFloat(),
                mCenterY.toFloat() + (mCircleRadius.toFloat() * 1.6f),
                mPaint
            )
            canvas.drawLine(
                (mCenterX - mCircleRadius).toFloat(),
                mCenterY.toFloat() - (mCircleRadius.toFloat() * 1.6f),
                (mCenterX - mCircleRadius).toFloat(),
                mCenterY.toFloat() + (mCircleRadius.toFloat() * 1.6f),
                mPaint
            )
            if (mFraction != 0f) {
                canvas.drawArc(mRectF, 0f, mFraction * 600f, false, mPaint)
            }
            canvas.drawArc(mBgRectF, (mFraction * 360f) - 0.04248047f, (1f - mFraction) * 360f, false, mPaint)
        } else if (fraction <= 0.6f) {
            canvas.drawArc(
                mRectF,
                (fraction - 0.3f) * 600f,
                180f - ((fraction - 0.3f) * 600f),
                false,
                mPaint
            )
            mDstPath.reset()
            mPathMeasure.getSegment(
                0.02f * mPathLength,
                (0.38f * mPathLength) + (((mPathLength * 0.42f) / 0.3f) * (mFraction - 0.3f)),
                mDstPath,
                true
            )
            canvas.drawPath(mDstPath, mPaint)
            canvas.drawArc(mBgRectF, (mFraction * 360f) - 0.04248047f, (1f - mFraction) * 360f, false, mPaint)
        } else if (fraction <= 0.8f) {
            mDstPath.reset()
            mPathMeasure.getSegment(
                (0.02f * mPathLength) + (((mPathLength * 0.2f) / 0.2f) * (mFraction - 0.6f)),
                (0.8f * mPathLength) + (((mPathLength * 0.2f) / 0.2f) * (mFraction - 0.6f)),
                mDstPath,
                true
            )
            canvas.drawPath(mDstPath, mPaint)
            canvas.drawArc(mBgRectF, (mFraction * 360f) - 0.04248047f, (1f - mFraction) * 360f, false, mPaint)
        } else {
            mDstPath.reset()
            mPathMeasure.getSegment((mCircleRadius * 10).toFloat() * (mFraction - 1f), mPathLength, mDstPath, true)
            canvas.drawPath(mDstPath, mPaint)
        }
    }

    fun play() {
        if (mCurrentState != STATE_PLAY) {
            mCurrentState = STATE_PLAY
            val animator = ValueAnimator.ofFloat(1f, 100f)
            animator.duration = mAnimDuration.toLong()
            animator.interpolator = AnticipateInterpolator()
            animator.addUpdateListener { valueAnimator ->
                mFraction = 1f - valueAnimator.animatedFraction
                invalidate()
            }
            animator.start()
        }
    }

    fun pause() {
        if (mCurrentState != STATE_PAUSE) {
            mCurrentState = STATE_PAUSE
            val animator = ValueAnimator.ofFloat(1f, 100f)
            animator.duration = mAnimDuration.toLong()
            animator.interpolator = AnticipateInterpolator()
            animator.addUpdateListener { valueAnimator ->
                mFraction = valueAnimator.animatedFraction
                invalidate()
            }
            animator.start()
        }
    }

    fun getCurrentState(): Int {
        return mCurrentState
    }

    fun setAnimDuration(animDuration: Int) {
        mAnimDuration = animDuration
    }

    fun setLineColor(lineColor: Int) {
        mPaint.color = lineColor
        invalidate()
    }

    fun setLineSize(lineSize: Int) {
        mPaint.strokeWidth = lineSize.toFloat()
        invalidate()
    }

    companion object {
        const val STATE_PAUSE = 1
        const val STATE_PLAY = 0
    }
}
