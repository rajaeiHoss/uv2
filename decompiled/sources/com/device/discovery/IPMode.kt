package com.device.discovery

enum class IPMode(private val nCode: Int) {
    IPMODE_STATIC(0),
    IPMODE_DHCP(1);

    override fun toString(): String {
        return nCode.toString()
    }
}
