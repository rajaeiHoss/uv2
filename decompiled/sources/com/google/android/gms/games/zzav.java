package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzav extends zzac<Intent> {
    private /* synthetic */ Player zzhuh;

    zzav(PlayersClient playersClient, Player player) {
        this.zzhuh = player;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(gamesClientImpl.zza(new PlayerEntity(this.zzhuh)));
    }
}
