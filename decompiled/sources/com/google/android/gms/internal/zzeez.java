package com.google.android.gms.internal;

final class zzeez implements Runnable {
    private /* synthetic */ boolean zznay;
    final /* synthetic */ zzeey zznaz;

    zzeez(zzeey zzeey, boolean z) {
        this.zznaz = zzeey;
        this.zznay = z;
    }

    public final void run() {
        this.zznaz.zzmxz.zzb("Trying to fetch auth token", (Throwable) null, new Object[0]);
        zzeet.zzc(this.zznaz.zznah == zzefi.Disconnected, "Not in disconnected state: %s", this.zznaz.zznah);
        zzefi unused = this.zznaz.zznah = zzefi.GettingToken;
        zzeey.zzc(this.zznaz);
        this.zznaz.zzmzv.zza(this.zznay, new zzefa(this, this.zznaz.zznat));
    }
}
