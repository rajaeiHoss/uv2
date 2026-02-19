package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;

final class PermissionApi {
    private static final PermissionDelegate DELEGATE;

    PermissionApi() {
    }

    static {
        if (AndroidVersion.isAndroid13()) {
            DELEGATE = new PermissionDelegateImplV33();
        } else if (AndroidVersion.isAndroid12()) {
            DELEGATE = new PermissionDelegateImplV31();
        } else if (AndroidVersion.isAndroid11()) {
            DELEGATE = new PermissionDelegateImplV30();
        } else if (AndroidVersion.isAndroid10()) {
            DELEGATE = new PermissionDelegateImplV29();
        } else if (AndroidVersion.isAndroid9()) {
            DELEGATE = new PermissionDelegateImplV28();
        } else if (AndroidVersion.isAndroid8()) {
            DELEGATE = new PermissionDelegateImplV26();
        } else if (AndroidVersion.isAndroid6()) {
            DELEGATE = new PermissionDelegateImplV23();
        } else {
            DELEGATE = new PermissionDelegateImplV14();
        }
    }

    static boolean isGrantedPermission(Context context, String str) {
        return DELEGATE.isGrantedPermission(context, str);
    }

    static boolean isPermissionPermanentDenied(Activity activity, String str) {
        return DELEGATE.isPermissionPermanentDenied(activity, str);
    }

    static Intent getPermissionIntent(Context context, String str) {
        return DELEGATE.getPermissionIntent(context, str);
    }

    static boolean isSpecialPermission(String str) {
        return PermissionUtils.isSpecialPermission(str);
    }

    static boolean containsSpecialPermission(List<String> list) {
        if (list != null && !list.isEmpty()) {
            for (String isSpecialPermission : list) {
                if (isSpecialPermission(isSpecialPermission)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean isGrantedPermissions(Context context, List<String> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (String isGrantedPermission : list) {
            if (!isGrantedPermission(context, isGrantedPermission)) {
                return false;
            }
        }
        return true;
    }

    static List<String> getGrantedPermissions(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String next : list) {
            if (isGrantedPermission(context, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    static List<String> getDeniedPermissions(Context context, List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String next : list) {
            if (!isGrantedPermission(context, next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    static boolean isPermissionPermanentDenied(Activity activity, List<String> list) {
        for (String isPermissionPermanentDenied : list) {
            if (isPermissionPermanentDenied(activity, isPermissionPermanentDenied)) {
                return true;
            }
        }
        return false;
    }

    static List<String> getDeniedPermissions(List<String> list, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] == -1) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }

    static List<String> getGrantedPermissions(List<String> list, int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] == 0) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }
}
