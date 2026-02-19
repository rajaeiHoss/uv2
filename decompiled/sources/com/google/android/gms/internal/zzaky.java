package com.google.android.gms.internal;

import android.util.Log;
import com.google.ads.AdRequest;

@zzabh
public class zzaky {
    public static void e(String str) {
        if (zzae(6)) {
            Log.e(AdRequest.LOGTAG, str);
        }
    }

    public static void zza(String str, Throwable th) {
        if (zzae(3)) {
            Log.d(AdRequest.LOGTAG, str, th);
        }
    }

    public static boolean zzae(int i) {
        return i >= 5 || Log.isLoggable(AdRequest.LOGTAG, i);
    }

    public static void zzb(String str, Throwable th) {
        if (zzae(6)) {
            Log.e(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzby(String str) {
        if (zzae(3)) {
            Log.d(AdRequest.LOGTAG, str);
        }
    }

    public static void zzc(String str, Throwable th) {
        if (zzae(5)) {
            Log.w(AdRequest.LOGTAG, str, th);
        }
    }

    public static void zzcy(String str) {
        if (zzae(4)) {
            Log.i(AdRequest.LOGTAG, str);
        }
    }

    public static void zzcz(String str) {
        if (zzae(5)) {
            Log.w(AdRequest.LOGTAG, str);
        }
    }
}
