package com.streamax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import com.streamax.utils.ScreenUtils;

public class CustomSurfaceView extends SurfaceView {
    private static final int MODE_DOUBLE_CLICK = 3;
    private static final int MODE_DRAG = 1;
    private static final int MODE_ZOOM = 2;
    private static final float SCALE_MAX = 4.0f;
    public static final String TAG = "MyGesture";
    private static float touchSlop;
    private int View_Height = 0;
    private int View_Width = 0;
    private int current_x;
    private int current_y;
    int distanceX = 0;
    int distanceY = 0;
    private int fatherBottom;
    private int fatherTop;
    /* access modifiers changed from: private */
    public int fatherView_H;
    private int fatherView_W;
    private long firstTime = 0;
    ICoallBack icallBack = null;
    /* access modifiers changed from: private */
    public int initViewHeight = 0;
    /* access modifiers changed from: private */
    public int initViewWidth = 0;
    private boolean isControl_Horizal = false;
    private boolean isControl_Vertical = false;
    GestureDetector mGestureDetector = null;
    /* access modifiers changed from: private */
    public int mode = 0;
    /* access modifiers changed from: private */
    public float ratio = 0.3f;
    ScaleGestureDetector scaleGestureDetector = null;
    private int screenHeight = 0;
    /* access modifiers changed from: private */
    public int screenWidth = 0;
    /* access modifiers changed from: private */
    public int start_Bottom = -1;
    /* access modifiers changed from: private */
    public int start_Left = -1;
    /* access modifiers changed from: private */
    public int start_Right = -1;
    /* access modifiers changed from: private */
    public int start_Top = -1;
    private int start_x;
    private int start_y;
    View view;

    public interface ICoallBack {
        void getAngle(int i, int i2);
    }

