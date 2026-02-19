package com.wifi.net.listener

interface OnWifiConnectListener {
    fun onWiFiConnectFailure(str: String)

    fun onWiFiConnectLog(str: String)

    fun onWiFiConnectSuccess(str: String)
}
