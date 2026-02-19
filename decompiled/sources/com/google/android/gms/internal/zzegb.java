package com.google.android.gms.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.concurrent.ScheduledExecutorService;

public final class zzegb {
    private final zzemm zzmxz;
    private long zzndg = 1000;
    private double zzndi = 0.5d;
    private double zzndj = 1.3d;
    private final ScheduledExecutorService zzndo;
    private long zzndp = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;

    public zzegb(ScheduledExecutorService scheduledExecutorService, zzemn zzemn, String str) {
        this.zzndo = scheduledExecutorService;
        this.zzmxz = new zzemm(zzemn, str);
    }

    public final zzegb zzbt(long j) {
        this.zzndg = 1000;
        return this;
    }

    public final zzegb zzbu(long j) {
        this.zzndp = NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
        return this;
    }

    public final zzefz zzbxt() {
        return new zzefz(this.zzndo, this.zzmxz, this.zzndg, this.zzndp, this.zzndj, this.zzndi, (zzega) null);
    }

    public final zzegb zzi(double d) {
        this.zzndj = 1.3d;
        return this;
    }

    public final zzegb zzj(double d) {
        this.zzndi = 0.7d;
        return this;
    }
}
