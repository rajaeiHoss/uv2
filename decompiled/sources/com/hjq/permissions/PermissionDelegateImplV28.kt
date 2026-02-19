package com.hjq.permissions

import android.app.Activity
import android.content.Context

internal open class PermissionDelegateImplV28 : PermissionDelegateImplV26() {
    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.ACCEPT_HANDOVER)) {
            return PermissionUtils.checkSelfPermission(context, permission)
        }
        return super.isGrantedPermission(context, permission)
    }

    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.ACCEPT_HANDOVER)) {
            return !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        return super.isPermissionPermanentDenied(activity, permission)
    }
}
