package com.device.discovery;

public enum EcryptType {
    ECRYPTTYPE_WE_NO(0),
    ECRYPTTYPE_WE_WEP(1),
    ECRYPTTYPE_WE_WPA(2);
    
    private int nCode;

    private EcryptType(int i) {
        this.nCode = i;
    }

    public String toString() {
        return String.valueOf(this.nCode);
    }
}
