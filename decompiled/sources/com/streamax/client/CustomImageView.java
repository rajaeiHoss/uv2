package com.streamax.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;

public class CustomImageView extends ImageView {
    private int mBkColor = ViewCompat.MEASURED_STATE_MASK;
    private int mHeight = 0;
    private Matrix mMatrix;
    public OneFrame mOneFrame;
    private Paint mPaint;
    private PointF mPoint;
    private float mScales = 1.0f;
    private int mSerial = 0;
    private PointF mTranslate = new PointF(0.0f, 0.0f);
    public Bitmap mVideoBit;
    private int mWidth = 0;
    private boolean mbDrag = false;
    private boolean mbFocus = false;
    private boolean mbMax = false;
    private boolean mbOsd = true;

    public class OneFrame {
        public byte[] data;
        public int height;
        public int width;

        public OneFrame() {
        }
    }

    public CustomImageView(Context context, int serial) {
        super(context);
        this.mSerial = serial;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
        setScaleType(ImageView.ScaleType.CENTER);
        this.mOneFrame = new OneFrame();
    }

    public CustomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
        this.mOneFrame = new OneFrame();
    }

    public void setBkColor(int color) {
        this.mBkColor = color;
    }

    public void setFocusState(boolean focused) {
        this.mbFocus = focused;
        postInvalidate();
    }

    public void setDragState(boolean dragging) {
        this.mbDrag = dragging;
        postInvalidate();
    }

    public void setOsdState(boolean enabled) {
        this.mbOsd = enabled;
        postInvalidate();
    }

    public void setMax(boolean maximized) {
        this.mbMax = maximized;
        postInvalidate();
    }

    public void writeIn(byte[] data, int width, int height) {
        if (data == null || width <= 0 || height <= 0) {
            return;
        }
        int frameSize = width * height * 2;
        synchronized (this.mOneFrame) {
            if (this.mOneFrame.data == null || this.mOneFrame.data.length < frameSize) {
                this.mOneFrame.data = new byte[frameSize];
            }
            if (this.mOneFrame.data == null) {
                return;
            }
            try {
                System.arraycopy(data, 0, this.mOneFrame.data, 0, frameSize);
            } catch (IndexOutOfBoundsException unused) {
            }
            this.mOneFrame.width = width;
            this.mOneFrame.height = height;
        }
        postInvalidate();
    }

    public void deDraw(android.graphics.Canvas canvas) {
        synchronized (this.mOneFrame) {
            if (this.mOneFrame.data == null || this.mOneFrame.data.length <= 0 || this.mOneFrame.width == 0 || this.mOneFrame.height == 0) {
                doPaint(canvas);
                return;
            }
            java.nio.ByteBuffer wrap = java.nio.ByteBuffer.wrap(this.mOneFrame.data);
            if (wrap == null) {
                doPaint(canvas);
                return;
            }
            this.mWidth = this.mOneFrame.width;
            this.mHeight = this.mOneFrame.height;
            if (this.mVideoBit == null) {
                this.mVideoBit = android.graphics.Bitmap.createBitmap(this.mWidth, this.mHeight, android.graphics.Bitmap.Config.RGB_565);
            } else if (this.mVideoBit.getWidth() != this.mWidth || this.mVideoBit.getHeight() != this.mHeight) {
                this.mVideoBit = android.graphics.Bitmap.createBitmap(this.mWidth, this.mHeight, android.graphics.Bitmap.Config.RGB_565);
            }
            if (this.mVideoBit != null) {
                wrap.rewind();
                this.mVideoBit.copyPixelsFromBuffer(wrap);
            }
        }
        if (this.mMatrix == null) {
            this.mMatrix = new android.graphics.Matrix();
        }
        if (this.mPoint == null) {
            this.mPoint = new android.graphics.PointF();
        }
        if (this.mScales >= 0.0f) {
            float scaleX = ((float) (getWidth() - 2)) / ((float) this.mWidth);
            float scaleY = ((float) (getHeight() - 2)) / ((float) this.mHeight);
            float scaledX = scaleX * this.mScales;
            float scaledY = this.mScales * scaleY;
            float translateX = this.mTranslate.x / scaledX;
            float translateY = this.mTranslate.y / scaledY;
            float half = 2.0f;
            if (scaledX > scaleX) {
                this.mPoint.x = (((scaledX - scaleX) * ((float) this.mWidth)) / (scaledX * half)) + translateX;
                this.mPoint.y = (((scaledY - scaleY) * ((float) this.mHeight)) / (half * scaledY)) + translateY;
            } else {
                this.mPoint.x = (((scaleX - scaledX) * ((float) this.mWidth)) / (scaledX * half)) + translateX;
                this.mPoint.y = (((scaleY - scaledY) * ((float) this.mHeight)) / (half * scaledY)) + translateY;
            }
            this.mMatrix.setScale(scaledX, scaledY, this.mPoint.x, this.mPoint.y);
        }
        canvas.drawColor(this.mBkColor);
        if (this.mVideoBit != null) {
            canvas.drawBitmap(this.mVideoBit, this.mMatrix, (android.graphics.Paint) null);
        }
        doPaint(canvas);
    }

    public void setDigitalZoomIn(float scaleFactor) {
        float previousScale = this.mScales;
        if (previousScale * scaleFactor > 0.0f) {
            this.mScales = previousScale * scaleFactor;
            this.mTranslate.set(0.0f, 0.0f);
        }
        invalidate();
    }

    public float getDigitalScales() {
        return this.mScales;
    }

    public void resetDigitalScales() {
        this.mScales = 1.0f;
        this.mTranslate.set(0.0f, 0.0f);
        invalidate();
    }

    public void setDigitalTranslate(float deltaX, float deltaY) {
        if (this.mTranslate == null) {
            this.mTranslate = new PointF();
        }
        this.mTranslate.offset(deltaX, deltaY);
        invalidate();
    }

    public void doPaint(Canvas canvas) {
        if (this.mbFocus) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(-16711936);
            Rect rect = new Rect();
            rect.left = 1;
            rect.top = 1;
            rect.right = getWidth();
            rect.bottom = getHeight();
            if (!this.mbMax) {
                canvas.drawRect(rect, this.mPaint);
            }
        } else if (this.mbDrag) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
            Rect rect2 = new Rect();
            rect2.left = 1;
            rect2.top = 1;
            rect2.right = getWidth();
            rect2.bottom = getHeight();
            canvas.drawRect(rect2, this.mPaint);
        } else {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(Color.rgb(72, 72, 72));
            Rect rect3 = new Rect();
            rect3.left = 1;
            rect3.top = 1;
            rect3.right = getWidth();
            rect3.bottom = getHeight();
            canvas.drawRect(rect3, this.mPaint);
        }
        if (this.mbOsd) {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(-1);
            this.mPaint.setTextAlign(Paint.Align.LEFT);
            this.mPaint.setTextSize(16.0f);
            canvas.drawText(Integer.toString(this.mSerial), 16.0f, 16.0f, this.mPaint);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        deDraw(canvas);
    }

    public void clearDraw() {
        this.mWidth = 0;
        this.mHeight = 0;
        Bitmap bitmap = this.mVideoBit;
        if (bitmap != null) {
            bitmap.recycle();
            this.mVideoBit = null;
        }
        synchronized (this.mOneFrame) {
            this.mOneFrame.width = 0;
            this.mOneFrame.height = 0;
            this.mOneFrame.data = null;
        }
        postInvalidate();
    }
}
