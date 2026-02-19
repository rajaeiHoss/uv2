package com.device.discovery

class WirelessNetwork {
    @JvmField
    var APMode: Int = 0

    @JvmField
    var Address: String? = null

    @JvmField
    var AlternateDNS: String? = null

    @JvmField
    var Essid: String? = null

    @JvmField
    var Gateway: String? = null

    @JvmField
    var Netmask: String? = null

    @JvmField
    var PrimayDNS: String? = null

    @JvmField
    var ipMode: IPMode? = null

    @JvmField
    var mac: String? = null

    @JvmField
    var nEnable: Int = 0

    @JvmField
    var password: String? = null

    companion object {
        const val WIFI_DISABLE = 1
        const val WIFI_ENABLE = 0
        const val WIFI_MODE_AP = 0
        const val WIFI_MODE_CLIENT = 1
    }
}
