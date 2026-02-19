package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.Leaderboards;

abstract class zzat extends Games.zza<Leaderboards.LoadScoresResult> {
    private zzat(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzat(GoogleApiClient googleApiClient, zzai zzai) {
        this(googleApiClient);
    }

    public final /* synthetic */ Leaderboards.LoadScoresResult zzb(Status status) {
        return new zzau(this, status);
    }
}
