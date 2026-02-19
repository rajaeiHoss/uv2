package com.streamax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class RlView extends RelativeLayout {
    public RlView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RlView SetCliskable(boolean z) {
        setClickable(z);
        setFocusable(z);
        return this;
    }
}
