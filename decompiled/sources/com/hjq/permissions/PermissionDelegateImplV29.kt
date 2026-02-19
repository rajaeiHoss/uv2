package com.hjq.permissions

import android.app.Activity
import android.content.Context
import android.os.Environment

internal open class PermissionDelegateImplV29 : PermissionDelegateImplV28() {
    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_MEDIA_LOCATION)) {
            return hasReadStoragePermission(context) &&
                PermissionUtils.checkSelfPermission(context, Permission.ACCESS_MEDIA_LOCATION)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_BACKGROUND_LOCATION) ||
            PermissionUtils.equalsPermission(permission, Permission.ACTIVITY_RECOGNITION)
        ) {
            return PermissionUtils.checkSelfPermission(context, permission)
        }
        if (AndroidVersion.isAndroid11() ||
            !PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE) ||
            isUseDeprecationExternalStorage()
        ) {
            return super.isGrantedPermission(context, permission)
        }
        return false
    }

    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_BACKGROUND_LOCATION)) {
            if (!PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION)) {
                return !PermissionUtils.shouldShowRequestPermissionRationale(
                    activity,
                    Permission.ACCESS_FINE_LOCATION
                )
            }
            return !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_MEDIA_LOCATION)) {
            return hasReadStoragePermission(activity) &&
                !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.ACTIVITY_RECOGNITION)) {
            return !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        if (AndroidVersion.isAndroid11() ||
            !PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE) ||
            isUseDeprecationExternalStorage()
        ) {
            return super.isPermissionPermanentDenied(activity, permission)
        }
        return true
    }

    private fun isUseDeprecationExternalStorage(): Boolean = Environment.isExternalStorageLegacy()

    private fun hasReadStoragePermission(context: Context): Boolean {
        if (!AndroidVersion.isAndroid13() || AndroidVersion.getTargetSdkVersionCode(context) < 33) {
            if (!AndroidVersion.isAndroid11() || AndroidVersion.getTargetSdkVersionCode(context) < 30) {
                return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE)
            }
            return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE) ||
                isGrantedPermission(context, Permission.MANAGE_EXTERNAL_STORAGE)
        }
        return PermissionUtils.checkSelfPermission(context, Permission.READ_MEDIA_IMAGES) ||
            isGrantedPermission(context, Permission.MANAGE_EXTERNAL_STORAGE)
    }
}
