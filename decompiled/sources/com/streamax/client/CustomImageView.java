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

    public CustomImageView(Context context, int i) {
        super(context);
        this.mSerial = i;
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

    public void setBkColor(int i) {
        this.mBkColor = i;
    }

    public void setFocusState(boolean z) {
        this.mbFocus = z;
        postInvalidate();
    }

    public void setDragState(boolean z) {
        this.mbDrag = z;
        postInvalidate();
    }

    public void setOsdState(boolean z) {
        this.mbOsd = z;
        postInvalidate();
    }

    public void setMax(boolean z) {
        this.mbMax = z;
        postInvalidate();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:16|17|18|19|20|21|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeIn(byte[] r5, int r6, int r7) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0048
            if (r6 <= 0) goto L_0x0048
            if (r7 > 0) goto L_0x0007
            goto L_0x0048
        L_0x0007:
            int r0 = r6 * r7
            int r0 = r0 * 2
            com.streamax.client.CustomImageView$OneFrame r1 = r4.mOneFrame
            monitor-enter(r1)
            com.streamax.client.CustomImageView$OneFrame r2 = r4.mOneFrame     // Catch:{ all -> 0x0045 }
            byte[] r2 = r2.data     // Catch:{ all -> 0x0045 }
            if (r2 != 0) goto L_0x001b
            com.streamax.client.CustomImageView$OneFrame r2 = r4.mOneFrame     // Catch:{ all -> 0x0045 }
            byte[] r3 = new byte[r0]     // Catch:{ all -> 0x0045 }
            r2.data = r3     // Catch:{ all -> 0x0045 }
            goto L_0x0028
        L_0x001b:
            com.streamax.client.CustomImageView$OneFrame r2 = r4.mOneFrame     // Catch:{ all -> 0x0045 }
            byte[] r2 = r2.data     // Catch:{ all -> 0x0045 }
            int r2 = r2.length     // Catch:{ all -> 0x0045 }
            if (r2 >= r0) goto L_0x0028
            com.streamax.client.CustomImageView$OneFrame r2 = r4.mOneFrame     // Catch:{ all -> 0x0045 }
            byte[] r3 = new byte[r0]     // Catch:{ all -> 0x0045 }
            r2.data = r3     // Catch:{ all -> 0x0045 }
        L_0x0028:
            com.streamax.client.CustomImageView$OneFrame r2 = r4.mOneFrame     // Catch:{ all -> 0x0045 }
            byte[] r2 = r2.data     // Catch:{ all -> 0x0045 }
            if (r2 != 0) goto L_0x0030
            monitor-exit(r1)     // Catch:{ all -> 0x0045 }
            return
        L_0x0030:
            com.streamax.client.CustomImageView$OneFrame r2 = r4.mOneFrame     // Catch:{ IndexOutOfBoundsException -> 0x0038 }
            byte[] r2 = r2.data     // Catch:{ IndexOutOfBoundsException -> 0x0038 }
            r3 = 0
            java.lang.System.arraycopy(r5, r3, r2, r3, r0)     // Catch:{ IndexOutOfBoundsException -> 0x0038 }
        L_0x0038:
            com.streamax.client.CustomImageView$OneFrame r5 = r4.mOneFrame     // Catch:{ all -> 0x0045 }
            r5.width = r6     // Catch:{ all -> 0x0045 }
            com.streamax.client.CustomImageView$OneFrame r5 = r4.mOneFrame     // Catch:{ all -> 0x0045 }
            r5.height = r7     // Catch:{ all -> 0x0045 }
            monitor-exit(r1)     // Catch:{ all -> 0x0045 }
            r4.postInvalidate()
            return
        L_0x0045:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0045 }
            throw r5
        L_0x0048:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.streamax.client.CustomImageView.writeIn(byte[], int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0075, code lost:
        if (r9.mMatrix != null) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0077, code lost:
        r9.mMatrix = new android.graphics.Matrix();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0080, code lost:
        if (r9.mPoint != null) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0082, code lost:
        r9.mPoint = new android.graphics.PointF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008e, code lost:
        if (r9.mScales < 0.0f) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0090, code lost:
        r0 = ((float) (getWidth() - 2)) / ((float) r9.mWidth);
        r1 = ((float) (getHeight() - 2)) / ((float) r9.mHeight);
        r2 = r9.mScales;
        r3 = r0 * r2;
        r2 = r2 * r1;
        r4 = r9.mTranslate.x / r3;
        r5 = r9.mTranslate.y / r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ba, code lost:
        if (r3 <= r0) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bc, code lost:
        r9.mPoint.x = (((r3 - r0) * ((float) r9.mWidth)) / (r3 * 2.0f)) + r4;
        r9.mPoint.y = (((r2 - r1) * ((float) r9.mHeight)) / (2.0f * r2)) + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00db, code lost:
        r9.mPoint.x = (((r0 - r3) * ((float) r9.mWidth)) / (r3 * 2.0f)) + r4;
        r9.mPoint.y = (((r1 - r2) * ((float) r9.mHeight)) / (2.0f * r2)) + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f7, code lost:
        r9.mMatrix.setScale(r3, r2, r9.mPoint.x, r9.mPoint.y);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0104, code lost:
        r10.drawColor(r9.mBkColor);
        r0 = r9.mVideoBit;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x010b, code lost:
        if (r0 == null) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x010d, code lost:
        r10.drawBitmap(r0, r9.mMatrix, (android.graphics.Paint) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0113, code lost:
        doPaint(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0116, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
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

    public void setDigitalZoomIn(float f) {
        float f2 = this.mScales;
        if (f2 * f > 0.0f) {
            this.mScales = f2 * f;
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

    public void setDigitalTranslate(float f, float f2) {
        if (this.mTranslate == null) {
            this.mTranslate = new PointF();
        }
        this.mTranslate.offset(f, f2);
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
