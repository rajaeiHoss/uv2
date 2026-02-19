package com.hjq.toast.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;

public class WhiteToastStyle extends BlackToastStyle {
    /* access modifiers changed from: protected */
    public int getTextColor(Context context) {
        return -1157627904;
    }

    /* access modifiers changed from: protected */
    public Drawable getBackgroundDrawable(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-1381654);
        gradientDrawable.setCornerRadius(TypedValue.applyDimension(1, 8.0f, context.getResources().getDisplayMetrics()));
        return gradientDrawable;
    }
}
