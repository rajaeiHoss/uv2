package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.zzg;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.tasks.Task;

public class PlayerStatsClient extends zzp {
    private static final zzbo<Stats.LoadPlayerStatsResult, PlayerStats> zzhud = new zzas();

    PlayerStatsClient(Activity activity, Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    PlayerStatsClient(Context context, Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<AnnotatedData<PlayerStats>> loadPlayerStats(boolean z) {
        return zzg.zzb(Games.Stats.loadPlayerStats(zzahw(), z), zzhud);
    }
}
