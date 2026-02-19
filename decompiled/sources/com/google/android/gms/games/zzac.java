package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzac implements zzbo<Leaderboards.LoadPlayerScoreResult, LeaderboardScore> {
    zzac() {
    }

    public final LeaderboardScore zzb(Leaderboards.LoadPlayerScoreResult result) {
        LeaderboardScore score;
        if (result == null || (score = result.getScore()) == null) {
            return null;
        }
        return (LeaderboardScore) score.freeze();
    }
}
