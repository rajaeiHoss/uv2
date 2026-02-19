package com.streamax.client;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class ExpLv extends ExpandableListView {
    public ExpLv(Context context) {
        this(context, (AttributeSet) null);
    }

    public ExpLv(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
