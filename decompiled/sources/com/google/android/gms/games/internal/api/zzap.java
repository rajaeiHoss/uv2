package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.Leaderboards;

abstract class zzap extends Games.zza<Leaderboards.LeaderboardMetadataResult> {
    private zzap(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzap(GoogleApiClient googleApiClient, zzai zzai) {
        this(googleApiClient);
    }

    public final /* synthetic */ Leaderboards.LeaderboardMetadataResult zzb(Status status) {
        return new zzaq(this, status);
    }
}
