package com.streamax.client;

import android.util.Log;

public class AlarmInfo {
    String alarmChannel = "";
    String alarmContent = "";
    String alarmSubType = "";
    String alarmTime = "";
    String alarmType = "";

    /* access modifiers changed from: package-private */
    public void Print(String str) {
        Log.v(str, "**************AlarmInfo start*******************");
        Log.v(str, "alarmTime = " + this.alarmTime);
        Log.v(str, "alarmType = " + this.alarmType);
        Log.v(str, "alarmSubType = " + this.alarmSubType);
        Log.v(str, "alarmChannel = " + this.alarmChannel);
        Log.v(str, "alarmContent = " + this.alarmContent);
        Log.v(str, "**************AlarmInfo end*******************");
    }
}
