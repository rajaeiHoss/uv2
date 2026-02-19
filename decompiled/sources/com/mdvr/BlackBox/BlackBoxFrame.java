package com.mdvr.BlackBox;

import android.util.Log;

public class BlackBoxFrame {
    private static final String TAG = "BlackBoxFrame";
    public DateTime datetime;
    public GPSInfo gpsInfo;
    long ullFrameStamp;

    public void print() {
        String format = String.format("%04d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(this.datetime.cYear + 2000), Byte.valueOf(this.datetime.cMonth), Byte.valueOf(this.datetime.cDay), Byte.valueOf(this.datetime.cHour), Byte.valueOf(this.datetime.cMinute), Byte.valueOf(this.datetime.cSecond)});
        Log.v(TAG, "[" + format + "]" + this.gpsInfo.usSpeed);
    }
}
