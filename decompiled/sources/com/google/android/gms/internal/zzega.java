package com.google.android.gms.internal;

import java.util.concurrent.ScheduledFuture;

final class zzega implements Runnable {
    private /* synthetic */ Runnable zzfct;
    private /* synthetic */ zzefz zzndn;

    zzega(zzefz zzefz, Runnable runnable) {
        this.zzndn = zzefz;
        this.zzfct = runnable;
    }

    public final void run() {
        ScheduledFuture unused = this.zzndn.zzndk = null;
        this.zzfct.run();
    }
}
