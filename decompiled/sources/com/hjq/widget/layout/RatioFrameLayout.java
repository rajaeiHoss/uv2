package com.hjq.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.hjq.widget.R;

public final class RatioFrameLayout extends FrameLayout {
    private float mHeightRatio;
    private float mWidthRatio;

    public RatioFrameLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public RatioFrameLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RatioFrameLayout);
        String string = obtainStyledAttributes.getString(R.styleable.RatioFrameLayout_sizeRatio);
        if (!TextUtils.isEmpty(string)) {
            String[] split = string.split(":");
            int length = split.length;
            if (length == 1) {
                this.mWidthRatio = Float.parseFloat(split[0]);
                this.mHeightRatio = 1.0f;
            } else if (length == 2) {
                this.mWidthRatio = Float.parseFloat(split[0]);
                this.mHeightRatio = Float.parseFloat(split[1]);
            } else {
                throw new IllegalArgumentException("are you ok?");
            }
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (!(this.mWidthRatio == 0.0f || this.mHeightRatio == 0.0f)) {
            float sizeRatio = getSizeRatio();
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (layoutParams.width != -2 && layoutParams.height != -2 && mode == 1073741824 && mode2 == 1073741824) {
                float f = (float) size;
                float f2 = f / sizeRatio;
                float f3 = (float) size2;
                if (f2 <= f3) {
                    i2 = View.MeasureSpec.makeMeasureSpec((int) f2, BasicMeasure.EXACTLY);
                } else {
                    float f4 = f3 * sizeRatio;
                    if (f4 <= f) {
                        i = View.MeasureSpec.makeMeasureSpec((int) f4, BasicMeasure.EXACTLY);
                    }
                }
            } else if (layoutParams.width != -2 && mode == 1073741824 && mode2 != 1073741824) {
                i2 = View.MeasureSpec.makeMeasureSpec((int) (((float) size) / sizeRatio), BasicMeasure.EXACTLY);
            } else if (!(layoutParams.height == -2 || mode2 != 1073741824 || mode == 1073741824)) {
                i = View.MeasureSpec.makeMeasureSpec((int) (((float) size2) * sizeRatio), BasicMeasure.EXACTLY);
            }
        }
        super.onMeasure(i, i2);
    }

    public float getWidthRatio() {
        return this.mWidthRatio;
    }

    public float getHeightRatio() {
        return this.mHeightRatio;
    }

    public float getSizeRatio() {
        return this.mWidthRatio / this.mHeightRatio;
    }

    public void setSizeRatio(float f, float f2) {
        this.mWidthRatio = f;
        this.mHeightRatio = f2;
        requestLayout();
        invalidate();
    }
}
