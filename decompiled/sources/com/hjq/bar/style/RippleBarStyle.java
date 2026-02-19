package com.hjq.bar.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import com.hjq.bar.TitleBarSupport;

public class RippleBarStyle extends TransparentBarStyle {
    public Drawable getLeftTitleBackground(Context context) {
        Drawable createRippleDrawable = createRippleDrawable(context);
        if (createRippleDrawable != null) {
            return createRippleDrawable;
        }
        return super.getLeftTitleBackground(context);
    }

    public Drawable getRightTitleBackground(Context context) {
        Drawable createRippleDrawable = createRippleDrawable(context);
        if (createRippleDrawable != null) {
            return createRippleDrawable;
        }
        return super.getRightTitleBackground(context);
    }

    public Drawable createRippleDrawable(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(16843868, typedValue, true)) {
            return TitleBarSupport.getDrawable(context, typedValue.resourceId);
        }
        return null;
    }
}
