package com.streamax.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.streamax.client.MyApp;
import com.zycs.UView.R;

public class AppProxy {
    public static int STORAGE_UNIT_B = 0;
    public static int STORAGE_UNIT_GB = 3;
    public static int STORAGE_UNIT_KB = 1;
    public static int STORAGE_UNIT_MB = 2;
    public static int STORAGE_UNIT_TB = 4;

    public static Resources getResources() {
        return MyApp.getContext().getResources();
    }

    public static Handler getHandler() {
        return MyApp.getHandler();
    }

    public static Context getContext() {
        return MyApp.getContext();
    }

    public static String getPckName(Context context) {
        return context.getPackageName();
    }

    public static Drawable getDrawable(int drawableResId) {
        return getResources().getDrawable(drawableResId);
    }

    public static boolean isCNLan() {
        return getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }

    public static float s2g(int storageUnit, long size) {
        if (storageUnit == STORAGE_UNIT_B) {
            return ((((float) size) * 10.0f) / 1.0E9f) / 10.0f;
        }
        if (storageUnit == STORAGE_UNIT_KB) {
            return ((((float) size) * 10.0f) / 1000000.0f) / 10.0f;
        }
        if (storageUnit == STORAGE_UNIT_MB) {
            return ((((float) size) * 10.0f) / 1000.0f) / 10.0f;
        }
        if (storageUnit == STORAGE_UNIT_GB) {
            return ((float) size) * 1.0f;
        }
        return storageUnit == STORAGE_UNIT_TB ? ((float) size) * 1000.0f : ((float) size) * 1.0f;
    }

    public static void singleIcon(boolean selected, View view) {
        if (selected) {
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(R.drawable.rb_signle_checked);
            }
        } else if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(0);
        }
    }
}
