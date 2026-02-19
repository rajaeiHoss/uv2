package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;

abstract class zzcgj extends LocationServices.zza<Status> {
    public zzcgj(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final Status zzb(Status status) {
        return status;
    }
}
