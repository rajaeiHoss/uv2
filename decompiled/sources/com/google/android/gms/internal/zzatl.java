package com.google.android.gms.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzato;

public final class zzatl<T extends Context & zzato> {
    private static Boolean zzeek;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public final T zzeej;

    public zzatl(T t) {
        zzbq.checkNotNull(t);
        this.zzeej = t;
    }

    private final void zza(Integer num, JobParameters jobParameters) {
        zzark zzbl = zzark.zzbl(this.zzeej);
        zzbl.zzyc().zza((zzasr) new zzatm(this, num, zzbl, zzbl.zzxy(), jobParameters));
    }

    public static boolean zzbn(Context context) {
        zzbq.checkNotNull(context);
        Boolean bool = zzeek;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zzp = zzatt.zzp(context, "com.google.android.gms.analytics.AnalyticsService");
        zzeek = Boolean.valueOf(zzp);
        return zzp;
    }

    public final void onCreate() {
        zzark.zzbl(this.zzeej).zzxy().zzea("Local AnalyticsService is starting up");
    }

    public final void onDestroy() {
        zzark.zzbl(this.zzeej).zzxy().zzea("Local AnalyticsService is shutting down");
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        try {
            synchronized (zzatk.sLock) {
                zzcyz zzcyz = zzatk.zzeei;
                if (zzcyz != null && zzcyz.isHeld()) {
                    zzcyz.release();
                }
            }
        } catch (SecurityException unused) {
        }
        zzatd zzxy = zzark.zzbl(this.zzeej).zzxy();
        if (intent == null) {
            zzxy.zzed("AnalyticsService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzxy.zza("Local AnalyticsService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zza(Integer.valueOf(i2), (JobParameters) null);
        }
        return 2;
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        zzatd zzxy = zzark.zzbl(this.zzeej).zzxy();
        String string = jobParameters.getExtras().getString("action");
        zzxy.zza("Local AnalyticsJobService called. action", string);
        if (!"com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(string)) {
            return true;
        }
        zza((Integer) null, jobParameters);
        return true;
    }
}
