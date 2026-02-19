package com.hjq.permissions

import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.Intent

internal open class PermissionDelegateImplV31 : PermissionDelegateImplV30() {
    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.SCHEDULE_EXACT_ALARM)) {
            return isGrantedAlarmPermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_SCAN) ||
            PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_CONNECT) ||
            PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_ADVERTISE)
        ) {
            return PermissionUtils.checkSelfPermission(context, permission)
        }
        return super.isGrantedPermission(context, permission)
    }

    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean {
        if (PermissionUtils.equalsPermission(permission, Permission.SCHEDULE_EXACT_ALARM)) {
            return false
        }
        if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_SCAN) ||
            PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_CONNECT) ||
            PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_ADVERTISE)
        ) {
            return !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        if (activity.applicationInfo.targetSdkVersion < 31 ||
            !PermissionUtils.equalsPermission(permission, Permission.ACCESS_BACKGROUND_LOCATION)
        ) {
            return super.isPermissionPermanentDenied(activity, permission)
        }
        if (PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION) ||
            PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_COARSE_LOCATION)
        ) {
            return !PermissionUtils.checkSelfPermission(activity, permission) &&
                !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
        }
        return !PermissionUtils.shouldShowRequestPermissionRationale(
            activity,
            Permission.ACCESS_FINE_LOCATION
        ) && !PermissionUtils.shouldShowRequestPermissionRationale(
            activity,
            Permission.ACCESS_COARSE_LOCATION
        )
    }

    override fun getPermissionIntent(context: Context, permission: String): Intent {
        if (PermissionUtils.equalsPermission(permission, Permission.SCHEDULE_EXACT_ALARM)) {
            return getAlarmPermissionIntent(context)
        }
        return super.getPermissionIntent(context, permission)
    }

    private fun isGrantedAlarmPermission(context: Context): Boolean {
        return context.getSystemService(AlarmManager::class.java).canScheduleExactAlarms()
    }

    private fun getAlarmPermissionIntent(context: Context): Intent {
        val intent = Intent("android.settings.REQUEST_SCHEDULE_EXACT_ALARM")
        intent.data = PermissionUtils.getPackageNameUri(context)
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }
}
