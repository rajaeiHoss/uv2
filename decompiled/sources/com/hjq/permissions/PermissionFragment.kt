package com.hjq.permissions

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.util.ArrayList
import java.util.Arrays
import java.util.Random

class PermissionFragment : Fragment(), Runnable {
    private var mCallBack: OnPermissionCallback? = null
    private var mDangerousRequest: Boolean = false
    private var mInterceptor: IPermissionInterceptor? = null
    private var mRequestFlag: Boolean = false
    private var mScreenOrientation: Int = 0
    private var mSpecialRequest: Boolean = false

    companion object {
        private const val REQUEST_CODE: String = "request_code"
        private const val REQUEST_PERMISSIONS: String = "request_permissions"
        private val REQUEST_CODE_ARRAY: MutableList<Int> = ArrayList()

        @JvmStatic
        fun beginRequest(
            activity: Activity,
            permissions: ArrayList<String>,
            interceptor: IPermissionInterceptor,
            callback: OnPermissionCallback?
        ) {
            var requestCode: Int
            do {
                requestCode = Random().nextInt(1 shl 8)
            } while (REQUEST_CODE_ARRAY.contains(requestCode))
            REQUEST_CODE_ARRAY.add(requestCode)
            val permissionFragment = PermissionFragment()
            val bundle = Bundle()
            bundle.putInt(REQUEST_CODE, requestCode)
            bundle.putStringArrayList(REQUEST_PERMISSIONS, permissions)
            permissionFragment.arguments = bundle
            permissionFragment.retainInstance = true
            permissionFragment.setRequestFlag(true)
            permissionFragment.setCallBack(callback)
            permissionFragment.setInterceptor(interceptor)
            permissionFragment.attachActivity(activity)
        }
    }

    fun attachActivity(activity: Activity) {
        activity.fragmentManager.beginTransaction().add(this, toString()).commitAllowingStateLoss()
    }

    fun detachActivity(activity: Activity) {
        activity.fragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
    }

    fun setCallBack(callback: OnPermissionCallback?) {
        mCallBack = callback
    }

    fun setRequestFlag(requestFlag: Boolean) {
        mRequestFlag = requestFlag
    }

