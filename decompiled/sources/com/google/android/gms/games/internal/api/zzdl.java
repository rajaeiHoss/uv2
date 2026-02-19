package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

abstract class zzdl extends Games.zza<TurnBasedMultiplayer.LeaveMatchResult> {
    private zzdl(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzdl(GoogleApiClient googleApiClient, zzcx zzcx) {
        this(googleApiClient);
    }

    public final /* synthetic */ TurnBasedMultiplayer.LeaveMatchResult zzb(Status status) {
        return new zzdm(this, status);
    }
}
