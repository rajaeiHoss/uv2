package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Players;

final class zzaz implements zzbo<Players.LoadPlayersResult, Player> {
    zzaz() {
    }

    private static Player zza(Players.LoadPlayersResult loadPlayersResult) {
        if (loadPlayersResult == null) {
            return null;
        }
        PlayerBuffer players = loadPlayersResult.getPlayers();
        if (players != null) {
            try {
                if (players.getCount() > 0) {
                    return (Player) ((Player) players.get(0)).freeze();
                }
            } finally {
                if (players != null) {
                    players.release();
                }
            }
        }
        if (players != null) {
            players.release();
        }
        return null;
    }

    public final Player zzb(Players.LoadPlayersResult result) {
        return zza(result);
    }
}
