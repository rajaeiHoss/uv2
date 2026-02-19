package com.google.android.gms.common.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import com.google.firebase.analytics.FirebaseAnalytics;

public final class zzk {
    private static IntentFilter zzgkv = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzgkw;
    private static float zzgkx = Float.NaN;

    public static int zzcy(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, zzgkv);
        int i = 0;
        if (((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0) {
            i = 1;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        int isInteractive = zzs.zzanw() ? (powerManager.isInteractive() ? 1 : 0) : (powerManager.isScreenOn() ? 1 : 0);
        return (isInteractive << 1) | i;
    }

    public static synchronized float zzcz(Context context) {
        synchronized (zzk.class) {
            if (SystemClock.elapsedRealtime() - zzgkw >= 60000 || Float.isNaN(zzgkx)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, zzgkv);
                if (registerReceiver != null) {
                    zzgkx = ((float) registerReceiver.getIntExtra(FirebaseAnalytics.Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzgkw = SystemClock.elapsedRealtime();
                float f = zzgkx;
                return f;
            }
            float f2 = zzgkx;
            return f2;
        }
    }
}
