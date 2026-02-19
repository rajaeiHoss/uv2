package com.google.android.gms.internal;

final class zzdnv implements Runnable {
    private /* synthetic */ zzdnt zzlxz;

    zzdnv(zzdnt zzdnt) {
        this.zzlxz = zzdnt;
    }

    public final void run() {
        this.zzlxz.doFrame(System.nanoTime());
    }
}
