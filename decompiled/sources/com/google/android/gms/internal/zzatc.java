package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public final class zzatc {
    private static volatile Logger zzedt = new zzasm();

    public static Logger getLogger() {
        return zzedt;
    }

    public static void setLogger(Logger logger) {
        zzedt = logger;
    }

    public static void v(String str) {
        zzatd zzaat = zzatd.zzaat();
        if (zzaat != null) {
            zzaat.zzea(str);
        } else if (zzae(0)) {
            Log.v(zzast.zzebn.get(), str);
        }
        Logger logger = zzedt;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    private static boolean zzae(int i) {
        return zzedt != null && zzedt.getLogLevel() <= i;
    }

    public static void zzcz(String str) {
        zzatd zzaat = zzatd.zzaat();
        if (zzaat != null) {
            zzaat.zzed(str);
        } else if (zzae(2)) {
            Log.w(zzast.zzebn.get(), str);
        }
        Logger logger = zzedt;
        if (logger != null) {
            logger.warn(str);
        }
    }

    public static void zzf(String str, Object obj) {
        String str2;
        zzatd zzaat = zzatd.zzaat();
        if (zzaat != null) {
            zzaat.zze(str, obj);
        } else if (zzae(3)) {
            if (obj != null) {
                String valueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(valueOf).length());
                sb.append(str);
                sb.append(":");
                sb.append(valueOf);
                str2 = sb.toString();
            } else {
                str2 = str;
            }
            Log.e(zzast.zzebn.get(), str2);
        }
        Logger logger = zzedt;
        if (logger != null) {
            logger.error(str);
        }
    }
}
