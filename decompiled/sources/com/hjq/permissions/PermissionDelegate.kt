package com.hjq.permissions

import android.app.Activity
import android.content.Context
import android.content.Intent

interface PermissionDelegate {
    fun getPermissionIntent(context: Context, permission: String): Intent

    fun isGrantedPermission(context: Context, permission: String): Boolean

    fun isPermissionPermanentDenied(activity: Activity, permission: String): Boolean
}
