package com.device.discovery;

public enum IPMode {
    IPMODE_STATIC(0),
    IPMODE_DHCP(1);
    
    private int nCode;

    private IPMode(int i) {
        this.nCode = i;
    }

    public String toString() {
        return String.valueOf(this.nCode);
    }
}
