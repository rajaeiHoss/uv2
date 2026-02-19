package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

abstract class zzdn extends Games.zza<TurnBasedMultiplayer.LoadMatchResult> {
    private zzdn(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzdn(GoogleApiClient googleApiClient, zzcx zzcx) {
        this(googleApiClient);
    }

    public final /* synthetic */ TurnBasedMultiplayer.LoadMatchResult zzb(Status status) {
        return new zzdo(this, status);
    }
}
