package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseError;

final class zzehq implements Runnable {
    private /* synthetic */ zzelu zzngc;
    private /* synthetic */ zzejg zzngd;
    private /* synthetic */ zzehp zznge;

    zzehq(zzehp zzehp, zzelu zzelu, zzejg zzejg) {
        this.zznge = zzehp;
        this.zzngc = zzelu;
        this.zzngd = zzejg;
    }

    public final void run() {
        zzenn zzp = this.zznge.zznfo.zzney.zzp(this.zzngc.zzbvh());
        if (!zzp.isEmpty()) {
            this.zznge.zznfo.zzaw(this.zznge.zznfo.zznfj.zzi(this.zzngc.zzbvh(), zzp));
            this.zzngd.zzb((DatabaseError) null);
        }
    }
}
