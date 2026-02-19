package com.google.android.gms.games;

import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.internal.zzo;

final class zzw implements zzo<GamesMetadata.LoadGamesResult> {
    zzw() {
    }

    public final void release(GamesMetadata.LoadGamesResult loadGamesResult) {
        if (loadGamesResult.getGames() != null) {
            loadGamesResult.getGames().release();
        }
    }
}
