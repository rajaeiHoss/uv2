package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

final class PermissionUtils {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    static String getAndroidNamespace() {
        return "http://schemas.android.com/apk/res/android";
    }

    PermissionUtils() {
    }

    public static boolean isSpecialPermission(String str) {
        return equalsPermission(str, Permission.MANAGE_EXTERNAL_STORAGE) || equalsPermission(str, Permission.REQUEST_INSTALL_PACKAGES) || equalsPermission(str, Permission.SYSTEM_ALERT_WINDOW) || equalsPermission(str, Permission.WRITE_SETTINGS) || equalsPermission(str, Permission.NOTIFICATION_SERVICE) || equalsPermission(str, Permission.PACKAGE_USAGE_STATS) || equalsPermission(str, Permission.SCHEDULE_EXACT_ALARM) || equalsPermission(str, Permission.BIND_NOTIFICATION_LISTENER_SERVICE) || equalsPermission(str, Permission.ACCESS_NOTIFICATION_POLICY) || equalsPermission(str, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) || equalsPermission(str, Permission.BIND_VPN_SERVICE);
    }

    public static boolean checkSelfPermission(Context context, String str) {
        return context.checkSelfPermission(str) == 0;
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
        if (AndroidVersion.getAndroidVersionCode() == 31) {
            try {
                PackageManager packageManager = activity.getApplication().getPackageManager();
                return ((Boolean) PackageManager.class.getMethod("shouldShowRequestPermissionRationale", new Class[]{String.class}).invoke(packageManager, new Object[]{str})).booleanValue();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return activity.shouldShowRequestPermissionRationale(str);
    }

    static void postDelayed(Runnable runnable, long j) {
        HANDLER.postDelayed(runnable, j);
    }

    static void postActivityResult(List<String> list, Runnable runnable) {
        long j = 300;
        long j2 = AndroidVersion.isAndroid11() ? 200 : 300;
        String lowerCase = Build.MANUFACTURER.toLowerCase();
        if (!lowerCase.contains("huawei")) {
            j = (!lowerCase.contains("xiaomi") || !AndroidVersion.isAndroid11() || !containsPermission(list, Permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)) ? j2 : 1000;
        } else if (!AndroidVersion.isAndroid8()) {
            j = 500;
        }
        HANDLER.postDelayed(runnable, j);
    }

    static boolean isDebugMode(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    static HashMap<String, Integer> getManifestPermissions(Context context) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        XmlResourceParser parseAndroidManifest = parseAndroidManifest(context);
        if (parseAndroidManifest != null) {
            try {
                do {
                    if (parseAndroidManifest.getEventType() == 2) {
                        if ("uses-permission".equals(parseAndroidManifest.getName())) {
                            hashMap.put(parseAndroidManifest.getAttributeValue(getAndroidNamespace(), "name"), Integer.valueOf(parseAndroidManifest.getAttributeIntValue(getAndroidNamespace(), "maxSdkVersion", Integer.MAX_VALUE)));
                        }
                    }
                } while (parseAndroidManifest.next() != 1);
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            } finally {
                parseAndroidManifest.close();
            }
        }
        if (hashMap.isEmpty()) {
            try {
                String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
                if (strArr != null) {
                    for (String put : strArr) {
                        hashMap.put(put, Integer.MAX_VALUE);
                    }
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return hashMap;
    }

    static void optimizePermissionResults(Activity activity, String[] strArr, int[] iArr) {
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            boolean isSpecialPermission = PermissionApi.isSpecialPermission(str);
            boolean z = true;
            if (!AndroidVersion.isAndroid13() && (equalsPermission(str, Permission.POST_NOTIFICATIONS) || equalsPermission(str, Permission.NEARBY_WIFI_DEVICES) || equalsPermission(str, Permission.BODY_SENSORS_BACKGROUND) || equalsPermission(str, Permission.READ_MEDIA_IMAGES) || equalsPermission(str, Permission.READ_MEDIA_VIDEO) || equalsPermission(str, Permission.READ_MEDIA_AUDIO))) {
                isSpecialPermission = true;
            }
            if (!AndroidVersion.isAndroid12() && (equalsPermission(str, Permission.BLUETOOTH_SCAN) || equalsPermission(str, Permission.BLUETOOTH_CONNECT) || equalsPermission(str, Permission.BLUETOOTH_ADVERTISE))) {
                isSpecialPermission = true;
            }
            if (!AndroidVersion.isAndroid10() && (equalsPermission(str, Permission.ACCESS_BACKGROUND_LOCATION) || equalsPermission(str, Permission.ACTIVITY_RECOGNITION) || equalsPermission(str, Permission.ACCESS_MEDIA_LOCATION))) {
                isSpecialPermission = true;
            }
            if (!AndroidVersion.isAndroid9() && equalsPermission(str, Permission.ACCEPT_HANDOVER)) {
                isSpecialPermission = true;
            }
            if (AndroidVersion.isAndroid8() || (!equalsPermission(str, Permission.ANSWER_PHONE_CALLS) && !equalsPermission(str, Permission.READ_PHONE_NUMBERS))) {
                z = isSpecialPermission;
            }
            if (z) {
                iArr[i] = PermissionApi.isGrantedPermission(activity, str) ? 0 : -1;
            }
        }
    }

    static <T> ArrayList<T> asArrayList(T... tArr) {
        ArrayList<T> arrayList = new ArrayList<>(tArr.length);
        if (!(tArr == null || tArr.length == 0)) {
            for (T add : tArr) {
                arrayList.add(add);
            }
        }
        return arrayList;
    }

    @SafeVarargs
    static <T> ArrayList<T> asArrayLists(T[]... tArr) {
        ArrayList<T> arrayList = new ArrayList<>();
        if (!(tArr == null || tArr.length == 0)) {
            for (T[] asArrayList : tArr) {
                arrayList.addAll(asArrayList(asArrayList));
            }
        }
        return arrayList;
    }

    static Activity findActivity(Context context) {
        while (true) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
            if (context == null) {
                return null;
            }
        }
    }

    static int findApkPathCookie(Context context, String str) {
        AssetManager assets = context.getAssets();
        try {
            int targetSdkVersionCode = AndroidVersion.getTargetSdkVersionCode(context);
            int androidVersionCode = AndroidVersion.getAndroidVersionCode();
            if (targetSdkVersionCode >= 28 && androidVersionCode >= 28 && androidVersionCode < 30) {
                Object invoke = AssetManager.class.getDeclaredMethod("findCookieForPath", new Class[]{String.class}).invoke(assets, new Object[]{str});
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            Object invoke2 = assets.getClass().getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assets, new Object[]{str});
            if (invoke2 instanceof Integer) {
                return ((Integer) invoke2).intValue();
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
        }
        return 0;
    }

    static XmlResourceParser parseAndroidManifest(Context context) {
        int findApkPathCookie = findApkPathCookie(context, context.getApplicationInfo().sourceDir);
        if (findApkPathCookie == 0) {
            return null;
        }
        try {
            XmlResourceParser openXmlResourceParser = context.getAssets().openXmlResourceParser(findApkPathCookie, "AndroidManifest.xml");
            do {
                if (openXmlResourceParser.getEventType() == 2) {
                    if ("manifest".equals(openXmlResourceParser.getName()) && TextUtils.equals(context.getPackageName(), openXmlResourceParser.getAttributeValue((String) null, "package"))) {
                        return openXmlResourceParser;
                    }
                }
            } while (openXmlResourceParser.next() != 1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
        return null;
    }

    static boolean isScopedStorage(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null || !bundle.containsKey("ScopedStorage")) {
                return false;
            }
            return Boolean.parseBoolean(String.valueOf(bundle.get("ScopedStorage")));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    static void lockActivityOrientation(Activity activity) {
        try {
            int i = activity.getResources().getConfiguration().orientation;
            int i2 = 1;
            if (i == 1) {
                if (isActivityReverse(activity)) {
                    i2 = 9;
                }
                activity.setRequestedOrientation(i2);
            } else if (i == 2) {
                activity.setRequestedOrientation(isActivityReverse(activity) ? 8 : 0);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    static boolean isActivityReverse(Activity activity) {
        int i;
        if (AndroidVersion.isAndroid11()) {
            i = activity.getDisplay().getRotation();
        } else {
            i = activity.getWindowManager().getDefaultDisplay().getRotation();
        }
        return i == 2 || i == 3;
    }

    static boolean areActivityIntent(Context context, Intent intent) {
        return !context.getPackageManager().queryIntentActivities(intent, 65536).isEmpty();
    }

    public static Intent getApplicationDetailsIntent(Context context) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(getPackageNameUri(context));
        if (areActivityIntent(context, intent)) {
            return intent;
        }
        Intent intent2 = new Intent("android.settings.APPLICATION_SETTINGS");
        return !areActivityIntent(context, intent2) ? new Intent("android.settings.MANAGE_APPLICATIONS_SETTINGS") : intent2;
    }

    public static Uri getPackageNameUri(Context context) {
        return Uri.parse("package:" + context.getPackageName());
    }

    static Intent getSmartPermissionIntent(Context context, List<String> list) {
        if (list == null || list.isEmpty() || !PermissionApi.containsSpecialPermission(list)) {
            return getApplicationDetailsIntent(context);
        }
        int size = list.size();
        if (size == 1) {
            return PermissionApi.getPermissionIntent(context, list.get(0));
        }
        if (size != 2) {
            if (size == 3 && AndroidVersion.isAndroid11() && containsPermission(list, Permission.MANAGE_EXTERNAL_STORAGE) && containsPermission(list, Permission.READ_EXTERNAL_STORAGE) && containsPermission(list, Permission.WRITE_EXTERNAL_STORAGE)) {
                return PermissionApi.getPermissionIntent(context, Permission.MANAGE_EXTERNAL_STORAGE);
            }
        } else if (!AndroidVersion.isAndroid13() && containsPermission(list, Permission.NOTIFICATION_SERVICE) && containsPermission(list, Permission.POST_NOTIFICATIONS)) {
            return PermissionApi.getPermissionIntent(context, Permission.NOTIFICATION_SERVICE);
        }
        return getApplicationDetailsIntent(context);
    }

    static boolean equalsPermission(String str, String str2) {
        int length;
        if (str == null || str2 == null || (length = str.length()) != str2.length()) {
            return false;
        }
        for (int i = length - 1; i >= 0; i--) {
            if (str.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean containsPermission(Collection<String> collection, String str) {
        if (!(collection == null || collection.isEmpty() || str == null)) {
            for (String equalsPermission : collection) {
                if (equalsPermission(equalsPermission, str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
