package com.streamax.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class IvView extends ImageView {
    public boolean mIsSelected;

    public IvView(Context context) {
        this(context, (AttributeSet) null);
    }

    public IvView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IvView SetSelected(boolean z) {
        this.mIsSelected = z;
        return this;
    }

    public boolean IsSelected() {
        return this.mIsSelected;
    }

    public IvView SetImageResource(int i) {
        setImageResource(i);
        return this;
    }
}
