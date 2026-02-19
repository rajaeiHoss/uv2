package com.google.android.gms.games;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.games.TurnBasedMultiplayerClient;
import com.google.android.gms.games.internal.zzn;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;

final class zzcv implements zzn<TurnBasedMatch> {
    zzcv() {
    }

    public final ApiException zza(Status status, TurnBasedMatch turnBasedMatch) {
        return status.getStatusCode() == 26593 ? new TurnBasedMultiplayerClient.MatchOutOfDateApiException(status, turnBasedMatch) : zzb.zzy(status);
    }
}
