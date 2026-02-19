package com.hjq.shape.drawable

import android.content.res.Resources
import android.graphics.Rect
import android.graphics.drawable.Drawable

class ShapeState() : Drawable.ConstantState() {
    @JvmField
    var mCenterX = 0.5f

    @JvmField
    var mCenterY = 0.5f

    @JvmField
    var mChangingConfigurations = 0

    @JvmField
    var mGradientColors: IntArray? = null

    @JvmField
    var mGradientOrientation: ShapeGradientOrientation = ShapeGradientOrientation.TOP_BOTTOM

    @JvmField
    var mGradientRadius = 0.5f

    @JvmField
    var mGradientType = 0

    @JvmField
    var mHasSolidColor = false

    @JvmField
    var mHeight = -1

    @JvmField
    var mInnerRadius = 0

    @JvmField
    var mInnerRadiusRatio = 0f

    @JvmField
    var mOpaque = false

    @JvmField
    var mPadding: Rect? = null

    @JvmField
    var mPositions: FloatArray? = null

    @JvmField
    var mRadius = 0f

    @JvmField
    var mRadiusArray: FloatArray? = null

    @JvmField
    var mShadowColor = 0

    @JvmField
    var mShadowOffsetX = 0

    @JvmField
    var mShadowOffsetY = 0

    @JvmField
    var mShadowSize = 0

    @JvmField
    var mShapeType = 0

    @JvmField
    var mSolidColor = 0

    @JvmField
    var mStrokeColor = 0

    @JvmField
    var mStrokeDashGap = 0f

    @JvmField
    var mStrokeDashWidth = 0f

    @JvmField
    var mStrokeWidth = -1

    @JvmField
    var mTempColors: IntArray? = null

    @JvmField
    var mTempPositions: FloatArray? = null

    @JvmField
    var mThickness = 0

    @JvmField
    var mThicknessRatio = 0f

    @JvmField
    var mUseLevel = false

    @JvmField
    var mUseLevelForShape = false

    @JvmField
    var mWidth = -1

    constructor(shapeState: ShapeState) : this() {
        mChangingConfigurations = shapeState.mChangingConfigurations
        mShapeType = shapeState.mShapeType
        mGradientType = shapeState.mGradientType
        mGradientOrientation = shapeState.mGradientOrientation
        shapeState.mGradientColors?.let {
            mGradientColors = it.clone()
        }
        shapeState.mPositions?.let {
            mPositions = it.clone()
        }
        mHasSolidColor = shapeState.mHasSolidColor
        mSolidColor = shapeState.mSolidColor
        mStrokeWidth = shapeState.mStrokeWidth
        mStrokeColor = shapeState.mStrokeColor
        mStrokeDashWidth = shapeState.mStrokeDashWidth
        mStrokeDashGap = shapeState.mStrokeDashGap
        mRadius = shapeState.mRadius
        shapeState.mRadiusArray?.let {
            mRadiusArray = it.clone()
        }
        shapeState.mPadding?.let {
            mPadding = Rect(it)
        }
        mWidth = shapeState.mWidth
        mHeight = shapeState.mHeight
        mInnerRadiusRatio = shapeState.mInnerRadiusRatio
        mThicknessRatio = shapeState.mThicknessRatio
        mInnerRadius = shapeState.mInnerRadius
        mThickness = shapeState.mThickness
        mCenterX = shapeState.mCenterX
        mCenterY = shapeState.mCenterY
        mGradientRadius = shapeState.mGradientRadius
        mUseLevel = shapeState.mUseLevel
        mUseLevelForShape = shapeState.mUseLevelForShape
        mOpaque = shapeState.mOpaque
        mShadowSize = shapeState.mShadowSize
        mShadowColor = shapeState.mShadowColor
        mShadowOffsetX = shapeState.mShadowOffsetX
        mShadowOffsetY = shapeState.mShadowOffsetY
    }

    override fun newDrawable(): Drawable {
        return ShapeDrawable(this)
    }

    override fun newDrawable(res: Resources?): Drawable {
        return ShapeDrawable(this)
    }

    override fun getChangingConfigurations(): Int {
        return mChangingConfigurations
    }

    fun setShape(shape: Int) {
        mShapeType = shape
        computeOpacity()
    }

    fun setGradientType(gradientType: Int) {
        mGradientType = gradientType
    }

    fun setGradientCenter(centerX: Float, centerY: Float) {
        mCenterX = centerX
        mCenterY = centerY
    }

    fun setGradientColor(gradientColors: IntArray?) {
        mHasSolidColor = false
        mGradientColors = gradientColors
        computeOpacity()
    }

    fun setSolidColor(solidColor: Int) {
        mHasSolidColor = true
        mSolidColor = solidColor
        mGradientColors = null
        computeOpacity()
    }

    fun setStroke(strokeWidth: Int, strokeColor: Int) {
        mStrokeWidth = strokeWidth
        mStrokeColor = strokeColor
        computeOpacity()
    }

    fun setStroke(strokeWidth: Int, strokeColor: Int, strokeDashWidth: Float, strokeDashGap: Float) {
        mStrokeWidth = strokeWidth
        mStrokeColor = strokeColor
        mStrokeDashWidth = strokeDashWidth
        mStrokeDashGap = strokeDashGap
        computeOpacity()
    }

    fun setCornerRadius(radius: Float) {
        var safeRadius = radius
        if (safeRadius < 0f) {
            safeRadius = 0f
        }
        mRadius = safeRadius
        mRadiusArray = null
    }

    fun setCornerRadii(radiusArray: FloatArray?) {
        mRadiusArray = radiusArray
        if (radiusArray == null) {
            mRadius = 0f
        }
    }

    fun setSize(width: Int, height: Int) {
        mWidth = width
        mHeight = height
    }

    fun setGradientRadius(gradientRadius: Float) {
        mGradientRadius = gradientRadius
    }

    fun setShadowColor(shadowColor: Int) {
        mShadowColor = shadowColor
    }

    fun setShadowSize(shadowSize: Int) {
        mShadowSize = shadowSize
    }

    fun setShadowOffsetX(shadowOffsetX: Int) {
        mShadowOffsetX = shadowOffsetX
    }

    fun setShadowOffsetY(shadowOffsetY: Int) {
        mShadowOffsetY = shadowOffsetY
    }

    private fun computeOpacity() {
        if (mShapeType != 0) {
            mOpaque = false
        } else if (mRadius > 0f || mRadiusArray != null) {
            mOpaque = false
        } else if (mStrokeWidth > 0 && !isOpaque(mStrokeColor)) {
            mOpaque = false
        } else if (mHasSolidColor) {
            mOpaque = isOpaque(mSolidColor)
        } else {
            val gradientColors = mGradientColors
            if (gradientColors != null) {
                for (color in gradientColors) {
                    if (!isOpaque(color)) {
                        mOpaque = false
                        return
                    }
                }
            }
            mOpaque = true
        }
    }

    companion object {
        private fun isOpaque(color: Int): Boolean {
            return (color shr 24 and 255) == 255
        }
    }
}
