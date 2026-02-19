package com.hjq.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent

internal open class PermissionDelegateImplV26 : PermissionDelegateImplV23() {
    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.REQUEST_INSTALL_PACKAGES)) {
            return isGrantedInstallPermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.READ_PHONE_NUMBERS) ||
            PermissionUtils.equalsPermission(permission, Permission.ANSWER_PHONE_CALLS)
        ) {
            return PermissionUtils.checkSelfPermission(context, permission)
        }
        return super.isGrantedPermission(context, permission)
    }

    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.REQUEST_INSTALL_PACKAGES)) {
            return false
        }
        if (!PermissionUtils.equalsPermission(permission, Permission.READ_PHONE_NUMBERS) &&
            !PermissionUtils.equalsPermission(permission, Permission.ANSWER_PHONE_CALLS)
        ) {
            return super.isPermissionPermanentDenied(activity, permission)
        }
        return !PermissionUtils.checkSelfPermission(activity, permission) &&
            !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
    }

    override fun getPermissionIntent(context: Context, permission: String): Intent {
        if (PermissionUtils.equalsPermission(permission, Permission.REQUEST_INSTALL_PACKAGES)) {
            return getInstallPermissionIntent(context)
        }
        return super.getPermissionIntent(context, permission)
    }

    private fun isGrantedInstallPermission(context: Context): Boolean =
        context.packageManager.canRequestPackageInstalls()

    private fun getInstallPermissionIntent(context: Context): Intent {
        val intent = Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES")
        intent.data = PermissionUtils.getPackageNameUri(context)
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }
}
