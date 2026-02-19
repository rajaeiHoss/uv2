package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends zzac<Void> {
    private /* synthetic */ int zzhsk;

    zzn(GamesClient gamesClient, int i) {
        this.zzhsk = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zzds(this.zzhsk);
        taskCompletionSource.setResult(null);
    }
}
