package com.hjq.permissions

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import java.util.ArrayList

class PermissionPageFragment : Fragment(), Runnable {
    private var mCallBack: OnPermissionPageCallback? = null
    private var mRequestFlag: Boolean = false
    private var mStartActivityFlag: Boolean = false

    companion object {
        private const val REQUEST_PERMISSIONS: String = "request_permissions"

        @JvmStatic
        fun beginRequest(
            activity: Activity,
            permissions: ArrayList<String>,
            callback: OnPermissionPageCallback
        ) {
            val permissionPageFragment = PermissionPageFragment()
            val bundle = Bundle()
            bundle.putStringArrayList(REQUEST_PERMISSIONS, permissions)
            permissionPageFragment.arguments = bundle
            permissionPageFragment.retainInstance = true
            permissionPageFragment.setRequestFlag(true)
            permissionPageFragment.setCallBack(callback)
            permissionPageFragment.attachActivity(activity)
        }
    }

    fun attachActivity(activity: Activity) {
        activity.fragmentManager.beginTransaction().add(this, toString()).commitAllowingStateLoss()
    }

    fun detachActivity(activity: Activity) {
        activity.fragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
    }

    override fun onResume() {
        super.onResume()
        if (!mRequestFlag) {
            activity?.let { detachActivity(it) }
        } else if (!mStartActivityFlag) {
            mStartActivityFlag = true
            val arguments = arguments
            val activity = activity
            if (arguments != null && activity != null) {
                startActivityForResult(
                    PermissionUtils.getSmartPermissionIntent(
                        activity,
                        arguments.getStringArrayList(REQUEST_PERMISSIONS)
                    ),
                    1025
                )
            }
        }
    }

    fun setCallBack(callback: OnPermissionPageCallback?) {
        mCallBack = callback
    }

    fun setRequestFlag(requestFlag: Boolean) {
        mRequestFlag = requestFlag
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1025) {
            val activity = activity
            val arguments = arguments
            val requestPermissions = arguments?.getStringArrayList(REQUEST_PERMISSIONS)
            if (activity != null && requestPermissions != null && requestPermissions.isNotEmpty()) {
                PermissionUtils.postActivityResult(requestPermissions, this)
            }
        }
    }

    override fun run() {
        val activity = activity
        if (isAdded && activity != null) {
            val callback = mCallBack
            mCallBack = null
            if (callback == null) {
                detachActivity(activity)
                return
            }
            val requestPermissions = arguments?.getStringArrayList(REQUEST_PERMISSIONS) ?: return
            if (PermissionApi.getGrantedPermissions(activity, requestPermissions).size ==
                requestPermissions.size
            ) {
                callback.onGranted()
            } else {
                callback.onDenied()
            }
        }
    }
}
