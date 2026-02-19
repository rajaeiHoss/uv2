package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbxb<R extends Result> extends zzm<R, zzbwy> {
    public zzbxb(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbwy.API, googleApiClient);
    }

}
