package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzaf implements zzbo<Leaderboards.LoadScoresResult, LeaderboardsClient.LeaderboardScores> {
    zzaf() {
    }

    public final LeaderboardsClient.LeaderboardScores zzb(Leaderboards.LoadScoresResult result) {
        Leaderboard leaderboard = null;
        if (result == null) {
            return null;
        }
        if (result.getLeaderboard() != null) {
            leaderboard = (Leaderboard) result.getLeaderboard().freeze();
        }
        return new LeaderboardsClient.LeaderboardScores(leaderboard, result.getScores());
    }
}
