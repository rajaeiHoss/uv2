package com.google.android.gms.internal;

final class zzdbw implements Runnable {
    private /* synthetic */ zzdbv zzkzf;

    zzdbw(zzdbv zzdbv) {
        this.zzkzf = zzdbv;
    }

    public final void run() {
        if (this.zzkzf.zzkyv.zzkyr == 1 || this.zzkzf.zzkyv.zzkyr == 2) {
            int unused = this.zzkzf.zzkyv.zzkyr = 4;
            zzdal.e("Container load timed out after 5000ms.");
            while (!this.zzkzf.zzkyv.zzkys.isEmpty()) {
                this.zzkzf.zzkyv.zzkvr.execute((Runnable) this.zzkzf.zzkyv.zzkys.remove());
            }
        }
    }
}
