package com.hjq.toast.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hjq.toast.config.IToastStyle;

public class BlackToastStyle implements IToastStyle<TextView> {
    public int getGravity() {
        return 17;
    }

    public float getHorizontalMargin() {
        return 0.0f;
    }

    /* access modifiers changed from: protected */
    public int getMaxLines(Context context) {
        return 5;
    }

    /* access modifiers changed from: protected */
    public int getTextColor(Context context) {
        return -285212673;
    }

    /* access modifiers changed from: protected */
    public int getTextGravity(Context context) {
        return 17;
    }

    public float getVerticalMargin() {
        return 0.0f;
    }

    public int getXOffset() {
        return 0;
    }

    public int getYOffset() {
        return 0;
    }

    public TextView createView(Context context) {
        TextView textView = new TextView(context);
        textView.setId(16908299);
        textView.setGravity(getTextGravity(context));
        textView.setTextColor(getTextColor(context));
        textView.setTextSize(0, getTextSize(context));
        int horizontalPadding = getHorizontalPadding(context);
        int verticalPadding = getVerticalPadding(context);
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setPaddingRelative(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        } else {
            textView.setPadding(horizontalPadding, verticalPadding, horizontalPadding, verticalPadding);
        }
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        Drawable backgroundDrawable = getBackgroundDrawable(context);
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(backgroundDrawable);
        } else {
            textView.setBackgroundDrawable(backgroundDrawable);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            textView.setZ(getTranslationZ(context));
        }
        textView.setMaxLines(getMaxLines(context));
        return textView;
    }

    /* access modifiers changed from: protected */
    public float getTextSize(Context context) {
        return TypedValue.applyDimension(2, 14.0f, context.getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public int getHorizontalPadding(Context context) {
        return (int) TypedValue.applyDimension(1, 24.0f, context.getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public int getVerticalPadding(Context context) {
        return (int) TypedValue.applyDimension(1, 16.0f, context.getResources().getDisplayMetrics());
    }

    /* access modifiers changed from: protected */
    public Drawable getBackgroundDrawable(Context context) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-2013265920);
        gradientDrawable.setCornerRadius(TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics()));
        return gradientDrawable;
    }

    /* access modifiers changed from: protected */
    public float getTranslationZ(Context context) {
        return TypedValue.applyDimension(1, 3.0f, context.getResources().getDisplayMetrics());
    }
}
