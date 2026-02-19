package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;

final class zzcoz extends zzcps<ConnectionLifecycleCallback> {
    private /* synthetic */ Status zzetp;
    private /* synthetic */ zzcth zzjxp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcoz(zzcox zzcox, zzcth zzcth, Status status) {
        super();
        this.zzjxp = zzcth;
        this.zzetp = status;
    }

    public final void zzu(ConnectionLifecycleCallback connectionLifecycleCallback) {
        connectionLifecycleCallback.onConnectionResult(this.zzjxp.zzbde(), new ConnectionResolution(this.zzetp));
    }
}
