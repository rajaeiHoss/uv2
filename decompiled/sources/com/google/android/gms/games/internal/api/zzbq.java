package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.quest.Quests;

abstract class zzbq extends Games.zza<Quests.ClaimMilestoneResult> {
    private zzbq(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzbq(GoogleApiClient googleApiClient, zzbk zzbk) {
        this(googleApiClient);
    }

    public final /* synthetic */ Quests.ClaimMilestoneResult zzb(Status status) {
        return new zzbr(this, status);
    }
}
