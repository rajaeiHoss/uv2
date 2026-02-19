package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzaq implements Leaderboards.LeaderboardMetadataResult {
    private /* synthetic */ Status zzetp;

    zzaq(zzap zzap, Status status) {
        this.zzetp = status;
    }

    public final LeaderboardBuffer getLeaderboards() {
        return new LeaderboardBuffer(DataHolder.zzbz(14));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
