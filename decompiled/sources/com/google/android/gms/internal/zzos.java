package com.google.android.gms.internal;

import android.text.TextUtils;

final class zzos extends zzop {
    zzos() {
    }

    private static String zzan(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 0;
        int length = str.length();
        while (i < str.length() && str.charAt(i) == ',') {
            i++;
        }
        while (length > 0 && str.charAt(length - 1) == ',') {
            length--;
        }
        return (i == 0 && length == str.length()) ? str : str.substring(i, length);
    }

    public final String zze(String str, String str2) {
        String zzan = zzan(str);
        String zzan2 = zzan(str2);
        if (TextUtils.isEmpty(zzan)) {
            return zzan2;
        }
        if (TextUtils.isEmpty(zzan2)) {
            return zzan;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(zzan).length() + 1 + String.valueOf(zzan2).length());
        sb.append(zzan);
        sb.append(",");
        sb.append(zzan2);
        return sb.toString();
    }
}
