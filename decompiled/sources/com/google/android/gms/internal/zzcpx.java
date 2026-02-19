package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

final class zzcpx implements Connections.StartAdvertisingResult {
    private final Status zzefs;
    private final String zzjyd;

    zzcpx(Status status, String str) {
        this.zzefs = status;
        this.zzjyd = str;
    }

    public final String getLocalEndpointName() {
        return this.zzjyd;
    }

    public final Status getStatus() {
        return this.zzefs;
    }
}
