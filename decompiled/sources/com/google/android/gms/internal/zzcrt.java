package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

final class zzcrt implements Connections.StartAdvertisingResult {
    private /* synthetic */ Status zzetp;

    zzcrt(zzcrs zzcrs, Status status) {
        this.zzetp = status;
    }

    public final String getLocalEndpointName() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
