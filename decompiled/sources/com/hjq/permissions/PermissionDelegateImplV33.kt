package com.hjq.permissions

import android.app.Activity
import android.content.Context

internal class PermissionDelegateImplV33 : PermissionDelegateImplV31() {
    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.BODY_SENSORS_BACKGROUND)) {
            return PermissionUtils.checkSelfPermission(context, Permission.BODY_SENSORS) &&
                PermissionUtils.checkSelfPermission(context, Permission.BODY_SENSORS_BACKGROUND)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS) ||
            PermissionUtils.equalsPermission(permission, Permission.NEARBY_WIFI_DEVICES) ||
            PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_IMAGES) ||
            PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_VIDEO) ||
            PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_AUDIO)
        ) {
            return PermissionUtils.checkSelfPermission(context, permission)
        }
        return super.isGrantedPermission(context, permission)
    }

    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.BODY_SENSORS_BACKGROUND)) {
            if (!PermissionUtils.checkSelfPermission(activity, Permission.BODY_SENSORS)) {
                return !PermissionUtils.shouldShowRequestPermissionRationale(
                    activity,
                    Permission.BODY_SENSORS
                )
            }
            return !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS) ||
            PermissionUtils.equalsPermission(permission, Permission.NEARBY_WIFI_DEVICES) ||
            PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_IMAGES) ||
            PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_VIDEO) ||
            PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_AUDIO)
        ) {
            return !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        return super.isPermissionPermanentDenied(activity, permission)
    }
}
