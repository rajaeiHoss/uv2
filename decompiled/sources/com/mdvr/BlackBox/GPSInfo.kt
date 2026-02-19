package com.mdvr.BlackBox

class GPSInfo {
    @JvmField
    var cBdPlanetNum: Byte = 0

    @JvmField
    var cDirectionLatitude: Byte = 0

    @JvmField
    var cDirectionLongitude: Byte = 0

    @JvmField
    var cGpPlanetNum: Byte = 0

    @JvmField
    var cGpsSource: Byte = 0

    @JvmField
    var cGpsStatus: Byte = 0

    @JvmField
    var cLatitudeCent: Byte = 0

    @JvmField
    var cLatitudeDegree: Byte = 0

    @JvmField
    var cLongitudeCent: Byte = 0

    @JvmField
    var cLongitudeDegree: Byte = 0

    @JvmField
    var cSignalStrength: Byte = 0

    @JvmField
    var cSpeedUnit: Byte = 0

    @JvmField
    var cVersion: Byte = 0

    @JvmField
    var lLatitudeSec: Int = 0

    @JvmField
    var lLongitudeSec: Int = 0

    @JvmField
    var usGpsAngle: Short = 0

    @JvmField
    var usSpeed: Short = 0

    companion object {
        const val GPS_DIRECTIONLAT_N: Byte = 78
        const val GPS_DIRECTIONLAT_S: Byte = 83
        const val GPS_DIRECTIONLON_E: Byte = 69
        const val GPS_DIRECTIONlON_W: Byte = 87
        const val GPS_SIGNALSTRENGTH_WEAK: Byte = 0
        const val GPS_SIGNLESTRENGTH_NORMAL: Byte = 1
        const val GPS_SIGNLESTRENGTH_STRONG: Byte = 2
        const val GPS_SOURCE_BEIDOU: Byte = 2
        const val GPS_SOURCE_STANDARD: Byte = 1
        const val GPS_SPEEDUNIT_KMH: Byte = 0
        const val GPS_SPEEDUNIT_MPH: Byte = 1
        const val GPS_STATUS_INVALID: Byte = 86
        const val GPS_STATUS_NOMODULE: Byte = 78
        const val GPS_STATUS_VALID: Byte = 65
    }
}
