package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;

final class zzaw implements Leaderboards.SubmitScoreResult {
    private /* synthetic */ Status zzetp;

    zzaw(zzav zzav, Status status) {
        this.zzetp = status;
    }

    public final ScoreSubmissionData getScoreData() {
        return new ScoreSubmissionData(DataHolder.zzbz(14));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
