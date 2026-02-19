package com.hjq.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Environment

internal open class PermissionDelegateImplV30 : PermissionDelegateImplV29() {
    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE)) {
            return isGrantedManageStoragePermission()
        }
        return super.isGrantedPermission(context, permission)
    }

    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE)) {
            return false
        }
        return super.isPermissionPermanentDenied(activity, permission)
    }

    override fun getPermissionIntent(context: Context, permission: String): Intent {
        if (PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE)) {
            return getManageStoragePermissionIntent(context)
        }
        return super.getPermissionIntent(context, permission)
    }

    private fun isGrantedManageStoragePermission(): Boolean = Environment.isExternalStorageManager()

    private fun getManageStoragePermissionIntent(context: Context): Intent {
        var intent = Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION")
        intent.data = PermissionUtils.getPackageNameUri(context)
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            intent = Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION")
        }
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }
}
