package com.hjq.widget.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.hjq.widget.R;

public final class PlayButton extends View {
    public static final int STATE_PAUSE = 1;
    public static final int STATE_PLAY = 0;
    private int mAnimDuration;
    private RectF mBgRectF;
    private int mCenterX;
    private int mCenterY;
    private int mCircleRadius;
    private int mCurrentState;
    private final Path mDstPath;
    private float mFraction;
    private int mHeight;
    private final Paint mPaint;
    private final Path mPath;
    private float mPathLength;
    private final PathMeasure mPathMeasure;
    private RectF mRectF;
    private int mWidth;

    public PlayButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public PlayButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentState = 1;
        this.mFraction = 1.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PlayButton);
        int color = obtainStyledAttributes.getColor(R.styleable.PlayButton_pb_lineColor, -1);
        int integer = obtainStyledAttributes.getInteger(R.styleable.PlayButton_pb_lineSize, (int) getResources().getDimension(R.dimen.dp_4));
        this.mAnimDuration = obtainStyledAttributes.getInteger(R.styleable.PlayButton_pb_animDuration, 200);
        obtainStyledAttributes.recycle();
        setLayerType(1, (Paint) null);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(color);
        paint.setStrokeWidth((float) integer);
        paint.setPathEffect(new CornerPathEffect(1.0f));
        this.mPath = new Path();
        this.mDstPath = new Path();
        this.mPathMeasure = new PathMeasure();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int i5 = (i * 9) / 10;
        this.mWidth = i5;
        this.mHeight = (i2 * 9) / 10;
        this.mCircleRadius = i5 / ((int) getResources().getDimension(R.dimen.dp_4));
        this.mCenterX = i / 2;
        this.mCenterY = i2 / 2;
        int i6 = this.mCenterX;
        int i7 = this.mCircleRadius;
        int i8 = this.mCenterY;
        this.mRectF = new RectF((float) (i6 - i7), ((float) i8) + (((float) i7) * 0.6f), (float) (i6 + i7), ((float) i8) + (((float) i7) * 2.6f));
        int i9 = this.mCenterX;
        int i10 = this.mWidth;
        int i11 = this.mCenterY;
        int i12 = this.mHeight;
        this.mBgRectF = new RectF(((float) i9) - (((float) i10) / 2.0f), ((float) i11) - (((float) i12) / 2.0f), ((float) i9) + (((float) i10) / 2.0f), ((float) i11) + (((float) i12) / 2.0f));
        Path path = this.mPath;
        int i13 = this.mCenterX;
        int i14 = this.mCircleRadius;
        path.moveTo((float) (i13 - i14), ((float) this.mCenterY) + (((float) i14) * 1.8f));
        Path path2 = this.mPath;
        int i15 = this.mCenterX;
        int i16 = this.mCircleRadius;
        path2.lineTo((float) (i15 - i16), ((float) this.mCenterY) - (((float) i16) * 1.8f));
        this.mPath.lineTo((float) (this.mCenterX + this.mCircleRadius), (float) this.mCenterY);
        this.mPath.close();
        this.mPathMeasure.setPath(this.mPath, false);
        this.mPathLength = this.mPathMeasure.getLength();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            i = View.MeasureSpec.makeMeasureSpec((int) getResources().getDimension(R.dimen.dp_60), BasicMeasure.EXACTLY);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            i2 = View.MeasureSpec.makeMeasureSpec((int) getResources().getDimension(R.dimen.dp_60), BasicMeasure.EXACTLY);
        }
        setMeasuredDimension(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        super.onDraw(canvas);
        canvas2.drawCircle((float) this.mCenterX, (float) this.mCenterY, ((float) this.mWidth) / 2.0f, this.mPaint);
        float f = this.mFraction;
        if (f < 0.0f) {
            int i = this.mCenterX;
            int i2 = this.mCircleRadius;
            int i3 = this.mCenterY;
            canvas.drawLine((float) (i + i2), (((float) i3) - (((float) i2) * 1.6f)) + (((float) (i2 * 10)) * f), (float) (i + i2), ((float) i3) + (((float) i2) * 1.6f) + (((float) (i2 * 10)) * f), this.mPaint);
            int i4 = this.mCenterX;
            int i5 = this.mCircleRadius;
            int i6 = this.mCenterY;
            Canvas canvas3 = canvas;
            canvas3.drawLine((float) (i4 - i5), ((float) i6) - (((float) i5) * 1.6f), (float) (i4 - i5), ((float) i6) + (((float) i5) * 1.6f), this.mPaint);
            canvas3.drawArc(this.mBgRectF, -105.0f, 360.0f, false, this.mPaint);
        } else if (((double) f) <= 0.3d) {
            int i7 = this.mCenterX;
            int i8 = this.mCircleRadius;
            int i9 = this.mCenterY;
            canvas.drawLine((float) (i7 + i8), (((float) i9) - (((float) i8) * 1.6f)) + (((((float) i8) * 3.2f) / 0.3f) * f), (float) (i7 + i8), ((float) i9) + (((float) i8) * 1.6f), this.mPaint);
            int i10 = this.mCenterX;
            int i11 = this.mCircleRadius;
            int i12 = this.mCenterY;
            canvas.drawLine((float) (i10 - i11), ((float) i12) - (((float) i11) * 1.6f), (float) (i10 - i11), ((float) i12) + (((float) i11) * 1.6f), this.mPaint);
            float f2 = this.mFraction;
            if (f2 != 0.0f) {
                canvas.drawArc(this.mRectF, 0.0f, f2 * 600.0f, false, this.mPaint);
            }
            RectF rectF = this.mBgRectF;
            float f3 = this.mFraction;
            canvas.drawArc(rectF, (f3 * 360.0f) - 0.04248047f, (1.0f - f3) * 360.0f, false, this.mPaint);
        } else if (((double) f) <= 0.6d) {
            canvas.drawArc(this.mRectF, (f - 0.3f) * 600.0f, 180.0f - ((f - 0.3f) * 600.0f), false, this.mPaint);
            this.mDstPath.reset();
            PathMeasure pathMeasure = this.mPathMeasure;
            float f4 = this.mPathLength;
            pathMeasure.getSegment(0.02f * f4, (0.38f * f4) + (((f4 * 0.42f) / 0.3f) * (this.mFraction - 0.3f)), this.mDstPath, true);
            canvas2.drawPath(this.mDstPath, this.mPaint);
            RectF rectF2 = this.mBgRectF;
            float f5 = this.mFraction;
            canvas.drawArc(rectF2, (f5 * 360.0f) - 0.04248047f, (1.0f - f5) * 360.0f, false, this.mPaint);
        } else if (((double) f) <= 0.8d) {
            this.mDstPath.reset();
            PathMeasure pathMeasure2 = this.mPathMeasure;
            float f6 = this.mPathLength;
            float f7 = this.mFraction;
            pathMeasure2.getSegment((0.02f * f6) + (((f6 * 0.2f) / 0.2f) * (f7 - 0.6f)), (0.8f * f6) + (((f6 * 0.2f) / 0.2f) * (f7 - 0.6f)), this.mDstPath, true);
            canvas2.drawPath(this.mDstPath, this.mPaint);
            RectF rectF3 = this.mBgRectF;
            float f8 = this.mFraction;
            canvas.drawArc(rectF3, (f8 * 360.0f) - 0.04248047f, (1.0f - f8) * 360.0f, false, this.mPaint);
        } else {
            this.mDstPath.reset();
            this.mPathMeasure.getSegment(((float) (this.mCircleRadius * 10)) * (this.mFraction - 1.0f), this.mPathLength, this.mDstPath, true);
            canvas2.drawPath(this.mDstPath, this.mPaint);
        }
    }

    public void play() {
        if (this.mCurrentState != 0) {
            this.mCurrentState = 0;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 100.0f});
            ofFloat.setDuration((long) this.mAnimDuration);
            ofFloat.setInterpolator(new AnticipateInterpolator());
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayButton.this.lambda$play$0$PlayButton(valueAnimator);
                }
            });
            ofFloat.start();
        }
    }

    public /* synthetic */ void lambda$play$0$PlayButton(ValueAnimator valueAnimator) {
        this.mFraction = 1.0f - valueAnimator.getAnimatedFraction();
        invalidate();
    }

    public void pause() {
        if (this.mCurrentState != 1) {
            this.mCurrentState = 1;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 100.0f});
            ofFloat.setDuration((long) this.mAnimDuration);
            ofFloat.setInterpolator(new AnticipateInterpolator());
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PlayButton.this.lambda$pause$1$PlayButton(valueAnimator);
                }
            });
            ofFloat.start();
        }
    }

    public /* synthetic */ void lambda$pause$1$PlayButton(ValueAnimator valueAnimator) {
        this.mFraction = valueAnimator.getAnimatedFraction();
        invalidate();
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public void setAnimDuration(int i) {
        this.mAnimDuration = i;
    }

    public void setLineColor(int i) {
        this.mPaint.setColor(i);
        invalidate();
    }

    public void setLineSize(int i) {
        this.mPaint.setStrokeWidth((float) i);
        invalidate();
    }
}
