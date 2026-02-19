package com.hjq.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.hjq.widget.R;

public final class SimpleRatingBar extends View {
    private float mCurrentGrade;
    private Drawable mFillDrawable;
    private final Rect mGradeBounds;
    private int mGradeCount;
    private int mGradeHeight;
    private int mGradeSpace;
    private GradleStep mGradeStep;
    private int mGradeWidth;
    private Drawable mHalfDrawable;
    private OnRatingChangeListener mListener;
    private Drawable mNormalDrawable;

    public enum GradleStep {
        HALF,
        ONE
    }

    public interface OnRatingChangeListener {
        void onRatingChanged(SimpleRatingBar simpleRatingBar, float f, boolean z);
    }

    public SimpleRatingBar(Context context) {
        this(context, (AttributeSet) null);
    }

    public SimpleRatingBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SimpleRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGradeBounds = new Rect();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SimpleRatingBar);
        this.mNormalDrawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(R.styleable.SimpleRatingBar_normalDrawable, R.drawable.rating_star_off_ic));
        this.mHalfDrawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(R.styleable.SimpleRatingBar_halfDrawable, R.drawable.rating_star_half_ic));
        this.mFillDrawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(R.styleable.SimpleRatingBar_fillDrawable, R.drawable.rating_star_fill_ic));
        if (this.mNormalDrawable.getIntrinsicWidth() == this.mFillDrawable.getIntrinsicWidth() && this.mNormalDrawable.getIntrinsicWidth() == this.mHalfDrawable.getIntrinsicWidth() && this.mNormalDrawable.getIntrinsicHeight() == this.mFillDrawable.getIntrinsicHeight() && this.mNormalDrawable.getIntrinsicHeight() == this.mHalfDrawable.getIntrinsicHeight()) {
            this.mCurrentGrade = obtainStyledAttributes.getFloat(R.styleable.SimpleRatingBar_grade, 0.0f);
            this.mGradeCount = obtainStyledAttributes.getInt(R.styleable.SimpleRatingBar_gradeCount, 5);
            this.mGradeWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SimpleRatingBar_gradeWidth, this.mNormalDrawable.getIntrinsicWidth());
            this.mGradeHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SimpleRatingBar_gradeHeight, this.mFillDrawable.getIntrinsicHeight());
            this.mGradeSpace = (int) obtainStyledAttributes.getDimension(R.styleable.SimpleRatingBar_gradeSpace, ((float) this.mGradeWidth) / 4.0f);
            if (obtainStyledAttributes.getInt(R.styleable.SimpleRatingBar_gradeStep, 0) != 1) {
                this.mGradeStep = GradleStep.HALF;
            } else {
                this.mGradeStep = GradleStep.ONE;
            }
            obtainStyledAttributes.recycle();
            return;
        }
        throw new IllegalStateException("The width and height of the picture do not agree");
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3 = this.mGradeWidth;
        int i4 = this.mGradeCount;
        setMeasuredDimension((i3 * i4) + (this.mGradeSpace * (i4 + 1)) + getPaddingLeft() + getPaddingRight(), this.mGradeHeight + getPaddingTop() + getPaddingBottom());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0 || action == 2) {
            float x = motionEvent.getX() - ((float) getPaddingLeft());
            int i = this.mGradeSpace;
            float f = x - ((float) i);
            float min = Math.min(Math.max(f > 0.0f ? f / ((float) (this.mGradeWidth + i)) : 0.0f, 0.0f), (float) this.mGradeCount);
            float f2 = (float) ((int) min);
            float f3 = min - f2;
            if (f3 > 0.0f) {
                min = f3 > 0.5f ? (float) ((int) (min + 0.5f)) : f2 + 0.5f;
            }
            if (min * 10.0f != this.mCurrentGrade * 10.0f) {
                this.mCurrentGrade = min;
                invalidate();
                OnRatingChangeListener onRatingChangeListener = this.mListener;
                if (onRatingChangeListener != null) {
                    onRatingChangeListener.onRatingChanged(this, this.mCurrentGrade, true);
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        for (int i = 0; i < this.mGradeCount; i++) {
            int i2 = this.mGradeSpace;
            int i3 = i2 + ((this.mGradeWidth + i2) * i);
            this.mGradeBounds.left = getPaddingLeft() + i3;
            this.mGradeBounds.top = getPaddingTop();
            Rect rect = this.mGradeBounds;
            rect.right = rect.left + this.mGradeWidth;
            Rect rect2 = this.mGradeBounds;
            rect2.bottom = rect2.top + this.mGradeHeight;
            if (this.mCurrentGrade > ((float) i)) {
                if (this.mHalfDrawable != null && this.mGradeStep == GradleStep.HALF) {
                    float f = this.mCurrentGrade;
                    if (((int) f) == i && f - ((float) ((int) f)) == 0.5f) {
                        this.mHalfDrawable.setBounds(this.mGradeBounds);
                        this.mHalfDrawable.draw(canvas);
                    }
                }
                this.mFillDrawable.setBounds(this.mGradeBounds);
                this.mFillDrawable.draw(canvas);
            } else {
                this.mNormalDrawable.setBounds(this.mGradeBounds);
                this.mNormalDrawable.draw(canvas);
            }
        }
    }

    public void setRatingDrawable(int i, int i2, int i3) {
        setRatingDrawable(ContextCompat.getDrawable(getContext(), i), ContextCompat.getDrawable(getContext(), i2), ContextCompat.getDrawable(getContext(), i3));
    }

    public void setRatingDrawable(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        if (drawable == null || drawable3 == null) {
            throw new IllegalStateException("Drawable cannot be empty");
        } else if (drawable.getIntrinsicWidth() == drawable3.getIntrinsicWidth() && drawable.getIntrinsicHeight() == drawable3.getIntrinsicHeight()) {
            this.mNormalDrawable = drawable;
            this.mHalfDrawable = drawable2;
            this.mFillDrawable = drawable3;
            this.mGradeWidth = drawable.getIntrinsicWidth();
            this.mGradeHeight = this.mNormalDrawable.getIntrinsicHeight();
            requestLayout();
        } else {
            throw new IllegalStateException("The width and height of the picture do not agree");
        }
    }

    public float getGrade() {
        return this.mCurrentGrade;
    }

    public void setGrade(float f) {
        int i = this.mGradeCount;
        if (f > ((float) i)) {
            f = (float) i;
        }
        float f2 = f - ((float) ((int) f));
        if (f2 != 0.5f || f2 > 0.0f) {
            throw new IllegalArgumentException("grade must be a multiple of 0.5f");
        }
        this.mCurrentGrade = f;
        invalidate();
        OnRatingChangeListener onRatingChangeListener = this.mListener;
        if (onRatingChangeListener != null) {
            onRatingChangeListener.onRatingChanged(this, this.mCurrentGrade, false);
        }
    }

    public int getGradeCount() {
        return this.mGradeCount;
    }

    public void setGradeCount(int i) {
        float f = (float) i;
        if (f > this.mCurrentGrade) {
            this.mCurrentGrade = f;
        }
        this.mGradeCount = i;
        invalidate();
    }

    public void setGradeSpace(int i) {
        this.mGradeSpace = i;
        requestLayout();
    }

    public void setGradeStep(GradleStep gradleStep) {
        this.mGradeStep = gradleStep;
        invalidate();
    }

    public GradleStep getGradeStep() {
        return this.mGradeStep;
    }

    public void setOnRatingBarChangeListener(OnRatingChangeListener onRatingChangeListener) {
        this.mListener = onRatingChangeListener;
    }
}
