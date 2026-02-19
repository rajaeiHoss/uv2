package com.device.discovery

enum class DeviceType(private val nCode: Int) {
    DEVICETYPE_PC(0),
    DEVICETYPE_DVR(1),
    DEVICETYPE_IPC(2),
    DEVICETYPE_NVR(3);

    override fun toString(): String {
        return nCode.toString()
    }
}
