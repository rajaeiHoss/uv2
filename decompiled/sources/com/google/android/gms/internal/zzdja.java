package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class zzdja {
    private boolean mClosed;
    private String zzkog;
    private final ScheduledExecutorService zzkse;
    private ScheduledFuture<?> zzksg;

    public zzdja() {
        this(Executors.newSingleThreadScheduledExecutor());
    }

    private zzdja(ScheduledExecutorService scheduledExecutorService) {
        this.zzksg = null;
        this.zzkog = null;
        this.zzkse = scheduledExecutorService;
        this.mClosed = false;
    }

    public final void zza(Context context, zzdim zzdim, long j, zzdid zzdid) {
        synchronized (this) {
            ScheduledFuture<?> scheduledFuture = this.zzksg;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.zzksg = this.zzkse.schedule(new zzdiz(context, zzdim, zzdid), 0, TimeUnit.MILLISECONDS);
        }
    }
}
