package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.io.UnsupportedEncodingException;

public final class zzfbd {
    public static String zzsn(String str) throws UnsupportedEncodingException {
        return TextUtils.isEmpty(str) ? "" : zzso(Uri.encode(str));
    }

    public static String zzso(String str) {
        zzbq.checkNotNull(str);
        return str.replace("%2F", "/");
    }

    public static String zzsp(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!str.startsWith("/") && !str.endsWith("/") && !str.contains("//")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split("/")) {
            if (!TextUtils.isEmpty(str2)) {
                if (sb.length() > 0) {
                    sb.append("/");
                }
                sb.append(str2);
            }
        }
        return sb.toString();
    }
}
