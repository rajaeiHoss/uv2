package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;

abstract class zzaa extends Games.zza<GamesMetadata.LoadGamesResult> {
    private zzaa(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzaa(GoogleApiClient googleApiClient, zzz zzz) {
        this(googleApiClient);
    }

    public final /* synthetic */ GamesMetadata.LoadGamesResult zzb(Status status) {
        return new zzab(this, status);
    }
}
