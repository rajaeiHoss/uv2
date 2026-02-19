package com.google.android.gms.internal;

final class zzdbv implements Runnable {
    final /* synthetic */ zzdbo zzkyv;

    zzdbv(zzdbo zzdbo) {
        this.zzkyv = zzdbo;
    }

    public final void run() {
        this.zzkyv.zzkvr.execute(new zzdbw(this));
    }
}
