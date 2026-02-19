package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcr extends zzac<Void> {
    private /* synthetic */ String zzhuo;

    zzcr(TurnBasedMultiplayerClient turnBasedMultiplayerClient, String str) {
        this.zzhuo = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zzs(this.zzhuo, 1);
        taskCompletionSource.setResult(null);
    }
}
