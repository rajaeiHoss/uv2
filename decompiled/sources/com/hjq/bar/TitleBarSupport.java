package com.hjq.bar;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public final class TitleBarSupport {
    public static final int NO_COLOR = 0;

    public static Drawable getDrawable(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(i, context.getTheme());
        }
        return context.getResources().getDrawable(i);
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static int getAbsoluteGravity(View view, int i) {
        if (Build.VERSION.SDK_INT < 17) {
            return i;
        }
        return Gravity.getAbsoluteGravity(i, view.getResources().getConfiguration().getLayoutDirection());
    }

    public static boolean isLayoutRtl(Context context) {
        if (Build.VERSION.SDK_INT >= 17 && context.getResources().getConfiguration().getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    public static boolean isContainContent(TextView textView) {
        if (!TextUtils.isEmpty(textView.getText())) {
            return true;
        }
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                return true;
            }
        }
        return false;
    }

    public static void setDrawableTint(Drawable drawable, int i) {
        if (drawable != null && i != 0) {
            drawable.mutate();
            drawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
        }
    }

    public static void clearDrawableTint(Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.clearColorFilter();
        }
    }

    public static void setDrawableSize(Drawable drawable, int i, int i2) {
        if (drawable != null) {
            if (i <= 0 && i2 <= 0) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            } else if (i <= 0 || i2 <= 0) {
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicWidth <= 0) {
                    intrinsicWidth = i;
                }
                if (intrinsicHeight <= 0) {
                    intrinsicHeight = i2;
                }
                if (i > 0) {
                    drawable.setBounds(0, 0, i, (intrinsicHeight * i) / intrinsicWidth);
                } else {
                    drawable.setBounds(0, 0, (intrinsicWidth * i2) / intrinsicHeight, i2);
                }
            } else {
                drawable.setBounds(0, 0, i, i2);
            }
        }
    }

    public static Drawable getTextCompoundDrawable(TextView textView, int i) {
        Drawable[] compoundDrawables = textView.getCompoundDrawables();
        int absoluteGravity = getAbsoluteGravity(textView, i);
        if (absoluteGravity == 3) {
            return compoundDrawables[0];
        }
        if (absoluteGravity == 5) {
            return compoundDrawables[2];
        }
        if (absoluteGravity == 48) {
            return compoundDrawables[1];
        }
        if (absoluteGravity != 80) {
            return null;
        }
        return compoundDrawables[3];
    }

    public static void setTextCompoundDrawable(TextView textView, Drawable drawable, int i) {
        int absoluteGravity = getAbsoluteGravity(textView, i);
        if (absoluteGravity == 3) {
            textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } else if (absoluteGravity == 5) {
            textView.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        } else if (absoluteGravity == 48) {
            textView.setCompoundDrawables((Drawable) null, drawable, (Drawable) null, (Drawable) null);
        } else if (absoluteGravity != 80) {
            textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            textView.setCompoundDrawables((Drawable) null, (Drawable) null, (Drawable) null, drawable);
        }
    }

    public static void setTextTypeface(TextView textView, int i) {
        if (i == 0) {
            textView.setTypeface(Typeface.DEFAULT, i);
        } else if (i == 1) {
            textView.setTypeface(Typeface.DEFAULT_BOLD, i);
        } else if (i == 2 || i == 3) {
            textView.setTypeface(Typeface.MONOSPACE, i);
        }
    }
}
