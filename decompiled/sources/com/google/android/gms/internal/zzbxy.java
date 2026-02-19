package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzbxy<R extends Result> extends zzm<R, zzbxv> {
    public zzbxy(GoogleApiClient googleApiClient) {
        super((Api<?>) zzbxv.API, googleApiClient);
    }

}
