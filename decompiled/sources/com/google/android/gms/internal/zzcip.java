package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.zzbq;

abstract class zzcip {
    private static volatile Handler handler;
    /* access modifiers changed from: private */
    public boolean enabled = true;
    /* access modifiers changed from: private */
    public volatile long zzhhl;
    /* access modifiers changed from: private */
    public final zzckj zzjev;
    private final Runnable zzjhk;

    zzcip(zzckj zzckj) {
        zzbq.checkNotNull(zzckj);
        this.zzjev = zzckj;
        this.zzjhk = new zzciq(this, zzckj);
    }

    private final Handler getHandler() {
        Handler handler2;
        if (handler != null) {
            return handler;
        }
        synchronized (zzcip.class) {
            if (handler == null) {
                handler = new Handler(this.zzjev.getContext().getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    public final void cancel() {
        this.zzhhl = 0;
        getHandler().removeCallbacks(this.zzjhk);
    }

    public abstract void run();

    public final boolean zzea() {
        return this.zzhhl != 0;
    }

    public final void zzs(long j) {
        cancel();
        if (j >= 0) {
            this.zzhhl = this.zzjev.zzxx().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzjhk, j)) {
                this.zzjev.zzayp().zzbau().zzj("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }
}
