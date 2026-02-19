package com.google.android.gms.internal;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class zzeou implements zzeig {
    private ScheduledThreadPoolExecutor zznrc;

    public zzeou() {
        zzeov zzeov = new zzeov(this, 1, new zzeow(this, (zzeov) null));
        this.zznrc = zzeov;
        zzeov.setKeepAliveTime(3, TimeUnit.SECONDS);
    }

    public final void restart() {
        this.zznrc.setCorePoolSize(1);
    }

    public final void shutdown() {
        this.zznrc.setCorePoolSize(0);
    }

    public final ScheduledExecutorService zzbwm() {
        return this.zznrc;
    }

    public abstract void zzj(Throwable th);

    public final void zzp(Runnable runnable) {
        this.zznrc.execute(runnable);
    }
}
