package com.google.android.gms.internal;

final class zzbdi implements Runnable {
    private /* synthetic */ zzbdg zzfly;

    zzbdi(zzbdg zzbdg) {
        this.zzfly = zzbdg;
    }

    public final void run() {
        this.zzfly.zzflx = false;
        this.zzfly.zzbg(this.zzfly.zzz(this.zzfly.zzata.elapsedRealtime()));
    }
}
