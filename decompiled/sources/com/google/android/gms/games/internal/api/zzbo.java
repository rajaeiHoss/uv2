package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.quest.Quests;

abstract class zzbo extends Games.zza<Quests.AcceptQuestResult> {
    private zzbo(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzbo(GoogleApiClient googleApiClient, zzbk zzbk) {
        this(googleApiClient);
    }

    public final /* synthetic */ Quests.AcceptQuestResult zzb(Status status) {
        return new zzbp(this, status);
    }
}
