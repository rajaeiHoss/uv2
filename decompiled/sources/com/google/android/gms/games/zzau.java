package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzau extends zzac<Player> {
    zzau(PlayersClient playersClient) {
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Player> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(gamesClientImpl.zzatg());
    }
}
