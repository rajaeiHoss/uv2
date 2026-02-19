package com.google.android.gms.internal;

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzavd<R extends Result> extends zzm<R, zzavk> {
    public zzavd(GoogleApiClient googleApiClient) {
        super((Api<?>) AppInvite.API, googleApiClient);
    }

}
