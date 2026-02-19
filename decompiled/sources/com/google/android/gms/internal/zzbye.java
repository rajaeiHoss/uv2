package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbye<R extends Result> extends zzm<R, zzbyb> {
    public zzbye(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbyb.API, googleApiClient);
    }

}
