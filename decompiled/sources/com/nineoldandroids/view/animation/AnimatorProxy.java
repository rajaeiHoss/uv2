package com.nineoldandroids.view.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.util.WeakHashMap;

public final class AnimatorProxy extends Animation {
    public static final boolean NEEDS_PROXY = (Integer.valueOf(Build.VERSION.SDK).intValue() < 11);
    private static final WeakHashMap<View, AnimatorProxy> PROXIES = new WeakHashMap<>();
    private float mAlpha = 1.0f;
    private final Camera mCamera;
    private boolean mHasPivot = false;
    private float mPivotX = 0.0f;
    private float mPivotY = 0.0f;
    private float mRotationX = 0.0f;
    private float mRotationY = 0.0f;
    private float mRotationZ = 0.0f;
    private float mScaleX = 1.0f;
    private float mScaleY = 1.0f;
    private float mTranslationX = 0.0f;
    private float mTranslationY = 0.0f;
    private final View mView;
    private final ViewGroup mViewParent;

    public static AnimatorProxy wrap(View view) {
        WeakHashMap<View, AnimatorProxy> weakHashMap = PROXIES;
        AnimatorProxy animatorProxy = weakHashMap.get(view);
        if (animatorProxy != null) {
            return animatorProxy;
        }
        AnimatorProxy animatorProxy2 = new AnimatorProxy(view);
        weakHashMap.put(view, animatorProxy2);
        return animatorProxy2;
    }

    private AnimatorProxy(View view) {
        setDuration(0);
        setFillAfter(true);
        view.setAnimation(this);
        this.mView = view;
        this.mViewParent = (ViewGroup) view.getParent();
        this.mCamera = new Camera();
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public void setAlpha(float f) {
        this.mAlpha = f;
        this.mView.invalidate();
    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public void setPivotX(float f) {
        this.mHasPivot = true;
        if (this.mPivotX != f) {
            this.mPivotX = f;
            this.mViewParent.invalidate();
        }
    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public void setPivotY(float f) {
        this.mHasPivot = true;
        if (this.mPivotY != f) {
            this.mPivotY = f;
            this.mViewParent.invalidate();
        }
    }

    public float getRotation() {
        return this.mRotationZ;
    }

    public void setRotation(float f) {
        if (this.mRotationZ != f) {
            this.mRotationZ = f;
            this.mViewParent.invalidate();
        }
    }

    public float getRotationX() {
        return this.mRotationX;
    }

    public void setRotationX(float f) {
        if (this.mRotationX != f) {
            this.mRotationX = f;
            this.mViewParent.invalidate();
        }
    }

    public float getRotationY() {
        return this.mRotationY;
    }

    public void setRotationY(float f) {
        if (this.mRotationY != f) {
            this.mRotationY = f;
            this.mViewParent.invalidate();
        }
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public void setScaleX(float f) {
        if (this.mScaleX != f) {
            this.mScaleX = f;
            this.mViewParent.invalidate();
        }
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public void setScaleY(float f) {
        if (this.mScaleY != f) {
            this.mScaleY = f;
            this.mViewParent.invalidate();
        }
    }

    public int getScrollX() {
        return this.mView.getScrollX();
    }

    public void setScrollX(int i) {
        View view = this.mView;
        view.scrollTo(i, view.getScrollY());
    }

    public int getScrollY() {
        return this.mView.getScrollY();
    }

    public void setScrollY(int i) {
        View view = this.mView;
        view.scrollTo(view.getScrollY(), i);
    }

    public float getTranslationX() {
        return this.mTranslationX;
    }

    public void setTranslationX(float f) {
        if (this.mTranslationX != f) {
            this.mTranslationX = f;
            this.mViewParent.invalidate();
        }
    }

    public float getTranslationY() {
        return this.mTranslationY;
    }

    public void setTranslationY(float f) {
        if (this.mTranslationY != f) {
            this.mTranslationY = f;
            this.mViewParent.invalidate();
        }
    }

    public float getX() {
        return ((float) this.mView.getLeft()) + this.mTranslationX;
    }

    public void setX(float f) {
        setTranslationX(f - ((float) this.mView.getLeft()));
    }

    public float getY() {
        return ((float) this.mView.getTop()) + this.mTranslationY;
    }

    public void setY(float f) {
        setTranslationY(f - ((float) this.mView.getTop()));
    }

    /* access modifiers changed from: protected */
    public void applyTransformation(float f, Transformation transformation) {
        transformation.setAlpha(this.mAlpha);
        View view = this.mView;
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        Matrix matrix = transformation.getMatrix();
        float f2 = this.mRotationX;
        float f3 = this.mRotationY;
        float f4 = this.mRotationZ;
        if (!(f2 == 0.0f && f3 == 0.0f && f4 == 0.0f)) {
            Camera camera = this.mCamera;
            boolean z = this.mHasPivot;
            float f5 = z ? this.mPivotX : width / 2.0f;
            float f6 = z ? this.mPivotY : height / 2.0f;
            camera.save();
            camera.rotateX(f2);
            camera.rotateY(f3);
            camera.rotateZ(-f4);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f5, -f6);
            matrix.postTranslate(f5, f6);
        }
        float f7 = this.mScaleX;
        float f8 = this.mScaleY;
        int i = (f7 > 0.0f ? 1 : (f7 == 0.0f ? 0 : -1));
        if (!(i == 0 && i == 0)) {
            matrix.postScale(f7, f8);
            matrix.postTranslate(-(((f7 * width) - width) / 2.0f), -(((f8 * height) - height) / 2.0f));
        }
        matrix.postTranslate(this.mTranslationX, this.mTranslationY);
    }
}
