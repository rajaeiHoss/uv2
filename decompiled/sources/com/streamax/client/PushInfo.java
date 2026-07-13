package com.streamax.client;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PushInfo {
    private static final String TAG = "PushInfo";
    public int channel;
    public String devicename = "";
    public int mDay;
    public int mHour;
    public int mMinute;
    public int mMonth;
    public int mSecond;
    public int mYear;
    public String message;
    public int nAlarmType = -1;
    public String time = "";

    public PushInfo(String pushMessage) {
        Log.v(TAG, "[PushInfo]message:" + pushMessage);
        this.message = pushMessage;
        getInfo();
    }

    public PushInfo(String pushMessage, int alarmType) {
        Log.v(TAG, "[PushInfo]message:" + pushMessage);
        this.message = pushMessage;
        this.nAlarmType = alarmType;
        getInfo();
    }

    public void getInfo() {
        Date eventDate;
        String[] messageLines = this.message.split("\n");
        this.devicename = messageLines[0].split(":")[0];
        this.channel = Integer.valueOf(messageLines[1].split(":")[1].split(",")[0]).intValue() - 1;
        try {
            eventDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(messageLines[2].substring(messageLines[2].indexOf(58, 0) + 1));
        } catch (ParseException e) {
            e.printStackTrace();
            eventDate = null;
        }
        if (eventDate != null) {
            Calendar eventCalendar = Calendar.getInstance();
            eventCalendar.setTime(eventDate);
            this.mYear = eventCalendar.get(1);
            this.mMonth = eventCalendar.get(2) + 1;
            this.mDay = eventCalendar.get(5);
            this.mHour = eventCalendar.get(11);
            this.mMinute = eventCalendar.get(12);
            this.mSecond = eventCalendar.get(13);
            this.time = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(eventCalendar.get(1)), Integer.valueOf(eventCalendar.get(2) + 1), Integer.valueOf(eventCalendar.get(5)), Integer.valueOf(eventCalendar.get(11)), Integer.valueOf(eventCalendar.get(12)), Integer.valueOf(eventCalendar.get(13))});
        }
        Log.v(TAG, "devicename:" + this.devicename);
        Log.v(TAG, "channel:" + this.channel);
        Log.v(TAG, "time:" + this.time);
    }

    /* access modifiers changed from: package-private */
    public void Print(String logTag) {
        Log.v(logTag, "****************start**********************");
        Log.v(logTag, "message = " + this.message);
        Log.v(logTag, "devicename = " + this.devicename);
        Log.v(logTag, "time = " + this.time);
        Log.v(logTag, "mYear = " + this.mYear);
        Log.v(logTag, "mMonth = " + this.mMonth);
        Log.v(logTag, "mDay = " + this.mDay);
        Log.v(logTag, "mHour = " + this.mHour);
        Log.v(logTag, "mMinute = " + this.mMinute);
        Log.v(logTag, "mSecond = " + this.mSecond);
        Log.v(logTag, "channel = " + this.channel);
        Log.v(logTag, "nAlarmType = " + this.nAlarmType);
        Log.v(logTag, "***************end************************");
    }
}
