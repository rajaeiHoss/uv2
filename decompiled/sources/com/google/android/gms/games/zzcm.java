package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzcm implements zzbo<TurnBasedMultiplayer.UpdateMatchResult, TurnBasedMatch> {
    zzcm() {
    }

    public final TurnBasedMatch zzb(TurnBasedMultiplayer.UpdateMatchResult result) {
        TurnBasedMatch match;
        if (result == null || (match = result.getMatch()) == null) {
            return null;
        }
        return (TurnBasedMatch) match.freeze();
    }
}
