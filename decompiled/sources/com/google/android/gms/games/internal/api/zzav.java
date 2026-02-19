package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.leaderboard.Leaderboards;

public abstract class zzav extends Games.zza<Leaderboards.SubmitScoreResult> {
    protected zzav(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final /* synthetic */ Leaderboards.SubmitScoreResult zzb(Status status) {
        return new zzaw(this, status);
    }
}
