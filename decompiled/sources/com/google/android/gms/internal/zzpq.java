package com.google.android.gms.internal;

final class zzpq implements Runnable {
    private /* synthetic */ zzpo zzbyq;

    zzpq(zzpo zzpo) {
        this.zzbyq = zzpo;
    }

    public final void run() {
        if (this.zzbyq.zzbyi != null) {
            this.zzbyq.zzbyi.zzks();
            this.zzbyq.zzbyi.zzkr();
        }
        zzpv unused = this.zzbyq.zzbyi = null;
    }
}
