package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.zze;

final class zzcpb extends zzcps<ConnectionLifecycleCallback> {
    private /* synthetic */ zzcsz zzjxr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpb(zzcox zzcox, zzcsz zzcsz) {
        super();
        this.zzjxr = zzcsz;
    }

    public final void zzu(ConnectionLifecycleCallback connectionLifecycleCallback) {
        this.zzjxr.zzbde();
        new zze(this.zzjxr.getQuality());
    }
}
