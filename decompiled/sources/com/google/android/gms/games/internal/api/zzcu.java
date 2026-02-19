package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.stats.Stats;

abstract class zzcu extends Games.zza<Stats.LoadPlayerStatsResult> {
    private zzcu(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzcu(GoogleApiClient googleApiClient, zzct zzct) {
        this(googleApiClient);
    }

    public final /* synthetic */ Stats.LoadPlayerStatsResult zzb(Status status) {
        return new zzcv(this, status);
    }
}
