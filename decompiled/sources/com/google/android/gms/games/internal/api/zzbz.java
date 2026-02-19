package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.request.Requests;

abstract class zzbz extends Games.zza<Requests.LoadRequestsResult> {
    private zzbz(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzbz(GoogleApiClient googleApiClient, zzbw zzbw) {
        this(googleApiClient);
    }

    public final /* synthetic */ Requests.LoadRequestsResult zzb(Status status) {
        return new zzca(this, status);
    }
}
