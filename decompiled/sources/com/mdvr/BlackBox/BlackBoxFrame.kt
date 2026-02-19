package com.mdvr.BlackBox

import android.util.Log

class BlackBoxFrame {
    @JvmField
    var datetime: DateTime? = null

    @JvmField
    var gpsInfo: GPSInfo? = null

    @JvmField
    var ullFrameStamp: Long = 0

    fun print() {
        val dt = datetime ?: return
        val gps = gpsInfo ?: return
        val format = String.format(
            "%04d-%02d-%02d %02d:%02d:%02d",
            dt.cYear.toInt() + 2000,
            dt.cMonth.toInt(),
            dt.cDay.toInt(),
            dt.cHour.toInt(),
            dt.cMinute.toInt(),
            dt.cSecond.toInt()
        )
        Log.v(TAG, "[$format]${gps.usSpeed}")
    }

    companion object {
        private const val TAG = "BlackBoxFrame"
    }
}
