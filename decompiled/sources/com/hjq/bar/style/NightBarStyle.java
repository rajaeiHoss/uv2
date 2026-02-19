package com.hjq.bar.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.view.ViewCompat;
import com.hjq.bar.R;
import com.hjq.bar.SelectorDrawable;
import com.hjq.bar.TitleBarSupport;

public class NightBarStyle extends CommonBarStyle {
    public Drawable getBackButtonDrawable(Context context) {
        return TitleBarSupport.getDrawable(context, R.drawable.bar_arrows_left_white);
    }

    public Drawable getLeftTitleBackground(Context context) {
        return new SelectorDrawable.Builder().setDefault(new ColorDrawable(0)).setFocused(new ColorDrawable(1728053247)).setPressed(new ColorDrawable(1728053247)).build();
    }

    public Drawable getRightTitleBackground(Context context) {
        return new SelectorDrawable.Builder().setDefault(new ColorDrawable(0)).setFocused(new ColorDrawable(1728053247)).setPressed(new ColorDrawable(1728053247)).build();
    }

    public Drawable getTitleBarBackground(Context context) {
        return new ColorDrawable(ViewCompat.MEASURED_STATE_MASK);
    }

    public ColorStateList getTitleColor(Context context) {
        return ColorStateList.valueOf(-285212673);
    }

    public ColorStateList getLeftTitleColor(Context context) {
        return ColorStateList.valueOf(-855638017);
    }

    public ColorStateList getRightTitleColor(Context context) {
        return ColorStateList.valueOf(-855638017);
    }

    public Drawable getLineDrawable(Context context) {
        return new ColorDrawable(-1);
    }
}
