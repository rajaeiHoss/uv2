package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.zzbq;

public final class zzatk {
    static Object sLock = new Object();
    private static Boolean zzdud;
    static zzcyz zzeei;

    public static void onReceive(Context context, Intent intent) {
        zzatd zzxy = zzark.zzbl(context).zzxy();
        if (intent == null) {
            zzxy.zzed("AnalyticsReceiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzxy.zza("Local AnalyticsReceiver got", action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            boolean zzbn = zzatl.zzbn(context);
            Intent intent2 = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent2.setComponent(new ComponentName(context, "com.google.android.gms.analytics.AnalyticsService"));
            intent2.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            synchronized (sLock) {
                context.startService(intent2);
                if (zzbn) {
                    try {
                        if (zzeei == null) {
                            zzcyz zzcyz = new zzcyz(context, 1, "Analytics WakeLock");
                            zzeei = zzcyz;
                            zzcyz.setReferenceCounted(false);
                        }
                        zzeei.acquire(1000);
                    } catch (SecurityException unused) {
                        zzxy.zzed("Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions.");
                    }
                }
            }
        }
    }

    public static boolean zzbj(Context context) {
        zzbq.checkNotNull(context);
        Boolean bool = zzdud;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zzb = zzatt.zzb(context, "com.google.android.gms.analytics.AnalyticsReceiver", false);
        zzdud = Boolean.valueOf(zzb);
        return zzb;
    }
}
