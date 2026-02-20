package com.hjq.shape.drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import androidx.core.view.ViewCompat

open class ShapeDrawable : Drawable {
    private var mAlpha = 255
    private var mColorFilter: ColorFilter? = null
    private var mDither = false
    private val mFillPaint = Paint(1)
    private var mLayerPaint: Paint? = null
    private var mMutated = false
    private var mPadding: Rect? = null
    private val mPath = Path()
    private var mPathIsDirty = true
    private val mRect = RectF()
    private var mRectIsDirty = true
    private var mRingPath: Path? = null
    private var mShapeState: ShapeState
    private var mStrokePaint: Paint? = null

    constructor() : this(ShapeState())

    constructor(shapeState: ShapeState) {
        mShapeState = shapeState
        initializeWithState(shapeState)
    }

    fun getShapeState(): ShapeState {
        return mShapeState
    }

    override fun getPadding(padding: Rect): Boolean {
        val rect = mPadding
        if (rect == null) {
            return super.getPadding(padding)
        }
        padding.set(rect)
        return true
    }

    fun setPadding(padding: Rect?): ShapeDrawable {
        mPadding = padding
        return this
    }

    fun setShape(shape: Int): ShapeDrawable {
        mRingPath = null
        mPathIsDirty = true
        mShapeState.setShape(shape)
        invalidateSelf()
        return this
    }

    fun setSize(width: Int, height: Int): ShapeDrawable {
        mShapeState.setSize(width, height)
        mPathIsDirty = true
        invalidateSelf()
        return this
    }

    fun setRadius(radius: Float): ShapeDrawable {
        mShapeState.setCornerRadius(radius)
        mPathIsDirty = true
        invalidateSelf()
        return this
    }

    fun setRadius(topLeft: Float, topRight: Float, bottomLeft: Float, bottomRight: Float): ShapeDrawable {
        if (topLeft == topRight && topLeft == bottomLeft && topLeft == bottomRight) {
            return setRadius(topLeft)
        }
        mShapeState.setCornerRadii(
            floatArrayOf(
                topLeft,
                topLeft,
                topRight,
                topRight,
                bottomRight,
                bottomRight,
                bottomLeft,
                bottomLeft
            )
        )
        mPathIsDirty = true
        invalidateSelf()
        return this
    }

    fun setSolidColor(solidColor: Int): ShapeDrawable {
        mShapeState.setSolidColor(solidColor)
        mFillPaint.color = solidColor
        invalidateSelf()
        return this
    }

    fun setStrokeColor(strokeColor: Int): ShapeDrawable {
        setStroke(
            mShapeState.mStrokeColor,
            mShapeState.mStrokeWidth,
            mShapeState.mStrokeDashWidth,
            mShapeState.mStrokeDashGap
        )
        return this
    }

    fun setStrokeWidth(strokeWidth: Int): ShapeDrawable {
        setStroke(
            mShapeState.mStrokeColor,
            strokeWidth,
            mShapeState.mStrokeDashWidth,
            mShapeState.mStrokeDashGap
        )
        return this
    }

    fun setDashWidth(dashWidth: Float): ShapeDrawable {
        setStroke(
            mShapeState.mStrokeColor,
            mShapeState.mStrokeWidth,
            dashWidth,
            mShapeState.mStrokeDashGap
        )
        return this
    }

    fun setDashGap(dashGap: Float): ShapeDrawable {
        setStroke(
            mShapeState.mStrokeColor,
            mShapeState.mStrokeWidth,
            mShapeState.mStrokeDashWidth,
            dashGap
        )
        return this
    }

    fun setStroke(strokeColor: Int, strokeWidth: Int, dashWidth: Float, dashGap: Float): ShapeDrawable {
        mShapeState.setStroke(strokeColor, strokeWidth, dashWidth, dashGap)

        if (mStrokePaint == null) {
            val paint = Paint(1)
            mStrokePaint = paint
            paint.style = Paint.Style.STROKE
        }

        mStrokePaint!!.strokeWidth = strokeColor.toFloat()
        mStrokePaint!!.color = strokeWidth
        val dashPathEffect = if (dashWidth > 0.0f) {
            DashPathEffect(floatArrayOf(dashWidth, dashGap), 0.0f)
        } else {
            null
        }
        mStrokePaint!!.pathEffect = dashPathEffect
        invalidateSelf()
        return this
    }

