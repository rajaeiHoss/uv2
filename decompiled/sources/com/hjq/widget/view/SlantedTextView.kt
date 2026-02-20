package com.hjq.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import androidx.core.view.GravityCompat
import com.hjq.widget.R

class SlantedTextView : View {
    private val mBackgroundPaint: Paint
    private var mColorBackground = 0
    private var mGravity = 0
    private var mText: String = ""
    private val mTextBounds = Rect()
    private var mTextHeight = 0
    private val mTextPaint: TextPaint
    private var mTriangle = false

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val paint = Paint()
        mBackgroundPaint = paint
        paint.style = Paint.Style.FILL
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
        paint.isAntiAlias = true

        val textPaint = TextPaint(1)
        mTextPaint = textPaint
        textPaint.isAntiAlias = true

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlantedTextView)
        setText(typedArray.getString(R.styleable.SlantedTextView_android_text))
        setTextSize(
            0,
            typedArray.getDimensionPixelSize(
                R.styleable.SlantedTextView_android_textSize,
                resources.getDimension(R.dimen.sp_12).toInt()
            ).toFloat()
        )
        setTextColor(typedArray.getColor(R.styleable.SlantedTextView_android_textColor, -0x1))
        setTextStyle(
            Typeface.defaultFromStyle(
                typedArray.getInt(R.styleable.SlantedTextView_android_textStyle, 0)
            )
        )
        setGravity(typedArray.getInt(R.styleable.SlantedTextView_android_gravity, GravityCompat.END))
        setColorBackground(
            typedArray.getColor(
                R.styleable.SlantedTextView_android_colorBackground,
                getAccentColor()
            )
        )
        setTriangle(typedArray.getBoolean(R.styleable.SlantedTextView_triangle, false))
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        mTextPaint.getTextBounds(mText, 0, mText.length, mTextBounds)
        mTextHeight = mTextBounds.height() + paddingTop + paddingBottom

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measuredWidth = when (widthMode) {
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> mTextBounds.width() + paddingLeft + paddingRight
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(widthMeasureSpec)
            else -> 0
        }

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val measuredHeight = when (heightMode) {
            MeasureSpec.AT_MOST, MeasureSpec.UNSPECIFIED -> mTextBounds.height() + paddingTop + paddingBottom
            MeasureSpec.EXACTLY -> MeasureSpec.getSize(heightMeasureSpec)
            else -> 0
        }

        val size = maxOf(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        drawText(canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        val path = Path()
        val width = canvas.width
        val height = canvas.height
        val gravity = mGravity
        if (gravity != 0) {
            if (gravity != 3) {
                if (gravity != 5) {
                    if (gravity != 51) {
                        if (gravity != 53) {
                            if (gravity == 80 || gravity == 83) {
                                if (mTriangle) {
                                    val h = height.toFloat()
                                    path.lineTo(width.toFloat(), h)
                                    path.lineTo(0.0f, h)
                                } else {
                                    val h = height.toFloat()
                                    path.lineTo(width.toFloat(), h)
                                    path.lineTo((width - mTextHeight).toFloat(), h)
                                    path.lineTo(0.0f, mTextHeight.toFloat())
                                }
                                path.close()
                                canvas.drawPath(path, mBackgroundPaint)
                                canvas.save()
                            } else if (gravity == 85) {
                                if (mTriangle) {
                                    val h = height.toFloat()
                                    path.moveTo(0.0f, h)
                                    val w = width.toFloat()
                                    path.lineTo(w, h)
                                    path.lineTo(w, 0.0f)
                                } else {
                                    val h = height.toFloat()
                                    path.moveTo(0.0f, h)
                                    path.lineTo((mTextHeight.toFloat()) * 1.0f, h)
                                    val w = width.toFloat()
                                    path.lineTo(w, mTextHeight.toFloat())
                                    path.lineTo(w, 0.0f)
                                }
                                path.close()
                                canvas.drawPath(path, mBackgroundPaint)
                                canvas.save()
                            } else {
                                throw IllegalArgumentException("are you ok?")
                            }
                        }
                    }
                }
            }
            if (mTriangle) {
                path.lineTo(0.0f, height.toFloat())
                path.lineTo(width.toFloat(), 0.0f)
            } else {
                path.moveTo(width.toFloat(), 0.0f)
                path.lineTo(0.0f, height.toFloat())
                path.lineTo(0.0f, (height - mTextHeight).toFloat())
                path.lineTo((width - mTextHeight).toFloat(), 0.0f)
            }
            path.close()
            canvas.drawPath(path, mBackgroundPaint)
            canvas.save()
        }

        if (mTriangle) {
            val w = width.toFloat()
            path.lineTo(w, 0.0f)
            path.lineTo(w, height.toFloat())
        } else {
            val w = width.toFloat()
            path.lineTo(w, height.toFloat())
            path.lineTo(w, (height - mTextHeight).toFloat())
            path.lineTo((mTextHeight.toFloat()) * 1.0f, 0.0f)
        }
        path.close()
        canvas.drawPath(path, mBackgroundPaint)
        canvas.save()
    }

    private fun drawText(canvas: Canvas) {
        var textX: Float
        var textY: Float
        var pivotX: Float
        var pivotY: Float

        val width = canvas.width - (mTextHeight / 2)
        val height = canvas.height
        val textHeight = mTextHeight
        val usableBottom = height - (textHeight / 2)
        val halfTextHeight = textHeight / 2
        val gravity = mGravity
        var angle = -45.0f

        if (gravity != 0) {
            if (gravity != 3) {
                if (gravity != 5) {
                    if (gravity != 51) {
                        if (gravity != 53) {
                            if (gravity == 80 || gravity == 83) {
                                val rect = Rect(0, halfTextHeight, width, usableBottom + halfTextHeight)
                                val rectF = RectF(rect)
                                rectF.right = mTextPaint.measureText(mText, 0, mText.length)
                                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent()
                                rectF.left += (rect.width().toFloat() - rectF.right) / 2.0f
                                rectF.top += (rect.height().toFloat() - rectF.bottom) / 2.0f
                                textX = rectF.left
                                textY = rectF.top - mTextPaint.ascent()
                                pivotX = width.toFloat() / 2.0f
                                pivotY = (usableBottom.toFloat() / 2.0f) + halfTextHeight.toFloat()
                                angle = 45.0f
                                canvas.rotate(angle, pivotX, pivotY)
                                canvas.drawText(mText, textX, textY, mTextPaint)
                                return
                            } else if (gravity == 85) {
                                val rect = Rect(
                                    halfTextHeight,
                                    halfTextHeight,
                                    width + halfTextHeight,
                                    usableBottom + halfTextHeight
                                )
                                val rectF = RectF(rect)
                                rectF.right = mTextPaint.measureText(mText, 0, mText.length)
                                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent()
                                rectF.left += (rect.width().toFloat() - rectF.right) / 2.0f
                                rectF.top += (rect.height().toFloat() - rectF.bottom) / 2.0f
                                textX = rectF.left
                                textY = rectF.top - mTextPaint.ascent()
                                val half = halfTextHeight.toFloat()
                                pivotX = (width.toFloat() / 2.0f) + half
                                pivotY = (usableBottom.toFloat() / 2.0f) + half
                                canvas.rotate(angle, pivotX, pivotY)
                                canvas.drawText(mText, textX, textY, mTextPaint)
                                return
                            } else {
                                throw IllegalArgumentException("are you ok?")
                            }
                        }
                    }
                }
            }

            val rect = Rect(0, 0, width, usableBottom)
            val rectF = RectF(rect)
            rectF.right = mTextPaint.measureText(mText, 0, mText.length)
            rectF.bottom = mTextPaint.descent() - mTextPaint.ascent()
            rectF.left += (rect.width().toFloat() - rectF.right) / 2.0f
            rectF.top += (rect.height().toFloat() - rectF.bottom) / 2.0f
            textX = rectF.left
            textY = rectF.top - mTextPaint.ascent()
            pivotX = width.toFloat() / 2.0f
            pivotY = usableBottom.toFloat() / 2.0f
            canvas.rotate(angle, pivotX, pivotY)
            canvas.drawText(mText, textX, textY, mTextPaint)
            return
        }

        val rect = Rect(halfTextHeight, 0, width + halfTextHeight, usableBottom)
        val rectF = RectF(rect)
        rectF.right = mTextPaint.measureText(mText, 0, mText.length)
        rectF.bottom = mTextPaint.descent() - mTextPaint.ascent()
        rectF.left += (rect.width().toFloat() - rectF.right) / 2.0f
        rectF.top += (rect.height().toFloat() - rectF.bottom) / 2.0f
        textX = rectF.left
        textY = rectF.top - mTextPaint.ascent()
        pivotX = (width.toFloat() / 2.0f) + halfTextHeight.toFloat()
        pivotY = usableBottom.toFloat() / 2.0f
        angle = 45.0f
        canvas.rotate(angle, pivotX, pivotY)
        canvas.drawText(mText, textX, textY, mTextPaint)
    }

    fun getText(): String {
        return mText
    }

    fun setText(resId: Int) {
        setText(resources.getString(resId))
    }

    fun setText(text: String?) {
        if (!TextUtils.isEmpty(text) && !getText().equals(text)) {
            mText = text!!
            invalidate()
        }
    }

    fun getTextColor(): Int {
        return mTextPaint.color
    }

    fun setTextColor(color: Int) {
        if (getTextColor() != color) {
            mTextPaint.color = color
            invalidate()
        }
    }

    fun getTextSize(): Float {
        return mTextPaint.textSize
    }

    fun setTextSize(size: Float) {
        setTextSize(2, size)
    }

    fun setTextSize(unit: Int, size: Float) {
        val applied = TypedValue.applyDimension(unit, size, resources.displayMetrics)
        if (getTextSize() != applied) {
            mTextPaint.textSize = applied
            invalidate()
        }
    }

    fun getTextStyle(): Typeface {
        return mTextPaint.typeface
    }

    fun setTextStyle(typeface: Typeface?) {
        if (getTextStyle() != typeface) {
            mTextPaint.typeface = typeface
            invalidate()
        }
    }

    fun getColorBackground(): Int {
        return mColorBackground
    }

    fun setColorBackground(color: Int) {
        if (getColorBackground() != color) {
            mColorBackground = color
            mBackgroundPaint.color = color
            invalidate()
        }
    }

    fun getGravity(): Int {
        return mGravity
    }

    fun setGravity(gravity: Int) {
        if (mGravity != gravity) {
            mGravity = Gravity.getAbsoluteGravity(gravity, resources.configuration.layoutDirection)
            invalidate()
        }
    }

    fun isTriangle(): Boolean {
        return mTriangle
    }

    fun setTriangle(triangle: Boolean) {
        if (isTriangle() != triangle) {
            mTriangle = triangle
            invalidate()
        }
    }

    private fun getAccentColor(): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
        return typedValue.data
    }

    companion object {
        const val ROTATE_ANGLE: Int = 45
    }
}
