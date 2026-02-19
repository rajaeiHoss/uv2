package com.hjq.permissions;

import android.app.Activity;
import android.content.Context;

class PermissionDelegateImplV28 extends PermissionDelegateImplV26 {
    PermissionDelegateImplV28() {
    }

    public boolean isGrantedPermission(Context context, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.ACCEPT_HANDOVER)) {
            return PermissionUtils.checkSelfPermission(context, str);
        }
        return super.isGrantedPermission(context, str);
    }

    public boolean isPermissionPermanentDenied(Activity activity, String str) {
        if (PermissionUtils.equalsPermission(str, Permission.ACCEPT_HANDOVER)) {
            return !PermissionUtils.checkSelfPermission(activity, str) && !PermissionUtils.shouldShowRequestPermissionRationale(activity, str);
        }
        return super.isPermissionPermanentDenied(activity, str);
    }
}
