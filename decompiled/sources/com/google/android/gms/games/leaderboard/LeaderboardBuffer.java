package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

public final class LeaderboardBuffer extends zzg<Leaderboard> {
    public LeaderboardBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public final String zzalj() {
        return "external_leaderboard_id";
    }

    /* access modifiers changed from: protected */
    public final Leaderboard zzl(int i, int i2) {
        return new LeaderboardRef(this.zzfxb, i, i2);
    }
}
