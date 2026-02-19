package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzcx {
    private static String zzkqd;
    static Map<String, String> zzkqe = new HashMap();

    static void zzae(Context context, String str) {
        zzfu.zze(context, "gtm_install_referrer", "referrer", str);
        zzag(context, str);
    }

    public static String zzaf(Context context, String str) {
        if (zzkqd == null) {
            synchronized (zzcx.class) {
                if (zzkqd == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    zzkqd = sharedPreferences != null ? sharedPreferences.getString("referrer", "") : "";
                }
            }
        }
        return zzaw(zzkqd, str);
    }

    public static void zzag(Context context, String str) {
        String zzaw = zzaw(str, "conv");
        if (zzaw != null && zzaw.length() > 0) {
            zzkqe.put(zzaw, str);
            zzfu.zze(context, "gtm_click_referrers", zzaw, str);
        }
    }

    public static String zzaw(String str, String str2) {
        if (str2 != null) {
            String valueOf = String.valueOf(str);
            return Uri.parse(valueOf.length() != 0 ? "http://hostname/?".concat(valueOf) : new String("http://hostname/?")).getQueryParameter(str2);
        } else if (str.length() > 0) {
            return str;
        } else {
            return null;
        }
    }

    public static void zzlx(String str) {
        synchronized (zzcx.class) {
            zzkqd = str;
        }
    }
}
