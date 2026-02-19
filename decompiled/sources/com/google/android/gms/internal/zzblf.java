package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

public abstract class zzblf extends zzm<Status, zzblg> {
    public zzblf(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbjs.API, googleApiClient);
    }

    public final Status zzb(Status status) {
        return status;
    }
}
