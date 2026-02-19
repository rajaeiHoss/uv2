package com.hjq.shape.builder;

import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import com.google.android.gms.drive.DriveFile;
import com.hjq.shape.drawable.ShapeDrawable;
import com.hjq.shape.styleable.IShapeDrawableStyleable;

public final class ShapeDrawableBuilder {
    private int mAngle;
    private float mBottomLeftRadius;
    private float mBottomRightRadius;
    private float mCenterX;
    private float mCenterY;
    private int mDashGap;
    private int mDashWidth;
    private int[] mGradientColor;
    private int mGradientRadius;
    private int mGradientType;
    private int mInnerRadius;
    private float mInnerRadiusRatio;
    private int mShadowColor;
    private int mShadowOffsetX;
    private int mShadowOffsetY;
    private int mShadowSize;
    private int mShape;
    private int mShapeHeight;
    private int mShapeWidth;
    private Integer mSolidCheckedColor;
    private int mSolidColor;
    private Integer mSolidDisabledColor;
    private Integer mSolidFocusedColor;
    private Integer mSolidPressedColor;
    private Integer mSolidSelectedColor;
    private Integer mStrokeCheckedColor;
    private int mStrokeColor;
    private Integer mStrokeDisabledColor;
    private Integer mStrokeFocusedColor;
    private Integer mStrokePressedColor;
    private Integer mStrokeSelectedColor;
    private int mStrokeWidth;
    private int mThickness;
    private float mThicknessRatio;
    private float mTopLeftRadius;
    private float mTopRightRadius;
    private boolean mUseLevel;
    private final View mView;

