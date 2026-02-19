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

    public static String getTime(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String parse2String_86399(int i) {
        if (i <= 0 || i > 86400) {
            return "00:00:00";
        }
        int i2 = i / 3600;
        int i3 = i - (i2 * 3600);
        return parse2Str_86399(i2, i3 / 60, i3 % 60);
    }

    public static String parse2Str_86399(int i, int i2, int i3) {
        return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public static int parse2Int_86399(String str) {
        if (str.length() != 8) {
            return 0;
        }
        return (StringUtils.parse2Int(str.substring(0, 2)) * 3600) + (StringUtils.parse2Int(str.substring(3, 5)) * 60) + StringUtils.parse2Int(str.substring(6, 8));
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

    public static void setTimePickListener(Activity activity, TextView textView, TimePickerView.Type type2, TimePickListener timePickListener) {
        type = type2;
        setTimePickListener(activity, textView, timePickListener);
    }

    public static void setTimePickListener(Activity activity, final TextView textView, Date date, TimePickerView.Type type2, final TimePickListener timePickListener) {
        TimePickerView timePickerView = new TimePickerView(activity, type2);
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
