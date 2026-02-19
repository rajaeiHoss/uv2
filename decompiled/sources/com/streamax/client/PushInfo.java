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

    public PushInfo(String str) {
        Log.v(TAG, "[PushInfo]message:" + str);
        this.message = str;
        getInfo();
    }

    public PushInfo(String str, int i) {
        Log.v(TAG, "[PushInfo]message:" + str);
        this.message = str;
        this.nAlarmType = i;
        getInfo();
    }

    public void getInfo() {
        Date date;
        String[] split = this.message.split("\n");
        this.devicename = split[0].split(":")[0];
        this.channel = Integer.valueOf(split[1].split(":")[1].split(",")[0]).intValue() - 1;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(split[2].substring(split[2].indexOf(58, 0) + 1));
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            this.mYear = instance.get(1);
            this.mMonth = instance.get(2) + 1;
            this.mDay = instance.get(5);
            this.mHour = instance.get(11);
            this.mMinute = instance.get(12);
            this.mSecond = instance.get(13);
            this.time = String.format("%04d%02d%02d%02d%02d%02d", new Object[]{Integer.valueOf(instance.get(1)), Integer.valueOf(instance.get(2) + 1), Integer.valueOf(instance.get(5)), Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12)), Integer.valueOf(instance.get(13))});
        }
        Log.v(TAG, "devicename:" + this.devicename);
        Log.v(TAG, "channel:" + this.channel);
        Log.v(TAG, "time:" + this.time);
    }

    /* access modifiers changed from: package-private */
    public void Print(String str) {
        Log.v(str, "****************start**********************");
        Log.v(str, "message = " + this.message);
        Log.v(str, "devicename = " + this.devicename);
        Log.v(str, "time = " + this.time);
        Log.v(str, "mYear = " + this.mYear);
        Log.v(str, "mMonth = " + this.mMonth);
        Log.v(str, "mDay = " + this.mDay);
        Log.v(str, "mHour = " + this.mHour);
        Log.v(str, "mMinute = " + this.mMinute);
        Log.v(str, "mSecond = " + this.mSecond);
        Log.v(str, "channel = " + this.channel);
        Log.v(str, "nAlarmType = " + this.nAlarmType);
        Log.v(str, "***************end************************");
    }
}
