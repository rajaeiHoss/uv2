package com.google.android.gms.internal;

final class zzefv implements Runnable {
    private /* synthetic */ zzefu zzncm;

    zzefv(zzefu zzefu) {
        this.zzncm = zzefu;
    }

    public final void run() {
        this.zzncm.zznck.zznci.cancel(false);
        boolean unused = this.zzncm.zznck.zzncc = true;
        if (this.zzncm.zznck.zzmxz.zzcbu()) {
            this.zzncm.zznck.zzmxz.zzb("websocket opened", (Throwable) null, new Object[0]);
        }
        this.zzncm.zznck.zzbxm();
    }
}
