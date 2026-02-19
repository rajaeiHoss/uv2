package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;

final class zzas implements zzbo<Stats.LoadPlayerStatsResult, PlayerStats> {
    zzas() {
    }

    public final PlayerStats zzb(Stats.LoadPlayerStatsResult result) {
        PlayerStats playerStats;
        if (result == null || (playerStats = result.getPlayerStats()) == null) {
            return null;
        }
        return (PlayerStats) playerStats.freeze();
    }
}
