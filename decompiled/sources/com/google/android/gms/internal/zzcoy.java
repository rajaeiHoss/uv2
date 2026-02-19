package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final class zzcoy extends zzcps<ConnectionLifecycleCallback> {
    private /* synthetic */ zzctb zzjxo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcoy(zzcox zzcox, zzctb zzctb) {
        super();
        this.zzjxo = zzctb;
    }

    public final void zzu(ConnectionLifecycleCallback connectionLifecycleCallback) {
        connectionLifecycleCallback.onConnectionInitiated(this.zzjxo.zzbde(), new ConnectionInfo(this.zzjxo.zzbdf(), this.zzjxo.getAuthenticationToken(), this.zzjxo.zzbdg()));
    }
}
