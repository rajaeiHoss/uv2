package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzcpr extends zzcps<Connections.MessageListener> {
    private /* synthetic */ zzctj zzjxq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpr(zzcpp zzcpp, zzctj zzctj) {
        super();
        this.zzjxq = zzctj;
    }

    public final void zzu(Connections.MessageListener messageListener) {
        messageListener.onDisconnected(this.zzjxq.zzbde());
    }
}
