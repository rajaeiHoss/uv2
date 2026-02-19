package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzch implements zzbo<TurnBasedMultiplayer.CancelMatchResult, String> {
    zzch() {
    }

    public final String zzb(TurnBasedMultiplayer.CancelMatchResult result) {
        if (result == null) {
            return null;
        }
        return result.getMatchId();
    }
}
