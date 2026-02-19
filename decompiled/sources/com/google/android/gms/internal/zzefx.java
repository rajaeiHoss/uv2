package com.google.android.gms.internal;

final class zzefx implements Runnable {
    private /* synthetic */ zzefu zzncm;

    zzefx(zzefu zzefu) {
        this.zzncm = zzefu;
    }

    public final void run() {
        if (this.zzncm.zznck.zzmxz.zzcbu()) {
            this.zzncm.zznck.zzmxz.zzb("closed", (Throwable) null, new Object[0]);
        }
        this.zzncm.zznck.zzbxn();
    }
}
