package com.dvr.bluetooth

class DeviceAccount {
    @JvmField
    var password: String? = null

    @JvmField
    var role: Int = 0

    @JvmField
    var username: String? = null

    companion object {
        private const val TAG: String = "DeviceAccount"
    }
}
