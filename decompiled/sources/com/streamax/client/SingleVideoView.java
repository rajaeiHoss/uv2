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
import com.streamax.utils.StringUtils;
import com.zycs.UView.R;
import java.nio.ByteBuffer;

// Bitmap-backed renderer for a single channel preview.
public class SingleVideoView extends ImageView {
    private static final String TAG = "VideoView";
    private Bitmap VideoBlt;
    private ByteBuffer buffer;
    private int mHeight = 0;
    private Paint mPaint;
    private byte[] mPixel;
    private float mScales = 1.0f;
    private int mSerial = 0;
    private PointF mTranslate = new PointF(0.0f, 0.0f);
    private int mWidth = 0;
    private boolean mbDrag = false;
    private boolean mbFocus = false;
    private boolean mbMax = false;
    private boolean mbOsd = true;

    public SingleVideoView(Context context, int serial) {
        super(context);
        this.mSerial = serial;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
    }

    public SingleVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
    }

    public SingleVideoView(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
    }

    public void SetFocusState(boolean focused) {
        this.mbFocus = focused;
        postInvalidate();
    }

    public void SetDragState(boolean dragging) {
        this.mbDrag = dragging;
        postInvalidate();
    }

    public void SetOsdState(boolean osdEnabled) {
        this.mbOsd = osdEnabled;
        postInvalidate();
    }

    public void SetMax(boolean max) {
        this.mbMax = max;
        postInvalidate();
    }

    public void WriteIn(byte[] pixelData, int width, int height) {
        if (pixelData != null && width != 0 && height != 0) {
            int requiredPixelBytes = width * height * 2;
            byte[] existingPixels = this.mPixel;
            if (existingPixels == null) {
                byte[] pixelBuffer = new byte[requiredPixelBytes];
                this.mPixel = pixelBuffer;
                this.buffer = ByteBuffer.wrap(pixelBuffer);
            } else if (existingPixels.length < requiredPixelBytes) {
                byte[] resizedPixelBuffer = new byte[requiredPixelBytes];
                this.mPixel = resizedPixelBuffer;
                this.buffer = ByteBuffer.wrap(resizedPixelBuffer);
            }
            if (!(this.mWidth == width && this.mHeight == height)) {
                this.mWidth = width;
                this.mHeight = height;
            }
            if (pixelData.length != 0) {
                try {
                    System.arraycopy(pixelData, 0, this.mPixel, 0, requiredPixelBytes);
                } catch (IndexOutOfBoundsException unused) {
                }
                postInvalidate();
            }
        }
    }

    public void DeDraw(Canvas canvas) {
        int frameHeight;
        int frameWidth = this.mWidth;
        if (frameWidth == 0 || (frameHeight = this.mHeight) == 0) {
            DoPaint(canvas);
            return;
        }
        Bitmap bitmap = this.VideoBlt;
        if (bitmap == null) {
            this.VideoBlt = Bitmap.createBitmap(frameWidth, frameHeight, Bitmap.Config.RGB_565);
        } else if (!(bitmap.getWidth() == this.mWidth && this.VideoBlt.getHeight() == this.mHeight)) {
            this.VideoBlt.recycle();
            this.VideoBlt = Bitmap.createBitmap(this.mWidth, this.mHeight, Bitmap.Config.RGB_565);
        }
        ByteBuffer byteBuffer = this.buffer;
        if (byteBuffer == null) {
            DoPaint(canvas);
            return;
        }
        if (this.VideoBlt != null) {
            byteBuffer.rewind();
            this.VideoBlt.copyPixelsFromBuffer(this.buffer);
        }
        Matrix matrix = new Matrix();
        PointF pointF = new PointF();
        if (this.mScales >= 0.0f) {
            float baseScaleX = ((float) (getWidth() - 2)) / ((float) this.mWidth);
            float baseScaleY = ((float) (getHeight() - 2)) / ((float) this.mHeight);
            float zoomScale = this.mScales;
            float scaledWidth = baseScaleX * zoomScale;
            float scaledHeight = zoomScale * baseScaleY;
            float translateX = this.mTranslate.x / scaledWidth;
            float translateY = this.mTranslate.y / scaledHeight;
            if (scaledWidth > baseScaleX) {
                pointF.x = (((scaledWidth - baseScaleX) * ((float) this.mWidth)) / (scaledWidth * 2.0f)) + translateX;
                pointF.y = (((scaledHeight - baseScaleY) * ((float) this.mHeight)) / (2.0f * scaledHeight)) + translateY;
            } else {
                pointF.x = (((baseScaleX - scaledWidth) * ((float) this.mWidth)) / (scaledWidth * 2.0f)) + translateX;
                pointF.y = (((baseScaleY - scaledHeight) * ((float) this.mHeight)) / (2.0f * scaledHeight)) + translateY;
            }
            matrix.postScale(scaledWidth, scaledHeight, pointF.x, pointF.y);
        }
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawBitmap(this.VideoBlt, matrix, (Paint) null);
        DoPaint(canvas);
    }

    public void SetDigitalZoomIn(float scaleFactor) {
        float currentScale = this.mScales;
        if (currentScale * scaleFactor > 0.0f) {
            this.mScales = currentScale * scaleFactor;
            this.mTranslate.set(0.0f, 0.0f);
        }
        invalidate();
    }

    public float GetDigitalScales() {
        return this.mScales;
    }

    public void ResetDigitalScales() {
        this.mScales = 1.0f;
        this.mTranslate.set(0.0f, 0.0f);
        invalidate();
    }

    public void SetDigitalTranslate(float deltaX, float deltaY) {
        if (this.mTranslate == null) {
            this.mTranslate = new PointF();
        }
        this.mTranslate.offset(deltaX, deltaY);
        invalidate();
    }

    public void DoPaint(Canvas canvas) {
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
            canvas.drawText(StringUtils.getString(Integer.valueOf(R.string.config_Channel)) + Integer.toString(this.mSerial), 16.0f, 16.0f, this.mPaint);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        DeDraw(canvas);
    }

    public void ClearDraw() {
        this.mWidth = 0;
        this.mHeight = 0;
        Bitmap bitmap = this.VideoBlt;
        if (bitmap != null) {
            bitmap.recycle();
            this.VideoBlt = null;
        }
        postInvalidate();
    }
}
