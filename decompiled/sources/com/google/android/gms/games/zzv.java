package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.GamesMetadata;

final class zzv implements zzbo<GamesMetadata.LoadGamesResult, Game> {
    zzv() {
    }

    private static Game zza(GamesMetadata.LoadGamesResult loadGamesResult) {
        GameBuffer games;
        if (loadGamesResult == null || (games = loadGamesResult.getGames()) == null) {
            return null;
        }
        try {
            if (games.getCount() > 0) {
                return (Game) ((Game) games.get(0)).freeze();
            }
            games.release();
            return null;
        } finally {
            games.release();
        }
    }

    public final Game zzb(GamesMetadata.LoadGamesResult result) {
        return zza(result);
    }
}
