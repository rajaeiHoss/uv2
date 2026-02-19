package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.safetynet.SafetyNet;

abstract class zzcxa<R extends Result> extends zzm<R, zzcxs> {
    public zzcxa(GoogleApiClient googleApiClient) {
        super((Api<?>) SafetyNet.API, googleApiClient);
    }

}