    fun setInterceptor(interceptor: IPermissionInterceptor?) {
        mInterceptor = interceptor
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity = activity
        if (activity != null) {
            val requestedOrientation = activity.requestedOrientation
            mScreenOrientation = requestedOrientation
            if (requestedOrientation == -1) {
                PermissionUtils.lockActivityOrientation(activity)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        val activity = activity
        if (activity != null && mScreenOrientation == -1 && activity.requestedOrientation != -1) {
            activity.requestedOrientation = -1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCallBack = null
    }

    override fun onResume() {
        super.onResume()
        if (!mRequestFlag) {
            activity?.let { detachActivity(it) }
        } else if (!mSpecialRequest) {
            mSpecialRequest = true
            requestSpecialPermission()
        }
    }

    fun requestSpecialPermission() {
        val arguments = arguments
        val activity = activity
        if (arguments != null && activity != null) {
            var hasSpecialPermissionRequest = false
            val requestPermissions = arguments.getStringArrayList(REQUEST_PERMISSIONS)
            if (requestPermissions != null) {
                for (permission in requestPermissions) {
                    if (PermissionApi.isSpecialPermission(permission) &&
                        !PermissionApi.isGrantedPermission(activity, permission) &&
                        (AndroidVersion.isAndroid11() ||
                            !PermissionUtils.equalsPermission(
                                permission,
                                Permission.MANAGE_EXTERNAL_STORAGE
                            ))
                    ) {
                        startActivityForResult(
                            PermissionUtils.getSmartPermissionIntent(
                                activity,
                                PermissionUtils.asArrayList(permission)
                            ),
                            arguments.getInt(REQUEST_CODE)
                        )
                        hasSpecialPermissionRequest = true
                    }
                }
            }
            if (!hasSpecialPermissionRequest) {
                requestDangerousPermission()
            }
        }
    }

    fun requestDangerousPermission() {
        val activity = activity
        val arguments = arguments
        if (activity != null && arguments != null) {
            val requestCode = arguments.getInt(REQUEST_CODE)
            val requestPermissions = arguments.getStringArrayList(REQUEST_PERMISSIONS)
            if (!requestPermissions.isNullOrEmpty()) {
                if (!AndroidVersion.isAndroid6()) {
                    val grantResults = IntArray(requestPermissions.size)
                    for (index in requestPermissions.indices) {
                        grantResults[index] =
                            if (PermissionApi.isGrantedPermission(activity, requestPermissions[index])) {
                                0
                            } else {
                                -1
                            }
                    }
                    onRequestPermissionsResult(
                        requestCode,
                        requestPermissions.toTypedArray(),
                        grantResults
                    )
                } else if (AndroidVersion.isAndroid13() &&
                    requestPermissions.size >= 2 &&
                    PermissionUtils.containsPermission(
                        requestPermissions,
                        Permission.BODY_SENSORS_BACKGROUND
                    )
                ) {
                    val firstRequestPermissions = ArrayList(requestPermissions)
                    firstRequestPermissions.remove(Permission.BODY_SENSORS_BACKGROUND)
                    splitTwiceRequestPermission(
                        activity,
                        requestPermissions,
                        firstRequestPermissions,
                        requestCode
                    )
                } else if (AndroidVersion.isAndroid10() &&
                    requestPermissions.size >= 2 &&
                    PermissionUtils.containsPermission(
                        requestPermissions,
                        Permission.ACCESS_BACKGROUND_LOCATION
                    )
                ) {
                    val firstRequestPermissions = ArrayList(requestPermissions)
                    firstRequestPermissions.remove(Permission.ACCESS_BACKGROUND_LOCATION)
                    splitTwiceRequestPermission(
                        activity,
                        requestPermissions,
                        firstRequestPermissions,
                        requestCode
                    )
                } else if (!AndroidVersion.isAndroid10() ||
                    !PermissionUtils.containsPermission(
                        requestPermissions,
                        Permission.ACCESS_MEDIA_LOCATION
                    ) ||
                    !PermissionUtils.containsPermission(
                        requestPermissions,
                        Permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    requestPermissions(requestPermissions.toTypedArray(), requestCode)
                } else {
                    val firstRequestPermissions = ArrayList(requestPermissions)
                    firstRequestPermissions.remove(Permission.ACCESS_MEDIA_LOCATION)
                    splitTwiceRequestPermission(
                        activity,
                        requestPermissions,
                        firstRequestPermissions,
                        requestCode
                    )
                }
            }
        }
    }

    fun splitTwiceRequestPermission(
        activity: Activity,
        allPermissions: ArrayList<String>,
        firstRequestPermissions: ArrayList<String>,
        requestCode: Int
    ) {
        val secondRequestPermissions = ArrayList(allPermissions)
        for (permission in firstRequestPermissions) {
            secondRequestPermissions.remove(permission)
        }
        beginRequest(activity, firstRequestPermissions, object : IPermissionInterceptor {}, object :
            OnPermissionCallback {
            override fun onGranted(grantedPermissions: List<String>, allGranted: Boolean) {
                if (allGranted && isAdded) {
                    PermissionUtils.postDelayed(
                        Runnable {
                            beginRequest(
                                activity,
                                secondRequestPermissions,
                                object : IPermissionInterceptor {},
                                object : OnPermissionCallback {
                                    override fun onGranted(
                                        grantedPermissions: List<String>,
                                        allGranted: Boolean
                                    ) {
                                        if (allGranted && isAdded) {
                                            val grantResults = IntArray(allPermissions.size)
                                            Arrays.fill(grantResults, 0)
                                            onRequestPermissionsResult(
                                                requestCode,
                                                allPermissions.toTypedArray(),
                                                grantResults
                                            )
                                        }
                                    }

                                    override fun onDenied(
                                        deniedPermissions: List<String>,
                                        doNotAskAgain: Boolean
                                    ) {
                                        if (isAdded) {
                                            val grantResults = IntArray(allPermissions.size)
                                            for (index in allPermissions.indices) {
                                                grantResults[index] =
                                                    if (PermissionUtils.containsPermission(
                                                            deniedPermissions,
                                                            allPermissions[index]
                                                        )
                                                    ) {
                                                        -1
                                                    } else {
                                                        0
                                                    }
                                            }
                                            onRequestPermissionsResult(
                                                requestCode,
                                                allPermissions.toTypedArray(),
                                                grantResults
                                            )
                                        }
                                    }
                                }
                            )
                        },
                        if (AndroidVersion.isAndroid13()) 150 else 0
                    )
                }
            }

            override fun onDenied(deniedPermissions: List<String>, doNotAskAgain: Boolean) {
                if (isAdded) {
                    val grantResults = IntArray(allPermissions.size)
                    Arrays.fill(grantResults, -1)
                    onRequestPermissionsResult(
                        requestCode,
                        allPermissions.toTypedArray(),
                        grantResults
                    )
                }
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (permissions.isNotEmpty() && grantResults.isNotEmpty()) {
            val arguments = arguments
            val activity = activity
            if (activity != null &&
                arguments != null &&
                mInterceptor != null &&
                requestCode == arguments.getInt(REQUEST_CODE)
            ) {
                val callback = mCallBack
                mCallBack = null
                val interceptor = mInterceptor
                mInterceptor = null
                PermissionUtils.optimizePermissionResults(activity, permissions, grantResults)
                val allPermissions = PermissionUtils.asArrayList(*permissions)
                REQUEST_CODE_ARRAY.remove(requestCode)
                detachActivity(activity)
                val grantedPermissions = PermissionApi.getGrantedPermissions(
                    allPermissions,
                    grantResults
                )
                if (grantedPermissions.size == allPermissions.size) {
                    interceptor!!.grantedPermissions(
                        activity,
                        allPermissions,
                        grantedPermissions,
                        true,
                        callback
                    )
                    return
                }
                val deniedPermissions = PermissionApi.getDeniedPermissions(
                    allPermissions,
                    grantResults
                )
                interceptor!!.deniedPermissions(
                    activity,
                    allPermissions,
                    deniedPermissions,
                    PermissionApi.isPermissionPermanentDenied(activity, deniedPermissions),
                    callback
                )
                if (grantedPermissions.isNotEmpty()) {
                    interceptor.grantedPermissions(
                        activity,
                        allPermissions,
                        grantedPermissions,
                        false,
                        callback
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val activity = activity
        val arguments = arguments
        val requestPermissions = arguments?.getStringArrayList(REQUEST_PERMISSIONS)
        if (activity != null &&
            arguments != null &&
            !mDangerousRequest &&
            requestCode == arguments.getInt(REQUEST_CODE) &&
            !requestPermissions.isNullOrEmpty()
        ) {
            mDangerousRequest = true
            PermissionUtils.postActivityResult(requestPermissions, this)
        }
    }

    override fun run() {
        if (isAdded) {
            requestDangerousPermission()
        }
    }
}
