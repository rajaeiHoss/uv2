package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Build;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

final class PermissionChecker {
    PermissionChecker() {
    }

    static boolean checkActivityStatus(Activity activity, boolean z) {
        if (activity == null) {
            if (!z) {
                return false;
            }
            throw new IllegalArgumentException("The instance of the context must be an activity object");
        } else if (activity.isFinishing()) {
            if (!z) {
                return false;
            }
            throw new IllegalStateException("The activity has been finishing, please manually determine the status of the activity");
        } else if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
            return true;
        } else {
            if (!z) {
                return false;
            }
            throw new IllegalStateException("The activity has been destroyed, please manually determine the status of the activity");
        }
    }

    static boolean checkPermissionArgument(List<String> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            if (Build.VERSION.SDK_INT <= 32 && z) {
                ArrayList arrayList = new ArrayList();
                Field[] declaredFields = Permission.class.getDeclaredFields();
                if (declaredFields.length == 0) {
                    return true;
                }
                for (Field field : declaredFields) {
                    if (String.class.equals(field.getType())) {
                        try {
                            arrayList.add((String) field.get((Object) null));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                for (String next : list) {
                    if (!PermissionUtils.containsPermission(arrayList, next)) {
                        throw new IllegalArgumentException("The " + next + " is not a dangerous permission or special permission, please do not apply dynamically");
                    }
                }
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            throw new IllegalArgumentException("The requested permission cannot be empty");
        }
    }

    static void checkMediaLocationPermission(Context context, List<String> list) {
        if (PermissionUtils.containsPermission(list, Permission.ACCESS_MEDIA_LOCATION)) {
            for (String next : list) {
                if (!PermissionUtils.equalsPermission(next, Permission.ACCESS_MEDIA_LOCATION) && !PermissionUtils.equalsPermission(next, Permission.READ_MEDIA_IMAGES) && !PermissionUtils.equalsPermission(next, Permission.READ_EXTERNAL_STORAGE) && !PermissionUtils.equalsPermission(next, Permission.WRITE_EXTERNAL_STORAGE) && !PermissionUtils.equalsPermission(next, Permission.MANAGE_EXTERNAL_STORAGE)) {
                    throw new IllegalArgumentException("Because it includes access media location permissions, do not apply for permissions unrelated to access media location");
                }
            }
            if (AndroidVersion.getTargetSdkVersionCode(context) >= 33) {
                if (!PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) && !PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
                    throw new IllegalArgumentException("You must add android.permission.READ_MEDIA_IMAGES or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights");
                }
            } else if (!PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) && !PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
                throw new IllegalArgumentException("You must add android.permission.READ_EXTERNAL_STORAGE or android.permission.MANAGE_EXTERNAL_STORAGE rights to apply for android.permission.ACCESS_MEDIA_LOCATION rights");
            }
        }
    }

    static void checkStoragePermission(Context context, List<String> list) {
        if (!PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) && !PermissionUtils.containsPermission(list, Permission.READ_MEDIA_VIDEO) && !PermissionUtils.containsPermission(list, Permission.READ_MEDIA_AUDIO) && !PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) && !PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) && !PermissionUtils.containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE)) {
            return;
        }
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 33 && (PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) || PermissionUtils.containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE))) {
            throw new IllegalArgumentException("When targetSdkVersion >= 33 should use android.permission.READ_MEDIA_IMAGES, android.permission.READ_MEDIA_VIDEO, android.permission.READ_MEDIA_AUDIO instead of android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE");
        } else if (!PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) && !PermissionUtils.containsPermission(list, Permission.ACCESS_MEDIA_LOCATION)) {
            boolean isScopedStorage = PermissionUtils.isScopedStorage(context);
            XmlResourceParser parseAndroidManifest = PermissionUtils.parseAndroidManifest(context);
            if (parseAndroidManifest != null) {
                while (true) {
                    try {
                        if (parseAndroidManifest.getEventType() == 2) {
                            if ("application".equals(parseAndroidManifest.getName())) {
                                int targetSdkVersionCode = AndroidVersion.getTargetSdkVersionCode(context);
                                boolean attributeBooleanValue = parseAndroidManifest.getAttributeBooleanValue(PermissionUtils.getAndroidNamespace(), "requestLegacyExternalStorage", false);
                                if (targetSdkVersionCode >= 29 && !attributeBooleanValue) {
                                    if (PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) || !isScopedStorage) {
                                        throw new IllegalStateException("Please register the android:requestLegacyExternalStorage=\"true\" attribute in the AndroidManifest.xml file, otherwise it will cause incompatibility with the old version");
                                    }
                                }
                                if (targetSdkVersionCode >= 30 && !PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
                                    if (!isScopedStorage) {
                                        throw new IllegalArgumentException("The storage permission application is abnormal. If you have adapted the scope storage, please register the <meta-data android:name=\"ScopedStorage\" android:value=\"true\" /> attribute in the AndroidManifest.xml file. If there is no adaptation scope storage, please use android.permission.MANAGE_EXTERNAL_STORAGE to apply for permission");
                                    }
                                }
                            }
                        }
                        if (parseAndroidManifest.next() == 1) {
                            break;
                        }
                    } catch (IOException | XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (Throwable th) {
                        parseAndroidManifest.close();
                        throw th;
                    }
                }
                parseAndroidManifest.close();
            }
        }
    }

    static void checkBodySensorsPermission(List<String> list) {
        if (PermissionUtils.containsPermission(list, Permission.BODY_SENSORS_BACKGROUND)) {
            if (!PermissionUtils.containsPermission(list, Permission.BODY_SENSORS_BACKGROUND) || PermissionUtils.containsPermission(list, Permission.BODY_SENSORS)) {
                for (String next : list) {
                    if (PermissionUtils.equalsPermission(next, Permission.ACCESS_BACKGROUND_LOCATION)) {
                        throw new IllegalArgumentException("Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_BACKGROUND_LOCATION at the same time is not supported");
                    } else if (PermissionUtils.equalsPermission(next, Permission.ACCESS_MEDIA_LOCATION)) {
                        throw new IllegalArgumentException("Applying for permissions android.permission.BODY_SENSORS_BACKGROUND and android.permission.ACCESS_MEDIA_LOCATION at the same time is not supported");
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Applying for background sensor permissions must contain android.permission.BODY_SENSORS");
        }
    }

    static void checkLocationPermission(Context context, List<String> list) {
        if (AndroidVersion.getTargetSdkVersionCode(context) >= 31 && PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION) && !PermissionUtils.containsPermission(list, Permission.ACCESS_COARSE_LOCATION)) {
            throw new IllegalArgumentException("If your app targets Android 12 or higher and requests the ACCESS_FINE_LOCATION runtime permission, you must also request the ACCESS_COARSE_LOCATION permission. You must include both permissions in a single runtime request.");
        } else if (PermissionUtils.containsPermission(list, Permission.ACCESS_BACKGROUND_LOCATION)) {
            if (!PermissionUtils.containsPermission(list, Permission.ACCESS_COARSE_LOCATION) || PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION)) {
                for (String next : list) {
                    if (!PermissionUtils.equalsPermission(next, Permission.ACCESS_FINE_LOCATION) && !PermissionUtils.equalsPermission(next, Permission.ACCESS_COARSE_LOCATION) && !PermissionUtils.equalsPermission(next, Permission.ACCESS_BACKGROUND_LOCATION)) {
                        throw new IllegalArgumentException("Because it includes background location permissions, do not apply for permissions unrelated to location");
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Applying for background positioning permissions must include android.permission.ACCESS_FINE_LOCATION");
        }
    }

    static void checkTargetSdkVersion(Context context, List<String> list) {
        int i = (PermissionUtils.containsPermission(list, Permission.POST_NOTIFICATIONS) || PermissionUtils.containsPermission(list, Permission.NEARBY_WIFI_DEVICES) || PermissionUtils.containsPermission(list, Permission.BODY_SENSORS_BACKGROUND) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_VIDEO) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_AUDIO)) ? 33 : (PermissionUtils.containsPermission(list, Permission.BLUETOOTH_SCAN) || PermissionUtils.containsPermission(list, Permission.BLUETOOTH_CONNECT) || PermissionUtils.containsPermission(list, Permission.BLUETOOTH_ADVERTISE) || PermissionUtils.containsPermission(list, Permission.SCHEDULE_EXACT_ALARM)) ? 31 : PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) ? 30 : (PermissionUtils.containsPermission(list, Permission.ACCESS_BACKGROUND_LOCATION) || PermissionUtils.containsPermission(list, Permission.ACTIVITY_RECOGNITION) || PermissionUtils.containsPermission(list, Permission.ACCESS_MEDIA_LOCATION)) ? 29 : PermissionUtils.containsPermission(list, Permission.ACCEPT_HANDOVER) ? 28 : (PermissionUtils.containsPermission(list, Permission.REQUEST_INSTALL_PACKAGES) || PermissionUtils.containsPermission(list, Permission.ANSWER_PHONE_CALLS) || PermissionUtils.containsPermission(list, Permission.READ_PHONE_NUMBERS)) ? 26 : 23;
        if (AndroidVersion.getTargetSdkVersionCode(context) < i) {
            throw new RuntimeException("The targetSdkVersion SDK must be " + i + " or more, if you do not want to upgrade targetSdkVersion, please apply with the old permissions");
        }
    }

    static void checkManifestPermissions(Context context, List<String> list) {
        HashMap<String, Integer> manifestPermissions = PermissionUtils.getManifestPermissions(context);
        if (!manifestPermissions.isEmpty()) {
            int i = Build.VERSION.SDK_INT >= 24 ? context.getApplicationInfo().minSdkVersion : 23;
            for (String next : list) {
                if (!PermissionUtils.equalsPermission(next, Permission.NOTIFICATION_SERVICE) && !PermissionUtils.equalsPermission(next, Permission.BIND_NOTIFICATION_LISTENER_SERVICE) && !PermissionUtils.equalsPermission(next, Permission.BIND_VPN_SERVICE)) {
                    if (PermissionUtils.equalsPermission(next, Permission.BODY_SENSORS_BACKGROUND)) {
                        checkManifestPermission(manifestPermissions, Permission.BODY_SENSORS, Integer.MAX_VALUE);
                    }
                    if (PermissionUtils.equalsPermission(next, Permission.ACCESS_BACKGROUND_LOCATION)) {
                        if (AndroidVersion.getTargetSdkVersionCode(context) >= 31) {
                            checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, 30);
                            checkManifestPermission(manifestPermissions, Permission.ACCESS_COARSE_LOCATION, Integer.MAX_VALUE);
                        } else {
                            checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, Integer.MAX_VALUE);
                        }
                    }
                    if (i < 33) {
                        if (PermissionUtils.equalsPermission(next, Permission.READ_MEDIA_IMAGES) || PermissionUtils.equalsPermission(next, Permission.READ_MEDIA_VIDEO) || PermissionUtils.equalsPermission(next, Permission.READ_MEDIA_AUDIO)) {
                            checkManifestPermission(manifestPermissions, Permission.READ_EXTERNAL_STORAGE, 32);
                        }
                        if (PermissionUtils.equalsPermission(next, Permission.NEARBY_WIFI_DEVICES)) {
                            checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, 32);
                        }
                    }
                    if (i < 31) {
                        if (PermissionUtils.equalsPermission(next, Permission.BLUETOOTH_SCAN)) {
                            checkManifestPermission(manifestPermissions, "android.permission.BLUETOOTH_ADMIN", 30);
                            checkManifestPermission(manifestPermissions, Permission.ACCESS_FINE_LOCATION, 30);
                        }
                        if (PermissionUtils.equalsPermission(next, Permission.BLUETOOTH_CONNECT)) {
                            checkManifestPermission(manifestPermissions, "android.permission.BLUETOOTH", 30);
                        }
                        if (PermissionUtils.equalsPermission(next, Permission.BLUETOOTH_ADVERTISE)) {
                            checkManifestPermission(manifestPermissions, "android.permission.BLUETOOTH_ADMIN", 30);
                        }
                    }
                    if (i < 30 && PermissionUtils.equalsPermission(next, Permission.MANAGE_EXTERNAL_STORAGE)) {
                        checkManifestPermission(manifestPermissions, Permission.READ_EXTERNAL_STORAGE, 29);
                        checkManifestPermission(manifestPermissions, Permission.WRITE_EXTERNAL_STORAGE, 29);
                    }
                    if (i < 29 && PermissionUtils.equalsPermission(next, Permission.ACTIVITY_RECOGNITION)) {
                        checkManifestPermission(manifestPermissions, Permission.BODY_SENSORS, 28);
                    }
                    if (i < 26 && PermissionUtils.equalsPermission(next, Permission.READ_PHONE_NUMBERS)) {
                        checkManifestPermission(manifestPermissions, Permission.READ_PHONE_STATE, 25);
                    }
                    checkManifestPermission(manifestPermissions, next, Integer.MAX_VALUE);
                }
            }
            return;
        }
        throw new IllegalStateException("No permissions are registered in the AndroidManifest.xml file");
    }

    static void checkManifestPermission(HashMap<String, Integer> hashMap, String str, int i) {
        String str2;
        if (hashMap.containsKey(str)) {
            Integer num = hashMap.get(str);
            if (num != null && num.intValue() < i) {
                StringBuilder sb = new StringBuilder();
                sb.append("The AndroidManifest.xml file <uses-permission android:name=\"");
                sb.append(str);
                sb.append("\" android:maxSdkVersion=\"");
                sb.append(num);
                sb.append("\" /> does not meet the requirements, ");
                if (i != Integer.MAX_VALUE) {
                    str2 = "the minimum requirement for maxSdkVersion is " + i;
                } else {
                    str2 = "please delete the android:maxSdkVersion=\"" + num + "\" attribute";
                }
                sb.append(str2);
                throw new IllegalArgumentException(sb.toString());
            }
            return;
        }
        throw new IllegalStateException("Please register permissions in the AndroidManifest.xml file <uses-permission android:name=\"" + str + "\" />");
    }

    static void optimizeDeprecatedPermission(List<String> list) {
        if (!AndroidVersion.isAndroid13()) {
            if (PermissionUtils.containsPermission(list, Permission.POST_NOTIFICATIONS) && !PermissionUtils.containsPermission(list, Permission.NOTIFICATION_SERVICE)) {
                list.add(Permission.NOTIFICATION_SERVICE);
            }
            if (PermissionUtils.containsPermission(list, Permission.NEARBY_WIFI_DEVICES) && !PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION)) {
                list.add(Permission.ACCESS_FINE_LOCATION);
            }
            if ((PermissionUtils.containsPermission(list, Permission.READ_MEDIA_IMAGES) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_VIDEO) || PermissionUtils.containsPermission(list, Permission.READ_MEDIA_AUDIO)) && !PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE)) {
                list.add(Permission.READ_EXTERNAL_STORAGE);
            }
        }
        if (!AndroidVersion.isAndroid12() && PermissionUtils.containsPermission(list, Permission.BLUETOOTH_SCAN) && !PermissionUtils.containsPermission(list, Permission.ACCESS_FINE_LOCATION)) {
            list.add(Permission.ACCESS_FINE_LOCATION);
        }
        if (PermissionUtils.containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE)) {
            if (PermissionUtils.containsPermission(list, Permission.READ_EXTERNAL_STORAGE) || PermissionUtils.containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE)) {
                throw new IllegalArgumentException("If you have applied for MANAGE_EXTERNAL_STORAGE permissions, do not apply for the READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE permissions");
            } else if (!AndroidVersion.isAndroid11()) {
                list.add(Permission.READ_EXTERNAL_STORAGE);
                list.add(Permission.WRITE_EXTERNAL_STORAGE);
            }
        }
        if (!AndroidVersion.isAndroid10() && PermissionUtils.containsPermission(list, Permission.ACTIVITY_RECOGNITION) && !PermissionUtils.containsPermission(list, Permission.BODY_SENSORS)) {
            list.add(Permission.BODY_SENSORS);
        }
        if (!AndroidVersion.isAndroid8() && PermissionUtils.containsPermission(list, Permission.READ_PHONE_NUMBERS) && !PermissionUtils.containsPermission(list, Permission.READ_PHONE_STATE)) {
            list.add(Permission.READ_PHONE_STATE);
        }
    }
}
