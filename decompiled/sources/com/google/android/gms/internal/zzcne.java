package com.google.android.gms.internal;

import android.os.Bundle;

final class zzcne extends zzcip {
    private /* synthetic */ zzcnd zzjse;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcne(zzcnd zzcnd, zzckj zzckj) {
        super(zzckj);
        this.zzjse = zzcnd;
    }

    public final void run() {
        zzcnd zzcnd = this.zzjse;
        zzcnd.zzwj();
        zzcnd.zzayp().zzbba().zzj("Session started, time", Long.valueOf(zzcnd.zzxx().elapsedRealtime()));
        zzcnd.zzayq().zzjmc.set(false);
        zzcnd.zzayd().zzd("auto", "_s", new Bundle());
        zzcnd.zzayq().zzjmd.set(zzcnd.zzxx().currentTimeMillis());
    }
}
