package com.google.android.gms.games;

import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzao extends zzac<Void> {
    private /* synthetic */ int zzhte;

    zzao(NotificationsClient notificationsClient, int i) {
        this.zzhte = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zzdt(this.zzhte);
        taskCompletionSource.setResult(null);
    }
}
