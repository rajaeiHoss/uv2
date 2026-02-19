package com.hjq.shape.styleable;

import com.hjq.shape.R;
class ShapeViewStyleable : IShapeDrawableStyleable {

    override fun getShapeTypeStyleable(): Int = R.styleable.ShapeView_shape

    override fun getShapeWidthStyleable(): Int = R.styleable.ShapeView_shape_width

    override fun getShapeHeightStyleable(): Int = R.styleable.ShapeView_shape_height

    override fun getSolidColorStyleable(): Int = R.styleable.ShapeView_shape_solidColor

    override fun getSolidPressedColorStyleable(): Int = R.styleable.ShapeView_shape_solidPressedColor

    override fun getSolidDisabledColorStyleable(): Int = R.styleable.ShapeView_shape_solidDisabledColor

    override fun getSolidFocusedColorStyleable(): Int = R.styleable.ShapeView_shape_solidFocusedColor

    override fun getSolidSelectedColorStyleable(): Int = R.styleable.ShapeView_shape_solidSelectedColor

    override fun getRadiusStyleable(): Int = R.styleable.ShapeView_shape_radius

    override fun getTopLeftRadiusStyleable(): Int = R.styleable.ShapeView_shape_topLeftRadius

    override fun getTopRightRadiusStyleable(): Int = R.styleable.ShapeView_shape_topRightRadius

    override fun getBottomLeftRadiusStyleable(): Int = R.styleable.ShapeView_shape_bottomLeftRadius

    override fun getBottomRightRadiusStyleable(): Int = R.styleable.ShapeView_shape_bottomRightRadius

    override fun getStartColorStyleable(): Int = R.styleable.ShapeView_shape_startColor

    override fun getCenterColorStyleable(): Int = R.styleable.ShapeView_shape_centerColor

    override fun getEndColorStyleable(): Int = R.styleable.ShapeView_shape_endColor

    override fun getUseLevelStyleable(): Int = R.styleable.ShapeView_shape_useLevel

    override fun getAngleStyleable(): Int = R.styleable.ShapeView_shape_angle

    override fun getGradientTypeStyleable(): Int = R.styleable.ShapeView_shape_gradientType

    override fun getCenterXStyleable(): Int = R.styleable.ShapeView_shape_centerX

    override fun getCenterYStyleable(): Int = R.styleable.ShapeView_shape_centerY

    override fun getGradientRadiusStyleable(): Int = R.styleable.ShapeView_shape_gradientRadius

    override fun getStrokeColorStyleable(): Int = R.styleable.ShapeView_shape_strokeColor

    override fun getStrokePressedColorStyleable(): Int = R.styleable.ShapeView_shape_strokePressedColor

    override fun getStrokeDisabledColorStyleable(): Int = R.styleable.ShapeView_shape_strokeDisabledColor

    override fun getStrokeFocusedColorStyleable(): Int = R.styleable.ShapeView_shape_strokeFocusedColor

    override fun getStrokeSelectedColorStyleable(): Int = R.styleable.ShapeView_shape_strokeSelectedColor

    override fun getStrokeWidthStyleable(): Int = R.styleable.ShapeView_shape_strokeWidth

    override fun getDashWidthStyleable(): Int = R.styleable.ShapeView_shape_dashWidth

    override fun getDashGapStyleable(): Int = R.styleable.ShapeView_shape_dashGap

    override fun getInnerRadiusStyleable(): Int = R.styleable.ShapeView_shape_innerRadius

    override fun getInnerRadiusRatioStyleable(): Int = R.styleable.ShapeView_shape_innerRadiusRatio

    override fun getThicknessStyleable(): Int = R.styleable.ShapeView_shape_thickness

    override fun getThicknessRatioStyleable(): Int = R.styleable.ShapeView_shape_thicknessRatio

    override fun getShadowSizeStyleable(): Int = R.styleable.ShapeView_shape_shadowSize

    override fun getShadowColorStyleable(): Int = R.styleable.ShapeView_shape_shadowColor

    override fun getShadowOffsetXStyleable(): Int = R.styleable.ShapeView_shape_shadowOffsetX

    override fun getShadowOffsetYStyleable(): Int = R.styleable.ShapeView_shape_shadowOffsetY

}
