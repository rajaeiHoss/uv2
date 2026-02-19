package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzcpk extends zzcps<EndpointDiscoveryCallback> {
    private /* synthetic */ zzctn zzjxy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpk(zzcpi zzcpi, zzctn zzctn) {
        super();
        this.zzjxy = zzctn;
    }

    public final void zzu(EndpointDiscoveryCallback endpointDiscoveryCallback) {
        endpointDiscoveryCallback.onEndpointLost(this.zzjxy.zzbdi());
    }
}
