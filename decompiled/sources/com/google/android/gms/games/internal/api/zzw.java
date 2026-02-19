package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

abstract class zzw extends Games.zza<Result> {
    private zzw(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzw(GoogleApiClient googleApiClient, zzr zzr) {
        this(googleApiClient);
    }

    public final Result zzb(Status status) {
        return new zzx(this, status);
    }
}
