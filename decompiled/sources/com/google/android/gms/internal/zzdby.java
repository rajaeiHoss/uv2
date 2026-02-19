package com.google.android.gms.internal;

final class zzdby implements Runnable {
    private /* synthetic */ zzdbx zzkzg;

    zzdby(zzdbx zzdbx) {
        this.zzkzg = zzdbx;
    }

    public final void run() {
        zzdal.v("App's UI deactivated. Dispatching hits.");
        this.zzkzg.zzkyv.zzkym.dispatch();
    }
}
