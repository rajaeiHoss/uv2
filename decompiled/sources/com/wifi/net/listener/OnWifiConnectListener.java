package com.wifi.net.listener;

public interface OnWifiConnectListener {
    void onWiFiConnectFailure(String str);

    void onWiFiConnectLog(String str);

    void onWiFiConnectSuccess(String str);
}
