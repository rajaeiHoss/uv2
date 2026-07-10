package com.streamax.client

import android.util.Log

class AlarmInfo {
    @JvmField var alarmChannel: String = ""
    @JvmField var alarmContent: String = ""
    @JvmField var alarmSubType: String = ""
    @JvmField var alarmTime: String = ""
    @JvmField var alarmType: String = ""

    fun Print(tag: String) {
        Log.v(tag, "**************AlarmInfo start*******************")
        Log.v(tag, "alarmTime = $alarmTime")
        Log.v(tag, "alarmType = $alarmType")
        Log.v(tag, "alarmSubType = $alarmSubType")
        Log.v(tag, "alarmChannel = $alarmChannel")
        Log.v(tag, "alarmContent = $alarmContent")
        Log.v(tag, "**************AlarmInfo end*******************")
    }
}
