package com.streamax.client;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import com.hjq.toast.style.BlackToastStyle;

public class ToastStyle extends BlackToastStyle {
    /* access modifiers changed from: protected */
    public int getHorizontalPadding(Context context) {
        return 64;
    }

    /* access modifiers changed from: protected */
    public float getTextSize(Context context) {
        return 48.0f;
    }

    /* access modifiers changed from: protected */
    public int getVerticalPadding(Context context) {
        return 48;
    }

    /* access modifiers changed from: protected */
    public Drawable getBackgroundDrawable(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-268400947);
        gradientDrawable.setCornerRadius(TypedValue.applyDimension(1, 99.0f, context.getResources().getDisplayMetrics()));
        return gradientDrawable;
    }
}
