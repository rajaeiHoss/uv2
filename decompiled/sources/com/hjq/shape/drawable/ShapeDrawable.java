package com.hjq.shape.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import androidx.core.view.ViewCompat;

public class ShapeDrawable extends Drawable {
    private int mAlpha;
    private ColorFilter mColorFilter;
    private boolean mDither;
    private final Paint mFillPaint;
    private Paint mLayerPaint;
    private boolean mMutated;
    private Rect mPadding;
    private final Path mPath;
    private boolean mPathIsDirty;
    private final RectF mRect;
    private boolean mRectIsDirty;
    private Path mRingPath;
    private ShapeState mShapeState;
    private Paint mStrokePaint;

    public ShapeDrawable() {
        this(new ShapeState());
    }

    public ShapeDrawable(ShapeState shapeState) {
        this.mFillPaint = new Paint(1);
        this.mAlpha = 255;
        this.mPath = new Path();
        this.mRect = new RectF();
        this.mPathIsDirty = true;
        this.mShapeState = shapeState;
        initializeWithState(shapeState);
        this.mRectIsDirty = true;
        this.mMutated = false;
    }

    public ShapeState getShapeState() {
        return this.mShapeState;
    }

    public boolean getPadding(Rect rect) {
        Rect rect2 = this.mPadding;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    public ShapeDrawable setPadding(Rect rect) {
        this.mPadding = rect;
        return this;
    }

    public ShapeDrawable setShape(int i) {
        this.mRingPath = null;
        this.mPathIsDirty = true;
        this.mShapeState.setShape(i);
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSize(int i, int i2) {
        this.mShapeState.setSize(i, i2);
        this.mPathIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRadius(float f) {
        this.mShapeState.setCornerRadius(f);
        this.mPathIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setRadius(float f, float f2, float f3, float f4) {
        if (f == f2 && f == f3 && f == f4) {
            return setRadius(f);
        }
        this.mShapeState.setCornerRadii(new float[]{f, f, f2, f2, f4, f4, f3, f3});
        this.mPathIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setSolidColor(int i) {
        this.mShapeState.setSolidColor(i);
        this.mFillPaint.setColor(i);
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setStrokeColor(int i) {
        setStroke(i, this.mShapeState.mStrokeWidth, this.mShapeState.mStrokeDashWidth, this.mShapeState.mStrokeDashGap);
        return this;
    }

    public ShapeDrawable setStrokeWidth(int i) {
        setStroke(this.mShapeState.mStrokeColor, i, this.mShapeState.mStrokeDashWidth, this.mShapeState.mStrokeDashGap);
        return this;
    }

    public ShapeDrawable setDashWidth(float f) {
        setStroke(this.mShapeState.mStrokeColor, this.mShapeState.mStrokeWidth, f, this.mShapeState.mStrokeDashGap);
        return this;
    }

    public ShapeDrawable setDashGap(float f) {
        setStroke(this.mShapeState.mStrokeColor, this.mShapeState.mStrokeWidth, this.mShapeState.mStrokeDashWidth, f);
        return this;
    }

    public ShapeDrawable setStroke(int i, int i2, float f, float f2) {
        DashPathEffect dashPathEffect;
        this.mShapeState.setStroke(i, i2, f, f2);
        if (this.mStrokePaint == null) {
            Paint paint = new Paint(1);
            this.mStrokePaint = paint;
            paint.setStyle(Paint.Style.STROKE);
        }
        this.mStrokePaint.setStrokeWidth((float) i);
        this.mStrokePaint.setColor(i2);
        Paint paint2 = this.mStrokePaint;
        if (f > 0.0f) {
            dashPathEffect = new DashPathEffect(new float[]{f, f2}, 0.0f);
        } else {
            dashPathEffect = null;
        }
        paint2.setPathEffect(dashPathEffect);
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setGradientColors(int[] iArr) {
        this.mShapeState.setGradientColor(iArr);
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setGradientType(int i) {
        this.mShapeState.setGradientType(i);
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setGradientCenter(float f, float f2) {
        this.mShapeState.setGradientCenter(f, f2);
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setGradientRadius(float f) {
        this.mShapeState.setGradientRadius(f);
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setUseLevel(boolean z) {
        this.mShapeState.mUseLevel = z;
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setGradientAngle(int i) {
        int i2 = i % 360;
        if (i2 % 45 == 0) {
            if (i2 == 0) {
                setGradientOrientation(ShapeGradientOrientation.LEFT_RIGHT);
            } else if (i2 == 45) {
                setGradientOrientation(ShapeGradientOrientation.BL_TR);
            } else if (i2 == 90) {
                setGradientOrientation(ShapeGradientOrientation.BOTTOM_TOP);
            } else if (i2 == 135) {
                setGradientOrientation(ShapeGradientOrientation.BR_TL);
            } else if (i2 == 180) {
                setGradientOrientation(ShapeGradientOrientation.RIGHT_LEFT);
            } else if (i2 == 225) {
                setGradientOrientation(ShapeGradientOrientation.TR_BL);
            } else if (i2 == 270) {
                setGradientOrientation(ShapeGradientOrientation.TOP_BOTTOM);
            } else if (i2 == 315) {
                setGradientOrientation(ShapeGradientOrientation.TL_BR);
            }
        }
        return this;
    }

    public ShapeDrawable setGradientOrientation(ShapeGradientOrientation shapeGradientOrientation) {
        this.mShapeState.mGradientOrientation = shapeGradientOrientation;
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setInnerRadius(int i) {
        this.mShapeState.mInnerRadius = i;
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setInnerRadiusRatio(float f) {
        this.mShapeState.mInnerRadiusRatio = f;
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setThickness(int i) {
        this.mShapeState.mThickness = i;
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setThicknessRatio(float f) {
        this.mShapeState.mThicknessRatio = f;
        this.mRectIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowColor(int i) {
        this.mShapeState.setShadowColor(i);
        this.mPathIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowSize(int i) {
        this.mShapeState.setShadowSize(i);
        this.mPathIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowOffsetX(int i) {
        this.mShapeState.setShadowOffsetX(i);
        this.mPathIsDirty = true;
        invalidateSelf();
        return this;
    }

    public ShapeDrawable setShadowOffsetY(int i) {
        this.mShapeState.setShadowOffsetY(i);
        this.mPathIsDirty = true;
        invalidateSelf();
        return this;
    }

    public void intoBackground(View view) {
        if (this.mShapeState.mStrokeDashGap > 0.0f || this.mShapeState.mShadowSize > 0) {
            view.setLayerType(1, (Paint) null);
        }
        view.setBackground(this);
    }

    public void draw(Canvas canvas) {
        int i;
        Paint paint;
        Canvas canvas2 = canvas;
        if (ensureValidRect()) {
            int alpha = this.mFillPaint.getAlpha();
            Paint paint2 = this.mStrokePaint;
            int alpha2 = paint2 != null ? paint2.getAlpha() : 0;
            int modulateAlpha = modulateAlpha(alpha);
            int modulateAlpha2 = modulateAlpha(alpha2);
            boolean z = modulateAlpha2 > 0 && (paint = this.mStrokePaint) != null && paint.getStrokeWidth() > 0.0f;
            boolean z2 = modulateAlpha > 0;
            ShapeState shapeState = this.mShapeState;
            boolean z3 = z && z2 && shapeState.mShapeType != 2 && modulateAlpha2 < 255 && (this.mAlpha < 255 || this.mColorFilter != null);
            if (this.mShapeState.mShadowSize > 0) {
                if (z) {
                    this.mStrokePaint.setShadowLayer((float) this.mShapeState.mShadowSize, (float) this.mShapeState.mShadowOffsetX, (float) this.mShapeState.mShadowOffsetY, this.mShapeState.mShadowColor);
                } else {
                    this.mFillPaint.setShadowLayer((float) this.mShapeState.mShadowSize, (float) this.mShapeState.mShadowOffsetX, (float) this.mShapeState.mShadowOffsetY, this.mShapeState.mShadowColor);
                }
            } else if (z) {
                this.mStrokePaint.clearShadowLayer();
            } else {
                this.mFillPaint.clearShadowLayer();
            }
            if (z3) {
                if (this.mLayerPaint == null) {
                    this.mLayerPaint = new Paint();
                }
                this.mLayerPaint.setDither(this.mDither);
                this.mLayerPaint.setAlpha(this.mAlpha);
                this.mLayerPaint.setColorFilter(this.mColorFilter);
                float strokeWidth = this.mStrokePaint.getStrokeWidth();
                if (Build.VERSION.SDK_INT >= 21) {
                    canvas.saveLayer(this.mRect.left - strokeWidth, this.mRect.top - strokeWidth, this.mRect.right + strokeWidth, this.mRect.bottom + strokeWidth, this.mLayerPaint);
                    i = 2;
                } else {
                    i = 2;
                    canvas.saveLayer(this.mRect.left - strokeWidth, this.mRect.top - strokeWidth, this.mRect.right + strokeWidth, this.mRect.bottom + strokeWidth, this.mLayerPaint, 4);
                }
                this.mFillPaint.setColorFilter((ColorFilter) null);
                this.mStrokePaint.setColorFilter((ColorFilter) null);
            } else {
                i = 2;
                this.mFillPaint.setAlpha(modulateAlpha);
                this.mFillPaint.setDither(this.mDither);
                this.mFillPaint.setColorFilter(this.mColorFilter);
                if (this.mColorFilter != null && !this.mShapeState.mHasSolidColor) {
                    this.mFillPaint.setColor(this.mAlpha << 24);
                }
                if (z) {
                    this.mStrokePaint.setAlpha(modulateAlpha2);
                    this.mStrokePaint.setDither(this.mDither);
                    this.mStrokePaint.setColorFilter(this.mColorFilter);
                }
            }
            int i2 = shapeState.mShapeType;
            if (i2 != 0) {
                if (i2 == 1) {
                    canvas2.drawOval(this.mRect, this.mFillPaint);
                    if (z) {
                        canvas2.drawOval(this.mRect, this.mStrokePaint);
                    }
                } else if (i2 == i) {
                    RectF rectF = this.mRect;
                    float centerY = rectF.centerY();
                    canvas.drawLine(rectF.left, centerY, rectF.right, centerY, this.mStrokePaint);
                } else if (i2 == 3) {
                    Path buildRing = buildRing(shapeState);
                    canvas2.drawPath(buildRing, this.mFillPaint);
                    if (z) {
                        canvas2.drawPath(buildRing, this.mStrokePaint);
                    }
                }
            } else if (shapeState.mRadiusArray != null) {
                if (this.mPathIsDirty || this.mRectIsDirty) {
                    this.mPath.reset();
                    this.mPath.addRoundRect(this.mRect, shapeState.mRadiusArray, Path.Direction.CW);
                    this.mRectIsDirty = false;
                    this.mPathIsDirty = false;
                }
                canvas2.drawPath(this.mPath, this.mFillPaint);
                if (z) {
                    canvas2.drawPath(this.mPath, this.mStrokePaint);
                }
            } else if (shapeState.mRadius > 0.0f) {
                float f = shapeState.mRadius;
                float min = Math.min(this.mRect.width(), this.mRect.height()) * 0.5f;
                if (f > min) {
                    f = min;
                }
                canvas2.drawRoundRect(this.mRect, f, f, this.mFillPaint);
                if (z) {
                    canvas2.drawRoundRect(this.mRect, f, f, this.mStrokePaint);
                }
            } else {
                if (!(this.mFillPaint.getColor() == 0 && this.mColorFilter == null && this.mFillPaint.getShader() == null)) {
                    canvas2.drawRect(this.mRect, this.mFillPaint);
                }
                if (z) {
                    canvas2.drawRect(this.mRect, this.mStrokePaint);
                }
            }
            if (z3) {
                canvas.restore();
                return;
            }
            this.mFillPaint.setAlpha(alpha);
            if (z) {
                this.mStrokePaint.setAlpha(alpha2);
            }
        }
    }

    private int modulateAlpha(int i) {
        int i2 = this.mAlpha;
        return (i * (i2 + (i2 >> 7))) >> 8;
    }

    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.mShapeState.mChangingConfigurations;
    }

    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setDither(boolean z) {
        if (z != this.mDither) {
            this.mDither = z;
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter != this.mColorFilter) {
            this.mColorFilter = colorFilter;
            invalidateSelf();
        }
    }

    public int getOpacity() {
        return this.mShapeState.mOpaque ? -1 : -3;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mRingPath = null;
        this.mPathIsDirty = true;
        this.mRectIsDirty = true;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        super.onLevelChange(i);
        this.mRectIsDirty = true;
        this.mPathIsDirty = true;
        invalidateSelf();
        return true;
    }

    private Path buildRing(ShapeState shapeState) {
        float f;
        float f2;
        if (this.mRingPath != null && (!shapeState.mUseLevelForShape || !this.mPathIsDirty)) {
            return this.mRingPath;
        }
        this.mPathIsDirty = false;
        float level = shapeState.mUseLevelForShape ? (((float) getLevel()) * 360.0f) / 10000.0f : 360.0f;
        RectF rectF = new RectF(this.mRect);
        float width = rectF.width() / 2.0f;
        float height = rectF.height() / 2.0f;
        if (shapeState.mThickness != -1) {
            f = (float) shapeState.mThickness;
        } else {
            f = rectF.width() / shapeState.mThicknessRatio;
        }
        if (shapeState.mInnerRadius != -1) {
            f2 = (float) shapeState.mInnerRadius;
        } else {
            f2 = rectF.width() / shapeState.mInnerRadiusRatio;
        }
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(width - f2, height - f2);
        RectF rectF3 = new RectF(rectF2);
        float f3 = -f;
        rectF3.inset(f3, f3);
        Path path = this.mRingPath;
        if (path == null) {
            this.mRingPath = new Path();
        } else {
            path.reset();
        }
        Path path2 = this.mRingPath;
        if (level >= 360.0f || level <= -360.0f) {
            path2.addOval(rectF3, Path.Direction.CW);
            path2.addOval(rectF2, Path.Direction.CCW);
        } else {
            path2.setFillType(Path.FillType.EVEN_ODD);
            float f4 = width + f2;
            path2.moveTo(f4, height);
            path2.lineTo(f4 + f, height);
            path2.arcTo(rectF3, 0.0f, level, false);
            path2.arcTo(rectF2, level, -level, false);
            path2.close();
        }
        return path2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00a6, code lost:
        r11 = r1;
        r12 = r6;
        r14 = r12;
        r13 = r8 * r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b7, code lost:
        r8 = r8 * r4;
        r11 = r1;
        r12 = r6;
        r13 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c3, code lost:
        r8 = r8 * r4;
        r11 = r1;
        r13 = r11;
        r12 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c8, code lost:
        r14 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c9, code lost:
        r0.mFillPaint.setShader(new android.graphics.LinearGradient(r11, r12, r13, r14, r3, r5.mPositions, android.graphics.Shader.TileMode.CLAMP));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean ensureValidRect() {
        /*
            r19 = this;
            r0 = r19
            boolean r1 = r0.mRectIsDirty
            r2 = 1
            if (r1 == 0) goto L_0x0196
            r1 = 0
            r0.mRectIsDirty = r1
            android.graphics.Rect r3 = r19.getBounds()
            r4 = 0
            android.graphics.Paint r5 = r0.mStrokePaint
            if (r5 == 0) goto L_0x001b
            float r4 = r5.getStrokeWidth()
            r5 = 1056964608(0x3f000000, float:0.5)
            float r4 = r4 * r5
        L_0x001b:
            com.hjq.shape.drawable.ShapeState r5 = r0.mShapeState
            android.graphics.RectF r6 = r0.mRect
            int r7 = r3.left
            float r7 = (float) r7
            float r7 = r7 + r4
            com.hjq.shape.drawable.ShapeState r8 = r0.mShapeState
            int r8 = r8.mShadowSize
            float r8 = (float) r8
            float r7 = r7 + r8
            int r8 = r3.top
            float r8 = (float) r8
            float r8 = r8 + r4
            com.hjq.shape.drawable.ShapeState r9 = r0.mShapeState
            int r9 = r9.mShadowSize
            float r9 = (float) r9
            float r8 = r8 + r9
            int r9 = r3.right
            float r9 = (float) r9
            float r9 = r9 - r4
            com.hjq.shape.drawable.ShapeState r10 = r0.mShapeState
            int r10 = r10.mShadowSize
            float r10 = (float) r10
            float r9 = r9 - r10
            int r3 = r3.bottom
            float r3 = (float) r3
            float r3 = r3 - r4
            com.hjq.shape.drawable.ShapeState r4 = r0.mShapeState
            int r4 = r4.mShadowSize
            float r4 = (float) r4
            float r3 = r3 - r4
            r6.set(r7, r8, r9, r3)
            int[] r3 = r5.mGradientColors
            if (r3 == 0) goto L_0x0196
            android.graphics.RectF r4 = r0.mRect
            int r6 = r5.mGradientType
            r7 = 1176256512(0x461c4000, float:10000.0)
            r8 = 1065353216(0x3f800000, float:1.0)
            if (r6 != 0) goto L_0x00dd
            boolean r1 = r5.mUseLevel
            if (r1 == 0) goto L_0x0064
            int r1 = r19.getLevel()
            float r1 = (float) r1
            float r8 = r1 / r7
        L_0x0064:
            int[] r1 = com.hjq.shape.drawable.ShapeDrawable.AnonymousClass1.$SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation
            com.hjq.shape.drawable.ShapeGradientOrientation r6 = r5.mGradientOrientation
            int r6 = r6.ordinal()
            r1 = r1[r6]
            switch(r1) {
                case 1: goto L_0x00bd;
                case 2: goto L_0x00ad;
                case 3: goto L_0x00a0;
                case 4: goto L_0x0095;
                case 5: goto L_0x008e;
                case 6: goto L_0x0083;
                case 7: goto L_0x007c;
                default: goto L_0x0071;
            }
        L_0x0071:
            float r1 = r4.left
            float r6 = r4.top
            float r7 = r4.right
            float r7 = r7 * r8
            float r4 = r4.bottom
            goto L_0x00b7
        L_0x007c:
            float r1 = r4.left
            float r6 = r4.top
            float r4 = r4.right
            goto L_0x00a6
        L_0x0083:
            float r1 = r4.left
            float r6 = r4.bottom
            float r7 = r4.right
            float r7 = r7 * r8
            float r4 = r4.top
            goto L_0x00b7
        L_0x008e:
            float r1 = r4.left
            float r6 = r4.bottom
            float r4 = r4.top
            goto L_0x00c3
        L_0x0095:
            float r1 = r4.right
            float r6 = r4.bottom
            float r7 = r4.left
            float r7 = r7 * r8
            float r4 = r4.top
            goto L_0x00b7
        L_0x00a0:
            float r1 = r4.right
            float r6 = r4.top
            float r4 = r4.left
        L_0x00a6:
            float r8 = r8 * r4
            r11 = r1
            r12 = r6
            r14 = r12
            r13 = r8
            goto L_0x00c9
        L_0x00ad:
            float r1 = r4.right
            float r6 = r4.top
            float r7 = r4.left
            float r7 = r7 * r8
            float r4 = r4.bottom
        L_0x00b7:
            float r8 = r8 * r4
            r11 = r1
            r12 = r6
            r13 = r7
            goto L_0x00c8
        L_0x00bd:
            float r1 = r4.left
            float r6 = r4.top
            float r4 = r4.bottom
        L_0x00c3:
            float r8 = r8 * r4
            r11 = r1
            r13 = r11
            r12 = r6
        L_0x00c8:
            r14 = r8
        L_0x00c9:
            android.graphics.Paint r1 = r0.mFillPaint
            android.graphics.LinearGradient r4 = new android.graphics.LinearGradient
            float[] r6 = r5.mPositions
            android.graphics.Shader$TileMode r17 = android.graphics.Shader.TileMode.CLAMP
            r10 = r4
            r15 = r3
            r16 = r6
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)
            r1.setShader(r4)
            goto L_0x018b
        L_0x00dd:
            int r6 = r5.mGradientType
            if (r6 != r2) goto L_0x011b
            float r1 = r4.left
            float r6 = r4.right
            float r9 = r4.left
            float r6 = r6 - r9
            float r9 = r5.mCenterX
            float r6 = r6 * r9
            float r11 = r1 + r6
            float r1 = r4.top
            float r6 = r4.bottom
            float r4 = r4.top
            float r6 = r6 - r4
            float r4 = r5.mCenterY
            float r6 = r6 * r4
            float r12 = r1 + r6
            boolean r1 = r5.mUseLevel
            if (r1 == 0) goto L_0x0106
            int r1 = r19.getLevel()
            float r1 = (float) r1
            float r8 = r1 / r7
        L_0x0106:
            android.graphics.Paint r1 = r0.mFillPaint
            android.graphics.RadialGradient r4 = new android.graphics.RadialGradient
            float r6 = r5.mGradientRadius
            float r13 = r8 * r6
            r15 = 0
            android.graphics.Shader$TileMode r16 = android.graphics.Shader.TileMode.CLAMP
            r10 = r4
            r14 = r3
            r10.<init>(r11, r12, r13, r14, r15, r16)
            r1.setShader(r4)
            goto L_0x018b
        L_0x011b:
            int r6 = r5.mGradientType
            r9 = 2
            if (r6 != r9) goto L_0x018b
            float r6 = r4.left
            float r9 = r4.right
            float r10 = r4.left
            float r9 = r9 - r10
            float r10 = r5.mCenterX
            float r9 = r9 * r10
            float r6 = r6 + r9
            float r9 = r4.top
            float r10 = r4.bottom
            float r4 = r4.top
            float r10 = r10 - r4
            float r4 = r5.mCenterY
            float r10 = r10 * r4
            float r9 = r9 + r10
            r4 = 0
            boolean r10 = r5.mUseLevel
            if (r10 == 0) goto L_0x0181
            int[] r4 = r5.mTempColors
            int r10 = r3.length
            if (r4 == 0) goto L_0x0147
            int r11 = r4.length
            int r12 = r10 + 1
            if (r11 == r12) goto L_0x014d
        L_0x0147:
            int r4 = r10 + 1
            int[] r4 = new int[r4]
            r5.mTempColors = r4
        L_0x014d:
            java.lang.System.arraycopy(r3, r1, r4, r1, r10)
            int r11 = r10 + -1
            r3 = r3[r11]
            r4[r10] = r3
            float[] r3 = r5.mTempPositions
            float r11 = (float) r11
            float r11 = r8 / r11
            if (r3 == 0) goto L_0x0162
            int r12 = r3.length
            int r13 = r10 + 1
            if (r12 == r13) goto L_0x0168
        L_0x0162:
            int r3 = r10 + 1
            float[] r3 = new float[r3]
            r5.mTempPositions = r3
        L_0x0168:
            int r12 = r19.getLevel()
            float r12 = (float) r12
            float r12 = r12 / r7
        L_0x016e:
            if (r1 >= r10) goto L_0x017a
            float r7 = (float) r1
            float r7 = r7 * r11
            float r7 = r7 * r12
            r3[r1] = r7
            int r1 = r1 + 1
            goto L_0x016e
        L_0x017a:
            r3[r10] = r8
            r18 = r4
            r4 = r3
            r3 = r18
        L_0x0181:
            android.graphics.Paint r1 = r0.mFillPaint
            android.graphics.SweepGradient r7 = new android.graphics.SweepGradient
            r7.<init>(r6, r9, r3, r4)
            r1.setShader(r7)
        L_0x018b:
            boolean r1 = r5.mHasSolidColor
            if (r1 != 0) goto L_0x0196
            android.graphics.Paint r1 = r0.mFillPaint
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r1.setColor(r3)
        L_0x0196:
            android.graphics.RectF r1 = r0.mRect
            boolean r1 = r1.isEmpty()
            r1 = r1 ^ r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hjq.shape.drawable.ShapeDrawable.ensureValidRect():boolean");
    }

    /* renamed from: com.hjq.shape.drawable.ShapeDrawable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation;

        static {
            int[] iArr = new int[com.hjq.shape.drawable.ShapeGradientOrientation.values().length];
            $SwitchMap$com$hjq$shape$drawable$ShapeGradientOrientation = iArr;
            try {
                iArr[com.hjq.shape.drawable.ShapeGradientOrientation.TOP_BOTTOM.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[com.hjq.shape.drawable.ShapeGradientOrientation.TR_BL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[com.hjq.shape.drawable.ShapeGradientOrientation.RIGHT_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[com.hjq.shape.drawable.ShapeGradientOrientation.BR_TL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[com.hjq.shape.drawable.ShapeGradientOrientation.BOTTOM_TOP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[com.hjq.shape.drawable.ShapeGradientOrientation.BL_TR.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[com.hjq.shape.drawable.ShapeGradientOrientation.LEFT_RIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public int getIntrinsicWidth() {
        return this.mShapeState.mWidth;
    }

    public int getIntrinsicHeight() {
        return this.mShapeState.mHeight;
    }

    public Drawable.ConstantState getConstantState() {
        this.mShapeState.mChangingConfigurations = getChangingConfigurations();
        return this.mShapeState;
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            ShapeState shapeState = new ShapeState(this.mShapeState);
            this.mShapeState = shapeState;
            initializeWithState(shapeState);
            this.mMutated = true;
        }
        return this;
    }

    private void initializeWithState(ShapeState shapeState) {
        if (shapeState.mHasSolidColor) {
            this.mFillPaint.setColor(shapeState.mSolidColor);
        } else if (shapeState.mGradientColors == null) {
            this.mFillPaint.setColor(0);
        } else {
            this.mFillPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        }
        this.mPadding = shapeState.mPadding;
        if (shapeState.mStrokeWidth >= 0) {
            setStroke(shapeState.mStrokeWidth, shapeState.mStrokeColor, shapeState.mStrokeDashWidth, shapeState.mStrokeDashGap);
        }
    }
}
