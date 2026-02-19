package com.hjq.shape.drawable;

import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class ShapeState extends Drawable.ConstantState {
    public float mCenterX = 0.5f;
    public float mCenterY = 0.5f;
    public int mChangingConfigurations;
    public int[] mGradientColors;
    public ShapeGradientOrientation mGradientOrientation = ShapeGradientOrientation.TOP_BOTTOM;
    public float mGradientRadius = 0.5f;
    public int mGradientType = 0;
    public boolean mHasSolidColor;
    public int mHeight = -1;
    public int mInnerRadius;
    public float mInnerRadiusRatio;
    public boolean mOpaque;
    public Rect mPadding;
    public float[] mPositions;
    public float mRadius;
    public float[] mRadiusArray;
    public int mShadowColor;
    public int mShadowOffsetX;
    public int mShadowOffsetY;
    public int mShadowSize;
    public int mShapeType = 0;
    public int mSolidColor;
    public int mStrokeColor;
    public float mStrokeDashGap;
    public float mStrokeDashWidth;
    public int mStrokeWidth = -1;
    public int[] mTempColors;
    public float[] mTempPositions;
    public int mThickness;
    public float mThicknessRatio;
    public boolean mUseLevel;
    public boolean mUseLevelForShape;
    public int mWidth = -1;

    private static boolean isOpaque(int i) {
        return ((i >> 24) & 255) == 255;
    }

    public ShapeState() {
    }

    public ShapeState(ShapeState shapeState) {
        this.mChangingConfigurations = shapeState.mChangingConfigurations;
        this.mShapeType = shapeState.mShapeType;
        this.mGradientType = shapeState.mGradientType;
        this.mGradientOrientation = shapeState.mGradientOrientation;
        int[] iArr = shapeState.mGradientColors;
        if (iArr != null) {
            this.mGradientColors = (int[]) iArr.clone();
        }
        float[] fArr = shapeState.mPositions;
        if (fArr != null) {
            this.mPositions = (float[]) fArr.clone();
        }
        this.mHasSolidColor = shapeState.mHasSolidColor;
        this.mSolidColor = shapeState.mSolidColor;
        this.mStrokeWidth = shapeState.mStrokeWidth;
        this.mStrokeColor = shapeState.mStrokeColor;
        this.mStrokeDashWidth = shapeState.mStrokeDashWidth;
        this.mStrokeDashGap = shapeState.mStrokeDashGap;
        this.mRadius = shapeState.mRadius;
        float[] fArr2 = shapeState.mRadiusArray;
        if (fArr2 != null) {
            this.mRadiusArray = (float[]) fArr2.clone();
        }
        if (shapeState.mPadding != null) {
            this.mPadding = new Rect(shapeState.mPadding);
        }
        this.mWidth = shapeState.mWidth;
        this.mHeight = shapeState.mHeight;
        this.mInnerRadiusRatio = shapeState.mInnerRadiusRatio;
        this.mThicknessRatio = shapeState.mThicknessRatio;
        this.mInnerRadius = shapeState.mInnerRadius;
        this.mThickness = shapeState.mThickness;
        this.mCenterX = shapeState.mCenterX;
        this.mCenterY = shapeState.mCenterY;
        this.mGradientRadius = shapeState.mGradientRadius;
        this.mUseLevel = shapeState.mUseLevel;
        this.mUseLevelForShape = shapeState.mUseLevelForShape;
        this.mOpaque = shapeState.mOpaque;
        this.mShadowSize = shapeState.mShadowSize;
        this.mShadowColor = shapeState.mShadowColor;
        this.mShadowOffsetX = shapeState.mShadowOffsetX;
        this.mShadowOffsetY = shapeState.mShadowOffsetY;
    }

    public Drawable newDrawable() {
        return new ShapeDrawable(this);
    }

    public Drawable newDrawable(Resources resources) {
        return new ShapeDrawable(this);
    }

    public int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }

    public void setShape(int i) {
        this.mShapeType = i;
        computeOpacity();
    }

    public void setGradientType(int i) {
        this.mGradientType = i;
    }

    public void setGradientCenter(float f, float f2) {
        this.mCenterX = f;
        this.mCenterY = f2;
    }

    public void setGradientColor(int[] iArr) {
        this.mHasSolidColor = false;
        this.mGradientColors = iArr;
        computeOpacity();
    }

    public void setSolidColor(int i) {
        this.mHasSolidColor = true;
        this.mSolidColor = i;
        this.mGradientColors = null;
        computeOpacity();
    }

    private void computeOpacity() {
        if (this.mShapeType != 0) {
            this.mOpaque = false;
        } else if (this.mRadius > 0.0f || this.mRadiusArray != null) {
            this.mOpaque = false;
        } else if (this.mStrokeWidth > 0 && !isOpaque(this.mStrokeColor)) {
            this.mOpaque = false;
        } else if (this.mHasSolidColor) {
            this.mOpaque = isOpaque(this.mSolidColor);
        } else {
            int[] iArr = this.mGradientColors;
            if (iArr != null) {
                for (int isOpaque : iArr) {
                    if (!isOpaque(isOpaque)) {
                        this.mOpaque = false;
                        return;
                    }
                }
            }
            this.mOpaque = true;
        }
    }

    public void setStroke(int i, int i2) {
        this.mStrokeWidth = i;
        this.mStrokeColor = i2;
        computeOpacity();
    }

    public void setStroke(int i, int i2, float f, float f2) {
        this.mStrokeWidth = i;
        this.mStrokeColor = i2;
        this.mStrokeDashWidth = f;
        this.mStrokeDashGap = f2;
        computeOpacity();
    }

    public void setCornerRadius(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.mRadius = f;
        this.mRadiusArray = null;
    }

    public void setCornerRadii(float[] fArr) {
        this.mRadiusArray = fArr;
        if (fArr == null) {
            this.mRadius = 0.0f;
        }
    }

    public void setSize(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public void setGradientRadius(float f) {
        this.mGradientRadius = f;
    }

    public void setShadowColor(int i) {
        this.mShadowColor = i;
    }

    public void setShadowSize(int i) {
        this.mShadowSize = i;
    }

    public void setShadowOffsetX(int i) {
        this.mShadowOffsetX = i;
    }

    public void setShadowOffsetY(int i) {
        this.mShadowOffsetY = i;
    }
}
