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

    public SingleVideoView(Context context, int i) {
        super(context);
        this.mSerial = i;
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

    public SingleVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void SetFocusState(boolean z) {
        this.mbFocus = z;
        postInvalidate();
    }

    public void SetDragState(boolean z) {
        this.mbDrag = z;
        postInvalidate();
    }

    public void SetOsdState(boolean z) {
        this.mbOsd = z;
        postInvalidate();
    }

    public void SetMax(boolean z) {
        this.mbMax = z;
        postInvalidate();
    }

    public void WriteIn(byte[] bArr, int i, int i2) {
        if (bArr != null && i != 0 && i2 != 0) {
            int i3 = i * i2 * 2;
            byte[] bArr2 = this.mPixel;
            if (bArr2 == null) {
                byte[] bArr3 = new byte[i3];
                this.mPixel = bArr3;
                this.buffer = ByteBuffer.wrap(bArr3);
            } else if (bArr2.length < i3) {
                byte[] bArr4 = new byte[i3];
                this.mPixel = bArr4;
                this.buffer = ByteBuffer.wrap(bArr4);
            }
            if (!(this.mWidth == i && this.mHeight == i2)) {
                this.mWidth = i;
                this.mHeight = i2;
            }
            if (bArr.length != 0) {
                try {
                    System.arraycopy(bArr, 0, this.mPixel, 0, i3);
                } catch (IndexOutOfBoundsException unused) {
                }
                postInvalidate();
            }
        }
    }

    public void DeDraw(Canvas canvas) {
        int i;
        int i2 = this.mWidth;
        if (i2 == 0 || (i = this.mHeight) == 0) {
            DoPaint(canvas);
            return;
        }
        Bitmap bitmap = this.VideoBlt;
        if (bitmap == null) {
            this.VideoBlt = Bitmap.createBitmap(i2, i, Bitmap.Config.RGB_565);
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
            float width = ((float) (getWidth() - 2)) / ((float) this.mWidth);
            float height = ((float) (getHeight() - 2)) / ((float) this.mHeight);
            float f = this.mScales;
            float f2 = width * f;
            float f3 = f * height;
            float f4 = this.mTranslate.x / f2;
            float f5 = this.mTranslate.y / f3;
            if (f2 > width) {
                pointF.x = (((f2 - width) * ((float) this.mWidth)) / (f2 * 2.0f)) + f4;
                pointF.y = (((f3 - height) * ((float) this.mHeight)) / (2.0f * f3)) + f5;
            } else {
                pointF.x = (((width - f2) * ((float) this.mWidth)) / (f2 * 2.0f)) + f4;
                pointF.y = (((height - f3) * ((float) this.mHeight)) / (2.0f * f3)) + f5;
            }
            matrix.postScale(f2, f3, pointF.x, pointF.y);
        }
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawBitmap(this.VideoBlt, matrix, (Paint) null);
        DoPaint(canvas);
    }

    public void SetDigitalZoomIn(float f) {
        float f2 = this.mScales;
        if (f2 * f > 0.0f) {
            this.mScales = f2 * f;
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

    public void SetDigitalTranslate(float f, float f2) {
        if (this.mTranslate == null) {
            this.mTranslate = new PointF();
        }
        this.mTranslate.offset(f, f2);
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
