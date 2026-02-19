package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzcpl extends zzcps<EndpointDiscoveryCallback> {
    private /* synthetic */ String zzjxs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpl(zzcpi zzcpi, String str) {
        super();
        this.zzjxs = str;
    }

    public final void zzu(EndpointDiscoveryCallback endpointDiscoveryCallback) {
        endpointDiscoveryCallback.onEndpointLost(this.zzjxs);
    }
}
