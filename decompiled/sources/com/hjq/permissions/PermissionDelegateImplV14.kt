package com.hjq.permissions

import android.app.Activity
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.net.VpnService
import androidx.core.app.NotificationManagerCompat

internal open class PermissionDelegateImplV14 : PermissionDelegate {
    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean = false

    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.NOTIFICATION_SERVICE)) {
            return isGrantedNotifyPermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.PACKAGE_USAGE_STATS)) {
            return isGrantedPackagePermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.BIND_NOTIFICATION_LISTENER_SERVICE)) {
            return isGrantedNotificationListenerPermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.BIND_VPN_SERVICE)) {
            return isGrantedVpnPermission(context)
        }
        if (AndroidVersion.isAndroid13() ||
            !PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS)
        ) {
            return true
        }
        return isGrantedNotifyPermission(context)
    }

    override fun getPermissionIntent(context: Context, permission: String): Intent {
        if (PermissionUtils.equalsPermission(permission, Permission.NOTIFICATION_SERVICE)) {
            return getNotifyPermissionIntent(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.PACKAGE_USAGE_STATS)) {
            return getPackagePermissionIntent(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.BIND_NOTIFICATION_LISTENER_SERVICE)) {
            return getNotificationListenerIntent(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.BIND_VPN_SERVICE)) {
            return getVpnPermissionIntent(context)
        }
        if (AndroidVersion.isAndroid13() ||
            !PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS)
        ) {
            return PermissionUtils.getApplicationDetailsIntent(context)
        }
        return getNotifyPermissionIntent(context)
    }

    private fun isGrantedNotifyPermission(context: Context): Boolean {
        return NotificationManagerCompat.from(context).areNotificationsEnabled()
    }

    private fun getNotifyPermissionIntent(context: Context): Intent {
        val intent = if (AndroidVersion.isAndroid8()) {
            Intent("android.settings.APP_NOTIFICATION_SETTINGS").apply {
                putExtra("android.provider.extra.APP_PACKAGE", context.packageName)
            }
        } else {
            null
        }
        return if (intent == null || !PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }

    private fun isGrantedNotificationListenerPermission(context: Context): Boolean {
        if (AndroidVersion.isAndroid4_3()) {
            return NotificationManagerCompat
                .getEnabledListenerPackages(context)
                .contains(context.packageName)
        }
        return true
    }

    private fun getNotificationListenerIntent(context: Context): Intent {
        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }

    private fun isGrantedPackagePermission(context: Context): Boolean {
        if (!AndroidVersion.isAndroid5()) {
            return true
        }
        val appOpsManager = context.getSystemService("appops") as AppOpsManager
        val checkResult = if (AndroidVersion.isAndroid10()) {
            appOpsManager.unsafeCheckOpNoThrow(
                "android:get_usage_stats",
                context.applicationInfo.uid,
                context.packageName
            )
        } else {
            appOpsManager.checkOpNoThrow(
                "android:get_usage_stats",
                context.applicationInfo.uid,
                context.packageName
            )
        }
        return checkResult == 0
    }

    private fun getPackagePermissionIntent(context: Context): Intent {
        val intent = if (AndroidVersion.isAndroid5()) {
            Intent("android.settings.USAGE_ACCESS_SETTINGS").apply {
                if (AndroidVersion.isAndroid10()) {
                    data = PermissionUtils.getPackageNameUri(context)
                }
            }
        } else {
            null
        }
        return if (intent == null || !PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }

    private fun isGrantedVpnPermission(context: Context): Boolean = VpnService.prepare(context) == null

    private fun getVpnPermissionIntent(context: Context): Intent {
        val intent = VpnService.prepare(context)
        return if (intent == null || !PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }
}
