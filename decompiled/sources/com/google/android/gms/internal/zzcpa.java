package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzcpa extends zzcps<ConnectionLifecycleCallback> {
    private /* synthetic */ zzctj zzjxq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpa(zzcox zzcox, zzctj zzctj) {
        super();
        this.zzjxq = zzctj;
    }

    public final void zzu(ConnectionLifecycleCallback connectionLifecycleCallback) {
        connectionLifecycleCallback.onDisconnected(this.zzjxq.zzbde());
    }
}
