package com.hjq.widget.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.AccelerateInterpolator;
import androidx.appcompat.widget.AppCompatButton;
import com.hjq.widget.R;

public final class SubmitButton extends AppCompatButton {
    private static final int STATE_LOADING = 2;
    private static final int STATE_NONE = 0;
    private static final int STATE_RESULT = 3;
    private static final int STATE_SUBMIT = 1;
    private static final int STYLE_LOADING = 0;
    private static final int STYLE_PROGRESS = 1;
    private Paint mBackgroundPaint;
    private Path mButtonPath;
    private int mButtonState;
    private RectF mCircleLeft;
    private RectF mCircleMid;
    private RectF mCircleRight;
    private float mCurrentProgress;
    /* access modifiers changed from: private */
    public boolean mDoResult;
    private Path mDstPath;
    private final int mErrorColor;
    private Path mLoadPath;
    private float mLoadValue;
    private ValueAnimator mLoadingAnim;
    private Paint mLoadingPaint;
    private int mMaxHeight;
    private int mMaxWidth;
    private PathMeasure mPathMeasure;
    private final int mProgressColor;
    private final int mProgressStyle;
    private ValueAnimator mResultAnim;
    private Paint mResultPaint;
    private Path mResultPath;
    private ValueAnimator mSubmitAnim;
    private boolean mSucceed;
    private final int mSucceedColor;
    private int mViewHeight;
    private int mViewWidth;
    private int mX;
    private int mY;

