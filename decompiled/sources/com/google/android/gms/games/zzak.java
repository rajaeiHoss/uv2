package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzak extends zzac<Void> {
    private /* synthetic */ String zzhsx;
    private /* synthetic */ long zzhta;
    private /* synthetic */ String zzhtb;

    zzak(LeaderboardsClient leaderboardsClient, String str, long j, String str2) {
        this.zzhsx = str;
        this.zzhta = j;
        this.zzhtb = str2;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zza((zzn<Leaderboards.SubmitScoreResult>) null, this.zzhsx, this.zzhta, this.zzhtb);
    }
}
