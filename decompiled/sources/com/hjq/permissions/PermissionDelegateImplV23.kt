package com.hjq.permissions

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.provider.Settings

internal open class PermissionDelegateImplV23 : PermissionDelegateImplV14() {
    override fun isGrantedPermission(context: Context, permission: String): Boolean {
        if (!PermissionUtils.isSpecialPermission(permission)) {
            if (!AndroidVersion.isAndroid13()) {
                if (PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS)) {
                    return super.isGrantedPermission(context, permission)
                }
                if (PermissionUtils.equalsPermission(permission, Permission.NEARBY_WIFI_DEVICES)) {
                    return PermissionUtils.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION)
                }
                if (PermissionUtils.equalsPermission(permission, Permission.BODY_SENSORS_BACKGROUND)) {
                    return PermissionUtils.checkSelfPermission(context, Permission.BODY_SENSORS)
                }
                if (PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_IMAGES) ||
                    PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_VIDEO) ||
                    PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_AUDIO)
                ) {
                    return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE)
                }
            }
            if (!AndroidVersion.isAndroid12()) {
                if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_SCAN)) {
                    return PermissionUtils.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION)
                }
                if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_CONNECT) ||
                    PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_ADVERTISE)
                ) {
                    return true
                }
            }
            if (!AndroidVersion.isAndroid10()) {
                if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_BACKGROUND_LOCATION)) {
                    return PermissionUtils.checkSelfPermission(context, Permission.ACCESS_FINE_LOCATION)
                }
                if (PermissionUtils.equalsPermission(permission, Permission.ACTIVITY_RECOGNITION)) {
                    return PermissionUtils.checkSelfPermission(context, Permission.BODY_SENSORS)
                }
                if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_MEDIA_LOCATION)) {
                    return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE)
                }
            }
            if (!AndroidVersion.isAndroid9() &&
                PermissionUtils.equalsPermission(permission, Permission.ACCEPT_HANDOVER)
            ) {
                return true
            }
            if (!AndroidVersion.isAndroid8()) {
                if (PermissionUtils.equalsPermission(permission, Permission.ANSWER_PHONE_CALLS)) {
                    return true
                }
                if (PermissionUtils.equalsPermission(permission, Permission.READ_PHONE_NUMBERS)) {
                    return PermissionUtils.checkSelfPermission(context, Permission.READ_PHONE_STATE)
                }
            }
            return PermissionUtils.checkSelfPermission(context, permission)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.SYSTEM_ALERT_WINDOW)) {
            return isGrantedWindowPermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.WRITE_SETTINGS)) {
            return isGrantedSettingPermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_NOTIFICATION_POLICY)) {
            return isGrantedNotDisturbPermission(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)) {
            return isGrantedIgnoreBatteryPermission(context)
        }
        if (AndroidVersion.isAndroid11() ||
            !PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE)
        ) {
            return super.isGrantedPermission(context, permission)
        }
        return PermissionUtils.checkSelfPermission(context, Permission.READ_EXTERNAL_STORAGE) &&
            PermissionUtils.checkSelfPermission(context, Permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean {
        if (PermissionUtils.isSpecialPermission(permission)) {
            return false
        }
        if (!AndroidVersion.isAndroid13()) {
            if (PermissionUtils.equalsPermission(permission, Permission.POST_NOTIFICATIONS)) {
                return super.isPermissionPermanentDenied(activity, permission)
            }
            if (PermissionUtils.equalsPermission(permission, Permission.NEARBY_WIFI_DEVICES)) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.ACCESS_FINE_LOCATION
                    )
            }
            if (PermissionUtils.equalsPermission(permission, Permission.BODY_SENSORS_BACKGROUND)) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.BODY_SENSORS) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.BODY_SENSORS
                    )
            }
            if (PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_IMAGES) ||
                PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_VIDEO) ||
                PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_AUDIO)
            ) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.READ_EXTERNAL_STORAGE) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.READ_EXTERNAL_STORAGE
                    )
            }
        }
        if (!AndroidVersion.isAndroid12()) {
            if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_SCAN)) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.ACCESS_FINE_LOCATION
                    )
            }
            if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_CONNECT) ||
                PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_ADVERTISE)
            ) {
                return false
            }
        }
        if (!AndroidVersion.isAndroid10()) {
            if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_BACKGROUND_LOCATION)) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.ACCESS_FINE_LOCATION) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.ACCESS_FINE_LOCATION
                    )
            }
            if (PermissionUtils.equalsPermission(permission, Permission.ACTIVITY_RECOGNITION)) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.BODY_SENSORS) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.BODY_SENSORS
                    )
            }
            if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_MEDIA_LOCATION)) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.READ_EXTERNAL_STORAGE) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.READ_EXTERNAL_STORAGE
                    )
            }
        }
        if (!AndroidVersion.isAndroid9() &&
            PermissionUtils.equalsPermission(permission, Permission.ACCEPT_HANDOVER)
        ) {
            return false
        }
        if (!AndroidVersion.isAndroid8()) {
            if (PermissionUtils.equalsPermission(permission, Permission.ANSWER_PHONE_CALLS)) {
                return false
            }
            if (PermissionUtils.equalsPermission(permission, Permission.READ_PHONE_NUMBERS)) {
                return !PermissionUtils.checkSelfPermission(activity, Permission.READ_PHONE_STATE) &&
                    !PermissionUtils.shouldShowRequestPermissionRationale(
                        activity,
                        Permission.READ_PHONE_STATE
                    )
            }
        }
        return !PermissionUtils.checkSelfPermission(activity, permission) &&
            !PermissionUtils.shouldShowRequestPermissionRationale(activity, permission)
    }

    override fun getPermissionIntent(context: Context, permission: String): Intent {
        if (PermissionUtils.equalsPermission(permission, Permission.SYSTEM_ALERT_WINDOW)) {
            return getWindowPermissionIntent(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.WRITE_SETTINGS)) {
            return getSettingPermissionIntent(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_NOTIFICATION_POLICY)) {
            return getNotDisturbPermissionIntent(context)
        }
        if (PermissionUtils.equalsPermission(permission, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)) {
            return getIgnoreBatteryPermissionIntent(context)
        }
        return super.getPermissionIntent(context, permission)
    }

    private fun isGrantedWindowPermission(context: Context): Boolean = Settings.canDrawOverlays(context)

    private fun getWindowPermissionIntent(context: Context): Intent {
        val intent = Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION")
        intent.data = PermissionUtils.getPackageNameUri(context)
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }

    private fun isGrantedSettingPermission(context: Context): Boolean {
        if (AndroidVersion.isAndroid6()) {
            return Settings.System.canWrite(context)
        }
        return true
    }

    private fun getSettingPermissionIntent(context: Context): Intent {
        val intent = Intent("android.settings.action.MANAGE_WRITE_SETTINGS")
        intent.data = PermissionUtils.getPackageNameUri(context)
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }

    private fun isGrantedNotDisturbPermission(context: Context): Boolean {
        return context.getSystemService(NotificationManager::class.java)
            .isNotificationPolicyAccessGranted
    }

    private fun getNotDisturbPermissionIntent(context: Context): Intent {
        var intent: Intent? = if (AndroidVersion.isAndroid10()) {
            Intent("android.settings.NOTIFICATION_POLICY_ACCESS_DETAIL_SETTINGS").apply {
                data = PermissionUtils.getPackageNameUri(context)
            }
        } else {
            null
        }
        if (intent == null || !PermissionUtils.areActivityIntent(context, intent)) {
            intent = Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS")
        }
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }

    private fun isGrantedIgnoreBatteryPermission(context: Context): Boolean {
        return context.getSystemService(PowerManager::class.java)
            .isIgnoringBatteryOptimizations(context.packageName)
    }

    private fun getIgnoreBatteryPermissionIntent(context: Context): Intent {
        var intent = Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS")
        intent.data = PermissionUtils.getPackageNameUri(context)
        if (!PermissionUtils.areActivityIntent(context, intent)) {
            intent = Intent("android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS")
        }
        return if (!PermissionUtils.areActivityIntent(context, intent)) {
            PermissionUtils.getApplicationDetailsIntent(context)
        } else {
            intent
        }
    }
}
