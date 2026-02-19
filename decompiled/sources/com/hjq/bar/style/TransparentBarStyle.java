package com.hjq.bar.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.hjq.bar.R;
import com.hjq.bar.SelectorDrawable;
import com.hjq.bar.TitleBarSupport;

public class TransparentBarStyle extends CommonBarStyle {
    public Drawable getBackButtonDrawable(Context context) {
        return TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_white);
    }

    public Drawable getLeftTitleBackground(Context context) {
        return new SelectorDrawable.Builder().setDefault(new ColorDrawable(0)).setFocused(new ColorDrawable(570425344)).setPressed(new ColorDrawable(570425344)).build();
    }

    public Drawable getRightTitleBackground(Context context) {
        return new SelectorDrawable.Builder().setDefault(new ColorDrawable(0)).setFocused(new ColorDrawable(570425344)).setPressed(new ColorDrawable(570425344)).build();
    }

    public Drawable getTitleBarBackground(Context context) {
        return new ColorDrawable(0);
    }

    public ColorStateList getTitleColor(Context context) {
        return ColorStateList.valueOf(-1);
    }

    public ColorStateList getLeftTitleColor(Context context) {
        return ColorStateList.valueOf(-1);
    }

    public ColorStateList getRightTitleColor(Context context) {
        return ColorStateList.valueOf(-1);
    }

    public Drawable getLineDrawable(Context context) {
        return new ColorDrawable(0);
    }
}
