package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzah extends zzac<Intent> {
    private /* synthetic */ String zzhsx;
    private /* synthetic */ int zzhsy;

    zzah(LeaderboardsClient leaderboardsClient, String str, int i) {
        this.zzhsx = str;
        this.zzhsy = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(gamesClientImpl.zzj(this.zzhsx, this.zzhsy, -1));
    }
}
