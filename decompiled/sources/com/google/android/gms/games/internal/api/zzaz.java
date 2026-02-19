package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;

public final class zzaz implements Players {
    public final Intent getCompareProfileIntent(GoogleApiClient googleApiClient, Player player) {
        return Games.zzg(googleApiClient).zzb(new PlayerEntity(player));
    }

    public final Player getCurrentPlayer(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzath();
    }

    public final String getCurrentPlayerId(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzbn(true);
    }

    public final Intent getPlayerSearchIntent(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzatw();
    }

    public final PendingResult<Players.LoadPlayersResult> loadConnectedPlayers(GoogleApiClient googleApiClient, boolean z) {
        return googleApiClient.zzd(new zzbg(this, googleApiClient, z));
    }

    public final PendingResult<Players.LoadPlayersResult> loadInvitablePlayers(GoogleApiClient googleApiClient, int i, boolean z) {
        return googleApiClient.zzd(new zzbc(this, googleApiClient, i, z));
    }

    public final PendingResult<Players.LoadPlayersResult> loadMoreInvitablePlayers(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.zzd(new zzbd(this, googleApiClient, i));
    }

    public final PendingResult<Players.LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.zzd(new zzbf(this, googleApiClient, i));
    }

    public final PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.zzd(new zzba(this, googleApiClient, str));
    }

    public final PendingResult<Players.LoadPlayersResult> loadPlayer(GoogleApiClient googleApiClient, String str, boolean z) {
        return googleApiClient.zzd(new zzbb(this, googleApiClient, str, z));
    }

    public final PendingResult<Players.LoadPlayersResult> loadRecentlyPlayedWithPlayers(GoogleApiClient googleApiClient, int i, boolean z) {
        return googleApiClient.zzd(new zzbe(this, googleApiClient, i, z));
    }
}
