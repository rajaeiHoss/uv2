package com.google.android.gms.internal;

import java.util.concurrent.ScheduledFuture;

final class zzefg implements Runnable {
    private /* synthetic */ zzeey zznaz;

    zzefg(zzeey zzeey) {
        this.zznaz = zzeey;
    }

    public final void run() {
        ScheduledFuture unused = this.zznaz.zznav = null;
        if (this.zznaz.zzbxb()) {
            this.zznaz.interrupt("connection_idle");
        } else {
            this.zznaz.zzbxa();
        }
    }
}
