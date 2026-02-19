package com.hjq.bar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

public interface ITitleBarStyle {
    TextView createLeftView(Context context);

    View createLineView(Context context);

    TextView createRightView(Context context);

    TextView createTitleView(Context context);

    Drawable getBackButtonDrawable(Context context);

    int getChildHorizontalPadding(Context context);

    int getChildVerticalPadding(Context context);

    int getLeftIconGravity(Context context);

    int getLeftIconHeight(Context context);

    int getLeftIconPadding(Context context);

    int getLeftIconWidth(Context context);

    CharSequence getLeftTitle(Context context);

    Drawable getLeftTitleBackground(Context context);

    ColorStateList getLeftTitleColor(Context context);

    float getLeftTitleSize(Context context);

    int getLeftTitleStyle(Context context);

    Drawable getLineDrawable(Context context);

    int getLineSize(Context context);

    int getRightIconGravity(Context context);

    int getRightIconHeight(Context context);

    int getRightIconPadding(Context context);

    int getRightIconWidth(Context context);

    CharSequence getRightTitle(Context context);

    Drawable getRightTitleBackground(Context context);

    ColorStateList getRightTitleColor(Context context);

    float getRightTitleSize(Context context);

    int getRightTitleStyle(Context context);

    CharSequence getTitle(Context context);

    Drawable getTitleBarBackground(Context context);

    ColorStateList getTitleColor(Context context);

    int getTitleIconGravity(Context context);

    int getTitleIconHeight(Context context);

    int getTitleIconPadding(Context context);

    int getTitleIconWidth(Context context);

    float getTitleSize(Context context);

    int getTitleStyle(Context context);

    boolean isLineVisible(Context context);
}
