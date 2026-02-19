package com.streamax.TimeBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.InputDeviceCompat;
import androidx.media2.MediaPlayer2;
import com.dvr.calendar.DayStyle;
import java.util.Random;

public class TimeBar extends View {
    private static final int DRAG = 1;
    private static final int NONE = 0;
    private static final int ZOOM = 2;
    private int mHeight = 0;
    private Paint mPaint = new Paint();
    private int mTempValue = 0;
    private int mTouchMode;
    private int mWidth = 0;
    private PointF mptLastPoint = new PointF();
    public PointF mptStart;
    Random randrom = new Random();

    public TimeBar(Context context) {
        super(context);
    }

    public TimeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TimeBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        canvas.drawColor(-16776961);
        int i = 0;
        int[] iArr = {-16711936, -65536, Color.rgb(38, 38, 38)};
        int i2 = this.mWidth;
        int i3 = this.mHeight;
        while (i < i2) {
            int nextInt = this.randrom.nextInt(3);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(iArr[nextInt]);
            i += 50;
            canvas.drawRect(new RectF((float) i, (float) (i3 - 64), (float) i, (float) i3), this.mPaint);
        }
        drawTimeScale(canvas);
        drawCursor(canvas);
    }

    public void drawCursor(Canvas canvas) {
        int i = this.mWidth;
        int i2 = this.mHeight;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(InputDeviceCompat.SOURCE_ANY);
        int i3 = i / 2;
        canvas.drawRect(new RectF((float) (i3 - 2), 5.0f, (float) (i3 + 2), (float) i2), this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-1);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTextSize(32.0f);
        this.mPaint.setTextScaleX(2.0f);
        canvas.drawText("2013-12-11 16:20:02", (float) i3, 160.0f, this.mPaint);
    }

    public void drawTimeScale(Canvas canvas) {
        int i = this.mWidth;
        int i2 = this.mHeight;
        int i3 = i / 48;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(DayStyle.iColorTextHeader);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTextSize(16.0f);
        this.mPaint.setTextScaleX(2.0f);
        float f = (float) (i2 - 90);
        canvas.drawLine(0.0f, f, (float) (i * 4), f, this.mPaint);
        for (int i4 = 0; i4 <= 144; i4++) {
            if (i4 % 6 == 0) {
                int i5 = i3 * i4;
                RectF rectF = new RectF((float) (i5 - 3), (float) (i2 - 120), (float) (i5 + 3), f);
                canvas.drawText(String.format("%02d:00", new Object[]{Integer.valueOf(i4 / 6)}), (float) i5, (float) (i2 - 130), this.mPaint);
                canvas.drawRect(rectF, this.mPaint);
            } else if (i4 % 3 == 0) {
                int i6 = i3 * i4;
                canvas.drawRect(new RectF((float) (i6 - 3), (float) (i2 + MediaPlayer2.MEDIA_ERROR_TIMED_OUT), (float) (i6 + 3), f), this.mPaint);
            } else if (i4 % 1 == 0) {
                int i7 = i3 * i4;
                canvas.drawRect(new RectF((float) (i7 - 2), (float) (i2 - 100), (float) (i7 + 2), f), this.mPaint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            if (this.mptStart == null) {
                this.mptStart = new PointF();
            }
            this.mptStart.set(motionEvent.getX(), motionEvent.getY());
            this.mTouchMode = 1;
            this.mTempValue = 0;
            this.mptLastPoint.set(motionEvent.getX(), motionEvent.getY());
            invalidate();
        } else if (action == 1) {
            invalidate();
        } else if (action == 2) {
            getScrollX();
            this.mTempValue = (int) (motionEvent.getX() - this.mptStart.x);
            scrollBy((int) (this.mptLastPoint.x - motionEvent.getX()), 0);
            this.mptLastPoint.set(motionEvent.getX(), motionEvent.getY());
        }
        return true;
    }

    public float GetDistance(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }
}
