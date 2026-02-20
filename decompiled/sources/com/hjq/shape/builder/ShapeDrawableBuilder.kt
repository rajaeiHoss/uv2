package com.hjq.shape.builder

import android.content.res.TypedArray
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.view.View
import com.google.android.gms.drive.DriveFile
import com.hjq.shape.drawable.ShapeDrawable
import com.hjq.shape.styleable.IShapeDrawableStyleable

class ShapeDrawableBuilder(
    private val mView: View,
    typedArray: TypedArray,
    iShapeDrawableStyleable: IShapeDrawableStyleable
) {
    private var mAngle = 0
    private var mBottomLeftRadius = 0.0f
    private var mBottomRightRadius = 0.0f
    private var mCenterX = 0.0f
    private var mCenterY = 0.0f
    private var mDashGap = 0
    private var mDashWidth = 0
    private var mGradientColor: IntArray? = null
    private var mGradientRadius = 0
    private var mGradientType = 0
    private var mInnerRadius = 0
    private var mInnerRadiusRatio = 0.0f
    private var mShadowColor = 0
    private var mShadowOffsetX = 0
    private var mShadowOffsetY = 0
    private var mShadowSize = 0
    private var mShape = 0
    private var mShapeHeight = 0
    private var mShapeWidth = 0
    private var mSolidCheckedColor: Int? = null
    private var mSolidColor = 0
    private var mSolidDisabledColor: Int? = null
    private var mSolidFocusedColor: Int? = null
    private var mSolidPressedColor: Int? = null
    private var mSolidSelectedColor: Int? = null
    private var mStrokeCheckedColor: Int? = null
    private var mStrokeColor = 0
    private var mStrokeDisabledColor: Int? = null
    private var mStrokeFocusedColor: Int? = null
    private var mStrokePressedColor: Int? = null
    private var mStrokeSelectedColor: Int? = null
    private var mStrokeWidth = 0
    private var mThickness = 0
    private var mThicknessRatio = 0.0f
    private var mTopLeftRadius = 0.0f
    private var mTopRightRadius = 0.0f
    private var mUseLevel = false

    init {
        mShape = typedArray.getInt(iShapeDrawableStyleable.getShapeTypeStyleable(), 0)
        mShapeWidth = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShapeWidthStyleable(), -1)
        mShapeHeight = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShapeHeightStyleable(), -1)
        mSolidColor = typedArray.getColor(iShapeDrawableStyleable.getSolidColorStyleable(), 0)

        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidPressedColorStyleable())) {
            mSolidPressedColor = typedArray.getColor(iShapeDrawableStyleable.getSolidPressedColorStyleable(), 0)
        }

        if (iShapeDrawableStyleable.getSolidCheckedColorStyleable() > 0 &&
            typedArray.hasValue(iShapeDrawableStyleable.getSolidCheckedColorStyleable())
        ) {
            mSolidCheckedColor = typedArray.getColor(iShapeDrawableStyleable.getSolidCheckedColorStyleable(), 0)
        }

        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidDisabledColorStyleable())) {
            mSolidDisabledColor = typedArray.getColor(iShapeDrawableStyleable.getSolidDisabledColorStyleable(), 0)
        }

        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidFocusedColorStyleable())) {
            mSolidFocusedColor = typedArray.getColor(iShapeDrawableStyleable.getSolidFocusedColorStyleable(), 0)
        }

        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidSelectedColorStyleable())) {
            mSolidSelectedColor = typedArray.getColor(iShapeDrawableStyleable.getSolidSelectedColorStyleable(), 0)
        }

        val radius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusStyleable(), 0)
        mTopLeftRadius = typedArray
            .getDimensionPixelSize(iShapeDrawableStyleable.getTopLeftRadiusStyleable(), radius)
            .toFloat()
        mTopRightRadius = typedArray
            .getDimensionPixelSize(iShapeDrawableStyleable.getTopRightRadiusStyleable(), radius)
            .toFloat()
        mBottomLeftRadius = typedArray
            .getDimensionPixelSize(iShapeDrawableStyleable.getBottomLeftRadiusStyleable(), radius)
            .toFloat()
        mBottomRightRadius = typedArray
            .getDimensionPixelSize(iShapeDrawableStyleable.getBottomRightRadiusStyleable(), radius)
            .toFloat()

        if (typedArray.hasValue(iShapeDrawableStyleable.getStartColorStyleable()) &&
            typedArray.hasValue(iShapeDrawableStyleable.getEndColorStyleable())
        ) {
            mGradientColor = if (typedArray.hasValue(iShapeDrawableStyleable.getCenterColorStyleable())) {
                intArrayOf(
                    typedArray.getColor(iShapeDrawableStyleable.getStartColorStyleable(), 0),
                    typedArray.getColor(iShapeDrawableStyleable.getCenterColorStyleable(), 0),
                    typedArray.getColor(iShapeDrawableStyleable.getEndColorStyleable(), 0)
                )
            } else {
                intArrayOf(
                    typedArray.getColor(iShapeDrawableStyleable.getStartColorStyleable(), 0),
                    typedArray.getColor(iShapeDrawableStyleable.getEndColorStyleable(), 0)
                )
            }
        }

        mUseLevel = typedArray.getBoolean(iShapeDrawableStyleable.getUseLevelStyleable(), false)
        mAngle = typedArray.getFloat(iShapeDrawableStyleable.getAngleStyleable(), 0.0f).toInt()
        mGradientType = typedArray.getInt(iShapeDrawableStyleable.getGradientTypeStyleable(), 0)
        mCenterX = typedArray.getFloat(iShapeDrawableStyleable.getCenterXStyleable(), 0.5f)
        mCenterY = typedArray.getFloat(iShapeDrawableStyleable.getCenterYStyleable(), 0.5f)
        mGradientRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getGradientRadiusStyleable(), radius)

        mStrokeColor = typedArray.getColor(iShapeDrawableStyleable.getStrokeColorStyleable(), 0)

        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokePressedColorStyleable())) {
            mStrokePressedColor = typedArray.getColor(iShapeDrawableStyleable.getStrokePressedColorStyleable(), 0)
        }

        if (iShapeDrawableStyleable.getStrokeCheckedColorStyleable() > 0 &&
            typedArray.hasValue(iShapeDrawableStyleable.getStrokeCheckedColorStyleable())
        ) {
            mStrokeCheckedColor = typedArray.getColor(iShapeDrawableStyleable.getStrokeCheckedColorStyleable(), 0)
        }

        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeDisabledColorStyleable())) {
            mStrokeDisabledColor = typedArray.getColor(iShapeDrawableStyleable.getStrokeDisabledColorStyleable(), 0)
        }

        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeFocusedColorStyleable())) {
            mStrokeFocusedColor = typedArray.getColor(iShapeDrawableStyleable.getStrokeFocusedColorStyleable(), 0)
        }

        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeSelectedColorStyleable())) {
            mStrokeSelectedColor = typedArray.getColor(iShapeDrawableStyleable.getStrokeSelectedColorStyleable(), 0)
        }

        mStrokeWidth = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getStrokeWidthStyleable(), 0)
        mDashWidth = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getDashWidthStyleable(), 0)
        mDashGap = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getDashGapStyleable(), 0)
        mInnerRadius = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getInnerRadiusStyleable(), -1)
        mInnerRadiusRatio = typedArray.getFloat(iShapeDrawableStyleable.getInnerRadiusRatioStyleable(), 3.0f)
        mThickness = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getThicknessStyleable(), -1)
        mThicknessRatio = typedArray.getFloat(iShapeDrawableStyleable.getThicknessRatioStyleable(), 9.0f)
        mShadowSize = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShadowSizeStyleable(), 0)
        mShadowColor = typedArray.getColor(iShapeDrawableStyleable.getShadowColorStyleable(), DriveFile.MODE_READ_ONLY)
        mShadowOffsetX = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getShadowOffsetXStyleable(), 0)
        mShadowOffsetY = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getShadowOffsetYStyleable(), 0)
    }

    fun setShape(shape: Int): ShapeDrawableBuilder {
        mShape = shape
        return this
    }

    fun getShape(): Int {
        return mShape
    }

    fun setShapeWidth(shapeWidth: Int): ShapeDrawableBuilder {
        mShapeWidth = shapeWidth
        return this
    }

    fun getShapeWidth(): Int {
        return mShapeWidth
    }

    fun setShapeHeight(shapeHeight: Int): ShapeDrawableBuilder {
        mShapeHeight = shapeHeight
        return this
    }

    fun getShapeHeight(): Int {
        return mShapeHeight
    }

    fun setSolidColor(solidColor: Int): ShapeDrawableBuilder {
        mSolidColor = solidColor
        clearGradientColor()
        return this
    }

    fun getSolidColor(): Int {
        return mSolidColor
    }

    fun setSolidPressedColor(solidPressedColor: Int?): ShapeDrawableBuilder {
        mSolidPressedColor = solidPressedColor
        return this
    }

    fun getSolidPressedColor(): Int? {
        return mSolidPressedColor
    }

    fun setSolidCheckedColor(solidCheckedColor: Int?): ShapeDrawableBuilder {
        mSolidCheckedColor = solidCheckedColor
        return this
    }

    fun getSolidCheckedColor(): Int? {
        return mSolidCheckedColor
    }

    fun setSolidDisabledColor(solidDisabledColor: Int?): ShapeDrawableBuilder {
        mSolidDisabledColor = solidDisabledColor
        return this
    }

    fun getSolidDisabledColor(): Int? {
        return mSolidDisabledColor
    }

    fun setSolidFocusedColor(solidFocusedColor: Int?): ShapeDrawableBuilder {
        mSolidFocusedColor = solidFocusedColor
        return this
    }

    fun getSolidFocusedColor(): Int? {
        return mSolidFocusedColor
    }

    fun setSolidSelectedColor(solidSelectedColor: Int?): ShapeDrawableBuilder {
        mSolidSelectedColor = solidSelectedColor
        return this
    }

    fun getSolidSelectedColor(): Int? {
        return mSolidSelectedColor
    }

    fun setRadius(radius: Float): ShapeDrawableBuilder {
        return setRadius(radius, radius, radius, radius)
    }

    fun setRadius(topLeftRadius: Float, topRightRadius: Float, bottomLeftRadius: Float, bottomRightRadius: Float): ShapeDrawableBuilder {
        mTopLeftRadius = topLeftRadius
        mTopRightRadius = topRightRadius
        mBottomLeftRadius = bottomLeftRadius
        mBottomRightRadius = bottomRightRadius
        return this
    }

    fun getTopLeftRadius(): Float {
        return mTopLeftRadius
    }

    fun getTopRightRadius(): Float {
        return mTopRightRadius
    }

    fun getBottomLeftRadius(): Float {
        return mBottomLeftRadius
    }

    fun getBottomRightRadius(): Float {
        return mBottomRightRadius
    }

    fun setGradientColor(gradientColor: IntArray?): ShapeDrawableBuilder {
        mGradientColor = gradientColor
        return this
    }

    fun getGradientColor(): IntArray? {
        return mGradientColor
    }

    fun isGradientColor(): Boolean {
        val gradientColor = mGradientColor
        return gradientColor != null && gradientColor.isNotEmpty()
    }

    fun clearGradientColor() {
        mGradientColor = null
    }

    fun setUseLevel(useLevel: Boolean): ShapeDrawableBuilder {
        mUseLevel = useLevel
        return this
    }

    fun isUseLevel(): Boolean {
        return mUseLevel
    }

    fun setAngle(angle: Int): ShapeDrawableBuilder {
        mAngle = angle
        return this
    }

    fun getAngle(): Int {
        return mAngle
    }

    fun setGradientType(gradientType: Int): ShapeDrawableBuilder {
        mGradientType = gradientType
        return this
    }

    fun getGradientType(): Int {
        return mGradientType
    }

    fun setCenterX(centerX: Float): ShapeDrawableBuilder {
        mCenterX = centerX
        return this
    }

    fun getCenterX(): Float {
        return mCenterX
    }

    fun setCenterY(centerY: Float): ShapeDrawableBuilder {
        mCenterY = centerY
        return this
    }

    fun getCenterY(): Float {
        return mCenterY
    }

    fun setGradientRadius(gradientRadius: Int): ShapeDrawableBuilder {
        mGradientRadius = gradientRadius
        return this
    }

    fun getGradientRadius(): Int {
        return mGradientRadius
    }

    fun setStrokeColor(strokeColor: Int): ShapeDrawableBuilder {
        mStrokeColor = strokeColor
        return this
    }

    fun getStrokeColor(): Int {
        return mStrokeColor
    }

    fun setStrokePressedColor(strokePressedColor: Int?): ShapeDrawableBuilder {
        mStrokePressedColor = strokePressedColor
        return this
    }

    fun getStrokePressedColor(): Int? {
        return mStrokePressedColor
    }

    fun setStrokeCheckedColor(strokeCheckedColor: Int?): ShapeDrawableBuilder {
        mStrokeCheckedColor = strokeCheckedColor
        return this
    }

    fun getStrokeCheckedColor(): Int? {
        return mStrokeCheckedColor
    }

    fun setStrokeDisabledColor(strokeDisabledColor: Int?): ShapeDrawableBuilder {
        mStrokeDisabledColor = strokeDisabledColor
        return this
    }

    fun getStrokeDisabledColor(): Int? {
        return mStrokeDisabledColor
    }

    fun setStrokeFocusedColor(strokeFocusedColor: Int?): ShapeDrawableBuilder {
        mStrokeFocusedColor = strokeFocusedColor
        return this
    }

    fun getStrokeFocusedColor(): Int? {
        return mStrokeFocusedColor
    }

    fun setStrokeSelectedColor(strokeSelectedColor: Int?): ShapeDrawableBuilder {
        mStrokeSelectedColor = strokeSelectedColor
        return this
    }

    fun getStrokeSelectedColor(): Int? {
        return mStrokeSelectedColor
    }

    fun setStrokeWidth(strokeWidth: Int): ShapeDrawableBuilder {
        mStrokeWidth = strokeWidth
        return this
    }

    fun getStrokeWidth(): Int {
        return mStrokeWidth
    }

    fun setDashWidth(dashWidth: Int): ShapeDrawableBuilder {
        mDashWidth = dashWidth
        return this
    }

    fun getDashWidth(): Int {
        return mDashWidth
    }

    fun setDashGap(dashGap: Int): ShapeDrawableBuilder {
        mDashGap = dashGap
        return this
    }

    fun getDashGap(): Int {
        return mDashGap
    }

    fun isDashLineEnable(): Boolean {
        return mDashGap > 0
    }

    fun setInnerRadius(innerRadius: Int): ShapeDrawableBuilder {
        mInnerRadius = innerRadius
        return this
    }

    fun getInnerRadius(): Int {
        return mInnerRadius
    }

    fun setInnerRadiusRatio(innerRadiusRatio: Float): ShapeDrawableBuilder {
        mInnerRadiusRatio = innerRadiusRatio
        return this
    }

    fun getInnerRadiusRatio(): Float {
        return mInnerRadiusRatio
    }

    fun setThickness(thickness: Int): ShapeDrawableBuilder {
        mThickness = thickness
        return this
    }

    fun getThickness(): Int {
        return mThickness
    }

    fun setThicknessRatio(thicknessRatio: Float): ShapeDrawableBuilder {
        mThicknessRatio = thicknessRatio
        return this
    }

    fun getThicknessRatio(): Float {
        return mThicknessRatio
    }

    fun isShadowEnable(): Boolean {
        return mShadowSize > 0
    }

    fun setShadowSize(shadowSize: Int): ShapeDrawableBuilder {
        mShadowSize = shadowSize
        return this
    }

    fun getShadowSize(): Int {
        return mShadowSize
    }

    fun setShadowColor(shadowColor: Int): ShapeDrawableBuilder {
        mShadowColor = shadowColor
        return this
    }

    fun getShadowColor(): Int {
        return mShadowColor
    }

    fun setShadowOffsetX(shadowOffsetX: Int): ShapeDrawableBuilder {
        mShadowOffsetX = shadowOffsetX
        return this
    }

    fun getShadowOffsetX(): Int {
        return mShadowOffsetX
    }

    fun setShadowOffsetY(shadowOffsetY: Int): ShapeDrawableBuilder {
        mShadowOffsetY = shadowOffsetY
        return this
    }

    fun getShadowOffsetY(): Int {
        return mShadowOffsetY
    }

    fun buildBackgroundDrawable(): Drawable? {
        if (!isGradientColor() && mSolidColor == 0 && mStrokeColor == 0) {
            return null
        }

        val normalDrawable = createShapeDrawable(mSolidColor, mStrokeColor)
        if (isGradientColor()) {
            normalDrawable.setGradientColors(mGradientColor)
        }

        if (mSolidPressedColor != null &&
            mStrokePressedColor != null &&
            mSolidCheckedColor != null &&
            mStrokeCheckedColor != null &&
            mSolidDisabledColor != null &&
            mStrokeDisabledColor != null &&
            mSolidFocusedColor != null &&
            mStrokeFocusedColor != null &&
            mSolidSelectedColor != null &&
            mStrokeSelectedColor != null
        ) {
            return normalDrawable
        }

        val stateListDrawable = StateListDrawable()

        if (!(mSolidPressedColor == null && mStrokePressedColor == null)) {
            val solidColor = mSolidPressedColor ?: mSolidColor
            val strokeColor = mStrokePressedColor ?: mStrokeColor
            stateListDrawable.addState(intArrayOf(16842919), createShapeDrawable(solidColor, strokeColor))
        }

        if (!(mSolidCheckedColor == null && mStrokeCheckedColor == null)) {
            val solidColor = mSolidCheckedColor ?: mSolidColor
            val strokeColor = mStrokeCheckedColor ?: mStrokeColor
            stateListDrawable.addState(intArrayOf(16842912), createShapeDrawable(solidColor, strokeColor))
        }

        if (!(mSolidDisabledColor == null && mStrokeDisabledColor == null)) {
            val solidColor = mSolidDisabledColor ?: mSolidColor
            val strokeColor = mStrokeDisabledColor ?: mStrokeColor
            stateListDrawable.addState(intArrayOf(-16842910), createShapeDrawable(solidColor, strokeColor))
        }

        if (!(mSolidFocusedColor == null && mStrokeFocusedColor == null)) {
            val solidColor = mSolidFocusedColor ?: mSolidColor
            val strokeColor = mStrokeFocusedColor ?: mStrokeColor
            stateListDrawable.addState(intArrayOf(16842908), createShapeDrawable(solidColor, strokeColor))
        }

        if (!(mSolidSelectedColor == null && mStrokeSelectedColor == null)) {
            val solidColor = mSolidSelectedColor ?: mSolidColor
            val strokeColor = mStrokeSelectedColor ?: mStrokeColor
            stateListDrawable.addState(intArrayOf(16842913), createShapeDrawable(solidColor, strokeColor))
        }

        stateListDrawable.addState(intArrayOf(), normalDrawable)
        return stateListDrawable
    }

    fun createShapeDrawable(solidColor: Int, strokeColor: Int): ShapeDrawable {
        val shapeDrawable = ShapeDrawable()
        shapeDrawable.setShape(mShape)
            .setSize(mShapeWidth, mShapeHeight)
            .setRadius(mTopLeftRadius, mTopRightRadius, mBottomLeftRadius, mBottomRightRadius)
            .setSolidColor(solidColor)
            .setUseLevel(isUseLevel())
            .setStroke(mStrokeWidth, strokeColor, mDashWidth.toFloat(), mDashGap.toFloat())
        shapeDrawable.setGradientAngle(mAngle)
            .setGradientType(mGradientType)
            .setGradientRadius(mGradientRadius.toFloat())
            .setGradientCenter(mCenterX, mCenterY)
        shapeDrawable.setInnerRadiusRatio(mInnerRadiusRatio)
            .setInnerRadius(mInnerRadius)
            .setThicknessRatio(mThicknessRatio)
            .setThickness(mThickness)
        shapeDrawable.setShadowSize(mShadowSize)
            .setShadowColor(mShadowColor)
            .setShadowOffsetX(mShadowOffsetX)
            .setShadowOffsetY(mShadowOffsetY)
        return shapeDrawable
    }

    fun intoBackground() {
        val drawable = buildBackgroundDrawable()
        if (drawable != null) {
            if (isDashLineEnable() || isShadowEnable()) {
                mView.setLayerType(1, null as Paint?)
            }
            mView.background = drawable
        }
    }
}
