package com.streamax.utils;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {
    public static boolean checkPermissionGranted(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == 0;
    }

    public static void requestPermission(Activity activity, String permission, int requestCode) {
        ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }
}
