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

    public TimeBar(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mWidth = getWidth();
        this.mHeight = getHeight();
        canvas.drawColor(-16776961);
        int segmentX = 0;
        int[] segmentColors = {-16711936, -65536, Color.rgb(38, 38, 38)};
        int width = this.mWidth;
        int height = this.mHeight;
        while (segmentX < width) {
            int nextInt = this.randrom.nextInt(3);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(segmentColors[nextInt]);
            segmentX += 50;
            canvas.drawRect(new RectF((float) segmentX, (float) (height - 64), (float) segmentX, (float) height), this.mPaint);
        }
        drawTimeScale(canvas);
        drawCursor(canvas);
    }

    public void drawCursor(Canvas canvas) {
        int width = this.mWidth;
        int height = this.mHeight;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(InputDeviceCompat.SOURCE_ANY);
        int cursorX = width / 2;
        canvas.drawRect(new RectF((float) (cursorX - 2), 5.0f, (float) (cursorX + 2), (float) height), this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(-1);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTextSize(32.0f);
        this.mPaint.setTextScaleX(2.0f);
        canvas.drawText("2013-12-11 16:20:02", (float) cursorX, 160.0f, this.mPaint);
    }

    public void drawTimeScale(Canvas canvas) {
        int width = this.mWidth;
        int height = this.mHeight;
        int tickSpacing = width / 48;
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(DayStyle.iColorTextHeader);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTextSize(16.0f);
        this.mPaint.setTextScaleX(2.0f);
        float scaleY = (float) (height - 90);
        canvas.drawLine(0.0f, scaleY, (float) (width * 4), scaleY, this.mPaint);
        for (int tickIndex = 0; tickIndex <= 144; tickIndex++) {
            if (tickIndex % 6 == 0) {
                int tickX = tickSpacing * tickIndex;
                RectF rectF = new RectF((float) (tickX - 3), (float) (height - 120), (float) (tickX + 3), scaleY);
                canvas.drawText(String.format("%02d:00", new Object[]{Integer.valueOf(tickIndex / 6)}), (float) tickX, (float) (height - 130), this.mPaint);
                canvas.drawRect(rectF, this.mPaint);
            } else if (tickIndex % 3 == 0) {
                int tickX = tickSpacing * tickIndex;
                canvas.drawRect(new RectF((float) (tickX - 3), (float) (height + MediaPlayer2.MEDIA_ERROR_TIMED_OUT), (float) (tickX + 3), scaleY), this.mPaint);
            } else if (tickIndex % 1 == 0) {
                int tickX = tickSpacing * tickIndex;
                canvas.drawRect(new RectF((float) (tickX - 2), (float) (height - 100), (float) (tickX + 2), scaleY), this.mPaint);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
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
