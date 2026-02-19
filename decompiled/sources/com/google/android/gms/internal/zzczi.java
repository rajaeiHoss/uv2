package com.google.android.gms.internal;

final class zzczi implements Runnable {
    private /* synthetic */ zzczg zzkvy;

    zzczi(zzczg zzczg) {
        this.zzkvy = zzczg;
    }

    public final void run() {
        this.zzkvy.zzkvr.execute(new zzczm(this.zzkvy, (zzczh) null));
    }
}
