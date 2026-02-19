package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.internal.zzo;
import com.google.android.gms.tasks.Task;

public class PlayersClient extends zzp {
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
    private static final zzbo<Players.LoadPlayersResult, PlayerBuffer> zzhue = new zzax();
    private static final zzo<Players.LoadPlayersResult> zzhuf = new zzay();
    private static final zzbo<Players.LoadPlayersResult, Player> zzhug = new zzaz();

    PlayersClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    PlayersClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Intent> getCompareProfileIntent(Player player) {
        return zza(new zzav(this, player));
    }

    public Task<Player> getCurrentPlayer() {
        return zza(new zzau(this));
    }

    public Task<String> getCurrentPlayerId() {
        return zza(new zzat(this));
    }

    public Task<Intent> getPlayerSearchIntent() {
        return zza(new zzaw(this));
    }

    public Task<AnnotatedData<PlayerBuffer>> loadMoreRecentlyPlayedWithPlayers(int i) {
        return zzg.zzc(Games.Players.loadMoreRecentlyPlayedWithPlayers(zzahw(), i), zzhue);
    }

    public Task<AnnotatedData<Player>> loadPlayer(String str) {
        return loadPlayer(str, false);
    }

    public Task<AnnotatedData<Player>> loadPlayer(String str, boolean z) {
        return zzg.zza(Games.Players.loadPlayer(zzahw(), str, z), zzhug, zzhuf);
    }

    public Task<AnnotatedData<PlayerBuffer>> loadRecentlyPlayedWithPlayers(int i, boolean z) {
        return zzg.zzc(Games.Players.loadRecentlyPlayedWithPlayers(zzahw(), i, z), zzhue);
    }
}
