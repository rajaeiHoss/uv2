package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.instantapps.InstantAppIntentData;
import com.google.android.gms.instantapps.InstantApps;
import com.google.android.gms.instantapps.LaunchData;
import com.google.android.gms.instantapps.Launcher;
import com.google.android.gms.tasks.Task;

public final class zzcex implements Launcher {
    private static zzcex zzipu;
    private final Context zzaiq;
    private final zzcej zzipv = new zzcej();

    private zzcex(Context context) {
        this.zzaiq = context;
    }

    public static synchronized zzcex zzdx(Context context) {
        zzcex zzcex;
        synchronized (zzcex.class) {
            zzbq.checkNotNull(context);
            Context applicationContext = context.getApplicationContext();
            zzcex zzcex2 = zzipu;
            if (zzcex2 == null || zzcex2.zzaiq != applicationContext) {
                zzipu = new zzcex(applicationContext);
            }
            zzcex = zzipu;
        }
        return zzcex;
    }

    public final InstantAppIntentData getInstantAppIntentData(String str, Intent intent) {
        return zzceu.zza(this.zzaiq, str, intent);
    }

    public final Task<LaunchData> getInstantAppLaunchData(String str) {
        return InstantApps.getInstantAppsClient(this.zzaiq).getInstantAppLaunchData(str);
    }

    public final boolean initializeIntentClient() {
        return zzceu.zzdu(this.zzaiq);
    }
}
