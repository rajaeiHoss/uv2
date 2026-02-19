package com.google.android.gms.internal;

final class zzczh implements Runnable {
    private /* synthetic */ zzczg zzkvy;

    zzczh(zzczg zzczg) {
        this.zzkvy = zzczg;
    }

    public final void run() {
        if (this.zzkvy.mState == 2) {
            this.zzkvy.zzkvv.dispatch();
        }
    }
}
