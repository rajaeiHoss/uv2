package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.video.Videos;

abstract class zzdz extends Games.zza<Videos.CaptureCapabilitiesResult> {
    private zzdz(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzdz(GoogleApiClient googleApiClient, zzdu zzdu) {
        this(googleApiClient);
    }

    public final /* synthetic */ Videos.CaptureCapabilitiesResult zzb(Status status) {
        return new zzea(this, status);
    }
}
