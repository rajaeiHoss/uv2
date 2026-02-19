package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzcpd extends zzcps<ConnectionLifecycleCallback> {
    private /* synthetic */ String zzjxs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpd(zzcox zzcox, String str) {
        super();
        this.zzjxs = str;
    }

    public final void zzu(ConnectionLifecycleCallback connectionLifecycleCallback) {
        connectionLifecycleCallback.onDisconnected(this.zzjxs);
    }
}
