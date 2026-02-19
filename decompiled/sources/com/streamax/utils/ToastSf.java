package com.streamax.utils;

import android.app.Activity;
import android.widget.Toast;

public class ToastSf {
    public static void toastSf(Activity activity, final String str) {
        if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
            Toast.makeText(AppProxy.getContext(), str, 0).show();
        } else {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(AppProxy.getContext(), str, 0).show();
                }
            });
        }
    }

    public static void toastSf(Activity activity, int i) {
        final String string = StringUtils.getString(Integer.valueOf(i));
        if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
            Toast.makeText(AppProxy.getContext(), string, 0).show();
        } else {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(AppProxy.getContext(), string, 0).show();
                }
            });
        }
    }
}
