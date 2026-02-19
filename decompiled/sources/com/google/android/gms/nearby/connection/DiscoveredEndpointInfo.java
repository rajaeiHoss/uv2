package com.google.android.gms.nearby.connection;

import android.bluetooth.BluetoothDevice;

public final class DiscoveredEndpointInfo {
    private final String zzjwe;
    private final String zzjwh;
    private final BluetoothDevice zzjwi;

    public DiscoveredEndpointInfo(String str, BluetoothDevice bluetoothDevice) {
        this.zzjwh = str;
        this.zzjwe = "__UNRECOGNIZED_BLUETOOTH_DEVICE__";
        this.zzjwi = bluetoothDevice;
    }

    public DiscoveredEndpointInfo(String str, String str2) {
        this.zzjwh = str;
        this.zzjwe = str2;
        this.zzjwi = null;
    }

    public final String getEndpointName() {
        return this.zzjwe;
    }

    public final String getServiceId() {
        return this.zzjwh;
    }
}
