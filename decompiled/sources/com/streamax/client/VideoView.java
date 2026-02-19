package com.streamax.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.streamax.utils.ScreenUtils;
import org.xbill.DNS.SimpleResolver;

// Zoom/pan video render view for a single stream.
public class VideoView extends ImageView {
    private static final int MODE_DOUBLE_CLICK = 3;
    private static final int MODE_DRAG = 1;
    private static final int MODE_ZOOM = 2;
    private static final float SCALE_MAX = 4.0f;
    public static final String TAG = "MyGesture";
    private static float touchSlop;
    private int View_Height = 0;
    private int View_Width = 0;
    private int bottom = 0;
    private int current_x;
    private int current_y;
    int distanceX = 0;
    int distanceY = 0;
    private int fatherBottom;
    private int fatherTop;
    private int fatherView_H;
    private int fatherView_W;
    private long firstTime = 0;
    private int initViewHeight = 0;
    private int initViewWidth = 0;
    private boolean isControl_Horizal = false;
    private boolean isControl_Vertical = false;
    private int left = 0;
    private float length = 0.0f;
    private int mAdasHorizon = 0;
    private int mAdasVertical = 0;
    private int mBkColor = ViewCompat.MEASURED_STATE_MASK;
    private Paint mBluePaint;
    public SurfaceCallBackInterface mCallBackInterface;
    private int mCrossLine = 50;
    GestureDetector mGestureDetector = null;
    private Paint mGreenPaint;
    private int mHeight = 0;
    private int mLineWidth = 2;
    private Matrix mMatrix;
    public OneFrame mOneFrame;
    private Paint mPaint;
    private PointF mPoint;
    private Paint mRedPaint;
    private float mScales = 1.0f;
    private float mScalesMax = 1.0f;
    private float mScalesRecode = 1.0f;
    private float mScalesRecodeMax = 1.0f;
    private int mSerial = 0;
    private SurfaceHolder mSurfaceHolder;
    private PointF mTranslate = new PointF(0.0f, 0.0f);
    public Bitmap mVideoBit;
    private int mVideoHeight = 720;
    private int mVideoWidth = SimpleResolver.DEFAULT_EDNS_PAYLOADSIZE;
    public int mView = 0;
    private int mWidth = 0;
    private Paint mYellowPaint;
    private Boolean mbAdas = false;
    private boolean mbDrag = false;
    private boolean mbFocus = false;
    private boolean mbMax = false;
    private boolean mbOsd = true;
    private int mode = 0;
    private float ratio = 0.3f;
    private int right = 0;
    ScaleGestureDetector scaleGestureDetector = null;
    private int screenHeight = 0;
    private int screenWidth = 0;
    private int start_Bottom = -1;
    private int start_Left = -1;
    private int start_Right = -1;
    private int start_Top = -1;
    private int start_x;
    private int start_y;
    private int top = 0;
    View view;

    public void setCallBack(SurfaceCallBackInterface surfaceCallBackInterface, int i) {
        this.mCallBackInterface = surfaceCallBackInterface;
        this.mView = i;
    }

    public class OneFrame {
        public byte[] data;
        public int height;
        public int width;

        public OneFrame() {
        }
    }

    public VideoView(Context context, int i) {
        super(context);
        init();
        this.mSerial = i;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
        setScaleType(ImageView.ScaleType.CENTER);
        this.mOneFrame = new OneFrame();
        initAdasPaint();
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(1.0f);
        this.mOneFrame = new OneFrame();
        initAdasPaint();
    }

    private void initAdasPaint() {
        Paint paint = new Paint();
        this.mGreenPaint = paint;
        paint.setAntiAlias(true);
        this.mGreenPaint.setColor(-16711936);
        this.mGreenPaint.setStyle(Paint.Style.STROKE);
        this.mGreenPaint.setStrokeWidth((float) this.mLineWidth);
        Paint paint2 = new Paint();
        this.mYellowPaint = paint2;
        paint2.setAntiAlias(true);
        this.mYellowPaint.setColor(InputDeviceCompat.SOURCE_ANY);
        this.mYellowPaint.setStyle(Paint.Style.STROKE);
        this.mYellowPaint.setStrokeWidth((float) this.mLineWidth);
        Paint paint3 = new Paint();
        this.mBluePaint = paint3;
        paint3.setAntiAlias(true);
        this.mBluePaint.setColor(-16776961);
        this.mBluePaint.setStyle(Paint.Style.STROKE);
        this.mBluePaint.setPathEffect(new DashPathEffect(new float[]{10.0f, 10.0f}, 0.0f));
        this.mBluePaint.setStrokeWidth((float) this.mLineWidth);
        Paint paint4 = new Paint();
        this.mRedPaint = paint4;
        paint4.setAntiAlias(true);
        this.mRedPaint.setColor(SupportMenu.CATEGORY_MASK);
        this.mRedPaint.setStyle(Paint.Style.STROKE);
        this.mRedPaint.setPathEffect(new DashPathEffect(new float[]{10.0f, 10.0f}, 0.0f));
        this.mRedPaint.setStrokeWidth((float) this.mLineWidth);
    }

