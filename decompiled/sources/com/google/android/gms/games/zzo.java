package com.google.android.gms.games;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzac;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends zzac<Void> {
    private /* synthetic */ View zzhsl;

    zzo(GamesClient gamesClient, View view) {
        this.zzhsl = view;
    }

    /* access modifiers changed from: protected */
    public final void zza(GamesClientImpl gamesClientImpl, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        gamesClientImpl.zzz(this.zzhsl);
        taskCompletionSource.setResult(null);
    }
}
