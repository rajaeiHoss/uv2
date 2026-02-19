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

    public StringUtils(String str) {
        this.mFormat = str;
    }

    public static StringUtils from(String str) {
        return new StringUtils(str);
    }

    public StringUtils with(String str, Object obj) {
        Map<String, Object> map = this.tags;
        map.put("\\{" + str + "\\}", obj);
        return this;
    }

    public String format() {
        String str = this.mFormat;
        for (Map.Entry next : this.tags.entrySet()) {
            str = str.replaceAll((String) next.getKey(), next.getValue().toString());
        }
        return str;
    }

    public static String formatBaseOnChina(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
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

    public static List<String> getStrDatas(int i) {
        return Arrays.asList(AppProxy.getResources().getStringArray(i));
    }

    public static List<Integer> getIntDatas(int i) {
        int[] intArray = AppProxy.getResources().getIntArray(i);
        ArrayList arrayList = new ArrayList();
        for (int valueOf : intArray) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        return arrayList;
    }

    public static int getInt(int i) {
        return AppProxy.getResources().getInteger(i);
    }

    public static Boolean isNumber(String str) {
        try {
            return Boolean.valueOf(Pattern.compile("[0-9]*").matcher(str).matches());
        } catch (Exception unused) {
            return false;
        }
    }
}
