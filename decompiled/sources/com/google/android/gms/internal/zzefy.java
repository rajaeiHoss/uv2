package com.google.android.gms.internal;

import java.io.EOFException;

final class zzefy implements Runnable {
    private /* synthetic */ zzefu zzncm;
    private /* synthetic */ zzeok zznco;

    zzefy(zzefu zzefu, zzeok zzeok) {
        this.zzncm = zzefu;
        this.zznco = zzeok;
    }

    public final void run() {
        if (this.zznco.getCause() == null || !(this.zznco.getCause() instanceof EOFException)) {
            this.zzncm.zznck.zzmxz.zzb("WebSocket error.", this.zznco, new Object[0]);
        } else {
            this.zzncm.zznck.zzmxz.zzb("WebSocket reached EOF.", (Throwable) null, new Object[0]);
        }
        this.zzncm.zznck.zzbxn();
    }
}
