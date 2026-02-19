package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

abstract class zzdh extends Games.zza<TurnBasedMultiplayer.CancelMatchResult> {
    /* access modifiers changed from: private */
    public final String zzbzd;

    public zzdh(String str, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzbzd = str;
    }

    public final /* synthetic */ TurnBasedMultiplayer.CancelMatchResult zzb(Status status) {
        return new zzdi(this, status);
    }
}
