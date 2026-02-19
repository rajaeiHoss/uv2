package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;

class PermissionDelegateImplV33 extends PermissionDelegateImplV31 {
    PermissionDelegateImplV33() {
    }

    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.BODY_SENSORS_BACKGROUND)) {
            return PermissionUtils.checkSelfPermission(context, Permission.BODY_SENSORS) && PermissionUtils.checkSelfPermission(context, Permission.BODY_SENSORS_BACKGROUND);
        }
        if (PermissionUtils.equalsPermission(str, Permission.POST_NOTIFICATIONS) || PermissionUtils.equalsPermission(str, Permission.NEARBY_WIFI_DEVICES) || PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_IMAGES) || PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_VIDEO) || PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_AUDIO)) {
            return PermissionUtils.checkSelfPermission(context, str);
        }
        return super.isGrantedPermission(context, str);
    }

    public boolean isPermissionPermanentDenied(Activity activity, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.BODY_SENSORS_BACKGROUND)) {
            if (!PermissionUtils.checkSelfPermission(activity, Permission.BODY_SENSORS)) {
                return !PermissionUtils.shouldShowRequestPermissionRationale(activity, Permission.BODY_SENSORS);
            }
            if (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) {
                return false;
            }
            return true;
        } else if (!PermissionUtils.equalsPermission(str, Permission.POST_NOTIFICATIONS) && !PermissionUtils.equalsPermission(str, Permission.NEARBY_WIFI_DEVICES) && !PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_IMAGES) && !PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_VIDEO) && !PermissionUtils.equalsPermission(str, Permission.READ_MEDIA_AUDIO)) {
            return super.isPermissionPermanentDenied(activity, str);
        } else {
            if (PermissionUtils.checkSelfPermission(activity, str) || PermissionUtils.shouldShowRequestPermissionRationale(activity, str)) {
                return false;
            }
            return true;
        }
    }
}
