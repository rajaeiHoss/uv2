package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzam implements zzbo<Leaderboards.LeaderboardMetadataResult, Leaderboard> {
    zzam() {
    }

    private static Leaderboard zza(Leaderboards.LeaderboardMetadataResult leaderboardMetadataResult) {
        if (leaderboardMetadataResult == null) {
            return null;
        }
        LeaderboardBuffer leaderboards = leaderboardMetadataResult.getLeaderboards();
        if (leaderboards != null) {
            try {
                if (leaderboards.getCount() > 0) {
                    return (Leaderboard) ((Leaderboard) leaderboards.get(0)).freeze();
                }
            } finally {
                if (leaderboards != null) {
                    leaderboards.release();
                }
            }
        }
        if (leaderboards != null) {
            leaderboards.release();
        }
        return null;
    }

    public final Leaderboard zzb(Leaderboards.LeaderboardMetadataResult result) {
        return zza(result);
    }
}
