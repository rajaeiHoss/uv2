package com.streamax.config.utils;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.streamax.utils.AppProxy;

public class ImageUtils {
    public static final int BOTTOM = 4;
    public static final int LEFT = 1;
    public static final int NONE = 0;
    public static final int RIGHT = 3;
    public static final int TOP = 2;

    public static void clearImage(TextView textView) {
        textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public static void showIcon(TextView textView, int drawableResId) {
        showIconForTv(textView, drawableResId, 3);
    }

    public static void showNone(TextView textView, int drawableResId) {
        showIconForTv(textView, drawableResId, 0);
    }

    public static void showIconForTv(TextView textView, int drawableResId, int position) {
        Drawable drawable = AppProxy.getDrawable(drawableResId);
        if (position == 0) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (position == 1) {
            textView.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (position == 2) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        } else if (position == 3) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        } else if (position == 4) {
            textView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, drawable);
        }
    }
}
