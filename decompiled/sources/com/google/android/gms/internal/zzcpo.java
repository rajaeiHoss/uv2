package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzcpo extends zzcps<Connections.EndpointDiscoveryListener> {
    private /* synthetic */ zzctn zzjxy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpo(zzcpm zzcpm, zzctn zzctn) {
        super();
        this.zzjxy = zzctn;
    }

    public final void zzu(Connections.EndpointDiscoveryListener endpointDiscoveryListener) {
        endpointDiscoveryListener.onEndpointLost(this.zzjxy.zzbdi());
    }
}
