package com.hjq.shape.styleable

interface IShapeDrawableStyleable {
    fun getAngleStyleable(): Int

    fun getBottomLeftRadiusStyleable(): Int

    fun getBottomRightRadiusStyleable(): Int

    fun getCenterColorStyleable(): Int

    fun getCenterXStyleable(): Int

    fun getCenterYStyleable(): Int

    fun getDashGapStyleable(): Int

    fun getDashWidthStyleable(): Int

    fun getEndColorStyleable(): Int

    fun getGradientRadiusStyleable(): Int

    fun getGradientTypeStyleable(): Int

    fun getInnerRadiusRatioStyleable(): Int

    fun getInnerRadiusStyleable(): Int

    fun getRadiusStyleable(): Int

    fun getShadowColorStyleable(): Int

    fun getShadowOffsetXStyleable(): Int

    fun getShadowOffsetYStyleable(): Int

    fun getShadowSizeStyleable(): Int

    fun getShapeHeightStyleable(): Int

    fun getShapeTypeStyleable(): Int

    fun getShapeWidthStyleable(): Int

    fun getSolidCheckedColorStyleable(): Int = 0

    fun getSolidColorStyleable(): Int

    fun getSolidDisabledColorStyleable(): Int

    fun getSolidFocusedColorStyleable(): Int

    fun getSolidPressedColorStyleable(): Int

    fun getSolidSelectedColorStyleable(): Int

    fun getStartColorStyleable(): Int

    fun getStrokeCheckedColorStyleable(): Int = 0

    fun getStrokeColorStyleable(): Int

    fun getStrokeDisabledColorStyleable(): Int

    fun getStrokeFocusedColorStyleable(): Int

    fun getStrokePressedColorStyleable(): Int

    fun getStrokeSelectedColorStyleable(): Int

    fun getStrokeWidthStyleable(): Int

    fun getThicknessRatioStyleable(): Int

    fun getThicknessStyleable(): Int

    fun getTopLeftRadiusStyleable(): Int

    fun getTopRightRadiusStyleable(): Int

    fun getUseLevelStyleable(): Int

    object CC {
        @JvmStatic
        fun `$default$getSolidCheckedColorStyleable`(iShapeDrawableStyleable: IShapeDrawableStyleable): Int {
            return 0
        }

        @JvmStatic
        fun `$default$getStrokeCheckedColorStyleable`(iShapeDrawableStyleable: IShapeDrawableStyleable): Int {
            return 0
        }
    }
}
