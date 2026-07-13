package com.streamax.utils;

import android.app.Activity;
import android.widget.TextView;
import com.pickview.TimePickerView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static final TimePickerView.Type DefaultType;
    public static String HMS = "HH:mm:ss";
    public static String YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static TimePickerView.Type type;

    public interface TimePickListener {
        void setTimePickListener(TextView textView, Date date);
    }

    static {
        TimePickerView.Type type2 = TimePickerView.Type.ALL;
        DefaultType = type2;
        type = type2;
    }

    public static String getTime(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String parse2String_86399(int secondsOfDay) {
        if (secondsOfDay <= 0 || secondsOfDay > 86400) {
            return "00:00:00";
        }
        int hours = secondsOfDay / 3600;
        int remainingSeconds = secondsOfDay - (hours * 3600);
        return parse2Str_86399(hours, remainingSeconds / 60, remainingSeconds % 60);
    }

    public static String parse2Str_86399(int hours, int minutes, int seconds) {
        return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(hours), Integer.valueOf(minutes), Integer.valueOf(seconds)});
    }

    public static int parse2Int_86399(String timeText) {
        if (timeText.length() != 8) {
            return 0;
        }
        return (StringUtils.parse2Int(timeText.substring(0, 2)) * 3600) + (StringUtils.parse2Int(timeText.substring(3, 5)) * 60) + StringUtils.parse2Int(timeText.substring(6, 8));
    }

    public static int parse2Int_86399(TextView textView) {
        try {
            return parse2Int_86399(StringUtils.getString(textView));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static void setTimePickListener(Activity activity, final TextView textView, final TimePickListener timePickListener) {
        TimePickerView timePickerView = new TimePickerView(activity, type);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(true);
        timePickerView.show();
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            public void onTimeSelect(Date date) {
                timePickListener.setTimePickListener(textView, date);
            }
        });
    }

    public static void setTimePickListener(Activity activity, TextView textView, TimePickerView.Type pickerType, TimePickListener timePickListener) {
        type = pickerType;
        setTimePickListener(activity, textView, timePickListener);
    }

    public static void setTimePickListener(Activity activity, final TextView textView, Date date, TimePickerView.Type pickerType, final TimePickListener timePickListener) {
        TimePickerView timePickerView = new TimePickerView(activity, pickerType);
        timePickerView.setTime(date);
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(true);
        timePickerView.show();
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            public void onTimeSelect(Date date) {
                timePickListener.setTimePickListener(textView, date);
            }
        });
    }
}