    public CustomSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public CustomSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CustomSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        touchSlop = (float) ViewConfiguration.getTouchSlop();
        setFocusable(true);
        setClickable(true);
        setLongClickable(true);
        this.view = this;
        this.screenHeight = ScreenUtils.getScreenHeight(getContext());
        this.screenWidth = ScreenUtils.getScreenWidth(getContext());
        this.scaleGestureDetector = new ScaleGestureDetector(getContext(), new SimpleScaleGestureListener());
        this.mGestureDetector = new GestureDetector(new SimpleGestureListener());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mGestureDetector.onTouchEvent(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            onTouchDown(motionEvent);
        } else if (action == 1) {
            this.mode = 0;
        } else if (action == 2) {
            onTouchMove(motionEvent);
        } else if (action == 5) {
            onPointerDown(motionEvent);
        } else if (action == 6) {
            this.mode = 0;
        }
        return this.scaleGestureDetector.onTouchEvent(motionEvent);
    }

    private void onTouchMove(MotionEvent motionEvent) {
        if (this.mode == 1) {
            int left = getLeft();
            int right = getRight();
            int top = getTop();
            int bottom = getBottom();
            this.distanceX = (int) (motionEvent.getRawX() - ((float) this.current_x));
            this.distanceY = (int) (motionEvent.getRawY() - ((float) this.current_y));
            ICoallBack iCoallBack = this.icallBack;
            if (iCoallBack != null) {
                iCoallBack.getAngle((int) getX(), getWidth());
            }
            if (touchSlop <= getDistance((float) this.distanceX, (float) this.distanceY)) {
                int i = this.distanceX;
                int i2 = left + i;
                int i3 = right + i;
                int i4 = this.distanceY;
                int i5 = bottom + i4;
                int i6 = top + i4;
                if (this.isControl_Horizal) {
                    if (i2 >= 0) {
                        i3 = getWidth();
                        i2 = 0;
                    }
                    int i7 = this.screenWidth;
                    if (i3 <= i7) {
                        i2 = i7 - getWidth();
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
                    int i8 = this.start_Bottom;
                    if (i5 <= i8) {
                        i6 = this.fatherView_H - getWidth();
                        i5 = i8;
                    }
                } else {
                    i6 = getTop();
                    i5 = getBottom();
                }
                if (this.isControl_Horizal || this.isControl_Vertical) {
                    setPosition(i2, i6, i3, i5);
                }
                this.current_x = (int) motionEvent.getRawX();
                this.current_y = (int) motionEvent.getRawY();
            }
        }
    }

    private void onPointerDown(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2) {
            this.mode = 2;
        }
    }

    private void onTouchDown(MotionEvent motionEvent) {
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
        if (this.start_Top == -1) {
            this.start_Top = i2;
            this.start_Left = i;
            this.start_Right = i3;
            this.start_Bottom = i4;
            this.initViewWidth = this.view.getWidth();
            this.initViewHeight = this.view.getHeight();
        }
    }

    private class SimpleScaleGestureListener implements ScaleGestureDetector.OnScaleGestureListener {
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }

        private SimpleScaleGestureListener() {
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (CustomSurfaceView.this.mode == 2) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                int left = CustomSurfaceView.this.getLeft();
                int top = CustomSurfaceView.this.getTop();
                int bottom = CustomSurfaceView.this.getBottom();
                int right = CustomSurfaceView.this.getRight();
                if (scaleFactor > 1.0f) {
                    float height = ((float) ((int) (((double) (((float) CustomSurfaceView.this.getHeight()) * (scaleFactor - 1.0f))) / 7.0d))) / 2.0f;
                    int i = (int) (((float) left) - height);
                    int i2 = (int) (((float) right) + height);
                    int i3 = (int) (((float) bottom) + height);
                    int i4 = (int) (((float) top) - height);
                    if (CustomSurfaceView.this.getWidth() <= CustomSurfaceView.this.screenWidth * 3 && CustomSurfaceView.this.getHeight() <= CustomSurfaceView.this.fatherView_H * 3) {
                        CustomSurfaceView.this.setPosition(i, i4, i2, i3);
                    }
                } else {
                    float height2 = ((float) ((int) (((double) (((float) CustomSurfaceView.this.getHeight()) * (1.0f - scaleFactor))) / 7.0d))) / 2.0f;
                    int i5 = (int) (((float) left) + height2);
                    int i6 = (int) (((float) right) - height2);
                    int i7 = (int) (((float) bottom) - height2);
                    int i8 = (int) (((float) top) + height2);
                    if (i5 >= 0) {
                        i5 = 0;
                    }
                    if (i6 <= CustomSurfaceView.this.screenWidth) {
                        i6 = CustomSurfaceView.this.screenWidth;
                    }
                    if (i8 >= 0) {
                        i8 = 0;
                    }
                    if (i7 <= CustomSurfaceView.this.fatherView_H) {
                        i7 = CustomSurfaceView.this.fatherView_H;
                    }
                    if (CustomSurfaceView.this.getWidth() <= CustomSurfaceView.this.initViewWidth || CustomSurfaceView.this.getHeight() <= CustomSurfaceView.this.fatherView_H) {
                        CustomSurfaceView customSurfaceView = CustomSurfaceView.this;
                        customSurfaceView.setPosition(customSurfaceView.start_Left, CustomSurfaceView.this.start_Top, CustomSurfaceView.this.start_Right, CustomSurfaceView.this.start_Bottom);
                    } else {
                        CustomSurfaceView.this.setPosition(i5, i8, i6, i7);
                    }
                }
            }
            return false;
        }
    }

    private class SimpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        private SimpleGestureListener() {
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            Log.i("MyGesture", "˫����Ļ");
            CustomSurfaceView.this.getLeft();
            CustomSurfaceView.this.getTop();
            CustomSurfaceView.this.getBottom();
            CustomSurfaceView.this.getRight();
            if (CustomSurfaceView.this.getHeight() > CustomSurfaceView.this.fatherView_H) {
                Log.i("MyGesture", "��Сģʽ");
                while (CustomSurfaceView.this.getHeight() > CustomSurfaceView.this.fatherView_H) {
                    int height = ((int) (((double) (((float) CustomSurfaceView.this.getHeight()) * CustomSurfaceView.this.ratio)) / 5.0d)) / 2;
                    int left = CustomSurfaceView.this.getLeft() + height;
                    int right = CustomSurfaceView.this.getRight() - height;
                    int bottom = CustomSurfaceView.this.getBottom() - height;
                    int top = CustomSurfaceView.this.getTop() + height;
                    if (left >= 0) {
                        left = 0;
                    }
                    if (right <= CustomSurfaceView.this.screenWidth) {
                        right = CustomSurfaceView.this.screenWidth;
                    }
                    if (top >= 0) {
                        top = 0;
                    }
                    if (bottom <= CustomSurfaceView.this.fatherView_H) {
                        bottom = CustomSurfaceView.this.fatherView_H;
                    }
                    if (CustomSurfaceView.this.getWidth() <= CustomSurfaceView.this.initViewWidth || CustomSurfaceView.this.getHeight() <= CustomSurfaceView.this.fatherView_H) {
                        CustomSurfaceView customSurfaceView = CustomSurfaceView.this;
                        customSurfaceView.setPosition(customSurfaceView.start_Left, CustomSurfaceView.this.start_Top, CustomSurfaceView.this.start_Right, CustomSurfaceView.this.start_Bottom);
                    } else {
                        CustomSurfaceView.this.setPosition(left, top, right, bottom);
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
            Log.i("MyGesture", "�Ŵ�ģʽ");
            if (CustomSurfaceView.this.getHeight() > CustomSurfaceView.this.fatherView_H) {
                return true;
            }
            while (CustomSurfaceView.this.getHeight() < CustomSurfaceView.this.initViewHeight * 2) {
                int height2 = ((int) (((double) (((float) CustomSurfaceView.this.getHeight()) * CustomSurfaceView.this.ratio)) / 5.0d)) / 2;
                int left2 = CustomSurfaceView.this.getLeft() - height2;
                int right2 = CustomSurfaceView.this.getRight() + height2;
                int bottom2 = CustomSurfaceView.this.getBottom() + height2;
                int top2 = CustomSurfaceView.this.getTop() - height2;
                if (CustomSurfaceView.this.getWidth() <= CustomSurfaceView.this.screenWidth * 3 && CustomSurfaceView.this.getHeight() <= CustomSurfaceView.this.fatherView_H * 3) {
                    CustomSurfaceView.this.setPosition(left2, top2, right2, bottom2);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void setPosition(int i, int i2, int i3, int i4) {
        layout(i, i2, i3, i4);
    }

    public void setFatherW_H(int i, int i2) {
        this.fatherView_W = i;
        this.fatherView_H = i2;
    }

    public void setLayoutParam(float f) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.gravity = 17;
        layoutParams.width = (int) (((float) layoutParams.width) * f);
        layoutParams.height = (int) (f * ((float) layoutParams.height));
        setLayoutParams(layoutParams);
    }

    private float getDistance(float f, float f2) {
        return (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
    }

    public void setFatherTopAndBottom(int i, int i2) {
        this.fatherTop = i;
        this.fatherBottom = i2;
    }

    public void setEvent(ICoallBack iCoallBack) {
        this.icallBack = iCoallBack;
    }
}
