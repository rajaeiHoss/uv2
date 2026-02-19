package com.hjq.bar.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.dvr.calendar.DayStyle;
import com.hjq.bar.R;
import com.hjq.bar.SelectorDrawable;
import com.hjq.bar.TitleBarSupport;

public class LightBarStyle extends CommonBarStyle {
    public Drawable getTitleBarBackground(Context context) {
        return new ColorDrawable(-1);
    }

    public Drawable getLeftTitleBackground(Context context) {
        return new SelectorDrawable.Builder().setDefault(new ColorDrawable(0)).setFocused(new ColorDrawable(201326592)).setPressed(new ColorDrawable(201326592)).build();
    }

    public Drawable getRightTitleBackground(Context context) {
        return new SelectorDrawable.Builder().setDefault(new ColorDrawable(0)).setFocused(new ColorDrawable(201326592)).setPressed(new ColorDrawable(201326592)).build();
    }

    public Drawable getBackButtonDrawable(Context context) {
        return TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_black);
    }

    public ColorStateList getTitleColor(Context context) {
        return ColorStateList.valueOf(-14540254);
    }

    public ColorStateList getLeftTitleColor(Context context) {
        return ColorStateList.valueOf(DayStyle.iColorFrameHeader);
    }

    public ColorStateList getRightTitleColor(Context context) {
        return ColorStateList.valueOf(-5987164);
    }

    public Drawable getLineDrawable(Context context) {
        return new ColorDrawable(-1250068);
    }
}
