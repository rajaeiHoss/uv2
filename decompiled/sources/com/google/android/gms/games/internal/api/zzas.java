package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzas implements Leaderboards.LoadPlayerScoreResult {
    private /* synthetic */ Status zzetp;

    zzas(zzar zzar, Status status) {
        this.zzetp = status;
    }

    public final LeaderboardScore getScore() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
