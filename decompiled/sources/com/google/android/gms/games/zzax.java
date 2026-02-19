package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Players;

final class zzax implements zzbo<Players.LoadPlayersResult, PlayerBuffer> {
    zzax() {
    }

    public final PlayerBuffer zzb(Players.LoadPlayersResult result) {
        if (result == null) {
            return null;
        }
        return result.getPlayers();
    }
}
