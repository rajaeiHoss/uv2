package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbwv<R extends Result> extends zzm<R, zzbws> {
    public zzbwv(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbws.API, googleApiClient);
    }

}
