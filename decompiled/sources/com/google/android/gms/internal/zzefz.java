package com.google.android.gms.internal;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class zzefz {
    private final Random random;
    private final ScheduledExecutorService zzmxn;
    private final zzemm zzmxz;
    private final long zzndg;
    private final long zzndh;
    private final double zzndi;
    private final double zzndj;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> zzndk;
    private long zzndl;
    private boolean zzndm;

    private zzefz(ScheduledExecutorService scheduledExecutorService, zzemm zzemm, long j, long j2, double d, double d2) {
        this.random = new Random();
        this.zzndm = true;
        this.zzmxn = scheduledExecutorService;
        this.zzmxz = zzemm;
        this.zzndg = j;
        this.zzndh = j2;
        this.zzndj = d;
        this.zzndi = d2;
    }

    /* synthetic */ zzefz(ScheduledExecutorService scheduledExecutorService, zzemm zzemm, long j, long j2, double d, double d2, zzega zzega) {
        this(scheduledExecutorService, zzemm, j, j2, d, d2);
    }

    public final void cancel() {
        if (this.zzndk != null) {
            this.zzmxz.zzb("Cancelling existing retry attempt", (Throwable) null, new Object[0]);
            this.zzndk.cancel(false);
            this.zzndk = null;
        } else {
            this.zzmxz.zzb("No existing retry attempt to cancel", (Throwable) null, new Object[0]);
        }
        this.zzndl = 0;
    }

    public final void zzbxr() {
        this.zzndm = true;
        this.zzndl = 0;
    }

    public final void zzbxs() {
        this.zzndl = this.zzndh;
    }

    public final void zzo(Runnable runnable) {
        zzega zzega = new zzega(this, runnable);
        if (this.zzndk != null) {
            this.zzmxz.zzb("Cancelling previous scheduled retry", (Throwable) null, new Object[0]);
            this.zzndk.cancel(false);
            this.zzndk = null;
        }
        long j = 0;
        if (!this.zzndm) {
            long j2 = this.zzndl;
            this.zzndl = j2 == 0 ? this.zzndg : Math.min((long) (((double) j2) * this.zzndj), this.zzndh);
            double d = this.zzndi;
            long j3 = this.zzndl;
            j = (long) (((1.0d - d) * ((double) j3)) + (d * ((double) j3) * this.random.nextDouble()));
        }
        this.zzndm = false;
        this.zzmxz.zzb("Scheduling retry in %dms", (Throwable) null, Long.valueOf(j));
        this.zzndk = this.zzmxn.schedule(zzega, j, TimeUnit.MILLISECONDS);
    }
}
