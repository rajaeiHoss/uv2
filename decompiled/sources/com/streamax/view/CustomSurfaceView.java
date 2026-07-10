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
        void getAngle(int left, int width);
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
                int deltaX = this.distanceX;
                int newLeft = left + deltaX;
                int newRight = right + deltaX;
                int deltaY = this.distanceY;
                int newBottom = bottom + deltaY;
                int newTop = top + deltaY;
                if (this.isControl_Horizal) {
                    if (newLeft >= 0) {
                        newRight = getWidth();
                        newLeft = 0;
                    }
                    int rightLimit = this.screenWidth;
                    if (newRight <= rightLimit) {
                        newLeft = rightLimit - getWidth();
                        newRight = this.screenWidth;
                    }
                } else {
                    newLeft = getLeft();
                    newRight = getRight();
                }
                if (this.isControl_Vertical) {
                    if (newTop > 0) {
                        newBottom = getHeight();
                        newTop = 0;
                    }
                    int bottomLimit = this.start_Bottom;
                    if (newBottom <= bottomLimit) {
                        newTop = this.fatherView_H - getWidth();
                        newBottom = bottomLimit;
                    }
                } else {
                    newTop = getTop();
                    newBottom = getBottom();
                }
                if (this.isControl_Horizal || this.isControl_Vertical) {
                    setPosition(newLeft, newTop, newRight, newBottom);
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
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.start_Top == -1) {
            this.start_Top = top;
            this.start_Left = left;
            this.start_Right = right;
            this.start_Bottom = bottom;
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
                    float scaleDelta = ((float) ((int) (((double) (((float) CustomSurfaceView.this.getHeight()) * (scaleFactor - 1.0f))) / 7.0d))) / 2.0f;
                    int scaledLeft = (int) (((float) left) - scaleDelta);
                    int scaledRight = (int) (((float) right) + scaleDelta);
                    int scaledBottom = (int) (((float) bottom) + scaleDelta);
                    int scaledTop = (int) (((float) top) - scaleDelta);
                    if (CustomSurfaceView.this.getWidth() <= CustomSurfaceView.this.screenWidth * 3 && CustomSurfaceView.this.getHeight() <= CustomSurfaceView.this.fatherView_H * 3) {
                        CustomSurfaceView.this.setPosition(scaledLeft, scaledTop, scaledRight, scaledBottom);
                    }
                } else {
                    float scaleDelta = ((float) ((int) (((double) (((float) CustomSurfaceView.this.getHeight()) * (1.0f - scaleFactor))) / 7.0d))) / 2.0f;
                    int scaledLeft = (int) (((float) left) + scaleDelta);
                    int scaledRight = (int) (((float) right) - scaleDelta);
                    int scaledBottom = (int) (((float) bottom) - scaleDelta);
                    int scaledTop = (int) (((float) top) + scaleDelta);
                    if (scaledLeft >= 0) {
                        scaledLeft = 0;
                    }
                    if (scaledRight <= CustomSurfaceView.this.screenWidth) {
                        scaledRight = CustomSurfaceView.this.screenWidth;
                    }
                    if (scaledTop >= 0) {
                        scaledTop = 0;
                    }
                    if (scaledBottom <= CustomSurfaceView.this.fatherView_H) {
                        scaledBottom = CustomSurfaceView.this.fatherView_H;
                    }
                    if (CustomSurfaceView.this.getWidth() <= CustomSurfaceView.this.initViewWidth || CustomSurfaceView.this.getHeight() <= CustomSurfaceView.this.fatherView_H) {
                        CustomSurfaceView customSurfaceView = CustomSurfaceView.this;
                        customSurfaceView.setPosition(customSurfaceView.start_Left, CustomSurfaceView.this.start_Top, CustomSurfaceView.this.start_Right, CustomSurfaceView.this.start_Bottom);
                    } else {
                        CustomSurfaceView.this.setPosition(scaledLeft, scaledTop, scaledRight, scaledBottom);
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
    public void setPosition(int left, int top, int right, int bottom) {
        layout(left, top, right, bottom);
    }

    public void setFatherW_H(int width, int height) {
        this.fatherView_W = width;
        this.fatherView_H = height;
    }

    public void setLayoutParam(float f) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.gravity = 17;
        layoutParams.width = (int) (((float) layoutParams.width) * f);
        layoutParams.height = (int) (f * ((float) layoutParams.height));
        setLayoutParams(layoutParams);
    }

    private float getDistance(float deltaX, float deltaY) {
        return (float) Math.sqrt((double) ((deltaX * deltaX) + (deltaY * deltaY)));
    }

    public void setFatherTopAndBottom(int top, int bottom) {
        this.fatherTop = top;
        this.fatherBottom = bottom;
    }

    public void setEvent(ICoallBack iCoallBack) {
        this.icallBack = iCoallBack;
    }
}
