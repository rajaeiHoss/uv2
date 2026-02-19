package com.google.android.gms.games;

import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.zzo;

final class zzay implements zzo<Players.LoadPlayersResult> {
    zzay() {
    }

    public final void release(Players.LoadPlayersResult loadPlayersResult) {
        if (loadPlayersResult.getPlayers() != null) {
            loadPlayersResult.getPlayers().release();
        }
    }
}
