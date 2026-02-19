package com.hjq.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public final class PressAlphaTextView extends AppCompatTextView {
    public PressAlphaTextView(Context context) {
        super(context);
    }

    public PressAlphaTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PressAlphaTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void dispatchSetPressed(boolean z) {
        if (z) {
            setAlpha(0.5f);
        } else {
            setAlpha(1.0f);
        }
    }
}
