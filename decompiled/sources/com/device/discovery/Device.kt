package com.device.discovery

class Device {
    @JvmField
    var ApName: String? = null

    @JvmField
    var UserRole: String? = null

    @JvmField
    var Username: String? = null

    @JvmField
    var deviceName: String? = null

    @JvmField
    var deviceType: DeviceType? = null

    @JvmField
    var nAliveNet: Int = 0

    @JvmField
    var nChannelCount: Int = 0

    @JvmField
    var nMediaPort: Int = 0

    @JvmField
    var nMobilePort: Int = 0

    @JvmField
    var nOnvifPort: Int = 0

    @JvmField
    var nProtocol: Int = 0

    @JvmField
    var nRtspPort: Int = 0

    @JvmField
    var nWebPort: Int = 0

    @JvmField
    var sn: String? = null

    @JvmField
    var wiredNetwork: WiredNetwork = WiredNetwork()

    @JvmField
    var wirelessNetwork: WirelessNetwork = WirelessNetwork()

    companion object {
        const val ALIVENET_WIFI = 1
        const val ALIVENET_WIRE = 0
    }
}
