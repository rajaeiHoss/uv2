package com.google.android.gms.games;

import com.google.android.gms.games.internal.zzo;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzan implements zzo<Leaderboards.LeaderboardMetadataResult> {
    zzan() {
    }

    public final void release(Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
        if (leaderboardMetadataResult.getLeaderboards() != null) {
            leaderboardMetadataResult.getLeaderboards().release();
        }
    }
}
