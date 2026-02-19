package com.hjq.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.hjq.widget.R;

public final class SwitchButton extends View {
    private static final int STATE_SWITCH_OFF = 1;
    private static final int STATE_SWITCH_OFF2 = 2;
    private static final int STATE_SWITCH_ON = 3;
    private static final int STATE_SWITCH_ON2 = 4;
    private float bRight;
    protected int mAccentColor;
    private float mAnim1;
    private float mAnim2;
    protected final float mAnimationSpeed;
    protected final float mAspectRatio;
    private final Path mBackgroundPath;
    private final Path mBarPath;
    private final RectF mBound;
    private boolean mCanVisibleDrawing;
    private float mCenterX;
    private float mCenterY;
    protected boolean mChecked;
    private int mCheckedState;
    private final AccelerateInterpolator mInterpolator;
    private int mLastCheckedState;
    private float mLeft;
    private OnCheckedChangeListener mListener;
    private float mOff2LeftX;
    protected int mOffColor;
    protected int mOffDarkColor;
    private float mOffLeftX;
    private float mOffset;
    private float mOn2LeftX;
    private float mOnLeftX;
    private final Paint mPaint;
    protected int mPrimaryDarkColor;
    private float mRadius;
    private float mRight;
    private float mScale;
    protected boolean mShadow;
    protected int mShadowColor;
    private RadialGradient mShadowGradient;
    private float mShadowReservedHeight;
    private float mStrokeWidth;
    private float mWidth;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SwitchButton switchButton, boolean z);
    }

    public SwitchButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mInterpolator = new AccelerateInterpolator(2.0f);
        this.mPaint = new Paint();
        this.mBackgroundPath = new Path();
        this.mBarPath = new Path();
        this.mBound = new RectF();
        this.mAspectRatio = 0.68f;
        this.mAnimationSpeed = 0.1f;
        this.mCanVisibleDrawing = false;
        this.mAccentColor = -11806877;
        this.mPrimaryDarkColor = -12925358;
        this.mOffColor = -1842205;
        this.mOffDarkColor = -4210753;
        this.mShadowColor = -13421773;
        int i3 = 1;
        setLayerType(1, (Paint) null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwitchButton);
        this.mChecked = obtainStyledAttributes.getBoolean(R.styleable.SwitchButton_android_checked, this.mChecked);
        setEnabled(obtainStyledAttributes.getBoolean(R.styleable.SwitchButton_android_enabled, isEnabled()));
        i3 = this.mChecked ? 3 : i3;
        this.mCheckedState = i3;
        this.mLastCheckedState = i3;
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec((int) (getResources().getDimension(R.dimen.dp_56) + ((float) getPaddingLeft()) + ((float) getPaddingRight())), BasicMeasure.EXACTLY);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(((int) (((float) View.MeasureSpec.getSize(i)) * 0.68f)) + getPaddingTop() + getPaddingBottom(), BasicMeasure.EXACTLY);
        }
        setMeasuredDimension(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z = i > getPaddingLeft() + getPaddingRight() && i2 > getPaddingTop() + getPaddingBottom();
        this.mCanVisibleDrawing = z;
        if (z) {
            float paddingLeft = (float) ((i - getPaddingLeft()) - getPaddingRight());
            float f = paddingLeft * 0.68f;
            float paddingTop = (float) ((i2 - getPaddingTop()) - getPaddingBottom());
            if (f < paddingTop) {
                i6 = getPaddingLeft();
                i7 = i - getPaddingRight();
                int i9 = ((int) (paddingTop - f)) / 2;
                i5 = getPaddingTop() + i9;
                i8 = (getHeight() - getPaddingBottom()) - i9;
            } else {
                int i10 = ((int) (paddingLeft - (paddingTop / 0.68f))) / 2;
                i6 = getPaddingLeft() + i10;
                i7 = (getWidth() - getPaddingRight()) - i10;
                i5 = getPaddingTop();
                i8 = getHeight() - getPaddingBottom();
            }
            float f2 = (float) ((int) (((float) (i8 - i5)) * 0.07f));
            this.mShadowReservedHeight = f2;
            float f3 = (float) i6;
            float f4 = ((float) i5) + f2;
            float f5 = (float) i7;
            this.mRight = f5;
            float f6 = ((float) i8) - f2;
            float f7 = f6 - f4;
            this.mCenterX = (f5 + f3) / 2.0f;
            float f8 = (f6 + f4) / 2.0f;
            this.mCenterY = f8;
            this.mLeft = f3;
            this.mWidth = f7;
            this.bRight = f3 + f7;
            float f9 = f7 / 2.0f;
            float f10 = 0.95f * f9;
            this.mRadius = f10;
            float f11 = 0.2f * f10;
            this.mOffset = f11;
            float f12 = (f9 - f10) * 2.0f;
            this.mStrokeWidth = f12;
            float f13 = f5 - f7;
            this.mOnLeftX = f13;
            this.mOn2LeftX = f13 - f11;
            this.mOffLeftX = f3;
            this.mOff2LeftX = f11 + f3;
            this.mScale = 1.0f - (f12 / f7);
            this.mBackgroundPath.reset();
            RectF rectF = new RectF();
            rectF.top = f4;
            rectF.bottom = f6;
            rectF.left = f3;
            rectF.right = f3 + f7;
            this.mBackgroundPath.arcTo(rectF, 90.0f, 180.0f);
            rectF.left = this.mRight - f7;
            rectF.right = this.mRight;
            this.mBackgroundPath.arcTo(rectF, 270.0f, 180.0f);
            this.mBackgroundPath.close();
            this.mBound.left = this.mLeft;
            this.mBound.right = this.bRight;
            this.mBound.top = f4 + (this.mStrokeWidth / 2.0f);
            this.mBound.bottom = f6 - (this.mStrokeWidth / 2.0f);
            float f14 = (this.bRight + this.mLeft) / 2.0f;
            int i11 = this.mShadowColor;
            int i12 = (i11 >> 16) & 255;
            int i13 = (i11 >> 8) & 255;
            int i14 = i11 & 255;
            this.mShadowGradient = new RadialGradient(f14, f8, this.mRadius, Color.argb(200, i12, i13, i14), Color.argb(25, i12, i13, i14), Shader.TileMode.CLAMP);
        }
    }

    private void calcBPath(float f) {
        this.mBarPath.reset();
        this.mBound.left = this.mLeft + (this.mStrokeWidth / 2.0f);
        this.mBound.right = this.bRight - (this.mStrokeWidth / 2.0f);
        this.mBarPath.arcTo(this.mBound, 90.0f, 180.0f);
        this.mBound.left = this.mLeft + (this.mOffset * f) + (this.mStrokeWidth / 2.0f);
        this.mBound.right = (this.bRight + (f * this.mOffset)) - (this.mStrokeWidth / 2.0f);
        this.mBarPath.arcTo(this.mBound, 270.0f, 180.0f);
        this.mBarPath.close();
    }

    private float calcBTranslate(float f) {
        float f2 = this.mOffLeftX;
        float f3 = this.mOnLeftX;
        float f4 = this.mOffLeftX;
        float f5 = this.mOnLeftX;
        float f6 = this.mOffLeftX;
        int i = this.mCheckedState;
        int i2 = i - this.mLastCheckedState;
        if (i2 != -3) {
            if (i2 != -2) {
                if (i2 != -1) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i == 1) {
                                    f2 = this.mOffLeftX;
                                } else if (i == 3) {
                                    f2 = this.mOnLeftX;
                                }
                                return f2 - this.mOffLeftX;
                            }
                            f5 = this.mOnLeftX;
                            f6 = this.mOffLeftX;
                        } else if (i == 3) {
                            f5 = this.mOnLeftX;
                            f6 = this.mOffLeftX;
                        } else if (i == 4) {
                            f5 = this.mOn2LeftX;
                            f6 = this.mOffLeftX;
                        }
                    } else if (i == 2) {
                        f2 = this.mOffLeftX;
                        return f2 - this.mOffLeftX;
                    } else if (i == 3) {
                        f5 = this.mOnLeftX;
                        f6 = this.mOn2LeftX;
                    }
                    f2 = f5 - ((f5 - f6) * f);
                    return f2 - this.mOffLeftX;
                } else if (i == 4) {
                    f4 = this.mOn2LeftX;
                    f3 = this.mOnLeftX;
                } else if (i == 1) {
                    f2 = this.mOffLeftX;
                    return f2 - this.mOffLeftX;
                }
            } else if (i == 1) {
                f4 = this.mOffLeftX;
                f3 = this.mOn2LeftX;
            } else if (i == 2) {
                f4 = this.mOff2LeftX;
                f3 = this.mOnLeftX;
            }
            f2 = 0.0f;
            return f2 - this.mOffLeftX;
        }
        f4 = this.mOffLeftX;
        f3 = this.mOnLeftX;
        f2 = f4 + ((f3 - f4) * f);
        return f2 - this.mOffLeftX;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mCanVisibleDrawing) {
            boolean z = true;
            this.mPaint.setAntiAlias(true);
            int i = this.mCheckedState;
            boolean z2 = i == 3 || i == 4;
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(z2 ? this.mAccentColor : this.mOffColor);
            canvas.drawPath(this.mBackgroundPath, this.mPaint);
            float f = this.mAnim1;
            float f2 = f - 0.1f > 0.0f ? f - 0.1f : 0.0f;
            this.mAnim1 = f2;
            float f3 = this.mAnim2;
            this.mAnim2 = f3 - 0.1f > 0.0f ? f3 - 0.1f : 0.0f;
            float interpolation = this.mInterpolator.getInterpolation(f2);
            float interpolation2 = this.mInterpolator.getInterpolation(this.mAnim2);
            float f4 = this.mScale * (z2 ? interpolation : 1.0f - interpolation);
            float f5 = (this.mRight - this.mCenterX) - this.mRadius;
            if (z2) {
                interpolation = 1.0f - interpolation;
            }
            canvas.save();
            canvas.scale(f4, f4, this.mCenterX + (f5 * interpolation), this.mCenterY);
            if (isEnabled()) {
                this.mPaint.setColor(-1);
            } else {
                this.mPaint.setColor(-4473925);
            }
            canvas.drawPath(this.mBackgroundPath, this.mPaint);
            canvas.restore();
            canvas.save();
            canvas.translate(calcBTranslate(interpolation2), this.mShadowReservedHeight);
            int i2 = this.mCheckedState;
            if (!(i2 == 4 || i2 == 2)) {
                z = false;
            }
            if (z) {
                interpolation2 = 1.0f - interpolation2;
            }
            calcBPath(interpolation2);
            if (this.mShadow) {
                this.mPaint.setStyle(Paint.Style.FILL);
                this.mPaint.setShader(this.mShadowGradient);
                canvas.drawPath(this.mBarPath, this.mPaint);
                this.mPaint.setShader((Shader) null);
            }
            canvas.translate(0.0f, -this.mShadowReservedHeight);
            float f6 = this.mWidth;
            canvas.scale(0.98f, 0.98f, f6 / 2.0f, f6 / 2.0f);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(-1);
            canvas.drawPath(this.mBarPath, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mStrokeWidth * 0.5f);
            this.mPaint.setColor(z2 ? this.mPrimaryDarkColor : this.mOffDarkColor);
            canvas.drawPath(this.mBarPath, this.mPaint);
            canvas.restore();
            this.mPaint.reset();
            if (this.mAnim1 > 0.0f || this.mAnim2 > 0.0f) {
                invalidate();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        super.onTouchEvent(motionEvent);
        if (isEnabled() && (((i = this.mCheckedState) == 3 || i == 1) && this.mAnim1 * this.mAnim2 == 0.0f && motionEvent.getAction() == 1)) {
            int i2 = this.mCheckedState;
            this.mLastCheckedState = i2;
            this.mAnim2 = 1.0f;
            if (i2 == 1) {
                setChecked(true, false);
                OnCheckedChangeListener onCheckedChangeListener = this.mListener;
                if (onCheckedChangeListener != null) {
                    onCheckedChangeListener.onCheckedChanged(this, true);
                }
            } else if (i2 == 3) {
                setChecked(false, false);
                OnCheckedChangeListener onCheckedChangeListener2 = this.mListener;
                if (onCheckedChangeListener2 != null) {
                    onCheckedChangeListener2.onCheckedChanged(this, false);
                }
            }
        }
        return true;
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        boolean unused = savedState.checked = this.mChecked;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        boolean access$000 = savedState.checked;
        this.mChecked = access$000;
        this.mCheckedState = access$000 ? 3 : 1;
        invalidate();
    }

    public void setColor(int i, int i2) {
        setColor(i, i2, this.mOffColor, this.mOffDarkColor);
    }

    public void setColor(int i, int i2, int i3, int i4) {
        setColor(i, i2, i3, i4, this.mShadowColor);
    }

    public void setColor(int i, int i2, int i3, int i4, int i5) {
        this.mAccentColor = i;
        this.mPrimaryDarkColor = i2;
        this.mOffColor = i3;
        this.mOffDarkColor = i4;
        this.mShadowColor = i5;
        invalidate();
    }

    public void setShadow(boolean z) {
        this.mShadow = z;
        invalidate();
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void setChecked(boolean z) {
        setChecked(z, true);
    }

    public void setChecked(boolean z, boolean z2) {
        OnCheckedChangeListener onCheckedChangeListener;
        int i = z ? 3 : 1;
        int i2 = this.mCheckedState;
        if (i != i2) {
            if ((i == 3 && (i2 == 1 || i2 == 2)) || (i == 1 && (i2 == 3 || i2 == 4))) {
                this.mAnim1 = 1.0f;
            }
            this.mAnim2 = 1.0f;
            boolean z3 = this.mChecked;
            if (!z3 && i == 3) {
                this.mChecked = true;
            } else if (z3 && i == 1) {
                this.mChecked = false;
            }
            this.mLastCheckedState = i2;
            this.mCheckedState = i;
            postInvalidate();
            if (z2 && (onCheckedChangeListener = this.mListener) != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mListener = onCheckedChangeListener;
    }

    private static final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public boolean checked;

        public int describeContents() {
            return 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.checked = 1 != parcel.readInt() ? false : true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.checked ? 1 : 0);
        }
    }
}
