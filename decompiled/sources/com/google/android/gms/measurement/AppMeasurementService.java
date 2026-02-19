package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.internal.zzcmy;
import com.google.android.gms.internal.zzcnc;

public final class AppMeasurementService extends Service implements zzcnc {
    private zzcmy<AppMeasurementService> zzjfe;

    private final zzcmy<AppMeasurementService> zzaxy() {
        if (this.zzjfe == null) {
            this.zzjfe = new zzcmy<>(this);
        }
        return this.zzjfe;
    }

    public final boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public final IBinder onBind(Intent intent) {
        return zzaxy().onBind(intent);
    }

    public final void onCreate() {
        super.onCreate();
        zzaxy().onCreate();
    }

    public final void onDestroy() {
        zzaxy().onDestroy();
        super.onDestroy();
    }

    public final void onRebind(Intent intent) {
        zzaxy().onRebind(intent);
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return zzaxy().onStartCommand(intent, i, i2);
    }

    public final boolean onUnbind(Intent intent) {
        return zzaxy().onUnbind(intent);
    }

    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    public final void zzl(Intent intent) {
        AppMeasurementReceiver.completeWakefulIntent(intent);
    }
}
