package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Players;

abstract class zzbh extends Games.zza<Players.LoadPlayersResult> {
    zzbh(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final Players.LoadPlayersResult zzb(Status status) {
        return new zzbi(this, status);
    }
}
