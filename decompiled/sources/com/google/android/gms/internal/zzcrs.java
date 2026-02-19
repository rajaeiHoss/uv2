package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.connection.Connections;

abstract class zzcrs extends zzcrr<Connections.StartAdvertisingResult> {
    private zzcrs(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzcrs(GoogleApiClient googleApiClient, zzcqx zzcqx) {
        this(googleApiClient);
    }

    public final Connections.StartAdvertisingResult zzb(Status status) {
        return new zzcrt(this, status);
    }
}
