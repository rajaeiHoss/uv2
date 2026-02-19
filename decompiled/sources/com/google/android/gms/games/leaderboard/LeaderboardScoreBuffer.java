package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class LeaderboardScoreBuffer extends AbstractDataBuffer<LeaderboardScore> {
    private final zza zzidz;

    public LeaderboardScoreBuffer(DataHolder dataHolder) {
        super(dataHolder);
        this.zzidz = new zza(dataHolder.zzahs());
    }

    public final LeaderboardScore get(int i) {
        return new LeaderboardScoreRef(this.zzfxb, i);
    }

    public final zza zzavp() {
        return this.zzidz;
    }
}
