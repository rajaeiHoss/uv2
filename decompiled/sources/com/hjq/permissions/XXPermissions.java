package com.hjq.permissions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.drive.DriveFile;
import com.hjq.permissions.IPermissionInterceptor;
import java.util.ArrayList;
import java.util.List;

public final class XXPermissions {
    public static final int REQUEST_CODE = 1025;
    private static Boolean sCheckMode;
    private static IPermissionInterceptor sInterceptor;
    private Boolean mCheckMode;
    private final Context mContext;
    private IPermissionInterceptor mInterceptor;
    private List<String> mPermissions;

    public static XXPermissions with(Context context) {
        return new XXPermissions(context);
    }

    public static XXPermissions with(Fragment fragment) {
        return with((Context) fragment.getActivity());
    }

    public static XXPermissions with(androidx.fragment.app.Fragment fragment) {
        return with((Context) fragment.getActivity());
    }

    public static void setCheckMode(boolean z) {
        sCheckMode = Boolean.valueOf(z);
    }

    public static void setInterceptor(IPermissionInterceptor iPermissionInterceptor) {
        sInterceptor = iPermissionInterceptor;
    }

    public static IPermissionInterceptor getInterceptor() {
        if (sInterceptor == null) {
            sInterceptor = new IPermissionInterceptor() {
                public /* synthetic */ void deniedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                    IPermissionInterceptor.CC.$default$deniedPermissions(this, activity, list, list2, z, onPermissionCallback);
                }

                public /* synthetic */ void grantedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                    IPermissionInterceptor.CC.$default$grantedPermissions(this, activity, list, list2, z, onPermissionCallback);
                }

                public /* synthetic */ void requestPermissions(Activity activity, List list, OnPermissionCallback onPermissionCallback) {
                    IPermissionInterceptor.CC.$default$requestPermissions(this, activity, list, onPermissionCallback);
                }
            };
        }
        return sInterceptor;
    }

    private XXPermissions(Context context) {
        this.mContext = context;
    }

    public XXPermissions permission(String... strArr) {
        return permission((List<String>) PermissionUtils.asArrayList(strArr));
    }

    public XXPermissions permission(String[]... strArr) {
        return permission((List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public XXPermissions permission(List<String> list) {
        if (list != null && !list.isEmpty()) {
            if (this.mPermissions == null) {
                this.mPermissions = new ArrayList(list);
                return this;
            }
            for (String next : list) {
                if (!PermissionUtils.containsPermission(this.mPermissions, next)) {
                    this.mPermissions.add(next);
                }
            }
        }
        return this;
    }

    public XXPermissions interceptor(IPermissionInterceptor iPermissionInterceptor) {
        this.mInterceptor = iPermissionInterceptor;
        return this;
    }

    public XXPermissions unchecked() {
        this.mCheckMode = false;
        return this;
    }

    public void request(OnPermissionCallback onPermissionCallback) {
        if (this.mContext != null) {
            if (this.mInterceptor == null) {
                this.mInterceptor = getInterceptor();
            }
            ArrayList arrayList = new ArrayList(this.mPermissions);
            boolean isCheckMode = isCheckMode();
            Activity findActivity = PermissionUtils.findActivity(this.mContext);
            if (PermissionChecker.checkActivityStatus(findActivity, isCheckMode) && PermissionChecker.checkPermissionArgument(arrayList, isCheckMode)) {
                if (isCheckMode) {
                    PermissionChecker.checkMediaLocationPermission(this.mContext, arrayList);
                    PermissionChecker.checkStoragePermission(this.mContext, arrayList);
                    PermissionChecker.checkBodySensorsPermission(arrayList);
                    PermissionChecker.checkLocationPermission(this.mContext, arrayList);
                    PermissionChecker.checkTargetSdkVersion(this.mContext, arrayList);
                    PermissionChecker.checkManifestPermissions(this.mContext, arrayList);
                }
                PermissionChecker.optimizeDeprecatedPermission(arrayList);
                if (!PermissionApi.isGrantedPermissions(this.mContext, arrayList)) {
                    this.mInterceptor.requestPermissions(findActivity, arrayList, onPermissionCallback);
                } else if (onPermissionCallback != null) {
                    this.mInterceptor.grantedPermissions(findActivity, arrayList, arrayList, true, onPermissionCallback);
                }
            }
        }
    }

    public boolean revokeOnKill() {
        if (this.mContext == null || !AndroidVersion.isAndroid13()) {
            return false;
        }
        try {
            if (this.mPermissions.size() == 1) {
                this.mContext.revokeSelfPermissionOnKill(this.mPermissions.get(0));
            } else {
                this.mContext.revokeSelfPermissionsOnKill(this.mPermissions);
            }
            return true;
        } catch (IllegalArgumentException e) {
            if (!isCheckMode()) {
                e.printStackTrace();
                return false;
            }
            throw e;
        }
    }

    private boolean isCheckMode() {
        if (this.mCheckMode == null) {
            if (sCheckMode == null) {
                sCheckMode = Boolean.valueOf(PermissionUtils.isDebugMode(this.mContext));
            }
            this.mCheckMode = sCheckMode;
        }
        return this.mCheckMode.booleanValue();
    }

    public static boolean isGranted(Context context, String... strArr) {
        return isGranted(context, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static boolean isGranted(Context context, String[]... strArr) {
        return isGranted(context, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static boolean isGranted(Context context, List<String> list) {
        return PermissionApi.isGrantedPermissions(context, list);
    }

    public static List<String> getDenied(Context context, String... strArr) {
        return getDenied(context, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static List<String> getDenied(Context context, String[]... strArr) {
        return getDenied(context, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static List<String> getDenied(Context context, List<String> list) {
        return PermissionApi.getDeniedPermissions(context, list);
    }

    public static boolean isSpecial(String str) {
        return PermissionApi.isSpecialPermission(str);
    }

    public static boolean containsSpecial(String... strArr) {
        return containsSpecial((List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static boolean containsSpecial(List<String> list) {
        return PermissionApi.containsSpecialPermission(list);
    }

    public static boolean isPermanentDenied(Activity activity, String... strArr) {
        return isPermanentDenied(activity, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static boolean isPermanentDenied(Activity activity, String[]... strArr) {
        return isPermanentDenied(activity, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static boolean isPermanentDenied(Activity activity, List<String> list) {
        return PermissionApi.isPermissionPermanentDenied(activity, list);
    }

    public static void startPermissionActivity(Context context) {
        startPermissionActivity(context, (List<String>) null);
    }

    public static void startPermissionActivity(Context context, String... strArr) {
        startPermissionActivity(context, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Context context, String[]... strArr) {
        startPermissionActivity(context, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(Context context, List<String> list) {
        Activity findActivity = PermissionUtils.findActivity(context);
        if (findActivity != null) {
            startPermissionActivity(findActivity, list);
            return;
        }
        Intent smartPermissionIntent = PermissionUtils.getSmartPermissionIntent(context, list);
        if (!(context instanceof Activity)) {
            smartPermissionIntent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        context.startActivity(smartPermissionIntent);
    }

    public static void startPermissionActivity(Activity activity) {
        startPermissionActivity(activity, (List<String>) null);
    }

    public static void startPermissionActivity(Activity activity, String... strArr) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Activity activity, String[]... strArr) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(Activity activity, List<String> list) {
        startPermissionActivity(activity, list, 1025);
    }

    public static void startPermissionActivity(Activity activity, List<String> list, int i) {
        activity.startActivityForResult(PermissionUtils.getSmartPermissionIntent(activity, list), i);
    }

    public static void startPermissionActivity(Activity activity, String str, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayList(str), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Activity activity, String[] strArr, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(activity, (List<String>) PermissionUtils.asArrayLists(strArr), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Activity activity, List<String> list, OnPermissionPageCallback onPermissionPageCallback) {
        PermissionPageFragment.beginRequest(activity, (ArrayList) list, onPermissionPageCallback);
    }

    public static void startPermissionActivity(Fragment fragment) {
        startPermissionActivity(fragment, (List<String>) null);
    }

    public static void startPermissionActivity(Fragment fragment, String... strArr) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(Fragment fragment, String[]... strArr) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(Fragment fragment, List<String> list) {
        startPermissionActivity(fragment, list, 1025);
    }

    public static void startPermissionActivity(Fragment fragment, List<String> list, int i) {
        Activity activity = fragment.getActivity();
        if (activity != null) {
            fragment.startActivityForResult(PermissionUtils.getSmartPermissionIntent(activity, list), i);
        }
    }

    public static void startPermissionActivity(Fragment fragment, String str, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayList(str), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Fragment fragment, String[] strArr, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayLists(strArr), onPermissionPageCallback);
    }

    public static void startPermissionActivity(Fragment fragment, List<String> list, OnPermissionPageCallback onPermissionPageCallback) {
        Activity activity = fragment.getActivity();
        if (activity != null && !activity.isFinishing()) {
            if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
                PermissionPageFragment.beginRequest(activity, (ArrayList) list, onPermissionPageCallback);
            }
        }
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment) {
        startPermissionActivity(fragment, (List<String>) null);
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String... strArr) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayList(strArr));
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String[]... strArr) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayLists(strArr));
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, List<String> list) {
        startPermissionActivity(fragment, list, 1025);
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, List<String> list, int i) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            fragment.startActivityForResult(PermissionUtils.getSmartPermissionIntent(activity, list), i);
        }
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String str, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayList(str), onPermissionPageCallback);
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, String[] strArr, OnPermissionPageCallback onPermissionPageCallback) {
        startPermissionActivity(fragment, (List<String>) PermissionUtils.asArrayLists(strArr), onPermissionPageCallback);
    }

    public static void startPermissionActivity(androidx.fragment.app.Fragment fragment, List<String> list, OnPermissionPageCallback onPermissionPageCallback) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null && !activity.isFinishing()) {
            if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
                PermissionPageFragment.beginRequest(activity, (ArrayList) list, onPermissionPageCallback);
            }
        }
    }
}
