package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzai extends zzac<Intent> {
    private /* synthetic */ String zzhsx;
    private /* synthetic */ int zzhsy;
    private /* synthetic */ int zzhsz;

    zzai(LeaderboardsClient leaderboardsClient, String str, int i, int i2) {
        this.zzhsx = str;
        this.zzhsy = i;
        this.zzhsz = i2;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(gamesClientImpl.zzj(this.zzhsx, this.zzhsy, this.zzhsz));
    }
}
