package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaa;

final class zzadf implements Runnable {
    private /* synthetic */ zzada zzcve;

    zzadf(zzada zzada) {
        this.zzcve = zzada;
    }

    public final void run() {
        if (this.zzcve.zzcvd != null) {
            this.zzcve.zzcvd.release();
            zzaa unused = this.zzcve.zzcvd = null;
        }
    }
}
