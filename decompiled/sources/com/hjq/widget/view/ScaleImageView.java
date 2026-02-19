package com.hjq.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.hjq.widget.R;

public final class ScaleImageView extends AppCompatImageView {
    private float mScaleSize;

    public ScaleImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ScaleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScaleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScaleSize = 1.2f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ScaleImageView);
        setScaleSize(obtainStyledAttributes.getFloat(R.styleable.ScaleImageView_scaleRatio, this.mScaleSize));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void dispatchSetPressed(boolean z) {
        if (z) {
            setScaleX(this.mScaleSize);
            setScaleY(this.mScaleSize);
            return;
        }
        setScaleX(1.0f);
        setScaleY(1.0f);
    }

    public void setScaleSize(float f) {
        this.mScaleSize = f;
    }
}
