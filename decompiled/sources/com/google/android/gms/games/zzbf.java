package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbf extends zzac<Void> {
    private /* synthetic */ String zzhuo;

    zzbf(RealTimeMultiplayerClient realTimeMultiplayerClient, String str) {
        this.zzhuo = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zzq(this.zzhuo, 0);
        taskCompletionSource.setResult(null);
    }
}
