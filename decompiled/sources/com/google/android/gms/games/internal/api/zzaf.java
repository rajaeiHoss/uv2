package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Invitations;

abstract class zzaf extends Games.zza<Invitations.LoadInvitationsResult> {
    private zzaf(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzaf(GoogleApiClient googleApiClient, zzae zzae) {
        this(googleApiClient);
    }

    public final /* synthetic */ Invitations.LoadInvitationsResult zzb(Status status) {
        return new zzag(this, status);
    }
}
