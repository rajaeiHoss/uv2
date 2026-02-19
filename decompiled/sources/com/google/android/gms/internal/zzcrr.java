package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.nearby.Nearby;

abstract class zzcrr<R extends Result> extends zzm<R, zzcov> {
    public zzcrr(GoogleApiClient googleApiClient) {
        super((Api<?>) Nearby.CONNECTIONS_API, googleApiClient);
    }

}
