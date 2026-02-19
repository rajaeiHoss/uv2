package com.hjq.permissions

import android.app.Activity
import java.util.ArrayList

interface IPermissionInterceptor {
    fun deniedPermissions(
        activity: Activity,
        allPermissions: List<String>,
        deniedPermissions: List<String>,
        doNotAskAgain: Boolean,
        callback: OnPermissionCallback?
    ) {
        callback?.onDenied(deniedPermissions, doNotAskAgain)
    }

    fun grantedPermissions(
        activity: Activity,
        allPermissions: List<String>,
        grantedPermissions: List<String>,
        allGranted: Boolean,
        callback: OnPermissionCallback?
    ) {
        callback?.onGranted(grantedPermissions, allGranted)
    }

    fun requestPermissions(
        activity: Activity,
        permissions: List<String>,
        callback: OnPermissionCallback?
    ) {
        PermissionFragment.beginRequest(activity, ArrayList(permissions), this, callback)
    }

    object CC {
        @JvmStatic
        fun `$default$requestPermissions`(
            interceptor: IPermissionInterceptor,
            activity: Activity,
            permissions: List<String>,
            callback: OnPermissionCallback?
        ) {
            PermissionFragment.beginRequest(activity, ArrayList(permissions), interceptor, callback)
        }

        @JvmStatic
        fun `$default$grantedPermissions`(
            interceptor: IPermissionInterceptor,
            activity: Activity,
            allPermissions: List<String>,
            grantedPermissions: List<String>,
            allGranted: Boolean,
            callback: OnPermissionCallback?
        ) {
            callback?.onGranted(grantedPermissions, allGranted)
        }

        @JvmStatic
        fun `$default$deniedPermissions`(
            interceptor: IPermissionInterceptor,
            activity: Activity,
            allPermissions: List<String>,
            deniedPermissions: List<String>,
            doNotAskAgain: Boolean,
            callback: OnPermissionCallback?
        ) {
            callback?.onDenied(deniedPermissions, doNotAskAgain)
        }
    }
}
