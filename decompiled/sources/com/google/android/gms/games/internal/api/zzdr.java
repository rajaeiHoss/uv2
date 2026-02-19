package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

abstract class zzdr extends Games.zza<TurnBasedMultiplayer.UpdateMatchResult> {
    private zzdr(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzdr(GoogleApiClient googleApiClient, zzcx zzcx) {
        this(googleApiClient);
    }

    public final /* synthetic */ TurnBasedMultiplayer.UpdateMatchResult zzb(Status status) {
        return new zzds(this, status);
    }
}