    public void setAdas(boolean z) {
        this.mbAdas = Boolean.valueOf(z);
        invalidate();
    }

    public void setVideoInfo(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.mVideoWidth = i;
            this.mVideoHeight = i2;
        }
    }

    public void setAdasHorizon(int i) {
        this.mAdasHorizon = i;
        invalidate();
    }

    public void setAdasVertical(int i) {
        this.mAdasVertical = i;
        invalidate();
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
    public void writeIn(byte[] data, int width, int height) {
        if (data == null || width <= 0 || height <= 0) {
            return;
        }
        int size = width * height * 2;
        if (data.length < size) {
            return;
        }
        synchronized (this.mOneFrame) {
            if (this.mOneFrame.data == null || this.mOneFrame.data.length < size) {
                this.mOneFrame.data = new byte[size];
            }
            if (this.mOneFrame.data == null) {
                return;
            }
            try {
                System.arraycopy(data, 0, this.mOneFrame.data, 0, size);
            } catch (IndexOutOfBoundsException unused) {
                return;
            }
            this.mOneFrame.width = width;
            this.mOneFrame.height = height;
        }
        postInvalidate();
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
        float f2 = f;
        Log.e("mode", "" + this.mode);
        if (this.mode == 2) {
            Log.e("mScales", "" + this.mScales);
            float f3 = this.mScales;
            this.mScalesRecode = f3;
            this.mScales = f3 * f2;
            Log.e("setDigitalZoomIn", "setDigitalZoomIn");
            this.left = getLeft();
            this.top = getTop();
            this.bottom = getBottom();
            this.right = getRight();
            Log.e("scale", "" + f2);
            Log.e("mScales", "" + this.mScales);
            Log.e("mScalesRecode", "" + this.mScalesRecode);
            Log.e("getWidth()", "" + getWidth());
            Log.e("screenWidth", "" + this.screenWidth);
            Log.e("getHeight()", "" + getHeight());
            Log.e("fatherView_H", "" + this.fatherView_H);
            if (this.mScales > 0.0f) {
                Log.e("mScales > 1", "getWidth():" + getWidth() + ",screenWidth * 4:" + (this.screenWidth * 4));
                if (this.mScales > this.mScalesRecode) {
                    Log.e("mScales>mScalesRecode", "yes");
                    if (getWidth() > this.screenWidth * 4 || getHeight() > this.fatherView_H * 4) {
                        this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScales - 1.0f))) / 40.0d));
                        int i = this.screenWidth;
                        this.left = (int) (FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE - (((double) i) * 1.5d));
                        this.right = (int) (((double) i) * 2.5d);
                        int i2 = this.fatherView_H;
                        this.top = (int) (FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE - (((double) i2) * 1.5d));
                        this.bottom = (int) (((double) i2) * 2.5d);
                        if (this.mScalesMax <= 1.0f) {
                            this.mScalesMax = this.mScalesRecodeMax;
                        }
                        Log.e("getWidth() > (screenWidth * 4)", "left:" + this.left + ",top:" + this.top + ",right:" + this.right + ",bottom:" + this.bottom);
                        StringBuilder sb = new StringBuilder();
                        sb.append("mScalesMax:");
                        sb.append(this.mScalesMax);
                        sb.append(",mScalesRecodeMax:");
                        sb.append(this.mScalesRecodeMax);
                        sb.append(",mScales:");
                        sb.append(this.mScales);
                        Log.e("mScales > 1", sb.toString());
                        return;
                    }
                    float f4 = this.mScalesMax;
                    if (f4 > 1.0f) {
                        float f5 = f4 * f2;
                        this.mScalesMax = f5;
                        this.mScales = f5;
                        this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScales - 1.0f))) / 40.0d));
                    } else {
                        float f6 = this.mScalesRecodeMax;
                        if (f6 > 1.0f) {
                            this.mScalesMax = 1.0f;
                            float f7 = f6 * f2;
                            this.mScalesRecodeMax = f7;
                            this.mScales = f7;
                            this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScalesRecodeMax - 1.0f))) / 40.0d));
                        } else {
                            float f8 = this.mScales;
                            this.length = (float) ((int) (((double) (((float) getHeight()) * (f8 - 1.0f))) / 40.0d));
                            this.mScalesRecodeMax = f8;
                        }
                    }
                    int i3 = this.left - 20;
                    this.left = i3;
                    int i4 = this.right + 20;
                    this.right = i4;
                    int i5 = this.bottom + 20;
                    this.bottom = i5;
                    int i6 = this.top - 20;
                    this.top = i6;
                    setPosition(i3, i6, i4, i5);
                    Log.e("mScales > 1", "length:" + this.length + ",left:" + this.left + ",top:" + this.top + ",right:" + this.right + ",bottom:" + this.bottom);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("mScalesMax:");
                    sb2.append(this.mScalesMax);
                    sb2.append(",mScalesRecodeMax:");
                    sb2.append(this.mScalesRecodeMax);
                    sb2.append(",mScales:");
                    sb2.append(this.mScales);
                    Log.e("mScales > 1", sb2.toString());
                    return;
                }
                Log.e("mScales<mScalesRecode", "yes");
                if (getWidth() <= this.initViewWidth || getHeight() <= this.fatherView_H) {
                    if (this.left >= 0) {
                        this.left = 0;
                    }
                    int i7 = this.right;
                    int i8 = this.screenWidth;
                    if (i7 <= i8) {
                        this.right = i8;
                    }
                    if (this.top >= 0) {
                        this.top = 0;
                    }
                    int i9 = this.bottom;
                    int i10 = this.fatherView_H;
                    if (i9 <= i10) {
                        this.bottom = i10;
                    }
                    setPosition(this.left, this.top, this.right, this.bottom);
                    Log.e("getWidth() < screenWidth", "left:" + this.left + ",top:" + this.top + ",right:" + this.right + ",bottom:" + this.bottom);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("mScalesMax:");
                    sb3.append(this.mScalesMax);
                    sb3.append(",mScalesRecodeMax:");
                    sb3.append(this.mScalesRecodeMax);
                    sb3.append(",mScales:");
                    sb3.append(this.mScales);
                    Log.e("mScales <= 1", sb3.toString());
                    return;
                }
                Log.e("mScalesMax", "" + this.mScalesMax);
                float f9 = this.mScalesMax;
                if (f9 > 1.0f) {
                    float f10 = f9 * f2;
                    this.mScalesMax = f10;
                    this.mScales = f10;
                    this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScalesMax - 1.0f))) / 40.0d));
                } else {
                    float f11 = this.mScalesRecodeMax;
                    if (f11 > 1.0f) {
                        float f12 = f11 * f2;
                        this.mScalesRecodeMax = f12;
                        this.mScales = f12;
                        this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScalesRecodeMax - 1.0f))) / 40.0d));
                    } else {
                        this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScales - 1.0f))) / 40.0d));
                    }
                }
                int i11 = this.left + 20;
                this.left = i11;
                int i12 = this.right - 20;
                this.right = i12;
                int i13 = this.bottom - 20;
                this.bottom = i13;
                int i14 = this.top + 20;
                this.top = i14;
                if (i11 >= 0) {
                    this.left = 0;
                }
                int i15 = this.screenWidth;
                if (i12 <= i15) {
                    this.right = i15;
                }
                if (i14 >= 0) {
                    this.top = 0;
                }
                int i16 = this.fatherView_H;
                if (i13 <= i16) {
                    this.bottom = i16;
                }
                setPosition(this.left, this.top, this.right, this.bottom);
                Log.e("mScales <= 1", "length:" + this.length + ",left:" + this.left + ",top:" + this.top + ",right:" + this.right + ",bottom:" + this.bottom);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("mScalesMax:");
                sb4.append(this.mScalesMax);
                sb4.append(",mScalesRecodeMax:");
                sb4.append(this.mScalesRecodeMax);
                sb4.append(",mScales:");
                sb4.append(this.mScales);
                Log.e("mScales <= 1", sb4.toString());
                return;
            }
            this.mScalesMax = 1.0f;
            this.mScales = 1.0f;
            this.mScalesRecode = 1.0f;
            this.mScalesRecodeMax = 1.0f;
            Log.e("mScales", "mScales<1");
            Log.e("mScalesMax", "mScalesMax");
            setPosition(this.start_Left, this.start_Top, this.start_Right, this.start_Bottom);
        }
    }

    public float getDigitalScales() {
        return this.mScales;
    }

    public void resetDigitalScales() {
        this.mScales = 1.0f;
        this.mScalesMax = 1.0f;
        this.mScalesRecode = 1.0f;
        this.mScalesRecodeMax = 1.0f;
        this.mTranslate.set(0.0f, 0.0f);
        invalidate();
        setPosition(this.start_Left, this.start_Top, this.start_Right, this.start_Bottom);
        Log.e("resetDigitalScales", "resetDigitalScales");
    }

    public void setDigitalTranslate(float f, float f2) {
        if (this.mTranslate == null) {
            this.mTranslate = new PointF();
        }
        this.mTranslate.offset(f, f2);
        invalidate();
    }

    public void drawAdas(Canvas canvas) {
        int i;
        int i2;
        int width = getWidth();
        int height = getHeight();
        float f = (float) width;
        float f2 = f / 2.0f;
        float f3 = (float) height;
        float f4 = f3 / 2.0f;
        int i3 = this.mCrossLine;
        Canvas canvas2 = canvas;
        canvas2.drawLine(f2 - ((float) i3), f4, f2 + ((float) i3), f4, this.mGreenPaint);
        int i4 = this.mCrossLine;
        canvas.drawLine(f2, f4 - ((float) i4), f2, f4 + ((float) i4), this.mGreenPaint);
        float f5 = f3 / 3.0f;
        float f6 = (float) (width - 1);
        canvas2.drawLine(1.0f, f5, f6, f5, this.mYellowPaint);
        float f7 = (2.0f * f3) / 3.0f;
        canvas.drawLine(1.0f, f7, f6, f7, this.mYellowPaint);
        int i5 = this.mAdasVertical;
        if (i5 >= 0 && i5 <= (i2 = this.mVideoWidth)) {
            float f8 = (((float) i5) / ((float) i2)) * f;
            canvas.drawLine(f8, 1.0f, f8, (float) (height - 1), this.mBluePaint);
        }
        int i6 = this.mAdasHorizon;
        if (i6 >= 0 && i6 <= (i = this.mVideoHeight)) {
            float f9 = (((float) i6) / ((float) i)) * f3;
            canvas.drawLine(1.0f, f9, f6, f9, this.mRedPaint);
        }
    }

    public void doPaint(Canvas canvas) {
        if (this.mbFocus) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(-16711936);
            Rect rect = new Rect();
            rect.left = 1;
            rect.top = 1;
            rect.right = getWidth() - 1;
            rect.bottom = getHeight() - 3;
            if (!this.mbMax) {
                canvas.drawRect(rect, this.mPaint);
            }
        } else if (this.mbDrag) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
            Rect rect2 = new Rect();
            rect2.left = 1;
            rect2.top = 1;
            rect2.right = getWidth() - 1;
            rect2.bottom = getHeight() - 3;
            canvas.drawRect(rect2, this.mPaint);
        } else {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(Color.rgb(72, 72, 72));
            Rect rect3 = new Rect();
            rect3.left = 1;
            rect3.top = 1;
            rect3.right = getWidth() - 1;
            rect3.bottom = getHeight() - 3;
            canvas.drawRect(rect3, this.mPaint);
        }
        if (this.mbOsd) {
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(-1);
            this.mPaint.setTextAlign(Paint.Align.LEFT);
            this.mPaint.setTextSize(16.0f);
            canvas.drawText(Integer.toString(this.mSerial), 16.0f, 16.0f, this.mPaint);
        }
        if (this.mbAdas.booleanValue()) {
            drawAdas(canvas);
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

    private void init() {
        touchSlop = (float) ViewConfiguration.getTouchSlop();
        this.view = this;
        this.screenHeight = ScreenUtils.getScreenHeight(getContext());
        this.screenWidth = ScreenUtils.getScreenWidth(getContext());
    }

    public void onTouchMove(MotionEvent motionEvent) {
        Log.e("onTouchMove", "onTouchMove");
        if (this.mode == 1) {
            int left2 = getLeft();
            int right2 = getRight();
            int top2 = getTop();
            int bottom2 = getBottom();
            this.distanceX = (int) (motionEvent.getRawX() - ((float) this.current_x));
            this.distanceY = (int) (motionEvent.getRawY() - ((float) this.current_y));
            Log.e("distanceX", "" + this.distanceX);
            Log.e("distanceY", "" + this.distanceY);
            Log.e("touchSlop", "" + touchSlop);
            Log.e("getDistance", "" + getDistance((float) this.distanceX, (float) this.distanceY));
            if (touchSlop <= getDistance((float) this.distanceX, (float) this.distanceY)) {
                int i = this.distanceX;
                int i2 = left2 + i;
                int i3 = right2 + i;
                int i4 = this.distanceY;
                int i5 = bottom2 + i4;
                int i6 = top2 + i4;
                int i7 = 0;
                if (this.isControl_Horizal) {
                    if (i2 >= 0) {
                        i3 = getWidth();
                        i2 = 0;
                    }
                    int i8 = this.screenWidth;
                    if (i3 <= i8) {
                        i2 = i8 - getWidth();
                        i3 = this.screenWidth;
                    }
                } else {
                    i2 = getLeft();
                    i3 = getRight();
                }
                if (this.isControl_Vertical) {
                    if (i6 > 0) {
                        i5 = getHeight();
                        i6 = 0;
                    }
                    int i9 = this.start_Bottom;
                    if (i5 <= i9) {
                        i6 = this.fatherView_H - getWidth();
                        i5 = i9;
                    }
                } else {
                    i6 = getTop();
                    i5 = getBottom();
                }
                if (this.isControl_Horizal || this.isControl_Vertical) {
                    if (i2 >= 0) {
                        i2 = 0;
                    }
                    int i10 = this.screenWidth;
                    if (i3 <= i10) {
                        i3 = i10;
                    }
                    if (i6 < 0) {
                        i7 = i6;
                    }
                    int i11 = this.fatherView_H;
                    if (i5 <= i11) {
                        i5 = i11;
                    }
                    setPosition(i2, i7, i3, i5);
                    Log.e("isControl_Horizal", "" + this.isControl_Horizal);
                    Log.e("isControl_Vertical", "" + this.isControl_Vertical);
                    Log.e("setPosition", i2 + "," + i7 + "," + i3 + "," + i5);
                }
                this.current_x = (int) motionEvent.getRawX();
                this.current_y = (int) motionEvent.getRawY();
            }
        }
    }

    public void onPointerDown(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2) {
            this.mode = 2;
        }
    }

    public void onTouchDown(MotionEvent motionEvent) {
        Log.e("onTouchDown", "onTouchDown");
        this.mode = 1;
        this.start_x = (int) motionEvent.getRawX();
        this.start_y = (int) motionEvent.getRawY();
        this.current_x = (int) motionEvent.getRawX();
        this.current_y = (int) motionEvent.getRawY();
        this.View_Width = getWidth();
        int height = getHeight();
        this.View_Height = height;
        if (height > this.fatherView_H) {
            this.isControl_Vertical = true;
        } else {
            this.isControl_Vertical = false;
        }
        if (this.View_Width > this.fatherView_W) {
            this.isControl_Horizal = true;
        } else {
            this.isControl_Horizal = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        Log.e("onLayout", "onLayout");
    }

    private void setPosition(int i, int i2, int i3, int i4) {
        layout(i, i2, i3, i4);
    }

    public void setFatherW_H(int i, int i2) {
        this.fatherView_W = i;
        this.fatherView_H = i2;
    }

    private float getDistance(float f, float f2) {
        return (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
    }

    public void setFatherW_H(int i, int i2, int i3, int i4, int i5, int i6) {
        if (this.start_Top == -1) {
            this.fatherView_W = i2;
            this.fatherView_H = i4;
            this.start_Top = i2;
            this.start_Left = i;
            this.start_Right = i3;
            this.start_Bottom = i4;
            this.initViewWidth = i5;
            this.initViewHeight = i6;
        }
    }
}
