package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbxm<R extends Result> extends zzm<R, zzbxj> {
    public zzbxm(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbxj.API, googleApiClient);
    }

}
