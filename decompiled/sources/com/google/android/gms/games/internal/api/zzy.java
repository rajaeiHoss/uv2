package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;

public final class zzy implements GamesMetadata {
    public final Game getCurrentGame(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzatj();
    }

    public final PendingResult<GamesMetadata.LoadGamesResult> loadGame(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzz(this, googleApiClient));
    }
}
