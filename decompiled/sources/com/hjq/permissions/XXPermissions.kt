package com.hjq.permissions

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.fragment.app.FragmentActivity
import java.util.ArrayList

class XXPermissions private constructor(private val mContext: Context) {
    private var mCheckMode: Boolean? = null
    private var mInterceptor: IPermissionInterceptor? = null
    private var mPermissions: MutableList<String>? = null

    companion object {
        const val REQUEST_CODE: Int = 1025

        private var sCheckMode: Boolean? = null
        private var sInterceptor: IPermissionInterceptor? = null

        @JvmStatic
        fun with(context: Context): XXPermissions = XXPermissions(context)

        @JvmStatic
        fun with(fragment: Fragment): XXPermissions = with(fragment.activity)

        @JvmStatic
        fun with(fragment: androidx.fragment.app.Fragment): XXPermissions = with(fragment.activity)

        @JvmStatic
        fun setCheckMode(checkMode: Boolean) {
            sCheckMode = checkMode
        }

        @JvmStatic
        fun setInterceptor(interceptor: IPermissionInterceptor?) {
            sInterceptor = interceptor
        }

        @JvmStatic
        fun getInterceptor(): IPermissionInterceptor {
            if (sInterceptor == null) {
                sInterceptor = object : IPermissionInterceptor {}
            }
            return sInterceptor!!
        }

        @JvmStatic
        fun isGranted(context: Context, vararg permissions: String): Boolean {
            return isGranted(context, PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun isGranted(context: Context, vararg permissions: Array<String>): Boolean {
            return isGranted(context, PermissionUtils.asArrayLists(*permissions))
        }

        @JvmStatic
        fun isGranted(context: Context, permissions: List<String>): Boolean {
            return PermissionApi.isGrantedPermissions(context, permissions)
        }

        @JvmStatic
        fun getDenied(context: Context, vararg permissions: String): List<String> {
            return getDenied(context, PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun getDenied(context: Context, vararg permissions: Array<String>): List<String> {
            return getDenied(context, PermissionUtils.asArrayLists(*permissions))
        }

        @JvmStatic
        fun getDenied(context: Context, permissions: List<String>): List<String> {
            return PermissionApi.getDeniedPermissions(context, permissions)
        }

        @JvmStatic
        fun isSpecial(permission: String): Boolean = PermissionApi.isSpecialPermission(permission)

        @JvmStatic
        fun containsSpecial(vararg permissions: String): Boolean {
            return containsSpecial(PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun containsSpecial(permissions: List<String>?): Boolean {
            return PermissionApi.containsSpecialPermission(permissions)
        }

        @JvmStatic
        fun isPermanentDenied(activity: Activity, vararg permissions: String): Boolean {
            return isPermanentDenied(activity, PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun isPermanentDenied(activity: Activity, vararg permissions: Array<String>): Boolean {
            return isPermanentDenied(activity, PermissionUtils.asArrayLists(*permissions))
        }

        @JvmStatic
        fun isPermanentDenied(activity: Activity, permissions: List<String>): Boolean {
            return PermissionApi.isPermissionPermanentDenied(activity, permissions)
        }

        @JvmStatic
        fun startPermissionActivity(context: Context) {
            startPermissionActivity(context, null as List<String>?)
        }

        @JvmStatic
        fun startPermissionActivity(context: Context, vararg permissions: String) {
            startPermissionActivity(context, PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(context: Context, vararg permissions: Array<String>) {
            startPermissionActivity(context, PermissionUtils.asArrayLists(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(context: Context, permissions: List<String>?) {
            val activity = PermissionUtils.findActivity(context)
            if (activity != null) {
                startPermissionActivity(activity, permissions)
                return
            }
            val permissionIntent = PermissionUtils.getSmartPermissionIntent(context, permissions)
            if (context !is Activity) {
                permissionIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(permissionIntent)
        }

        @JvmStatic
        fun startPermissionActivity(activity: Activity) {
            startPermissionActivity(activity, null as List<String>?)
        }

        @JvmStatic
        fun startPermissionActivity(activity: Activity, vararg permissions: String) {
            startPermissionActivity(activity, PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(activity: Activity, vararg permissions: Array<String>) {
            startPermissionActivity(activity, PermissionUtils.asArrayLists(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(activity: Activity, permissions: List<String>?) {
            startPermissionActivity(activity, permissions, REQUEST_CODE)
        }

        @JvmStatic
        fun startPermissionActivity(activity: Activity, permissions: List<String>?, requestCode: Int) {
            activity.startActivityForResult(
                PermissionUtils.getSmartPermissionIntent(activity, permissions),
                requestCode
            )
        }

        @JvmStatic
        fun startPermissionActivity(
            activity: Activity,
            permission: String,
            callback: OnPermissionPageCallback
        ) {
            startPermissionActivity(activity, PermissionUtils.asArrayList(permission), callback)
        }

        @JvmStatic
        fun startPermissionActivity(
            activity: Activity,
            permissions: Array<String>,
            callback: OnPermissionPageCallback
        ) {
            startPermissionActivity(activity, PermissionUtils.asArrayLists(permissions), callback)
        }

        @JvmStatic
        fun startPermissionActivity(
            activity: Activity,
            permissions: List<String>,
            callback: OnPermissionPageCallback
        ) {
            PermissionPageFragment.beginRequest(activity, permissions as ArrayList<String>, callback)
        }

        @JvmStatic
        fun startPermissionActivity(fragment: Fragment) {
            startPermissionActivity(fragment, null as List<String>?)
        }

        @JvmStatic
        fun startPermissionActivity(fragment: Fragment, vararg permissions: String) {
            startPermissionActivity(fragment, PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(fragment: Fragment, vararg permissions: Array<String>) {
            startPermissionActivity(fragment, PermissionUtils.asArrayLists(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(fragment: Fragment, permissions: List<String>?) {
            startPermissionActivity(fragment, permissions, REQUEST_CODE)
        }

        @JvmStatic
        fun startPermissionActivity(fragment: Fragment, permissions: List<String>?, requestCode: Int) {
            val activity = fragment.activity
            if (activity != null) {
                fragment.startActivityForResult(
                    PermissionUtils.getSmartPermissionIntent(activity, permissions),
                    requestCode
                )
            }
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: Fragment,
            permission: String,
            callback: OnPermissionPageCallback
        ) {
            startPermissionActivity(fragment, PermissionUtils.asArrayList(permission), callback)
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: Fragment,
            permissions: Array<String>,
            callback: OnPermissionPageCallback
        ) {
            startPermissionActivity(fragment, PermissionUtils.asArrayLists(permissions), callback)
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: Fragment,
            permissions: List<String>,
            callback: OnPermissionPageCallback
        ) {
            val activity = fragment.activity
            if (activity != null && !activity.isFinishing) {
                if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed) {
                    PermissionPageFragment.beginRequest(
                        activity,
                        permissions as ArrayList<String>,
                        callback
                    )
                }
            }
        }

        @JvmStatic
        fun startPermissionActivity(fragment: androidx.fragment.app.Fragment) {
            startPermissionActivity(fragment, null as List<String>?)
        }

        @JvmStatic
        fun startPermissionActivity(fragment: androidx.fragment.app.Fragment, vararg permissions: String) {
            startPermissionActivity(fragment, PermissionUtils.asArrayList(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: androidx.fragment.app.Fragment,
            vararg permissions: Array<String>
        ) {
            startPermissionActivity(fragment, PermissionUtils.asArrayLists(*permissions))
        }

        @JvmStatic
        fun startPermissionActivity(fragment: androidx.fragment.app.Fragment, permissions: List<String>?) {
            startPermissionActivity(fragment, permissions, REQUEST_CODE)
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: androidx.fragment.app.Fragment,
            permissions: List<String>?,
            requestCode: Int
        ) {
            val activity: FragmentActivity? = fragment.activity
            if (activity != null) {
                fragment.startActivityForResult(
                    PermissionUtils.getSmartPermissionIntent(activity, permissions),
                    requestCode
                )
            }
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: androidx.fragment.app.Fragment,
            permission: String,
            callback: OnPermissionPageCallback
        ) {
            startPermissionActivity(fragment, PermissionUtils.asArrayList(permission), callback)
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: androidx.fragment.app.Fragment,
            permissions: Array<String>,
            callback: OnPermissionPageCallback
        ) {
            startPermissionActivity(fragment, PermissionUtils.asArrayLists(permissions), callback)
        }

        @JvmStatic
        fun startPermissionActivity(
            fragment: androidx.fragment.app.Fragment,
            permissions: List<String>,
            callback: OnPermissionPageCallback
        ) {
            val activity: FragmentActivity? = fragment.activity
            if (activity != null && !activity.isFinishing) {
                if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed) {
                    PermissionPageFragment.beginRequest(
                        activity,
                        permissions as ArrayList<String>,
                        callback
                    )
                }
            }
        }
    }

    fun permission(vararg permissions: String): XXPermissions {
        return permission(PermissionUtils.asArrayList(*permissions))
    }

    fun permission(vararg permissions: Array<String>): XXPermissions {
        return permission(PermissionUtils.asArrayLists(*permissions))
    }

    fun permission(permissions: List<String>?): XXPermissions {
        if (!permissions.isNullOrEmpty()) {
            if (mPermissions == null) {
                mPermissions = ArrayList(permissions)
                return this
            }
            for (permission in permissions) {
                if (!PermissionUtils.containsPermission(mPermissions, permission)) {
                    mPermissions!!.add(permission)
                }
            }
        }
        return this
    }

    fun interceptor(interceptor: IPermissionInterceptor?): XXPermissions {
        mInterceptor = interceptor
        return this
    }

    fun unchecked(): XXPermissions {
        mCheckMode = false
        return this
    }

    fun request(callback: OnPermissionCallback?) {
        if (mInterceptor == null) {
            mInterceptor = getInterceptor()
        }
        val permissions = ArrayList(mPermissions!!)
        val checkMode = isCheckMode()
        val activity = PermissionUtils.findActivity(mContext)
        if (PermissionChecker.checkActivityStatus(activity, checkMode) &&
            PermissionChecker.checkPermissionArgument(permissions, checkMode)
        ) {
            if (checkMode) {
                PermissionChecker.checkMediaLocationPermission(mContext, permissions)
                PermissionChecker.checkStoragePermission(mContext, permissions)
                PermissionChecker.checkBodySensorsPermission(permissions)
                PermissionChecker.checkLocationPermission(mContext, permissions)
                PermissionChecker.checkTargetSdkVersion(mContext, permissions)
                PermissionChecker.checkManifestPermissions(mContext, permissions)
            }
            PermissionChecker.optimizeDeprecatedPermission(permissions)
            if (!PermissionApi.isGrantedPermissions(mContext, permissions)) {
                mInterceptor!!.requestPermissions(activity!!, permissions, callback)
            } else if (callback != null) {
                mInterceptor!!.grantedPermissions(activity!!, permissions, permissions, true, callback)
            }
        }
    }

    fun revokeOnKill(): Boolean {
        if (!AndroidVersion.isAndroid13()) {
            return false
        }
        return try {
            if (mPermissions!!.size == 1) {
                mContext.revokeSelfPermissionOnKill(mPermissions!![0])
            } else {
                mContext.revokeSelfPermissionsOnKill(mPermissions!!)
            }
            true
        } catch (e: IllegalArgumentException) {
            if (!isCheckMode()) {
                e.printStackTrace()
                false
            } else {
                throw e
            }
        }
    }

    private fun isCheckMode(): Boolean {
        if (mCheckMode == null) {
            if (sCheckMode == null) {
                sCheckMode = PermissionUtils.isDebugMode(mContext)
            }
            mCheckMode = sCheckMode
        }
        return mCheckMode!!
    }
}
