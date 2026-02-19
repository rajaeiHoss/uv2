package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.DiscoveredEndpointInfo;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzcpj extends zzcps<EndpointDiscoveryCallback> {
    private /* synthetic */ zzctl zzjxx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcpj(zzcpi zzcpi, zzctl zzctl) {
        super();
        this.zzjxx = zzctl;
    }

    public final void zzu(EndpointDiscoveryCallback endpointDiscoveryCallback) {
        String zzbdi;
        DiscoveredEndpointInfo discoveredEndpointInfo;
        if ("__UNRECOGNIZED_BLUETOOTH_DEVICE__".equals(this.zzjxx.zzbdi())) {
            zzbdi = this.zzjxx.zzbdi();
            discoveredEndpointInfo = new DiscoveredEndpointInfo(this.zzjxx.getServiceId(), this.zzjxx.zzbdj());
        } else {
            zzbdi = this.zzjxx.zzbdi();
            discoveredEndpointInfo = new DiscoveredEndpointInfo(this.zzjxx.getServiceId(), this.zzjxx.getEndpointName());
        }
        endpointDiscoveryCallback.onEndpointFound(zzbdi, discoveredEndpointInfo);
    }
}
