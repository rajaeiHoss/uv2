package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.zzr;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcp extends zzr<OnTurnBasedMatchUpdateReceivedListener> {
    zzcp(TurnBasedMultiplayerClient turnBasedMultiplayerClient, zzck zzck) {
        super(zzck);
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException, SecurityException {
        gamesClientImpl.zzatr();
        taskCompletionSource.setResult(true);
    }
}
