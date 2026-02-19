package com.google.android.gms.internal;

final class zzdnq extends zzdnt {
    private /* synthetic */ zzdnp zzlxw;

    zzdnq(zzdnp zzdnp) {
        this.zzlxw = zzdnp;
    }

    public final void doFrame(long j) {
        zzdnp.zza(this.zzlxw);
        zzdnp zzdnp = this.zzlxw;
        if (!zzdnp.zzb(zzdnp.animator) && !this.zzlxw.animator.isStarted() && !this.zzlxw.zzbme()) {
            if (this.zzlxw.zzlxs != null) {
                this.zzlxw.zzlxs.run();
            }
            this.zzlxw.animator.start();
        }
    }
}