    public SubmitButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public SubmitButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SubmitButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mButtonState = 0;
        setLayerType(1, (Paint) null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SubmitButton, i, 0);
        this.mProgressColor = obtainStyledAttributes.getColor(R.styleable.SubmitButton_progressColor, getAccentColor());
        this.mSucceedColor = obtainStyledAttributes.getColor(R.styleable.SubmitButton_succeedColor, Color.parseColor("#19CC95"));
        this.mErrorColor = obtainStyledAttributes.getColor(R.styleable.SubmitButton_errorColor, Color.parseColor("#FC8E34"));
        this.mProgressStyle = obtainStyledAttributes.getInt(R.styleable.SubmitButton_progressStyle, 0);
        obtainStyledAttributes.recycle();
        initPaint();
        resetPaint();
    }

    private void initPaint() {
        this.mBackgroundPaint = new Paint();
        this.mLoadingPaint = new Paint();
        this.mResultPaint = new Paint();
        this.mButtonPath = new Path();
        this.mLoadPath = new Path();
        this.mResultPath = new Path();
        this.mDstPath = new Path();
        this.mCircleMid = new RectF();
        this.mCircleLeft = new RectF();
        this.mCircleRight = new RectF();
        this.mPathMeasure = new PathMeasure();
    }

    private void resetPaint() {
        this.mBackgroundPaint.setColor(this.mProgressColor);
        this.mBackgroundPaint.setStrokeWidth(5.0f);
        this.mBackgroundPaint.setAntiAlias(true);
        this.mLoadingPaint.setColor(this.mProgressColor);
        this.mLoadingPaint.setStyle(Paint.Style.STROKE);
        this.mLoadingPaint.setStrokeWidth(9.0f);
        this.mLoadingPaint.setAntiAlias(true);
        this.mResultPaint.setColor(-1);
        this.mResultPaint.setStyle(Paint.Style.STROKE);
        this.mResultPaint.setStrokeWidth(9.0f);
        this.mResultPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mResultPaint.setAntiAlias(true);
        this.mButtonPath.reset();
        this.mLoadPath.reset();
        this.mResultPath.reset();
        this.mDstPath.reset();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mButtonState != 2) {
            int i5 = i - 10;
            this.mViewWidth = i5;
            int i6 = i2 - 10;
            this.mViewHeight = i6;
            this.mX = (int) (((double) i) * 0.5d);
            this.mY = (int) (((double) i2) * 0.5d);
            this.mMaxWidth = i5;
            this.mMaxHeight = i6;
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i = this.mButtonState;
        if (i == 0) {
            super.onDraw(canvas);
        } else if (i == 1 || i == 2) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.translate((float) this.mX, (float) this.mY);
            drawButton(canvas);
            drawLoading(canvas);
        } else if (i == 3) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.translate((float) this.mX, (float) this.mY);
            drawButton(canvas);
            drawResult(canvas, this.mSucceed);
        }
    }

    private void drawButton(Canvas canvas) {
        this.mButtonPath.reset();
        RectF rectF = this.mCircleLeft;
        int i = this.mViewWidth;
        int i2 = this.mViewHeight;
        rectF.set(((float) (-i)) / 2.0f, ((float) (-i2)) / 2.0f, (((float) (-i)) / 2.0f) + ((float) i2), ((float) i2) / 2.0f);
        this.mButtonPath.arcTo(this.mCircleLeft, 90.0f, 180.0f);
        Path path = this.mButtonPath;
        int i3 = this.mViewHeight;
        path.lineTo((((float) this.mViewWidth) / 2.0f) - (((float) i3) / 2.0f), ((float) (-i3)) / 2.0f);
        RectF rectF2 = this.mCircleRight;
        int i4 = this.mViewWidth;
        int i5 = this.mViewHeight;
        rectF2.set((((float) i4) / 2.0f) - ((float) i5), ((float) (-i5)) / 2.0f, ((float) i4) / 2.0f, ((float) i5) / 2.0f);
        this.mButtonPath.arcTo(this.mCircleRight, 270.0f, 180.0f);
        Path path2 = this.mButtonPath;
        int i6 = this.mViewHeight;
        path2.lineTo((((float) (-this.mViewWidth)) / 2.0f) + (((float) i6) / 2.0f), ((float) i6) / 2.0f);
        canvas.drawPath(this.mButtonPath, this.mBackgroundPaint);
    }

    private void drawLoading(Canvas canvas) {
        float f;
        float f2;
        this.mDstPath.reset();
        RectF rectF = this.mCircleMid;
        int i = this.mMaxHeight;
        rectF.set(((float) (-i)) / 2.0f, ((float) (-i)) / 2.0f, ((float) i) / 2.0f, ((float) i) / 2.0f);
        this.mLoadPath.addArc(this.mCircleMid, 270.0f, 359.999f);
        this.mPathMeasure.setPath(this.mLoadPath, true);
        if (this.mProgressStyle == 0) {
            f2 = this.mPathMeasure.getLength() * this.mLoadValue;
            f = ((this.mPathMeasure.getLength() / 2.0f) * this.mLoadValue) + f2;
        } else {
            f = this.mCurrentProgress * this.mPathMeasure.getLength();
            f2 = 0.0f;
        }
        this.mPathMeasure.getSegment(f2, f, this.mDstPath, true);
        canvas.drawPath(this.mDstPath, this.mLoadingPaint);
    }

    private void drawResult(Canvas canvas, boolean z) {
        if (z) {
            this.mResultPath.moveTo(((float) (-this.mViewHeight)) / 6.0f, 0.0f);
            this.mResultPath.lineTo(0.0f, (float) (((double) ((-this.mViewHeight) / 6)) + (((Math.sqrt(5.0d) + 1.0d) * ((double) this.mViewHeight)) / 12.0d)));
            Path path = this.mResultPath;
            int i = this.mViewHeight;
            path.lineTo(((float) i) / 6.0f, ((float) (-i)) / 6.0f);
        } else {
            Path path2 = this.mResultPath;
            int i2 = this.mViewHeight;
            path2.moveTo(((float) (-i2)) / 6.0f, ((float) i2) / 6.0f);
            Path path3 = this.mResultPath;
            int i3 = this.mViewHeight;
            path3.lineTo(((float) i3) / 6.0f, ((float) (-i3)) / 6.0f);
            Path path4 = this.mResultPath;
            int i4 = this.mViewHeight;
            path4.moveTo(((float) (-i4)) / 6.0f, ((float) (-i4)) / 6.0f);
            Path path5 = this.mResultPath;
            int i5 = this.mViewHeight;
            path5.lineTo(((float) i5) / 6.0f, ((float) i5) / 6.0f);
        }
        canvas.drawPath(this.mResultPath, this.mResultPaint);
    }

    private void startSubmitAnim() {
        this.mButtonState = 1;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mMaxWidth, this.mMaxHeight});
        this.mSubmitAnim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SubmitButton.this.lambda$startSubmitAnim$0$SubmitButton(valueAnimator);
            }
        });
        this.mSubmitAnim.setDuration(300);
        this.mSubmitAnim.setInterpolator(new AccelerateInterpolator());
        this.mSubmitAnim.start();
        this.mSubmitAnim.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (SubmitButton.this.mDoResult) {
                    SubmitButton.this.startResultAnim();
                } else {
                    SubmitButton.this.startLoadingAnim();
                }
            }
        });
    }

    public /* synthetic */ void lambda$startSubmitAnim$0$SubmitButton(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mViewWidth = intValue;
        if (intValue == this.mViewHeight) {
            this.mBackgroundPaint.setColor(Color.parseColor("#DDDDDD"));
            this.mBackgroundPaint.setStyle(Paint.Style.STROKE);
        }
        invalidate();
    }

    /* access modifiers changed from: private */
    public void startLoadingAnim() {
        this.mButtonState = 2;
        if (this.mProgressStyle != 1) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mLoadingAnim = ofFloat;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    SubmitButton.this.lambda$startLoadingAnim$1$SubmitButton(valueAnimator);
                }
            });
            this.mLoadingAnim.setDuration(2000);
            this.mLoadingAnim.setRepeatCount(-1);
            this.mLoadingAnim.start();
        }
    }

    public /* synthetic */ void lambda$startLoadingAnim$1$SubmitButton(ValueAnimator valueAnimator) {
        this.mLoadValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        invalidate();
    }

    /* access modifiers changed from: private */
    public void startResultAnim() {
        this.mButtonState = 3;
        ValueAnimator valueAnimator = this.mLoadingAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.mMaxHeight, this.mMaxWidth});
        this.mResultAnim = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SubmitButton.this.lambda$startResultAnim$2$SubmitButton(valueAnimator);
            }
        });
        this.mResultAnim.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                SubmitButton.this.requestLayout();
            }
        });
        this.mResultAnim.setDuration(300);
        this.mResultAnim.setInterpolator(new AccelerateInterpolator());
        this.mResultAnim.start();
    }

    public /* synthetic */ void lambda$startResultAnim$2$SubmitButton(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mViewWidth = intValue;
        this.mResultPaint.setAlpha(((intValue - this.mViewHeight) * 255) / (this.mMaxWidth - this.mMaxHeight));
        if (this.mViewWidth == this.mViewHeight) {
            if (this.mSucceed) {
                this.mBackgroundPaint.setColor(this.mSucceedColor);
            } else {
                this.mBackgroundPaint.setColor(this.mErrorColor);
            }
            this.mBackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        invalidate();
    }

    public boolean performClick() {
        if (this.mButtonState != 0) {
            return true;
        }
        startSubmitAnim();
        return super.performClick();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.mSubmitAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mLoadingAnim;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        ValueAnimator valueAnimator3 = this.mResultAnim;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
    }

    public void showProgress() {
        if (this.mButtonState == 0) {
            startSubmitAnim();
        }
    }

    public void showSucceed() {
        showResult(true);
    }

    public void showError() {
        showResult(false);
    }

    public void showError(long j) {
        showResult(false);
        postDelayed(new Runnable() {
            public final void run() {
                SubmitButton.this.reset();
            }
        }, j);
    }

    private void showResult(boolean z) {
        int i = this.mButtonState;
        if (i != 0 && i != 3 && !this.mDoResult) {
            this.mDoResult = true;
            this.mSucceed = z;
            if (i == 2) {
                startResultAnim();
            }
        }
    }

    public void reset() {
        ValueAnimator valueAnimator = this.mSubmitAnim;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mLoadingAnim;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        ValueAnimator valueAnimator3 = this.mResultAnim;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
        this.mButtonState = 0;
        this.mViewWidth = this.mMaxWidth;
        this.mViewHeight = this.mMaxHeight;
        this.mSucceed = false;
        this.mDoResult = false;
        this.mCurrentProgress = 0.0f;
        resetPaint();
        invalidate();
    }

    public void setProgress(float f) {
        this.mCurrentProgress = f;
        if (this.mProgressStyle == 1 && this.mButtonState == 2) {
            invalidate();
        }
    }

    private int getAccentColor() {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
        return typedValue.data;
    }
}
