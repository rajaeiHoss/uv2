package com.Zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.Zxing.camera.CameraManager;
import com.google.zxing.ResultPoint;
import com.zycs.UView.R;
import java.util.Collection;
import java.util.HashSet;
import org.kxml2.wap.Wbxml;

public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 100;
    private static final int CORNER_WIDTH = 10;
    private static final int OPAQUE = 255;
    private static final int[] SCANNER_ALPHA = {0, 64, 128, Wbxml.EXT_0, 255, Wbxml.EXT_0, 128, 64};
    private static float density;
    private int ScreenRate;
    private int count = 0;
    private final int frameColor;
    private final int laserColor;
    private Collection<ResultPoint> lastPossibleResultPoints;
    private final int maskColor;
    private final Paint paint = new Paint();
    private Collection<ResultPoint> possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultColor;
    private final int resultPointColor;
    private int scannerAlpha;

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        this.maskColor = resources.getColor(R.color.viewfinder_mask);
        this.resultColor = resources.getColor(R.color.result_view);
        this.frameColor = resources.getColor(R.color.viewfinder_frame);
        this.laserColor = resources.getColor(R.color.viewfinder_laser);
        this.resultPointColor = resources.getColor(R.color.possible_result_points);
        this.scannerAlpha = 0;
        this.possibleResultPoints = new HashSet(5);
        float f = context.getResources().getDisplayMetrics().density;
        density = f;
        this.ScreenRate = (int) (f * 20.0f);
    }

    public void onDraw(Canvas canvas) {
        Rect framingRect;
        if (!isInEditMode() && (framingRect = CameraManager.get().getFramingRect()) != null) {
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
            float f = (float) width;
            canvas.drawRect(0.0f, 0.0f, f, (float) framingRect.top, this.paint);
            canvas.drawRect(0.0f, (float) framingRect.top, (float) framingRect.left, (float) (framingRect.bottom + 1), this.paint);
            float f2 = f;
            canvas.drawRect((float) (framingRect.right + 1), (float) framingRect.top, f2, (float) (framingRect.bottom + 1), this.paint);
            canvas.drawRect(0.0f, (float) (framingRect.bottom + 1), f2, (float) height, this.paint);
            if (this.resultBitmap != null) {
                this.paint.setAlpha(255);
                canvas.drawBitmap(this.resultBitmap, (float) framingRect.left, (float) framingRect.top, this.paint);
                return;
            }
            this.paint.setColor(this.frameColor);
            canvas.drawRect((float) framingRect.left, (float) framingRect.top, (float) (framingRect.right + 1), (float) (framingRect.top + 2), this.paint);
            canvas.drawRect((float) framingRect.left, (float) (framingRect.top + 2), (float) (framingRect.left + 2), (float) (framingRect.bottom - 1), this.paint);
            canvas.drawRect((float) (framingRect.right - 1), (float) framingRect.top, (float) (framingRect.right + 1), (float) (framingRect.bottom - 1), this.paint);
            canvas.drawRect((float) framingRect.left, (float) (framingRect.bottom - 1), (float) (framingRect.right + 1), (float) (framingRect.bottom + 1), this.paint);
            drawAngle(canvas, framingRect);
            drawLaser(canvas, framingRect);
            Collection<ResultPoint> collection = this.possibleResultPoints;
            Collection<ResultPoint> collection2 = this.lastPossibleResultPoints;
            if (collection.isEmpty()) {
                this.lastPossibleResultPoints = null;
            } else {
                this.possibleResultPoints = new HashSet(5);
                this.lastPossibleResultPoints = collection;
                this.paint.setAlpha(255);
                this.paint.setColor(this.resultPointColor);
                for (ResultPoint next : collection) {
                    canvas.drawCircle(((float) framingRect.left) + next.getX(), ((float) framingRect.top) + next.getY(), 6.0f, this.paint);
                }
            }
            if (collection2 != null) {
                this.paint.setAlpha(127);
                this.paint.setColor(this.resultPointColor);
                for (ResultPoint next2 : collection2) {
                    canvas.drawCircle(((float) framingRect.left) + next2.getX(), ((float) framingRect.top) + next2.getY(), 3.0f, this.paint);
                }
            }
            postInvalidateDelayed(ANIMATION_DELAY, framingRect.left, framingRect.top, framingRect.right, framingRect.bottom);
        }
    }

    private void drawAngle(Canvas canvas, Rect rect) {
        Rect rect2 = rect;
        this.paint.setColor(this.laserColor);
        this.paint.setAlpha(255);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(10.0f);
        int i = rect2.left;
        int i2 = rect2.top;
        int i3 = rect2.right;
        int i4 = rect2.bottom;
        float f = (float) i;
        float f2 = (float) (i2 + 10);
        Canvas canvas2 = canvas;
        float f3 = f;
        float f4 = (float) i2;
        canvas2.drawRect(f3, f4, (float) (this.ScreenRate + i), f2, this.paint);
        float f5 = (float) (i + 10);
        canvas.drawRect(f3, f4, f5, (float) (this.ScreenRate + i2), this.paint);
        float f6 = (float) i3;
        float f7 = f6;
        canvas.drawRect((float) (i3 - this.ScreenRate), f4, f7, f2, this.paint);
        float f8 = (float) (i3 - 10);
        Canvas canvas3 = canvas;
        canvas3.drawRect(f8, f4, f7, (float) (i2 + this.ScreenRate), this.paint);
        float f9 = (float) i4;
        float f10 = f;
        float f11 = f9;
        canvas3.drawRect(f10, (float) (i4 - this.ScreenRate), f5, f11, this.paint);
        float f12 = (float) (i4 - 10);
        canvas.drawRect(f10, f12, (float) (i + this.ScreenRate), f11, this.paint);
        float f13 = f6;
        Canvas canvas4 = canvas;
        float f14 = f13;
        float f15 = f9;
        canvas4.drawRect((float) (i3 - this.ScreenRate), f12, f14, f15, this.paint);
        canvas4.drawRect(f8, (float) (i4 - this.ScreenRate), f14, f15, this.paint);
    }

    private void drawLaser(Canvas canvas, Rect rect) {
        this.paint.setColor(this.laserColor);
        Paint paint2 = this.paint;
        int[] iArr = SCANNER_ALPHA;
        paint2.setAlpha(iArr[this.scannerAlpha]);
        this.scannerAlpha = (this.scannerAlpha + 1) % iArr.length;
        int height = (this.count % rect.height()) + rect.top;
        canvas.drawRect((float) (rect.left + 2), (float) (height - 1), (float) (rect.right - 1), (float) (height + 2), this.paint);
        this.count += 10;
    }

    public void drawViewfinder() {
        this.resultBitmap = null;
        invalidate();
    }

    public void drawResultBitmap(Bitmap bitmap) {
        this.resultBitmap = bitmap;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint resultPoint) {
        this.possibleResultPoints.add(resultPoint);
    }
}
