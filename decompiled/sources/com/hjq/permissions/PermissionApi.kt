package com.hjq.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.util.ArrayList

object PermissionApi {
    private val DELEGATE: PermissionDelegate =
        when {
            AndroidVersion.isAndroid13() -> PermissionDelegateImplV33()
            AndroidVersion.isAndroid12() -> PermissionDelegateImplV31()
            AndroidVersion.isAndroid11() -> PermissionDelegateImplV30()
            AndroidVersion.isAndroid10() -> PermissionDelegateImplV29()
            AndroidVersion.isAndroid9() -> PermissionDelegateImplV28()
            AndroidVersion.isAndroid8() -> PermissionDelegateImplV26()
            AndroidVersion.isAndroid6() -> PermissionDelegateImplV23()
            else -> PermissionDelegateImplV14()
        }

    @JvmStatic
    fun isGrantedPermission(context: Context, permission: String): Boolean =
        DELEGATE.isGrantedPermission(context, permission)

    @JvmStatic
    fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean =
        DELEGATE.isPermissionPermanentDenied(activity, permission)

    @JvmStatic
    fun getPermissionIntent(context: Context, permission: String): Intent =
        DELEGATE.getPermissionIntent(context, permission)

    @JvmStatic
    fun isSpecialPermission(permission: String): Boolean =
        PermissionUtils.isSpecialPermission(permission)

    @JvmStatic
    fun containsSpecialPermission(permissions: List<String>?): Boolean {
        if (permissions.isNullOrEmpty()) {
            return false
        }
        for (permission in permissions) {
            if (isSpecialPermission(permission)) {
                return true
            }
        }
        return false
    }

    @JvmStatic
    fun isGrantedPermissions(context: Context, permissions: List<String>?): Boolean {
        if (permissions.isNullOrEmpty()) {
            return false
        }
        for (permission in permissions) {
            if (!isGrantedPermission(context, permission)) {
                return false
            }
        }
        return true
    }

    @JvmStatic
    fun getGrantedPermissions(context: Context, permissions: List<String>): List<String> {
        val grantedPermissions = ArrayList<String>(permissions.size)
        for (permission in permissions) {
            if (isGrantedPermission(context, permission)) {
                grantedPermissions.add(permission)
            }
        }
        return grantedPermissions
    }

    @JvmStatic
    fun getDeniedPermissions(context: Context, permissions: List<String>): List<String> {
        val deniedPermissions = ArrayList<String>(permissions.size)
        for (permission in permissions) {
            if (!isGrantedPermission(context, permission)) {
                deniedPermissions.add(permission)
            }
        }
        return deniedPermissions
    }

    @JvmStatic
    fun isPermissionPermanentDenied(activity: Activity, permissions: List<String>): Boolean {
        for (permission in permissions) {
            if (isPermissionPermanentDenied(activity, permission)) {
                return true
            }
        }
        return false
    }

    @JvmStatic
    fun getDeniedPermissions(permissions: List<String>, grantResults: IntArray): List<String> {
        val deniedPermissions = ArrayList<String>()
        for (index in grantResults.indices) {
            if (grantResults[index] == -1) {
                deniedPermissions.add(permissions[index])
            }
        }
        return deniedPermissions
    }

    @JvmStatic
    fun getGrantedPermissions(permissions: List<String>, grantResults: IntArray): List<String> {
        val grantedPermissions = ArrayList<String>()
        for (index in grantResults.indices) {
            if (grantResults[index] == 0) {
                grantedPermissions.add(permissions[index])
            }
        }
        return grantedPermissions
    }
}
