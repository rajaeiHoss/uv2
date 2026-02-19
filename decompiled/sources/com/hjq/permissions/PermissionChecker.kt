package com.hjq.permissions

import android.app.Activity
import android.content.Context
import android.content.res.XmlResourceParser
import android.os.Build
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.lang.reflect.Field
import java.util.ArrayList
import java.util.HashMap

object PermissionChecker {
    @JvmStatic
    fun checkActivityStatus(activity: Activity?, checkMode: Boolean): Boolean {
        if (activity == null) {
            if (!checkMode) {
                return false
            }
            throw IllegalArgumentException("The instance of the context must be an activity object")
        }
        if (activity.isFinishing) {
            if (!checkMode) {
                return false
            }
            throw IllegalStateException(
                "The activity has been finishing, please manually determine the status of the activity"
            )
        }
        if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed) {
            return true
        }
        if (!checkMode) {
            return false
        }
        throw IllegalStateException(
            "The activity has been destroyed, please manually determine the status of the activity"
        )
    }

    @JvmStatic
    fun checkPermissionArgument(permissions: List<String>?, checkMode: Boolean): Boolean {
        if (permissions.isNullOrEmpty()) {
            if (!checkMode) {
                return false
            }
            throw IllegalArgumentException("The requested permission cannot be empty")
        }
        if (Build.VERSION.SDK_INT <= 32 && checkMode) {
            val dangerousAndSpecialPermissions = ArrayList<String>()
            val declaredFields: Array<Field> = Permission::class.java.declaredFields
            if (declaredFields.isEmpty()) {
                return true
            }
            for (field in declaredFields) {
                if (String::class.java == field.type) {
                    try {
                        dangerousAndSpecialPermissions.add(field.get(null) as String)
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    }
                }
            }
            for (permission in permissions) {
                if (!PermissionUtils.containsPermission(dangerousAndSpecialPermissions, permission)) {
                    throw IllegalArgumentException(
                        "The " +
                            permission +
                            " is not a dangerous permission or special permission, please do not apply dynamically"
                    )
                }
            }
        }
        return true
    }

    @JvmStatic
    fun checkMediaLocationPermission(context: Context, permissions: List<String>) {
        if (!PermissionUtils.containsPermission(permissions, Permission.ACCESS_MEDIA_LOCATION)) {
            return
        }
        for (permission in permissions) {
            if (!PermissionUtils.equalsPermission(permission, Permission.ACCESS_MEDIA_LOCATION) &&
                !PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_IMAGES) &&
                !PermissionUtils.equalsPermission(permission, Permission.READ_EXTERNAL_STORAGE) &&
                !PermissionUtils.equalsPermission(permission, Permission.WRITE_EXTERNAL_STORAGE) &&
                !PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE)
            ) {
                throw IllegalArgumentException(
                    "Because it includes access media location permissions, do not apply for permissions unrelated to access media location"
                )
            }
        }
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 33) {
            if (!PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_IMAGES) &&
                !PermissionUtils.containsPermission(permissions, Permission.MANAGE_EXTERNAL_STORAGE)
            ) {
                throw IllegalArgumentException(
                    "You must add android.permission.READ_MEDIA_IMAGES or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights"
                )
            }
        } else if (!PermissionUtils.containsPermission(permissions, Permission.READ_EXTERNAL_STORAGE) &&
            !PermissionUtils.containsPermission(permissions, Permission.MANAGE_EXTERNAL_STORAGE)
        ) {
            throw IllegalArgumentException(
                "You must add android.permission.READ_EXTERNAL_STORAGE or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights"
            )
        }
    }

    @JvmStatic
    fun checkStoragePermission(context: Context, permissions: List<String>) {
        if (!PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_IMAGES) &&
            !PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_VIDEO) &&
            !PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_AUDIO) &&
            !PermissionUtils.containsPermission(permissions, Permission.MANAGE_EXTERNAL_STORAGE) &&
            !PermissionUtils.containsPermission(permissions, Permission.READ_EXTERNAL_STORAGE) &&
            !PermissionUtils.containsPermission(permissions, Permission.WRITE_EXTERNAL_STORAGE)
        ) {
            return
        }
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 33 &&
            (PermissionUtils.containsPermission(permissions, Permission.READ_EXTERNAL_STORAGE) ||
                PermissionUtils.containsPermission(permissions, Permission.WRITE_EXTERNAL_STORAGE))
        ) {
            throw IllegalArgumentException(
                "When targetSdkVersion >= 33 should use android.permission.READ_MEDIA_IMAGES, android.permission.READ_MEDIA_VIDEO, android.permission.READ_MEDIA_AUDIO instead of android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE"
            )
        }
        if (!PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_IMAGES) &&
            !PermissionUtils.containsPermission(permissions, Permission.ACCESS_MEDIA_LOCATION)
        ) {
            val isScopedStorage = PermissionUtils.isScopedStorage(context)
            val parseAndroidManifest: XmlResourceParser? = PermissionUtils.parseAndroidManifest(context)
            if (parseAndroidManifest != null) {
                try {
                    while (true) {
                        if (parseAndroidManifest.eventType == 2 &&
                            "application" == parseAndroidManifest.name
                        ) {
                            val targetSdkVersionCode = AndroidVersion.getTargetSdkVersionCode(context)
                            val requestLegacyExternalStorage =
                                parseAndroidManifest.getAttributeBooleanValue(
                                    PermissionUtils.getAndroidNamespace(),
                                    "requestLegacyExternalStorage",
                                    false
                                )
                            if (targetSdkVersionCode >= 29 && !requestLegacyExternalStorage &&
                                (PermissionUtils.containsPermission(
                                    permissions,
                                    Permission.MANAGE_EXTERNAL_STORAGE
                                ) || !isScopedStorage)
                            ) {
                                throw IllegalStateException(
                                    "Please register the android:requestLegacyExternalStorage=\"true\" attribute in the AndroidManifest.xml file, otherwise it will cause incompatibility with the old version"
                                )
                            }
                            if (targetSdkVersionCode >= 30 &&
                                !PermissionUtils.containsPermission(
                                    permissions,
                                    Permission.MANAGE_EXTERNAL_STORAGE
                                ) &&
                                !isScopedStorage
                            ) {
                                throw IllegalArgumentException(
                                    "The storage permission application is abnormal. If you have adapted the scope storage, please register the <meta-data android:name=\"ScopedStorage\" android:value=\"true\" /> attribute in the AndroidManifest.xml file. If there is no adaptation scope storage, please use android.permission.MANAGE_EXTERNAL_STORAGE to apply for permission"
                                )
                            }
                        }
                        if (parseAndroidManifest.next() == 1) {
                            break
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: XmlPullParserException) {
                    e.printStackTrace()
                } finally {
                    parseAndroidManifest.close()
                }
            }
        }
    }

    @JvmStatic
    fun checkBodySensorsPermission(permissions: List<String>) {
        if (PermissionUtils.containsPermission(permissions, Permission.BODY_SENSORS_BACKGROUND)) {
            if (!PermissionUtils.containsPermission(
                    permissions,
                    Permission.BODY_SENSORS_BACKGROUND
                ) ||
                PermissionUtils.containsPermission(permissions, Permission.BODY_SENSORS)
            ) {
                for (permission in permissions) {
                    if (PermissionUtils.equalsPermission(
                            permission,
                            Permission.ACCESS_BACKGROUND_LOCATION
                        )
                    ) {
                        throw IllegalArgumentException(
                            "Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_BACKGROUND_LOCATION at the same time is not supported"
                        )
                    } else if (PermissionUtils.equalsPermission(
                            permission,
                            Permission.ACCESS_MEDIA_LOCATION
                        )
                    ) {
                        throw IllegalArgumentException(
                            "Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_MEDIA_LOCATION at the same time is not supported"
                        )
                    }
                }
                return
            }
            throw IllegalArgumentException(
                "Applying for background sensor permissions must contain android.permission.BODY_SENSORS"
            )
        }
    }

    @JvmStatic
    fun checkLocationPermission(context: Context, permissions: List<String>) {
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 31 &&
            PermissionUtils.containsPermission(permissions, Permission.ACCESS_FINE_LOCATION) &&
            !PermissionUtils.containsPermission(permissions, Permission.ACCESS_COARSE_LOCATION)
        ) {
            throw IllegalArgumentException(
                "If your app targets Android 12 or higher and requests the ACCESS_FINE_LOCATION runtime permission, you must also request the ACCESS_COARSE_LOCATION permission. You must include both permissions in a single runtime request."
            )
        } else if (PermissionUtils.containsPermission(
                permissions,
                Permission.ACCESS_BACKGROUND_LOCATION
            )
        ) {
            if (!PermissionUtils.containsPermission(
                    permissions,
                    Permission.ACCESS_COARSE_LOCATION
                ) ||
                PermissionUtils.containsPermission(permissions, Permission.ACCESS_FINE_LOCATION)
            ) {
                for (permission in permissions) {
                    if (!PermissionUtils.equalsPermission(permission, Permission.ACCESS_FINE_LOCATION) &&
                        !PermissionUtils.equalsPermission(
                            permission,
                            Permission.ACCESS_COARSE_LOCATION
                        ) &&
                        !PermissionUtils.equalsPermission(
                            permission,
                            Permission.ACCESS_BACKGROUND_LOCATION
                        )
                    ) {
                        throw IllegalArgumentException(
                            "Because it includes background location permissions, do not apply for permissions unrelated to location"
                        )
                    }
                }
                return
            }
            throw IllegalArgumentException(
                "Applying for background positioning permissions must include android.permission.ACCESS_FINE_LOCATION"
            )
        }
    }

    @JvmStatic
    fun checkTargetSdkVersion(context: Context, permissions: List<String>) {
        val requiredSdkVersion =
            if (PermissionUtils.containsPermission(permissions, Permission.POST_NOTIFICATIONS) ||
                PermissionUtils.containsPermission(permissions, Permission.NEARBY_WIFI_DEVICES) ||
                PermissionUtils.containsPermission(permissions, Permission.BODY_SENSORS_BACKGROUND) ||
                PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_IMAGES) ||
                PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_VIDEO) ||
                PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_AUDIO)
            ) {
                33
            } else if (PermissionUtils.containsPermission(
                    permissions,
                    Permission.BLUETOOTH_SCAN
                ) ||
                PermissionUtils.containsPermission(permissions, Permission.BLUETOOTH_CONNECT) ||
                PermissionUtils.containsPermission(permissions, Permission.BLUETOOTH_ADVERTISE) ||
                PermissionUtils.containsPermission(permissions, Permission.SCHEDULE_EXACT_ALARM)
            ) {
                31
            } else if (PermissionUtils.containsPermission(
                    permissions,
                    Permission.MANAGE_EXTERNAL_STORAGE
                )
            ) {
                30
            } else if (PermissionUtils.containsPermission(
                    permissions,
                    Permission.ACCESS_BACKGROUND_LOCATION
                ) ||
                PermissionUtils.containsPermission(permissions, Permission.ACTIVITY_RECOGNITION) ||
                PermissionUtils.containsPermission(permissions, Permission.ACCESS_MEDIA_LOCATION)
            ) {
                29
            } else if (PermissionUtils.containsPermission(permissions, Permission.ACCEPT_HANDOVER)) {
                28
            } else if (PermissionUtils.containsPermission(
                    permissions,
                    Permission.REQUEST_INSTALL_PACKAGES
                ) ||
                PermissionUtils.containsPermission(permissions, Permission.ANSWER_PHONE_CALLS) ||
                PermissionUtils.containsPermission(permissions, Permission.READ_PHONE_NUMBERS)
            ) {
                26
            } else {
                23
            }
        if (AndroidVersion.getTargetSdkVersionCode(context) < requiredSdkVersion) {
            throw RuntimeException(
                "The targetSdkVersion SDK must be " +
                    requiredSdkVersion +
                    " or more, if you do not want to upgrade targetSdkVersion, please apply with the old permissions"
            )
        }
    }

    @JvmStatic
    fun checkManifestPermissions(context: Context, permissions: List<String>) {
        val manifestPermissions: HashMap<String, Int> = PermissionUtils.getManifestPermissions(context)
        if (manifestPermissions.isEmpty()) {
            throw IllegalStateException(
                "No permissions are registered in the AndroidManifest.xml file"
            )
        }
        val minSdkVersion =
            if (Build.VERSION.SDK_INT >= 24) context.applicationInfo.minSdkVersion else 23
        for (permission in permissions) {
            if (!PermissionUtils.equalsPermission(permission, Permission.NOTIFICATION_SERVICE) &&
                !PermissionUtils.equalsPermission(
                    permission,
                    Permission.BIND_NOTIFICATION_LISTENER_SERVICE
                ) &&
                !PermissionUtils.equalsPermission(permission, Permission.BIND_VPN_SERVICE)
            ) {
                if (PermissionUtils.equalsPermission(permission, Permission.BODY_SENSORS_BACKGROUND)) {
                    checkManifestPermission(
                        manifestPermissions,
                        Permission.BODY_SENSORS,
                        Int.MAX_VALUE
                    )
                }
                if (PermissionUtils.equalsPermission(permission, Permission.ACCESS_BACKGROUND_LOCATION)) {
                    if (AndroidVersion.getTargetSdkVersionCode(context) >= 31) {
                        checkManifestPermission(
                            manifestPermissions,
                            Permission.ACCESS_FINE_LOCATION,
                            30
                        )
                        checkManifestPermission(
                            manifestPermissions,
                            Permission.ACCESS_COARSE_LOCATION,
                            Int.MAX_VALUE
                        )
                    } else {
                        checkManifestPermission(
                            manifestPermissions,
                            Permission.ACCESS_FINE_LOCATION,
                            Int.MAX_VALUE
                        )
                    }
                }
                if (minSdkVersion < 33) {
                    if (PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_IMAGES) ||
                        PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_VIDEO) ||
                        PermissionUtils.equalsPermission(permission, Permission.READ_MEDIA_AUDIO)
                    ) {
                        checkManifestPermission(
                            manifestPermissions,
                            Permission.READ_EXTERNAL_STORAGE,
                            32
                        )
                    }
                    if (PermissionUtils.equalsPermission(permission, Permission.NEARBY_WIFI_DEVICES)) {
                        checkManifestPermission(
                            manifestPermissions,
                            Permission.ACCESS_FINE_LOCATION,
                            32
                        )
                    }
                }
                if (minSdkVersion < 31) {
                    if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_SCAN)) {
                        checkManifestPermission(
                            manifestPermissions,
                            "android.permission.BLUETOOTH_ADMIN",
                            30
                        )
                        checkManifestPermission(
                            manifestPermissions,
                            Permission.ACCESS_FINE_LOCATION,
                            30
                        )
                    }
                    if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_CONNECT)) {
                        checkManifestPermission(
                            manifestPermissions,
                            "android.permission.BLUETOOTH",
                            30
                        )
                    }
                    if (PermissionUtils.equalsPermission(permission, Permission.BLUETOOTH_ADVERTISE)) {
                        checkManifestPermission(
                            manifestPermissions,
                            "android.permission.BLUETOOTH_ADMIN",
                            30
                        )
                    }
                }
                if (minSdkVersion < 30 &&
                    PermissionUtils.equalsPermission(permission, Permission.MANAGE_EXTERNAL_STORAGE)
                ) {
                    checkManifestPermission(
                        manifestPermissions,
                        Permission.READ_EXTERNAL_STORAGE,
                        29
                    )
                    checkManifestPermission(
                        manifestPermissions,
                        Permission.WRITE_EXTERNAL_STORAGE,
                        29
                    )
                }
                if (minSdkVersion < 29 &&
                    PermissionUtils.equalsPermission(permission, Permission.ACTIVITY_RECOGNITION)
                ) {
                    checkManifestPermission(manifestPermissions, Permission.BODY_SENSORS, 28)
                }
                if (minSdkVersion < 26 &&
                    PermissionUtils.equalsPermission(permission, Permission.READ_PHONE_NUMBERS)
                ) {
                    checkManifestPermission(manifestPermissions, Permission.READ_PHONE_STATE, 25)
                }
                checkManifestPermission(manifestPermissions, permission, Int.MAX_VALUE)
            }
        }
    }

    @JvmStatic
    fun checkManifestPermission(
        manifestPermissions: HashMap<String, Int>,
        permission: String,
        maxSdkVersion: Int
    ) {
        if (!manifestPermissions.containsKey(permission)) {
            throw IllegalStateException(
                "Please register permissions in the AndroidManifest.xml file <uses-permission android:name=\"" +
                    permission +
                    "\" />"
            )
        }
        val declaredMaxSdkVersion = manifestPermissions[permission]
        if (declaredMaxSdkVersion != null && declaredMaxSdkVersion < maxSdkVersion) {
            val requireMessage =
                if (maxSdkVersion != Int.MAX_VALUE) {
                    "the minimum requirement for maxSdkVersion is $maxSdkVersion"
                } else {
                    "please delete the android:maxSdkVersion=\"$declaredMaxSdkVersion\" attribute"
                }
            throw IllegalArgumentException(
                "The AndroidManifest.xml file <uses-permission android:name=\"" +
                    permission +
                    "\" android:maxSdkVersion=\"" +
                    declaredMaxSdkVersion +
                    "\" /> does not meet the requirements, " +
                    requireMessage
            )
        }
    }

    @JvmStatic
    fun optimizeDeprecatedPermission(permissions: MutableList<String>) {
        if (!AndroidVersion.isAndroid13()) {
            if (PermissionUtils.containsPermission(permissions, Permission.POST_NOTIFICATIONS) &&
                !PermissionUtils.containsPermission(permissions, Permission.NOTIFICATION_SERVICE)
            ) {
                permissions.add(Permission.NOTIFICATION_SERVICE)
            }
            if (PermissionUtils.containsPermission(permissions, Permission.NEARBY_WIFI_DEVICES) &&
                !PermissionUtils.containsPermission(permissions, Permission.ACCESS_FINE_LOCATION)
            ) {
                permissions.add(Permission.ACCESS_FINE_LOCATION)
            }
            if ((PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_IMAGES) ||
                    PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_VIDEO) ||
                    PermissionUtils.containsPermission(permissions, Permission.READ_MEDIA_AUDIO)) &&
                !PermissionUtils.containsPermission(permissions, Permission.READ_EXTERNAL_STORAGE)
            ) {
                permissions.add(Permission.READ_EXTERNAL_STORAGE)
            }
        }
        if (!AndroidVersion.isAndroid12() &&
            PermissionUtils.containsPermission(permissions, Permission.BLUETOOTH_SCAN) &&
            !PermissionUtils.containsPermission(permissions, Permission.ACCESS_FINE_LOCATION)
        ) {
            permissions.add(Permission.ACCESS_FINE_LOCATION)
        }
        if (PermissionUtils.containsPermission(permissions, Permission.MANAGE_EXTERNAL_STORAGE)) {
            if (PermissionUtils.containsPermission(permissions, Permission.READ_EXTERNAL_STORAGE) ||
                PermissionUtils.containsPermission(permissions, Permission.WRITE_EXTERNAL_STORAGE)
            ) {
                throw IllegalArgumentException(
                    "If you have applied for MANAGE_EXTERNAL_STORAGE permissions, do not apply for the READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE permissions"
                )
            } else if (!AndroidVersion.isAndroid11()) {
                permissions.add(Permission.READ_EXTERNAL_STORAGE)
                permissions.add(Permission.WRITE_EXTERNAL_STORAGE)
            }
        }
        if (!AndroidVersion.isAndroid10() &&
            PermissionUtils.containsPermission(permissions, Permission.ACTIVITY_RECOGNITION) &&
            !PermissionUtils.containsPermission(permissions, Permission.BODY_SENSORS)
        ) {
            permissions.add(Permission.BODY_SENSORS)
        }
        if (!AndroidVersion.isAndroid8() &&
            PermissionUtils.containsPermission(permissions, Permission.READ_PHONE_NUMBERS) &&
            !PermissionUtils.containsPermission(permissions, Permission.READ_PHONE_STATE)
        ) {
            permissions.add(Permission.READ_PHONE_STATE)
        }
    }
}
