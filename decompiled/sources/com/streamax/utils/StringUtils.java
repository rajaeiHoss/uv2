package com.streamax.utils;

import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class StringUtils {
    public String mFormat;
    public final Map<String, Object> tags = new LinkedHashMap();

    public StringUtils(String format) {
        this.mFormat = format;
    }

    public static StringUtils from(String format) {
        return new StringUtils(format);
    }

    public StringUtils with(String tagName, Object value) {
        Map<String, Object> map = this.tags;
        map.put("\\{" + tagName + "\\}", value);
        return this;
    }

    public String format() {
        String formattedText = this.mFormat;
        for (Map.Entry<String, Object> tagEntry : this.tags.entrySet()) {
            formattedText = formattedText.replaceAll(tagEntry.getKey(), tagEntry.getValue().toString());
        }
        return formattedText;
    }

    public static String formatBaseOnChina(String format, Object... args) {
        return String.format(Locale.getDefault(), format, args);
    }

    public static <T> String getString(T t) {
        try {
            if (t instanceof TextView) {
                return ((TextView) t).getText().toString().trim();
            }
            if (t instanceof Integer) {
                return AppProxy.getResources().getString(((Integer) t).intValue());
            }
            if (t instanceof String) {
                return (String) t;
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static <T> int parse2Int(T t) {
        try {
            return Integer.valueOf(getString(t)).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static <T> int getStrLen(T t) {
        try {
            return getString(t).length();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static List<String> getStrDatas(int arrayResId) {
        return Arrays.asList(AppProxy.getResources().getStringArray(arrayResId));
    }

    public static List<Integer> getIntDatas(int arrayResId) {
        int[] intArray = AppProxy.getResources().getIntArray(arrayResId);
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int valueOf : intArray) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }

    public static int getInt(int intResId) {
        return AppProxy.getResources().getInteger(intResId);
    }

    public static Boolean isNumber(String value) {
        try {
            return Boolean.valueOf(Pattern.compile("[0-9]*").matcher(value).matches());
        } catch (Exception unused) {
            return false;
        }
    }
}
