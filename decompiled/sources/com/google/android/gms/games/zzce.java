package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzce implements zzbo<TurnBasedMultiplayer.LoadMatchesResult, LoadMatchesResponse> {
    zzce() {
    }

    public final LoadMatchesResponse zzb(TurnBasedMultiplayer.LoadMatchesResult result) {
        if (result == null) {
            return null;
        }
        return result.getMatches();
    }
}
