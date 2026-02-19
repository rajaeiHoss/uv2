package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbxs<R extends Result> extends zzm<R, zzbxp> {
    public zzbxs(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbxp.API, googleApiClient);
    }

}
