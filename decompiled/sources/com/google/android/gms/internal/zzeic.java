package com.google.android.gms.internal;

final class zzeic implements Runnable {
    private /* synthetic */ zzegx zznfw;

    zzeic(zzegx zzegx) {
        this.zznfw = zzegx;
    }

    public final void run() {
        this.zznfw.interrupt();
    }
}
