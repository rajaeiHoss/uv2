package org.kobjects.isodate;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class IsoDate {
    public static final int DATE = 1;
    public static final int DATE_TIME = 3;
    public static final int TIME = 2;

    static void dd(StringBuffer stringBuffer, int i) {
        stringBuffer.append((char) ((i / 10) + 48));
        stringBuffer.append((char) ((i % 10) + 48));
    }

    public static String dateToString(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getTimeZone("GMT"));
        instance.setTime(date);
        StringBuffer stringBuffer = new StringBuffer();
        if ((i & 1) != 0) {
            int i2 = instance.get(1);
            dd(stringBuffer, i2 / 100);
            dd(stringBuffer, i2 % 100);
            stringBuffer.append('-');
            dd(stringBuffer, instance.get(2) + 0 + 1);
            stringBuffer.append('-');
            dd(stringBuffer, instance.get(5));
            if (i == 3) {
                stringBuffer.append('T');
            }
        }
        if ((i & 2) != 0) {
            dd(stringBuffer, instance.get(11));
            stringBuffer.append(':');
            dd(stringBuffer, instance.get(12));
            stringBuffer.append(':');
            dd(stringBuffer, instance.get(13));
            stringBuffer.append('.');
            int i3 = instance.get(14);
            stringBuffer.append((char) ((i3 / 100) + 48));
            dd(stringBuffer, i3 % 100);
            stringBuffer.append('Z');
        }
        return stringBuffer.toString();
    }

    public static Date stringToDate(String str, int i) {
        int i2;
        String str2 = str;
        int i3 = i;
        Calendar instance = Calendar.getInstance();
        int i4 = 0;
        if ((i3 & 1) != 0) {
            instance.set(1, Integer.parseInt(str.substring(0, 4)));
            instance.set(2, (Integer.parseInt(str.substring(5, 7)) - 1) + 0);
            instance.set(5, Integer.parseInt(str.substring(8, 10)));
            if (i3 != 3 || str.length() < 11) {
                instance.set(11, 0);
                instance.set(12, 0);
                instance.set(13, 0);
                instance.set(14, 0);
                return instance.getTime();
            }
            str2 = str.substring(11);
        } else {
            instance.setTime(new Date(0));
        }
        instance.set(11, Integer.parseInt(str2.substring(0, 2)));
        instance.set(12, Integer.parseInt(str2.substring(3, 5)));
        instance.set(13, Integer.parseInt(str2.substring(6, 8)));
        if (8 >= str2.length() || str2.charAt(8) != '.') {
            instance.set(14, 0);
            i2 = 8;
        } else {
            int i5 = 100;
            i2 = 8;
            while (true) {
                i2++;
                char charAt = str2.charAt(i2);
                if (charAt < '0' || charAt > '9') {
                    instance.set(14, i4);
                    break;
                } else {
                    i4 += (charAt - '0') * i5;
                    i5 /= 10;
                }
            }
        }
        if (i2 < str2.length()) {
            if (str2.charAt(i2) == '+' || str2.charAt(i2) == '-') {
                instance.setTimeZone(TimeZone.getTimeZone("GMT" + str2.substring(i2)));
            } else if (str2.charAt(i2) == 'Z') {
                instance.setTimeZone(TimeZone.getTimeZone("GMT"));
            } else {
                throw new RuntimeException("illegal time format!");
            }
        }
        return instance.getTime();
    }
}
