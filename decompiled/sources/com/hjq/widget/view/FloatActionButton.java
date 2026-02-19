package com.hjq.widget.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

public final class FloatActionButton extends AppCompatImageView {
    private static final int ANIM_TIME = 300;
    private final Runnable mHideRunnable = new Runnable() {
        public final void run() {
            FloatActionButton.this.lambda$new$3$FloatActionButton();
        }
    };
    private final Runnable mShowRunnable = new Runnable() {
        public final void run() {
            FloatActionButton.this.lambda$new$1$FloatActionButton();
        }
    };

    public FloatActionButton(Context context) {
        super(context);
    }

    public FloatActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FloatActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void show() {
        removeCallbacks(this.mHideRunnable);
        postDelayed(this.mShowRunnable, 600);
    }

    public void hide() {
        removeCallbacks(this.mShowRunnable);
        post(this.mHideRunnable);
    }

    public /* synthetic */ void lambda$new$1$FloatActionButton() {
        if (getVisibility() == 4) {
            setVisibility(0);
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(300);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                FloatActionButton.this.lambda$null$0$FloatActionButton(valueAnimator);
            }
        });
        ofFloat.start();
    }

    public /* synthetic */ void lambda$null$0$FloatActionButton(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        setAlpha(floatValue);
        setScaleX(floatValue);
        setScaleY(floatValue);
    }

    public /* synthetic */ void lambda$new$3$FloatActionButton() {
        if (getVisibility() != 4) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
            ofFloat.setDuration(300);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    FloatActionButton.this.lambda$null$2$FloatActionButton(valueAnimator);
                }
            });
            ofFloat.start();
        }
    }

    public /* synthetic */ void lambda$null$2$FloatActionButton(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        setAlpha(floatValue);
        setScaleX(floatValue);
        setScaleY(floatValue);
        if (floatValue == 0.0f) {
            setVisibility(4);
        }
    }
}
