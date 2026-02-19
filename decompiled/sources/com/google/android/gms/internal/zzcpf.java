package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzcpf extends zzcps<Connections.ConnectionRequestListener> {
    private /* synthetic */ zzctd zzjxt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpf(zzcpe zzcpe, zzctd zzctd) {
        super();
        this.zzjxt = zzctd;
    }

    public final void zzu(Connections.ConnectionRequestListener connectionRequestListener) {
        connectionRequestListener.onConnectionRequest(this.zzjxt.zzbde(), this.zzjxt.zzbdf(), this.zzjxt.zzbdh());
    }
}
