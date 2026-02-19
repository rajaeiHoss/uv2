package com.google.android.gms.nearby.connection;

import com.google.android.gms.common.api.Status;

public final class ConnectionResolution {
    private final Status zzefs;

    public ConnectionResolution(Status status) {
        this.zzefs = status;
    }

    public final Status getStatus() {
        return this.zzefs;
    }
}
