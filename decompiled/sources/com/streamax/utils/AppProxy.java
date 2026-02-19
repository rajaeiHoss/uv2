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

    public static Drawable getDrawable(int i) {
        return getResources().getDrawable(i);
    }

    public static boolean isCNLan() {
        return getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }

    public static float s2g(int i, long j) {
        if (i == STORAGE_UNIT_B) {
            return ((((float) j) * 10.0f) / 1.0E9f) / 10.0f;
        }
        if (i == STORAGE_UNIT_KB) {
            return ((((float) j) * 10.0f) / 1000000.0f) / 10.0f;
        }
        if (i == STORAGE_UNIT_MB) {
            return ((((float) j) * 10.0f) / 1000.0f) / 10.0f;
        }
        if (i == STORAGE_UNIT_GB) {
            return ((float) j) * 1.0f;
        }
        return i == STORAGE_UNIT_TB ? ((float) j) * 1000.0f : ((float) j) * 1.0f;
    }

    public static void singleIcon(boolean z, View view) {
        if (z) {
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(R.drawable.rb_signle_checked);
            }
        } else if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(0);
        }
    }
}
