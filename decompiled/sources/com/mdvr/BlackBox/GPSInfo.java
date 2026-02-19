package com.mdvr.BlackBox;

public class GPSInfo {
    public static final byte GPS_DIRECTIONLAT_N = 78;
    public static final byte GPS_DIRECTIONLAT_S = 83;
    public static final byte GPS_DIRECTIONLON_E = 69;
    public static final byte GPS_DIRECTIONlON_W = 87;
    public static final byte GPS_SIGNALSTRENGTH_WEAK = 0;
    public static final byte GPS_SIGNLESTRENGTH_NORMAL = 1;
    public static final byte GPS_SIGNLESTRENGTH_STRONG = 2;
    public static final byte GPS_SOURCE_BEIDOU = 2;
    public static final byte GPS_SOURCE_STANDARD = 1;
    public static final byte GPS_SPEEDUNIT_KMH = 0;
    public static final byte GPS_SPEEDUNIT_MPH = 1;
    public static final byte GPS_STATUS_INVALID = 86;
    public static final byte GPS_STATUS_NOMODULE = 78;
    public static final byte GPS_STATUS_VALID = 65;
    public byte cBdPlanetNum;
    public byte cDirectionLatitude;
    public byte cDirectionLongitude;
    public byte cGpPlanetNum;
    public byte cGpsSource;
    public byte cGpsStatus;
    public byte cLatitudeCent;
    public byte cLatitudeDegree;
    public byte cLongitudeCent;
    public byte cLongitudeDegree;
    public byte cSignalStrength;
    public byte cSpeedUnit;
    public byte cVersion;
    public int lLatitudeSec;
    public int lLongitudeSec;
    public short usGpsAngle;
    public short usSpeed;
}
