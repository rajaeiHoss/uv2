package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzcph extends zzcps<Connections.ConnectionResponseCallback> {
    private /* synthetic */ zzctf zzjxv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcph(zzcpg zzcpg, zzctf zzctf) {
        super();
        this.zzjxv = zzctf;
    }

    public final void zzu(Connections.ConnectionResponseCallback connectionResponseCallback) {
        connectionResponseCallback.onConnectionResponse(this.zzjxv.zzbde(), zzcov.zzcm(this.zzjxv.getStatusCode()), this.zzjxv.zzbdh());
    }
}
