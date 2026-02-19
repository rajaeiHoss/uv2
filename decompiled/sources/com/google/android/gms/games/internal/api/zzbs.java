package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.quest.Quests;

abstract class zzbs extends Games.zza<Quests.LoadQuestsResult> {
    private zzbs(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzbs(GoogleApiClient googleApiClient, zzbk zzbk) {
        this(googleApiClient);
    }

    public final /* synthetic */ Quests.LoadQuestsResult zzb(Status status) {
        return new zzbt(this, status);
    }
}
