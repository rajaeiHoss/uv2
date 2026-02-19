package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.Stats;

final class zzcv implements Stats.LoadPlayerStatsResult {
    private /* synthetic */ Status zzetp;

    zzcv(zzcu zzcu, Status status) {
        this.zzetp = status;
    }

    public final PlayerStats getPlayerStats() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
