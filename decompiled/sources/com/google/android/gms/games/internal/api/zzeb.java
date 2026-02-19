package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.video.Videos;

abstract class zzeb extends Games.zza<Videos.CaptureStateResult> {
    private zzeb(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzeb(GoogleApiClient googleApiClient, zzdu zzdu) {
        this(googleApiClient);
    }

    public final /* synthetic */ Videos.CaptureStateResult zzb(Status status) {
        return new zzec(this, status);
    }
}
