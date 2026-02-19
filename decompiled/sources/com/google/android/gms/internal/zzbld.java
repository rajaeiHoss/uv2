package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

public abstract class zzbld extends zzm<zzazy, zzblg> {
    public zzbld(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbjs.API, googleApiClient);
    }

    public final zzazy zzb(Status status) {
        return new zzble(this, status);
    }
}
