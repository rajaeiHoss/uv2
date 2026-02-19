package com.google.android.gms.internal;

final class zzdbg implements Runnable {
    private /* synthetic */ zzdbe zzkyg;

    zzdbg(zzdbe zzdbe) {
        this.zzkyg = zzdbe;
    }

    public final void run() {
        boolean unused = this.zzkyg.zzkyb = false;
        this.zzkyg.zzkxz.dispatch();
    }
}
