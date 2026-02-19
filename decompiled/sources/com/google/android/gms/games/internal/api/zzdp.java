package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

abstract class zzdp extends Games.zza<TurnBasedMultiplayer.LoadMatchesResult> {
    private zzdp(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzdp(GoogleApiClient googleApiClient, zzcx zzcx) {
        this(googleApiClient);
    }

    public final /* synthetic */ TurnBasedMultiplayer.LoadMatchesResult zzb(Status status) {
        return new zzdq(this, status);
    }
}
