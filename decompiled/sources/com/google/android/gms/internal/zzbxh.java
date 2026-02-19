package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbxh<R extends Result> extends zzm<R, zzbxe> {
    public zzbxh(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbxe.API, googleApiClient);
    }
}
