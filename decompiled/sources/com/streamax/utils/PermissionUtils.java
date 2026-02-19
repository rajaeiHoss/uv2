package com.streamax.utils;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {
    public static boolean checkPermissionGranted(Activity activity, String str) {
        return ContextCompat.checkSelfPermission(activity, str) == 0;
    }

    public static void requestPermission(Activity activity, String str, int i) {
        ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
        ActivityCompat.requestPermissions(activity, new String[]{str}, i);
    }
}
