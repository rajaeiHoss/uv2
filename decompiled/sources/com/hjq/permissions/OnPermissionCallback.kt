package com.hjq.permissions

interface OnPermissionCallback {
    fun onDenied(deniedPermissions: List<String>, doNotAskAgain: Boolean) {}

    fun onGranted(grantedPermissions: List<String>, allGranted: Boolean)

    object CC {
        @JvmStatic
        fun `$default$onDenied`(
            onPermissionCallback: OnPermissionCallback,
            deniedPermissions: List<String>,
            doNotAskAgain: Boolean
        ) {
        }
    }
}
