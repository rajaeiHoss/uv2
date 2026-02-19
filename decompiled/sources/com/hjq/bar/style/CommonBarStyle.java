package com.hjq.bar.style;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.hjq.bar.ITitleBarStyle;

public abstract class CommonBarStyle implements ITitleBarStyle {
    public int getLeftIconGravity(Context context) {
        return 8388611;
    }

    public int getLeftIconHeight(Context context) {
        return 0;
    }

    public int getLeftIconWidth(Context context) {
        return 0;
    }

    public CharSequence getLeftTitle(Context context) {
        return "";
    }

    public int getLeftTitleStyle(Context context) {
        return 0;
    }

    public int getLineSize(Context context) {
        return 1;
    }

    public int getRightIconGravity(Context context) {
        return GravityCompat.END;
    }

    public int getRightIconHeight(Context context) {
        return 0;
    }

    public int getRightIconWidth(Context context) {
        return 0;
    }

    public CharSequence getRightTitle(Context context) {
        return "";
    }

    public int getRightTitleStyle(Context context) {
        return 0;
    }

    public int getTitleIconGravity(Context context) {
        return GravityCompat.END;
    }

    public int getTitleIconHeight(Context context) {
        return 0;
    }

    public int getTitleIconWidth(Context context) {
        return 0;
    }

    public int getTitleStyle(Context context) {
        return 0;
    }

    public boolean isLineVisible(Context context) {
        return true;
    }

    public TextView createTitleView(Context context) {
        TextView newTitleView = newTitleView(context);
        newTitleView.setGravity(16);
        newTitleView.setFocusable(true);
        newTitleView.setSingleLine();
        newTitleView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        newTitleView.setMarqueeRepeatLimit(-1);
        newTitleView.setSelected(true);
        return newTitleView;
    }

    public TextView newTitleView(Context context) {
        return new TextView(context);
    }

    public TextView createLeftView(Context context) {
        TextView newLeftView = newLeftView(context);
        newLeftView.setGravity(16);
        newLeftView.setFocusable(true);
        newLeftView.setSingleLine();
        newLeftView.setEllipsize(TextUtils.TruncateAt.END);
        return newLeftView;
    }

    public TextView newLeftView(Context context) {
        return new TextView(context);
    }

    public TextView createRightView(Context context) {
        TextView newRightView = newRightView(context);
        newRightView.setGravity(16);
        newRightView.setFocusable(true);
        newRightView.setSingleLine();
        newRightView.setEllipsize(TextUtils.TruncateAt.END);
        return newRightView;
    }

    public TextView newRightView(Context context) {
        return new TextView(context);
    }

    public View createLineView(Context context) {
        return new View(context);
    }

    public int getChildHorizontalPadding(Context context) {
        return (int) TypedValue.applyDimension(1, 12.0f, context.getResources().getDisplayMetrics());
    }

    public int getChildVerticalPadding(Context context) {
        return (int) TypedValue.applyDimension(1, 15.0f, context.getResources().getDisplayMetrics());
    }

    public CharSequence getTitle(Context context) {
        if (!(context instanceof Activity)) {
            return "";
        }
        CharSequence title = ((Activity) context).getTitle();
        if (TextUtils.isEmpty(title)) {
            return "";
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            return !title.toString().equals(packageManager.getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(packageManager).toString()) ? title : "";
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public float getTitleSize(Context context) {
        return TypedValue.applyDimension(2, 16.0f, context.getResources().getDisplayMetrics());
    }

    public float getLeftTitleSize(Context context) {
        return TypedValue.applyDimension(2, 14.0f, context.getResources().getDisplayMetrics());
    }

    public float getRightTitleSize(Context context) {
        return TypedValue.applyDimension(2, 14.0f, context.getResources().getDisplayMetrics());
    }

    public int getTitleIconPadding(Context context) {
        return (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
    }

    public int getLeftIconPadding(Context context) {
        return (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
    }

    public int getRightIconPadding(Context context) {
        return (int) TypedValue.applyDimension(1, 2.0f, context.getResources().getDisplayMetrics());
    }
}
