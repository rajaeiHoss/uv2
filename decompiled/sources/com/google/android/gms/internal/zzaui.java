package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public final class zzaui {
    static final String[] zzefv;
    private static final Map<String, Integer> zzefw;

    static {
        String[] strArr = {"text1", "text2", "icon", "intent_action", "intent_data", "intent_data_id", "intent_extra_data", "suggest_large_icon", "intent_activity", "thing_proto"};
        zzefv = strArr;
        zzefw = new HashMap(strArr.length);
        int i = 0;
        while (true) {
            String[] strArr2 = zzefv;
            if (i < strArr2.length) {
                zzefw.put(strArr2[i], Integer.valueOf(i));
                i++;
            } else {
                break;
            }
        }
    }

    public static String zzaw(int i) {
        if (i < 0) {
            return null;
        }
        String[] strArr = zzefv;
        if (i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    public static int zzet(String str) {
        Integer num = zzefw.get(str);
        if (num != null) {
            return num.intValue();
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 44);
        sb.append("[");
        sb.append(str);
        sb.append("] is not a valid global search section name");
        throw new IllegalArgumentException(sb.toString());
    }
}
