package com.google.android.gms.games;

import com.google.android.gms.games.internal.zzo;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;

final class zzcf implements zzo<TurnBasedMultiplayer.LoadMatchesResult> {
    zzcf() {
    }

    public final void release(TurnBasedMultiplayer.LoadMatchesResult result) {
        if (result != null && result.getMatches() != null) {
            result.getMatches().release();
        }
    }
}
