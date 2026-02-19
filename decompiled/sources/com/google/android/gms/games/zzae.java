package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

final class zzae implements zzbo<Leaderboards.SubmitScoreResult, ScoreSubmissionData> {
    zzae() {
    }

    public final ScoreSubmissionData zzb(Leaderboards.SubmitScoreResult result) {
        if (result == null) {
            return null;
        }
        return result.getScoreData();
    }
}
