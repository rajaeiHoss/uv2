package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.event.Events;

abstract class zzu extends Games.zza<Events.LoadEventsResult> {
    private zzu(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzu(GoogleApiClient googleApiClient, zzr zzr) {
        this(googleApiClient);
    }

    public final /* synthetic */ Events.LoadEventsResult zzb(Status status) {
        return new zzv(this, status);
    }
}
