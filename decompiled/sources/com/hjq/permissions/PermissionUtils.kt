package com.hjq.permissions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.XmlResourceParser
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import java.util.ArrayList
import java.util.HashMap

object PermissionUtils {
    private val HANDLER: Handler = Handler(Looper.getMainLooper())

    @JvmStatic
    fun getAndroidNamespace(): String = "http://schemas.android.com/apk/res/android"

    @JvmStatic
    fun isSpecialPermission(permission: String): Boolean {
        return equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE) ||
            equalsPermission(permission, Permission.REQUEST_INSTALL_PACKAGES) ||
            equalsPermission(permission, Permission.SYSTEM_ALERT_WINDOW) ||
            equalsPermission(permission, Permission.WRITE_SETTINGS) ||
            equalsPermission(permission, Permission.NOTIFICATION_SERVICE) ||
            equalsPermission(permission, Permission.PACKAGE_USAGE_STATS) ||
            equalsPermission(permission, Permission.SCHEDULE_EXACT_ALARM) ||
            equalsPermission(permission, Permission.BIND_NOTIFICATION_LISTENER_SERVICE) ||
            equalsPermission(permission, Permission.ACCESS_NOTIFICATION_POLICY) ||
            equalsPermission(permission, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) ||
            equalsPermission(permission, Permission.BIND_VPN_SERVICE)
    }

    @JvmStatic
    fun checkSelfPermission(context: Context, permission: String): Boolean {
        return context.checkSelfPermission(permission) == 0
    }

    @JvmStatic
    fun shouldShowRequestPermissionRationale(activity: Activity, permission: String): Boolean {
        if (AndroidVersion.getAndroidVersionCode() == 31) {
            try {
                val packageManager = activity.application.packageManager
                return (PackageManager::class.java.getMethod(
                    "shouldShowRequestPermissionRationale",
                    String::class.java
                ).invoke(packageManager, permission) as Boolean)
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            }
        }
        return activity.shouldShowRequestPermissionRationale(permission)
    }

    @JvmStatic
    fun postDelayed(runnable: Runnable, delayMillis: Long) {
        HANDLER.postDelayed(runnable, delayMillis)
    }

    @JvmStatic
    fun postActivityResult(permissions: List<String>, runnable: Runnable) {
        var delayMillis = 300L
        val commonDelayMillis = if (AndroidVersion.isAndroid11()) 200L else 300L
        val manufacturer = Build.MANUFACTURER.lowercase()
        if (!manufacturer.contains("huawei")) {
            delayMillis =
                if (!manufacturer.contains("xiaomi") ||
                    !AndroidVersion.isAndroid11() ||
                    !containsPermission(permissions, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                ) {
                    commonDelayMillis
                } else {
                    1000L
                }
        } else if (!AndroidVersion.isAndroid8()) {
            delayMillis = 500L
        }
        HANDLER.postDelayed(runnable, delayMillis)
    }

    @JvmStatic
    fun isDebugMode(context: Context): Boolean {
        return context.applicationInfo.flags and 2 != 0
    }

    @JvmStatic
    fun getManifestPermissions(context: Context): HashMap<String, Int> {
        val manifestPermissions = HashMap<String, Int>()
        val parseAndroidManifest = parseAndroidManifest(context)
        if (parseAndroidManifest != null) {
            try {
                do {
                    if (parseAndroidManifest.eventType == 2 &&
                        "uses-permission" == parseAndroidManifest.name
                    ) {
                        manifestPermissions[
                            parseAndroidManifest.getAttributeValue(getAndroidNamespace(), "name")
                        ] = parseAndroidManifest.getAttributeIntValue(
                            getAndroidNamespace(),
                            "maxSdkVersion",
                            Int.MAX_VALUE
                        )
                    }
                } while (parseAndroidManifest.next() != 1)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: XmlPullParserException) {
                e.printStackTrace()
            } finally {
                parseAndroidManifest.close()
            }
        }
        if (manifestPermissions.isEmpty()) {
            try {
                val requestedPermissions = context.packageManager
                    .getPackageInfo(context.packageName, 4096).requestedPermissions
                if (requestedPermissions != null) {
                    for (permission in requestedPermissions) {
                        manifestPermissions[permission] = Int.MAX_VALUE
                    }
                }
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }
        return manifestPermissions
    }

    @JvmStatic
    fun optimizePermissionResults(activity: Activity, permissions: Array<String>, grantResults: IntArray) {
        for (index in permissions.indices) {
            val permission = permissions[index]
            var isSpecialPermission = PermissionApi.isSpecialPermission(permission)
            var optimizeGrantResult = true
            if (!AndroidVersion.isAndroid13() &&
                (equalsPermission(permission, Permission.POST_NOTIFICATIONS) ||
                    equalsPermission(permission, Permission.NEARBY_WIFI_DEVICES) ||
                    equalsPermission(permission, Permission.BODY_SENSORS_BACKGROUND) ||
                    equalsPermission(permission, Permission.READ_MEDIA_IMAGES) ||
                    equalsPermission(permission, Permission.READ_MEDIA_VIDEO) ||
                    equalsPermission(permission, Permission.READ_MEDIA_AUDIO))
            ) {
                isSpecialPermission = true
            }
            if (!AndroidVersion.isAndroid12() &&
                (equalsPermission(permission, Permission.BLUETOOTH_SCAN) ||
                    equalsPermission(permission, Permission.BLUETOOTH_CONNECT) ||
                    equalsPermission(permission, Permission.BLUETOOTH_ADVERTISE))
            ) {
                isSpecialPermission = true
            }
            if (!AndroidVersion.isAndroid10() &&
                (equalsPermission(permission, Permission.ACCESS_BACKGROUND_LOCATION) ||
                    equalsPermission(permission, Permission.ACTIVITY_RECOGNITION) ||
                    equalsPermission(permission, Permission.ACCESS_MEDIA_LOCATION))
            ) {
                isSpecialPermission = true
            }
            if (!AndroidVersion.isAndroid9() &&
                equalsPermission(permission, Permission.ACCEPT_HANDOVER)
            ) {
                isSpecialPermission = true
            }
            if (AndroidVersion.isAndroid8() ||
                (!equalsPermission(permission, Permission.ANSWER_PHONE_CALLS) &&
                    !equalsPermission(permission, Permission.READ_PHONE_NUMBERS))
            ) {
                optimizeGrantResult = isSpecialPermission
            }
            if (optimizeGrantResult) {
                grantResults[index] = if (PermissionApi.isGrantedPermission(activity, permission)) {
                    0
                } else {
                    -1
                }
            }
        }
    }

    @JvmStatic
    fun <T> asArrayList(vararg values: T): ArrayList<T> {
        val arrayList = ArrayList<T>(values.size)
        if (values.isNotEmpty()) {
            for (value in values) {
                arrayList.add(value)
            }
        }
        return arrayList
    }

    @JvmStatic
    fun <T> asArrayLists(vararg arrays: Array<out T>?): ArrayList<T> {
        val arrayList = ArrayList<T>()
        if (arrays.isNotEmpty()) {
            for (array in arrays) {
                if (array != null) {
                    arrayList.addAll(asArrayList(*array))
                }
            }
        }
        return arrayList
    }

    @JvmStatic
    fun findActivity(context: Context): Activity? {
        var currentContext = context
        while (true) {
            if (currentContext is Activity) {
                return currentContext
            }
            if (currentContext !is ContextWrapper) {
                return null
            }
            currentContext = currentContext.baseContext ?: return null
        }
    }

    @JvmStatic
    fun findApkPathCookie(context: Context, apkPath: String): Int {
        val assets: AssetManager = context.assets
        try {
            val targetSdkVersionCode = AndroidVersion.getTargetSdkVersionCode(context)
            val androidVersionCode = AndroidVersion.getAndroidVersionCode()
            if (targetSdkVersionCode >= 28 && androidVersionCode >= 28 && androidVersionCode < 30) {
                val cookie = AssetManager::class.java.getDeclaredMethod(
                    "findCookieForPath",
                    String::class.java
                ).invoke(assets, apkPath)
                if (cookie is Int) {
                    return cookie
                }
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        try {
            val cookie = assets.javaClass.getDeclaredMethod(
                "addAssetPath",
                String::class.java
            ).invoke(assets, apkPath)
            if (cookie is Int) {
                return cookie
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        return 0
    }

    @JvmStatic
    fun parseAndroidManifest(context: Context): XmlResourceParser? {
        val apkPathCookie = findApkPathCookie(context, context.applicationInfo.sourceDir)
        if (apkPathCookie == 0) {
            return null
        }
        try {
            val androidManifest = context.assets.openXmlResourceParser(
                apkPathCookie,
                "AndroidManifest.xml"
            )
            do {
                if (androidManifest.eventType == 2 &&
                    "manifest" == androidManifest.name &&
                    TextUtils.equals(
                        context.packageName,
                        androidManifest.getAttributeValue(null, "package")
                    )
                ) {
                    return androidManifest
                }
            } while (androidManifest.next() != 1)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }
        return null
    }

    @JvmStatic
    fun isScopedStorage(context: Context): Boolean {
        return try {
            val metaData: Bundle? = context.packageManager
                .getApplicationInfo(context.packageName, 128).metaData
            if (metaData == null || !metaData.containsKey("ScopedStorage")) {
                false
            } else {
                java.lang.Boolean.parseBoolean(metaData["ScopedStorage"].toString())
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            false
        }
    }

    @JvmStatic
    fun lockActivityOrientation(activity: Activity) {
        try {
            val orientation = activity.resources.configuration.orientation
            var requestedOrientation = 1
            if (orientation == 1) {
                if (isActivityReverse(activity)) {
                    requestedOrientation = 9
                }
                activity.requestedOrientation = requestedOrientation
            } else if (orientation == 2) {
                activity.requestedOrientation = if (isActivityReverse(activity)) 8 else 0
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    @JvmStatic
    fun isActivityReverse(activity: Activity): Boolean {
        val rotation = if (AndroidVersion.isAndroid11()) {
            activity.display!!.rotation
        } else {
            activity.windowManager.defaultDisplay.rotation
        }
        return rotation == 2 || rotation == 3
    }

    @JvmStatic
    fun areActivityIntent(context: Context, intent: Intent): Boolean {
        return context.packageManager.queryIntentActivities(intent, 65536).isNotEmpty()
    }

    @JvmStatic
    fun getApplicationDetailsIntent(context: Context): Intent {
        val appDetailsIntent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS")
        appDetailsIntent.data = getPackageNameUri(context)
        if (areActivityIntent(context, appDetailsIntent)) {
            return appDetailsIntent
        }
        val appSettingsIntent = Intent("android.settings.APPLICATION_SETTINGS")
        return if (!areActivityIntent(context, appSettingsIntent)) {
            Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS")
        } else {
            appSettingsIntent
        }
    }

    @JvmStatic
    fun getPackageNameUri(context: Context): Uri {
        return Uri.parse("package:" + context.packageName)
    }

    @JvmStatic
    fun getSmartPermissionIntent(context: Context, permissions: List<String>?): Intent {
        if (permissions.isNullOrEmpty() || !PermissionApi.containsSpecialPermission(permissions)) {
            return getApplicationDetailsIntent(context)
        }
        val size = permissions.size
        if (size == 1) {
            return PermissionApi.getPermissionIntent(context, permissions[0])
        }
        if (size != 2) {
            if (size == 3 &&
                AndroidVersion.isAndroid11() &&
                containsPermission(permissions, Permission.MANAGE_EXTERNAL_STORAGE) &&
                containsPermission(permissions, Permission.READ_EXTERNAL_STORAGE) &&
                containsPermission(permissions, Permission.WRITE_EXTERNAL_STORAGE)
            ) {
                return PermissionApi.getPermissionIntent(context, Permission.MANAGE_EXTERNAL_STORAGE)
            }
        } else if (!AndroidVersion.isAndroid13() &&
            containsPermission(permissions, Permission.NOTIFICATION_SERVICE) &&
            containsPermission(permissions, Permission.POST_NOTIFICATIONS)
        ) {
            return PermissionApi.getPermissionIntent(context, Permission.NOTIFICATION_SERVICE)
        }
        return getApplicationDetailsIntent(context)
    }

    @JvmStatic
    fun equalsPermission(permission: String?, anotherPermission: String?): Boolean {
        if (permission == null || anotherPermission == null || permission.length != anotherPermission.length) {
            return false
        }
        for (index in permission.length - 1 downTo 0) {
            if (permission[index] != anotherPermission[index]) {
                return false
            }
        }
        return true
    }

    @JvmStatic
    fun containsPermission(permissions: Collection<String>?, permission: String?): Boolean {
        if (!permissions.isNullOrEmpty() && permission != null) {
            for (item in permissions) {
                if (equalsPermission(item, permission)) {
                    return true
                }
            }
        }
        return false
    }
}
