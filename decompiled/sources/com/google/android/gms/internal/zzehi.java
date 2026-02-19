package com.google.android.gms.internal;

final class zzehi implements Runnable {
    private /* synthetic */ zzegx zznfo;
    private /* synthetic */ zzehy zznfy;

    zzehi(zzegx zzegx, zzehy zzehy) {
        this.zznfo = zzegx;
        this.zznfy = zzehy;
    }

    public final void run() {
        zzegx zzegx = this.zznfo;
        zzegx.zze((zzegr) new zzejp(zzegx, this.zznfy.zzngn, zzelu.zzam(this.zznfy.zzmxa)));
    }
}
