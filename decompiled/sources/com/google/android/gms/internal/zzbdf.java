package com.google.android.gms.internal;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

public abstract class zzbdf<R extends Result> extends zzm<R, zzbdp> {
    public zzbdf(GoogleApiClient googleApiClient) {
        super((Api<?>) Cast.API, googleApiClient);
    }

    public final void zzbj(int i) {
        setResult(zzb(new Status(2001)));
    }
}