    public ShapeDrawableBuilder(View view, TypedArray typedArray, IShapeDrawableStyleable iShapeDrawableStyleable) {
        this.mView = view;
        this.mShape = typedArray.getInt(iShapeDrawableStyleable.getShapeTypeStyleable(), 0);
        this.mShapeWidth = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShapeWidthStyleable(), -1);
        this.mShapeHeight = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShapeHeightStyleable(), -1);
        this.mSolidColor = typedArray.getColor(iShapeDrawableStyleable.getSolidColorStyleable(), 0);
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidPressedColorStyleable())) {
            this.mSolidPressedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidPressedColorStyleable(), 0));
        }
        if (iShapeDrawableStyleable.getSolidCheckedColorStyleable() > 0 && typedArray.hasValue(iShapeDrawableStyleable.getSolidCheckedColorStyleable())) {
            this.mSolidCheckedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidCheckedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidDisabledColorStyleable())) {
            this.mSolidDisabledColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidDisabledColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidFocusedColorStyleable())) {
            this.mSolidFocusedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidFocusedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getSolidSelectedColorStyleable())) {
            this.mSolidSelectedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getSolidSelectedColorStyleable(), 0));
        }
        int dimensionPixelSize = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getRadiusStyleable(), 0);
        this.mTopLeftRadius = (float) typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getTopLeftRadiusStyleable(), dimensionPixelSize);
        this.mTopRightRadius = (float) typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getTopRightRadiusStyleable(), dimensionPixelSize);
        this.mBottomLeftRadius = (float) typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getBottomLeftRadiusStyleable(), dimensionPixelSize);
        this.mBottomRightRadius = (float) typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getBottomRightRadiusStyleable(), dimensionPixelSize);
        if (typedArray.hasValue(iShapeDrawableStyleable.getStartColorStyleable()) && typedArray.hasValue(iShapeDrawableStyleable.getEndColorStyleable())) {
            if (typedArray.hasValue(iShapeDrawableStyleable.getCenterColorStyleable())) {
                this.mGradientColor = new int[]{typedArray.getColor(iShapeDrawableStyleable.getStartColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getCenterColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getEndColorStyleable(), 0)};
            } else {
                this.mGradientColor = new int[]{typedArray.getColor(iShapeDrawableStyleable.getStartColorStyleable(), 0), typedArray.getColor(iShapeDrawableStyleable.getEndColorStyleable(), 0)};
            }
        }
        this.mUseLevel = typedArray.getBoolean(iShapeDrawableStyleable.getUseLevelStyleable(), false);
        this.mAngle = (int) typedArray.getFloat(iShapeDrawableStyleable.getAngleStyleable(), 0.0f);
        this.mGradientType = typedArray.getInt(iShapeDrawableStyleable.getGradientTypeStyleable(), 0);
        this.mCenterX = typedArray.getFloat(iShapeDrawableStyleable.getCenterXStyleable(), 0.5f);
        this.mCenterY = typedArray.getFloat(iShapeDrawableStyleable.getCenterYStyleable(), 0.5f);
        this.mGradientRadius = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getGradientRadiusStyleable(), dimensionPixelSize);
        this.mStrokeColor = typedArray.getColor(iShapeDrawableStyleable.getStrokeColorStyleable(), 0);
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokePressedColorStyleable())) {
            this.mStrokePressedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokePressedColorStyleable(), 0));
        }
        if (iShapeDrawableStyleable.getStrokeCheckedColorStyleable() > 0 && typedArray.hasValue(iShapeDrawableStyleable.getStrokeCheckedColorStyleable())) {
            this.mStrokeCheckedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeCheckedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeDisabledColorStyleable())) {
            this.mStrokeDisabledColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeDisabledColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeFocusedColorStyleable())) {
            this.mStrokeFocusedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeFocusedColorStyleable(), 0));
        }
        if (typedArray.hasValue(iShapeDrawableStyleable.getStrokeSelectedColorStyleable())) {
            this.mStrokeSelectedColor = Integer.valueOf(typedArray.getColor(iShapeDrawableStyleable.getStrokeSelectedColorStyleable(), 0));
        }
        this.mStrokeWidth = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getStrokeWidthStyleable(), 0);
        this.mDashWidth = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getDashWidthStyleable(), 0);
        this.mDashGap = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getDashGapStyleable(), 0);
        this.mInnerRadius = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getInnerRadiusStyleable(), -1);
        this.mInnerRadiusRatio = typedArray.getFloat(iShapeDrawableStyleable.getInnerRadiusRatioStyleable(), 3.0f);
        this.mThickness = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getThicknessStyleable(), -1);
        this.mThicknessRatio = typedArray.getFloat(iShapeDrawableStyleable.getThicknessRatioStyleable(), 9.0f);
        this.mShadowSize = typedArray.getDimensionPixelSize(iShapeDrawableStyleable.getShadowSizeStyleable(), 0);
        this.mShadowColor = typedArray.getColor(iShapeDrawableStyleable.getShadowColorStyleable(), DriveFile.MODE_READ_ONLY);
        this.mShadowOffsetX = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getShadowOffsetXStyleable(), 0);
        this.mShadowOffsetY = typedArray.getDimensionPixelOffset(iShapeDrawableStyleable.getShadowOffsetYStyleable(), 0);
    }

    public ShapeDrawableBuilder setShape(int i) {
        this.mShape = i;
        return this;
    }

    public int getShape() {
        return this.mShape;
    }

    public ShapeDrawableBuilder setShapeWidth(int i) {
        this.mShapeWidth = i;
        return this;
    }

    public int getShapeWidth() {
        return this.mShapeWidth;
    }

    public ShapeDrawableBuilder setShapeHeight(int i) {
        this.mShapeHeight = i;
        return this;
    }

    public int getShapeHeight() {
        return this.mShapeHeight;
    }

    public ShapeDrawableBuilder setSolidColor(int i) {
        this.mSolidColor = i;
        clearGradientColor();
        return this;
    }

    public int getSolidColor() {
        return this.mSolidColor;
    }

    public ShapeDrawableBuilder setSolidPressedColor(Integer num) {
        this.mSolidPressedColor = num;
        return this;
    }

    public Integer getSolidPressedColor() {
        return this.mSolidPressedColor;
    }

    public ShapeDrawableBuilder setSolidCheckedColor(Integer num) {
        this.mSolidCheckedColor = num;
        return this;
    }

    public Integer getSolidCheckedColor() {
        return this.mSolidCheckedColor;
    }

    public ShapeDrawableBuilder setSolidDisabledColor(Integer num) {
        this.mSolidDisabledColor = num;
        return this;
    }

    public Integer getSolidDisabledColor() {
        return this.mSolidDisabledColor;
    }

    public ShapeDrawableBuilder setSolidFocusedColor(Integer num) {
        this.mSolidFocusedColor = num;
        return this;
    }

    public Integer getSolidFocusedColor() {
        return this.mSolidFocusedColor;
    }

    public ShapeDrawableBuilder setSolidSelectedColor(Integer num) {
        this.mSolidSelectedColor = num;
        return this;
    }

    public Integer getSolidSelectedColor() {
        return this.mSolidSelectedColor;
    }

    public ShapeDrawableBuilder setRadius(float f) {
        return setRadius(f, f, f, f);
    }

    public ShapeDrawableBuilder setRadius(float f, float f2, float f3, float f4) {
        this.mTopLeftRadius = f;
        this.mTopRightRadius = f2;
        this.mBottomLeftRadius = f3;
        this.mBottomRightRadius = f4;
        return this;
    }

    public float getTopLeftRadius() {
        return this.mTopLeftRadius;
    }

    public float getTopRightRadius() {
        return this.mTopRightRadius;
    }

    public float getBottomLeftRadius() {
        return this.mBottomLeftRadius;
    }

    public float getBottomRightRadius() {
        return this.mBottomRightRadius;
    }

    public ShapeDrawableBuilder setGradientColor(int[] iArr) {
        this.mGradientColor = iArr;
        return this;
    }

    public int[] getGradientColor() {
        return this.mGradientColor;
    }

    public boolean isGradientColor() {
        int[] iArr = this.mGradientColor;
        return iArr != null && iArr.length > 0;
    }

    public void clearGradientColor() {
        this.mGradientColor = null;
    }

    public ShapeDrawableBuilder setUseLevel(boolean z) {
        this.mUseLevel = z;
        return this;
    }

    public boolean isUseLevel() {
        return this.mUseLevel;
    }

    public ShapeDrawableBuilder setAngle(int i) {
        this.mAngle = i;
        return this;
    }

    public int getAngle() {
        return this.mAngle;
    }

    public ShapeDrawableBuilder setGradientType(int i) {
        this.mGradientType = i;
        return this;
    }

    public int getGradientType() {
        return this.mGradientType;
    }

    public ShapeDrawableBuilder setCenterX(float f) {
        this.mCenterX = f;
        return this;
    }

    public float getCenterX() {
        return this.mCenterX;
    }

    public ShapeDrawableBuilder setCenterY(float f) {
        this.mCenterY = f;
        return this;
    }

    public float getCenterY() {
        return this.mCenterY;
    }

    public ShapeDrawableBuilder setGradientRadius(int i) {
        this.mGradientRadius = i;
        return this;
    }

    public int getGradientRadius() {
        return this.mGradientRadius;
    }

    public ShapeDrawableBuilder setStrokeColor(int i) {
        this.mStrokeColor = i;
        return this;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public ShapeDrawableBuilder setStrokePressedColor(Integer num) {
        this.mStrokePressedColor = num;
        return this;
    }

    public Integer getStrokePressedColor() {
        return this.mStrokePressedColor;
    }

    public ShapeDrawableBuilder setStrokeCheckedColor(Integer num) {
        this.mStrokeCheckedColor = num;
        return this;
    }

    public Integer getStrokeCheckedColor() {
        return this.mStrokeCheckedColor;
    }

    public ShapeDrawableBuilder setStrokeDisabledColor(Integer num) {
        this.mStrokeDisabledColor = num;
        return this;
    }

    public Integer getStrokeDisabledColor() {
        return this.mStrokeDisabledColor;
    }

    public ShapeDrawableBuilder setStrokeFocusedColor(Integer num) {
        this.mStrokeFocusedColor = num;
        return this;
    }

    public Integer getStrokeFocusedColor() {
        return this.mStrokeFocusedColor;
    }

    public ShapeDrawableBuilder setStrokeSelectedColor(Integer num) {
        this.mStrokeSelectedColor = num;
        return this;
    }

    public Integer getStrokeSelectedColor() {
        return this.mStrokeSelectedColor;
    }

    public ShapeDrawableBuilder setStrokeWidth(int i) {
        this.mStrokeWidth = i;
        return this;
    }

    public int getStrokeWidth() {
        return this.mStrokeWidth;
    }

    public ShapeDrawableBuilder setDashWidth(int i) {
        this.mDashWidth = i;
        return this;
    }

    public int getDashWidth() {
        return this.mDashWidth;
    }

    public ShapeDrawableBuilder setDashGap(int i) {
        this.mDashGap = i;
        return this;
    }

    public int getDashGap() {
        return this.mDashGap;
    }

    public boolean isDashLineEnable() {
        return this.mDashGap > 0;
    }

    public ShapeDrawableBuilder setInnerRadius(int i) {
        this.mInnerRadius = i;
        return this;
    }

    public int getInnerRadius() {
        return this.mInnerRadius;
    }

    public ShapeDrawableBuilder setInnerRadiusRatio(float f) {
        this.mInnerRadiusRatio = f;
        return this;
    }

    public float getInnerRadiusRatio() {
        return this.mInnerRadiusRatio;
    }

    public ShapeDrawableBuilder setThickness(int i) {
        this.mThickness = i;
        return this;
    }

    public int getThickness() {
        return this.mThickness;
    }

    public ShapeDrawableBuilder setThicknessRatio(float f) {
        this.mThicknessRatio = f;
        return this;
    }

    public float getThicknessRatio() {
        return this.mThicknessRatio;
    }

    public boolean isShadowEnable() {
        return this.mShadowSize > 0;
    }

    public ShapeDrawableBuilder setShadowSize(int i) {
        this.mShadowSize = i;
        return this;
    }

    public int getShadowSize() {
        return this.mShadowSize;
    }

    public ShapeDrawableBuilder setShadowColor(int i) {
        this.mShadowColor = i;
        return this;
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }

    public ShapeDrawableBuilder setShadowOffsetX(int i) {
        this.mShadowOffsetX = i;
        return this;
    }

    public int getShadowOffsetX() {
        return this.mShadowOffsetX;
    }

    public ShapeDrawableBuilder setShadowOffsetY(int i) {
        this.mShadowOffsetY = i;
        return this;
    }

    public int getShadowOffsetY() {
        return this.mShadowOffsetY;
    }

    public Drawable buildBackgroundDrawable() {
        if (!isGradientColor() && this.mSolidColor == 0 && this.mStrokeColor == 0) {
            return null;
        }
        ShapeDrawable createShapeDrawable = createShapeDrawable(this.mSolidColor, this.mStrokeColor);
        if (isGradientColor()) {
            createShapeDrawable.setGradientColors(this.mGradientColor);
        }
        if (this.mSolidPressedColor != null && this.mStrokePressedColor != null && this.mSolidCheckedColor != null && this.mStrokeCheckedColor != null && this.mSolidDisabledColor != null && this.mStrokeDisabledColor != null && this.mSolidFocusedColor != null && this.mStrokeFocusedColor != null && this.mSolidSelectedColor != null && this.mStrokeSelectedColor != null) {
            return createShapeDrawable;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        Integer num = this.mSolidPressedColor;
        if (!(num == null && this.mStrokePressedColor == null)) {
            int[] iArr = {16842919};
            int intValue = num != null ? num.intValue() : this.mSolidColor;
            Integer num2 = this.mStrokePressedColor;
            stateListDrawable.addState(iArr, createShapeDrawable(intValue, num2 != null ? num2.intValue() : this.mStrokeColor));
        }
        Integer num3 = this.mSolidCheckedColor;
        if (!(num3 == null && this.mStrokeCheckedColor == null)) {
            int[] iArr2 = {16842912};
            int intValue2 = num3 != null ? num3.intValue() : this.mSolidColor;
            Integer num4 = this.mStrokeCheckedColor;
            stateListDrawable.addState(iArr2, createShapeDrawable(intValue2, num4 != null ? num4.intValue() : this.mStrokeColor));
        }
        Integer num5 = this.mSolidDisabledColor;
        if (!(num5 == null && this.mStrokeDisabledColor == null)) {
            int[] iArr3 = {-16842910};
            int intValue3 = num5 != null ? num5.intValue() : this.mSolidColor;
            Integer num6 = this.mStrokeDisabledColor;
            stateListDrawable.addState(iArr3, createShapeDrawable(intValue3, num6 != null ? num6.intValue() : this.mStrokeColor));
        }
        Integer num7 = this.mSolidFocusedColor;
        if (!(num7 == null && this.mStrokeFocusedColor == null)) {
            int[] iArr4 = {16842908};
            int intValue4 = num7 != null ? num7.intValue() : this.mSolidColor;
            Integer num8 = this.mStrokeFocusedColor;
            stateListDrawable.addState(iArr4, createShapeDrawable(intValue4, num8 != null ? num8.intValue() : this.mStrokeColor));
        }
        Integer num9 = this.mSolidSelectedColor;
        if (!(num9 == null && this.mStrokeSelectedColor == null)) {
            int[] iArr5 = {16842913};
            int intValue5 = num9 != null ? num9.intValue() : this.mSolidColor;
            Integer num10 = this.mStrokeSelectedColor;
            stateListDrawable.addState(iArr5, createShapeDrawable(intValue5, num10 != null ? num10.intValue() : this.mStrokeColor));
        }
        stateListDrawable.addState(new int[0], createShapeDrawable);
        return stateListDrawable;
    }

    public ShapeDrawable createShapeDrawable(int i, int i2) {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(this.mShape).setSize(this.mShapeWidth, this.mShapeHeight).setRadius(this.mTopLeftRadius, this.mTopRightRadius, this.mBottomLeftRadius, this.mBottomRightRadius).setSolidColor(i).setUseLevel(isUseLevel()).setStroke(this.mStrokeWidth, i2, (float) this.mDashWidth, (float) this.mDashGap);
        shapeDrawable.setGradientAngle(this.mAngle).setGradientType(this.mGradientType).setGradientRadius((float) this.mGradientRadius).setGradientCenter(this.mCenterX, this.mCenterY);
        shapeDrawable.setInnerRadiusRatio(this.mInnerRadiusRatio).setInnerRadius(this.mInnerRadius).setThicknessRatio(this.mThicknessRatio).setThickness(this.mThickness);
        shapeDrawable.setShadowSize(this.mShadowSize).setShadowColor(this.mShadowColor).setShadowOffsetX(this.mShadowOffsetX).setShadowOffsetY(this.mShadowOffsetY);
        return shapeDrawable;
    }

    public void intoBackground() {
        Drawable buildBackgroundDrawable = buildBackgroundDrawable();
        if (buildBackgroundDrawable != null) {
            if (isDashLineEnable() || isShadowEnable()) {
                this.mView.setLayerType(1, (Paint) null);
            }
            this.mView.setBackground(buildBackgroundDrawable);
        }
    }
}
