package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;

final class zzcpc extends zzcps<ConnectionLifecycleCallback> {
    private /* synthetic */ String zzjxs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpc(zzcox zzcox, String str) {
        super();
        this.zzjxs = str;
    }

    public final void zzu(ConnectionLifecycleCallback connectionLifecycleCallback) {
        connectionLifecycleCallback.onConnectionResult(this.zzjxs, new ConnectionResolution(zzcov.zzcm(13)));
    }
}
