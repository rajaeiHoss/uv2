package com.hjq.permissions

interface OnPermissionPageCallback {
    fun onDenied()

    fun onGranted()
}
