package com.device.discovery;

public enum DeviceType {
    DEVICETYPE_PC(0),
    DEVICETYPE_DVR(1),
    DEVICETYPE_IPC(2),
    DEVICETYPE_NVR(3);
    
    private int nCode;

    private DeviceType(int i) {
        this.nCode = i;
    }

    public String toString() {
        return String.valueOf(this.nCode);
    }
}
