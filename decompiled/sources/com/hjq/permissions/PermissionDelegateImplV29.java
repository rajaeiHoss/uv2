package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

class PermissionDelegateImplV29 extends PermissionDelegateImplV28 {
    PermissionDelegateImplV29() {
    }

    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION)) {
            if (!hasReadStoragePermission(context) || !PermissionUtils.checkSelfPermission(context, Permission.ACCESS_MEDIA_LOCATION)) {
                return false;
            }
            return true;
        } else if (PermissionUtils.equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION) || PermissionUtils.equalsPermission(str, Permission.ACTIVITY_RECOGNITION)) {
            return PermissionUtils.checkSelfPermission(context, str);
        } else {
            if (AndroidVersion.isAndroid11() || !PermissionUtils.equalsPermission(str, Permission.MANAGE_EXTERNAL_STORAGE) || isUseDeprecationExternalStorage()) {
                return super.isGrantedPermission(context, str);
            }
            return false;
        }
    }

    public boolean isPermissionPermanentDenied(Activity activity, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION)) {
            if (!PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION)) {
                return !PermissionUtils.shouldShowRequestPermissionRationale(activity, Permission.ACCESS_FINE_LOCATION);
            }
            if (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) {
                return false;
            }
            return true;
        } else if (PermissionUtils.equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION)) {
            if (!hasReadStoragePermission(activity) || PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) {
                return false;
            }
            return true;
        } else if (PermissionUtils.equalsPermission(str, Permission.ACTIVITY_RECOGNITION)) {
            if (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) {
                return false;
            }
            return true;
        } else if (AndroidVersion.isAndroid11() || !PermissionUtils.equalsPermission(str, Permission.MANAGE_EXTERNAL_STORAGE) || isUseDeprecationExternalStorage()) {
            return super.isPermissionPermanentDenied(activity, str);
        } else {
            return true;
        }
    }

    private static boolean isUseDeprecationExternalStorage() {
        return Environment.isExternalStorageLegacy();
    }

    private boolean hasReadStoragePermission(Context context) {
        if (!AndroidVersion.isAndroid13() || AndroidVersion.getTargetSdkVersionCode(context) < 33) {
            if (!AndroidVersion.isAndroid11() || AndroidVersion.getTargetSdkVersionCode(context) < 30) {
                return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE);
            }
            if (PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE) || isGrantedPermission(context, Permission.MANAGE_EXTERNAL_STORAGE)) {
                return true;
            }
            return false;
        } else if (PermissionUtils.checkSelfPermission(context, Permission.READ_MEDIA_IMAGES) || isGrantedPermission(context, Permission.MANAGE_EXTERNAL_STORAGE)) {
            return true;
        } else {
            return false;
        }
    }
}
