package com.device.discovery

enum class EcryptType(private val nCode: Int) {
    ECRYPTTYPE_WE_NO(0),
    ECRYPTTYPE_WE_WEP(1),
    ECRYPTTYPE_WE_WPA(2);

    override fun toString(): String {
        return nCode.toString()
    }
}
