package com.google.android.gms.internal;

import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

public abstract class zzblb extends zzm<FenceQueryResult, zzblg> {
    public zzblb(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbjs.API, googleApiClient);
    }

    public final FenceQueryResult zzb(Status status) {
        return new zzblc(this, status);
    }
}