    fun setGradientColors(gradientColors: IntArray?): ShapeDrawable {
        mShapeState.setGradientColor(gradientColors)
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setGradientType(gradientType: Int): ShapeDrawable {
        mShapeState.setGradientType(gradientType)
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setGradientCenter(centerX: Float, centerY: Float): ShapeDrawable {
        mShapeState.setGradientCenter(centerX, centerY)
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setGradientRadius(gradientRadius: Float): ShapeDrawable {
        mShapeState.setGradientRadius(gradientRadius)
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setUseLevel(useLevel: Boolean): ShapeDrawable {
        mShapeState.mUseLevel = useLevel
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setGradientAngle(angle: Int): ShapeDrawable {
        val normalized = angle % 360
        if (normalized % 45 == 0) {
            if (normalized == 0) {
                setGradientOrientation(ShapeGradientOrientation.LEFT_RIGHT)
            } else if (normalized == 45) {
                setGradientOrientation(ShapeGradientOrientation.BL_TR)
            } else if (normalized == 90) {
                setGradientOrientation(ShapeGradientOrientation.BOTTOM_TOP)
            } else if (normalized == 135) {
                setGradientOrientation(ShapeGradientOrientation.BR_TL)
            } else if (normalized == 180) {
                setGradientOrientation(ShapeGradientOrientation.RIGHT_LEFT)
            } else if (normalized == 225) {
                setGradientOrientation(ShapeGradientOrientation.TR_BL)
            } else if (normalized == 270) {
                setGradientOrientation(ShapeGradientOrientation.TOP_BOTTOM)
            } else if (normalized == 315) {
                setGradientOrientation(ShapeGradientOrientation.TL_BR)
            }
        }
        return this
    }

    fun setGradientOrientation(gradientOrientation: ShapeGradientOrientation): ShapeDrawable {
        mShapeState.mGradientOrientation = gradientOrientation
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setInnerRadius(innerRadius: Int): ShapeDrawable {
        mShapeState.mInnerRadius = innerRadius
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setInnerRadiusRatio(innerRadiusRatio: Float): ShapeDrawable {
        mShapeState.mInnerRadiusRatio = innerRadiusRatio
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setThickness(thickness: Int): ShapeDrawable {
        mShapeState.mThickness = thickness
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setThicknessRatio(thicknessRatio: Float): ShapeDrawable {
        mShapeState.mThicknessRatio = thicknessRatio
        mRectIsDirty = true
        invalidateSelf()
        return this
    }

    fun setShadowColor(shadowColor: Int): ShapeDrawable {
        mShapeState.setShadowColor(shadowColor)
        mPathIsDirty = true
        invalidateSelf()
        return this
    }

    fun setShadowSize(shadowSize: Int): ShapeDrawable {
        mShapeState.setShadowSize(shadowSize)
        mPathIsDirty = true
        invalidateSelf()
        return this
    }

    fun setShadowOffsetX(shadowOffsetX: Int): ShapeDrawable {
        mShapeState.setShadowOffsetX(shadowOffsetX)
        mPathIsDirty = true
        invalidateSelf()
        return this
    }

    fun setShadowOffsetY(shadowOffsetY: Int): ShapeDrawable {
        mShapeState.setShadowOffsetY(shadowOffsetY)
        mPathIsDirty = true
        invalidateSelf()
        return this
    }

    fun intoBackground(view: View) {
        if (mShapeState.mStrokeDashGap > 0.0f || mShapeState.mShadowSize > 0) {
            view.setLayerType(1, null)
        }
        view.background = this
    }

    override fun draw(canvas: Canvas) {
        var i: Int
        val ensureValidRect = ensureValidRect()
        if (ensureValidRect) {
            val alpha = mFillPaint.alpha
            val paint = mStrokePaint
            val alpha2 = paint?.alpha ?: 0
            val modulateAlpha = modulateAlpha(alpha)
            val modulateAlpha2 = modulateAlpha(alpha2)
            val hasStroke = modulateAlpha2 > 0 && paint != null && paint.strokeWidth > 0.0f
            val hasFill = modulateAlpha > 0
            val shapeState = mShapeState
            val useLayer = hasStroke &&
                hasFill &&
                shapeState.mShapeType != 2 &&
                modulateAlpha2 < 255 &&
                (mAlpha < 255 || mColorFilter != null)

            if (mShapeState.mShadowSize > 0) {
                if (hasStroke) {
                    mStrokePaint!!.setShadowLayer(
                        mShapeState.mShadowSize.toFloat(),
                        mShapeState.mShadowOffsetX.toFloat(),
                        mShapeState.mShadowOffsetY.toFloat(),
                        mShapeState.mShadowColor
                    )
                } else {
                    mFillPaint.setShadowLayer(
                        mShapeState.mShadowSize.toFloat(),
                        mShapeState.mShadowOffsetX.toFloat(),
                        mShapeState.mShadowOffsetY.toFloat(),
                        mShapeState.mShadowColor
                    )
                }
            } else if (hasStroke) {
                mStrokePaint!!.clearShadowLayer()
            } else {
                mFillPaint.clearShadowLayer()
            }

            if (useLayer) {
                if (mLayerPaint == null) {
                    mLayerPaint = Paint()
                }
                mLayerPaint!!.isDither = mDither
                mLayerPaint!!.alpha = mAlpha
                mLayerPaint!!.colorFilter = mColorFilter
                val strokeWidth = mStrokePaint!!.strokeWidth
                if (Build.VERSION.SDK_INT >= 21) {
                    canvas.saveLayer(
                        mRect.left - strokeWidth,
                        mRect.top - strokeWidth,
                        mRect.right + strokeWidth,
                        mRect.bottom + strokeWidth,
                        mLayerPaint
                    )
                    i = 2
                } else {
                    i = 2
                    canvas.saveLayer(
                        mRect.left - strokeWidth,
                        mRect.top - strokeWidth,
                        mRect.right + strokeWidth,
                        mRect.bottom + strokeWidth,
                        mLayerPaint,
                        4
                    )
                }
                mFillPaint.colorFilter = null
                mStrokePaint!!.colorFilter = null
            } else {
                i = 2
                mFillPaint.alpha = modulateAlpha
                mFillPaint.isDither = mDither
                mFillPaint.colorFilter = mColorFilter
                if (mColorFilter != null && !mShapeState.mHasSolidColor) {
                    mFillPaint.color = mAlpha shl 24
                }
                if (hasStroke) {
                    mStrokePaint!!.alpha = modulateAlpha2
                    mStrokePaint!!.isDither = mDither
                    mStrokePaint!!.colorFilter = mColorFilter
                }
            }

            val shapeType = shapeState.mShapeType
            if (shapeType != 0) {
                if (shapeType == 1) {
                    canvas.drawOval(mRect, mFillPaint)
                    if (hasStroke) {
                        canvas.drawOval(mRect, mStrokePaint!!)
                    }
                } else if (shapeType == i) {
                    val rect = mRect
                    val centerY = rect.centerY()
                    canvas.drawLine(rect.left, centerY, rect.right, centerY, mStrokePaint!!)
                } else if (shapeType == 3) {
                    val ring = buildRing(shapeState)
                    canvas.drawPath(ring, mFillPaint)
                    if (hasStroke) {
                        canvas.drawPath(ring, mStrokePaint!!)
                    }
                }
            } else if (shapeState.mRadiusArray != null) {
                if (mPathIsDirty || mRectIsDirty) {
                    mPath.reset()
                    mPath.addRoundRect(mRect, shapeState.mRadiusArray!!, Path.Direction.CW)
                    mRectIsDirty = false
                    mPathIsDirty = false
                }
                canvas.drawPath(mPath, mFillPaint)
                if (hasStroke) {
                    canvas.drawPath(mPath, mStrokePaint!!)
                }
            } else if (shapeState.mRadius > 0.0f) {
                var radius = shapeState.mRadius
                val min = minOf(mRect.width(), mRect.height()) * 0.5f
                if (radius > min) {
                    radius = min
                }
                canvas.drawRoundRect(mRect, radius, radius, mFillPaint)
                if (hasStroke) {
                    canvas.drawRoundRect(mRect, radius, radius, mStrokePaint!!)
                }
            } else {
                if (!(mFillPaint.color == 0 && mColorFilter == null && mFillPaint.shader == null)) {
                    canvas.drawRect(mRect, mFillPaint)
                }
                if (hasStroke) {
                    canvas.drawRect(mRect, mStrokePaint!!)
                }
            }

            if (useLayer) {
                canvas.restore()
                return
            }
            mFillPaint.alpha = alpha
            if (hasStroke) {
                mStrokePaint!!.alpha = alpha2
            }
        }
    }

    private fun modulateAlpha(alpha: Int): Int {
        val i2 = mAlpha
        return (alpha * (i2 + (i2 shr 7))) shr 8
    }

    override fun getChangingConfigurations(): Int {
        return super.getChangingConfigurations() or mShapeState.mChangingConfigurations
    }

    override fun setAlpha(alpha: Int) {
        if (alpha != mAlpha) {
            mAlpha = alpha
            invalidateSelf()
        }
    }

    override fun getAlpha(): Int {
        return mAlpha
    }

    override fun setDither(dither: Boolean) {
        if (dither != mDither) {
            mDither = dither
            invalidateSelf()
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        if (colorFilter != mColorFilter) {
            mColorFilter = colorFilter
            invalidateSelf()
        }
    }

    override fun getOpacity(): Int {
        return if (mShapeState.mOpaque) -1 else -3
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mRingPath = null
        mPathIsDirty = true
        mRectIsDirty = true
    }

    override fun onLevelChange(level: Int): Boolean {
        super.onLevelChange(level)
        mRectIsDirty = true
        mPathIsDirty = true
        invalidateSelf()
        return true
    }

    private fun buildRing(shapeState: ShapeState): Path {
        if (mRingPath != null && (!shapeState.mUseLevelForShape || !mPathIsDirty)) {
            return mRingPath!!
        }
        mPathIsDirty = false

        val level = if (shapeState.mUseLevelForShape) {
            (this.level.toFloat() * 360.0f) / 10000.0f
        } else {
            360.0f
        }

        val rect = RectF(mRect)
        val halfWidth = rect.width() / 2.0f
        val halfHeight = rect.height() / 2.0f

        val thickness = if (shapeState.mThickness != -1) {
            shapeState.mThickness.toFloat()
        } else {
            rect.width() / shapeState.mThicknessRatio
        }

        val innerRadius = if (shapeState.mInnerRadius != -1) {
            shapeState.mInnerRadius.toFloat()
        } else {
            rect.width() / shapeState.mInnerRadiusRatio
        }

        val innerBounds = RectF(rect)
        innerBounds.inset(halfWidth - innerRadius, halfHeight - innerRadius)
        val outerBounds = RectF(innerBounds)
        val thicknessNeg = -thickness
        outerBounds.inset(thicknessNeg, thicknessNeg)

        val path = mRingPath
        if (path == null) {
            mRingPath = Path()
        } else {
            path.reset()
        }

        val ringPath = mRingPath!!
        if (level >= 360.0f || level <= -360.0f) {
            ringPath.addOval(outerBounds, Path.Direction.CW)
            ringPath.addOval(innerBounds, Path.Direction.CCW)
        } else {
            ringPath.fillType = Path.FillType.EVEN_ODD
            val right = halfWidth + innerRadius
            ringPath.moveTo(right, halfHeight)
            ringPath.lineTo(right + thickness, halfHeight)
            ringPath.arcTo(outerBounds, 0.0f, level, false)
            ringPath.arcTo(innerBounds, level, -level, false)
            ringPath.close()
        }

        return ringPath
    }

    private fun ensureValidRect(): Boolean {
        throw UnsupportedOperationException("Method not decompiled: com.hjq.shape.drawable.ShapeDrawable.ensureValidRect():boolean")
    }

    override fun getIntrinsicWidth(): Int {
        return mShapeState.mWidth
    }

    override fun getIntrinsicHeight(): Int {
        return mShapeState.mHeight
    }

    override fun getConstantState(): Drawable.ConstantState {
        mShapeState.mChangingConfigurations = changingConfigurations
        return mShapeState
    }

    override fun mutate(): Drawable {
        if (!mMutated && super.mutate() === this) {
            val shapeState = ShapeState(mShapeState)
            mShapeState = shapeState
            initializeWithState(shapeState)
            mMutated = true
        }
        return this
    }

    private fun initializeWithState(shapeState: ShapeState) {
        if (shapeState.mHasSolidColor) {
            mFillPaint.color = shapeState.mSolidColor
        } else if (shapeState.mGradientColors == null) {
            mFillPaint.color = 0
        } else {
            mFillPaint.color = ViewCompat.MEASURED_STATE_MASK
        }
        mPadding = shapeState.mPadding
        if (shapeState.mStrokeWidth >= 0) {
            setStroke(
                shapeState.mStrokeWidth,
                shapeState.mStrokeColor,
                shapeState.mStrokeDashWidth,
                shapeState.mStrokeDashGap
            )
        }
    }
}
