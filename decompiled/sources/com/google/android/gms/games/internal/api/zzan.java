package com.google.android.gms.games.internal.api;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;

final class zzan extends zzat {
    private /* synthetic */ int zziao;
    private /* synthetic */ LeaderboardScoreBuffer zziap;
    private /* synthetic */ int zziaq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzan(zzah zzah, GoogleApiClient googleApiClient, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) {
        super(googleApiClient, (zzai) null);
        this.zziap = leaderboardScoreBuffer;
        this.zziao = i;
        this.zziaq = i2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(GamesClientImpl gamesClientImpl) throws RemoteException {
        gamesClientImpl.zza((zzn<Leaderboards.LoadScoresResult>) this, this.zziap, this.zziao, this.zziaq);
    }
}
