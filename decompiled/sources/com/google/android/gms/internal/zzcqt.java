package com.google.android.gms.internal;

import com.google.android.gms.nearby.connection.ConnectionInfo;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionResolution;

final class zzcqt extends ConnectionLifecycleCallback {
    private /* synthetic */ zzcpz zzjyo;
    private final ConnectionLifecycleCallback zzjyr;

    zzcqt(zzcpz zzcpz, ConnectionLifecycleCallback connectionLifecycleCallback) {
        this.zzjyo = zzcpz;
        this.zzjyr = connectionLifecycleCallback;
    }

    public final void onConnectionInitiated(String str, ConnectionInfo connectionInfo) {
        if (connectionInfo.isIncomingConnection()) {
            this.zzjyo.zzkv(str);
        }
        this.zzjyr.onConnectionInitiated(str, connectionInfo);
    }

    public final void onConnectionResult(String str, ConnectionResolution connectionResolution) {
        if (!connectionResolution.getStatus().isSuccess()) {
            this.zzjyo.zzkw(str);
        }
        this.zzjyr.onConnectionResult(str, connectionResolution);
    }

    public final void onDisconnected(String str) {
        this.zzjyo.zzkw(str);
        this.zzjyr.onDisconnected(str);
    }
}
