package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.Leaderboards;

abstract class zzar extends Games.zza<Leaderboards.LoadPlayerScoreResult> {
    private zzar(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzar(GoogleApiClient googleApiClient, zzai zzai) {
        this(googleApiClient);
    }

    public final /* synthetic */ Leaderboards.LoadPlayerScoreResult zzb(Status status) {
        return new zzas(this, status);
    }
}
