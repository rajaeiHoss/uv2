package com.google.android.gms.games;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbi extends zzac<Intent> {
    private /* synthetic */ int zzhup;
    private /* synthetic */ int zzhuq;
    private /* synthetic */ boolean zzhur;

    zzbi(RealTimeMultiplayerClient realTimeMultiplayerClient, int i, int i2, boolean z) {
        this.zzhup = i;
        this.zzhuq = i2;
        this.zzhur = z;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Intent> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(gamesClientImpl.zzd(this.zzhup, this.zzhuq, this.zzhur));
    }
}
