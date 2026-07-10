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

    public void setAdas(boolean enabled) {
        this.mbAdas = Boolean.valueOf(enabled);
        invalidate();
    }

    public void setVideoInfo(int videoWidth, int videoHeight) {
        if (videoWidth > 0 && videoHeight > 0) {
            this.mVideoWidth = videoWidth;
            this.mVideoHeight = videoHeight;
        }
    }

    public void setAdasHorizon(int horizon) {
        this.mAdasHorizon = horizon;
        invalidate();
    }

    public void setAdasVertical(int vertical) {
        this.mAdasVertical = vertical;
        invalidate();
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
        Log.e("mode", "" + this.mode);
        if (this.mode == 2) {
            Log.e("mScales", "" + this.mScales);
            float previousScale = this.mScales;
            this.mScalesRecode = previousScale;
            this.mScales = previousScale * scaleFactor;
            Log.e("setDigitalZoomIn", "setDigitalZoomIn");
            this.left = getLeft();
            this.top = getTop();
            this.bottom = getBottom();
            this.right = getRight();
            Log.e("scale", "" + scaleFactor);
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
                        int screenWidthValue = this.screenWidth;
                        this.left = (int) (FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE - (((double) screenWidthValue) * 1.5d));
                        this.right = (int) (((double) screenWidthValue) * 2.5d);
                        int fatherHeightValue = this.fatherView_H;
                        this.top = (int) (FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE - (((double) fatherHeightValue) * 1.5d));
                        this.bottom = (int) (((double) fatherHeightValue) * 2.5d);
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
                    float maxScale = this.mScalesMax;
                    if (maxScale > 1.0f) {
                        float newMaxScale = maxScale * scaleFactor;
                        this.mScalesMax = newMaxScale;
                        this.mScales = newMaxScale;
                        this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScales - 1.0f))) / 40.0d));
                    } else {
                        float recordedMaxScale = this.mScalesRecodeMax;
                        if (recordedMaxScale > 1.0f) {
                            this.mScalesMax = 1.0f;
                            float newRecordedMaxScale = recordedMaxScale * scaleFactor;
                            this.mScalesRecodeMax = newRecordedMaxScale;
                            this.mScales = newRecordedMaxScale;
                            this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScalesRecodeMax - 1.0f))) / 40.0d));
                        } else {
                            float scaleForLength = this.mScales;
                            this.length = (float) ((int) (((double) (((float) getHeight()) * (scaleForLength - 1.0f))) / 40.0d));
                            this.mScalesRecodeMax = scaleForLength;
                        }
                    }
                    int newLeft = this.left - 20;
                    this.left = newLeft;
                    int newRight = this.right + 20;
                    this.right = newRight;
                    int newBottom = this.bottom + 20;
                    this.bottom = newBottom;
                    int newTop = this.top - 20;
                    this.top = newTop;
                    setPosition(newLeft, newTop, newRight, newBottom);
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
                    int currentRight = this.right;
                    int screenWidthLimit = this.screenWidth;
                    if (currentRight <= screenWidthLimit) {
                        this.right = screenWidthLimit;
                    }
                    if (this.top >= 0) {
                        this.top = 0;
                    }
                    int currentBottom = this.bottom;
                    int fatherHeightLimit = this.fatherView_H;
                    if (currentBottom <= fatherHeightLimit) {
                        this.bottom = fatherHeightLimit;
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
                float maxScale = this.mScalesMax;
                if (maxScale > 1.0f) {
                    float newMaxScale = maxScale * scaleFactor;
                    this.mScalesMax = newMaxScale;
                    this.mScales = newMaxScale;
                    this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScalesMax - 1.0f))) / 40.0d));
                } else {
                    float recordedMaxScale = this.mScalesRecodeMax;
                    if (recordedMaxScale > 1.0f) {
                        float newRecordedMaxScale = recordedMaxScale * scaleFactor;
                        this.mScalesRecodeMax = newRecordedMaxScale;
                        this.mScales = newRecordedMaxScale;
                        this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScalesRecodeMax - 1.0f))) / 40.0d));
                    } else {
                        this.length = (float) ((int) (((double) (((float) getHeight()) * (this.mScales - 1.0f))) / 40.0d));
                    }
                }
                int newLeft = this.left + 20;
                this.left = newLeft;
                int newRight = this.right - 20;
                this.right = newRight;
                int newBottom = this.bottom - 20;
                this.bottom = newBottom;
                int newTop = this.top + 20;
                this.top = newTop;
                if (newLeft >= 0) {
                    this.left = 0;
                }
                int screenWidthLimit = this.screenWidth;
                if (newRight <= screenWidthLimit) {
                    this.right = screenWidthLimit;
                }
                if (newTop >= 0) {
                    this.top = 0;
                }
                int fatherHeightLimit = this.fatherView_H;
                if (newBottom <= fatherHeightLimit) {
                    this.bottom = fatherHeightLimit;
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

    public void setDigitalTranslate(float deltaX, float deltaY) {
        if (this.mTranslate == null) {
            this.mTranslate = new PointF();
        }
        this.mTranslate.offset(deltaX, deltaY);
        invalidate();
    }

    public void drawAdas(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        float widthFloat = (float) width;
        float centerX = widthFloat / 2.0f;
        float heightFloat = (float) height;
        float centerY = heightFloat / 2.0f;
        int crossLine = this.mCrossLine;
        canvas.drawLine(centerX - ((float) crossLine), centerY, centerX + ((float) crossLine), centerY, this.mGreenPaint);
        int crossLineVertical = this.mCrossLine;
        canvas.drawLine(centerX, centerY - ((float) crossLineVertical), centerX, centerY + ((float) crossLineVertical), this.mGreenPaint);
        float firstGuideY = heightFloat / 3.0f;
        float maxX = (float) (width - 1);
        canvas.drawLine(1.0f, firstGuideY, maxX, firstGuideY, this.mYellowPaint);
        float secondGuideY = (2.0f * heightFloat) / 3.0f;
        canvas.drawLine(1.0f, secondGuideY, maxX, secondGuideY, this.mYellowPaint);
        int adasVertical = this.mAdasVertical;
        int videoWidth = this.mVideoWidth;
        if (adasVertical >= 0 && adasVertical <= videoWidth) {
            float adasVerticalX = (((float) adasVertical) / ((float) videoWidth)) * widthFloat;
            canvas.drawLine(adasVerticalX, 1.0f, adasVerticalX, (float) (height - 1), this.mBluePaint);
        }
        int adasHorizon = this.mAdasHorizon;
        int videoHeight = this.mVideoHeight;
        if (adasHorizon >= 0 && adasHorizon <= videoHeight) {
            float adasHorizonY = (((float) adasHorizon) / ((float) videoHeight)) * heightFloat;
            canvas.drawLine(1.0f, adasHorizonY, maxX, adasHorizonY, this.mRedPaint);
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
        if (this.mode == MODE_DRAG) {
            int currentLeft = getLeft();
            int currentRight = getRight();
            int currentTop = getTop();
            int currentBottom = getBottom();
            this.distanceX = (int) (motionEvent.getRawX() - ((float) this.current_x));
            this.distanceY = (int) (motionEvent.getRawY() - ((float) this.current_y));
            Log.e("distanceX", "" + this.distanceX);
            Log.e("distanceY", "" + this.distanceY);
            Log.e("touchSlop", "" + touchSlop);
            Log.e("getDistance", "" + getDistance((float) this.distanceX, (float) this.distanceY));
            if (touchSlop <= getDistance((float) this.distanceX, (float) this.distanceY)) {
                int horizontalDelta = this.distanceX;
                int nextLeft = currentLeft + horizontalDelta;
                int nextRight = currentRight + horizontalDelta;
                int verticalDelta = this.distanceY;
                int nextBottom = currentBottom + verticalDelta;
                int nextTop = currentTop + verticalDelta;
                int boundedTop = 0;
                if (this.isControl_Horizal) {
                    if (nextLeft >= 0) {
                        nextRight = getWidth();
                        nextLeft = 0;
                    }
                    int screenWidthLimit = this.screenWidth;
                    if (nextRight <= screenWidthLimit) {
                        nextLeft = screenWidthLimit - getWidth();
                        nextRight = this.screenWidth;
                    }
                } else {
                    nextLeft = getLeft();
                    nextRight = getRight();
                }
                if (this.isControl_Vertical) {
                    if (nextTop > 0) {
                        nextBottom = getHeight();
                        nextTop = 0;
                    }
                    int startBottom = this.start_Bottom;
                    if (nextBottom <= startBottom) {
                        nextTop = this.fatherView_H - getWidth();
                        nextBottom = startBottom;
                    }
                } else {
                    nextTop = getTop();
                    nextBottom = getBottom();
                }
                if (this.isControl_Horizal || this.isControl_Vertical) {
                    if (nextLeft >= 0) {
                        nextLeft = 0;
                    }
                    int screenWidthLimit = this.screenWidth;
                    if (nextRight <= screenWidthLimit) {
                        nextRight = screenWidthLimit;
                    }
                    if (nextTop < 0) {
                        boundedTop = nextTop;
                    }
                    int fatherHeight = this.fatherView_H;
                    if (nextBottom <= fatherHeight) {
                        nextBottom = fatherHeight;
                    }
                    setPosition(nextLeft, boundedTop, nextRight, nextBottom);
                    Log.e("isControl_Horizal", "" + this.isControl_Horizal);
                    Log.e("isControl_Vertical", "" + this.isControl_Vertical);
                    Log.e("setPosition", nextLeft + "," + boundedTop + "," + nextRight + "," + nextBottom);
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
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("onLayout", "onLayout");
    }

    private void setPosition(int left, int top, int right, int bottom) {
        layout(left, top, right, bottom);
    }

    public void setFatherW_H(int fatherWidth, int fatherHeight) {
        this.fatherView_W = fatherWidth;
        this.fatherView_H = fatherHeight;
    }

    private float getDistance(float deltaX, float deltaY) {
        return (float) Math.sqrt((double) ((deltaX * deltaX) + (deltaY * deltaY)));
    }

    public void setFatherW_H(int startLeft, int startTop, int startRight, int startBottom, int initialWidth, int initialHeight) {
        if (this.start_Top == -1) {
            this.fatherView_W = startTop;
            this.fatherView_H = startBottom;
            this.start_Top = startTop;
            this.start_Left = startLeft;
            this.start_Right = startRight;
            this.start_Bottom = startBottom;
            this.initViewWidth = initialWidth;
            this.initViewHeight = initialHeight;
        }
    }
}
