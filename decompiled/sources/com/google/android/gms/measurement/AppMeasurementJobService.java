package com.google.android.gms.measurement;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.internal.zzcmy;
import com.google.android.gms.internal.zzcnc;

public final class AppMeasurementJobService extends JobService implements zzcnc {
    private zzcmy<AppMeasurementJobService> zzjfd;

    private final zzcmy<AppMeasurementJobService> zzaxy() {
        if (this.zzjfd == null) {
            this.zzjfd = new zzcmy<>(this);
        }
        return this.zzjfd;
    }

    public final boolean callServiceStopSelfResult(int i) {
        throw new UnsupportedOperationException();
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

    public final boolean onStartJob(JobParameters jobParameters) {
        return zzaxy().onStartJob(jobParameters);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final boolean onUnbind(Intent intent) {
        return zzaxy().onUnbind(intent);
    }

    public final void zza(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }

    public final void zzl(Intent intent) {
    }
}
