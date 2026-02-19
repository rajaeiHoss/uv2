package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzal implements zzbo<Leaderboards.LeaderboardMetadataResult, LeaderboardBuffer> {
    zzal() {
    }

    public final LeaderboardBuffer zzb(Leaderboards.LeaderboardMetadataResult result) {
        if (result == null) {
            return null;
        }
        return result.getLeaderboards();
    }
}
