package com.device.discovery;

public class Device {
    public static final int ALIVENET_WIFI = 1;
    public static final int ALIVENET_WIRE = 0;
    public String ApName;
    public String UserRole;
    public String Username;
    public String deviceName;
    public DeviceType deviceType;
    public int nAliveNet;
    public int nChannelCount;
    public int nMediaPort;
    public int nMobilePort;
    public int nOnvifPort;
    public int nProtocol;
    public int nRtspPort;
    public int nWebPort;
    public String sn;
    public WiredNetwork wiredNetwork = new WiredNetwork();
    public WirelessNetwork wirelessNetwork = new WirelessNetwork();
}
