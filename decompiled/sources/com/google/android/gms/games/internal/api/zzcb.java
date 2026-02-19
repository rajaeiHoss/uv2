package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.request.Requests;

abstract class zzcb extends Games.zza<Requests.UpdateRequestsResult> {
    private zzcb(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzcb(GoogleApiClient googleApiClient, zzbw zzbw) {
        this(googleApiClient);
    }

    public final /* synthetic */ Requests.UpdateRequestsResult zzb(Status status) {
        return new zzcc(this, status);
    }
}
