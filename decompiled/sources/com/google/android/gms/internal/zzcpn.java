package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.Connections;

final class zzcpn extends zzcps<Connections.EndpointDiscoveryListener> {
    private /* synthetic */ zzctl zzjxx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpn(zzcpm zzcpm, zzctl zzctl) {
        super();
        this.zzjxx = zzctl;
    }

    public final void zzu(Connections.EndpointDiscoveryListener endpointDiscoveryListener) {
        endpointDiscoveryListener.onEndpointFound(this.zzjxx.zzbdi(), this.zzjxx.getServiceId(), this.zzjxx.getEndpointName());
    }
}
