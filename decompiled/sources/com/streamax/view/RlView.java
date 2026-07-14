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

    public RlView SetCliskable(boolean clickable) {
        setClickable(clickable);
        setFocusable(clickable);
        return this;
    }
}
