package com.nineoldandroids.view;

import android.animation.Animator;
import android.view.View;
import android.view.animation.Interpolator;

class ViewPropertyAnimatorICS extends ViewPropertyAnimator {
    private final android.view.ViewPropertyAnimator mNative;

    ViewPropertyAnimatorICS(View view) {
        this.mNative = view.animate();
    }

    public ViewPropertyAnimator setDuration(long j) {
        this.mNative.setDuration(j);
        return this;
    }

    public long getDuration() {
        return this.mNative.getDuration();
    }

    public ViewPropertyAnimator setStartDelay(long j) {
        this.mNative.setStartDelay(j);
        return this;
    }

    public long getStartDelay() {
        return this.mNative.getStartDelay();
    }

    public ViewPropertyAnimator setInterpolator(Interpolator interpolator) {
        this.mNative.setInterpolator(interpolator);
        return this;
    }

    public ViewPropertyAnimator setListener(final com.nineoldandroids.animation.Animator.AnimatorListener animatorListener) {
        if (animatorListener == null) {
            this.mNative.setListener((Animator.AnimatorListener) null);
        } else {
            this.mNative.setListener(new Animator.AnimatorListener() {
                public void onAnimationStart(android.animation.Animator animator) {
                    animatorListener.onAnimationStart((com.nineoldandroids.animation.Animator) null);
                }

                public void onAnimationRepeat(android.animation.Animator animator) {
                    animatorListener.onAnimationRepeat((com.nineoldandroids.animation.Animator) null);
                }

                public void onAnimationEnd(android.animation.Animator animator) {
                    animatorListener.onAnimationEnd((com.nineoldandroids.animation.Animator) null);
                }

                public void onAnimationCancel(android.animation.Animator animator) {
                    animatorListener.onAnimationCancel((com.nineoldandroids.animation.Animator) null);
                }
            });
        }
        return this;
    }

    public void start() {
        this.mNative.start();
    }

    public void cancel() {
        this.mNative.cancel();
    }

    public ViewPropertyAnimator x(float f) {
        this.mNative.x(f);
        return this;
    }

    public ViewPropertyAnimator xBy(float f) {
        this.mNative.xBy(f);
        return this;
    }

    public ViewPropertyAnimator y(float f) {
        this.mNative.y(f);
        return this;
    }

    public ViewPropertyAnimator yBy(float f) {
        this.mNative.yBy(f);
        return this;
    }

    public ViewPropertyAnimator rotation(float f) {
        this.mNative.rotation(f);
        return this;
    }

    public ViewPropertyAnimator rotationBy(float f) {
        this.mNative.rotationBy(f);
        return this;
    }

    public ViewPropertyAnimator rotationX(float f) {
        this.mNative.rotationX(f);
        return this;
    }

    public ViewPropertyAnimator rotationXBy(float f) {
        this.mNative.rotationXBy(f);
        return this;
    }

    public ViewPropertyAnimator rotationY(float f) {
        this.mNative.rotationY(f);
        return this;
    }

    public ViewPropertyAnimator rotationYBy(float f) {
        this.mNative.rotationYBy(f);
        return this;
    }

    public ViewPropertyAnimator translationX(float f) {
        this.mNative.translationX(f);
        return this;
    }

    public ViewPropertyAnimator translationXBy(float f) {
        this.mNative.translationXBy(f);
        return this;
    }

    public ViewPropertyAnimator translationY(float f) {
        this.mNative.translationY(f);
        return this;
    }

    public ViewPropertyAnimator translationYBy(float f) {
        this.mNative.translationYBy(f);
        return this;
    }

    public ViewPropertyAnimator scaleX(float f) {
        this.mNative.scaleX(f);
        return this;
    }

    public ViewPropertyAnimator scaleXBy(float f) {
        this.mNative.scaleXBy(f);
        return this;
    }

    public ViewPropertyAnimator scaleY(float f) {
        this.mNative.scaleY(f);
        return this;
    }

    public ViewPropertyAnimator scaleYBy(float f) {
        this.mNative.scaleYBy(f);
        return this;
    }

    public ViewPropertyAnimator alpha(float f) {
        this.mNative.alpha(f);
        return this;
    }

    public ViewPropertyAnimator alphaBy(float f) {
        this.mNative.alphaBy(f);
        return this;
    }
}
